package com.bluemobi.controller.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.ams.AmsArchivesAuditDao;
import com.bluemobi.dao.ams.AmsArchivesAuditInDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
import com.bluemobi.util.excel.excel;

/**
 * WEB【档案出入纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebArchiveController")
@RequestMapping("web/archive")
public class WebArchiveController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebArchiveController.class);

    @Autowired
    private AmsArchivesDao amsArchivesDao;
    @Autowired
    private AmsArchivesActlogService amsArchivesActlogService;
    @Autowired
    private AmsArchivesAuditService amsArchivesAuditService;
    @Autowired
    private AmsArchivesAuditDao amsArchivesAuditDao;
    @Autowired
    private AmsArchivesAuditInDao amsArchivesAuditInDao;
    /**
     * 查询最新档案出入纪录
     * 
     */
    @RequestMapping(value = "getNewInOutAms")
    @ResponseBody
    public ResultTO getNewInOutAms(HttpServletRequest request) {
	LOGGER.info("*************获取档案出入纪录-begin*****************");
	// 返回结果级
	Map<String,Object> result = new HashMap<String,Object>();
	List<Map<String,Object>> year = null;
	List<Map<String,Object>> month = null;
	List<Map<String,Object>> day = null;
	try {
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("type", 1);//年
	    year = amsArchivesActlogService.getNewInOutAms(map);
	    if(year!=null)result.put("year", year);
	    map.put("type", 2);//月
	    month = amsArchivesActlogService.getNewInOutAms(map);
	    if(month!=null)result.put("month", month);
	    map.put("type", 3);//日
	    day = amsArchivesActlogService.getNewInOutAms(map);
	    if(day!=null)result.put("day", day);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案出入纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案出入纪录-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", result);

    }
    /**
     * 获取待执行档案列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getRfidInout")
    @ResponseBody
    public ResultTO getRfidInout(HttpServletRequest request) {
	LOGGER.info("*************获取档案审批列表-begin*****************");
	// 参数格式化
	Map<String, Object> parmMap = new HashMap<String, Object>();
	// 审批状态
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("审批状态不能为空", null);
	}
	parmMap.put("status", status);
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
	Page<Map<String, Object>> page  = null;
	try {
	    // 查询档案审核列表
	    page = amsArchivesAuditService.page(parmMap,Integer.parseInt(pageIndex), Integer.parseInt(pageSize));

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案审批列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案审批列表-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", page);
	
    }
    
    /**
     * 获取待执行档案列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getNewAuditAms")
    @ResponseBody
    public ResultTO getNewAuditAms(HttpServletRequest request) {
	LOGGER.info("*************获取档案审批列表-begin*****************");
	// 参数格式化
	Map<String, Object> parmMap = new HashMap<String, Object>();
	// 审批状态
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("审批状态不能为空", null);
	}
	parmMap.put("status", status);
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
	Page<Map<String, Object>> page  = null;
	try {
	    // 查询档案审核列表
	    page = amsArchivesAuditService.page(parmMap,Integer.parseInt(pageIndex), Integer.parseInt(pageSize));

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案审批列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案审批列表-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", page);
	
    }
    /**
     * 按条件获取待执行档案列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getAuditAmsList")
    @ResponseBody
    public ResultTO getAuditAmsList(HttpServletRequest request) {
	LOGGER.info("*************获取档案审批列表-begin*****************");
	Map<String,Object> map=new HashMap<String,Object>();
	// 档案名称
	String archiveName = RequestParamUtil.getEncodeParam(request, "archiveName");
	if(archiveName!=null&&archiveName!="") {
		map.put("title", "%"+archiveName+ "%");
	}
	
	// 开始时间
	String timeStart = RequestParamUtil.getEncodeParam(request, "timeStart");
	if(timeStart!=null&&timeStart!="") {
		map.put("timeStart", timeStart);
	}
	// 结束时间
	String timeEnd = RequestParamUtil.getEncodeParam(request, "timeEnd");
	if(timeEnd!=null&&timeEnd!="") {
		map.put("timeEnd", timeEnd);
	}
	// 库房Id
	String storeId = RequestParamUtil.getEncodeParam(request, "store");
	if(storeId!=null&& storeId!="") {
		map.put("stroreId", storeId);
	}else {
		AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
  		map.put("enName",user.getEnName());
	}
	// 库区Id
	String storeAreaId = RequestParamUtil.getEncodeParam(request, "storeArea");
	if(storeAreaId!=null&& storeAreaId!="") {
		map.put("stroreAreaId", storeAreaId);;
	}
	//
	String inout = RequestParamUtil.getEncodeParam(request, "inout");
	if (StringUtils.isEmpty(inout)) {
	    return ResultTO.newFailResultTO("内外部操作不能为空", null);
	}
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("档案状态不能为空", null);
	}
	// 第几页
	String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
	if (StringUtils.isEmpty(pageIndex)) {
	    return ResultTO.newFailResultTO("第几页不能为空", null);
	}
	//操作类型
	String type = RequestParamUtil.getEncodeParam(request, "type");
	if(type!=null&& type!="") {
		map.put("type", type);;
	}
	map.put("status", status);
	map.put("pageIndex", Integer.parseInt(pageIndex)-1);
	map.put("type", type);
	
	Map<String,Object> resmap=new HashMap<String,Object>();
	try {
		if(inout.equals("0")) {
			List<Map<String,Object>> list=amsArchivesAuditDao.getAmsArchivesAudit(map);
			int rfidCount=amsArchivesAuditDao.getAmsArchivesAuditCount(map);
			resmap.put("data", list);
			resmap.put("count", rfidCount);
		}else if(inout.equals("1")) {
			List<Map<String,Object>> list=amsArchivesAuditInDao.getAmsArchivesAuditIn(map);
			int rfidCount=amsArchivesAuditInDao.getAmsArchivesAuditInCount(map);
			resmap.put("data", list);
			resmap.put("count", rfidCount);
		}
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案审批列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案审批列表-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", resmap);
	
    }
    /**
     * 按条件获取待执行档案列表
     * @param request
     * @return
     */
    @RequestMapping(value = "getAuditAmsListToexcel")
    @ResponseBody
    public ResultTO getAuditAmsListToexcel(HttpServletRequest request,HttpServletResponse response) {
	LOGGER.info("*************获取档案审批列表-begin*****************");
	Map<String,Object> map=new HashMap<String,Object>();
	// 档案名称
	String archiveName = RequestParamUtil.getEncodeParam(request, "archiveName");
	if(archiveName!=null&&archiveName!="") {
		map.put("title", "%"+archiveName+ "%");
	}
	
	// 开始时间
	String timeStart = RequestParamUtil.getEncodeParam(request, "timeStart");
	if(timeStart!=null&&timeStart!="") {
		map.put("timeStart", timeStart);
	}
	// 结束时间
	String timeEnd = RequestParamUtil.getEncodeParam(request, "timeEnd");
	if(timeEnd!=null&&timeEnd!="") {
		map.put("timeEnd", timeEnd);
	}
	// 库房Id
	String storeId = RequestParamUtil.getEncodeParam(request, "store");
	if(storeId!=null&& storeId!="") {
		map.put("stroreId", storeId);
	}
	// 库区Id
	String storeAreaId = RequestParamUtil.getEncodeParam(request, "storeArea");
	if(storeAreaId!=null&& storeAreaId!="") {
		map.put("stroreAreaId", storeAreaId);;
	}
	//
	String inout = RequestParamUtil.getEncodeParam(request, "inout");
	if (StringUtils.isEmpty(inout)) {
	    return ResultTO.newFailResultTO("内外部操作不能为空", null);
	}
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("档案状态不能为空", null);
	}
	//操作类型
	String type = RequestParamUtil.getEncodeParam(request, "type");
	if (StringUtils.isEmpty(type)) {
		return ResultTO.newFailResultTO("操作类型不能为空", null);
	}
	map.put("status", status);
	map.put("type", type);
	
	List<Map<String,Object>> list=null;
	String sheetName=null;
	String titleName=null;
	String fileName=null;
	String[] getName= {"startTime","title","storeplace","applyUser","remark"};
	String[] columnName= {"操作时间","档案名称","操作地点","操作人员","备注"};
	 List<String[]> dataList =new ArrayList<String[]>();
	try {
		if(inout.equals("0")) {
			list=amsArchivesAuditDao.getAmsArchivesAudit(map);
			if(type.equals("0")) {
				sheetName="外部借档操作记录";
	    		titleName="外部借档操作记录";
	    		fileName="wbjd"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
			}else {
				sheetName="外部还档操作记录";
	    		titleName="外部还档操作记录";
	    		fileName="wbhd"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
			}
		}else if(inout.equals("1")) {
			list=amsArchivesAuditInDao.getAmsArchivesAuditIn(map);
			if(type.equals("0")) {
				sheetName="内部借档操作记录";
	    		titleName="内部借档操作记录";
	    		fileName="nbjd"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
			}else {
				sheetName="内部还档操作记录";
	    		titleName="内部还档操作记录";
	    		fileName="nbhd"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
			}
		}
		for(Map<String,Object> m:list) {
    		String[] strs=new String[getName.length];
    		for (int i = 0; i < getName.length; i++) {
    			String var=String.valueOf(m.get(getName[i]));
    			if(m.get(getName[i])==null) {
    				var="";
    			}
    			strs[i]=var;
			}
    		dataList.add(strs);
    	}
		excel.ExportWithResponse(sheetName, titleName, fileName, columnName, dataList, response);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案审批列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案审批列表-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", list);
	
    }
    /**
     * 按条件获取待执行档案列表
     * @param request
     * @return
     */
    @RequestMapping(value = "indexFindArchives")
    @ResponseBody
    public ResultTO indexFindArchives(HttpServletRequest request,HttpServletResponse response) {
    	LOGGER.info("*************获取档案首页搜索列表列表-begin*****************");
    	Map<String,Object> map=new HashMap<String,Object>();
    	String keyWord = RequestParamUtil.getEncodeParam(request, "keyWord");
    	if(keyWord!=null&&keyWord!="") {
    		map.put("keyWord", "%"+keyWord+ "%");
    	}
    	String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
    	if (StringUtils.isEmpty(pageIndex)) {
    	    return ResultTO.newFailResultTO("第几页不能为空", null);
    	}
    	String pagesize = RequestParamUtil.getEncodeParam(request, "pagesize");
    	if (StringUtils.isEmpty(pagesize)) {
    	    return ResultTO.newFailResultTO("条数不能为空", null);
    	}
    	map.put("pagesize", Integer.parseInt(pagesize));
    	map.put("pageIndex",(Integer.parseInt(pageIndex)-1)*Integer.parseInt(pagesize));
    	AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
    	String userStroreId= user.getEnName();
    	map.put("userStroreId",userStroreId);
    	Map<String,Object> resmap=new HashMap<String,Object>();
    	try {
    	List<Map<String,Object>> list=amsArchivesDao.indexFindArchives(map);
    	Integer count=amsArchivesDao.indexFindArchivesCount(map);
    	resmap.put("data", list);
    	resmap.put("count", count);
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    return ResultTO.newFailResultTO("请求失败！", null);
    	}
    	LOGGER.info("*************获取档案审批列表-end*****************");
    	return ResultTO.newSuccessResultTO("请求成功", resmap);
    }
}
