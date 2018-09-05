package com.bluemobi.po.user;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【用户搜索表】持久化对象 数据库表：user_search
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-10
 * 
 */
public class UserSearch extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 用户id
    private Integer userId;
    // 搜索类型：0、首页 1、分期专区 2、积分专区 3、权益专区
    private Integer type;
    // 搜索关键字
    private String searchKey;
    // 搜索时间
    private Date ctime;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
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

    /** 获取 搜索时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 搜索时间 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("UserSearch");
        sb.append("{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", searchKey=").append(searchKey);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UserSearch) {
            UserSearch userSearch = (UserSearch) obj;
            if (this.getId().equals(userSearch.getId())) {
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