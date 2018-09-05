package com.bluemobi.controller.device;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.alarm.AlarmHcsdeviceConfig;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.po.wms.WmsStoreArea;
import com.bluemobi.service.alarm.AlarmHcsdeviceConfigService;
import com.bluemobi.service.device.DeviceCategoryBrandService;
import com.bluemobi.service.device.DeviceCategoryLinkpropertyService;
import com.bluemobi.service.device.DeviceCategoryService;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.wms.WmsStoreAreaService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 【设备管理表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("deviceManage")
public class DeviceManageController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceManageController.class);

    @Autowired
    private DeviceManageService deviceManageService;

    @Autowired
    private DeviceCategoryService deviceCategoryService;

    @Autowired
    private WmsStoreService wmsStoreService;
    @Autowired
    private WmsStoreAreaService wmsStoreAreaService;

    @Autowired
    private DeviceCategoryBrandService deviceCategoryBrandService;

    @Autowired
    private DeviceCategoryLinkpropertyService deviceCategoryLinkpropertyService;
    @Autowired
    private AlarmHcsdeviceConfigService alarmHcsdeviceConfigService;

    /**
     * 将请求参数中的字符串转换成日期格式
     * 
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 请求参数中的日期字符串格式
	CustomDateEditor editor = new CustomDateEditor(df, false);
	binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 进入【设备管理表】主页
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
	// 加载公共数据
	initIndex(model);
	// 查询所有设备分类
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("grade", 1);
	model.addAttribute("deviceCategory", deviceCategoryService.selectObjectList(map));
	// 查询所有库房
	model.addAttribute("wmsStore", wmsStoreService.selectObjectList(new HashMap<String, Object>()));
	return "device/manage.index";
    }

    /**
     * 分页查询【设备管理表】
     * 
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(@ModelAttribute DeviceManage deviceManage, int pageSize, int pageIndex) {
	// 查询条件
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("name", "".equals(deviceManage.getName()) ? null : deviceManage.getName());
	map.put("categoryId", "".equals(deviceManage.getCategoryId()) ? null : deviceManage.getCategoryId());
	map.put("storeId", "".equals(deviceManage.getStoreId()) ? null : deviceManage.getStoreId());
	Page<Map<String, Object>> page = deviceManageService.page(map, pageIndex, pageSize);
	return page;
    }

    /**
     * 查询【设备管理表】详情
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer deviceId) {
	// 加载公共数据
	initIndex(model);
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("deviceId", deviceId);
	model.addAttribute("deviceManage", deviceManageService.selectObject(map));
	// 查找设备的连接配置信息
	model.addAttribute("linkList", deviceManageService.getLinkListByDeviceId(deviceId));
	return "device/manage.detail";
    }

    /**
     * 进入新增【设备管理表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, String category2, String category1) {
	// 加载公共数据
	initIndex(model);
	String rcategoryId = null;
	// 解析设备类型参数 规则id,name
	if (!StringUtils.isEmpty(category2)) {
	    String categoryId = category2.split(",")[0];
	    rcategoryId = categoryId;
	    // 根据设备id查询设备的连接属性集合
	    List<Map<String, Object>> linkList = deviceCategoryLinkpropertyService.getLinkByCategoryId(Integer.parseInt(categoryId));
	    model.addAttribute("linkList", linkList);
	}
	model.addAttribute("categoryId", rcategoryId);
	// 解析设备类型参数 规则id,name
	if (!StringUtils.isEmpty(category1)) {
	    String storeId = category1.split(",")[0];
	    // 库区集合
	    List<WmsStoreArea> storeArea = wmsStoreAreaService.getStoreAreaByStoreId(Integer.parseInt(storeId));
	    model.addAttribute("wmsStoreArea", storeArea);
	} 
	return "device/manage.edit";
    }

    /**
     * 新增【设备管理表】数据
     * 
     * @param deviceManage
     * @param linkIds
     *            设备连接属性id 多值记录已逗号隔开 注意：linkIds和linkIdValues为顺序对应关系
     * @param linkIdValues
     *            设备连接属性值 多值记录已逗号隔开
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addDeviceManage(@ModelAttribute DeviceManage deviceManage, @ModelAttribute AlarmHcsdeviceConfig alarmHcsdeviceConfig, String linkIds,
	    String linkIdValues, BindingResult result) {
	try {
	    // 补全参数
	    deviceManage.setCreator(this.getUserId());
	    deviceManage.setCtime(new Date());
	    deviceManage.setModifier(this.getUserId());
	    deviceManage.setMtime(new Date());
	    deviceManage.setLinkIds(linkIds.split(","));
	    deviceManage.setLinkIdValues(linkIdValues.split(","));
	    if (alarmHcsdeviceConfig != null&&deviceManage.getCategoryId()==4) {
		alarmHcsdeviceConfig.setCreator(this.getUserId());
		alarmHcsdeviceConfig.setCtime(new Date());
		alarmHcsdeviceConfig.setModifier(this.getUserId());
		alarmHcsdeviceConfig.setMtime(new Date());
	    }else{
	    	alarmHcsdeviceConfig = new AlarmHcsdeviceConfig();
	    }
	    // 添加设备
	    deviceManageService.addDevice(deviceManage, alarmHcsdeviceConfig);
	    LOGGER.info("用户【{}】添加设备管理表数据【{}】成功", new Object[] { this.getUserId(), deviceManage });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】添加设备管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceManage, e });
	    return ResultTO.newFailResultTO("添加失败", null);
	}
	return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 进入修改【设备管理表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer deviceId) {
	// 加载公共数据
	initIndex(model);
	// 查询设备主表信息
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("deviceId", deviceId);
	DeviceManage deviceManage = deviceManageService.selectObject(map);
	model.addAttribute("deviceManage", deviceManage);
	model.addAttribute("linkList", deviceManageService.getLinkListByDeviceId(deviceId));
	if(alarmHcsdeviceConfigService.selectObjectList(map).size()>0){
	    model.addAttribute("alarmHcsdeviceConfig", alarmHcsdeviceConfigService.selectObjectList(map).get(0));
	}else{
	    model.addAttribute("alarmHcsdeviceConfig", new AlarmHcsdeviceConfig());
	}
	
	// 查询设备连接属性配置
	List<WmsStoreArea> storeArea = wmsStoreAreaService.getStoreAreaByStoreId(deviceManage.getStoreId());
	model.addAttribute("wmsStoreArea", storeArea);
	return "device/manage.edit";
    }

    /**
     * 修改【设备管理表】数据
     * 
     * @param deviceManage
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editDeviceManage(@ModelAttribute DeviceManage deviceManage,@ModelAttribute AlarmHcsdeviceConfig alarmHcsdeviceConfig, String linkIds, String linkIdValues, BindingResult result) {
	// 补全参数
	deviceManage.setModifier(this.getUserId());
	deviceManage.setMtime(new Date());
	deviceManage.setLinkIds(linkIds.split(","));
	deviceManage.setLinkIdValues(linkIdValues.split(","));
	try {
	    alarmHcsdeviceConfig.setModifier(this.getUserId());
	    alarmHcsdeviceConfig.setMtime(new Date());
	    deviceManageService.updateDevice(deviceManage,alarmHcsdeviceConfig);
	    LOGGER.info("用户【{}】修改设备管理表数据【{}】成功", new Object[] { this.getUserId(), deviceManage });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】修改设备管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceManage, e });
	    return ResultTO.newFailResultTO("更新失败", null);
	}
	return ResultTO.newSuccessResultTO("更新成功", null);
    }

    /**
     * 删除【设备管理表】数据
     * 
     * @param deviceId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteDeviceManage(Integer deviceId) {
	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    map.put("deviceId", deviceId);
	    deviceManageService.delete(map);
	    LOGGER.info("用户【{}】删除设备管理表数据【{}】成功", new Object[] { this.getUserId(), deviceId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】删除设备管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceId, e });
	    return ResultTO.newFailResultTO("删除失败", null);
	}
	return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 进入设备分类，库房添加页面
     * 
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "editCategory", method = RequestMethod.GET)
    public String editCategory(Model model, Integer id) {
	// 加载公共数据
	initIndex(model);
	// 查询所有设备分类
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("grade", 1);
	model.addAttribute("deviceCategory", deviceCategoryService.selectObjectList(map));
	// 查询所有库房
	model.addAttribute("wmsStore", wmsStoreService.selectObjectList(new HashMap<String, Object>()));
	return "device/manage.edit.category";
    }

    /**
     * 根据设备分类id获取品牌列表
     * 
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "getBrandByCategoryId", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO getBrandByCategoryId(Integer categoryId) {
	// 返回结果级
	List<Map<String, Object>> brandList = null;

	try {
	    // 查询
	    brandList = deviceCategoryBrandService.getBrandByCategoryId(categoryId);
	    LOGGER.info("用户【{}】获取品牌列表【{}】成功", new Object[] { this.getUserId(), categoryId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取品牌列表【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, e });
	    return ResultTO.newFailResultTO("获取品牌列表失败", null);
	}
	return ResultTO.newSuccessResultTO("获取品牌列表成功", brandList);
    }

    /**
     * 根据库房id获取库房设备分类列表
     * 
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "getStoreDevCate", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO getStoreDevCate(Integer storeId) {
	// 返回结果级
	List<Map<String, Object>> cateList = null;
	try {
	    // 查询
	    cateList = deviceManageService.getStoreDevCate(storeId);
	    LOGGER.info("用户【{}】获取库房设备分类列表【{}】成功", new Object[] { this.getUserId(), storeId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取库房设备分类列表【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), storeId, e });
	    return ResultTO.newFailResultTO("获取库房设备分类列表失败", null);
	}
	return ResultTO.newSuccessResultTO("获取库房设备分类列表成功", cateList);
    }

    /**
     * 获取库房设备列表
     * 
     * @param categoryId
     * @param storeId
     * @return
     */
    @RequestMapping(value = "getStoreDeviceList", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO getStoreDeviceList(Integer categoryId, Integer storeId) {
	// 返回结果级
	List<Map<String, Object>> rdataList = null;
	try {
	    // 查询
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("storeId", storeId);
	    paramMap.put("categoryId", categoryId);
	    // 查询库房设备列表
	    rdataList = deviceManageService.selectMapList(paramMap);
	    LOGGER.info("用户【{}】获取库房设备列表【{}】成功", new Object[] { this.getUserId(), categoryId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取库房设备列表【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, e });
	    return ResultTO.newFailResultTO("获取库房设备列表失败", null);
	}
	return ResultTO.newSuccessResultTO("获取库房设备列表成功", rdataList);
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
     * 获取所有设备列表
     * @return
     */
    @RequestMapping(value = "getDevice", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getDevice() {
	List<DeviceManage> list = deviceManageService.selectDevice();
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
    /**
     * 通过库房Id和库区Id和密集架号找设备
     * @param map
     * @return
     */
    @RequestMapping(value = "selectDeviceForArchive", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO selectDeviceForArchive(HttpServletRequest request) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String stroreId=RequestParamUtil.getEncodeParam(request, "stroreId");
    	if(stroreId!=null) {
    		map.put("storeId",stroreId );
    	}
    	String stroteAreaId=RequestParamUtil.getEncodeParam(request, "stroteAreaId");
    	if(stroteAreaId!=null) {
    		map.put("stroreAreaId",stroteAreaId );
    	}
    	String storeColumn=RequestParamUtil.getEncodeParam(request, "storeColumn");
    	if(storeColumn!=null) {
    		map.put("sortOrder",storeColumn);
    	}
    	Map<String,Object> resMap = deviceManageService.selectDeviceForArchive(map);
	return ResultTO.newSuccessResultTO("获取成功", resMap);
    }
}
