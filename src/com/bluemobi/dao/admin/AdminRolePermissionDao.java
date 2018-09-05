package com.bluemobi.dao.admin;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.to.admin.AdminMenuPermissionTO;

/**
 * 【角色权限表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public interface AdminRolePermissionDao extends MyBatisBaseDao {

	/**
	 * 获取菜单权限列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 上午1:19:25
	 * @param map
	 * @return
	 */
	List<AdminMenuPermissionTO> selectAdminMenuPermissionTOList(Map<String,Object> map);
	
}
