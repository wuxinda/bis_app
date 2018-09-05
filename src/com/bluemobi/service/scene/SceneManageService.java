package com.bluemobi.service.scene;

import com.appcore.service.MybatisBaseService;

/**
 * 【场景模式管理表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
public interface SceneManageService extends MybatisBaseService {
	/**
	 * 编辑场景模式对应的联动设备
     * @param actionpropertyIds 规则:deviceId.actionpropertyId,deviceId.actionpropertyId
     * @param sceneId
	 */
	public void updateSceneLinkage(String actionpropertyIds, Integer sceneId);
	/**
	 * 执行场景模式
	 * @param sceneId
	 * @return 
	 */
	public int executeScene(Integer sceneId);
	/**
	 * 执行场景模式
	 * @param sceneId
	 * @param archivesId
	 * @return 
	 */
	public int executeScene(Integer sceneId,Integer archivesId);
}
