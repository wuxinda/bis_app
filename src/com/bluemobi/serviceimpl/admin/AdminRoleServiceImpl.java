package com.bluemobi.serviceimpl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminRoleDao;
import com.bluemobi.po.admin.AdminRole;
import com.bluemobi.service.admin.AdminRoleService;

/**
 * 【角色表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Service(value = "adminRoleService")
public class AdminRoleServiceImpl extends MybatisBaseServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleDao adminRoleDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminRoleDao;
    }

	/**
	 * 根据用户类型查询角色列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-17 下午4:48:16
	 * @param userType 用户类型：0、后台用户 
	 * @return
	 */
	@Override
	public List<AdminRole> selectAdminRoleListByUserType(int userType) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userType", userType);
		List<AdminRole> list = adminRoleDao.selectObjectList(map);
		return list;
	}

}
