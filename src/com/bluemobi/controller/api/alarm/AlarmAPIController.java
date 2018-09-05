package com.bluemobi.controller.api.alarm;

import java.util.ArrayList;
import java.util.Date;
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

import com.appcore.page.Page;
import com.bluemobi.apito.alarm.AlarmManageTO;
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
@Controller(value = "alarmAPIController")
@RequestMapping("api/alarm")
public class AlarmAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmAPIController.class);

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
	List<AlarmManageTO> alarmManageTOList = new ArrayList<AlarmManageTO>();
	try {
	    // 组装查询条件
	    Map<String, Object> reqMap = new HashMap<String, Object>();
	    reqMap.put("status", status);
	    // 查询报警
	    Page<Map<String, Object>> page = alarmManageService.page(reqMap, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
	    // 过滤报警返回字段
	    for (Map<String, Object> alarmManageMap : page.getData()) {
		AlarmManageTO to = new AlarmManageTO();
		to.setAlarmId((Integer) alarmManageMap.get("alarmId"));
		to.setLevel((String) alarmManageMap.get("level"));
		to.setModifier((Integer) alarmManageMap.get("modifier"));
		to.setRemark((String) alarmManageMap.get("remark"));
		to.setStatus((String) alarmManageMap.get("status"));
		to.setMtime((Date) alarmManageMap.get("ctime"));
		alarmManageTOList.add(to);
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取报警记录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取报警记录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", alarmManageTOList);
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
		reqMap.put("mtime", new Date());
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
}
