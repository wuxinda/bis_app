package com.bluemobi.dao.device;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【设备所在库区连接属性值表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceStoreLinkpropertyValueDao extends MyBatisBaseDao {
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
