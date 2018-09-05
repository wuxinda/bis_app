package com.bluemobi.serviceimpl.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.alarm.AlarmTypeDao;
import com.bluemobi.service.alarm.AlarmTypeService;

/**
 * 【报警类型】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
@Service(value = "alarmTypeService")
public class AlarmTypeServiceImpl extends MybatisBaseServiceImpl implements AlarmTypeService {

    @Autowired
    private AlarmTypeDao alarmTypeDao;

    @Override
    public MyBatisBaseDao getDao() {
        return alarmTypeDao;
    }

}
