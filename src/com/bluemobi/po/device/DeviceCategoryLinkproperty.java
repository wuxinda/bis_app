package com.bluemobi.po.device;


import com.appcore.model.AbstractObject;

/**
 * 【设备分类绑定连接属性表】持久化对象 数据库表：device_category_linkproperty
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceCategoryLinkproperty extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer ctgyLinkpId;
    // 设备分类id
    private Integer categoryId;
    // 设备连接属性id
    private Integer linkpropertyId;

    /** 获取 主键ID */
    public Integer getCtgyLinkpId() {
        return ctgyLinkpId;
    }

    /** 设置 主键ID */
    public void setCtgyLinkpId(Integer ctgyLinkpId) {
        this.ctgyLinkpId = ctgyLinkpId;
    }

    /** 获取 设备分类id */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 设备分类id */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 设备连接属性id */
    public Integer getLinkpropertyId() {
        return linkpropertyId;
    }

    /** 设置 设备连接属性id */
    public void setLinkpropertyId(Integer linkpropertyId) {
        this.linkpropertyId = linkpropertyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceCategoryLinkproperty");
        sb.append("{ctgyLinkpId=").append(ctgyLinkpId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", linkpropertyId=").append(linkpropertyId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceCategoryLinkproperty) {
            DeviceCategoryLinkproperty deviceCategoryLinkproperty = (DeviceCategoryLinkproperty) obj;
            if (this.getCtgyLinkpId().equals(deviceCategoryLinkproperty.getCtgyLinkpId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCtgyLinkpId();
        return pkStr.hashCode();
    }

}