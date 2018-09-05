package com.bluemobi.po.alarm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appcore.model.AbstractObject;

/**
 * 【报警管理表】持久化对象 数据库表：alarm_manage
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Table(name="alarm_manage")
public class AlarmManage extends AbstractObject implements Serializable{

    public static final long serialVersionUID = 1L;

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer alarmId;
    // 设备分类id
    private Integer categoryId;
    // 设备id
    private Integer deviceId;
    // 所属库房id
    private Integer storeId;
    // 所属库区id
    private Integer stroreAreaId;
    // 设备名称
    private String deviceName;
    // 序号
    private String sortOrder;
    //报警类型
    private Integer alarmType;
    // 报警级别  0.低 1.中 2.高
    private String level;
    // 状态：0.未处理 1.已处理		
    private String status;
    // 报警原因
    private String remark;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;
    
    private Integer archivesId;
    

    public Integer getArchivesId() {
		return archivesId;
	}

	public void setArchivesId(Integer archivesId) {
		this.archivesId = archivesId;
	}

	/** 获取 主键ID */
    public Integer getAlarmId() {
        return alarmId;
    }

    /** 设置 主键ID */
    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    /** 获取 设备分类id */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 设备分类id */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 设备id */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 设备id */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
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
    public String getDeviceName() {
        return deviceName;
    }

    /** 设置 设备名称 */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /** 获取 序号 */
    public String getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 报警级别 */
    public String getLevel() {
        return level;
    }

    /** 设置 报警级别 */
    public void setLevel(String level) {
        this.level = level;
    }

    /** 获取 状态：0.未处理 1.已处理		 */
    public String getStatus() {
        return status;
    }

    /** 设置 状态：0.未处理 1.已处理		 */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 获取 报警原因 */
    public String getRemark() {
        return remark;
    }

    /** 设置 报警原因 */
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

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AlarmManage");
        sb.append("{alarmId=").append(alarmId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", storeId=").append(storeId);
        sb.append(", stroreAreaId=").append(stroreAreaId);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", level=").append(level);
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
        if (obj instanceof AlarmManage) {
            AlarmManage alarmManage = (AlarmManage) obj;
            if (this.getAlarmId().equals(alarmManage.getAlarmId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAlarmId();
        return pkStr.hashCode();
    }

}