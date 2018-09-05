package com.bluemobi.serviceimpl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.system.SystemRegionDao;
import com.bluemobi.service.system.SystemRegionService;

/**
 * 【地区表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Service(value = "systemRegionService")
public class SystemRegionServiceImpl extends MybatisBaseServiceImpl implements SystemRegionService {

    @Autowired
    private SystemRegionDao systemRegionDao;

    @Override
    public MyBatisBaseDao getDao() {
        return systemRegionDao;
    }

}
