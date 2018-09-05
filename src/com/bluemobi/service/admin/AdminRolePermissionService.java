package com.bluemobi.service.admin;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.to.admin.AdminMenuPermissionTO;

/**
 * 【角色权限表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public interface AdminRolePermissionService extends MybatisBaseService {

	/**
	 * 获取菜单权限列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 上午1:21:00
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	List<AdminMenuPermissionTO> selectAdminMenuPermissionTOList(int roleId, int menuId);
	
	/**
	 * 修改角色权限
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 上午10:15:40
	 * @param roleId 
	 * @param permissionId
	 * @param status  状态：1、勾选 0、取消勾选
	 * @return
	 */
	int changeRolePermission(int roleId, int permissionId, int status);
	
}
