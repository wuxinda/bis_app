package com.bluemobi.controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.util.AuthCodeUtil;
import com.appcore.util.CookieUtil;
import com.appcore.util.SessionManager;
import com.appcore.util.StringUtil;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.constant.BMTokenConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.admin.AdminToken;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.service.admin.AdminTokenService;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.to.ResultTO;

/**
 * 登陆
 * 
 * @author haojian
 * @date 2015-6-9 上午10:52:37
 * 
 */
@Controller
@RequestMapping("adminLogin")
public class AdminLoginController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    private AdminUserService adminUserService;
    
    @Autowired
    private AdminTokenService adminTokenService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String urlContinue) {

        model.addAttribute("urlContinue", urlContinue);

        return "admin/login";
    }

    /**
     * 获得验证码
     * 
     * @author haojian
     * @date 2015-9-25 下午2:25:49
     * @param request
     * @param response
     * @throws IOException
     * @return void
     */
    @RequestMapping("code")
    public void code(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 创建验证码，并将验证图片输出到客户端，将验证码md5后保存到客户端cookie
        AuthCodeUtil.createAuthCodeAndOutput(200, 70, 4, request, response);
        
    }

    /**
     * 用户登录
     * 
     * @author haojian
     * @date 2015-9-25 下午2:27:40
     * @param model
     * @param req
     * @param resp
     * @param captcha
     * @param username
     * @param password
     * @param urlContinue
     * @return
     * @throws IOException
     * @throws ServletException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @return ResultTO
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO login(Model model, HttpServletRequest request, HttpServletResponse response, 
            String captcha, String username, String password, String urlContinue) 
                    throws IOException, ServletException, IllegalAccessException, InvocationTargetException {

        //1、校验验证码是否正确
/*        boolean isValid = AuthCodeUtil.checkAuthCode(captcha, request, response);
        if (!isValid) {
            LOGGER.info("用户验证码输入错误");
            return ResultTO.newFailResultTO("验证码错误", null);
        }*/
        
        //2、校验用户名密码长度
        if (username.length()<3 || password.length()<6) {
            return ResultTO.newFailResultTO("请输入正确的用户名和密码", null);
        }
        
        //3、校验用户名密码
        //查询用户信息
        AdminUser user = null;
        Map<String, String> reqMap = new HashMap<String, String>();
        reqMap.put("userName", username);
        List<AdminUser> userList = adminUserService.selectObjectList(reqMap);
        if (!userList.isEmpty()) {
            user = userList.get(0);
        }
        if (user == null ) {
            return ResultTO.newFailResultTO("用户名不存在", null);
        } else if (user.getStatus() < 1) {
            return ResultTO.newFailResultTO("用户已被禁止", null);
        }
        
        //校验密码
        String pwd = StringUtil.md5(password) + user.getSalt();
        if (!StringUtil.md5(pwd).equals(user.getPassword())) {
            return ResultTO.newFailResultTO("密码错误", null);
        }
        
        //4、登录成功后操作
        this.loginSuccess(request, response, user);
        
        //5、设置跳转uri
        String data = null;
        if (urlContinue == null || "".equals(urlContinue) || "/".equals(urlContinue) || "/adminLogin/logout".equals(urlContinue)) {
            data = "/";
        } else {
            data = urlContinue;
        }
        //String data = "/";
        LOGGER.info("登录成功返回信息：【{}】", new Object[]{data});
        return ResultTO.newSuccessResultTO("登录成功", data);
    }
    
    
    /**
     * 登录成功需要处理的事件
     * 
     * @author haojian
     * @date 2016-6-24 下午3:04:06
     * @param request
     * @param response
     * @param user
     * @return void
     */
    private void loginSuccess(HttpServletRequest request, HttpServletResponse response, AdminUser user) {
        // 1、修改最后登录时间
    	user.setIsOnline(1);
        //user.setLoginTime(new Date());
        adminUserService.asyncUpdate(user);
        // 2、将用户信息保存到session中
        SessionManager.setAttribute(request, AdminConstant.KEY_ADMIN_USER, user);
        // 3、生成登录token，并将tokenId写入到数据库和客户端
        if (BMTokenConstant.IS_AUTO_LOGIN) {// 系统是否开启自动登录
            this.createToken(response, user);
        }
    }

    /**
     * 创建token，用于自动登录
     * 
     * @author haojian
     * @date 2016-6-24 下午12:27:25
     * @param response
     * @param user
     * @return void
     */
    private void createToken(HttpServletResponse response, AdminUser user) {

        // 1、将tokenId写出到客户端,并存储到数据库
        String bmTokenId = UUID.randomUUID().toString().toUpperCase();
        CookieUtil.writeCookie(response, BMTokenConstant.TOKEN_NAME, bmTokenId, BMTokenConstant.TOKEN_EXPIRE_TIME, "/");

        // 2、创建token，并保存到数据库
        AdminToken token = new AdminToken();
        token.setUserId(user.getUserId());
        token.setTokenId(bmTokenId);
        token.setStatus(1);
        token.setCtime(new Date());
        token.setExpires(new Date(System.currentTimeMillis() + BMTokenConstant.TOKEN_EXPIRE_TIME * 1000));
        adminTokenService.asyncInsert(token);

    }



    /**
     * 退出登录
     * 如果客户端cookie里面有token，需要清除token
     * @author haojian
     * @date 2016-1-16 上午10:15:47 
     * @param model
     * @param request
     * @param response
     * @return
     * @return String
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response) {
        
    	LOGGER.info("用户退出登录");
    	
    	SessionManager.removeAttribute(request, AdminConstant.KEY_ADMIN_USER);
    	
        // 如果开启了自动登录，需要删除用户cookie中的tokenId
        if (BMTokenConstant.IS_AUTO_LOGIN) {
            //CookieUtil.removeCookie(request, response, BMTokenConstant.TOKEN_NAME, "/");
        }
        
        return "admin/login";
        
    }

}