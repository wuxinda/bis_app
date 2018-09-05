package com.bluemobi.service.admin;


import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.admin.AdminRole;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.to.admin.AdminMenuTO;
import com.bluemobi.to.admin.AdminUserTO;

/**
 * 【后台用户】 服务类 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-28 15:14:39
 *
 */
public interface AdminUserService extends MybatisBaseService{    
    
    /**
     * 新增管理员用户
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:50:52
     * @param adminUser
     * @param roleIds
     * @param creator
     */
    void insertUser(AdminUser adminUser, Integer[] roleIds, int creator);
    
    /**
     * 修改管理员用户
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:51:03
     * @param user
     * @param roleIds
     * @param modifier
     */
    void updateUser(AdminUser user, Integer[] roleIds, int modifier);
    
    /**
     * 删除管理员用户
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:51:42
     * @param userId
     */
    void deleteUser(int userId);
    
    /**
     * 根据tokenId查询用户, 用于自动登录
     * 
     * @author haojian
     * @date 2016-6-24 下午2:16:26
     * @param userName
     * @return
     * @return AdminUser
     */
    AdminUser selectUserByTokenId(String tokenId);
    
	/**
	 * 获取用户菜单列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 下午9:59:29
	 * @param userId
	 * @param merchantId 平台商户id传 0 
	 * @return
	 */
	List<AdminMenuTO> selectAdminMenuTOList(int userId, int merchantId);
	
	/**
	 * 获得 用户在 某个菜单下面 的 权限按钮代码列表，用于页面显示或隐藏按钮
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-20 下午3:33:23
	 * @param userId
	 * @param url
	 * @return
	 */
	List<String> selectAdminPermissionCodeList(int userId, String url);
	
	/**
	 * 查询用户角色
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-17 下午4:46:27
	 * @param userId
	 * @return
	 */
	List<AdminRole> selectAdminRoleListByUserId(int userId);
	
	/**
	 * 修改密码
	 * @author daiq 309444359@qq.com
	 * @date 2016-9-17 下午4:46:27
	 * @param userId
	 * @return
	 */
	Boolean changePassword(AdminUserTO user);

	/**
	 * 手机端用户注册
	 * 
	 * @author haojian
	 * @date 2016-1-26 下午1:50:24
	 * @param casUser
	 * @return
	 * @return CasUser
	 */
	public AdminUser regiest(AdminUser casUser);
	/**
	 * 服务器关掉时用户在线状态重置
	 * 
	 * @author tony
	 * @date 2016-1-26 下午1:50:24
	 * @param casUser
	 * @return
	 * @return CasUser
	 */
	void resetOnline(Map<String,Object> map);
	/**
     * 获取所有人员列表
     */
    public List<AdminUser> selectUserName();
}

