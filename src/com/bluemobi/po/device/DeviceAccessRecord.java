package com.bluemobi.po.device;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：device_access_record
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
public class DeviceAccessRecord extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 门禁记录id
    private Integer recordId;
    // 创建时间
    private Date ctime;
    // 库房id
    private String storeId;
    // 设备id
    private String deviceId;
    // 门禁信息
    private String remark;
    // 设备名称
    private String deviceName;
    // 操作类型
    private String type;

    /** 获取 门禁记录id */
    public Integer getRecordId() {
        return recordId;
    }

    /** 设置 门禁记录id */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /** 获取 创建时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 库房id */
    public String getStoreId() {
        return storeId;
    }

    /** 设置 库房id */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /** 获取 设备id */
    public String getDeviceId() {
        return deviceId;
    }

    /** 设置 设备id */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 门禁信息 */
    public String getRemark() {
        return remark;
    }

    /** 设置 门禁信息 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获取 设备名称 */
    public String getDeviceName() {
        return deviceName;
    }

    /** 设置 设备名称 */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /** 获取 操作类型 */
    public String getType() {
        return type;
    }

    /** 设置 操作类型 */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceAccessRecord");
        sb.append("{recordId=").append(recordId);
        sb.append(", ctime=").append(ctime);
        sb.append(", storeId=").append(storeId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", remark=").append(remark);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceAccessRecord) {
            DeviceAccessRecord deviceAccessRecord = (DeviceAccessRecord) obj;
            if (this.getRecordId().equals(deviceAccessRecord.getRecordId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getRecordId();
        return pkStr.hashCode();
    }

}