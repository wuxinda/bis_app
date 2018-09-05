package com.bluemobi.controller.api.statis;
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
import com.bluemobi.service.satatis.WmsSatatisService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
/**
 * 统计控制器
 * @author 
 *
 */
@Controller(value = "statisAPIController")
@RequestMapping("api/statis")
public class StatisAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(StatisAPIController.class);
	@Autowired
	private WmsSatatisService wmsSatatisService;	
	/**
	 * 档案统计数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchAmsList")
	@ResponseBody
	public ResultTO searchAmsList(HttpServletRequest request) {
		LOGGER.info("*************获取档案统计数据-begin*****************");
		//返回结果级
		Map<String, Object> result = null;
		try {
	         Map<String, Object> map = this.paraminit(request);
		 result = wmsSatatisService.selectAmsArchivesStatis(map);
					
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取档案统计数据出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取档案统计数据-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}
	/**
	 * 环境统计数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searcHjhList")
	@ResponseBody
	public ResultTO searchHumitureList(HttpServletRequest request) {
	    LOGGER.info("*************获取档环境计数据-begin*****************");
	    //返回结果级
	    Map<String, Object> result = null;
	    try {
		Map<String, Object> map = this.paraminit(request);
		result = wmsSatatisService.selectHumitureStatis(map);
		
	    } catch (Exception e) {
		e.printStackTrace();
		LOGGER.error("获取环境统计数据出现异常【{}】，请求ip【{}】，请求信息【{}】",
			new Object[] { e.getMessage(), request.getRemoteHost() });
		return ResultTO.newFailResultTO("请求失败！", null);
	    }
	    LOGGER.info("*************获环境案统计数据-end*****************");
	    return ResultTO.newSuccessResultTO("请求成功", result);
	}
	/**
	 * 报警统计数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchAlarmList")
	@ResponseBody
	public ResultTO searchAlarmList(HttpServletRequest request) {
	    LOGGER.info("*************获报警计数据-begin*****************");
	    //返回结果级
	    List<Map<String,Object>> result = null;
	    try {
		Map<String, Object> map = this.paraminit(request);
		result = wmsSatatisService.selectAlarmStatis(map);
		
	    } catch (Exception e) {
		e.printStackTrace();
		LOGGER.error("获取报警统计数据出现异常【{}】，请求ip【{}】，请求信息【{}】",
			new Object[] { e.getMessage(), request.getRemoteHost() });
		return ResultTO.newFailResultTO("请求失败！", null);
	    }
	    LOGGER.info("*************获环报警统计数据-end*****************");
	    return ResultTO.newSuccessResultTO("请求成功", result);
	}
	
	
	/**
	 * 参数格式化
	 * @param request
	 * @return
	 */
	public Map<String,Object> paraminit(HttpServletRequest request){
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    	   //获取参数
		   String currentTime = RequestParamUtil.getEncodeParam(request, "currentType");//当前时间（年/月/日）
		   String starTime = RequestParamUtil.getEncodeParam(request, "starTime");//开始时间
		   String endTime = RequestParamUtil.getEncodeParam(request, "endTime");//结束时间
		   String storeId = RequestParamUtil.getEncodeParam(request, "storeId");//库房id
	    
		 //获取档案操作数据，当查询具体时间starttime和endtime有值时将会清空当前时间
		   if(!StringUtils.isEmpty(starTime)||!StringUtils.isEmpty(endTime)){
		       currentTime=null;
		   }
		   if(StringUtils.isEmpty(currentTime))currentTime=null;
		   if(StringUtils.isEmpty(starTime))starTime=null;
		   if(StringUtils.isEmpty(endTime))endTime=null;
		   if(StringUtils.isEmpty(storeId))storeId=null;
		   map.put("currentType", currentTime);
		   map.put("starTime", starTime);
		   map.put("endTime", endTime);
		   map.put("storeId", storeId);
	    
	    return map;
	    
	}
}
