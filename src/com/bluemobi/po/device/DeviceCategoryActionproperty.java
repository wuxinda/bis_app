package com.bluemobi.po.device;


import com.appcore.model.AbstractObject;

/**
 * 【设备分类绑定操作属性表】持久化对象 数据库表：device_category_actionproperty
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceCategoryActionproperty extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer ctgyActpId;
    // 设备分类id
    private String categoryId;
    // 设备连接属性id
    private String actionpropertyId;

    /** 获取 主键ID */
    public Integer getCtgyActpId() {
        return ctgyActpId;
    }

    /** 设置 主键ID */
    public void setCtgyActpId(Integer ctgyActpId) {
        this.ctgyActpId = ctgyActpId;
    }

    /** 获取 设备分类id */
    public String getCategoryId() {
        return categoryId;
    }

    /** 设置 设备分类id */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 设备连接属性id */
    public String getActionpropertyId() {
        return actionpropertyId;
    }

    /** 设置 设备连接属性id */
    public void setActionpropertyId(String actionpropertyId) {
        this.actionpropertyId = actionpropertyId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceCategoryActionproperty");
        sb.append("{ctgyActpId=").append(ctgyActpId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", actionpropertyId=").append(actionpropertyId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceCategoryActionproperty) {
            DeviceCategoryActionproperty deviceCategoryActionproperty = (DeviceCategoryActionproperty) obj;
            if (this.getCtgyActpId().equals(deviceCategoryActionproperty.getCtgyActpId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCtgyActpId();
        return pkStr.hashCode();
    }

}