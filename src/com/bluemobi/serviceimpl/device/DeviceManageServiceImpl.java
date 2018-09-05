package com.bluemobi.serviceimpl.device;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.alarm.AlarmHcsdeviceConfigDao;
import com.bluemobi.dao.device.DeviceManageDao;
import com.bluemobi.dao.device.DeviceStoreLinkpropertyValueDao;
import com.bluemobi.po.alarm.AlarmHcsdeviceConfig;
import com.bluemobi.po.alarm.AlarmManage;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.po.device.DeviceStoreLinkpropertyValue;
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.service.alarm.AlarmManageService;
import com.bluemobi.service.device.DeviceCategoryLinkpropertyService;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.device.DeviceStoreLinkpropertyValueService;
import com.bluemobi.service.device.access.AccessControlsService;
import com.bluemobi.service.device.light.HWLightService;
import com.bluemobi.service.device.shelve.ShelveOpForAndroidService;
import com.bluemobi.serviceimpl.device.light.entity.lrms.LRMSRelayRequest;
import com.bluemobi.serviceimpl.device.shelve.ShelveControlByTcp;
import com.bluemobi.serviceimpl.device.util.HardwareResult;
import com.bluemobi.util.StringUtils;

/**
 * 【设备管理表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceManageService")
public class DeviceManageServiceImpl extends MybatisBaseServiceImpl implements DeviceManageService {

	@Autowired
	private DeviceManageDao deviceManageDao;
	@Autowired
	private AlarmHcsdeviceConfigDao alarmHcsdeviceConfigDao;
	@Autowired
	private DeviceStoreLinkpropertyValueService deviceStoreLinkpropertyValueService;
	@Autowired
	private DeviceCategoryLinkpropertyService deviceCategoryLinkpropertyService;
	@Autowired
	private ShelveOpForAndroidService shelveOpForAndroidService;
	@Autowired
	private DeviceStoreLinkpropertyValueDao deviceStoreLinkpropertyValueDao;
	@Autowired
	private AccessControlsService accessControlsService;
	@Autowired
	private HWLightService hwLightService;
	@Autowired
	private AlarmManageService alarmManageService;

	@Override
	public MyBatisBaseDao getDao() {
		return deviceManageDao;
	}

	/**
	 * 添加设备
	 * 
	 * @param deviceManage
	 * @return
	 */
	public void addDevice(DeviceManage deviceManage, AlarmHcsdeviceConfig alarmHcsdeviceConfig) {
		// 设备主表添加记录
		deviceManageDao.insert(deviceManage);
		// 添加设备连接属性值记录
		if (deviceManage.getLinkIds().length > 0 && deviceManage.getLinkIdValues().length > 0
				&& deviceManage.getLinkIds().length == deviceManage.getLinkIdValues().length) {
			for (int i = 0; i < deviceManage.getLinkIds().length; i++) {
				// 属性名称id
				String linkpropertyId = deviceManage.getLinkIds()[i];
				// 设备连接属性值对象
				DeviceStoreLinkpropertyValue val = new DeviceStoreLinkpropertyValue();
				val.setDeviceId(deviceManage.getDeviceId());
				val.setLinkpropertyId(Integer.parseInt(linkpropertyId));
				// 注意：linkIds和linkIdValues为顺序对应关系
				val.setName(deviceManage.getLinkIdValues()[i]);
				// 添加设备连接属性值记录
				deviceStoreLinkpropertyValueService.insert(val);
			}
		}
		if ((alarmHcsdeviceConfig.getAlarmStatus() != null && !alarmHcsdeviceConfig.getAlarmStatus().equals(""))
				|| (alarmHcsdeviceConfig.getHumDown() != null && !alarmHcsdeviceConfig.getHumDown().equals(""))
				|| (alarmHcsdeviceConfig.getHumUp() != null && !alarmHcsdeviceConfig.getHumUp().equals(""))
				|| (alarmHcsdeviceConfig.getHumDown() != null && !alarmHcsdeviceConfig.getHumDown().equals(""))
				|| (alarmHcsdeviceConfig.getTemUp() != null && !alarmHcsdeviceConfig.getTemUp().equals(""))) {
			alarmHcsdeviceConfig.setDeviceId(deviceManage.getDeviceId());
			alarmHcsdeviceConfigDao.insert(alarmHcsdeviceConfig);
		}
	}

	/**
	 * 修改设备
	 * 
	 * @param deviceManage
	 * @return
	 */
	public void updateDevice(DeviceManage deviceManage, AlarmHcsdeviceConfig alarmHcsdeviceConfig) {
		// 设备主表修改记录
		deviceManageDao.update(deviceManage);
		// 删除设备连接属性值记录
		deviceStoreLinkpropertyValueService.deleteByDeviceId(deviceManage.getDeviceId());
		// 添加设备连接属性值记录
		if (deviceManage.getLinkIds().length > 0 && deviceManage.getLinkIdValues().length > 0
				&& deviceManage.getLinkIds().length == deviceManage.getLinkIdValues().length) {
			for (int i = 0; i < deviceManage.getLinkIds().length; i++) {
				// 属性名称id
				String linkpropertyId = deviceManage.getLinkIds()[i];
				// 设备连接属性值对象
				DeviceStoreLinkpropertyValue val = new DeviceStoreLinkpropertyValue();
				val.setDeviceId(deviceManage.getDeviceId());
				val.setLinkpropertyId(Integer.parseInt(linkpropertyId));
				// 注意：linkIds和linkIdValues为顺序对应关系
				val.setName(deviceManage.getLinkIdValues()[i]);
				// 添加设备连接属性值记录
				deviceStoreLinkpropertyValueService.insert(val);
			}
		}
		if ((alarmHcsdeviceConfig.getAlarmStatus() != null && !alarmHcsdeviceConfig.getAlarmStatus().equals(""))
				|| (alarmHcsdeviceConfig.getHumDown() != null && !alarmHcsdeviceConfig.getHumDown().equals(""))
				|| (alarmHcsdeviceConfig.getHumUp() != null && !alarmHcsdeviceConfig.getHumUp().equals(""))
				|| (alarmHcsdeviceConfig.getHumDown() != null && !alarmHcsdeviceConfig.getHumDown().equals(""))
				|| (alarmHcsdeviceConfig.getTemUp() != null && !alarmHcsdeviceConfig.getTemUp().equals(""))) {
			alarmHcsdeviceConfig.setDeviceId(deviceManage.getDeviceId());
			if (alarmHcsdeviceConfig.getAlarmDevconfId() == null) {
				alarmHcsdeviceConfigDao.insert(alarmHcsdeviceConfig);
			} else {
				alarmHcsdeviceConfigDao.update(alarmHcsdeviceConfig);
			}

		}
	}

	/**
	 * 获取设备的连接属性信息
	 * 
	 * @param deviceId
	 * @return
	 */
	public List<Map<String, Object>> getLinkListByDeviceId(Integer deviceId) {
		// 定义返回结果集合
		List<Map<String, Object>> rlist = new ArrayList<Map<String, Object>>();
		// 根据设备id查找设备详情
		Map<Object, Object> dmap = new HashMap<Object, Object>();
		dmap.put("deviceId", deviceId);
		DeviceManage deviceManage = selectObject(dmap);
		// 根据设备分类id查询设备的连接属性集合
		List<Map<String, Object>> linkList = deviceCategoryLinkpropertyService
				.getLinkByCategoryId(deviceManage.getCategoryId());
		// 根据设备id查询设备的连接属性值集合
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("deviceId", deviceId);
		List<Map<String, Object>> linkVlist = deviceStoreLinkpropertyValueService.selectMapList(pMap);
		// 组装属性－值
		for (Map<String, Object> linkMap : linkList) {
			Integer linkpropertyId = (Integer) linkMap.get("linkpropertyId");
			for (Map<String, Object> linkVMap : linkVlist) {
				if (linkpropertyId == (Integer) linkVMap.get("linkpropertyId")) {
					linkMap.put("val", linkVMap.get("name"));
				}
			}
			// 添加组装结果
			rlist.add(linkMap);
		}
		return rlist;
	}

	/**
	 * 设备操控
	 * 
	 * @param map
	 *            参数集合（userId 用户id，storeId 库房id,deviceId 设备id， categoryId
	 *            设备类型：1.视频 2.灯光 3.门禁 4.温度计 5.密集架 actionType 动作类型：1.开 2.关 3.停止
	 *            4.左开 5.右开 6.通风 7.合拢）
	 * @return
	 */
	public int operateDevice(Map<String, Object> map) {

		switch ((String.valueOf(map.get("categoryId")))) {
		case "1":// 视频
			return 0;
		case "2":// 灯光
			return this.lightOperate(map);
		case "3":// 门禁
			return this.accessOperate(map);
		case "4":// 温度计
			return 0;
		case "5":// 密集架
			return this.shelveOperate(map);
		default://
			break;
		}
		return 1;

	}

	/**
	 * 获取库房设备分类列表
	 * 
	 * @param storeId
	 * @return
	 */
	public List<Map<String, Object>> getStoreDevCate(Integer storeId) {
		return deviceManageDao.getStoreDevCate(storeId);
	}

	/**
	 * 密集架操作
	 * 
	 * @param map
	 * @return
	 */
	public int shelveOperate(Map<String, Object> map) {
		List<Map<String, Object>> result = this
				.getLinkListByDeviceId(Integer.parseInt(String.valueOf(map.get("deviceId"))));
		String ip = null;
		int port = -1;
		int chanel = -1;
		String spec = "-1";
		for (Map<String, Object> list : result) {
			if (list.get("name").equals("服务器地址")) {
				ip = String.valueOf(list.get("val"));
			} else if (list.get("name").equals("端口号")) {
				port = Integer.parseInt(String.valueOf(list.get("val")));
			} else if (list.get("name").equals("密集架列号")) {
				chanel = Integer.parseInt(String.valueOf(list.get("val")));
			} else if (list.get("name").equals("型号")) {
				spec = String.valueOf(list.get("val")).toUpperCase();
			}
		}
		if (ip != null && port != -1 && chanel != -1 && !spec.equals("-1")) {
			try {
				if (spec.equals("V6")) {
					switch ((String.valueOf(map.get("actionType")))) {
					case "3":// 停止
						shelveOpForAndroidService.stop(ip, port);
						return 0;
					case "4":// 左开
						shelveOpForAndroidService.leftOpen(ip, port, chanel);
						return 0;
					case "5":// 右开
						shelveOpForAndroidService.rightOpen(ip, port, chanel);
						return 0;
					case "6":// 通风
						shelveOpForAndroidService.vent(ip, port);
						return 0;
					case "7":// 合拢
						shelveOpForAndroidService.close(ip, port);
						return 0;
					case "8":// 通电
						shelveOpForAndroidService.openPws(ip, port);
						return 0;
					case "9":// 断电
						shelveOpForAndroidService.closePws(ip, port);
						return 0;
					case "10":// 调档
						//默认给－1
						int archivesId = -1;
						if (StringUtils.isEmpty(String.valueOf(map.get("archivesId")))){
							archivesId = Integer.parseInt((String.valueOf(map.get("archivesId"))));
						}
						shelveOpForAndroidService.openByArchivesId(ip, port,archivesId);
						return 0;
					default://
						return 1;
					}
				} else {
					switch ((String.valueOf(map.get("actionType")))) {
					case "3":// 停止
						ShelveControlByTcp.stopOrCancel(ip, port);
						return 0;
					case "4":// 左移
						ShelveControlByTcp.open(ip, port, chanel);
						return 0;
					case "5":// 右开
						ShelveControlByTcp.open(ip, port, chanel + 1);
						return 0;
					case "6":// 通风
						ShelveControlByTcp.vent(ip, port);
						return 0;
					case "7":// 合拢
						ShelveControlByTcp.close(ip, port);
						return 0;
					default://
						return 1;
					}
				}
			} catch (IOException e) {
				//e.printStackTrace();
				List<AlarmManage>  list = new ArrayList<AlarmManage>();
		    	AlarmManage alarmManage = new AlarmManage();
		    	alarmManage.setAlarmType(1);
		    	alarmManage.setCategoryId(5);
		    	alarmManage.setCreator(1);
		    	alarmManage.setCtime(new Date());
		    	alarmManage.setDeviceId(Integer.parseInt(String.valueOf(map.get("deviceId"))));
		    	DeviceManage alarmManages = this.selectObject(map);
		    	alarmManage.setDeviceName(alarmManages.getName());
		    	alarmManage.setLevel("2");// 报警级别  0.低 1.中 2.高
		    	alarmManage.setRemark("设备连接失败");
		    	alarmManage.setStatus("0"); // 状态：0.未处理 1.已处理	
		    	alarmManage.setStoreId(1);
		    	alarmManage.setStroreAreaId(1);
		    	list.add(alarmManage);
		    	alarmManageService.addAlarmData(list);
				return 1;
			} catch (InterruptedException e) {
				//e.printStackTrace();
				List<AlarmManage>  list = new ArrayList<AlarmManage>();
		    	AlarmManage alarmManage = new AlarmManage();
		    	alarmManage.setAlarmType(1);
		    	alarmManage.setCategoryId(5);
		    	alarmManage.setCreator(1);
		    	alarmManage.setCtime(new Date());
		    	alarmManage.setDeviceId(Integer.parseInt(String.valueOf(map.get("deviceId"))));
		    	DeviceManage alarmManages = this.selectObject(map);
		    	alarmManage.setDeviceName(alarmManages.getName());
		    	alarmManage.setLevel("2");// 报警级别  0.低 1.中 2.高
		    	alarmManage.setRemark("设备连接失败");
		    	alarmManage.setStatus("0"); // 状态：0.未处理 1.已处理	
		    	alarmManage.setStoreId(1);
		    	alarmManage.setStroreAreaId(1);
		    	list.add(alarmManage);
				return 1;
			}
		} else {
			return 1;
		}
	}

	/**
	 * M门禁控制服务类
	 * 
	 * @param map
	 * @return
	 */
	public int accessOperate(Map<String, Object> map) {
		List<Map<String, Object>> result = this
				.getLinkListByDeviceId(Integer.parseInt(String.valueOf(map.get("deviceId"))));
		Integer thredId = -1;
		for (Map<String, Object> list : result) {
			if (list.get("name").equals("第三方Id")) {
				thredId = Integer.parseInt(String.valueOf(list.get("val")));
			}
		}
		if (thredId != -1 && thredId != null) {
			switch ((String.valueOf(map.get("actionType")))) {
			case "2":// 关
				accessControlsService.close(thredId);
				return 0;
			case "1":// 开
				accessControlsService.open(thredId);
				return 0;
			default://
				return 1;
			}
		} else {
			return 1;
		}
	}

	/**
	 * 灯光控制
	 * 
	 * @param map
	 * @return
	 */
	public int lightOperate(Map<String, Object> map) {
		// 查询灯光连接配置
		List<Map<String, Object>> result = this
				.getLinkListByDeviceId(Integer.parseInt(String.valueOf(map.get("deviceId"))));
		// 组装请求参数
		LRMSRelayRequest req = new LRMSRelayRequest();
		for (Map<String, Object> list : result) {
			if (list.get("name").equals("服务器地址")) {
				String host = String.valueOf(list.get("val"));
				req.setHost(host);
			} else if (list.get("name").equals("端口号")) {
				Integer port = Integer.parseInt(String.valueOf(list.get("val")));
				req.setPort(port);
			} else if (list.get("name").equals("分区号")) {
				String areaId = String.valueOf(list.get("val"));
				req.setAreaId(areaId);
			} else if (list.get("name").equals("模块号")) {
				String modelId = String.valueOf(list.get("val"));
				req.setModelId(modelId);
			} else if (list.get("name").equals("渠道号")) {
				String channelId = String.valueOf(list.get("val"));
				req.setChannelId(channelId);
			}
		}
		// 返回结果集
		HardwareResult hardwareResult = hwLightService.relay(req);
		// 返回成功 0 返回失败 1
		if ("1001".equals(hardwareResult.getResultCode())) {
			return 0;
		} else {
			return 1;
		}
	}
	@Override
	public List<Map<String, Object>> getVideoNo(Integer deviceId) {
		List<Map<String,Object>> list = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("deviceId", deviceId);
			list = deviceManageDao.getVideo(map);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}    
	/**
     * 获取所有设备列表
     */
    @Override
    public List<DeviceManage> selectDevice() {
	Map<Object, Object> map = new HashMap<Object, Object>();
	List<DeviceManage> list = this.selectObjectList(map);
	return list;
    }
    /**
     * 通过库房Id和库区Id和密集架号找设备
     * @param map
     * @return
     */
    public Map<String, Object> selectDeviceForArchive(Map<String, Object> map){
    	Map<String, Object> resMap=deviceManageDao.selectDeviceForArchive(map);
    	
		return resMap;
    }
}
