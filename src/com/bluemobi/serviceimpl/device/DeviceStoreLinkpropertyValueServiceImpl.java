package com.bluemobi.serviceimpl.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceStoreLinkpropertyValueDao;
import com.bluemobi.service.device.DeviceStoreLinkpropertyValueService;

/**
 * 【设备所在库区连接属性值表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceStoreLinkpropertyValueService")
public class DeviceStoreLinkpropertyValueServiceImpl extends MybatisBaseServiceImpl
		implements DeviceStoreLinkpropertyValueService {

	@Autowired
	private DeviceStoreLinkpropertyValueDao deviceStoreLinkpropertyValueDao;

	@Override
	public MyBatisBaseDao getDao() {
		return deviceStoreLinkpropertyValueDao;
	}

	/**
	 * 根据设备id删除连接属性值记录
	 * 
	 * @param deviceId
	 */
	public void deleteByDeviceId(Integer deviceId) {
		deviceStoreLinkpropertyValueDao.deleteByDeviceId(deviceId);
	}

	/**
	 * 获取设备通道号
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectDeviceChanal(Map<String, Object> map) {
		return deviceStoreLinkpropertyValueDao.selectDeviceChanal(map);
	}
}
