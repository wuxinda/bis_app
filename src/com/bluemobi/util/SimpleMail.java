package com.bluemobi.util;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluemobi.to.mail.MailEntity;

/**
 * 邮件系统
 * @author heweiwen
 * 2015-12-1 下午3:54:05
 */
public class SimpleMail {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMail.class);
    
    private static Properties property = new Properties();
    private boolean active=true;
    static{
         try {
            property.load(SimpleMail.class
                    .getResourceAsStream("/mail.properties"));
        } catch (Exception e) {
            LOGGER.error("读取mail.properties文件时出错！", e.getCause());
            LOGGER.error("读取mail.properties文件时出错:【{}】",e);
        }
     }
    MailEntity entity = new MailEntity();
    private String to ; // 收件人地址   entity.getSendMailAddress()
    private String host = property.getProperty("smtp_host_name").trim(); // smtp服务器  ,这里是网易邮箱服务器 地址    
    private String user = property.getProperty("auth_name").trim(); // 用户名，即发送邮件邮箱 
    private String pwd = property.getProperty("auth_pwd").trim(); // 登录密码    
    private String from = property.getProperty("auth_name_from").trim(); // 发件人地址   
    private String subject = property.getProperty("subject_result").trim();
    
    public void setAddress(String address){
        this.to = address;
    }
    
    /**
      * 订单邮件发送
     * property.getProperty("message").trim()+entity.getVerCode()
      * @author HeWeiwen
      * 2015-9-6
      * @param txt
      * 消息队列
      * entity = MailQueue.put(entity);
      * 正文
      * contentPart.setText(txt);
      * 消息队列遍历
      * entity = (MailEntity) MailQueue.take();
      */
    public void runOrder(String txt) {
        String methodName = "runOrder";
        LOGGER.info(methodName, "订单邮件发送");
        do{
            if(!active)
                break;
            synchronized (this) {
                try {
                    entity.setSmtphostName(host);
                    entity.setSendName(subject);
                    entity.setAuthName(user);
                    entity.setAuthPwd(pwd);
                    entity.setFrom(from);
                    entity.setMessage(txt);
                    entity.setSendMailAddress(to);
                    //消息队列
                    if (entity != null) {
                        Properties props = new Properties();
                        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
                        props.put("mail.smtp.host", entity.getSmtphostName());
                        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
                        props.put("mail.smtp.auth", "true");
                        Session session = Session.getDefaultInstance(props);
                        session.setDebug(true);
                        MimeMessage message = new MimeMessage(session);

                        try {
                            message.setFrom(new InternetAddress(entity.getFrom()));
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                                    entity.getSendMailAddress()));
                            message.setSubject(entity.getSendName());
                            Multipart mainPart = new MimeMultipart();
                            // 创建一个包含HTML内容的MimeBodyPart
                            BodyPart html = new MimeBodyPart();
                            // 设置HTML内容
                            html.setContent(entity.getMessage(), "text/html; charset=utf-8");
                            mainPart.addBodyPart(html);
                            // 将MiniMultipart对象设置为邮件内容
                            message.setContent(mainPart);
                            //正文
                            mainPart.addBodyPart(html);
                            message.setContent(mainPart);
                            message.saveChanges();
                            Transport transport = session.getTransport("smtp");
                            transport.connect(entity.getSmtphostName(), entity.getAuthName(), entity.getAuthPwd());
                            transport.sendMessage(message, message.getAllRecipients());
                            transport.close();
                        } catch (Exception e) {
                            LOGGER.error("Exception",e.getCause());
                            LOGGER.error("Exception:【{}】",e);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("从邮件队列取邮件发布时出现异常",e.getCause());
                    LOGGER.error("从邮件队列取邮件发布时出现异常:【{}】",e);
                }
                entity = null;
                try {
                    //消息队列遍历
                } catch (Exception e) {
                    LOGGER.error("从邮件队列获取并移除此队列的头部一份邮件出现异常",e.getCause());
                    LOGGER.error("从邮件队列获取并移除此队列的头部一份邮件出现异常:【{}】",e);
                }
            }
        }while(false);
        LOGGER.info(methodName, "订单邮件发送");
    }
    
    public static void main(String[] args) {
        SimpleMail cn = new SimpleMail();
        // 设置发件人地址、收件人地址和邮件标题
        cn.setAddress("15074887812@163.com");
        String txt = "您的由你验证码是：";
        cn.runOrder(txt);
    }
    
}
