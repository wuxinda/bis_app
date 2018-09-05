package com.bluemobi.controller.api.wms;

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
import com.bluemobi.dao.log.LogUserImageDao;
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.po.wms.WmsStoreArea;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.wms.WmsStoreAreaService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.service.wms.WmsUserService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 库房控制器
 * 
 * @author Riven
 *
 */
@Controller(value = "storeAPIController")
@RequestMapping("api/store")
public class StoreAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreAPIController.class);

	@Autowired
	private WmsStoreAreaService wmsStoreAreaService;
	@Autowired
	private WmsStoreService wmsStoreService;
	@Autowired
	private DeviceManageService deviceManageService;
	@Autowired
	private WmsUserService wmsUserService;
	@Autowired
	private LogUserImageDao logUserImageDao;

	/**
	 * 获取库房列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStoreList")
	@ResponseBody
	public ResultTO getStoreList(HttpServletRequest request) {
		LOGGER.info("************* 获取库房列表-begin*****************");
		// 返回结果级
		List<WmsStore> wmsStoreist = null;
		try {
			// 查询所有库房列表
			wmsStoreist = wmsStoreService.selectObjectList(new HashMap<String, Object>());

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取库房列表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("************* 获取库房列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", wmsStoreist);
	}

	/**
	 * 获取库房下库区列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStoreAreaList")
	@ResponseBody
	public ResultTO getStoreAreaList(HttpServletRequest request) {
		LOGGER.info("*************获取库房下库区列表-begin*****************");
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		// 返回结果级
		List<WmsStoreArea> storeAreaList = null;
		try {
			Map<String, Object> reqMap = new HashMap<String, Object>();
			// 检索档案标题
			reqMap.put("storeId", storeId);
			// 查询所有库房列表
			storeAreaList = wmsStoreAreaService.selectObjectList(reqMap);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取库房下库区列表出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("************* 获取库房下库区列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", storeAreaList);
	}

	/**
	 * 获取库房信息接口
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStoreInfo")
	@ResponseBody
	public ResultTO getStoreInfo(HttpServletRequest request) {
		LOGGER.info("*************获取库房信息-begin*****************");
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房id不能为空", null);
		}
		// 返回结果级
		Map<String, Object> rdata = null;
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("storeId", storeId);
			// 查询库房信息
			rdata = wmsStoreService.selectStoreInfo(paramMap);

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取库房信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("************* 获取库房信息-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", rdata);
	}

	/**
	 * 获取库房设备列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getStoreDeviceList")
	@ResponseBody
	public ResultTO getStoreDeviceList(HttpServletRequest request) {
		LOGGER.info("*************获取库房设备列表-begin*****************");
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		// 库房id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		/*
		 * if (StringUtils.isEmpty(storeId)) { return
		 * ResultTO.newFailResultTO("库房id不能为空", null); }
		 */
		// 设备类型id 1.视频 2.灯光 3.门禁 4.温度计 5.密集架
		String categoryId = RequestParamUtil.getEncodeParam(request, "categoryId");
		/*
		 * if (StringUtils.isEmpty(categoryId)) { return
		 * ResultTO.newFailResultTO("设备类型id不能为空", null); }
		 */
		// 返回结果级
		List<Map<String, Object>> rdataList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("categoryId", categoryId);
			// 获取当前用户所管理的所有库房
			List<Integer> list = new ArrayList<Integer>();
			list = wmsUserService.selectUserStoreIds(userId);
			// 如果没有管理的库房则返回空
			if (list != null && list.size() > 0) {
				if (storeId != null && !storeId.equals("")) {// 反回权限内指定库房
					for (Integer storeid : list) {
						if (String.valueOf(storeid).equals(storeId)) {
							paramMap.put("storeId", storeId);
							rdataList = deviceManageService.selectMapList(paramMap);
							break;
						}
					}
				} else {// 用户没有指定库房返回属于用户的所有库房设备
					for (Integer storeid : list) {
						List<Map<String, Object>> dataList = null;
						paramMap.put("storeId", storeid);
						// 查询库房设备列表
						dataList = deviceManageService.selectMapList(paramMap);
						if (dataList != null && dataList.size() > 0) {
							for (Map<String, Object> ls : dataList) {
								rdataList.add(ls);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取库房信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("************* 获取库房设备列表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", rdataList);
	}
	/**
	 * 获取库房设备列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getImgUrl")
	@ResponseBody
	public ResultTO getImgUrl(HttpServletRequest request) {
		LOGGER.info("*************获取库房设备列表-begin*****************");
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("storeId", storeId);
		List<Map<String,Object>> list=logUserImageDao.getImgUrl(map);
 		return ResultTO.newSuccessResultTO("请求成功", list);
	}
}
