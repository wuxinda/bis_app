package com.bluemobi.po.admin;


import com.appcore.model.AbstractObject;

/**
 * 【角色权限表】持久化对象 数据库表：admin_role_permission
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public class AdminRolePermission extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 角色id
    private Integer roleId;
    // 权限id
    private Integer permissionId;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 角色id */
    public Integer getRoleId() {
        return roleId;
    }

    /** 设置 角色id */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /** 获取 权限id */
    public Integer getPermissionId() {
        return permissionId;
    }

    /** 设置 权限id */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminRolePermission");
        sb.append("{id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", permissionId=").append(permissionId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminRolePermission) {
            AdminRolePermission adminRolePermission = (AdminRolePermission) obj;
            if (this.getId().equals(adminRolePermission.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}