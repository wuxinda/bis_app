package com.bluemobi.controller.api.otherapi;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.admin.AdminUserDao;
import com.bluemobi.dao.ams.AmsArchivesAuditInDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.rfid.RfidInoutDao;
import com.bluemobi.dao.rfid.RfidIntyDao;
import com.bluemobi.dao.wms.WmsStoreAreaDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.alarm.AlarmManage;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesActlog;
import com.bluemobi.po.ams.AmsArchivesAuditIn;
import com.bluemobi.po.rfid.RfidInout;
import com.bluemobi.po.rfid.RfidInty;
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.alarm.AlarmManageService;
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.service.ams.AmsArchivesAuditInService;
import com.bluemobi.service.ams.AmsArchivesService;
import com.bluemobi.service.rfid.RfidInoutService;
import com.bluemobi.service.rfid.RfidIntyService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.MsgServiceUtil;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

import net.sf.json.JSONArray;

/**
 * 档案对外接口控制类
 * 
 * @author Tony黄
 * 
 */
@Controller(value = "RFIDOtherAPIController")
@RequestMapping("api/otherapi")
public class RFIDOtherAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RFIDOtherAPIController.class);
	@Autowired
	private RfidInoutService rfidInoutService;
	@Autowired
	private RfidIntyService rfidIntyService;
	@Autowired
	private AlarmManageService alarmManageService;
	@Autowired
	private AmsArchivesActlogService amsArchivesActlogService;
	@Autowired
	private AmsArchivesService amsArchivesService;
	@Autowired
	private AmsArchivesAuditInDao amsArchivesAuditInDao;
    @Autowired
   	private AmsArchivesDao amsArchivesDao;
    @Autowired
   	private WmsStoreDao wmsStoreDao;
	Random random = new Random();

	/**
	 * rfid门禁出入 applyNo applyUser auditType archiveno clientId
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "rfidInoutSync")
	@ResponseBody
	public ResultTO rfidInoutSync(HttpServletRequest request) {
		LOGGER.info("*************RFID进出同步提交-begin*****************");
		// 档案号
		String archiveno = RequestParamUtil.getEncodeParam(request, "archiveno");
		if (StringUtils.isEmpty(archiveno)) {
			return ResultTO.newFailResultTO("档案号号不能为空", null);
		}
		// 档案标题
		String tittle = RequestParamUtil.getEncodeParam(request, "tittle");
		if (StringUtils.isEmpty(tittle)) {
			// return ResultTO.newFailResultTO("档案标题不能为空", null);
			tittle = "";
		}
		// 操作类型 0.出库 1.入库
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("操作类型不能为空", null);
		}
		// 状态 0.正常 1.异常
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("状态不能为空", null);
		}
		// 操作人姓名
		String userName = RequestParamUtil.getEncodeParam(request, "userName");
		if (StringUtils.isEmpty(userName)) {
			return ResultTO.newFailResultTO("操作人姓名不能为空", null);
		}
		// 操作时间
		String dateTime = RequestParamUtil.getEncodeParam(request, "dateTime");
		if (StringUtils.isEmpty(dateTime)) {
			return ResultTO.newFailResultTO("操作时间", null);
		}
		try {
			RfidInout rfidInout = new RfidInout();
			rfidInout.setArchiveno(archiveno);
			rfidInout.setTittle(tittle);
			rfidInout.setType(type);
			rfidInout.setStatus(status);
			rfidInout.setUsername(userName);
			rfidInout.setCtime(new Date());
			// 添加
			rfidInoutService.insert(rfidInout);
			if (status.equals("1")) {
				AlarmManage alarmManage = new AlarmManage();
				alarmManage.setAlarmType(6);
				alarmManage.setCategoryId(6);
				alarmManage.setDeviceName("一号设备");
				alarmManage.setCtime(new Date());
				alarmManage.setRemark("RFID出入库异常");
				alarmManage.setStatus("0");
				alarmManage.setLevel("1");
				// 以下模拟数据写成固定
				alarmManage.setStoreId(1);
				alarmManage.setStroreAreaId(1);
				alarmManageService.insert(alarmManage);
			}
			// 添加档案操作数据
			AmsArchivesActlog amsArchivesActlog = new AmsArchivesActlog();
			amsArchivesActlog.setArchivesId(random.nextInt(30) + 1);// 模拟数据演示用，，应该用档案号查询档案ID 不过要档案数据同步才能用
			amsArchivesActlog.setCreator(1);
			amsArchivesActlog.setCtime(new Date());
			amsArchivesActlog.setModifier(1);
			amsArchivesActlog.setMtime(new Date());
			// AmsArchivesActlog.setStoreareaId(storeareaId);
			amsArchivesActlog.setUserId(1);
			amsArchivesActlog.setStatus("1");
			if (String.valueOf(type).equals("1")) {
				amsArchivesActlog.setType("0");
			} else {
				amsArchivesActlog.setType("4");
			}
			// AmsArchivesActlog.setWmsstoreId(wmsstoreId);
			amsArchivesActlogService.insert(amsArchivesActlog);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("RFID进出同步出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost()});
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		// 批量插入数据
		LOGGER.info("*************RFID进出同步提交-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * RFID盘点数据同步
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "rfidIntySync")
	@ResponseBody
	public ResultTO rfidIntySync(HttpServletRequest request) {
		LOGGER.info("*************RFID盘点数据同步提交-begin*****************");
		// 档案号
		Map<String,Object> map=new HashMap<String,Object>();
		String archiveno = RequestParamUtil.getEncodeParam(request, "archiveno");
		if (StringUtils.isEmpty(archiveno)) {
			return ResultTO.newFailResultTO("档案号不能为空", null);
		}
		map.put("archiveno", archiveno);
		// 档案号
		String archivesType = RequestParamUtil.getEncodeParam(request, "archivesType");
		if (archivesType!=null) {
			//return ResultTO.newFailResultTO("档案号不能为空", null);
			map.put("archiveTypearchiveTypeId", archivesType);
		}
		// 盘点状态 0.在架 1.离架 2.错架
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("盘点状态不能为空", null);
		}
		// 存址
		String storeplace = RequestParamUtil.getEncodeParam(request, "storeplace");
		if (StringUtils.isEmpty(storeplace)) {
			return ResultTO.newFailResultTO("存址不能为空", null);
		}
		// 设备名
		String deviceName = RequestParamUtil.getEncodeParam(request, "deviceName");
		if (StringUtils.isEmpty(deviceName)) {
			return ResultTO.newFailResultTO("设备信息不能为空", null);
		}
		try {
			Map<String,Object> resmap=amsArchivesDao.findArchivesByNoAndType(map);
			RfidInty rfidInty = new RfidInty();
			rfidInty.setArchiveno(String.valueOf(resmap.get("archiveno")));
			rfidInty.setArchiveId(Integer.parseInt(resmap.get("archivesId")+""));
			rfidInty.setTittle(String.valueOf(resmap.get("title")));
			rfidInty.setType(type);
			rfidInty.setStoreplace(storeplace);
			rfidInty.setCtime(new Date());
			// 添加
			rfidIntyService.insert(rfidInty);
			if (type.equals("2")) {
				AlarmManage alarmManage = new AlarmManage();
				alarmManage.setAlarmType(6);
				alarmManage.setCategoryId(6);
				alarmManage.setDeviceName(deviceName);
				alarmManage.setCtime(new Date());
				alarmManage.setRemark("RFID盘点错架");
				alarmManage.setStatus("0");
				alarmManage.setLevel("1");
				alarmManage.setStoreId(1);
				alarmManage.setStroreAreaId(1);
//				alarmManageService.insert(alarmManage);
				alarmManage.setArchivesId(Integer.parseInt(resmap.get("archivesId").toString()));
				alarmManageService.insertAlarmManage(alarmManage);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("RFID盘点数据同步出同步出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		// 批量插入数据
		LOGGER.info("*************RFID盘点数据同步提交-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * RFID盘点数据同步
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "rfidsxjSync")
	@ResponseBody
	public ResultTO rfidrfidsxjSync(HttpServletRequest request) {
		LOGGER.info("*************RFID盘点数据同步-begin*****************");

		// 档案号
		String data = RequestParamUtil.getEncodeParam(request, "data");
		if (StringUtils.isEmpty(data)) {
			return ResultTO.newFailResultTO("数据不能为空", null);
		}
		JSONArray datas = JSONArray.fromObject(data);
		List<Map<String, Object>> datass = new ArrayList<Map<String, Object>>();
		datass = (List<Map<String, Object>>) JSONArray.toCollection(datas, Map.class);
		if (datass != null && datass.size() > 0) {
			if (datass.size() > 1000) {
				return ResultTO.newFailResultTO("单次数据不能大于1000条", null);
			}
		} else {
			return ResultTO.newFailResultTO("档案数据不能为空", null);
		}
		try {
			String result = amsArchivesService.confirmrfidsxjSync(datass);
			if (result != null) {
				return ResultTO.newFailResultTO(result, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("请求操作出现异常");
			return ResultTO.newFailResultTO("请求失败", null);
		}

		LOGGER.info("*************RFID盘点数据同步-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * RFID档案数据同步
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "rfidasjSync")
	@ResponseBody
	public ResultTO rfidrfidasjSync(HttpServletRequest request) {
		LOGGER.info("*************RFID档案数据同步-begin*****************");
		Map<String, Object> map = new HashMap<String, Object>();
		// 档案号
		String archiveno = RequestParamUtil.getEncodeParam(request, "archiveno");
		if (StringUtils.isEmpty(archiveno)) {
			return ResultTO.newFailResultTO("档案号不能为空", null);
		}
		// 档案类型
		String archiveTypeId = RequestParamUtil.getEncodeParam(request, "archiveTypeId");
		if (StringUtils.isEmpty(archiveTypeId)) {
			return ResultTO.newFailResultTO("档案类型不能为空", null);
		}
		map.put("archiveno", archiveno);
		map.put("archiveTypeId", archiveTypeId);
		try {
			Map<String, Object> result = amsArchivesService.findArchivesByNoAndType(map);
			if (result != null) {
				return ResultTO.newFailResultTO(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("请求操作出现异常");
			return ResultTO.newFailResultTO("请求失败", null);
		}

		LOGGER.info("*************RFID盘点数据同步-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * 获用户权限库房下档案出入列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "auditInSubmitList")
	@ResponseBody
	public ResultTO auditInSubmitList(HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
    	String type=RequestParamUtil.getEncodeParam(request, "type");
    	if(type!=null) {
    		map.put("type",type);
    	}
		String storeName = RequestParamUtil.getEncodeParam(request, "storeName");
		if(storeName!=null) {
			WmsStore store=new WmsStore();
			store.setName(storeName);
			store =wmsStoreDao.selectObjectByCode(store);
    		map.put("storeId",store.getStoreId());
    	}
    List<Map<String, Object>> count=amsArchivesAuditInDao.auditInSubmitList(map);
	return ResultTO.newSuccessResultTO("请求成功", count);
	}
	/**
	 * 获用户权限库房下档案出入列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "auditInSubmit")
	@ResponseBody
	public ResultTO auditSubmit(HttpServletRequest request) {
		LOGGER.info("*************修改档案状态-begin*****************");
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (StringUtils.isEmpty(type)) {
		    return ResultTO.newFailResultTO("状态不能为空", null);
		}
		String auditinId = RequestParamUtil.getEncodeParam(request, "auditinId");
		if (StringUtils.isEmpty(auditinId)) {
		    return ResultTO.newFailResultTO("申请id不能为空", null);
		}
		AmsArchivesAuditIn amsArchivesAuditIn = new AmsArchivesAuditIn();
		amsArchivesAuditIn.setAuditInId(Integer.parseInt(auditinId));
		amsArchivesAuditIn=amsArchivesAuditInDao.selectObject(amsArchivesAuditIn);
		amsArchivesAuditIn.setStatus("2");
		AmsArchives amsArchives = new AmsArchives();
		amsArchives.setArchivesId(amsArchivesAuditIn.getArchivesId());
		amsArchives=amsArchivesDao.selectObject(amsArchives);
		try {
				if(type.equals("0")) {
					amsArchives.setInflag(0);
					amsArchivesDao.update(amsArchives);
				}else{
					amsArchives.setInflag(1);
					amsArchivesDao.update(amsArchives);
				}
				amsArchivesAuditInDao.update(amsArchivesAuditIn);
		} catch (Exception e) {
		    e.printStackTrace();
		    LOGGER.error("修改档案状态出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
		    return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************修改档案状态-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	    }
	    
}
