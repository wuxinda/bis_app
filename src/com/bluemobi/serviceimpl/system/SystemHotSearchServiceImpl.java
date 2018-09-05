package com.bluemobi.serviceimpl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.system.SystemHotSearchDao;
import com.bluemobi.service.system.SystemHotSearchService;

/**
 * 【热门搜索表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-10
 * 
 */
@Service(value = "systemHotSearchService")
public class SystemHotSearchServiceImpl extends MybatisBaseServiceImpl implements SystemHotSearchService {

    @Autowired
    private SystemHotSearchDao systemHotSearchDao;

    @Override
    public MyBatisBaseDao getDao() {
        return systemHotSearchDao;
    }

}
