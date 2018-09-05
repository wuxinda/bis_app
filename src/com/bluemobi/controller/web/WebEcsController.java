package com.bluemobi.controller.web;

import java.util.ArrayList;
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
import com.bluemobi.service.ecs.EcsManageService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 能耗记录控制器
 * 
 * @author Riven
 * 
 */
@Controller(value = "webEcsController")
@RequestMapping("web/ecs")
public class WebEcsController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebEcsController.class);

	@Autowired
	private EcsManageService ecsManageService;

	/**
	 * 获取能耗总数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTotalData")
	@ResponseBody
	public ResultTO getTotalData(HttpServletRequest request) {
		LOGGER.info("*************获取能耗记录-begin*****************");
		// 查询时间
		String cyear = RequestParamUtil.getEncodeParam(request, "searchYear");

		// 查询时间
		String cmonth = RequestParamUtil.getEncodeParam(request, "searchMonth");
		List<String[]> result = new ArrayList<String[]>();
		try {

			// 组装查询条件
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("cYear", cyear);
			reqMap.put("cMonth", cmonth);
			List<Map<String, Object>> list = ecsManageService.getTotalData(reqMap);
			if (list != null && list.size() > 0) {
				String str = String.valueOf(list.get(0).get("actualEc"));
				String str1 = String.valueOf(list.get(0).get("targetEc"));
				String[] sj = { "实际能耗", str };
				String[] mb = { "目标能耗", str1 };
				result.add(sj);
				result.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取能耗记录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取能耗记录-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}

	/**
	 * 获取库房能耗统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStoreEcs")
	@ResponseBody
	public ResultTO getStoreEcs(HttpServletRequest request) {
		LOGGER.info("*************获取能耗记录-begin*****************");
		// 查询时间
		String cyear = RequestParamUtil.getEncodeParam(request, "searchYear");

		// 查询时间
		String cmonth = RequestParamUtil.getEncodeParam(request, "searchMonth");

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {

			// 组装查询条件
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("cYear", cyear);
			reqMap.put("cMonth", cmonth);
			result = ecsManageService.getStoreEcs(reqMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取能耗记录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取能耗记录-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}
	/**
	 * 获取分项实际能耗统计
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTypeEcs")
	@ResponseBody
	public ResultTO getTypeEcs(HttpServletRequest request) {
		LOGGER.info("*************获取能耗记录-begin*****************");
		// 查询时间
		String cyear = RequestParamUtil.getEncodeParam(request, "searchYear");
		
		// 查询时间
		String cmonth = RequestParamUtil.getEncodeParam(request, "searchMonth");
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			
			// 组装查询条件
			Map<String, Object> reqMap = new HashMap<String, Object>();
			reqMap.put("cYear", cyear);
			reqMap.put("cMonth", cmonth);
			result = ecsManageService.getTypeEcs(reqMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取能耗记录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取能耗记录-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}
}
