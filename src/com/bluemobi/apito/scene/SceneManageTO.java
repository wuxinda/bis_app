package com.bluemobi.apito.scene;

import com.appcore.model.AbstractObject;

/**
 * 场景模式数据接口返回实体
 * @author Riven
 *
 */
public class SceneManageTO extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer sceneId;
    // 情景名称
    private String name;
	public Integer getSceneId() {
		return sceneId;
	}
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}