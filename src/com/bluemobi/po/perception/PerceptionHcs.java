package com.bluemobi.po.perception;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【温湿度感知数据收集表】持久化对象 数据库表：perception_hcs
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
public class PerceptionHcs extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer hcsId;
    // 设备ID
    private Integer deviceId;
    // 温度
    private String temperature;
    // 湿度
    private String humidity;
    // 空调状态
    private String airStatus;
    // 除湿状态
    private String dryingStatus;
    // 增湿状态
    private String wettingStatus;
    // 通风状态
    private String ventilationStatus;
    // 净化状态
    private String cleansingStatus;
    // 警告状态
    private String warningStatus;
    // 采集时间
    private Date collectDate;
    // 
    private String remark;

    /** 获取 主键id */
    public Integer getHcsId() {
        return hcsId;
    }

    /** 设置 主键id */
    public void setHcsId(Integer hcsId) {
        this.hcsId = hcsId;
    }

    /** 获取 设备ID */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 设备ID */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 温度 */
    public String getTemperature() {
        return temperature;
    }

    /** 设置 温度 */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /** 获取 湿度 */
    public String getHumidity() {
        return humidity;
    }

    /** 设置 湿度 */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    /** 获取 空调状态 */
    public String getAirStatus() {
        return airStatus;
    }

    /** 设置 空调状态 */
    public void setAirStatus(String airStatus) {
        this.airStatus = airStatus;
    }

    /** 获取 除湿状态 */
    public String getDryingStatus() {
        return dryingStatus;
    }

    /** 设置 除湿状态 */
    public void setDryingStatus(String dryingStatus) {
        this.dryingStatus = dryingStatus;
    }

    /** 获取 增湿状态 */
    public String getWettingStatus() {
        return wettingStatus;
    }

    /** 设置 增湿状态 */
    public void setWettingStatus(String wettingStatus) {
        this.wettingStatus = wettingStatus;
    }

    /** 获取 通风状态 */
    public String getVentilationStatus() {
        return ventilationStatus;
    }

    /** 设置 通风状态 */
    public void setVentilationStatus(String ventilationStatus) {
        this.ventilationStatus = ventilationStatus;
    }

    /** 获取 净化状态 */
    public String getCleansingStatus() {
        return cleansingStatus;
    }

    /** 设置 净化状态 */
    public void setCleansingStatus(String cleansingStatus) {
        this.cleansingStatus = cleansingStatus;
    }

    /** 获取 警告状态 */
    public String getWarningStatus() {
        return warningStatus;
    }

    /** 设置 警告状态 */
    public void setWarningStatus(String warningStatus) {
        this.warningStatus = warningStatus;
    }

    /** 获取 采集时间 */
    public Date getCollectDate() {
        return collectDate;
    }

    /** 设置 采集时间 */
    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }

    /** 获取  */
    public String getRemark() {
        return remark;
    }

    /** 设置  */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PerceptionHcs");
        sb.append("{hcsId=").append(hcsId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", temperature=").append(temperature);
        sb.append(", humidity=").append(humidity);
        sb.append(", airStatus=").append(airStatus);
        sb.append(", dryingStatus=").append(dryingStatus);
        sb.append(", wettingStatus=").append(wettingStatus);
        sb.append(", ventilationStatus=").append(ventilationStatus);
        sb.append(", cleansingStatus=").append(cleansingStatus);
        sb.append(", warningStatus=").append(warningStatus);
        sb.append(", collectDate=").append(collectDate);
        sb.append(", remark=").append(remark);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PerceptionHcs) {
            PerceptionHcs perceptionHcs = (PerceptionHcs) obj;
            if (this.getHcsId().equals(perceptionHcs.getHcsId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getHcsId();
        return pkStr.hashCode();
    }

}