package com.bluemobi.apito.alarm;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 报警记录接口返回数据实体
 * @author Riven
 *
 */
public class AlarmManageTO extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer alarmId;
    // 报警级别 0.低 1.中 2.高
    private String level;
    // 状态：0.未处理 1.已处理		
    private String status;
    // 报警原因 
    private String remark;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;
	public Integer getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getModifier() {
		return modifier;
	}
	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
}