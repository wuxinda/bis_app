package com.bluemobi.controller.device;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.device.DeviceCategoryActionproperty;
import com.bluemobi.service.device.DeviceCategoryActionpropertyService;
import com.bluemobi.to.ResultTO;



/**
 * 【设备分类绑定操作属性表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("deviceCategoryActionproperty")
public class DeviceCategoryActionpropertyController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceCategoryActionpropertyController.class);
    
    @Autowired
    private DeviceCategoryActionpropertyService deviceCategoryActionpropertyService;
    

    
    /**
     * 进入【设备分类绑定操作属性表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/categoryActionproperty.index";
    }
    
    /**
     * 分页查询【设备分类绑定操作属性表】
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
        Page<Map<String, Object>> page = deviceCategoryActionpropertyService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【设备分类绑定操作属性表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer ctgyActpId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("ctgyActpId", ctgyActpId); 
        model.addAttribute("deviceCategoryActionproperty", deviceCategoryActionpropertyService.selectObject(map));
        return "device/categoryActionproperty.detail";
    }
    
    /**
     * 进入新增【设备分类绑定操作属性表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/categoryActionproperty.edit";
    }
    
    /**
     * 新增【设备分类绑定操作属性表】数据
     * @param deviceCategoryActionproperty
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addDeviceCategoryActionproperty(@ModelAttribute DeviceCategoryActionproperty deviceCategoryActionproperty, BindingResult result) {
        try {
            deviceCategoryActionpropertyService.insert(deviceCategoryActionproperty);
            LOGGER.info("用户【{}】添加设备分类绑定操作属性表数据【{}】成功", new Object[] { this.getUserId(), deviceCategoryActionproperty } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加设备分类绑定操作属性表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceCategoryActionproperty, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【设备分类绑定操作属性表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer ctgyActpId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ctgyActpId", ctgyActpId); 
        model.addAttribute("deviceCategoryActionproperty", deviceCategoryActionpropertyService.selectObject(map));
        return "device/categoryActionproperty.edit";
    }
    
    /**
     * 修改【设备分类绑定操作属性表】数据
     * @param deviceCategoryActionproperty
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editDeviceCategoryActionproperty(@ModelAttribute DeviceCategoryActionproperty deviceCategoryActionproperty, BindingResult result) {
        try {
            deviceCategoryActionpropertyService.update(deviceCategoryActionproperty);
            LOGGER.info("用户【{}】修改设备分类绑定操作属性表数据【{}】成功", new Object[] { this.getUserId(), deviceCategoryActionproperty } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改设备分类绑定操作属性表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceCategoryActionproperty, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【设备分类绑定操作属性表】数据
     * @param ctgyActpId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteDeviceCategoryActionproperty(Integer ctgyActpId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("ctgyActpId", ctgyActpId); 
            deviceCategoryActionpropertyService.delete(map);
            LOGGER.info("用户【{}】删除设备分类绑定操作属性表数据【{}】成功", new Object[] { this.getUserId(), ctgyActpId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除设备分类绑定操作属性表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), ctgyActpId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 根据设备分类id获取设备操作属性
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "getActionByCategoryId", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO getActionByCategoryId(Integer categoryId) {
    	//返回结果级
        List<Map<String,Object>> actionList = null;   
        try {
        	//查询
        	actionList = deviceCategoryActionpropertyService.getActionByCategoryId(categoryId);
            LOGGER.info("用户【{}】获取操作属性列表成功", new Object[] { this.getUserId(), categoryId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】获取操作属性列表失败 Exception:【{}】", new Object[] { this.getUserId(), categoryId, e });
            return ResultTO.newFailResultTO("获取操作属性列表失败", null);
        }
        return ResultTO.newSuccessResultTO("获取操作属性列表成功", actionList);
    }
}
