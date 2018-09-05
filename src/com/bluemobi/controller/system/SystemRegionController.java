package com.bluemobi.controller.system;
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
import com.bluemobi.po.system.SystemRegion;
import com.bluemobi.service.system.SystemRegionService;
import com.bluemobi.to.ResultTO;



/**
 * 【地区表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Controller
@RequestMapping("systemRegion")
public class SystemRegionController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemRegionController.class);
    
    @Autowired
    private SystemRegionService systemRegionService;
    

    
    /**
     * 进入【地区表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "system/region.index";
    }
    
    /**
     * 分页查询【地区表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = systemRegionService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【地区表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", id); 
        model.addAttribute("systemRegion", systemRegionService.selectObject(map));
        return "system/region.detail";
    }
    
    /**
     * 进入新增【地区表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "system/region.edit";
    }
    
    /**
     * 新增【地区表】数据
     * @param systemRegion
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addSystemRegion(@ModelAttribute SystemRegion systemRegion, BindingResult result) {
        try {
            systemRegionService.insert(systemRegion);
            LOGGER.info("用户【{}】添加地区表数据【{}】成功", new Object[] { this.getUserId(), systemRegion } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加地区表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), systemRegion, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【地区表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id); 
        model.addAttribute("systemRegion", systemRegionService.selectObject(map));
        return "system/region.edit";
    }
    
    /**
     * 修改【地区表】数据
     * @param systemRegion
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editSystemRegion(@ModelAttribute SystemRegion systemRegion, BindingResult result) {
        try {
            systemRegionService.update(systemRegion);
            LOGGER.info("用户【{}】修改地区表数据【{}】成功", new Object[] { this.getUserId(), systemRegion } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改地区表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), systemRegion, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【地区表】数据
     * @param id
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteSystemRegion(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", id); 
            systemRegionService.delete(map);
            LOGGER.info("用户【{}】删除地区表数据【{}】成功", new Object[] { this.getUserId(), id });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除地区表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), id, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
    /**
     * 分页查询【地区表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO list(String key) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pid", key);
        List<Map<String, Object>> page = systemRegionService.selectObjectList(map);
        return ResultTO.newSuccessResultTO("查询成功", page);
    }
}
