package com.bluemobi.po.message;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【消息发送表】持久化对象 数据库表：message_send
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class MessageSend extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Integer sendId;
    // 消息id
    private Integer messageId;
    // 用户id
    private Integer userId;
    // 删除标示：0.否 1.是		
    private Integer isDel;
    // 发送人
    private Integer creator;
    // 发送时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 主键ID */
    public Integer getSendId() {
        return sendId;
    }

    /** 设置 主键ID */
    public void setSendId(Integer sendId) {
        this.sendId = sendId;
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

    /** 获取 发送人 */
    public Integer getCreator() {
        return creator;
    }

    /** 设置 发送人 */
    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    /** 获取 发送时间 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 发送时间 */
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
        sb.append("MessageSend");
        sb.append("{sendId=").append(sendId);
        sb.append(", messageId=").append(messageId);
        sb.append(", userId=").append(userId);
        sb.append(", isDel=").append(isDel);
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
        if (obj instanceof MessageSend) {
            MessageSend messageSend = (MessageSend) obj;
            if (this.getSendId().equals(messageSend.getSendId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getSendId();
        return pkStr.hashCode();
    }

}