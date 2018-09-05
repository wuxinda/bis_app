package com.bluemobi.controller.api.index;
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

import com.bluemobi.apito.ams.AmsArchivesTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.service.ams.AmsArchivesService;
import com.bluemobi.service.message.MessageReceiveService;
import com.bluemobi.service.satatis.WmsSatatisService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
/**
 * 首页控制器
 * @author Riven
 *
 */
@Controller(value = "indexAPIController")
@RequestMapping("api/index")
public class IndexAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(IndexAPIController.class);
    
	@Autowired
	private AmsArchivesService amsArchivesService;
	@Autowired
	private WmsStoreService wmsStoreService;
	@Autowired
	private WmsSatatisService wmsSatatisService;
	@Autowired
	private MessageReceiveService messageReceiveService;
	@Autowired
	private AmsArchivesAuditService amsArchivesAuditService;

	/**
	 * 跳转领导者端首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "jumpLeadIndex")
	@ResponseBody
	public ResultTO jumpLeadIndex(HttpServletRequest request) {
		LOGGER.info("************* 跳转领导者端首页	-begin*****************");
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
		    //return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		//返回结果级
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			//1.获取库房统计数据
			resultMap.put("vmsStatis", wmsStoreService.getVmsStatisData(storeId));
			//2.获取未读消息数目
			Map<String, Object> parmUp = new HashMap<String, Object>();
			parmUp.put("userId", userId);
			parmUp.put("status", "0");//0未读
			resultMap.put("unMessNum",String.valueOf(messageReceiveService.selectMessagesNum(parmUp) ));
			//3.获取待审批申请数目
			Map<String, Object> parmMap = new HashMap<String, Object>();
			parmMap.put("status", "0".split(","));//0未审批
			parmMap.put("pageIndex", null);
			parmMap.put("pageSize", null);
			resultMap.put("auditNum",String.valueOf(amsArchivesAuditService.getAuditUserGroupList(parmMap).size()));
			//4.获取未处理报警总数目
			Integer alarmNum = 0;//初始化
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", 0);//0未处理
			List<Map<String, Object>> result = wmsSatatisService.selectAlarmStatis(map);
			//返回的是分类型报警数数量的集合,便利相加得到总数
			for(Map<String, Object> list:result){
			    alarmNum+=Integer.parseInt(list.get("count").toString());
			}
			resultMap.put("alarmNum",String.valueOf(alarmNum));
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("跳转首页出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("************* 跳转领导者端首页	接口	-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", resultMap);
	}
	/**
	 * 档案检索
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "searchAmsList")
	@ResponseBody
	public ResultTO searchAmsList(HttpServletRequest request) {
		LOGGER.info("*************档案检索-begin*****************");
		// 档案标题名称
		String title = RequestParamUtil.getEncodeParam(request, "title");
		if (StringUtils.isEmpty(title)) {
			return ResultTO.newFailResultTO("档案标题名称不能为空", null);
		}

		//返回结果级
		List<AmsArchivesTO> amsArchivesTOList = new ArrayList<AmsArchivesTO>();
		try {
			Map<String, Object> reqMap = new HashMap<String, Object>();
			// 检索档案标题
			reqMap.put("title", title);
			List<AmsArchives>  amsArchivesList = amsArchivesService.selectObjectList(reqMap);
			//过滤返回字段
			for(AmsArchives amsArchives : amsArchivesList){
				AmsArchivesTO amsArchivesTO = new AmsArchivesTO();
				amsArchivesTO.setArchivesId(amsArchives.getArchivesId());
				amsArchivesTO.setArchivesTypeId(amsArchives.getArchivesTypeId());
				amsArchivesTO.setArchivesTypeName(amsArchives.getArchivesTypeName());
				amsArchivesTO.setCheckStatus(amsArchives.getCheckStatus());
				amsArchivesTO.setStoreplace(amsArchives.getStoreplace());
				amsArchivesTO.setTitle(amsArchives.getTitle());
				amsArchivesTO.setInflag(amsArchives.getInflag());
				amsArchivesTO.setStroreId(amsArchives.getStroreId());
				amsArchivesTO.setStroreAreaId(amsArchives.getStroreAreaId());
				amsArchivesTO.setStroreColumn(amsArchives.getStoreColumn());
				amsArchivesTO.setStroreRl(amsArchives.getStoreLr());
				//加入结果级
				amsArchivesTOList.add(amsArchivesTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("检索档案出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("检索档案失败！", null);
		}
		LOGGER.info("*************档案检索-end*****************");
		return ResultTO.newSuccessResultTO("检索成功", amsArchivesTOList);
	}
}
