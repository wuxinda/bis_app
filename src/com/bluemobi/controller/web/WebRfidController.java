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

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.device.DeviceManageDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.rfid.RfidIntyService;
import com.bluemobi.to.ResultTO;

/**
 * WEB【rifd纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebRfidController")
@RequestMapping("web/rfid")
public class WebRfidController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebRfidController.class);

	@Autowired
	private RfidIntyService rfidIntyService;
	

	/**
     * 查询最新RFID操作纪录
     * 
     */
    @RequestMapping(value = "getNewIntyList")
    @ResponseBody
    public ResultTO getNewIntyList(HttpServletRequest request) {
	LOGGER.info("*************获取RFID纪录-begin*****************");
	// 返回结果级
	Page<Map<String, Object>> page = null;
	try {
	    // 组装查询条件
	    Map<String, Object> reqMap = new HashMap<String, Object>();
	    // 查询RFID记录-－固定取3条最新的档案操作纪录
	    page = rfidIntyService.page(reqMap, 1, 3);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取RFID纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取RFID纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", page);

    }
}
