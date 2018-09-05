package com.bluemobi.controller.web;

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
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.device.DeviceManageDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.service.satatis.WmsSatatisService;
import com.bluemobi.to.ResultTO;

/**
 * WEB【馆藏量纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebHoldingController")
@RequestMapping("web/holding")
public class WebHoldingController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHoldingController.class);

    @Autowired
    private AmsArchivesDao amsArchivesDao;
    @Autowired
    private DeviceManageDao deviceManageDao;
    @Autowired
    private WmsStoreDao wmsStoreDao;
    @Autowired
    private WmsSatatisService wmsSatatisService;

    /**
     * 查询最新馆藏量纪录
     * 
     */
    @RequestMapping(value = "getNewHolding")
    @ResponseBody
    public ResultTO getNewHolding(HttpServletRequest request) {
	LOGGER.info("*************获取馆藏量纪录-begin*****************");
	// 返回结果级
	List<Map<String, Object>> result = null;
	try {
	    // 查询首页档案类型和馆藏量
	    result = amsArchivesDao.selectAmsTypeStatis(null);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取馆藏量纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取馆藏量纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", result);

    }

    /**
     * 查询馆藏量基础数据统计
     * 
     */
    @RequestMapping(value = "getBaseNum")
    @ResponseBody
    public ResultTO getBaseNum(HttpServletRequest request) {
	LOGGER.info("*************获取馆藏量基础数据统计纪录-begin*****************");
	// 返回结果级
	Map<String, Object> data = new HashMap<String, Object>();
	try {
	    List<Map<String, Object>> wmsNum = null;
	    List<Map<String, Object>> selNum = null;
	    List<Map<String, Object>> amsNum = null;
	    // 查询馆藏量基础数据统计
	    wmsNum = wmsStoreDao.selectObjectList(null);
	    Map<String, Object> pre = new HashMap<String, Object>();
	    pre.put("categoryId", 5);
	    selNum = deviceManageDao.selectObjectList(pre);
	    amsNum = amsArchivesDao.selectObjectList(null);
	    if (wmsNum.size() > 0)
		data.put("wmsNum", wmsNum.size());
	    if (selNum.size() > 0)
		data.put("selNum", selNum.size());
	    if (amsNum.size() > 0)
		data.put("amsNum", amsNum.size());
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取馆藏量基础数据统计出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取馆藏量基础数据统计纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", data);

    }

    /**
     * 查询馆藏量档案数据统计
     * 
     */
    @RequestMapping(value = "getAmsSatas")
    @ResponseBody
    public ResultTO getAmsSatas(HttpServletRequest request) {
	LOGGER.info("*************获取馆藏量档案数据统计纪录-begin*****************");
	// 返回结果级
	Map<String, Object> result = null;
	try {
	    result = wmsSatatisService.selectAmsArchivesStatis(null);
	    List<Map<String, Object>> wmsStore = amsArchivesDao.selectAmsgroupByStore(null);
	    result.put("wmsStore", wmsStore);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取馆藏量档案数据统计出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取馆藏量档案数据统计纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", result);

    }

}
