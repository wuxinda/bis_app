package com.bluemobi.controller.ecs;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.bluemobi.po.ecs.EcsManage;
import com.bluemobi.service.ecs.EcsManageService;
import com.bluemobi.to.ResultTO;



/**
 * 【】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
@Controller
@RequestMapping("ecsManage")
public class EcsManageController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EcsManageController.class);
    
    @Autowired
    private EcsManageService ecsManageService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ecs/manage.index";
    }
    
    /**
     * 分页查询【】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = ecsManageService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer ecsManageId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("ecsManageId", ecsManageId); 
        model.addAttribute("ecsManage", ecsManageService.selectObject(map));
        return "ecs/manage.detail";
    }
    
    /**
     * 进入新增【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ecs/manage.edit";
    }
    
    /**
     * 新增【】数据
     * @param ecsManage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addEcsManage(@ModelAttribute EcsManage ecsManage, BindingResult result) {
        try {
            ecsManageService.insert(ecsManage);
            LOGGER.info("用户【{}】添加数据【{}】成功", new Object[] { this.getUserId(), ecsManage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), ecsManage, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer ecsManageId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ecsManageId", ecsManageId); 
        model.addAttribute("ecsManage", ecsManageService.selectObject(map));
        return "ecs/manage.edit";
    }
    
    /**
     * 修改【】数据
     * @param ecsManage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editEcsManage(@ModelAttribute EcsManage ecsManage, BindingResult result) {
        try {
            ecsManageService.update(ecsManage);
            LOGGER.info("用户【{}】修改数据【{}】成功", new Object[] { this.getUserId(), ecsManage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), ecsManage, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【】数据
     * @param ecsManageId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteEcsManage(Integer ecsManageId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("ecsManageId", ecsManageId); 
            ecsManageService.delete(map);
            LOGGER.info("用户【{}】删除数据【{}】成功", new Object[] { this.getUserId(), ecsManageId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), ecsManageId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
