package com.bluemobi.serviceimpl.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceLinkpropertyDao;
import com.bluemobi.service.device.DeviceLinkpropertyService;

/**
 * 【设备连接属性表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceLinkpropertyService")
public class DeviceLinkpropertyServiceImpl extends MybatisBaseServiceImpl implements DeviceLinkpropertyService {

    @Autowired
    private DeviceLinkpropertyDao deviceLinkpropertyDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceLinkpropertyDao;
    }

}
