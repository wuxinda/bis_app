package com.bluemobi.serviceimpl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminRolePermissionDao;
import com.bluemobi.po.admin.AdminRolePermission;
import com.bluemobi.service.admin.AdminRolePermissionService;
import com.bluemobi.to.admin.AdminMenuPermissionTO;

/**
 * 【角色权限表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Service(value = "adminRolePermissionService")
public class AdminRolePermissionServiceImpl extends MybatisBaseServiceImpl implements AdminRolePermissionService {

    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminRolePermissionDao;
    }
    

	/**
	 * 获取菜单权限列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 上午1:21:00
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	@Override
	public List<AdminMenuPermissionTO> selectAdminMenuPermissionTOList(int roleId, int menuId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleId", roleId);
		map.put("menuId", menuId);
		List<AdminMenuPermissionTO> list = adminRolePermissionDao.selectAdminMenuPermissionTOList(map);
		return list;
	}


	/**
	 * 修改角色权限
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 上午10:15:40
	 * @param roleId 
	 * @param permissionId
	 * @param status  状态：1、勾选 0、取消勾选
	 * @return
	 */
	@Override
	public int changeRolePermission(int roleId, int permissionId, int status) {
		AdminRolePermission adminRolePermission = new AdminRolePermission();
		adminRolePermission.setRoleId(roleId);
		adminRolePermission.setPermissionId(permissionId);
		if(status==1){
			return adminRolePermissionDao.insert(adminRolePermission);
		}else{
			return adminRolePermissionDao.delete(adminRolePermission);
		}
	}


}
