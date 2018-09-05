package com.bluemobi.serviceimpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminPermissionDao;
import com.bluemobi.service.admin.AdminPermissionService;

/**
 * 【权限表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Service(value = "adminPermissionService")
public class AdminPermissionServiceImpl extends MybatisBaseServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminPermissionDao;
    }

}
