package com.bluemobi.po.device;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【设备分类表】持久化对象 数据库表：device_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer categoryId;
    // 分类名称
    private String name;
    // 父级分类id
    private Integer pId;
    // 分类等级 1.一级分类 2.二级分类 3.三级分类
    private Integer grade;
    // log图
    private String pic;
    // 排序号
    private Integer sortOrder;
    // 状态 0.停用 1.在用
    private String status;
    // 备注
    private String remark;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 主键ID */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 主键ID */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 分类名称 */
    public String getName() {
        return name;
    }

    /** 设置 分类名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 父级分类id */
    public Integer getPId() {
        return pId;
    }

    /** 设置 父级分类id */
    public void setPId(Integer pId) {
        this.pId = pId;
    }

    /** 获取 分类等级 */
    public Integer getGrade() {
        return grade;
    }

    /** 设置 分类等级 */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /** 获取 log图 */
    public String getPic() {
        return pic;
    }

    /** 设置 log图 */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /** 获取 排序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态 */
    public String getStatus() {
        return status;
    }

    /** 设置 状态 */
    public void setStatus(String status) {
        this.status = status;
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
        sb.append("DeviceCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", name=").append(name);
        sb.append(", pId=").append(pId);
        sb.append(", grade=").append(grade);
        sb.append(", pic=").append(pic);
        sb.append(", sortOrder=").append(sortOrder);
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
        if (obj instanceof DeviceCategory) {
            DeviceCategory deviceCategory = (DeviceCategory) obj;
            if (this.getCategoryId().equals(deviceCategory.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCategoryId();
        return pkStr.hashCode();
    }

}