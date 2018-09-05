package com.bluemobi.po.alarm;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【报警类型】持久化对象 数据库表：alarm_type
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
public class AlarmType extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer alarmTypeId;
    // 报警名称
    private String alarmTypeName;
    // 报警级别  级别：0.低 1.中 2.高
    private Integer alarmLevel;
    // 排序号
    private Integer sortOrder;
    // 备注
    private String remark;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后修改时间
    private Date mtime;

    /** 获取  */
    public Integer getAlarmTypeId() {
        return alarmTypeId;
    }

    /** 设置  */
    public void setAlarmTypeId(Integer alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }

    /** 获取 报警名称 */
    public String getAlarmTypeName() {
        return alarmTypeName;
    }

    /** 设置 报警名称 */
    public void setAlarmTypeName(String alarmTypeName) {
        this.alarmTypeName = alarmTypeName;
    }

    /** 获取 报警级别  级别：0.低 1.中 2.高 */
    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    /** 设置 报警级别  级别：0.低 1.中 2.高 */
    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    /** 获取 排序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序号 */
    public void setSortOrder(Integer sortOrder) {
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

    /** 获取 最后修改时间 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后修改时间 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AlarmType");
        sb.append("{alarmTypeId=").append(alarmTypeId);
        sb.append(", alarmTypeName=").append(alarmTypeName);
        sb.append(", alarmLevel=").append(alarmLevel);
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
        if (obj instanceof AlarmType) {
            AlarmType alarmType = (AlarmType) obj;
            if (this.getAlarmTypeId().equals(alarmType.getAlarmTypeId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAlarmTypeId();
        return pkStr.hashCode();
    }

}