package com.bluemobi.po.ams;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【档案操作纪录表】持久化对象 数据库表：ams_archives_actlog
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
public class AmsArchivesActlog extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer actlogId;
    // 档案id
    private Integer archivesId;
    // 用户id
    private Integer userId;
    // 操作类型 0.入库  1.借阅  2.上架  3.归还4.出库		
    private String type;
    // 审核状态 0.待审批 1.审核通过  2.审核拒绝		
    private String status;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;
    //库房id
    private Integer wmsstoreId;
    //库区id
    private Integer storeareaId;
    /** 获取 主键id */
    public Integer getActlogId() {
        return actlogId;
    }

    /** 设置 主键id */
    public void setActlogId(Integer actlogId) {
        this.actlogId = actlogId;
    }

    /** 获取 档案id */
    public Integer getArchivesId() {
        return archivesId;
    }

    /** 设置 档案id */
    public void setArchivesId(Integer archivesId) {
        this.archivesId = archivesId;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 操作类型 0.入库  1.借阅  2.上架  3.归还		 */
    public String getType() {
        return type;
    }

    /** 设置 操作类型 0.入库  1.借阅  2.上架  3.归还		 */
    public void setType(String type) {
        this.type = type;
    }

    /** 获取 审核状态 0.待审批 1.审核通过  2.审核拒绝		 */
    public String getStatus() {
        return status;
    }

    /** 设置 审核状态 0.待审批 1.审核通过  2.审核拒绝		 */
    public void setStatus(String status) {
        this.status = status;
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
    /** 获取库房id */
    public Integer getWmsstoreId() {
        return wmsstoreId;
    }
    /** 设置库房id */
    public void setWmsstoreId(Integer wmsstoreId) {
        this.wmsstoreId = wmsstoreId;
    }
    /** 获取库区id */
    public Integer getStoreareaId() {
        return storeareaId;
    }
    /** 设置库区id */
    public void setStoreareaId(Integer storeareaId) {
        this.storeareaId = storeareaId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AmsArchivesActlog");
        sb.append("{actlogId=").append(actlogId);
        sb.append(", archivesId=").append(archivesId);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
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
        if (obj instanceof AmsArchivesActlog) {
            AmsArchivesActlog amsArchivesActlog = (AmsArchivesActlog) obj;
            if (this.getActlogId().equals(amsArchivesActlog.getActlogId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getActlogId();
        return pkStr.hashCode();
    }

}