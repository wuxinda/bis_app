package com.bluemobi.po.ams;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【档案保管年限
】持久化对象 数据库表：ams_archives_keepyear
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-05
 * 
 */
public class AmsArchivesKeepyear extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer amsArchivesKeepyear;
    // 保管年限名称
    private String name;
    // 排序号
    private String sortOrder;
    // 备注
    private String remark;
    // 创建人
    private String creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private String modifier;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 主键ID */
    public Integer getAmsArchivesKeepyear() {
        return amsArchivesKeepyear;
    }

    /** 设置 主键ID */
    public void setAmsArchivesKeepyear(Integer amsArchivesKeepyear) {
        this.amsArchivesKeepyear = amsArchivesKeepyear;
    }

    /** 获取 保管年限名称 */
    public String getName() {
        return name;
    }

    /** 设置 保管年限名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 排序号 */
    public String getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序号 */
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
    public String getModifier() {
        return modifier;
    }

    /** 设置 修改人 */
    public void setModifier(String modifier) {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AmsArchivesKeepyear");
        sb.append("{amsArchivesKeepyear=").append(amsArchivesKeepyear);
        sb.append(", name=").append(name);
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
        if (obj instanceof AmsArchivesKeepyear) {
            AmsArchivesKeepyear amsArchivesKeepyear = (AmsArchivesKeepyear) obj;
            if (this.getAmsArchivesKeepyear().equals(amsArchivesKeepyear.getAmsArchivesKeepyear())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAmsArchivesKeepyear();
        return pkStr.hashCode();
    }

}