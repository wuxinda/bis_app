package com.bluemobi.serviceimpl.scene;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.scene.SceneDeviceLinkageDao;
import com.bluemobi.dao.scene.SceneManageDao;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.po.scene.SceneDeviceLinkage;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.scene.SceneManageService;

/**
 * 【场景模式管理表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
@Service(value = "sceneManageService")
public class SceneManageServiceImpl extends MybatisBaseServiceImpl implements
		SceneManageService {

	@Autowired
	private SceneManageDao sceneManageDao;
	@Autowired
	private SceneDeviceLinkageDao sceneDeviceLinkageDao;
	@Autowired
	private DeviceManageService deviceManageService;

	@Override
	public MyBatisBaseDao getDao() {
		return sceneManageDao;
	}

	/**
	 * 编辑场景模式对应的联动设备
	 * 
	 * @param actionpropertyIds
	 *            规则:deviceId.actionpropertyId,deviceId.actionpropertyId
	 * @param sceneId
	 */
	public void updateSceneLinkage(String actionpropertyIds, Integer sceneId) {
		// 删除场景对应的设备联动列表
		sceneDeviceLinkageDao.deleteBySceneId(sceneId);

		// 数据分割 actionpropertyIds
		// 规则:deviceId.actionpropertyId,deviceId.actionpropertyId
		String[] ids = actionpropertyIds.split(",");
		for (String actionpropertyId : ids) {
			// 新增联动设备-组装参事
			SceneDeviceLinkage sceneDeviceLinkage = new SceneDeviceLinkage();
			sceneDeviceLinkage.setSceneId(sceneId);
			sceneDeviceLinkage.setDeviceId(Integer.parseInt(actionpropertyId
					.split("\\.")[0]));
			sceneDeviceLinkage.setActionpropertyId(Integer
					.parseInt(actionpropertyId.split("\\.")[1]));
			// 执行间隔 默认20s
			sceneDeviceLinkage.setExecSec(20);
			// 默认系统管理员
			sceneDeviceLinkage.setCreator(1);
			sceneDeviceLinkage.setCtime(new Date());
			sceneDeviceLinkage.setModifier(1);
			sceneDeviceLinkage.setMtime(new Date());
			// 新增
			sceneDeviceLinkageDao.insert(sceneDeviceLinkage);
		}
	}

	/**
	 * 执行场景模式
	 * 
	 * @param sceneId
	 */
	public int executeScene(Integer sceneId) {
		return executeScene(sceneId,null);
	}
	/**
	 * 执行场景模式
	 * @param sceneId
     * @param archivesId
	 */
	public int executeScene(Integer sceneId,Integer archivesId) {
		// 执行结果 0成功 1失败
		int result = 1;
		
		// 查询场景详情
		Map<String, Object> mapLink = new HashMap<String, Object>();
		mapLink.put("sceneId", sceneId);
		// 查询场景对应的联动设备列表
		List<SceneDeviceLinkage> sdlist = sceneDeviceLinkageDao
				.selectObjectList(mapLink);
		for (SceneDeviceLinkage sceneDeviceLinkage : sdlist) {
			// 组装设备操作参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// 根据设备id查询设备详情
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deviceId", sceneDeviceLinkage.getDeviceId()); 
			DeviceManage deviceManage = deviceManageService.selectObject(map);
			if(null==deviceManage)
				return result=0; 
			paramMap.put("deviceId", deviceManage.getDeviceId());
			paramMap.put("categoryId", deviceManage.getCategoryId());
			paramMap.put("actionType", sceneDeviceLinkage.getActionpropertyId());
			paramMap.put("archivesId", archivesId);
			// 单个设备执行结果
			int result2 = deviceManageService.operateDevice(paramMap);
			// 检测有一项设备执行失败,
			if(result2==0){
				return result=0;
			}
		}
		return result;
	}
}
