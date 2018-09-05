package com.bluemobi.controller.device;
import java.util.HashMap;
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
import com.bluemobi.po.device.DeviceActionproperty;
import com.bluemobi.service.device.DeviceActionpropertyService;
import com.bluemobi.to.ResultTO;



/**
 * 【设备操作属性表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("deviceActionproperty")
public class DeviceActionpropertyController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceActionpropertyController.class);
    
    @Autowired
    private DeviceActionpropertyService deviceActionpropertyService;
    

    
    /**
     * 进入【设备操作属性表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/actionproperty.index";
    }
    
    /**
     * 分页查询【设备操作属性表】
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
        Page<Map<String, Object>> page = deviceActionpropertyService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【设备操作属性表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer actionpropertyId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("actionpropertyId", actionpropertyId); 
        model.addAttribute("deviceActionproperty", deviceActionpropertyService.selectObject(map));
        return "device/actionproperty.detail";
    }
    
    /**
     * 进入新增【设备操作属性表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/actionproperty.edit";
    }
    
    /**
     * 新增【设备操作属性表】数据
     * @param deviceActionproperty
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addDeviceActionproperty(@ModelAttribute DeviceActionproperty deviceActionproperty, BindingResult result) {
        try {
            deviceActionpropertyService.insert(deviceActionproperty);
            LOGGER.info("用户【{}】添加设备操作属性表数据【{}】成功", new Object[] { this.getUserId(), deviceActionproperty } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加设备操作属性表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceActionproperty, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【设备操作属性表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer actionpropertyId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("actionpropertyId", actionpropertyId); 
        model.addAttribute("deviceActionproperty", deviceActionpropertyService.selectObject(map));
        return "device/actionproperty.edit";
    }
    
    /**
     * 修改【设备操作属性表】数据
     * @param deviceActionproperty
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editDeviceActionproperty(@ModelAttribute DeviceActionproperty deviceActionproperty, BindingResult result) {
        try {
            deviceActionpropertyService.update(deviceActionproperty);
            LOGGER.info("用户【{}】修改设备操作属性表数据【{}】成功", new Object[] { this.getUserId(), deviceActionproperty } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改设备操作属性表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceActionproperty, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【设备操作属性表】数据
     * @param actionpropertyId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteDeviceActionproperty(Integer actionpropertyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("actionpropertyId", actionpropertyId); 
            deviceActionpropertyService.delete(map);
            LOGGER.info("用户【{}】删除设备操作属性表数据【{}】成功", new Object[] { this.getUserId(), actionpropertyId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除设备操作属性表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), actionpropertyId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
