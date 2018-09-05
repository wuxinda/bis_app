package com.bluemobi.po.wms;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【库房分区表】持久化对象 数据库表：wms_store_area
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class WmsStoreArea extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer stroreAreaId;
    // 所属库房id
    private Integer storeId;
    // 库房分区代码
    private String code;
    // 库房分区名称
    private String name;
    // 序号
    private Integer sortOrder;
    // 状态：0.停用 1.在用		
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
    public Integer getStroreAreaId() {
        return stroreAreaId;
    }

    /** 设置 主键ID */
    public void setStroreAreaId(Integer stroreAreaId) {
        this.stroreAreaId = stroreAreaId;
    }

    /** 获取 所属库房id */
    public Integer getStoreId() {
        return storeId;
    }

    /** 设置 所属库房id */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /** 获取 库房代码 */
    public String getCode() {
        return code;
    }

    /** 设置 库房代码 */
    public void setCode(String code) {
        this.code = code;
    }

    /** 获取 库房名称 */
    public String getName() {
        return name;
    }

    /** 设置 库房名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态：0.停用 1.在用		 */
    public String getStatus() {
        return status;
    }

    /** 设置 状态：0.停用 1.在用		 */
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
        sb.append("WmsStoreArea");
        sb.append("{stroreAreaId=").append(stroreAreaId);
        sb.append(", storeId=").append(storeId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
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
        if (obj instanceof WmsStoreArea) {
            WmsStoreArea wmsStoreArea = (WmsStoreArea) obj;
            if (this.getStroreAreaId().equals(wmsStoreArea.getStroreAreaId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getStroreAreaId();
        return pkStr.hashCode();
    }

}