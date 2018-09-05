package com.bluemobi.service.device;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【设备所在库区连接属性值表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceStoreLinkpropertyValueService extends MybatisBaseService {
	/**
	 * 根据设备id删除连接属性值记录
	 * @param deviceId
	 */
	public void deleteByDeviceId(Integer deviceId);
	/**
     * 获取设备通道号
     * @param map
     * @return
     */
    public List<Map<String, Object>> selectDeviceChanal(Map<String, Object> map);

}
