package com.bluemobi.po.log;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：log_user_image
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
public class LogUserImage extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键
    private Integer id;
    // 图片地址
    private String imageUrl;
    // 日志信息
    private String logMsg;
    // 创建时间
    private Date time;
    // 库房id
    private String storeId;
    // 库区id
    private String storeAreaId;

    /** 获取 主键 */
    public Integer getId() {
        return id;
    }

    /** 设置 主键 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 图片地址 */
    public String getImageUrl() {
        return imageUrl;
    }

    /** 设置 图片地址 */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /** 获取 日志信息 */
    public String getLogMsg() {
        return logMsg;
    }

    /** 设置 日志信息 */
    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    /** 获取 创建时间 */
    public Date getTime() {
        return time;
    }

    /** 设置 创建时间 */
    public void setTime(Date time) {
        this.time = time;
    }

    /** 获取 库房id */
    public String getStoreId() {
        return storeId;
    }

    /** 设置 库房id */
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    /** 获取 库区id */
    public String getStoreAreaId() {
        return storeAreaId;
    }

    /** 设置 库区id */
    public void setStoreAreaId(String storeAreaId) {
        this.storeAreaId = storeAreaId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LogUserImage");
        sb.append("{id=").append(id);
        sb.append(", imageUrl=").append(imageUrl);
        sb.append(", logMsg=").append(logMsg);
        sb.append(", time=").append(time);
        sb.append(", storeId=").append(storeId);
        sb.append(", storeAreaId=").append(storeAreaId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogUserImage) {
            LogUserImage logUserImage = (LogUserImage) obj;
            if (this.getId().equals(logUserImage.getId())) {
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