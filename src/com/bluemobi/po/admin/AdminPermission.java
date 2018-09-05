package com.bluemobi.po.admin;


import com.appcore.model.AbstractObject;

/**
 * 【权限表】持久化对象 数据库表：admin_permission
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public class AdminPermission extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 权限id
    private Integer permissionId;
    // 权限名称
    private String name;
    // 权限代码，如：add edit delete index page detail
    private String code;
    // 二级菜单id
    private Integer menuId;
    // 链接=二级菜单url + 权限代码
    private String url;
    // 序号
    private Integer sortOrder;
    // 状态：0、不使用 1、使用
    private Integer status;
    // 用户类型：1、平台 2、商户
    private Integer userType;

    /** 获取 权限id */
    public Integer getPermissionId() {
        return permissionId;
    }

    /** 设置 权限id */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    /** 获取 权限名称 */
    public String getName() {
        return name;
    }

    /** 设置 权限名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 权限代码，如：add edit delete index page detail */
    public String getCode() {
        return code;
    }

    /** 设置 权限代码，如：add edit delete index page detail */
    public void setCode(String code) {
        this.code = code;
    }

    /** 获取 二级菜单id */
    public Integer getMenuId() {
        return menuId;
    }

    /** 设置 二级菜单id */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /** 获取 链接=二级菜单url + 权限代码 */
    public String getUrl() {
        return url;
    }

    /** 设置 链接=二级菜单url + 权限代码 */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 获取 序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态：0、不使用 1、使用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、不使用 1、使用 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 用户类型：1、平台 2、商户 */
    public Integer getUserType() {
        return userType;
    }

    /** 设置 用户类型：1、平台 2、商户 */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminPermission");
        sb.append("{permissionId=").append(permissionId);
        sb.append(", name=").append(name);
        sb.append(", code=").append(code);
        sb.append(", menuId=").append(menuId);
        sb.append(", url=").append(url);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", userType=").append(userType);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminPermission) {
            AdminPermission adminPermission = (AdminPermission) obj;
            if (this.getPermissionId().equals(adminPermission.getPermissionId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPermissionId();
        return pkStr.hashCode();
    }

}