package com.bluemobi.controller.admin;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.appcore.util.SessionManager;
import com.appcore.util.StringUtil;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.constant.BMTokenConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.service.admin.AdminRoleService;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.admin.AdminUserTO;



/**
 * 【管理员用户表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Controller
@RequestMapping("adminUser")
public class AdminUserController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminUserController.class);
    
    @Autowired
    private AdminUserService adminUserService;
    
    @Autowired
    private AdminRoleService adminRoleService;
  
    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【管理员用户表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        if(isAdminUser()){
        	return "admin/user.index";
        }else{
        	return "admin/user.index";
        }
    }
    
    /**
     * 是否是后台用户
     * @return boolean
     * @author baojing
     * @date 2016-10
     */
    private boolean isAdminUser(){
    	return this.getAdminUser().getUserType()==AdminConstant.USER_TYPE_ADMIN;
    }
    
    /**
     * 分页查询【管理员用户表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = adminUserService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【管理员用户表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer userId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("userId", userId); 
        model.addAttribute("adminUser", adminUserService.selectObject(map));
        return "admin/user.detail";
    }
    
    /**
     * 进入新增【管理员用户表】页面
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午6:53:48
     * @param model
     * @param userType 用户类型：1、平台 2、商户
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model,Integer userType) {
        // 加载公共数据
        initIndex(model);
        model.addAttribute("userType",userType);
        model.addAttribute("adminRoles",adminRoleService.selectAdminRoleListByUserType(userType));
        if(this.isAdminUser()){
        	return "admin/user.edit";
        }else{
        	return "admin/merchantUser.edit";
        }
    }
    
    /**
     * 新增【管理员用户表】数据
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:46:01
     * @param adminUser
     * @param roleIds
     * @param result
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdminUser(@ModelAttribute AdminUser adminUser, Integer[] roleIds, BindingResult result) {
        try {
            adminUserService.insertUser(adminUser, roleIds, this.getUserId());
            LOGGER.info("用户【{}】添加管理员用户表数据【{}】成功", new Object[] { this.getUserId(), adminUser } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加管理员用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminUser, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【管理员用户表】页面
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:47:16
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer userId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId); 
        AdminUser adminUser = adminUserService.selectObject(map);
        model.addAttribute("adminUser",adminUser);
        model.addAttribute("userAdminRoles",adminUserService.selectAdminRoleListByUserId(userId));
        //默认查询后台所有角色 0.后台用户 1.领导者端用户 2.操作者端用户
        model.addAttribute("adminRoles",adminRoleService.selectAdminRoleListByUserType(0));
        
        return "admin/user.edit";
    }
    /**
     * 修改【管理员用户表】数据
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:47:23
     * @param adminUser
     * @param roleIds
     * @param result
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdminUser(@ModelAttribute AdminUser adminUser, Integer[] roleIds, BindingResult result) {
        try {
            adminUserService.updateUser(adminUser, roleIds, this.getUserId());
            LOGGER.info("用户【{}】修改管理员用户表数据【{}】成功", new Object[] { this.getUserId(), adminUser } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改管理员用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), adminUser, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【管理员用户表】数据
     * @param userId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdminUser(Integer userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("userId", userId); 
            adminUserService.delete(map);
            LOGGER.info("用户【{}】删除管理员用户表数据【{}】成功", new Object[] { this.getUserId(), userId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除管理员用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), userId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    /**
     * 
     * @author daiq
     * @date 2016-1-16 上午10:15:47 
     * @param model
     * @param request
     * @param response
     * @return
     * @return String
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.GET)
    public String changePassword(Model model, String user) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	initIndex(model);
    	map.put("userId", user);
    	AdminUser adminUser = adminUserService.selectObject(map);
    	model.addAttribute("userId", adminUser.getUserId());
        return "admin/adminuser.changepwd";
    }
    
    /**
     * 
     * @author daiq
     * @date 2016-1-16 上午10:15:47 
     * @param model
     * @param request
     * @param response
     * @return
     * @return String
     */
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO changePassword(Model model, AdminUserTO user, HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", user.getUserId());
    	AdminUser adminUser = adminUserService.selectObject(map);
    	
    	String pwd = StringUtil.md5(user.getOldpass()) + adminUser.getSalt();
        if (!StringUtil.md5(pwd).equals(adminUser.getPassword())) {
            return ResultTO.newFailResultTO("密码错误", null);
        }
        user.setNewpass(StringUtil.md5(user.getNewpass()) + adminUser.getSalt());
    	try {
    		adminUserService.changePassword(user);
    		SessionManager.removeAttribute(request, AdminConstant.KEY_ADMIN_USER);
        	
            // 如果开启了自动登录，需要删除用户cookie中的tokenId
            if (BMTokenConstant.IS_AUTO_LOGIN) {
               // CookieUtil.removeCookie(request, response, BMTokenConstant.TOKEN_NAME, "/");
            }
    		LOGGER.info("用户【{}】修改密码【{}】成功", new Object[] {
					this.getUserId(), user });
    	} catch (Exception e) {
    		LOGGER.info("用户【{}】修改密码【{}】失败 Exception:【{}】", new Object[] {
    				this.getUserId(), e });
    		return ResultTO.newFailResultTO("修改失败", null);
    	}
    	return ResultTO.newSuccessResultTO("修改成功", null);
    }
    
    /**
     * 获取用户列表
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "getUserList", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getUserList(Map<String, Object> map) {
        List<AdminUser> list = new ArrayList<AdminUser>();
        try {
            list = adminUserService.selectObjectList(map);
            LOGGER.info("用户【{}】获取户表数据【{}】成功", new Object[] { this.getUserId(), null });
        } catch (Exception e) {
            LOGGER.error("用户【{}】获取用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), null, e });
            return ResultTO.newFailResultTO("获取失败", null);
        }
        return ResultTO.newSuccessResultTO("获取成功", list);
    }
    /**
     * 获取所有人员列表
     * @return
     */
    @RequestMapping(value = "getAdminUserName", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getAdminUserName() {
	List<AdminUser> list = adminUserService.selectUserName();
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
}
