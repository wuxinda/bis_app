package com.bluemobi.controller.wms;
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
import com.bluemobi.po.wms.WmsUser;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.service.wms.WmsUserService;
import com.bluemobi.to.ResultTO;



/**
 * 【库房用户表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-07
 * 
 */
@Controller
@RequestMapping("wmsUser")
public class WmsUserController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WmsUserController.class);
    
    @Autowired
    private WmsUserService wmsUserService;
    @Autowired
    private WmsStoreService wmsStoreService;
    @Autowired
    private AdminUserService adminUserService;
    

    
    /**
     * 进入【库房用户表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "wms/user.index";
    }
    
    /**
     * 分页查询【库房用户表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = wmsUserService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【库房用户表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer wmsUserId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("wmsUserId", wmsUserId); 
        model.addAttribute("wmsUser", wmsUserService.selectObject(map));
        return "wms/user.detail";
    }
    
    /**
     * 进入新增【库房用户表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "wms/user.edit";
    }
    
    /**
     * 新增【库房用户表】数据
     * @param wmsUser
     * @return ResultTO
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addWmsUser(@ModelAttribute WmsUser wmsUser, BindingResult result) {
        try {
            wmsUserService.insert(wmsUser);
            LOGGER.info("用户【{}】添加库房用户表数据【{}】成功", new Object[] { this.getUserId(), wmsUser } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加库房用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsUser, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【库房用户表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer wmsUserId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wmsUserId", wmsUserId); 
        model.addAttribute("wmsUser", wmsUserService.selectObject(map));
        return "wms/user.edit";
    }
    
    /**
     * 修改【库房用户表】数据
     * @param wmsUser
     * @return ResultTO
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editWmsUser(@ModelAttribute WmsUser wmsUser, BindingResult result) {
        try {
            wmsUserService.update(wmsUser);
            LOGGER.info("用户【{}】修改库房用户表数据【{}】成功", new Object[] { this.getUserId(), wmsUser } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改库房用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsUser, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【库房用户表】数据
     * @param wmsUserId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteWmsUser(Integer wmsUserId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("wmsUserId", wmsUserId); 
            wmsUserService.delete(map);
            LOGGER.info("用户【{}】删除库房用户表数据【{}】成功", new Object[] { this.getUserId(), wmsUserId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除库房用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsUserId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 进入分配库房页面管理员
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "setWmsUser", method = RequestMethod.GET)
    public String setWmsUser(Model model, String storeId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", Integer.parseInt(storeId)); 
        model.addAttribute("wmsUser", wmsUserService.selectWmsAllUser(map));
        
        model.addAttribute("storeId", Integer.parseInt(storeId));
        model.addAttribute("storeName", wmsStoreService.selectObject(map));
        //获取所有用户
        model.addAttribute("allUser", adminUserService.selectObjectList(null));
        //根据库房id获取所有选中和未选中的所有人员
        return "wms/user.edit";
    }
    
    /**
     * 进入分配库房页面管理员
     * @param wmsUser
     * @return ResultTO
     * @author AutoCode
     * @date 2017-07
     */
    @RequestMapping(value = "setWmsUser", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO setWmsUser(@ModelAttribute WmsUser wmsUser, BindingResult result) {
        try {
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("storeId", wmsUser.getStoreId()); 
        	List<WmsUser> list = wmsUserService.selectObjectList(map);
        	//删除本库房之前所有
        	for(WmsUser po:list){
        		wmsUserService.delete(po.getWmsUserId());
        	}
        	
        	String[] userids = wmsUser.getUserIds();
        	//添加新设置人员
        	if(userids!=null&&userids.length>0){
        		for(String userId :userids){
            		WmsUser newwmsUser = new WmsUser();
            		newwmsUser.setStoreId(wmsUser.getStoreId());
            		newwmsUser.setUserId(Integer.parseInt(userId));
            		wmsUserService.insert(newwmsUser);
            	}
        	}
            LOGGER.info("用户【{}】修改库房用户表数据【{}】成功", new Object[] { this.getUserId(), wmsUser } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改库房用户表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsUser, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
}
