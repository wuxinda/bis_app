package com.bluemobi.dao.admin;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.admin.AdminRole;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.to.admin.AdminMenuTO;

/**
 * 【后台用户】 数据访问对象 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-28 15:14:39
 *
 */
public interface AdminUserDao extends MyBatisBaseDao{    
    
    
    /**
     * 根据tokenId查询用户，用于自动登录
     * 
     * @author haojian
     * @date 2016-6-24 下午2:09:08
     * @param param
     * @return
     * @return CasUser
     */
    AdminUser selectUserByTokenId(Map<String, Object> map);
    
    
	/**
	 * 获取用户菜单列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 下午9:55:44
	 * @param map
	 * @return
	 */
	List<AdminMenuTO> selectAdminMenuTOList(Map<String,Object> map);

	/**
	 * 获得 用户在 某个菜单下面 的 权限按钮代码列表，用于页面显示或隐藏按钮
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-20 下午3:30:51
	 * @param map
	 * @return
	 */
	List<String> selectAdminPermissionCodeList(Map<String,Object> map);
	
	
	/**
	 * 查询用户角色
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-17 下午4:46:27
	 * @param map
	 * @return
	 */
	List<AdminRole> selectAdminRoleListByUserId(Map<String,Object> map);
	/**
	 * 用户登录状态重置
	 * @author tony
	 * @date 2016-9-17 下午4:46:27
	 * @param map
	 * @return
	 */
	void resetOnline(Map<String,Object> map);
	/**
	 * 通过库房查找用户
	 * @author tony
	 * @date 2016-9-17 下午4:46:27
	 * @param map
	 * @return
	 */
	List<Integer> getAdminUserByStoreId(Map<String,Object> map);
	
}


