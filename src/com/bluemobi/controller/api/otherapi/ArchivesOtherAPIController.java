package com.bluemobi.controller.api.otherapi;

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

import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.service.ams.AmsArchivesService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.JsonSendByPostUtil;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

import net.sf.json.JSONArray;

/**
 * 档案对外接口控制类
 * 
 * @author Tony黄
 * 
 */
@Controller(value = "ArchivesOtherAPIController")
@RequestMapping("api/otherapi")
public class ArchivesOtherAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivesOtherAPIController.class);
	@Autowired
	private AmsArchivesAuditService amsArchivesAuditService;
	@Autowired
	private AmsArchivesService amsArchivesService;
	/**
	 * 智能调档 applyNo applyUser auditType archiveno clientId
	 * 
	 * @param request
	 * @return
	 */

	@RequestMapping(value = "auditSubmit")
	@ResponseBody
	public ResultTO auditSubmit(HttpServletRequest request) {
		LOGGER.info("*************申请提交-begin*****************");
		// 申请单号
		String applyNo = RequestParamUtil.getEncodeParam(request, "applyNo");
		if (StringUtils.isEmpty(applyNo)) {
			return ResultTO.newFailResultTO("申请单号不能为空", null);
		}
		// 申请人
		String applyUser = RequestParamUtil.getEncodeParam(request, "applyUser");
		if (StringUtils.isEmpty(applyUser)) {
			return ResultTO.newFailResultTO("申请人不能为空", null);
		}
		// 调卷类型
		String type = RequestParamUtil.getEncodeParam(request, "auditType");
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("调卷类型不能为空", null);
		}
		// 档案
		String archivenos = RequestParamUtil.getEncodeParam(request, "archiveno");
		if (StringUtils.isEmpty(archivenos)) {
			return ResultTO.newFailResultTO("档案号不能为空", null);
		}
		// 客户端Id
		String clientId = RequestParamUtil.getEncodeParam(request, "clientId");
		if (StringUtils.isEmpty(clientId)) {
			return ResultTO.newFailResultTO("客户端Id不能为空", null);
		}
		// 档案类型
		String ArchiveCode = RequestParamUtil.getEncodeParam(request, "ArchiveCode");
		if (StringUtils.isEmpty(ArchiveCode)) {
			return ResultTO.newFailResultTO("档案类型不能为空", null);
		}
		// 处理档案编号集合
		String[] archiveNos = archivenos.split(",");
		// 处理档案类型集合
		String[] ArchiveCodes = ArchiveCode.split(",");
		if (archiveNos.length != ArchiveCodes.length) {
			return ResultTO.newFailResultTO("请求失败,档案数据发送错误", null);
		}
		// 档案参数
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 添加参数
		parmMap.put("archiveNos", archiveNos);
		parmMap.put("applyNo", applyNo);
		parmMap.put("type", type);
		parmMap.put("applyUser", applyUser);
		parmMap.put("clientId", clientId);
		parmMap.put("ArchiveTypeIds", ArchiveCodes);
		try {
			// 批量添加申请
			String result = amsArchivesAuditService.confirmaddAmsApplys(parmMap);
			if (result != null) {
				return ResultTO.newFailResultTO(result, null);
			}
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
	 * 档案信息同步
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "archiveSyncold")
	@ResponseBody
	public ResultTO archiveSyncold(HttpServletRequest request) {
		LOGGER.info("*************档案同步-begin*****************");

		// 档案号
		String archiveno = RequestParamUtil.getEncodeParam(request, "archiveno");
		if (StringUtils.isEmpty(archiveno)) {
			return ResultTO.newFailResultTO("档案号不能为空", null);
		}
		// 档案类型
		String archiveType = RequestParamUtil.getEncodeParam(request, "archivesType");
		if (StringUtils.isEmpty(archiveType)) {
			return ResultTO.newFailResultTO("档案类型不能为空", null);
		}
		// 立卷方式
		String filingmethod = RequestParamUtil.getEncodeParam(request, "filingmethod");
		if (StringUtils.isEmpty(filingmethod)) {
			return ResultTO.newFailResultTO("立卷方式不能为空", null);
		}
		// 文件正题名
		String title = RequestParamUtil.getEncodeParam(request, "title");
		if (StringUtils.isEmpty(title)) {
			return ResultTO.newFailResultTO("文件整体名不能为空", null);
		}
		// 案卷题名
		String paratitle = RequestParamUtil.getEncodeParam(request, "paratitle");
		if (StringUtils.isEmpty(paratitle)) {
			return ResultTO.newFailResultTO("案卷题名不能为空", null);
		}
		// 保管期限
		String keepyear = RequestParamUtil.getEncodeParam(request, "keepyear");
		if (StringUtils.isEmpty(keepyear)) {
			return ResultTO.newFailResultTO("保管期限不能为空", null);
		}
		// 密级
		String security = RequestParamUtil.getEncodeParam(request, "security");

		// 主题词
		String subject = RequestParamUtil.getEncodeParam(request, "subject");
		if (StringUtils.isEmpty(subject)) {
			return ResultTO.newFailResultTO("主题词不能为空", null);
		}
		// 关键词
		String keyword = RequestParamUtil.getEncodeParam(request, "keyword");
		if (StringUtils.isEmpty(keyword)) {
			return ResultTO.newFailResultTO("关键词不能为空", null);
		}
		// 客户端ID
		String clientId = RequestParamUtil.getEncodeParam(request, "clientId");
		if (StringUtils.isEmpty(clientId)) {
			return ResultTO.newFailResultTO("客户端ID不能为空", null);
		}
		// 同步类型
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("同步类型不能为空", null);
		}
		// 总库存
		String totalNum = RequestParamUtil.getEncodeParam(request, "totalNum");
		if (StringUtils.isEmpty(totalNum)) {
			return ResultTO.newFailResultTO("总库存不能为空", null);
		}
		// 现有库存
		String nowlNum = RequestParamUtil.getEncodeParam(request, "nowlNum");
		if (StringUtils.isEmpty(nowlNum)) {
			nowlNum = totalNum;
			// return ResultTO.newFailResultTO("同步类型不能为空", null);
		}

		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 添加参数
		paraMap.put("archiveno", archiveno);
		paraMap.put("archivesTypeId", archiveType);
		paraMap.put("filingMethod", filingmethod);
		paraMap.put("title", title);
		paraMap.put("paratitle", paratitle);
		paraMap.put("keepyear", keepyear);
		paraMap.put("security", security);
		paraMap.put("subject", subject);
		paraMap.put("keyword", keyword);
		paraMap.put("ClientID", clientId);
		paraMap.put("type", type);
		paraMap.put("totalNumber", totalNum);
		paraMap.put("nowNumber", nowlNum);
		paraMap.put("creator", clientId);
		paraMap.put("ctime", new Date());

		try {
			String result = amsArchivesService.confirmarchivesSync(null);
			if (result != null) {
				return ResultTO.newFailResultTO(result, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("请求操作出现异常！");
			return ResultTO.newFailResultTO("请求失败！", null);
		}

		LOGGER.info("*************档案同步-end*****************");
		return ResultTO.newSuccessResultTO("请求成功！", null);
	}

	/**
	 * 数据字典同步控制器
	 * 
	 * @Date:2017年5月12日
	 * @author:Tony
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "dictSync")
	@ResponseBody
	public ResultTO dictSync(HttpServletRequest request) {
		LOGGER.info("*************数据字典同步-begin*****************");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 字典编号
		String dictNo = RequestParamUtil.getEncodeParam(request, "dictNo");
		if (StringUtils.isEmpty(dictNo)) {
			return ResultTO.newFailResultTO("字典编号不能为空", null);
		}
		paraMap.put("dictNo", dictNo);
		// 数据标识
		String dataValue = RequestParamUtil.getEncodeParam(request, "DataValue");
		if (StringUtils.isEmpty(dataValue)) {
			return ResultTO.newFailResultTO("数据标识不能为空", null);
		}
		paraMap.put("dataValue", dataValue);
		// 数据名称
		String dataName = RequestParamUtil.getEncodeParam(request, "DataName");
		if (StringUtils.isEmpty(dataName)) {
			return ResultTO.newFailResultTO("数据名称不能为空", null);
		}
		paraMap.put("dataName", dataName);
		// 同步类型
		String type = RequestParamUtil.getEncodeParam(request, "type");
		if (StringUtils.isEmpty(type)) {
			return ResultTO.newFailResultTO("同步类型不能为空", null);
		}
		paraMap.put("type", type);
		String POST_URL = "http://192.168.1.163:8080/bis_app/api/otherapi/dictSync"; 
		JsonSendByPostUtil.httpURLConnectionPOST(POST_URL, paraMap);
		paraMap.put("userId", this.getUserId());

		try {
			String result = amsArchivesService.confirmdictSync(paraMap);
			if (result != null) {
				return ResultTO.newFailResultTO(result, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("请求操作出现异常！");
			return ResultTO.newFailResultTO("请求失败", null);
		}

		LOGGER.info("*************数据字典同步-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}

	/**
	 * 档案信息同步（批量）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "archiveSync")
	@ResponseBody
	public ResultTO archiveSync(HttpServletRequest request) {
		LOGGER.info("*************档案批量同步-begin*****************");
		
		// 档案号
		String data = RequestParamUtil.getEncodeParam(request, "Data");
		if (StringUtils.isEmpty(data)) {
			return ResultTO.newFailResultTO("数据不能为空", null);
		}
		JSONArray datas = JSONArray.fromObject(data);
		List<Map<String, Object>> datass = new ArrayList<Map<String, Object>>();
		datass = (List<Map<String, Object>>) JSONArray.toCollection(datas,Map.class);
		if (datass != null && datass.size() > 0) {
			if (datass.size() > 1000) {
				return ResultTO.newFailResultTO("单次数据不能大于1000条", null);
			}
		} else {
			return ResultTO.newFailResultTO("档案数据不能为空", null);
		}
		try {
			String result = amsArchivesService.confirmarchivesSync(datass);
			if (result != null) {
				return ResultTO.newFailResultTO(result, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("请求操作出现异常");
			return ResultTO.newFailResultTO("请求失败", null);
		}
		LOGGER.info("*************档案批量同步-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}
}
