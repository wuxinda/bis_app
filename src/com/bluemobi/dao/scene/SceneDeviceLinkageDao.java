package com.bluemobi.dao.scene;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【场景模式设备联动表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
public interface SceneDeviceLinkageDao extends MyBatisBaseDao {
	
	/**
	 * 根据sceneId批量删除设备联动
	 * @param categoryActionpropertyList
	 */
	public void deleteBySceneId(Integer sceneId);
}
