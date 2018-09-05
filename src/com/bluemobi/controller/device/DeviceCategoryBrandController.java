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
import com.bluemobi.po.device.DeviceCategoryBrand;
import com.bluemobi.service.device.DeviceCategoryBrandService;
import com.bluemobi.to.ResultTO;



/**
 * 【设备分类绑定品牌表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("deviceCategoryBrand")
public class DeviceCategoryBrandController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceCategoryBrandController.class);
    
    @Autowired
    private DeviceCategoryBrandService deviceCategoryBrandService;
    

    
    /**
     * 进入【设备分类绑定品牌表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/categoryBrand.index";
    }
    
    /**
     * 分页查询【设备分类绑定品牌表】
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
        Page<Map<String, Object>> page = deviceCategoryBrandService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【设备分类绑定品牌表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer categoryBrandId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("categoryBrandId", categoryBrandId); 
        model.addAttribute("deviceCategoryBrand", deviceCategoryBrandService.selectObject(map));
        return "device/categoryBrand.detail";
    }
    
    /**
     * 进入新增【设备分类绑定品牌表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/categoryBrand.edit";
    }
    
    /**
     * 新增【设备分类绑定品牌表】数据
     * @param deviceCategoryBrand
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addDeviceCategoryBrand(@ModelAttribute DeviceCategoryBrand deviceCategoryBrand, BindingResult result) {
        try {
            deviceCategoryBrandService.insert(deviceCategoryBrand);
            LOGGER.info("用户【{}】添加设备分类绑定品牌表数据【{}】成功", new Object[] { this.getUserId(), deviceCategoryBrand } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加设备分类绑定品牌表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceCategoryBrand, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【设备分类绑定品牌表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer categoryBrandId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryBrandId", categoryBrandId); 
        model.addAttribute("deviceCategoryBrand", deviceCategoryBrandService.selectObject(map));
        return "device/categoryBrand.edit";
    }
    
    /**
     * 修改【设备分类绑定品牌表】数据
     * @param deviceCategoryBrand
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editDeviceCategoryBrand(@ModelAttribute DeviceCategoryBrand deviceCategoryBrand, BindingResult result) {
        try {
            deviceCategoryBrandService.update(deviceCategoryBrand);
            LOGGER.info("用户【{}】修改设备分类绑定品牌表数据【{}】成功", new Object[] { this.getUserId(), deviceCategoryBrand } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改设备分类绑定品牌表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceCategoryBrand, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【设备分类绑定品牌表】数据
     * @param categoryBrandId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteDeviceCategoryBrand(Integer categoryBrandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("categoryBrandId", categoryBrandId); 
            deviceCategoryBrandService.delete(map);
            LOGGER.info("用户【{}】删除设备分类绑定品牌表数据【{}】成功", new Object[] { this.getUserId(), categoryBrandId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除设备分类绑定品牌表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), categoryBrandId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
