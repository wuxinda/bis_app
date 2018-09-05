package com.bluemobi.po.wms;


import com.appcore.model.AbstractObject;

/**
 * 【库房用户表】持久化对象 数据库表：wms_user
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-07
 * 
 */
public class WmsUser extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer wmsUserId;
    // 所属库房id
    private Integer storeId;
    // 用户id
    private Integer userId;
    // 备注
    private String remark;
    //用户地基和
    private String [] userIds;
    
    public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	/** 获取 主键ID */
    public Integer getWmsUserId() {
        return wmsUserId;
    }

    /** 设置 主键ID */
    public void setWmsUserId(Integer wmsUserId) {
        this.wmsUserId = wmsUserId;
    }

    /** 获取 所属库房id */
    public Integer getStoreId() {
        return storeId;
    }

    /** 设置 所属库房id */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("WmsUser");
        sb.append("{wmsUserId=").append(wmsUserId);
        sb.append(", storeId=").append(storeId);
        sb.append(", userId=").append(userId);
        sb.append(", remark=").append(remark);
        sb.append(", userIds=").append(userIds);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof WmsUser) {
            WmsUser wmsUser = (WmsUser) obj;
            if (this.getWmsUserId().equals(wmsUser.getWmsUserId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getWmsUserId();
        return pkStr.hashCode();
    }

}