package com.bluemobi.controller.api.device;
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

import com.bluemobi.apito.scene.SceneManageTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.scene.SceneManage;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.scene.SceneManageService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
/**
 * 设备控制器
 * @author Riven
 *
 */
@Controller(value = "deviceAPIController")
@RequestMapping("api/device")
public class DeviceAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeviceAPIController.class);
    
	@Autowired
	private DeviceManageService deviceManageService;
    @Autowired
    private SceneManageService sceneManageService;
	/**
	 * 设备控制接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "operateDevice")
	@ResponseBody
	public ResultTO operateDevice(HttpServletRequest request) {
		LOGGER.info("*************设备控制接口-begin*****************");
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		// 设备id
		String deviceId = RequestParamUtil.getEncodeParam(request, "deviceId");
		if (StringUtils.isEmpty(deviceId)) {
			return ResultTO.newFailResultTO("设备id不能为空", null);
		}
		//设备类型id  1.视频 2.灯光 3.门禁 4.温度计  5.密集架
		String categoryId = RequestParamUtil.getEncodeParam(request, "categoryId");
		if (StringUtils.isEmpty(categoryId)) {
			return ResultTO.newFailResultTO("设备类型id不能为空", null);
		}
		//档案id
		String archivesId = RequestParamUtil.getEncodeParam(request, "archivesId");

		//动作类型id  1.开 2.关 3.停止 4.左开  5.右开 6.通风 7.合拢 8.通电 9.断电 10.调档
		String actionType = RequestParamUtil.getEncodeParam(request, "actionType");
		if (StringUtils.isEmpty(actionType)) {
			return ResultTO.newFailResultTO("动作类型不能为空", null);
			//如果动作类型为存取档则需要传入档案id才能进行操作
		}else if("10".equals(actionType)){
			if (StringUtils.isEmpty(archivesId)) {
				return ResultTO.newFailResultTO("如果需要进行密集架存取档操作，档案id不能为空", null);
			}
		}
		//全选标示 0 否 1是
		String isSelect = RequestParamUtil.getEncodeParam(request, "isSelect");
		if (StringUtils.isEmpty(isSelect)) {
			return ResultTO.newFailResultTO("全选标示不能为空", null);
		}
		try {
			//组装参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("storeId", storeId);
			paramMap.put("deviceId", deviceId);
			paramMap.put("categoryId", categoryId);
			paramMap.put("actionType", actionType);
			paramMap.put("isSelect", isSelect);
			//执行设备操控
			int result = deviceManageService.operateDevice(paramMap);
			if(result==1){//0成功  1失败
			    return ResultTO.newFailResultTO("操作失败！请检查连接参数和网络是否配置正确！", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("操作设备出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！请检查连接参数和网络是否配置正确！", null);
		}
		LOGGER.info("************* 设备控制接口-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);
	}
	/**
	 * 获取情景模式列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getSceneManageList")
	@ResponseBody
	public ResultTO getSceneManageList(HttpServletRequest request) {
		LOGGER.info("*************获取情景模式列表-begin*****************");
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		//返回结果级
		List<SceneManageTO> sceneManageToList = null;
		try {
			//组装参数
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("storeId", storeId);
		    //只查询在用的
			paramMap.put("status", "1");
			//获取情景模式列表
			List<SceneManage> sceneManageList = sceneManageService.selectObjectList(paramMap);
			//过滤返回字段
			sceneManageToList = new ArrayList<SceneManageTO>();
			for(SceneManage  sceneManage : sceneManageList){
				SceneManageTO to = new SceneManageTO();
				to.setName(sceneManage.getName());
				to.setSceneId(sceneManage.getSceneId());
				sceneManageToList.add(to);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取情景模式列表异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("获取情景模式列表！", null);
		}
		LOGGER.info("************* 获取情景模式列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", sceneManageToList);
	}
	/**
	 * 执行场景模式
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "executeScene")
	@ResponseBody
	public ResultTO executeScene(HttpServletRequest request) {
		LOGGER.info("*************执行场景模式-begin*****************");
		// 情景id
		String sceneId = RequestParamUtil.getEncodeParam(request, "sceneId");
		if (StringUtils.isEmpty(sceneId)) {
			return ResultTO.newFailResultTO("情景id不能为空", null);
		}
		//档案id
		String archivesId = RequestParamUtil.getEncodeParam(request, "archivesId");

		// 执行结果 0成功 1失败
		int result = 1;
		try {
			if (StringUtils.isEmpty(archivesId)) {
				//执行不带档案号操作的场景模式
				result = sceneManageService.executeScene(Integer.parseInt(sceneId));	
			}else{
				//执行带档案操作的场景模式
				result = sceneManageService.executeScene(Integer.parseInt(sceneId),Integer.parseInt(archivesId));
			}

			if(result==0){
				LOGGER.info("用户【{}】执行场景模式【{}】异常，请检查设备配置是否正确或网络是否正常", new Object[] {
						this.getUserId(), sceneId });
				return ResultTO.newFailResultTO("执行场景模式失败", result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("执行场景模式异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("执行场景模式异常！", null);
		}
		LOGGER.info("************* 执行场景模式-end*****************");
		return ResultTO.newSuccessResultTO("执行场景模式成功", result);
	}
}
