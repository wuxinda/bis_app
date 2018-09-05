package com.bluemobi.serviceimpl.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceActionpropertyDao;
import com.bluemobi.service.device.DeviceActionpropertyService;

/**
 * 【设备操作属性表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceActionpropertyService")
public class DeviceActionpropertyServiceImpl extends MybatisBaseServiceImpl implements DeviceActionpropertyService {

    @Autowired
    private DeviceActionpropertyDao deviceActionpropertyDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceActionpropertyDao;
    }

}
