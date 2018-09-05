/**
 * 
 */
package com.bluemobi.to.mail;

/**
 * 发送邮件实体
 * @author heweiwen
 * 2015-7-24 下午5:55:24
 */
public class MailEntity {
    
    private String userNo;
    private String smtphostName;//邮件发送服务器
    private String authName;//邮件服务器账号
    private String authPwd;//邮件服务器密码
    private String sendName;//邮件服务器名称
    private String subject;//标题
    private String message;//正文
    private String from;//发件人地址
    private String sendMailAddress;//收件人地址
    
    private String verCode;//验证码
    
    public final String getSmtphostName() {
        return smtphostName;
    }
    public final void setSmtphostName(String smtphostName) {
        this.smtphostName = smtphostName;
    }
    public final String getAuthName() {
        return authName;
    }
    public final void setAuthName(String authName) {
        this.authName = authName;
    }
    public final String getAuthPwd() {
        return authPwd;
    }
    public final void setAuthPwd(String authPwd) {
        this.authPwd = authPwd;
    }
    public final String getFrom() {
        return from;
    }
    public final void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the sendName
     */
    public String getSendName() {
        return sendName;
    }
    /**
     * @param sendName the sendName to set
     */
    public void setSendName(String sendName) {
        this.sendName = sendName;
    }
    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * @return the sendMailAddress
     */
    public String getSendMailAddress() {
        return sendMailAddress;
    }
    /**
     * @param sendMailAddress the sendMailAddress to set
     */
    public void setSendMailAddress(String sendMailAddress) {
        this.sendMailAddress = sendMailAddress;
    }
    
    /**
     * @return the userNo
     */
    public String getUserNo() {
        return userNo;
    }
    /**
     * @param userNo the userNo to set
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    /**
     * @return the verCode
     */
    public String getVerCode() {
        return verCode;
    }
    /**
     * @param verCode the verCode to set
     */
    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }
    
}
