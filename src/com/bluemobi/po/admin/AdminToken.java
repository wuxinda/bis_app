package com.bluemobi.po.admin;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【后台用户token表】持久化对象 数据库表：admin_token
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public class AdminToken extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 用户id
    private Integer userId;
    // token ID
    private String tokenId;
    // 状态：0、不可用 1、可用
    private Integer status;
    // 创建时间
    private Date ctime;
    // 过期时间，超过这个时间就算过期
    private Date expires;

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

    /** 获取 token ID */
    public String getTokenId() {
        return tokenId;
    }

    /** 设置 token ID */
    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    /** 获取 状态：0、不可用 1、可用 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0、不可用 1、可用 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 创建时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 过期时间，超过这个时间就算过期 */
    public Date getExpires() {
        return expires;
    }

    /** 设置 过期时间，超过这个时间就算过期 */
    public void setExpires(Date expires) {
        this.expires = expires;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AdminToken");
        sb.append("{id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", tokenId=").append(tokenId);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
        sb.append(", expires=").append(expires);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AdminToken) {
            AdminToken adminToken = (AdminToken) obj;
            if (this.getId().equals(adminToken.getId())) {
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