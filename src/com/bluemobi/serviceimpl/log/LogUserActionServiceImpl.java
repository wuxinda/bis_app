package com.bluemobi.serviceimpl.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.log.LogUserActionDao;
import com.bluemobi.service.log.LogUserActionService;

/**
 * 【用户行为日志表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-02 16:54:16
 * 
 */
@Service(value = "logUserActionService")
public class LogUserActionServiceImpl extends MybatisBaseServiceImpl implements LogUserActionService {

    @Autowired
    private LogUserActionDao logUserActionDao;

    @Override
    public MyBatisBaseDao getDao() {
        return logUserActionDao;
    }

}
