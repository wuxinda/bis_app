package com.bluemobi.po.admin;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【角色表】持久化对象 数据库表：admin_role
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public class AdminRole extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 角色id
    private Integer roleId;
    // 角色名
    private String name;
    // 描述
    private String description;
    // 状态：0、不可用 1、可用
    private Integer status;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 修改时间
    private Date mtime;
    // 用户类型：0、后台用户
    private Integer userType;
    // 商户id
    private Integer merchantId;

    /** 获取 角色id */
    public Integer getRoleId() {
        return roleId;
    }

    /** 设置 角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获取 角色名 */
    public String getName() {
        return name;
    }

    /** 设置 角色名 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 描述 */
    public String getDescription() {
        return description;
    }

    /** 设置 描述 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 状态：0、不可用 1、可用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、不可用 1、可用 */
    public void setStatus(Integer status) {
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

    /** 获取 修改时间 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 修改时间 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 用户类型：1、平台 2、商户 */
    public Integer getUserType() {
        return userType;
    }

    /** 设置 用户类型：1、平台 2、商户 */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /** 获取 商户id */
    public Integer getMerchantId() {
        return merchantId;
    }

    /** 设置 商户id */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminRole");
        sb.append("{roleId=").append(roleId);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", status=").append(status);
        sb.append(", creator=").append(creator);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append(", userType=").append(userType);
        sb.append(", merchantId=").append(merchantId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminRole) {
            AdminRole adminRole = (AdminRole) obj;
            if (this.getRoleId().equals(adminRole.getRoleId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getRoleId();
        return pkStr.hashCode();
    }

}