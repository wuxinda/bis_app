package com.bluemobi.po.wms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.appcore.model.AbstractObject;

/**
 * 【库房表】持久化对象 数据库表：wms_store
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Table(name="wms_store")
public class WmsStore extends AbstractObject implements Serializable {

    public static final long serialVersionUID = 1L;

    // 主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storeId;
    // 库房代码
    private String code;
    // 库房名称
    private String name;
    // 所属楼号
    private String buildingNo;
    // 所属层数
    private String floorNo;
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
    public Integer getStoreId() {
        return storeId;
    }

    /** 设置 主键ID */
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

    /** 获取 所属楼号 */
    public String getBuildingNo() {
        return buildingNo;
    }

    /** 设置 所属楼号 */
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    /** 获取 所属层数 */
    public String getFloorNo() {
        return floorNo;
    }

    /** 设置 所属层数 */
    public void setFloorNo(String floorNo) {
        this.floorNo = floorNo;
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
        sb.append("WmsStore");
        sb.append("{storeId=").append(storeId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", buildingNo=").append(buildingNo);
        sb.append(", floorNo=").append(floorNo);
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
        if (obj instanceof WmsStore) {
            WmsStore wmsStore = (WmsStore) obj;
            if (this.getStoreId().equals(wmsStore.getStoreId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getStoreId();
        return pkStr.hashCode();
    }

}