package com.bluemobi.po.message;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【消息接收表】持久化对象 数据库表：message_receive
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class MessageReceive extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer receiveId;
    // 消息id
    private Integer messageId;
    // 用户id
    private Integer userId;
    // 删除标示：0.否 1.是		
    private Integer isDel;
    // 状态：0.未读 1.已读
    private Integer status;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 主键ID */
    public Integer getReceiveId() {
        return receiveId;
    }

    /** 设置 主键ID */
    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    /** 获取 消息id */
    public Integer getMessageId() {
        return messageId;
    }

    /** 设置 消息id */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /** 获取 用户id */
    public Integer getUserId() {
        return userId;
    }

    /** 设置 用户id */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取 删除标示：0.否 1.是		 */
    public Integer getIsDel() {
        return isDel;
    }

    /** 设置 删除标示：0.否 1.是		 */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /** 获取 状态：0.未读 1.已读 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态：0.未读 1.已读 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 修改人 */
    public Integer getModifier() {
        return modifier;
    }

    /** 设置 修改人 */
    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    /** 获取 最后一次更新时间 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MessageReceive");
        sb.append("{receiveId=").append(receiveId);
        sb.append(", messageId=").append(messageId);
        sb.append(", userId=").append(userId);
        sb.append(", isDel=").append(isDel);
        sb.append(", status=").append(status);
        sb.append(", modifier=").append(modifier);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MessageReceive) {
            MessageReceive messageReceive = (MessageReceive) obj;
            if (this.getReceiveId().equals(messageReceive.getReceiveId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getReceiveId();
        return pkStr.hashCode();
    }

}