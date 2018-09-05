package com.bluemobi.service.device;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.alarm.AlarmHcsdeviceConfig;
import com.bluemobi.po.device.DeviceManage;

/**
 * 【设备管理表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceManageService extends MybatisBaseService {
	/**添加设备
	 * @param deviceManage
	 * @return
	 */
	public void addDevice(DeviceManage  deviceManage,AlarmHcsdeviceConfig alarmHcsdeviceConfig);
	/**修改设备
	 * @param deviceManage
	 * @return
	 */
	public void updateDevice(DeviceManage  deviceManage,AlarmHcsdeviceConfig alarmHcsdeviceConfig);
	
	/**
	 * 设备操控
	 * @param map 参数集合（userId 用户id，storeId 库房id,deviceId 设备id，
	 * categoryId 设备类型：1.视频 2.灯光 3.门禁 4.温度计  5.密集架
	 * actionType 动作类型：1.开 2.关 3.停止 4.左开  5.右开 6.通风 7.合拢）
	 * @return
	 */
	public int operateDevice(Map<String,Object> map);
    /**
     * 获取设备的连接属性信息
     * @param deviceId
     * @return
     */
    public List<Map<String,Object>> getLinkListByDeviceId(Integer deviceId);
    /**
     * 获取库房设备分类列表
     * @param storeId
     * @return
     */
    public List<Map<String,Object>> getStoreDevCate(Integer storeId);
    /**
     * 通过密集架id获取视频通道号
     * @param storeId
     * @return
     */
    public List<Map<String,Object>> getVideoNo(Integer deviceId);
	/**
     * 获取所有设备列表
     */
    public List<DeviceManage> selectDevice();
    /**
     * 通过库房Id和库区Id和密集架号找设备
     * @param map
     * @return
     */
    public Map<String, Object> selectDeviceForArchive(Map<String, Object> map);
}
