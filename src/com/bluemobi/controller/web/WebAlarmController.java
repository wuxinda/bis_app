package com.bluemobi.controller.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.alarm.AlarmManageService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 报警记录控制器
 * 
 * @author Riven
 * 
 */
@Controller(value = "webAlarmController")
@RequestMapping("web/alarm")
public class WebAlarmController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAlarmController.class);

    @Autowired
    private AlarmManageService alarmManageService;

    /**
     * 获取报警记录
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "searchList")
    @ResponseBody
    public ResultTO searchList(HttpServletRequest request) {
	LOGGER.info("*************获取报警记录-begin*****************");
	// 查询时间
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("状态不能为空", null);
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
	// 返回结果级
	Page<Map<String, Object>> page =null;
	try {
	    // 组装查询条件
	    Map<String, Object> reqMap = new HashMap<String, Object>();
	    reqMap.put("status", status);
	    // 查询报警
	    page = alarmManageService.page(reqMap, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取报警记录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取报警记录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", page);
    }

    /**
     * 获取最新报警纪录
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "getNewAlarmList")
    @ResponseBody
    public ResultTO getNewAlarmList(HttpServletRequest request) {
	LOGGER.info("*************获取最新报警纪录-begin*****************");
	// 返回结果级
	Page<Map<String, Object>> page = null;
	try {
	    // 组装查询条件
	    Map<String, Object> reqMap = new HashMap<String, Object>();
	    // 查询报警-－固定取5条最新的报警纪录
	    page = alarmManageService.page(reqMap, 1, 11);

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取最新报警纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取最新报警纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", page);
    }

    /**
     * 处理报警
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "handle")
    @ResponseBody
    public ResultTO handle(HttpServletRequest request) {
	LOGGER.info("*************处理报警-begin*****************");
	// 报警id
	String alarmId = RequestParamUtil.getEncodeParam(request, "alarmId");
	if (StringUtils.isEmpty(alarmId)) {
	    return ResultTO.newFailResultTO("报警不能为空", null);
	}
	// 用户Id
	String userId = RequestParamUtil.getEncodeParam(request, "userId");
	if (StringUtils.isEmpty(userId)) {
	    return ResultTO.newFailResultTO("用户不能为空", null);
	}

	try {
	    for (String s : alarmId.split(",")) {
		// 组装查询条件
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("alarmId", Integer.parseInt(s));
		reqMap.put("status", "1");
		reqMap.put("modifier", Integer.parseInt(userId));
		// 更新报警状态
		alarmManageService.update(reqMap);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("处理报警出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************处理报警-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", null);
    }

    /**
     * 报警统计
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "getAlarmSata")
    @ResponseBody
    public ResultTO getAlarmSata(HttpServletRequest request) {
	LOGGER.info("*************报警统计-begin*****************");
	Map<String, Object> req = new HashMap<String, Object>();
	// 类型
	String type = RequestParamUtil.getEncodeParam(request, "type");
	req.put("type", type);
	// 时间
	String date = RequestParamUtil.getEncodeParam(request, "date");
	req.put("date", date);
	// 返回结果集
	Map<String, Object> reqMap = new HashMap<String, Object>();
	try {

	    reqMap = alarmManageService.getAlarmSata(req);

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("报警统计出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************报警统计-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", reqMap);
    }
}
