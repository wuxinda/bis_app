package com.bluemobi.po.admin;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【菜单表】持久化对象 数据库表：admin_menu
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public class AdminMenu extends AbstractObject {

    public static final long serialVersionUID = 1L;

    private String abc;
    // 菜单id
    private Integer menuId;
    // 菜单名称
    private String name;
    // 父级菜单id
    private Integer pid;
    // 菜单级别：1、一级菜单 2、二级菜单
    private Integer grade;
    // 链接
    private String url;
    // icon图标
    private String icon;
    // icon背景
    private String iconBg;
    // 序号
    private Integer sortOrder;
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

    /** 获取 菜单id */
    public Integer getMenuId() {
        return menuId;
    }

    /** 设置 菜单id */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /** 获取 菜单名称 */
    public String getName() {
        return name;
    }

    /** 设置 菜单名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 父级菜单id */
    public Integer getPid() {
        return pid;
    }

    /** 设置 父级菜单id */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /** 获取 菜单级别：1、一级菜单 2、二级菜单 */
    public Integer getGrade() {
        return grade;
    }

    /** 设置 菜单级别：1、一级菜单 2、二级菜单 */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /** 获取 链接 */
    public String getUrl() {
        return url;
    }

    /** 设置 链接 */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 获取 icon图标 */
    public String getIcon() {
        return icon;
    }

    /** 设置 icon图标 */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /** 获取 icon背景 */
    public String getIconBg() {
        return iconBg;
    }

    /** 设置 icon背景 */
    public void setIconBg(String iconBg) {
        this.iconBg = iconBg;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminMenu");
        sb.append("{menuId=").append(menuId);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", grade=").append(grade);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", iconBg=").append(iconBg);
        sb.append(", sortOrder=").append(sortOrder);
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
        if (obj instanceof AdminMenu) {
            AdminMenu adminMenu = (AdminMenu) obj;
            if (this.getMenuId().equals(adminMenu.getMenuId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getMenuId();
        return pkStr.hashCode();
    }

}