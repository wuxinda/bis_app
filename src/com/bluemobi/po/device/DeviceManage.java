package com.bluemobi.po.device;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【设备管理表】持久化对象 数据库表：device_manage
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceManage extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer deviceId;
    // 设备分类id
    private Integer categoryId;
    //设备分类名称
    private String categoryName;
    // 品牌id
    private Integer brandId;
    //品牌名称
    private String brandName;
    // 所属库房id
    private Integer storeId;
    // 所属库房名称
    private String storeName;
    // 所属库区id
    private Integer stroreAreaId;
    // 设备名称
    private String name;
    // 序号
    private Integer sortOrder;
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
    //设备连接属性id集合 注意：linkIds和linkIdValues为顺序对应关系
    private String [] linkIds;
    //设备连接属性值集合
    private String [] linkIdValues;

    /** 获取 主键ID */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 主键ID */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 设备分类id */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 设备分类id */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 品牌id */
    public Integer getBrandId() {
        return brandId;
    }

    /** 设置 品牌id */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /** 获取 所属库房id */
    public Integer getStoreId() {
        return storeId;
    }

    /** 设置 所属库房id */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** 获取 所属库区id */
    public Integer getStroreAreaId() {
        return stroreAreaId;
    }

    /** 设置 所属库区id */
    public void setStroreAreaId(Integer stroreAreaId) {
        this.stroreAreaId = stroreAreaId;
    }

    /** 获取 设备名称 */
    public String getName() {
        return name;
    }

    /** 设置 设备名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态：0.停用 1.在用		 */
    public String getStatus() {
        return status;
    }

    /** 设置 状态：0.停用 1.在用		 */
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
    public String[] getLinkIds() {
		return linkIds;
	}

	public void setLinkIds(String[] linkIds) {
		this.linkIds = linkIds;
	}

	public String[] getLinkIdValues() {
		return linkIdValues;
	}

	public void setLinkIdValues(String[] linkIdValues) {
		this.linkIdValues = linkIdValues;
	}
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceManage");
        sb.append("{deviceId=").append(deviceId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", brandId=").append(brandId);
        sb.append(", storeId=").append(storeId);
        sb.append(", stroreAreaId=").append(stroreAreaId);
        sb.append(", name=").append(name);
        sb.append(", sortOrder=").append(sortOrder);
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
        if (obj instanceof DeviceManage) {
            DeviceManage deviceManage = (DeviceManage) obj;
            if (this.getDeviceId().equals(deviceManage.getDeviceId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getDeviceId();
        return pkStr.hashCode();
    }

}