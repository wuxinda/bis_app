package com.bluemobi.serviceimpl.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.log.LogUserImageDao;
import com.bluemobi.service.log.LogUserImageService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
@Service(value = "logUserImageService")
public class LogUserImageServiceImpl extends MybatisBaseServiceImpl implements LogUserImageService {

    @Autowired
    private LogUserImageDao logUserImageDao;

    @Override
    public MyBatisBaseDao getDao() {
        return logUserImageDao;
    }

}
