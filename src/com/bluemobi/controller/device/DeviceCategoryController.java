package com.bluemobi.controller.device;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.bluemobi.po.device.DeviceActionproperty;
import com.bluemobi.po.device.DeviceCategory;
import com.bluemobi.po.device.DeviceCategoryActionproperty;
import com.bluemobi.po.device.DeviceCategoryLinkproperty;
import com.bluemobi.po.device.DeviceLinkproperty;
import com.bluemobi.service.device.DeviceActionpropertyService;
import com.bluemobi.service.device.DeviceCategoryActionpropertyService;
import com.bluemobi.service.device.DeviceCategoryLinkpropertyService;
import com.bluemobi.service.device.DeviceCategoryService;
import com.bluemobi.service.device.DeviceLinkpropertyService;
import com.bluemobi.to.ResultTO;

/**
 * 【设备分类表】控制器
 * 
 * @author Riven
 * 
 */
@Controller
@RequestMapping("deviceCategory")
public class DeviceCategoryController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceCategoryController.class);

    @Autowired
    private DeviceCategoryService deviceCategoryService;
    @Autowired
    private DeviceCategoryLinkpropertyService deviceCategoryLinkpropertyService;
    @Autowired
    private DeviceLinkpropertyService deviceLinkpropertyService;
    @Autowired
    private DeviceCategoryActionpropertyService deviceCategoryActionpropertyService;
    @Autowired
    private DeviceActionpropertyService deviceActionpropertyService;

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
     * 进入【设备分类表】主页
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
	return "device/category.index";
    }

    /**
     * 分页查询【设备分类表】
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
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("key", key);
	Page<Map<String, Object>> page = deviceCategoryService.page(map, pageIndex, pageSize);
	return page;
    }

    /**
     * 查询【设备分类表】详情
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer categoryId) {
	// 加载公共数据
	initIndex(model);
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("categoryId", categoryId);
	model.addAttribute("deviceCategory", deviceCategoryService.selectObject(map));
	return "device/category.detail";
    }

    /**
     * 进入新增【设备分类表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
	// 加载公共数据
	initIndex(model);
	return "device/category.edit";
    }

    /**
     * 新增【设备分类表】数据
     * 
     * @param deviceCategory
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addDeviceCategory(@ModelAttribute DeviceCategory deviceCategory, BindingResult result) {
	try {
	    // 设置创建人，创建时间
	    deviceCategory.setCreator(this.getUserId());
	    deviceCategory.setCtime(new Date());
	    // 设置分类等级 1.一级分类 2.二级分类 3.三级分类，默认为1
	    deviceCategory.setGrade(1);
	    deviceCategoryService.insert(deviceCategory);
	    LOGGER.info("用户【{}】添加设备分类表数据【{}】成功", new Object[] { this.getUserId(), deviceCategory });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】添加设备分类表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceCategory, e });
	    return ResultTO.newFailResultTO("添加失败", null);
	}
	return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 进入修改【设备分类表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer categoryId) {
	// 加载公共数据
	initIndex(model);
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("categoryId", categoryId);
	model.addAttribute("deviceCategory", deviceCategoryService.selectObject(map));
	return "device/category.edit";
    }

    /**
     * 修改【设备分类表】数据
     * 
     * @param deviceCategory
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editDeviceCategory(@ModelAttribute DeviceCategory deviceCategory, BindingResult result) {
	try {
	    // 设置修改人和修改时间
	    deviceCategory.setModifier(this.getUserId());
	    deviceCategory.setMtime(new Date());
	    deviceCategoryService.update(deviceCategory);
	    LOGGER.info("用户【{}】修改设备分类表数据【{}】成功", new Object[] { this.getUserId(), deviceCategory });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】修改设备分类表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceCategory, e });
	    return ResultTO.newFailResultTO("更新失败", null);
	}
	return ResultTO.newSuccessResultTO("更新成功", null);
    }

    /**
     * 删除【设备分类表】数据
     * 
     * @param categoryId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteDeviceCategory(Integer categoryId) {
	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    map.put("categoryId", categoryId);
	    deviceCategoryService.delete(map);
	    LOGGER.info("用户【{}】删除设备分类表数据【{}】成功", new Object[] { this.getUserId(), categoryId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】删除设备分类表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, e });
	    return ResultTO.newFailResultTO("删除失败", null);
	}
	return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 设备分类绑定设备连接属性页面跳转
     * 
     * @param model
     * @param brandId
     * @return
     */
    @RequestMapping(value = "categoryLinkpropertyJump", method = RequestMethod.GET)
    public String categoryLinkpropertyJump(Model model, Integer categoryId) {
	// 加载公共数据
	initIndex(model);
	Map<String, Object> mapTemp = new HashMap<String, Object>();
	mapTemp.put("categoryId", categoryId);
	// 查询当前设备分类id对应的连接属性id
	List<DeviceCategoryLinkproperty> categoryLinkpropertyList = deviceCategoryLinkpropertyService.selectObjectList(mapTemp);
	// 当前设备分类已选择的连接属性对象集合
	List<DeviceLinkproperty> linkpropertyList = new ArrayList<DeviceLinkproperty>();
	Map<String, Object> mapTmp = new HashMap<String, Object>();
	for (DeviceCategoryLinkproperty categoryLinkproperty : categoryLinkpropertyList) {
	    mapTmp = new HashMap<String, Object>();
	    mapTmp.put("linkpropertyId", categoryLinkproperty.getLinkpropertyId());
	    DeviceLinkproperty deviceLinkproperty = deviceLinkpropertyService.selectObject(mapTmp);
	    linkpropertyList.add(deviceLinkproperty);
	}
	// 已选择设备连接属性集合
	model.addAttribute("linkpropertys", linkpropertyList);
	// 设备分类id
	model.addAttribute("categoryId", categoryId);
	return "device/category.categoryLinkproperty";
    }

    /**
     * 设备分类绑定连接属性
     * 
     * @param linkpropertyIds
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "categoryLinkproperty", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO categoryLinkproperty(String linkpropertyIds, Integer categoryId) {
	try {
	    // 更新绑定
	    deviceCategoryService.updateCategoryLinkproperty(linkpropertyIds, categoryId);
	    LOGGER.info("用户【{}】绑定设备连接属性数据【{}】成功", new Object[] { this.getUserId(), categoryId, linkpropertyIds });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】绑定设备连接属性【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, linkpropertyIds, e });
	    return ResultTO.newFailResultTO("连接属性绑定属性失败", null);
	}
	return ResultTO.newSuccessResultTO("连接属性绑定属性成功", null);
    }

    /**
     * 设备分类绑定设备操作属性页面跳转
     * 
     * @param model
     * @param brandId
     * @return
     */
    @RequestMapping(value = "categoryActionpropertyJump", method = RequestMethod.GET)
    public String categoryActionpropertyJump(Model model, Integer categoryId) {
	// 加载公共数据
	initIndex(model);
	Map<String, Object> mapTemp = new HashMap<String, Object>();
	mapTemp.put("categoryId", categoryId);
	// 查询当前设备分类id对应的操作属性id
	List<DeviceCategoryActionproperty> categoryActionpropertyList = deviceCategoryActionpropertyService.selectObjectList(mapTemp);
	// 当前设备分类已选择的操作属性对象集合
	List<DeviceActionproperty> actionpropertyList = new ArrayList<DeviceActionproperty>();
	Map<String, Object> mapTmp = new HashMap<String, Object>();
	for (DeviceCategoryActionproperty categoryActionproperty : categoryActionpropertyList) {
	    mapTmp = new HashMap<String, Object>();
	    mapTmp.put("actionpropertyId", categoryActionproperty.getActionpropertyId());
	    DeviceActionproperty deviceActionproperty = deviceActionpropertyService.selectObject(mapTmp);
	    actionpropertyList.add(deviceActionproperty);
	}
	// 已选择设备操作属性集合
	model.addAttribute("actionpropertys", actionpropertyList);
	// 设备分类id
	model.addAttribute("categoryId", categoryId);
	return "device/category.categoryActionproperty";
    }

    /**
     * 设备分类绑定操作属性
     * 
     * @param actionpropertyIds
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "categoryActionproperty", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO categoryActionproperty(String actionpropertyIds, Integer categoryId) {
	try {
	    // 更新绑定
	    deviceCategoryService.updateCategoryActionproperty(actionpropertyIds, categoryId);
	    ;
	    LOGGER.info("用户【{}】绑定设备操作属性数据【{}】成功", new Object[] { this.getUserId(), categoryId, actionpropertyIds });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】绑定设备操作属性【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, actionpropertyIds, e });
	    return ResultTO.newFailResultTO("操作属性绑定属性失败", null);
	}
	return ResultTO.newSuccessResultTO("操作属性绑定属性成功", null);
    }

    /**
     * 获取类型列表
     * 
     * @param userId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "getcategoryList", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getUserList(Map<String, Object> mapTemp) {
	List<DeviceCategory> list = new ArrayList<DeviceCategory>();
	try {
	    list = deviceCategoryService.selectObjectList(mapTemp);
	    LOGGER.info("用户【{}】获取设备类型表数据【{}】成功", new Object[] { this.getUserId(), null });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取设备类型表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), null, e });
	    return ResultTO.newFailResultTO("获取失败", null);
	}
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
}
