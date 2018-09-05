package com.bluemobi.po.alarm;


import com.appcore.model.AbstractObject;

/**
 * 【报警消息推送规则配置表】持久化对象 数据库表：alarm_message_config
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
public class AlarmMessageConfig extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer almmsgcfgId;
    // 报警类型Id
    private Integer alarmType;
    // 用户类型：0.不限1、领导者 2、操作者
    private Integer userType;
    // 单个用户Id
    private Integer userId;
    // 推送时间间隔（单位：分钟）
    private Integer intervals;
    // 工作日推送：0.是（只在周一到周五推送）1.否（每日推送）
    private Integer iswork;
    // 每日时间范围起
    private Integer starpush;
    // 0.推送1.不推送
    private Integer ispush;
    // 每日时间范围终
    private Integer endpush;
    // 备注
    private String remark;

    /** 获取 主键id */
    public Integer getAlmmsgcfgId() {
        return almmsgcfgId;
    }

    /** 设置 主键id */
    public void setAlmmsgcfgId(Integer almmsgcfgId) {
        this.almmsgcfgId = almmsgcfgId;
    }

    /** 获取 报警类型Id */
    public Integer getAlarmType() {
        return alarmType;
    }

    /** 设置 报警类型Id */
    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    /** 获取 用户类型：0.不限1、领导者 2、操作者 */
    public Integer getUserType() {
        return userType;
    }

    /** 设置 用户类型：0.不限1、领导者 2、操作者 */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /** 获取 单个用户Id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 单个用户Id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 推送时间间隔（单位：分钟） */
    public Integer getIntervals() {
        return intervals;
    }

    /** 设置 推送时间间隔（单位：分钟） */
    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }

    /** 获取 工作日推送：0.是（只在周一到周五推送）1.否（每日推送） */
    public Integer getIswork() {
        return iswork;
    }

    /** 设置 工作日推送：0.是（只在周一到周五推送）1.否（每日推送） */
    public void setIswork(Integer iswork) {
        this.iswork = iswork;
    }

    /** 获取 每日时间范围起 */
    public Integer getStarpush() {
        return starpush;
    }

    /** 设置 每日时间范围起 */
    public void setStarpush(Integer starpush) {
        this.starpush = starpush;
    }

    /** 获取 0.推送1.不推送 */
    public Integer getIspush() {
        return ispush;
    }

    /** 设置 0.推送1.不推送 */
    public void setIspush(Integer ispush) {
        this.ispush = ispush;
    }

    /** 获取 每日时间范围终 */
    public Integer getEndpush() {
        return endpush;
    }

    /** 设置 每日时间范围终 */
    public void setEndpush(Integer endpush) {
        this.endpush = endpush;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AlarmMessageConfig");
        sb.append("{almmsgcfgId=").append(almmsgcfgId);
        sb.append(", alarmType=").append(alarmType);
        sb.append(", userType=").append(userType);
        sb.append(", userId=").append(userId);
        sb.append(", intervals=").append(intervals);
        sb.append(", iswork=").append(iswork);
        sb.append(", starpush=").append(starpush);
        sb.append(", ispush=").append(ispush);
        sb.append(", endpush=").append(endpush);
        sb.append(", remark=").append(remark);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AlarmMessageConfig) {
            AlarmMessageConfig alarmMessageConfig = (AlarmMessageConfig) obj;
            if (this.getAlmmsgcfgId().equals(alarmMessageConfig.getAlmmsgcfgId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAlmmsgcfgId();
        return pkStr.hashCode();
    }

}