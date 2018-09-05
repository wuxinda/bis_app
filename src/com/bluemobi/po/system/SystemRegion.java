package com.bluemobi.po.system;


import com.appcore.model.AbstractObject;

/**
 * 【地区表】持久化对象 数据库表：system_region
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public class SystemRegion extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 地区id
    private Integer id;
    // 地区名称
    private String name;
    // 父级地区id
    private Integer pid;
    // 地区级别：1、省 2、市 3、区县
    private Integer grade;
    // 序号
    private Integer sortOrder;
    // 状态：0、未使用 1、使用
    private Integer status;
    // 英文名全称
    private String enName;
    // 英文名首字母
    private String initial;

    /** 获取 地区id */
    public Integer getId() {
        return id;
    }

    /** 设置 地区id */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 地区名称 */
    public String getName() {
        return name;
    }

    /** 设置 地区名称 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 父级地区id */
    public Integer getPid() {
        return pid;
    }

    /** 设置 父级地区id */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /** 获取 地区级别：1、省 2、市 3、区县 */
    public Integer getGrade() {
        return grade;
    }

    /** 设置 地区级别：1、省 2、市 3、区县 */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /** 获取 序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态：0、未使用 1、使用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、未使用 1、使用 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 英文名全称 */
    public String getEnName() {
        return enName;
    }

    /** 设置 英文名全称 */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /** 获取 英文名首字母 */
    public String getInitial() {
        return initial;
    }

    /** 设置 英文名首字母 */
    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SystemRegion");
        sb.append("{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", grade=").append(grade);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", enName=").append(enName);
        sb.append(", initial=").append(initial);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SystemRegion) {
            SystemRegion systemRegion = (SystemRegion) obj;
            if (this.getId().equals(systemRegion.getId())) {
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