package com.bluemobi.po.admin;


import com.appcore.model.AbstractObject;

/**
 * 【权限表代码表，用于控制前端的按钮显示】持久化对象 数据库表：admin_permission_code
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public class AdminPermissionCode extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 权限代码id
    private Integer permissionCodeId;
    // 权限代码，如：add edit delete index page detail
    private String code;
    // 权限名称
    private String name;
    // 序号
    private Integer sortOrder;
    // 状态：0、不可用 1、可用
    private Integer status;
    // 描述
    private String description;

    /** 获取 权限代码id */
    public Integer getPermissionCodeId() {
        return permissionCodeId;
    }

    /** 设置 权限代码id */
    public void setPermissionCodeId(Integer permissionCodeId) {
        this.permissionCodeId = permissionCodeId;
    }

    /** 获取 权限代码，如：add edit delete index page detail */
    public String getCode() {
        return code;
    }

    /** 设置 权限代码，如：add edit delete index page detail */
    public void setCode(String code) {
        this.code = code;
    }

    /** 获取 权限名称 */
    public String getName() {
        return name;
    }

    /** 设置 权限名称 */
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

    /** 获取 状态：0、不可用 1、可用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、不可用 1、可用 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 描述 */
    public String getDescription() {
        return description;
    }

    /** 设置 描述 */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminPermissionCode");
        sb.append("{permissionCodeId=").append(permissionCodeId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminPermissionCode) {
            AdminPermissionCode adminPermissionCode = (AdminPermissionCode) obj;
            if (this.getPermissionCodeId().equals(adminPermissionCode.getPermissionCodeId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPermissionCodeId();
        return pkStr.hashCode();
    }

}