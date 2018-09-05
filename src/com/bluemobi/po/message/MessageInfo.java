package com.bluemobi.po.message;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【消息表】持久化对象 数据库表：message_info
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public class MessageInfo extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer messageId;
    // 消息类型：1.系统消息 2.报警消息 3.审批消息
    private Integer type;
    // 标题
    private String title;
    // 内容
    private String content;
    // 是否推送 0.否  1.是
    private Integer isPush;
    // 删除标示：0.否 1.是		
    private Integer isDel;
    // 创建人
    private Integer creator;
    // 创建时间
    private Date ctime;
    // 修改人
    private Integer modifier;
    // 最后一次更新时间
    private Date mtime;

    /** 获取 主键id */
    public Integer getMessageId() {
        return messageId;
    }

    /** 设置 主键id */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /** 获取 消息类型：1.系统消息 2.报警消息 3.审批消息 */
    public Integer getType() {
        return type;
    }

    /** 设置 消息类型：1.系统消息 2.报警消息 3.审批消息 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 标题 */
    public String getTitle() {
        return title;
    }

    /** 设置 标题 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 内容 */
    public String getContent() {
        return content;
    }

    /** 设置 内容 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 是否推送 */
    public Integer getIsPush() {
        return isPush;
    }

    /** 设置 是否推送 */
    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    /** 获取 删除标示：0.否 1.是		 */
    public Integer getIsDel() {
        return isDel;
    }

    /** 设置 删除标示：0.否 1.是		 */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
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
        sb.append("MessageInfo");
        sb.append("{messageId=").append(messageId);
        sb.append(", type=").append(type);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", isPush=").append(isPush);
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
        if (obj instanceof MessageInfo) {
            MessageInfo messageInfo = (MessageInfo) obj;
            if (this.getMessageId().equals(messageInfo.getMessageId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getMessageId();
        return pkStr.hashCode();
    }

}