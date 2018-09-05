package com.bluemobi.po.device;


import com.appcore.model.AbstractObject;

/**
 * 【设备连接属性表】持久化对象 数据库表：device_linkproperty
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class DeviceLinkproperty extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer linkpropertyId;
    // 属性名称
    private String name;
    // 备注
    private String remark;
    // 排序号
    private Integer sortOrder;

    /** 获取 主键ID */
    public Integer getLinkpropertyId() {
        return linkpropertyId;
    }

    /** 设置 主键ID */
    public void setLinkpropertyId(Integer linkpropertyId) {
        this.linkpropertyId = linkpropertyId;
    }

    /** 获取 属性名称 */
    public String getName() {
        return name;
    }

    /** 设置 属性名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 备注 */
    public String getRemark() {
        return remark;
    }

    /** 设置 备注 */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** 获取 排序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DeviceLinkproperty");
        sb.append("{linkpropertyId=").append(linkpropertyId);
        sb.append(", name=").append(name);
        sb.append(", remark=").append(remark);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DeviceLinkproperty) {
            DeviceLinkproperty deviceLinkproperty = (DeviceLinkproperty) obj;
            if (this.getLinkpropertyId().equals(deviceLinkproperty.getLinkpropertyId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getLinkpropertyId();
        return pkStr.hashCode();
    }

}