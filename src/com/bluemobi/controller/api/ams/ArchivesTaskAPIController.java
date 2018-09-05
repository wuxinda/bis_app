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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.ams.AmsArchivesAuditDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.service.wms.WmsUserService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 档案管理控制器3.1
 * 
 * @author Tony
 * 
 */
@Controller(value = "ArchivesTaskAPIController")
@RequestMapping("api/amsTask")
public class ArchivesTaskAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivesTaskAPIController.class);
	@Autowired
	private AmsArchivesAuditService amsArchivesAuditService;
	@Autowired
	private AmsArchivesDao amsArchivesDao;
	@Autowired
	private AmsArchivesAuditDao amsArchivesAuditDao;
	@Autowired
	private WmsUserService wmsUserService;

	/**
	 * 申请单列表（未完成的）3.1
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTaskList")
	@ResponseBody
	public ResultTO getTaskList(HttpServletRequest request) {
		LOGGER.info("*************获取任务列表-begin*****************");
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 当前用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		parmMap.put("userId", userId);
		// 审批状态
		String status = RequestParamUtil.getEncodeParam(request, "status");
		if (StringUtils.isEmpty(status)) {
			return ResultTO.newFailResultTO("状态不能为空", null);
		}
		parmMap.put("status", status);
		// 第几页
		String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			return ResultTO.newFailResultTO("第几页不能为空", null);
		}
		parmMap.put("pageIndex", pageIndex);
		// 每页条数
		String pageSize = RequestParamUtil.getEncodeParam(request, "pageSize");
		if (StringUtils.isEmpty(pageSize)) {
			return ResultTO.newFailResultTO("每页条数不能为空", null);
		}
		parmMap.put("pageSize", pageSize);
		// 返回结果级
		List<Map<String, Object>> auditList = new ArrayList<Map<String, Object>>();
		try {
			//获取用户所管理的库房
			parmMap.put("storeIds", wmsUserService.selectUserStoreIds(userId));
			// 查询档案审核列表
			auditList = amsArchivesAuditService.getTaskList(parmMap);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取档案申请列表出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取档案申请列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", auditList);
	}

	/**
	 * 获取申请单档案列表3.1
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getTaskAmsList")
	@ResponseBody
	public ResultTO getTaskAmsList(HttpServletRequest request) {
		LOGGER.info("*************获取档案列表-begin*****************");
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 当前用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		parmMap.put("userId", userId);
		// 申请单id
		String applyNo = RequestParamUtil.getEncodeParam(request, "applyNo");
		if (StringUtils.isEmpty(applyNo)) {
			return ResultTO.newFailResultTO("申请单不能为空", null);
		}
		parmMap.put("applyNo", applyNo);
		String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			return ResultTO.newFailResultTO("第几页不能为空", null);
		}
		parmMap.put("pageIndex", pageIndex);
		// 每页条数
		String pageSize = RequestParamUtil.getEncodeParam(request, "pageSize");
		if (StringUtils.isEmpty(pageSize)) {
			return ResultTO.newFailResultTO("每页条数不能为空", null);
		}
		parmMap.put("pageSize", pageSize);
		// 返回结果级
		List<Map<String, Object>> taskAmsList = new ArrayList<Map<String, Object>>();
		try {
			//获取用户所管理的库房
			parmMap.put("storeIds", wmsUserService.selectUserStoreIds(userId));
			taskAmsList = amsArchivesAuditService.getApplyAmsList(parmMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取档案列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取档案列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", taskAmsList);
	}

	/**
	 * 获取任务详情，按通道分组3.1
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getGalleryList")
	@ResponseBody
	public ResultTO getGalleryList(HttpServletRequest request) {
		LOGGER.info("*************获取任务详情，按通道分组-begin*****************");
		// 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 申请单号
		String applyNo = RequestParamUtil.getEncodeParam(request, "applyNo");
		if (applyNo.equals("") || applyNo.equals("null")) {
			applyNo = null;
		}
		// 返回结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			// 执行查询
			result = amsArchivesAuditService.getGalleryList(userId, "1", applyNo);
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
	 * 智能引导模式3.1
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
		// 申请单号
		String applyNo = RequestParamUtil.getEncodeParam(request, "applyNo");
		if (applyNo.equals("") || applyNo.equals("null")) {
			applyNo = null;
		}
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("status", 1);// 待执行
		parmMap.put("applyNo", applyNo);
		// 返回结果集
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			//获取用户所管理的库房
			parmMap.put("storeIds", wmsUserService.selectUserStoreIds(userId));
			// 执行查询返回以通道为单位的列表
			list = amsArchivesAuditDao.selectTaskGallaryList(parmMap);
			result.put("galleryList", list);
			Integer count = amsArchivesAuditDao.selectTaskAmsNums(parmMap);
			result.put("amsSum", count);
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
	 * 执行任务，获取通道内档案列表3.1
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getGalleryAmsList")
	@ResponseBody
	public ResultTO getGalleryAmsList(HttpServletRequest request) {
		LOGGER.info("*************执行任务-begin*****************");
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		parmMap.put("userId", userId);
		// 申请单号,可为空
		String applyNo = RequestParamUtil.getEncodeParam(request, "applyNo");
		if (applyNo.equals("") || applyNo.equals("null")) {
			applyNo = null;
		}
		parmMap.put("applyNo", applyNo);
		// 任务类型,可为空
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (type.equals("") || type.equals("null")) {
			type = null;
		}
		parmMap.put("type", type);
		// 任务状态
		parmMap.put("status", 1);
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		parmMap.put("storeId", storeId);
		// 库区id
		String areaId = RequestParamUtil.getEncodeParam(request, "areaId");
		if (StringUtils.isEmpty(areaId)) {
			return ResultTO.newFailResultTO("库区id不能为空", null);
		}
		parmMap.put("areaId", areaId);
		// 通道号
		String galleryNum = RequestParamUtil.getEncodeParam(request, "galleryNum");
		if (StringUtils.isEmpty(galleryNum)) {
			return ResultTO.newFailResultTO("通道号不能为空", null);
		}
		parmMap.put("galleryNum", galleryNum);
		// 第几页
		String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
		if (StringUtils.isEmpty(pageIndex)) {
			return ResultTO.newFailResultTO("第几页不能为空", null);
		}
		parmMap.put("pageIndex", pageIndex);
		// 每页显示条数
		String pageSize = RequestParamUtil.getEncodeParam(request, "pageSize");
		if (StringUtils.isEmpty(pageSize)) {
			return ResultTO.newFailResultTO("每页条数不能为空", null);
		}
		parmMap.put("pageSize", pageSize);
		// 返回结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		try {
			parmMap.put("offset", (Integer.parseInt(String.valueOf(parmMap.get("pageIndex"))) - 1)
					* Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 第几页（起始量）
			parmMap.put("rows", Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 每页条数（偏移量）
			//获取用户所管理的库房
			parmMap.put("storeIds", wmsUserService.selectUserStoreIds(userId));
			// 执行查询
			result = amsArchivesAuditDao.getGalleryAmsList(parmMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("执行任务出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************执行任务-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);
	}

	/**
	 * 提交任务3.1
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "taskSubmit")
	@ResponseBody
	public ResultTO taskSubmit(HttpServletRequest request) {
		LOGGER.info("*************提交任务-begin*****************");
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 申请人userid
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		parmMap.put("userId", userId);
		// 申请id
		String auditIds = RequestParamUtil.getEncodeParam(request, "auditId");
		if (StringUtils.isEmpty(auditIds)) {
			return ResultTO.newFailResultTO("申请id不能为空", null);
		}
		parmMap.put("auditIds", auditIds.split(","));

		try {
			// 检查申请单状态和档案状态是否可以操作任务
			String flag = amsArchivesAuditService.checkstatus(parmMap);
			if (flag != null) {
				return ResultTO.newFailResultTO(flag, null);
			}
			// 变更申请单状态
			String res = amsArchivesAuditService.confirmCommitTaskAmsArchivesAudit(auditIds.split(","), userId, "3",
					parmMap);
			if (res.equals("1")) {
				return ResultTO.newFailResultTO("请求失败！请求数字档案系统异常", null);
			}

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
		LOGGER.info("*************取消任务-begin*****************");
		// 任务用户Id
		String auditId = RequestParamUtil.getEncodeParam(request, "auditId");
		if (StringUtils.isEmpty(auditId)) {
			return ResultTO.newFailResultTO("任务Id不能为空", null);
		}
		// 检查状态是否支持取消
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
		LOGGER.info("*************返回上一步-begin*****************");
		// 任务用户Id
		String auditId = RequestParamUtil.getEncodeParam(request, "auditId");
		if (StringUtils.isEmpty(auditId)) {
			return ResultTO.newFailResultTO("任务Id不能为空", null);
		}
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("AuditIds", auditId.split(","));
		// 查询得审核档案集合
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		try {
			// 检查档案状态是否被他人利用 存档 档案状态应该是在库 取档 档案状态应该是出库
			list = amsArchivesAuditDao.selectAmsIdByAuditId(parmMap);
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
		try {
			// 查询操作
			result = amsArchivesAuditDao.selectAuditStatusCount(reqMap);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("查询给定申请单状态数量出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage() });
		}
		LOGGER.info("*************验证结束-end*****************");
		return result;
	}
}
