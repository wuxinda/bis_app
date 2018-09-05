package com.bluemobi.serviceimpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminUserRoleDao;
import com.bluemobi.service.admin.AdminUserRoleService;

/**
 * 【用户角色表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Service(value = "adminUserRoleService")
public class AdminUserRoleServiceImpl extends MybatisBaseServiceImpl implements AdminUserRoleService {

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminUserRoleDao;
    }

}
