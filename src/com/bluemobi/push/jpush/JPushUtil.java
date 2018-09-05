package com.bluemobi.push.jpush;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-15 下午5:25:28
 * 
 */
public class JPushUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JPushUtil.class);

    private JPushUtil() {

    }

    /**
     * 给所有平台的所有用户推送文本消息
     * 
     * @author haojian
     * @date 2015-10-16 下午4:54:50
     * @param alert
     * @param extras
     * @return void
     */
    public static void pushAll(String alert, Map<String, String> extras) {

        // 1、操作系统平台
        Platform platform = Platform.all();

        // 2、被通知的用户
        Audience audience = Audience.all();

        // 3、 通知内容
        Notification notification = JPushUtil.newNotification(alert, extras);

        // 4、推送消息
        JPushUtil.pushNotification(platform, audience, notification);

    }

    /**
     * 根据单个别名(alias)推送
     * 
     * @author haojian
     * @date 2015-10-16 下午4:57:54
     * @param alias
     * @param alert
     * @param extras
     * @return void
     */
    public static void pushByAlias(String alias, String alert, Map<String, String> extras) {

        // 1、操作系统平台
        Platform platform = Platform.all();

        // 2、被通知的用户
        Audience audience = Audience.alias(alias);

        // 3、 通知内容
        Notification notification = JPushUtil.newNotification(alert, extras);

        // 4、推送消息
        JPushUtil.pushNotification(platform, audience, notification);

    }

    /**
     * 根据别名(alias)集合推送
     * 
     * @author haojian
     * @date 2015-10-16 下午4:59:36
     * @param aliasColl
     * @param alert
     * @param extras
     * @return void
     */
    public static void pushByAliasColl(Collection<String> aliasColl, String alert, Map<String, String> extras) {

        // 1、操作系统平台
        Platform platform = Platform.all();

        // 2、被通知的用户
        Audience audience = Audience.alias(aliasColl);

        // 3、 通知内容
        Notification notification = JPushUtil.newNotification(alert, extras);

        // 4、推送消息
        JPushUtil.pushNotification(platform, audience, notification);

    }

    /**
     * 根据群组(tag)推送
     * 
     * @author haojian
     * @date 2015-10-16 下午5:00:50
     * @param tag
     * @param alert
     * @param extras
     * @return void
     */
    public static void pushByTag(String tag, String alert, Map<String, String> extras) {

        // 1、操作系统平台
        Platform platform = Platform.all();

        // 2、被通知的用户
        Audience audience = Audience.tag(tag);

        // 3、 通知内容
        Notification notification = JPushUtil.newNotification(alert, extras);

        // 4、推送消息
        JPushUtil.pushNotification(platform, audience, notification);

    }

    /**
     * 根据群组(tag)集合推送
     * 
     * @author haojian
     * @date 2015-10-16 下午5:01:13
     * @param tagColl
     * @param alert
     * @param extras
     * @return void
     */
    public static void pushByTag(Collection<String> tagColl, String alert, Map<String, String> extras) {

        // 1、操作系统平台
        Platform platform = Platform.all();

        // 2、被通知的用户
        Audience audience = Audience.tag(tagColl);

        // 3、 通知内容
        Notification notification = JPushUtil.newNotification(alert, extras);

        // 4、推送消息
        JPushUtil.pushNotification(platform, audience, notification);

    }

    /**
     * 创建通知内容对象
     * 
     * @author haojian
     * @date 2015-10-16 下午4:52:46
     * @param alert
     * @param extras
     * @return
     * @return Notification
     */
    private static Notification newNotification(String alert, Map<String, String> extras) {
        Notification notification = Notification.newBuilder().addPlatformNotification(IosNotification.newBuilder().setAlert(alert).addExtras(extras).build()).build();
        return notification;
    }

    /**
     * 推送通知 可自定义平台，用户群体，通知内容
     * 
     * @author haojian
     * @date 2015-10-16 下午5:14:35
     * @param platform
     * @param audience
     * @param notification
     * @return
     * @return PushResult
     */
    public static PushResult pushNotification(Platform platform, Audience audience, Notification notification) {
        return JPushUtil.push(platform, audience, notification, null);
    }

    /**
     * 推送消息 可自定义平台，用户群体，消息内容
     * 
     * @author haojian
     * @date 2015-10-16 下午5:14:49
     * @param platform
     * @param audience
     * @param message
     * @return
     * @return PushResult
     */
    public static PushResult pushMessage(Platform platform, Audience audience, Message message) {
        return JPushUtil.push(platform, audience, null, message);
    }

    /**
     * 推送消息
     * 
     * @author haojian
     * @date 2015-10-16 下午4:44:11
     * @param platform
     *            操作系统平台
     * @param audience
     *            接收通知的用户群体
     * @param notification
     *            通知内容 ，发消息时候设为null（优先级高）
     * @param message
     *            消息内容，发通知时候设为null
     * @return PushResult
     */
    private static PushResult push(Platform platform, Audience audience, Notification notification, Message message) {

        JPushClient jpushClient = new JPushClient(JpushConfig.MASTER_SECRET, JpushConfig.APP_KEY, JpushConfig.MAX_RETRY_TIMES);

        // 1、创建builder
        Builder builder = PushPayload.newBuilder();

        // 2、设置builder属性
        // 2.1、设置操作系统平台
        builder.setPlatform(platform);
        // 2.2、设置接收通知的用户
        builder.setAudience(audience);
        if (notification != null) {
            // 2.3、设置通知内容
            builder.setNotification(notification);
        } else {
            // 2.4、设置消息内容
            builder.setMessage(message);
        }
        
        // 3、创建PushPayload
        PushPayload payload = builder.build();

        // 4、推送消息
        return sendPush(jpushClient, payload);
    }

    /**
     * 推送消息
     * 
     * @author haojian
     * @date 2015-10-16 下午4:45:59
     * @param jpushClient
     * @param payload
     * @return
     * @return PushResult
     */
    private static PushResult sendPush(JPushClient jpushClient, PushPayload payload) {
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
            LOGGER.info("推送结果：【{}】", result);
        } catch (APIConnectionException e) {
            LOGGER.error("连接错误，稍后将重试，错误信息【{}】", e);
        } catch (APIRequestException e) {
            LOGGER.error("请求错误，错误信息【{}】", e);
            LOGGER.info("HTTP Status: 【{}】", e.getStatus());
            LOGGER.info("Error Code: 【{}】", e.getErrorCode());
            LOGGER.info("Error Message: 【{}】", e.getErrorMessage());
        }
        return result;
    }

}
