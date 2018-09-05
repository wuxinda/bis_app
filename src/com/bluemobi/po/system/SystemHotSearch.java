package com.bluemobi.po.system;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【热门搜索表】持久化对象 数据库表：system_hot_search
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-10
 * 
 */
public class SystemHotSearch extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 搜索类型：0、首页 1、分期专区 2、积分专区 3、权益专区
    private Integer type;
    // 搜索关键字
    private String searchKey;
    // 搜索次数
    private Integer count;
    // 序号
    private Integer sortOrder;
    // 状态：0、可用 1、不可用
    private Integer status;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 修改时间
    private Date mtime;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 搜索类型：0、首页 1、分期专区 2、积分专区 3、权益专区 */
    public Integer getType() {
        return type;
    }

    /** 设置 搜索类型：0、首页 1、分期专区 2、积分专区 3、权益专区 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 搜索关键字 */
    public String getSearchKey() {
        return searchKey;
    }

    /** 设置 搜索关键字 */
    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    /** 获取 搜索次数 */
    public Integer getCount() {
        return count;
    }

    /** 设置 搜索次数 */
    public void setCount(Integer count) {
        this.count = count;
    }

    /** 获取 序号 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态：0、可用 1、不可用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、可用 1、不可用 */
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
        sb.append("SystemHotSearch");
        sb.append("{id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", searchKey=").append(searchKey);
        sb.append(", count=").append(count);
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
        if (obj instanceof SystemHotSearch) {
            SystemHotSearch systemHotSearch = (SystemHotSearch) obj;
            if (this.getId().equals(systemHotSearch.getId())) {
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