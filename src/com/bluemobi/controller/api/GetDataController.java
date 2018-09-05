package com.bluemobi.controller.api;

import java.text.SimpleDateFormat;
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

import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.perception.PerceptionHcsDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.service.perception.PerceptionHcsService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

@Controller(value = "GetDataController")
@RequestMapping("api/getData")
public class GetDataController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GetDataController.class);

	@Autowired
	private PerceptionHcsDao perceptionHcsDao;
	@Autowired
	private PerceptionHcsService perceptionHcsService;
	@Autowired
	private AmsArchivesDao amsArchivesDao;
	@Autowired
	private WmsStoreDao WmsStoreDao;
	/**
	 * 查询最新温湿度纪录
	 * 
	 */
	@RequestMapping(value = "getHcs")
	@ResponseBody
	public ResultTO getNewHumiture(HttpServletRequest request) {
		LOGGER.info("*************获取最新温湿度纪录-begin*****************");
		Map<String, Object> map = new HashMap<String, Object>();
		// 库房ID
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (storeId != null) {
			Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("storeId",storeId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
		}
		// 返回结果级
		List<Map<String, Object>> result = null;
		try {
			// 查询首页档案类型和温湿度
			result = perceptionHcsDao.getWmsAvgHumTem(map);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取温湿度纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取最新温湿度纪录-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);

	}

	/**
	 * 温湿度报表
	 * 
	 */
	@RequestMapping(value = "getHcsByMon")
	@ResponseBody
	public ResultTO getHumitureReport(HttpServletRequest request) {
		LOGGER.info("*************获取温湿度报表-begin*****************");
		Map<String, Object> map = new HashMap<String, Object>();
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (storeId != null) {
			Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("storeId",storeId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
		}
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM");
		map.put("time", time.format(date));
		// 返回结果级
		Map<String, Object> rmap = new HashMap<String, Object>();
		List<Map<String, Object>> dresult = new ArrayList<Map<String, Object>>();
		// List<Map<String, Object>> wresult = new ArrayList<Map<String, Object>>();
		try {
			// 查询温湿度报表
			dresult = perceptionHcsService.getHumitureReport(map);
			/* rmap.put("data", dresult); */
			/*
			 * wresult = perceptionHcsService.getHumitureWms(rmap); rmap.put("wmslist",
			 * wresult);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取温湿度报表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取温湿度报表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", dresult);
	}

	/**
	 * 案卷号查询
	 * 
	 */
	@RequestMapping(value = "getArchivesQzh")
	@ResponseBody
	public ResultTO getArchivesQzh(HttpServletRequest request) {
		LOGGER.info("*************案卷号查询-begin*****************");
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<>();
		// 库房ID
		String stroreId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (stroreId != null) {
			Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("stroreId",stroreId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
		}
		try {
			list = amsArchivesDao.getArchivesQzh(map);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取案卷号查询出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取案卷号查询-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", list);

	}

	/**
	 * 当日最大最小温湿度
	 * 
	 */
	@RequestMapping(value = "getMaxHcs")
	@ResponseBody
	public ResultTO getMaxMinAvgHcsForToday(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (storeId != null) {
			Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("storeId",storeId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
		}
		List<Map<String, Object>> map1 = perceptionHcsDao.getMaxMinAvgHcsForToday(map);
		return ResultTO.newSuccessResultTO(map1);
	}
}
