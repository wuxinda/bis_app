package com.bluemobi.serviceimpl.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.alarm.AlarmHcsdeviceConfigDao;
import com.bluemobi.service.alarm.AlarmHcsdeviceConfigService;

/**
 * 【温湿度设备报警配置表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
@Service(value = "alarmHcsdeviceConfigService")
public class AlarmHcsdeviceConfigServiceImpl extends MybatisBaseServiceImpl implements AlarmHcsdeviceConfigService {

    @Autowired
    private AlarmHcsdeviceConfigDao alarmHcsdeviceConfigDao;

    @Override
    public MyBatisBaseDao getDao() {
        return alarmHcsdeviceConfigDao;
    }

}
