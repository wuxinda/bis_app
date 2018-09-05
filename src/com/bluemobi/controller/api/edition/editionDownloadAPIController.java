package com.bluemobi.controller.api.edition;


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

import com.bluemobi.conf.Config;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.edition.EditionInfo;
import com.bluemobi.service.edition.EditionInfoService;
import com.bluemobi.serviceimpl.edition.EditionInfoServiceImpl;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
/**
 * 设备控制器
 * @author Riven
 *
 */
@Controller(value = "editionAPIController")
@RequestMapping("api/edition")
public class editionDownloadAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(editionDownloadAPIController.class);
    
	@Autowired
	private EditionInfoService editionInfoService;
	/**
	 * 设备控制接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getEdition")
	@ResponseBody
	public ResultTO operateEdition(HttpServletRequest request) {
		LOGGER.info("*************版本下载接口-begin*****************");
		// 用户id
		String editionNum = RequestParamUtil.getEncodeParam(request, "editionNumber");
		
		String editionType = RequestParamUtil.getEncodeParam(request, "editionType");
		if (StringUtils.isEmpty(editionType)) {
			return ResultTO.newFailResultTO("应用平台不能为空", null);
		}
		String editionCode = RequestParamUtil.getEncodeParam(request, "editionCode");
		if (StringUtils.isEmpty(editionCode)) {
			return ResultTO.newFailResultTO("应用名称CODE不能为空", null);
		}		
		EditionInfo edition=null;
		List<EditionInfo> lists=new ArrayList<EditionInfo>();
		try {
			//组装参数			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			
			if(editionNum!=null&&!editionNum.equals("")){//获取指定版本
				paramMap.put("editionNumber", editionNum);
			}else{//获取发布版本
				paramMap.put("isPublish", 0);
			}	
			paramMap.put("editionType", editionType);
			paramMap.put("sunType", editionCode);
			//获取相应版本的对象
			List<EditionInfo> result = editionInfoService.selectObjectList(paramMap);
			System.out.println(result);
			if(result.size()>0&&result!=null){
				edition = result.get(0);
				edition.setEditionUrl(Config.TEMP_IMG_URL+edition.getEditionUrl());	
				lists.add(edition);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			LOGGER.error("获取版本下载信息出现异常【{}】，请求ip【{}】，请求信息【{}】",
//					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("************* 版本下载接口-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", lists);
	}
}
