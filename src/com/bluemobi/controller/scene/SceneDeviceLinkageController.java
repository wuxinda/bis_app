package com.bluemobi.controller.scene;
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
import com.bluemobi.po.scene.SceneDeviceLinkage;
import com.bluemobi.service.scene.SceneDeviceLinkageService;
import com.bluemobi.to.ResultTO;



/**
 * 【场景模式设备联动表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
@Controller
@RequestMapping("sceneDeviceLinkage")
public class SceneDeviceLinkageController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SceneDeviceLinkageController.class);
    
    @Autowired
    private SceneDeviceLinkageService sceneDeviceLinkageService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【场景模式设备联动表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "scene/deviceLinkage.index";
    }
    
    /**
     * 分页查询【场景模式设备联动表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = sceneDeviceLinkageService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【场景模式设备联动表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer linkageId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("linkageId", linkageId); 
        model.addAttribute("sceneDeviceLinkage", sceneDeviceLinkageService.selectObject(map));
        return "scene/deviceLinkage.detail";
    }
    
    /**
     * 进入新增【场景模式设备联动表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "scene/deviceLinkage.edit";
    }
    
    /**
     * 新增【场景模式设备联动表】数据
     * @param sceneDeviceLinkage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addSceneDeviceLinkage(@ModelAttribute SceneDeviceLinkage sceneDeviceLinkage, BindingResult result) {
        try {
            sceneDeviceLinkageService.insert(sceneDeviceLinkage);
            LOGGER.info("用户【{}】添加场景模式设备联动表数据【{}】成功", new Object[] { this.getUserId(), sceneDeviceLinkage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加场景模式设备联动表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), sceneDeviceLinkage, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【场景模式设备联动表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer linkageId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("linkageId", linkageId); 
        model.addAttribute("sceneDeviceLinkage", sceneDeviceLinkageService.selectObject(map));
        return "scene/deviceLinkage.edit";
    }
    
    /**
     * 修改【场景模式设备联动表】数据
     * @param sceneDeviceLinkage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editSceneDeviceLinkage(@ModelAttribute SceneDeviceLinkage sceneDeviceLinkage, BindingResult result) {
        try {
            sceneDeviceLinkageService.update(sceneDeviceLinkage);
            LOGGER.info("用户【{}】修改场景模式设备联动表数据【{}】成功", new Object[] { this.getUserId(), sceneDeviceLinkage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改场景模式设备联动表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), sceneDeviceLinkage, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【场景模式设备联动表】数据
     * @param linkageId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteSceneDeviceLinkage(Integer linkageId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("linkageId", linkageId); 
            sceneDeviceLinkageService.delete(map);
            LOGGER.info("用户【{}】删除场景模式设备联动表数据【{}】成功", new Object[] { this.getUserId(), linkageId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除场景模式设备联动表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), linkageId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
