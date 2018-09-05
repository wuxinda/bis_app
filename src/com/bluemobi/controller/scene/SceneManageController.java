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
import com.bluemobi.po.scene.SceneManage;
import com.bluemobi.service.device.DeviceCategoryService;
import com.bluemobi.service.scene.SceneDeviceLinkageService;
import com.bluemobi.service.scene.SceneManageService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;



/**
 * 【场景模式管理表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
@Controller
@RequestMapping("sceneManage")
public class SceneManageController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SceneManageController.class);
    
    @Autowired
    private SceneManageService sceneManageService;
    @Autowired
    private DeviceCategoryService deviceCategoryService;
    
    @Autowired
    private WmsStoreService wmsStoreService;
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
     * 进入【场景模式管理表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "scene/manage.index";
    }
    
    /**
     * 分页查询【场景模式管理表】
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
        Page<Map<String, Object>> page = sceneManageService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【场景模式管理表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer sceneId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("sceneId", sceneId); 
        model.addAttribute("sceneManage", sceneManageService.selectObject(map));
        return "scene/manage.detail";
    }
    
    /**
     * 进入新增【场景模式管理表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        //查询所有库房
        model.addAttribute("wmsStore", wmsStoreService.selectObjectList(new HashMap<String, Object>()));
        return "scene/manage.edit";
    }
    
    /**
     * 新增【场景模式管理表】数据
     * @param sceneManage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addSceneManage(@ModelAttribute SceneManage sceneManage, BindingResult result) {
        try {
        	//设置创建人，创建时间
        	sceneManage.setUserId(this.getUserId());
        	sceneManage.setCreator(this.getUserId());
        	sceneManage.setCtime(new Date());
        	sceneManage.setModifier(this.getUserId());
        	sceneManage.setMtime(new Date());
            sceneManageService.insert(sceneManage);
            LOGGER.info("用户【{}】添加场景模式管理表数据【{}】成功", new Object[] { this.getUserId(), sceneManage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加场景模式管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), sceneManage, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【场景模式管理表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer sceneId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sceneId", sceneId); 
        model.addAttribute("sceneManage", sceneManageService.selectObject(map));
        //查询所有库房
        model.addAttribute("wmsStore", wmsStoreService.selectObjectList(new HashMap<String, Object>()));
        return "scene/manage.edit";
    }
    
    /**
     * 修改【场景模式管理表】数据
     * @param sceneManage
     * @return ResultTO
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editSceneManage(@ModelAttribute SceneManage sceneManage, BindingResult result) {
        try {
        	//设置修改人和修改时间
        	sceneManage.setModifier(this.getUserId());
        	sceneManage.setMtime(new Date());
            sceneManageService.update(sceneManage);
            LOGGER.info("用户【{}】修改场景模式管理表数据【{}】成功", new Object[] { this.getUserId(), sceneManage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改场景模式管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), sceneManage, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【场景模式管理表】数据
     * @param sceneId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-01
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteSceneManage(Integer sceneId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("sceneId", sceneId); 
            sceneManageService.delete(map);
            LOGGER.info("用户【{}】删除场景模式管理表数据【{}】成功", new Object[] { this.getUserId(), sceneId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除场景模式管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), sceneId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 进入场景模式设备联动配置界面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "deviceLinkageJump", method = RequestMethod.GET)
    public String deviceLinkageJump(Model model, Integer sceneId) {
        // 加载公共数据
        initIndex(model);
        //查询场景详情
        Map<String, Object> mapLink = new HashMap<String, Object>();
        mapLink.put("sceneId", sceneId); 
        //查询场景对应的联动设备列表
        model.addAttribute("linkageList", sceneDeviceLinkageService.selectObjectList(mapLink));
        //查询所有库房
        model.addAttribute("wmsStore", wmsStoreService.selectObjectList(new HashMap<String, Object>()));
        //查询场景详情
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sceneId", sceneId); 
        model.addAttribute("sceneManage", sceneManageService.selectObject(map));
        return "scene/manage.edit.device";
    }

    /**
     * 编辑场景模式对应的联动设备
     * @param actionpropertyIds 规则:deviceId.actionpropertyId,deviceId.actionpropertyId
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "editSceneLinkage", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editSceneLinkage(String linkageActionIds, Integer sceneId) {
		try {
			if(linkageActionIds.length()<1||null==linkageActionIds||null==sceneId)
				return ResultTO.newFailResultTO("请选择需要联动的设备", null);
			//更新绑定
			sceneManageService.updateSceneLinkage(linkageActionIds, sceneId);;
			LOGGER.info("用户【{}】添加场景模式对应的联动设备数据【{}】成功", new Object[] {
					this.getUserId(), sceneId, linkageActionIds });
		} catch (Exception e) {
			LOGGER.error("用户【{}】添加场景模式对应的联动设备【{}】失败 Exception:【{}】", new Object[] {
					this.getUserId(), sceneId, linkageActionIds, e });
			return ResultTO.newFailResultTO("添加场景模式对应的联动设备失败", null);
		}
		return ResultTO.newSuccessResultTO("添加场景模式对应的联动设备成功", null);
	}
    
    /**
     * 执行场景模式
     * @param sceneId
     * @return
     */
    @RequestMapping(value = "executeScene", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO executeScene(Integer sceneId ){
		// 执行结果 0成功 1失败
		int result = 1;
		try {
			if(null==sceneId)
				return ResultTO.newFailResultTO("请选择需要执行的场景模式", null);
			//执行场景模式
			result = sceneManageService.executeScene(sceneId);
			if(result==0){
				LOGGER.info("用户【{}】执行场景模式【{}】异常，请检查设备配置是否正确或网络是否正常", new Object[] {
						this.getUserId(), sceneId });
				return ResultTO.newFailResultTO("执行场景模式失败", result);
			}

		} catch (Exception e) {
			LOGGER.error("用户【{}】执行场景模式【{}】失败 Exception:【{}】", new Object[] {
					this.getUserId(), sceneId, e });
			return ResultTO.newFailResultTO("执行场景模式失败", result);
		}
		LOGGER.info("用户【{}】执行场景模式【{}】成功", new Object[] {
				this.getUserId(), sceneId });
		return ResultTO.newSuccessResultTO("执行场景模式成功", result);
    }
}
