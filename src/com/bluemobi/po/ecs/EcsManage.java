package com.bluemobi.po.ecs;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：ecs_manage
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
public class EcsManage extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer ecsManageId;
    // 年份
    private String cYear;
    // 月份
    private String cMonth;
    // 日
    private String cDay;
    // 库房id
    private Integer storeId;
    // 设备类型id
    private Integer deviceTypeId;
    // 设备id
    private Integer deviceId;
    // 实际能耗
    private String actualEc;
    // 目标能耗
    private String targetEc;
    // 创建人
    private String creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;
    // 备注
    private String remark;

    /** 获取 主键ID */
    public Integer getEcsManageId() {
        return ecsManageId;
    }

    /** 设置 主键ID */
    public void setEcsManageId(Integer ecsManageId) {
        this.ecsManageId = ecsManageId;
    }

    /** 获取 年份 */
    public String getCYear() {
        return cYear;
    }

    /** 设置 年份 */
    public void setCYear(String cYear) {
        this.cYear = cYear;
    }

    /** 获取 月份 */
    public String getCMonth() {
        return cMonth;
    }

    /** 设置 月份 */
    public void setCMonth(String cMonth) {
        this.cMonth = cMonth;
    }

    /** 获取 日 */
    public String getCDay() {
        return cDay;
    }

    /** 设置 日 */
    public void setCDay(String cDay) {
        this.cDay = cDay;
    }

    /** 获取 库房id */
    public Integer getStoreId() {
        return storeId;
    }

    /** 设置 库房id */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** 获取 设备类型id */
    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    /** 设置 设备类型id */
    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    /** 获取 设备id */
    public Integer getDeviceId() {
        return deviceId;
    }

    /** 设置 设备id */
    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    /** 获取 实际能耗 */
    public String getActualEc() {
        return actualEc;
    }

    /** 设置 实际能耗 */
    public void setActualEc(String actualEc) {
        this.actualEc = actualEc;
    }

    /** 获取 目标能耗 */
    public String getTargetEc() {
        return targetEc;
    }

    /** 设置 目标能耗 */
    public void setTargetEc(String targetEc) {
        this.targetEc = targetEc;
    }

    /** 获取 创建人 */
    public String getCreator() {
        return creator;
    }

    /** 设置 创建人 */
    public void setCreator(String creator) {
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
        sb.append("EcsManage");
        sb.append("{ecsManageId=").append(ecsManageId);
        sb.append(", cYear=").append(cYear);
        sb.append(", cMonth=").append(cMonth);
        sb.append(", cDay=").append(cDay);
        sb.append(", storeId=").append(storeId);
        sb.append(", deviceTypeId=").append(deviceTypeId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", actualEc=").append(actualEc);
        sb.append(", targetEc=").append(targetEc);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append(", remark=").append(remark);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EcsManage) {
            EcsManage ecsManage = (EcsManage) obj;
            if (this.getEcsManageId().equals(ecsManage.getEcsManageId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getEcsManageId();
        return pkStr.hashCode();
    }

}