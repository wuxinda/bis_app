package com.bluemobi.po.alarm;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【温湿度设备报警配置表】持久化对象 数据库表：alarm_hcsdevice_config
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
public class AlarmHcsdeviceConfig extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer alarmDevconfId;
    // 设备id
    private Integer deviceId;
    // 温度上限
    private String temUp;
    // 温度下限
    private String temDown;
    // 湿度上限
    private String humUp;
    // 湿度下限
    private String humDown;
    // 是否报警 0.报警1.不报警
    private String alarmStatus;
    // 
    private String sortOrder;
    // 备注
    private String remark;
    // 
    private Integer creator;
    // 
    private Date ctime;
    // 
    private Integer modifier;
    // 
    private Date mtime;

    /** 获取 主键id */
    public Integer getAlarmDevconfId() {
        return alarmDevconfId;
    }

    /** 设置 主键id */
    public void setAlarmDevconfId(Integer alarmDevconfId) {
        this.alarmDevconfId = alarmDevconfId;
    }

    /** 获取 设备id */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 设备id */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 温度上限 */
    public String getTemUp() {
        return temUp;
    }

    /** 设置 温度上限 */
    public void setTemUp(String temUp) {
        this.temUp = temUp;
    }

    /** 获取 温度下限 */
    public String getTemDown() {
        return temDown;
    }

    /** 设置 温度下限 */
    public void setTemDown(String temDown) {
        this.temDown = temDown;
    }

    /** 获取 湿度上限 */
    public String getHumUp() {
        return humUp;
    }

    /** 设置 湿度上限 */
    public void setHumUp(String humUp) {
        this.humUp = humUp;
    }

    /** 获取 湿度下限 */
    public String getHumDown() {
        return humDown;
    }

    /** 设置 湿度下限 */
    public void setHumDown(String humDown) {
        this.humDown = humDown;
    }

    /** 获取 是否报警 0.报警1.不报警*/
    public String getAlarmStatus() {
        return alarmStatus;
    }

    /** 设置 是否报警 0.报警1.不报警*/
    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    /** 获取  */
    public String getSortOrder() {
        return sortOrder;
    }

    /** 设置  */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获取  */
    public Integer getCreator() {
        return creator;
    }

    /** 设置  */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /** 获取  */
    public Date getCtime() {
        return ctime;
    }

    /** 设置  */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取  */
    public Integer getModifier() {
        return modifier;
    }

    /** 设置  */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /** 获取  */
    public Date getMtime() {
        return mtime;
    }

    /** 设置  */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AlarmHcsdeviceConfig");
        sb.append("{alarmDevconfId=").append(alarmDevconfId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", temUp=").append(temUp);
        sb.append(", temDown=").append(temDown);
        sb.append(", humUp=").append(humUp);
        sb.append(", humDown=").append(humDown);
        sb.append(", alarmStatus=").append(alarmStatus);
        sb.append(", sortOrder=").append(sortOrder);
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
        if (obj instanceof AlarmHcsdeviceConfig) {
            AlarmHcsdeviceConfig alarmHcsdeviceConfig = (AlarmHcsdeviceConfig) obj;
            if (this.getAlarmDevconfId().equals(alarmHcsdeviceConfig.getAlarmDevconfId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAlarmDevconfId();
        return pkStr.hashCode();
    }

}