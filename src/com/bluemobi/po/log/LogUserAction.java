package com.bluemobi.po.log;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【用户行为日志表】持久化对象 数据库表：log_user_action
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-10 11:10:47
 * 
 */
public class LogUserAction extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 日志id
    private Integer logId;
    // 日志类型：1、后台日志 2、接口日志 3、web端日志
    private Integer logType;
    // 用户id
    private Integer userid;
    // 请求url
    private String url;
    //动作名称
    private String actionName;
    // 请求参数
    private String param;
    // 用户ip
    private String ip;
    // 日志记录时间
    private Date logTime;

    /** 获取 日志id 属性 */
    public Integer getLogId() {
        return logId;
    }

    /** 设置 日志id 属性 */
    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    /** 获取 日志类型：1、后台日志 2、接口日志 3、web端日志 属性 */
    public Integer getLogType() {
        return logType;
    }

    /** 设置 日志类型：1、后台日志 2、接口日志 3、web端日志 属性 */
    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    /** 获取 用户id 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户id 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 请求url 属性 */
    public String getUrl() {
        return url;
    }

    /** 设置 请求url 属性 */
    public void setUrl(String url) {
        this.url = url;
    }

    /** 获取 请求参数 属性 */
    public String getParam() {
        return param;
    }

    /** 设置 请求参数 属性 */
    public void setParam(String param) {
        this.param = param;
    }

    /** 获取 用户ip 属性 */
    public String getIp() {
        return ip;
    }

    /** 设置 用户ip 属性 */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /** 获取 日志记录时间 属性 */
    public Date getLogTime() {
        return logTime;
    }

    /** 设置 日志记录时间 属性 */
    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LogUserAction");
        sb.append("{logId=").append(logId);
        sb.append(", logType=").append(logType);
        sb.append(", userid=").append(userid);
        sb.append(", url=").append(url);
        sb.append(", param=").append(param);
        sb.append(", ip=").append(ip);
        sb.append(", logTime=").append(logTime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogUserAction) {
            LogUserAction logUserAction = (LogUserAction) obj;
            if (this.getLogId().equals(logUserAction.getLogId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getLogId();
        return pkStr.hashCode();
    }

}