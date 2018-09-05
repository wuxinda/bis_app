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

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 档案审批控制器
 * 
 * @author Riven
 * 
 */
@Controller(value = "archivesAuditAPIController")
@RequestMapping("api/audit")
public class ArchivesAuditAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArchivesAuditAPIController.class);

    @Autowired
    private AmsArchivesAuditService amsArchivesAuditService;
    @Autowired
    private AmsArchivesDao amsArchivesDao;

    /**
     * 获取档案审批列表领导端获取审批列表
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "getList")
    @ResponseBody
    public ResultTO getStoreList(HttpServletRequest request) {
	LOGGER.info("*************获取档案审批列表-begin*****************");
	// 参数格式化
	Map<String, Object> parmMap = new HashMap<String, Object>();
	// 审批状态
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("审批状态不能为空", null);
	}
	parmMap.put("status", status.split(","));
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
	List<Map<String, Object>> auditList = null;
	try {
	    // 查询档案审核列表
	    auditList = amsArchivesAuditService.getAuditUserGroupList(parmMap);

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案审批列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案审批列表-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", auditList);
    }

    /**
     * 获取用户申请审批明细
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "getDetail")
    @ResponseBody
    public ResultTO getDetail(HttpServletRequest request) {
	LOGGER.info("*************获取用户申请审批明细-begin*****************");

	// 用户id
	String userId = RequestParamUtil.getEncodeParam(request, "userId");
	if (StringUtils.isEmpty(userId)) {
	    return ResultTO.newFailResultTO("用户id不能为空", null);
	}
	// 审批装状态
	String status = RequestParamUtil.getEncodeParam(request, "status");
	if (StringUtils.isEmpty(status)) {
	    return ResultTO.newFailResultTO("审批状态不能为空", null);
	}
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
	List<Map<String, Object>> deetailList = new ArrayList<Map<String, Object>>();
	try {
	    for (String s : status.split(",")) {
		// 组装查询条件
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("userId", userId);
		// 审核状态 0.待审批 1.审核通过 2.审核拒绝
		reqMap.put("status", s);
		// 查询档案审核列表
		Page<Map<String, Object>> page = amsArchivesAuditService.page(reqMap, Integer.parseInt(pageIndex), Integer.parseInt(pageSize));
		for (Map<String, Object> list : page.getData()) {
		    deetailList.add(list);
		}
	    }

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案审批列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取用户申请审批明细-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", deetailList);
    }

    /**
     * 批量操作审批事项    领导端审批
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "handle")
    @ResponseBody
    public ResultTO handle(HttpServletRequest request) {
	LOGGER.info("*************批量操作审批事项-begin*****************");
	// 操作人id
	String handUserId = RequestParamUtil.getEncodeParam(request, "handUserId");
	if (StringUtils.isEmpty(handUserId)) {
	    return ResultTO.newFailResultTO("操作人id不能为空", null);
	}
	// 申请单id
	String auditIds = RequestParamUtil.getEncodeParam(request, "auditId");
	if (StringUtils.isEmpty(auditIds)) {
	    return ResultTO.newFailResultTO("申请id不能为空", null);
	}
	// 操作类型 1.审批通过 2.审批拒绝
	String handType = RequestParamUtil.getEncodeParam(request, "handType");
	if (StringUtils.isEmpty(handType)) {
	    return ResultTO.newFailResultTO("操作类型不能为空", null);
	} else if (!"1".equals(handType) && !"2".equals(handType)) {
	    return ResultTO.newFailResultTO("非法的操作类型", null);
	}
	try {
	    // 批量操作
	    amsArchivesAuditService.confirmHandAmsArchivesAudit(auditIds.split(","), handUserId, handType);
	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("批量操作审批事项出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************批量操作审批事项-end*****************");
	return ResultTO.newSuccessResultTO("处理成功", null);
    }

    /**
     * 获取档案详情
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "getAmsDetail")
    @ResponseBody
    public ResultTO getAmsDetail(HttpServletRequest request) {
	LOGGER.info("*************获取档案详情-begin*****************");

	// 档案id
	String archivesId = RequestParamUtil.getEncodeParam(request, "archivesId");
	if (StringUtils.isEmpty(archivesId)) {
	    return ResultTO.newFailResultTO("档案id不能为空", null);
	}
	AmsArchives amsArchives = null;
	try {
	    // 组装查询条件
	    Map<String, Object> reqMap = new HashMap<String, Object>();
	    reqMap.put("archivesId", archivesId);
	    // 查询档案审核列表
	    amsArchives = amsArchivesDao.selectObject(reqMap);

	} catch (Exception e) {
	    e.printStackTrace();
	    LOGGER.error("获取档案详情出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
	    return ResultTO.newFailResultTO("请求失败！", null);
	}
	LOGGER.info("*************获取档案详情-end*****************");
	return ResultTO.newSuccessResultTO("请求成功", amsArchives);
    }
}
