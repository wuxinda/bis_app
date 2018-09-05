package com.bluemobi.controller.api.ams;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.apito.ams.AmsArchivesTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.ams.AmsArchivesAuditDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.service.ams.AmsArchivesService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 档案管理控制器3.0
 * 
 * @author Tony
 * 
 */
@Controller(value = "archivesManageAPIController")
@RequestMapping("api/amsManage")
public class ArchivesManageAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivesManageAPIController.class);
	@Autowired
	private AmsArchivesAuditService amsArchivesAuditService;
	@Autowired
	private AmsArchivesService amsArchivesService;
	@Autowired
	private AmsArchivesDao amsArchivesDao;
	@Autowired
	private AmsArchivesAuditDao amsArchivesAuditDao;
	@Autowired
	private AmsArchivesActlogService amsArchivesActlogService;
	@Autowired
	private WmsStoreDao WmsStoreDao;

	/**
	 * 档案申请获取档案列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchAmsList")
	@ResponseBody
	public ResultTO searchAmsList(HttpServletRequest request) {
		LOGGER.info("*************获取档案列表-begin*****************");
		// 组装查询条件
		Map<String, Object> reqMap = new HashMap<String, Object>();
		// 申请类型
		String type = RequestParamUtil.getEncodeParam(request, "auditType");
		// 档案盘点状态，0存档 ：只有出库状态的档案才能发起村档申请 1取档：只有在库状态的档案才能发起村档申请
		Integer checkStatus = null;
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("申请类型不能为空", null);
		} else if (type.equals("0")) {
			reqMap.put("cun", "cun");
			checkStatus = 7;// 出库
		} else if (type.equals("1")) {
			checkStatus = 5;// 在库
			reqMap.put("qu", "qu");
		} else {
			return ResultTO.newFailResultTO("申请类型不存在", null);
		}
		// 档案标题名称
		String title = RequestParamUtil.getEncodeParam(request, "title");
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
		List<AmsArchivesTO> amsArchivesTOList = new ArrayList<AmsArchivesTO>();
		try {
			// reqMap.put("checkStatus", checkStatus);
			reqMap.put("title", title);
			// 查询列表
			Page<Map<String, Object>> page = amsArchivesService.page(reqMap, Integer.parseInt(pageIndex),
					Integer.parseInt(pageSize));
			// 过滤档案返回字段
			for (Map<String, Object> amsArchivesMap : page.getData()) {
				AmsArchivesTO to = new AmsArchivesTO();
				to.setArchivesId((Integer) amsArchivesMap.get("archivesId"));
				to.setArchivesTypeId((String) amsArchivesMap.get("archivesTypeId"));
				to.setArchivesTypeName((String) amsArchivesMap.get("archivesTypeName"));
				to.setTitle((String) amsArchivesMap.get("title"));
				to.setStoreplace((String) amsArchivesMap.get("storeplace"));
				to.setCheckStatus((Integer) amsArchivesMap.get("checkStatus"));
				amsArchivesTOList.add(to);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取档案列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取档案列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", amsArchivesTOList);
	}

	/**
	 * 存/取档申请提交
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "auditSubmit")
	@ResponseBody
	public ResultTO auditSubmit(HttpServletRequest request) {
		LOGGER.info("*************申请提交-begin*****************");
		// 当前用户、创建人id
		String creator = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(creator)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 申请人id
		String userId = RequestParamUtil.getEncodeParam(request, "auditUserId");
		if (StringUtils.isEmpty(userId)) {
			// 申请人为空默认为当前用户
			userId = creator;
		}
		// 申请类型
		String type = RequestParamUtil.getEncodeParam(request, "auditType");
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("申请类型不能为空", null);
		}
		// 档案数组
		String amsIds = RequestParamUtil.getEncodeParam(request, "amsIds");
		if (StringUtils.isEmpty(amsIds)) {
			return ResultTO.newFailResultTO("档案不能为空", null);
		}
		String[] archivesIds = amsIds.split(",");
		// 更行档案参数
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 档案id集合
		parmMap.put("archivesIds", archivesIds);
		// 验证提交的档案是否可以被申请 在库状态才能进行取档申请 出库状态才能存档申请 0.待入库 1.入库审批中
		// 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还7.出库8.待出库 9.出库审批中10.任务操作中
		/*
		 * 3.1的逻辑 Integer result = null; if (type.equals("0")) { result =
		 * this.checkAmsStatus(archivesIds, "7");// 出库 parmMap.put("status", "1"); }
		 * else if (type.equals("1")) { result = this.checkAmsStatus(archivesIds,
		 * "5");// 在库 parmMap.put("status", "9"); } if (result == null || result !=
		 * archivesIds.length) { if (type.equals("1")) return
		 * ResultTO.newFailResultTO("档案状态非‘在库’，无法提交取档申请，请重新选择档案", null); if
		 * (type.equals("0")) return
		 * ResultTO.newFailResultTO("档案状态非‘出库’，无法提交存档申请，请重新选择档案", null); }
		 */
		// 获取档案列表
		Map<String, Object> parmMaps = new HashMap<String, Object>();
		List<AmsArchives> amsList = new ArrayList<AmsArchives>();
		for (int i = 0; i < archivesIds.length; i++) {
			parmMaps.put("archivesId", archivesIds[i]);
			amsList.add((AmsArchives) amsArchivesService.selectObject(parmMaps));
		}
		// 验证档案状态
		for (AmsArchives amsArchives : amsList) {
			if (type.equals("0")) {// 存档
				if (amsArchives.getNowNumber() == amsArchives.getTotalNumber()
						|| amsArchives.getNowNumber() > amsArchives.getTotalNumber()) {
					ResultTO.newFailResultTO("请求失败，档案号为:" + amsArchives.getArchiveno() + "的实际库存量已经等于或者大于总库存，请检查后再申请",
							null);

				}
			}
			if (type.equals("1")) {// 取档
				if (amsArchives.getNowNumber() == 0 || amsArchives.getNowNumber() < 0) {
					ResultTO.newFailResultTO("请求失败，档案号为:" + amsArchives.getArchiveno() + "实际库存等于0或者小于0不能发起取档", null);
				}
			}
		}
		try {
			// 批量添加申请
			amsArchivesAuditService.addAmsArchivesAudits(creator, userId, type, archivesIds, parmMap);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("批量添加申请出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		// 批量插入数据
		LOGGER.info("*************申请提交-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * 获取任务详情，按通道分组
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "taskInfo")

	@ResponseBody
	public ResultTO taskInfo(HttpServletRequest request) {
		LOGGER.info("*************获取任务详情，按通道分组-begin*****************");
		// 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}

		// 任务状态
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("任务状态不能为空", null);
		} // 返回结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try { // 执行查询
			result = amsArchivesAuditService.selectTaskInfo(userId, status);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取任务详情，按通道分组出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取任务详情，按通道分组-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}

	/**
	 * 智能引导模式
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "intelligentize")

	@ResponseBody
	public ResultTO intelligentize(HttpServletRequest request) {
		LOGGER.info("*************智能引导模式获取任务详情，按通道分组-begin*****************");
		// 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}

		// 任务状态
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("任务状态不能为空", null);
		}
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("status", status); // 返回结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		try {
			// 执行查询返回以通道为单位的列表
			result = amsArchivesAuditDao.selectTaskInfo(parmMap);
			// 返回上一步添加返回通道内申请单id
			for (Map<String, Object> list : result) {
				parmMap.put("storeId", list.get("storeId"));
				parmMap.put("areaId", list.get("areaId"));
				parmMap.put("galleryNum", list.get("galleryNum"));
				results = amsArchivesAuditDao.selectTaskExecute(parmMap);
				String auditIds = new String();
				for (int i = 0; i < results.size(); i++) {
					if (i == results.size() - 1) {
						auditIds += String.valueOf(results.get(i).get("auditId"));
					} else {
						auditIds += String.valueOf(results.get(i).get("auditId")) + ",";
					}

				}
				String ss = auditIds.toString();
				list.put("auditId", ss);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("智能引导模式获取任务详情，按通道分组出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************智能引导模式获取任务详情，按通道分组-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}

	/**
	 * 执行任务，获取通道内档案列表
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "taskExecute")

	@ResponseBody
	public ResultTO taskExecute(HttpServletRequest request) {
		LOGGER.info("*************执行任务-begin*****************"); // 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>(); // 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		parmMap.put("userId", userId); // 任务状态
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("任务状态不能为空", null);
		}
		parmMap.put("status", status); // 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		parmMap.put("storeId", storeId); // 库区id
		String areaId = RequestParamUtil.getEncodeParam(request, "areaId");
		if (StringUtils.isEmpty(areaId)) {
			return ResultTO.newFailResultTO("库区id不能为空", null);
		}
		parmMap.put("areaId", areaId); // 通道号
		String galleryNum = RequestParamUtil.getEncodeParam(request, "galleryNum");
		if (StringUtils.isEmpty(galleryNum)) {
			return ResultTO.newFailResultTO("通道号不能为空", null);
		}
		parmMap.put("galleryNum", galleryNum); // 第几页
		String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			return ResultTO.newFailResultTO("第几页不能为空", null);
		}
		parmMap.put("pageIndex", pageIndex); // 每页显示条数
		String pageSize = RequestParamUtil.getEncodeParam(request, "pageSize");
		if (StringUtils.isEmpty(pageSize)) {
			return ResultTO.newFailResultTO("每页条数不能为空", null);
		}
		parmMap.put("pageSize", pageSize); // 返回结果集
		Map<String, Object> result = new HashMap<String, Object>();
		try { // 执行查询
			result = amsArchivesAuditService.selectTaskExecute(parmMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("执行任务出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************执行任务-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}

	/**
	 * 按通道提交任务 注意：未做事务处理，如需使用请整理代码到service层进行事物处理
	 * 
	 * @param request
	 * @return
	 */

	@SuppressWarnings("unchecked")

	@RequestMapping(value = "taskSubmit")

	@ResponseBody
	public ResultTO taskSubmit(HttpServletRequest request) {
		LOGGER.info("*************提交任务-begin*****************"); // 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>(); // 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		parmMap.put("userId", userId); // 任务状态
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("任务状态不能为空", null);
		}
		parmMap.put("status", status); // 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		parmMap.put("storeId", storeId); // 库区id
		String areaId = RequestParamUtil.getEncodeParam(request, "areaId");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("库区id不能为空", null);
		}
		parmMap.put("areaId", areaId); // 通道号
		String galleryNum = RequestParamUtil.getEncodeParam(request, "galleryNum");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("通道号不能为空", null);
		}
		parmMap.put("galleryNum", galleryNum); // 返回结果集
		Map<String, Object> result = new HashMap<String, Object>();
		try { // 执行查询通道内返回id集合申请id 和档案id
			result = amsArchivesAuditService.selectTaskSubmit(parmMap); //
			// 验证申请单状态是否全是待执行任务，若不是则不提交任务
			String[] auditIds = new String[((List<Object>) result.get("auditIds")).size()];
			if (auditIds.length < 1)
				return ResultTO.newFailResultTO("该通道内无任务，无需执行", null);
			for (int i = 0; i < ((List<Object>) result.get("auditIds")).size(); i++) {
				auditIds[i] = String.valueOf(((List<Object>) result.get("auditIds")).get(i));
			} // 审核状态
				// 0.待审批 1.审核通过 2.审核拒绝 3.已完成
			if (!this.checkAuditStatus(auditIds, "1").equals(((List<Object>) result.get("auditIds")).size())) {
				return ResultTO.newFailResultTO("申请单状态不支持当前任务", null);
			} //
				// 验证档案实体状态是不是任务执行中，若不是则不提交任务
			String[] archivesIdsIn = new String[((List<Object>) result.get("archivesIdsIn")).size()];
			for (int i = 0; i < ((List<Object>) result.get("archivesIdsIn")).size(); i++) {
				archivesIdsIn[i] = String.valueOf(((List<Object>) result.get("archivesIdsIn")).get(i));
			} // // 盘点状态 0.待入库 1.入库审批中
				// 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还7.出库8.待出库 // 9.出库审批中10.任务操作中
			if (!this.checkAmsStatus(archivesIdsIn, "10").equals(((List<Object>) result.get("archivesIdsIn")).size())) {
				return ResultTO.newFailResultTO("档案状态不支持当前任务", null);
			}
			// 验证档案实体状态是不是任务执行中，若不是则不提交任务
			String[] archivesIdsOut = new String[((List<Object>) result.get("archivesIdsOut")).size()];
			for (int i = 0; i < ((List<Object>) result.get("archivesIdsOut")).size(); i++) {
				archivesIdsOut[i] = String.valueOf(((List<Object>) result.get("archivesIdsOut")).get(i));
			} // // 盘点状态 0.待入库 1.入库审批中2.上架审批中3.借阅审批中4.待上架5.在库6.待归还7.出库8.待出库 // 9.出库审批中10.任务操作中
			if (!this.checkAmsStatus(archivesIdsOut, "10")
					.equals(((List<Object>) result.get("archivesIdsOut")).size())) {
				return ResultTO.newFailResultTO("档案状态不支持当前任务", null);
			} // 执行任务，更新申请表数据
			amsArchivesAuditService.confirmAmsArchivesAudit(auditIds, userId, "3"); // 更新档案表数据
			Map<String, Object> parmMapin = new HashMap<String, Object>();
			parmMapin.put("archivesIds", archivesIdsIn);
			parmMapin.put("status", "5");
			// 入库申请成功任务执行完后将档案状态变更为入库，在库状态变更成在库0:在库 1.出库
			if (archivesIdsIn.length > 0)
				amsArchivesDao.updateAmsArchivesStatus(parmMapin);
			amsArchivesActlogService.insertArchivesActlogs(archivesIdsIn, "0", userId, storeId, areaId);// 操作类型 0.入库
																										// 1.借阅 2.上架
																										// 3.归还4.出库
			Map<String, Object> parmMapout = new HashMap<String, Object>();
			parmMapout.put("archivesIds", archivesIdsOut);
			parmMapout.put("status", "7");// 出库申请任务执行完后将档案状态变更为出库，在库状态变更为出库
			parmMapout.put("inflag", "1");
			if (archivesIdsOut.length > 0)
				amsArchivesDao.updateAmsArchivesStatus(parmMapout);
			amsArchivesActlogService.insertArchivesActlogs(archivesIdsOut, "4", userId, storeId, areaId);// 操作类型 0.入库
																											// 1.借阅 2.上架
																											// 3.归还4.出库
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("提交任务出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************提交任务-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * 取消任务
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "taskCancel")

	@ResponseBody
	public ResultTO taskCancel(HttpServletRequest request) {
		LOGGER.info("*************取消任务-begin*****************"); // 任务用户Id
		String auditId = RequestParamUtil.getEncodeParam(request, "auditId");
		if (StringUtils.isEmpty(auditId)) {
			return ResultTO.newFailResultTO("任务Id不能为空", null);
		} // 检查状态是否支持取消
		if (!(this.checkAuditStatus(auditId.split(","), "0") == auditId.split(",").length)) {
			return ResultTO.newFailResultTO("任务状态不支持取消", null);
		}
		try {
			amsArchivesAuditService.confirmTaskCancel(auditId.split(","));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("取消任务出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************取消任务-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * 返回上一步
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "goHistory")

	@ResponseBody
	public ResultTO goHistory(HttpServletRequest request) {
		LOGGER.info("*************返回上一步-begin*****************"); // 任务用户Id
		String auditId = RequestParamUtil.getEncodeParam(request, "auditId");
		if (StringUtils.isEmpty(auditId)) {
			return ResultTO.newFailResultTO("任务Id不能为空", null);
		} // 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("AuditIds", auditId.split(",")); // 查询得审核档案集合
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try { // 检查档案状态是否被他人利用 存档 档案状态应该是在库 取档 档案状态应该是出库 list =
			amsArchivesAuditDao.selectAmsIdByAuditId(parmMap);
			String[] archivesIdsin = new String[list.size()];
			String[] archivesIdsout = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> obj = list.get(i);
				if (obj.get("status").equals("3") && obj.get("type").equals("0")
						&& String.valueOf(obj.get("checkStatus")).equals("5")) {
					archivesIdsin[i] = String.valueOf(obj.get("archivesId"));
					continue;
				} else if (obj.get("status").equals("3") && obj.get("type").equals("1")
						&& String.valueOf(obj.get("checkStatus")).equals("7")) {
					archivesIdsout[i] = String.valueOf(obj.get("archivesId"));
					continue;
				} else {
					if (!obj.get("status").equals("3")) {
						return ResultTO.newFailResultTO("申请状态不支持返回", null);
					} else {
						return ResultTO.newFailResultTO("档案已被他人申请不支持返回", null);
					}
				}
			}
			amsArchivesAuditService.confirmGoHistory(auditId.split(","), archivesIdsin, archivesIdsout);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("取消任务出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************返回上一步-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * 检查档案状态
	 * 
	 * @param archivesIds档案id集合
	 * @param status档案状态
	 * @return 给定档案状态数量
	 */
	public Integer checkAmsStatus(String[] archivesIds, String status) {
		LOGGER.info("*************验证开始-begin*****************");
		if (!(archivesIds.length > 0))
			return 0;
		Integer result = -1;
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("archivesIds", archivesIds);
		reqMap.put("checkStatus", status);
		try {
			// 查询操作
			result = amsArchivesDao.selectAmsStatusCount(reqMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询给定档案状态数量出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage() });
		}
		LOGGER.info("*************验证结束-end*****************");
		return result;
	}

	/**
	 * 检查申请单状态
	 * 
	 * @param AuditIds申请单id集合
	 * @param status申请单状态
	 * @return
	 */
	public Integer checkAuditStatus(String[] AuditIds, String status) {
		LOGGER.info("*************验证开始-begin*****************");
		if (!(AuditIds.length > 0))
			return 0;
		Integer result = -1;
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("AuditIds", AuditIds);
		reqMap.put("status", status);
		try { // 查询操作 result =
			amsArchivesAuditDao.selectAuditStatusCount(reqMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询给定申请单状态数量出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage() });
		}
		LOGGER.info("*************验证结束-end*****************");
		return result;
	}
	/**
	 * 获取档案检索列表
	 * 
	 * @param
	 * @param
	 * @return
	 */
    @RequestMapping(value = "getArchives")
    @ResponseBody
	public ResultTO getArchives(HttpServletRequest request) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String storeId=RequestParamUtil.getEncodeParam(request, "storeId");
    	if(storeId!=null) {
    		Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("storeId",storeId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
    	}
    	String str=RequestParamUtil.getEncodeParam(request, "str");
    	if(str!=null) {
    		map.put("str","%"+str+"%" );
    	}
    	List<Map<String,Object>> map1=amsArchivesDao.getArchives(map);
		return ResultTO.newSuccessResultTO(map1);
    }
    /**
	 * 获取档案统计
	 * 
	 * @param
	 * @param
	 * @return
	 */
    @RequestMapping(value = "getArchivesCount")
    @ResponseBody
	public ResultTO getArchivesCount(HttpServletRequest request) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String storeId=RequestParamUtil.getEncodeParam(request, "storeId");
    	if(storeId!=null) {
    		Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("storeId",storeId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
    	}
    	List<Map<String,Object>> map1=amsArchivesDao.getArchivesCount(map);
		return ResultTO.newSuccessResultTO(map1);
    }
    /**
   	 * 获取在库状态统计
   	 * 
   	 * @param
   	 * @param
   	 * @return
   	 */
       @RequestMapping(value = "getInflagCount")
       @ResponseBody
   	public ResultTO getInflagCount(HttpServletRequest request) {
       	Map<String,Object> map=new HashMap<String,Object>();
       	String storeId=RequestParamUtil.getEncodeParam(request, "storeId");
       	if(storeId!=null) {
       		Map<String,Object> map1=new HashMap<String,Object>();
       		map1.put("storeId",storeId );
       		Map<String,Object> parmMap=WmsStoreDao.selectStoreIdByCode(map1);
       		if(parmMap!=null) {
       			map.put("storeId",parmMap.get("storeId") );
       		}
       	}
       	List<Map<String,Object>> map1=amsArchivesDao.getInflagCount(map);
   		return ResultTO.newSuccessResultTO(map1);
       }
}
