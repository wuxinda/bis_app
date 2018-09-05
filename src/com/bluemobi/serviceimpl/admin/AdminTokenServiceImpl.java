package com.bluemobi.serviceimpl.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminTokenDao;
import com.bluemobi.service.admin.AdminTokenService;

/**
 * 【后台用户token表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
@Service(value = "adminTokenService")
public class AdminTokenServiceImpl extends MybatisBaseServiceImpl implements AdminTokenService {

    @Autowired
    private AdminTokenDao adminTokenDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminTokenDao;
    }

}
