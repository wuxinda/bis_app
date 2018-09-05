package com.bluemobi.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appcore.context.AppContext;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.message.MessageInfo;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.message.MessageInfoService;
import com.bluemobi.service.message.MessageReceiveService;
/**
 * 消息推送服务类
 * 
 * @version V1.0
 * @author 黄作国
 * @date 2017年10月27日
 */
@SuppressWarnings("deprecation")
public class MsgServiceUtil extends WebSocketServlet {
	private static AdminUserService adminUserService = AppContext.getBean("adminUserService");
	private static MessageInfoService messageInfoService = AppContext.getBean("messageInfoService");
	private static MessageReceiveService messageReceiveService = AppContext.getBean("messageReceiveService");
	private AdminUser smpUser = null;
	private static final long serialVersionUID = 911879078000755859L;
	private static final Logger LOGGER = LoggerFactory.getLogger("WebSocketUtil");
	private static final Map<String, WsOutbound> users = new HashMap<String, WsOutbound>();
	// private final static Map<String, String> user_deviceId = new
	// HashMap<String, String>();

	/**
	 * 与socket建立连接
	 */
	@Override
	protected StreamInbound createWebSocketInbound(String arg0, HttpServletRequest request) {
		String userId = null;
		String deviceId = null;
		try {
			userId = request.getParameter("userId");
			deviceId = request.getParameter("deviceId");
			if (userId != null && deviceId != null && userId.length() > 0 && deviceId.length() > 0
					&& !userId.equals("null") && !deviceId.equals("null")) {
				userId = new String(userId.getBytes("ISO8859_1"), "UTF-8");
				deviceId = new String(deviceId.getBytes("ISO8859_1"), "UTF-8");
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info(userId + "  " + deviceId + "   请求连接");
		return new ChatMessageInbound(deviceId, userId);
	}

	class ChatMessageInbound extends MessageInbound {
		private String userId = "user";
		private String deviceId = "deviceId";

		public ChatMessageInbound(String deviceId, String userId) {
			this.userId = userId;
			this.deviceId = deviceId;
		}

		// 创建连接，将用户连接创建WsOutbound存放于users中
		@Override
		protected void onOpen(WsOutbound outbound) {
			users.put(userId + "_" + deviceId, outbound);
			// user_deviceId.put(deviceId, userId);
			LOGGER.info(userId + "_" + deviceId + "   上线了,总共" + users.size() + "人在线");
			smpUser = new AdminUser();
			smpUser.setUserId(Integer.parseInt(userId));
			smpUser.setIsOnline(1);
			adminUserService.asyncUpdate(smpUser);
			super.onOpen(outbound);
		}

		// 关闭时执行
		@Override
		protected void onClose(int status) {
			users.remove(userId + "_" + deviceId);
			// user_deviceId.remove(deviceId);
			LOGGER.info(userId + "_" + deviceId + "   下线,总共" + users.size() + "人在线");
			smpUser = new AdminUser();
			smpUser.setUserId(Integer.parseInt(userId));
			smpUser.setIsOnline(0);
			Set<String>  keys = users.keySet();
			int flag = 0;
			for(String key:keys) {
				if(key.split("_")[0].equals(userId)) {
					flag=1;
				}
			}
			if(flag==0) {
				adminUserService.asyncUpdate(smpUser);
			}
			super.onClose(status);
		}

		// 二进制消息
		@Override
		protected void onBinaryMessage(ByteBuffer buffer) throws IOException {
		}

		// 接收客户端文本消息数据，参数里包含了数据
		@Override
		protected void onTextMessage(CharBuffer buffer) throws IOException {

			String msg = buffer.toString();
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			msg = userId + " " + sdf.format(date) + ":\n" + msg;
			// 此处产生log日志
			LOGGER.info(msg);
			// 收到消息后向所有在线用户发送消息
			sendPush(msg);
		}

	}

	/**
	 * 向客户端所有在线用户发送消息制作推送不做消息模块逻辑处理
	 * 
	 * @param msg
	 *            消息neirong
	 * @param users
	 */
	public static void sendPush(String msg) {
		Set<String> user_deviceIdsKey = users.keySet();
		for (String user_deviceId : user_deviceIdsKey) {
			WsOutbound outbound = users.get(user_deviceId);
			CharBuffer buffer = CharBuffer.wrap(msg);
			try {
				// 这里向客户端发送数据
				outbound.writeTextMessage(buffer);
				outbound.flush();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(user_deviceId + "——————————————————————-推送失败————————————————————————");
			}
			System.out.println(user_deviceId + "+++++++++++++++++++++++推送成功+++++++++++++++++++++++");
		}
	}

	/**
	 * 向客户端指定在线用户发送消息不做消息模块逻辑处理
	 * 
	 * @param msg消息neirong
	 * @param users
	 * @return
	 */
	public static void sendPushByUser(String msg, List<Integer> userIds) {
		Set<String> user_deviceIdsKey = users.keySet();
		for (String user_deviceId : user_deviceIdsKey) {
			WsOutbound outbound = null;
			for (Integer userId : userIds) {
				if (user_deviceId.split("_")[0].toString().equals(userId.toString())) {
					outbound = users.get(user_deviceId);
					CharBuffer buffer = CharBuffer.wrap(msg);
					try {
						// 这里向客户端发送数据
						outbound.writeTextMessage(buffer);
						outbound.flush();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(user_deviceId + "——————————————————————-推送失败————————————————————————");
						}
					System.out.println(user_deviceId + "+++++++++++++++++++++++推送成功+++++++++++++++++++++++");
				}
			}
		}
	}

	/**
	 * 向所有用户推送消息，包含消息模块业务处理（消息保存，消息用户关系建立）
	 * 
	 * @param messageInfo
	 * @return 0.失败 1成功
	 * @author huangzuoguo
	 * @date 2017年7月12日
	 * 
	 */
	public static Integer messageService(MessageInfo messageInfo) {
		if (messageInfo != null) {
			try {
				// 保存消息
				messageInfoService.insert(messageInfo);
				// 建立消息与用户之间的连接关系，用户可在自己的账户下查看自己的消息
				// 1.得到所有用户,建立消息和用户之间的关系
				List<AdminUser> adminUserList = new ArrayList<AdminUser>();
				adminUserList = adminUserService.selectObjectList(null);
				List<Integer> userIds = new ArrayList<Integer>();
				if (adminUserList != null & adminUserList.size() > 0) {// 批量添加用户消息关系
					for (AdminUser adminuser : adminUserList) {
						userIds.add(adminuser.getUserId());
					}
					// 用户消息跟新
					Map<String, Object> parmUp = new HashMap<String, Object>();
					parmUp.put("messageId", messageInfo.getMessageId());
					parmUp.put("isDel", 0); // 删除标示：0.否 1.是
					parmUp.put("status", 0); // 状态：0.未读 1.已读
					parmUp.put("mtime", new Date());
					parmUp.put("userId", userIds);
					messageReceiveService.addMessageReceives(parmUp);
				}
				// 2.如果需要推送就推送给所有在线用户
				if (messageInfo.getIsPush().toString().equals("1")) {
					Map<String, Object> result = new HashMap<String, Object>();
					result.put("type", messageInfo.getType());
					result.put("msg", "您有新消息：" + messageInfo.getContent());
					MsgServiceUtil.sendPush(result.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		return null;
	}

	/**
	 * 向指定用户发送消息，包括消息模块处理（消息建立，用户消息建立）
	 * 
	 * @param messageInfo
	 * @param userIds
	 * @return 0.失败 1成功
	 * @author huangzuoguo
	 * @date 2017年7月12日
	 * 
	 */
	public static Integer messageService(MessageInfo messageInfo, List<Integer> userIds) {
		try {
			if(messageInfo!=null&&userIds.size()>0){
				// 保存消息
				messageInfoService.insert(messageInfo);
				//处理消息和用户之间的绑定
				// 用户消息跟新
				Map<String, Object> parmUp = new HashMap<String, Object>();
				parmUp.put("messageId", messageInfo.getMessageId());
				parmUp.put("isDel", 0); // 删除标示：0.否 1.是
				parmUp.put("status", 0); // 状态：0.未读 1.已读
				parmUp.put("mtime", new Date());
				parmUp.put("userId", userIds);
				messageReceiveService.addMessageReceives(parmUp);
				//如果需要推送就推送给用户
				if (messageInfo.getIsPush().toString().equals("1")) {
					Map<String, Object> result = new HashMap<String, Object>();
					result.put("type", messageInfo.getType());
					result.put("msg", "您有新消息：" + messageInfo.getContent());
					MsgServiceUtil.sendPushByUser(result.toString(), userIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
