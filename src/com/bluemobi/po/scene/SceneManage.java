package com.bluemobi.po.scene;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【场景模式管理表】持久化对象 数据库表：scene_manage
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
public class SceneManage extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer sceneId;
    // 情景名称
    private String name;
    // 情景类型：0.系统默认 1.用户自定义
    private String type;
    // 所属用户id
    private Integer userId;
    // 状态：0.停用 1.在用
    private String status;
    // 备注
    private String remark;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;
    // 所属库房ID
    private Integer stroreId;
    // 所属库房名称
    private String stroreName;

    /** 获取 主键ID */
    public Integer getSceneId() {
        return sceneId;
    }

    /** 设置 主键ID */
    public void setSceneId(Integer sceneId) {
        this.sceneId = sceneId;
    }

    /** 获取 情景名称 */
    public String getName() {
        return name;
    }

    /** 设置 情景名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 情景类型：0.系统默认 1.用户自定义 */
    public String getType() {
        return type;
    }

    /** 设置 情景类型：0.系统默认 1.用户自定义 */
    public void setType(String type) {
        this.type = type;
    }

    /** 获取 所属用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 所属用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 状态：0.停用 1.在用 */
    public String getStatus() {
        return status;
    }

    /** 设置 状态：0.停用 1.在用 */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
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
    public Integer getStroreId() {
		return stroreId;
	}

	public void setStroreId(Integer stroreId) {
		this.stroreId = stroreId;
	}
	public String getStroreName() {
		return stroreName;
	}

	public void setStroreName(String stroreName) {
		this.stroreName = stroreName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SceneManage");
        sb.append("{sceneId=").append(sceneId);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", userId=").append(userId);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
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
        if (obj instanceof SceneManage) {
            SceneManage sceneManage = (SceneManage) obj;
            if (this.getSceneId().equals(sceneManage.getSceneId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getSceneId();
        return pkStr.hashCode();
    }

}