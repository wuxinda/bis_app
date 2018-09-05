package com.bluemobi.controller.api.rfid;

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

import com.bluemobi.dao.ams.AmsArchivesAuditDao;
import com.bluemobi.dao.ams.AmsArchivesAuditInDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.rfid.RfidInoutDao;
import com.bluemobi.dao.rfid.RfidIntyDao;
import com.bluemobi.dao.wms.WmsStoreAreaDao;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesAudit;
import com.bluemobi.po.ams.AmsArchivesAuditIn;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

@Controller(value = "RfidAPIController")
@RequestMapping("api/rfid")
public class RfidAPIController {
	 private static final Logger LOGGER = LoggerFactory.getLogger(RfidAPIController.class);
	 @Autowired
	 private RfidIntyDao rfidIntyDao;
	 @Autowired
	 private RfidInoutDao rfidInoutDao ;
	 @Autowired
	 private  WmsStoreAreaDao wmsStoreAreaDao;
	 @Autowired
	 private  AmsArchivesAuditInDao amsArchivesAuditInDao ;
	 @Autowired
	 private  AmsArchivesAuditDao amsArchivesAuditDao ;
	 @Autowired
	 private  AmsArchivesDao amsArchivesDao ;
	 
	 /**
	     * 获用户权限库房下档案盘点列表
	     * 
	     * @param request
	     * @return
	     */
	    @RequestMapping(value = "getRFIDIntyByUser")
	    @ResponseBody
	    public ResultTO getRFIDIntyByUser(HttpServletRequest request) {
		LOGGER.info("*************获用户权限库房下档案盘点列表-begin*****************");
		List<Map<String,Object>> storeAreaList = null;
		List<Map<String,Object>> rfidIntyList = null;
		List<Map<String,Object>> result = new ArrayList<>();
		List<Integer> StoreList = new ArrayList<Integer>();
		Map<String,Object> StoreAreaSelectCondition = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
		    return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		// 起始时间
		String timeBegin = RequestParamUtil.getEncodeParam(request, "timeBegin");
		timeBegin+= " 00:00:00";
		// 结束时间
		String timeEnd = RequestParamUtil.getEncodeParam(request, "timeEnd");
		timeEnd+=" 23:59:59";
		if(storeId.indexOf(",")!=-1) {
			String [] storeIdList = storeId.split(",");
			for(int i=0;i<storeIdList.length;i++) {
				StoreList.add(Integer.parseInt(storeIdList[i]));
			}
		}else {
			StoreList.add(Integer.parseInt(storeId));
		}
		StoreAreaSelectCondition.put("list", StoreList);
		map.put("timeBegin", timeBegin);
		map.put("timeEnd", timeEnd);
		try {
			storeAreaList = wmsStoreAreaDao.selectStoreAreaIdByStores(StoreAreaSelectCondition);
			rfidIntyList = rfidIntyDao.getRfidIntyByTimeArea(map);
			if(storeAreaList!=null &&rfidIntyList!=null ) {
				for(int t=0;t<storeAreaList.size();t++) {
					for(int j=0;j<rfidIntyList.size();j++) {
						if(storeAreaList.get(t).get("stroreAreaId").equals(rfidIntyList.get(j).get("strore_area_id"))) {
							result.add(rfidIntyList.get(j));
						}
					}
				}
			}
			
		} catch (Exception e) {
		    e.printStackTrace();
		    LOGGER.error("获用户权限库房下档案盘点列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
		    return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获用户权限库房下档案盘点列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	    }
	    
	    /**
	     * 修改档案状态
	     * 
	     * @param request
	     * @return
	     */
	    @RequestMapping(value = "updateArchivesStatus")
	    @ResponseBody
	    public ResultTO updateArchivesStatus(HttpServletRequest request) {
		LOGGER.info("*************修改档案状态-begin*****************");
		String delete = RequestParamUtil.getEncodeParam(request, "delete");
		// 档案申请审批id
		String archivesId = RequestParamUtil.getEncodeParam(request, "archivesId");
		if (StringUtils.isEmpty(archivesId)) {
		    return ResultTO.newFailResultTO("档案id不能为空", null);
		}
		// 档案状态 0:申请中 1：待处理 2：已处理
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
		    return ResultTO.newFailResultTO("档案状态不能为空", null);
		}
		String type = RequestParamUtil.getEncodeParam(request, "type");
		String auditInId = RequestParamUtil.getEncodeParam(request, "auditInId");
		if (StringUtils.isEmpty(auditInId)) {
		    return ResultTO.newFailResultTO("申请id不能为空", null);
		}
		AmsArchivesAuditIn amsArchivesAuditIn = new AmsArchivesAuditIn();
    	Date date=new Date();
    	amsArchivesAuditIn.setMtime(date);
		amsArchivesAuditIn.setAuditInId(Integer.parseInt(auditInId));
		amsArchivesAuditIn=amsArchivesAuditInDao.selectObject(amsArchivesAuditIn);
		amsArchivesAuditIn.setStatus(status);
		AmsArchives amsArchives = new AmsArchives();
		amsArchives.setArchivesId(Integer.parseInt(archivesId));
		amsArchives=amsArchivesDao.selectObject(amsArchives);
		try {
			if(type!=null) {
				if(type.equals("0")&&status.equals("0")) {
					amsArchives.setInflag(1);
					amsArchivesAuditIn.setStatus("-1");
					amsArchivesAuditInDao.update(amsArchivesAuditIn);
					amsArchivesDao.update(amsArchives);
				}else if(type.equals("1")&&status.equals("0")) {
					amsArchives.setInflag(0);
					amsArchivesAuditIn.setStatus("-1");
					amsArchivesAuditInDao.update(amsArchivesAuditIn);
					amsArchivesDao.update(amsArchives);
				}else if(type.equals("0")&&status.equals("2")) {
					amsArchives.setInflag(0);
					amsArchivesAuditInDao.update(amsArchivesAuditIn);
					amsArchivesDao.update(amsArchives);
				}else if(type.equals("1")&&status.equals("2")) {
					amsArchives.setInflag(1);
					amsArchivesAuditInDao.update(amsArchivesAuditIn);
					amsArchivesDao.update(amsArchives);
				}
			}else{
				amsArchivesAuditInDao.update(amsArchivesAuditIn);
			}
			
		} catch (Exception e) {
		    e.printStackTrace();
		    LOGGER.error("修改档案状态出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
		    return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************修改档案状态-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	    }
	    /**
	     * 修改档案状态
	     * 
	     * @param request
	     * @return
	     */
	    @RequestMapping(value = "updateWBArchivesStatus")
	    @ResponseBody
	    public ResultTO updateWBArchivesStatus(HttpServletRequest request) {
		LOGGER.info("*************修改档案状态-begin*****************");
		String delete = RequestParamUtil.getEncodeParam(request, "delete");
		// 档案申请审批id
		String archivesId = RequestParamUtil.getEncodeParam(request, "archivesId");
		if (StringUtils.isEmpty(archivesId)) {
		    return ResultTO.newFailResultTO("档案id不能为空", null);
		}
		// 档案状态 0:申请中 1：待处理 2：已处理
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
		    return ResultTO.newFailResultTO("档案状态不能为空", null);
		}
		String type = RequestParamUtil.getEncodeParam(request, "type");
		String auditId = RequestParamUtil.getEncodeParam(request, "auditId");
		if (StringUtils.isEmpty(auditId)) {
		    return ResultTO.newFailResultTO("申请id不能为空", null);
		}
		AmsArchivesAudit amsArchivesAudit = new AmsArchivesAudit();
    	Date date=new Date();
    	amsArchivesAudit.setMtime(date);
		amsArchivesAudit.setAuditId(Integer.parseInt(auditId));
		amsArchivesAudit=amsArchivesAuditDao.selectObject(amsArchivesAudit);
		amsArchivesAudit.setStatus(status);
		AmsArchives amsArchives = new AmsArchives();
		amsArchives.setArchivesId(Integer.parseInt(archivesId));
		amsArchives=amsArchivesDao.selectObject(amsArchives);
		try {
			if(type!=null) {
				if(type.equals("0")&&status.equals("3")) {
					amsArchives.setInflag(0);
					amsArchivesDao.update(amsArchives);
				}else if(type.equals("1")&&status.equals("3")) {
					amsArchives.setInflag(1);
					amsArchivesDao.update(amsArchives);
				}
			}
				amsArchivesAuditDao.update(amsArchivesAudit);
			
		} catch (Exception e) {
		    e.printStackTrace();
		    LOGGER.error("修改档案状态出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
		    return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************修改档案状态-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	    }
	    /**
	     * 获用户权限库房下档案出入列表
	     * 
	     * @param request
	     * @return
	     */
	    @RequestMapping(value = "getRFIDInOutByUser")
	    @ResponseBody
	    public ResultTO getRFIDInOutByUser(HttpServletRequest request) {
		LOGGER.info("*************获用户权限库房下档案出入列表-begin*****************");
		List<Map<String,Object>> storeAreaList = null;
		List<Map<String,Object>> rfidInoutList = null;
		List<Map<String,Object>> result = new ArrayList<>();
		List<Integer> StoreList = new ArrayList<Integer>();
		Map<String,Object> StoreAreaSelectCondition = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
		    return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		// 起始时间
		String timeBegin = RequestParamUtil.getEncodeParam(request, "timeBegin");
		// 结束时间
		String timeEnd = RequestParamUtil.getEncodeParam(request, "timeEnd");
		if(storeId.indexOf(",")!=-1) {
			String [] storeIdList = storeId.split(",");
			for(int i=0;i<storeIdList.length;i++) {
				StoreList.add(Integer.parseInt(storeIdList[i]));
			}
		}else {
			StoreList.add(Integer.parseInt(storeId));
		}
		StoreAreaSelectCondition.put("list", StoreList);
		map.put("timeBegin", timeBegin);
		map.put("timeEnd", timeEnd);
		try {
			storeAreaList = wmsStoreAreaDao.selectStoreAreaIdByStores(StoreAreaSelectCondition);
			rfidInoutList = rfidInoutDao.getRfidInoutByTimeArea(map);
			if(rfidInoutList!=null &&rfidInoutList!=null ) {
				for(int t=0;t<storeAreaList.size();t++) {
					for(int j=0;j<rfidInoutList.size();j++) {
						if(storeAreaList.get(t).get("stroreAreaId").equals(rfidInoutList.get(j).get("strore_area_id"))) {
							result.add(rfidInoutList.get(j));
						}
					}
				}
			}
			
		} catch (Exception e) {
		    e.printStackTrace();
		    LOGGER.error("获用户权限库房下档案出入列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
		    return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获用户权限库房下档案出入列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	    }
	    /**
	     * 修改档案状态
	     * 
	     * @param request
	     * @return
	     */
	    @RequestMapping(value = "deleteArchivesStatus")
	    @ResponseBody
	    public ResultTO deleteArchivesStatus(HttpServletRequest request) {
		LOGGER.info("*************修改档案状态-begin*****************");
		String delete = RequestParamUtil.getEncodeParam(request, "delete");
		// 档案申请审批id
		String archivesId = RequestParamUtil.getEncodeParam(request, "archivesId");
		if (StringUtils.isEmpty(archivesId)) {
		    return ResultTO.newFailResultTO("档案id不能为空", null);
		}
		// 档案状态 0:申请中 1：待处理 2：已处理
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (StringUtils.isEmpty(type)) {
		    return ResultTO.newFailResultTO("档案状态不能为空", null);
		}
		String auditInId = RequestParamUtil.getEncodeParam(request, "auditInId");
		if (StringUtils.isEmpty(auditInId)) {
		    return ResultTO.newFailResultTO("申请id不能为空", null);
		}
		AmsArchivesAuditIn amsArchivesAuditIn = new AmsArchivesAuditIn();
		amsArchivesAuditIn.setAuditInId(Integer.parseInt(auditInId));
		AmsArchives amsArchives = new AmsArchives();
		amsArchives.setArchivesId(Integer.parseInt(archivesId));
		amsArchives=amsArchivesDao.selectObject(amsArchives);
		try {
			if(type.equals("0")) {
			amsArchives.setInflag(1);
			amsArchivesAuditInDao.delete(amsArchivesAuditIn);
			amsArchivesDao.update(amsArchives);
			}else if(type.equals("1")) {
				amsArchives.setInflag(1);
				amsArchivesAuditInDao.delete(amsArchivesAuditIn);
				amsArchivesDao.update(amsArchives);
			}
		} catch (Exception e) {
		    e.printStackTrace();
		    LOGGER.error("修改档案状态出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
		    return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************修改档案状态-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	    }
	    
}

