package com.bluemobi.po.device;


import com.appcore.model.AbstractObject;

/**
 * 【设备操作属性表】持久化对象 数据库表：device_actionproperty
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceActionproperty extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer actionpropertyId;
    // 属性名称
    private String name;
    // 备注
    private String remark;
    // 排序号
    private Integer sortOrder;

    /** 获取 主键ID */
    public Integer getActionpropertyId() {
        return actionpropertyId;
    }

    /** 设置 主键ID */
    public void setActionpropertyId(Integer actionpropertyId) {
        this.actionpropertyId = actionpropertyId;
    }

    /** 获取 属性名称 */
    public String getName() {
        return name;
    }

    /** 设置 属性名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获取 排序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceActionproperty");
        sb.append("{actionpropertyId=").append(actionpropertyId);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceActionproperty) {
            DeviceActionproperty deviceActionproperty = (DeviceActionproperty) obj;
            if (this.getActionpropertyId().equals(deviceActionproperty.getActionpropertyId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getActionpropertyId();
        return pkStr.hashCode();
    }

}