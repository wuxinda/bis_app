package com.bluemobi.controller.web;

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

import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.po.wms.WmsStoreArea;

import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.wms.WmsStoreAreaService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;


/**
 * WEB【档案操作纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebImsController")
@RequestMapping("web/ims")
public class WebImsController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebImsController.class);

    @Autowired
    private WmsStoreAreaService wmsStoreAreaService;
   
    @Autowired
    private DeviceManageService deviceManageService;
    @Autowired
    private WmsStoreService wmsStoreService;
    @Autowired
    private WmsStoreDao wmsStoreDao;
    /**
     * 根据库房获取库区
     */
    @RequestMapping(value = "getStoreAreaByStoreId", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getStoreAreaByStoreId(Integer storeId){
	List<WmsStoreArea> list = wmsStoreAreaService.getStoreAreaByStoreId(storeId);
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
    /**
     * 通过库房库区获取设备列表
     * 
     * @param categoryId
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getStoreDeviceListProperty", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getStoreDeviceListLink(Integer categoryId, Integer storeId, Integer stroreAreaId) {
	// 返回结果级
	List<Map<String, Object>> rdataList = null;
	try {
	    // 查询
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("storeId", storeId);
	    paramMap.put("stroreAreaId", stroreAreaId);
	    paramMap.put("categoryId", 5);
	    // 查询库房设备列表
	    rdataList = deviceManageService.selectObjectList(paramMap);
	    LOGGER.info("用户【{}】获取库房设备列表【{}】成功", new Object[] { this.getUserId(), categoryId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取库房设备列表【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, e });
	    return ResultTO.newFailResultTO("获取库房设备列表失败", null);
	}
	return ResultTO.newSuccessResultTO("获取库房设备列表成功", rdataList);
    }
    /**
     * 通过密集架id获取视频通道号
     * 
     * @param categoryId
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getVideoNo", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getVideoNo(Integer deviceId) {
	// 返回结果级
	List<Map<String, Object>> list = null;
	try {
	    list = deviceManageService.getVideoNo(deviceId);
	    LOGGER.info("用户【{}】获取视频通道号通过密集架id【{}】成功", new Object[] { this.getUserId(), deviceId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取视频通道号通过密集架id【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceId, e });
	    return ResultTO.newFailResultTO("获取视频通道号失败", null);
	}
	return ResultTO.newSuccessResultTO("获取视频通道号成功", list);
    }
    /**
     * 获取库房列表
     * @return
     */
    @RequestMapping(value = "getWmsStore", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getWmsStore(HttpServletRequest request) {
    	AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("enName", user.getEnName());
    	List<WmsStore> list = wmsStoreDao.selectObjectListByUser(map);
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
}
