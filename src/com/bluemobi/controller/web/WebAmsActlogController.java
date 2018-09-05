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
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.serviceimpl.ams.AmsArchivesAuditServiceImpl;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;

/**
 * WEB【档案操作纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebAmsActlogController")
@RequestMapping("web/amsActlog")
public class WebAmsActlogController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebAmsActlogController.class);

    @Autowired
    private AmsArchivesActlogService amsArchivesActlogService;
    @Autowired
    private AmsArchivesAuditService amsArchivesAuditService;
    
    /**
     * 查询最新档案操作纪录
     * 
     */
    @RequestMapping(value = "getNewAmsActLogList")
    @ResponseBody
    public ResultTO getNewAmsActLogList(HttpServletRequest request) {
	LOGGER.info("*************获取档案操作纪录-begin*****************");
	// 返回结果级
	Page<Map<String, Object>> page = null;
	try {
	    // 组装查询条件
	    Map<String, Object> reqMap = new HashMap<String, Object>();
	    // 查询档案操作记录-－固定取5条最新的档案操作纪录
	    page = amsArchivesActlogService.page(reqMap, 1, 6);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案操作纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案操作纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", page);

    }

}
