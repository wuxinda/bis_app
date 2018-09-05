package com.bluemobi.po.device;


import com.appcore.model.AbstractObject;

/**
 * 【设备所在库区连接属性值表】持久化对象 数据库表：device_store_linkproperty_value
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceStoreLinkpropertyValue extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer pvalueId;
    // 设备id
    private Integer deviceId;
    // 设备连接属性id
    private Integer linkpropertyId;
    // 属性值名称
    private String name;

    /** 获取 主键ID */
    public Integer getPvalueId() {
        return pvalueId;
    }

    /** 设置 主键ID */
    public void setPvalueId(Integer pvalueId) {
        this.pvalueId = pvalueId;
    }

    /** 获取 设备id */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 设备id */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 设备连接属性id */
    public Integer getLinkpropertyId() {
        return linkpropertyId;
    }

    /** 设置 设备连接属性id */
    public void setLinkpropertyId(Integer linkpropertyId) {
        this.linkpropertyId = linkpropertyId;
    }

    /** 获取 属性值名称 */
    public String getName() {
        return name;
    }

    /** 设置 属性值名称 */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceStoreLinkpropertyValue");
        sb.append("{pvalueId=").append(pvalueId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", linkpropertyId=").append(linkpropertyId);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceStoreLinkpropertyValue) {
            DeviceStoreLinkpropertyValue deviceStoreLinkpropertyValue = (DeviceStoreLinkpropertyValue) obj;
            if (this.getPvalueId().equals(deviceStoreLinkpropertyValue.getPvalueId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPvalueId();
        return pkStr.hashCode();
    }

}