package com.bluemobi.po.scene;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【场景模式设备联动表】持久化对象 数据库表：scene_device_linkage
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
public class SceneDeviceLinkage extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer linkageId;
    // 设备id
    private Integer deviceId;
    // 设备名称
    private String deviceName;
    // 场景id
    private Integer sceneId;
    // 设备操作属性id
    private Integer actionpropertyId;
    //操作名称
    private String actionName;
    // 执行间隔：单位为秒
    private Integer execSec;
    // 
    private Integer sortOrder;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 主键ID */
    public Integer getLinkageId() {
        return linkageId;
    }

    /** 设置 主键ID */
    public void setLinkageId(Integer linkageId) {
        this.linkageId = linkageId;
    }

    /** 获取 设备id */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 设备id */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 场景id */
    public Integer getSceneId() {
        return sceneId;
    }

    /** 设置 场景id */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /** 获取 设备操作属性id */
    public Integer getActionpropertyId() {
        return actionpropertyId;
    }

    /** 设置 设备操作属性id */
    public void setActionpropertyId(Integer actionpropertyId) {
        this.actionpropertyId = actionpropertyId;
    }

    /** 获取 执行间隔：单位为秒 */
    public Integer getExecSec() {
        return execSec;
    }

    /** 设置 执行间隔：单位为秒 */
    public void setExecSec(Integer execSec) {
        this.execSec = execSec;
    }

    /** 获取  */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置  */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 创建人 */
    public Integer getCreator() {
        return creator;
    }

    /** 设置 创建人 */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /** 获取 创建时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 修改人 */
    public Integer getModifier() {
        return modifier;
    }

    /** 设置 修改人 */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /** 获取 最后一次更新时间 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SceneDeviceLinkage");
        sb.append("{linkageId=").append(linkageId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", sceneId=").append(sceneId);
        sb.append(", actionpropertyId=").append(actionpropertyId);
        sb.append(", execSec=").append(execSec);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SceneDeviceLinkage) {
            SceneDeviceLinkage sceneDeviceLinkage = (SceneDeviceLinkage) obj;
            if (this.getLinkageId().equals(sceneDeviceLinkage.getLinkageId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getLinkageId();
        return pkStr.hashCode();
    }

}