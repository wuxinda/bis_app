package com.bluemobi.service.admin;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.admin.AdminRole;

/**
 * 【角色表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public interface AdminRoleService extends MybatisBaseService {
	
	/**
	 * 根据用户类型查询角色列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-17 下午4:48:16
	 * @param userType 用户类型：0、后台用户
	 * @return
	 */
	List<AdminRole> selectAdminRoleListByUserType(int userType);

}
