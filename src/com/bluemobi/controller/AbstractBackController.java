package com.bluemobi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.appcore.util.AjaxUtil;
import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.to.admin.AdminMenuTO;

/**
 * 抽象的web控制器
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-26 下午5:14:41 
 *
 */
public abstract class AbstractBackController extends AbstractController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBackController.class);
    
    @Autowired
    private AdminUserService adminUserService;
    
    /**
     * 获取userId
     * @author haojian
     * @date 2015-10-15 上午10:09:30 
     * @return
     * @return int
     */
    public int getUserId() {
        
        int userid = 0;
        
        AdminUser adminUser = getAdminUser();
        if(adminUser!=null){
            userid =  adminUser.getUserId();
        }
        
        return userid;
    }
    
    /**
     * 获取用户名
     * @author haojian
     * @date 2015-12-2 上午9:48:47 
     * @param request
     * @return
     * @return String
     */
    public String getUserName(){
        
        String username = "";
        
        AdminUser adminUser = getAdminUser();
        if(adminUser!=null){
            username = adminUser.getUserName();
        }
        
        return username;
    }
    
    /**
     * 获取管理员用户
     * @author haojian
     * @date 2015-12-2 上午9:48:53 
     * @param request
     * @return
     * @return AdminUser
     */
    public AdminUser getAdminUser(){
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        AdminUser adminUser = (AdminUser) SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
        return adminUser;
        
    }

    
	public void initIndex(Model model) {
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        
        //1、查询用户在当前菜单下面的权限按钮列表
        List<String> codeList = adminUserService.selectAdminPermissionCodeList(this.getUserId(), request.getServletPath());
        model.addAttribute("codeList", codeList);
        
        //1、检测是否是ajax请求，是否需要重新加载数据
        if (AjaxUtil.checkIsAjax(request)) {
            LOGGER.info("ajax请求，不查询导航栏！");
            return;
        }
        
        //2、通过tokenID获得用户对象
        AdminUser adminUser = SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
        model.addAttribute("loggedInUser", adminUser);
        
        //3、设置用户菜单信息
        List<AdminMenuTO> adminMenuTOs = adminUserService.selectAdminMenuTOList(this.getUserId(), 0);
        model.addAttribute("adminMenuTOs", adminMenuTOs);
        
        //3,通过用户信息获得所有用户权限菜单数据
        //adminMenuService.selectObjectList(arg0);
       /* List<SystemNavigation> navigationList = SessionManager.getAttribute(request, AdminConstant.KEY_NAVIGATION_LIST);
        model.addAttribute("userNavsList", navigationList);*/
    }
	
	/**
	 * 判断某个用户是否有某个权限
	 * @author haoj 309444359@qq.com
	 * @date 2016-10-13 下午7:51:33
	 * @param permissionCode  从CodeConstant类中获取
	 * @return
	 */
	public boolean validatePermission(String permissionCode) {
		
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	     
	     List<String> codeList = adminUserService.selectAdminPermissionCodeList(this.getUserId(), request.getServletPath());
	     
	     if(codeList.contains(permissionCode)){
	    	 return true;
	     }else{
	    	 return false;
	     }
		
	}
    
}
