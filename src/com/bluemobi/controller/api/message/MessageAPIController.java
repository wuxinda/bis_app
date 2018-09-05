package com.bluemobi.controller.api.message;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.message.MessageInfo;
import com.bluemobi.po.message.MessageReceive;
import com.bluemobi.service.message.MessageInfoService;
import com.bluemobi.service.message.MessageReceiveService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
/**
 * 消息控制器
 * @author Riven
 *
 */
@Controller(value = "messageAPIController")
@RequestMapping("api/message")
public class MessageAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageAPIController.class);
    
	@Autowired
	private MessageInfoService messageInfoService;
	@Autowired
	private MessageReceiveService messageReceiveService;
	/**
	 * 获取消息列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getList")
	@ResponseBody
	public ResultTO getList(HttpServletRequest request) {
		LOGGER.info("*************获取消息列表-begin*****************");
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 消息状态：0.未读 1.已读
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("消息状态不能为空", null);
		}
		// 第几页
		String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			return ResultTO.newFailResultTO("第几页不能为空", null);
		}
		// 每页条数
		String pageSize = RequestParamUtil.getEncodeParam(request, "pageSize");
		if (StringUtils.isEmpty(pageSize)) {
			return ResultTO.newFailResultTO("每页条数不能为空", null);
		}
		//返回结果级
		List<Map<String, Object>> page = null;
		try {
			//组装查询条件
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("userId", userId);
			reqMap.put("status", status);
			//查询用户消息列表
		    page = messageReceiveService.selectMsgList(reqMap, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取消息列表出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取消息列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", page);
	}
	
	/**
	 * 获取消息详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getDetail")
	@ResponseBody
	public ResultTO getDetail(HttpServletRequest request) {
		LOGGER.info("*************获取消息详情-begin*****************");
		// 用户id
		String messageId = RequestParamUtil.getEncodeParam(request, "messageId");
		if (StringUtils.isEmpty(messageId)) {
			return ResultTO.newFailResultTO("消息id不能为空", null);
		}
		// 消息id（用户关联消息）
		String messageReciveId = RequestParamUtil.getEncodeParam(request, "messageReciveId");
		if (StringUtils.isEmpty(messageReciveId)) {
		    return ResultTO.newFailResultTO("消息关联id不能为空", null);
		}
		//返回结果级
		MessageInfo messageInfo = null;
		MessageReceive messageReceive = new MessageReceive();
		try {
			//查看消息详情
			messageInfo = messageInfoService.selectObject(messageId);
			messageReceive.setStatus(1);
			messageReceive.setReceiveId(Integer.parseInt(messageReciveId));
			messageReceiveService.update(messageReceive);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取消息详情出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取消息详情-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", messageInfo);
	}
	/**
	 * 获取消息详情
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public ResultTO delete(HttpServletRequest request) {
	    LOGGER.info("*************删除消息详情-begin*****************");
	    // 消息id（用户关联消息）
	    String messageReciveId = RequestParamUtil.getEncodeParam(request, "messageReciveId");
	    if (StringUtils.isEmpty(messageReciveId)) {
		return ResultTO.newFailResultTO("消息关联id不能为空", null);
	    }
	    try {
		messageReceiveService.deletes(messageReciveId.split(","));
	    } catch (Exception e) {
		e.printStackTrace();
		LOGGER.error("删除消息详情出现异常【{}】，请求ip【{}】，请求信息【{}】",
			new Object[] { e.getMessage(), request.getRemoteHost() });
		return ResultTO.newFailResultTO("请求失败！", null);
	    }
	    LOGGER.info("*************删除消息详情-end*****************");
	    return ResultTO.newSuccessResultTO("请求成功", null);
	}
}
