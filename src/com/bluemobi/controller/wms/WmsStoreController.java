package com.bluemobi.controller.wms;
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
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.tk.mybatis.mapper.wms.WmsStoreMapper;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;



/**
 * 【库房表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("wmsStore")
public class WmsStoreController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WmsStoreController.class);
    
    @Autowired
    private WmsStoreService wmsStoreService;
    
    @Autowired
    private WmsStoreMapper wmsStoreMapper;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【库房表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "wms/store.index";
    }
    
    /**
     * 分页查询【库房表】
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
        Page<Map<String, Object>> page = wmsStoreService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【库房表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer storeId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("storeId", storeId); 
        WmsStore wmsStore=new WmsStore();
//        wmsStore.
        wmsStore=wmsStoreMapper.selectByPrimaryKey(10);
      model.addAttribute("wmsStore", wmsStore);
//      model.addAttribute("wmsStore", wmsStoreService.selectObject(map));
        return "wms/store.detail";
    }
    
    /**
     * 进入新增【库房表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "wms/store.edit";
    }
    
    /**
     * 新增【库房表】数据
     * @param wmsStore
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addWmsStore(@ModelAttribute WmsStore wmsStore, BindingResult result) {
    	//库房创建人
    	wmsStore.setCreator(this.getUserId());
    	//库房创建时间
    	wmsStore.setCtime(DateUtil.getCurrentDate());
        try {
            wmsStoreService.insert(wmsStore);
            LOGGER.info("用户【{}】添加库房表数据【{}】成功", new Object[] { this.getUserId(), wmsStore } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加库房表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsStore, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【库房表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer storeId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId); 
        model.addAttribute("wmsStore", wmsStoreService.selectObject(map));
        return "wms/store.edit";
    }
    
    /**
     * 修改【库房表】数据
     * @param wmsStore
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editWmsStore(@ModelAttribute WmsStore wmsStore, BindingResult result) {
    	//修改人
    	wmsStore.setModifier(this.getUserId());
    	//修改时间
    	wmsStore.setMtime(DateUtil.getCurrDate());
        try {
            wmsStoreService.update(wmsStore);
            LOGGER.info("用户【{}】修改库房表数据【{}】成功", new Object[] { this.getUserId(), wmsStore } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改库房表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsStore, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【库房表】数据
     * @param storeId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteWmsStore(Integer storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("storeId", storeId); 
            wmsStoreService.delete(map);
            LOGGER.info("用户【{}】删除库房表数据【{}】成功", new Object[] { this.getUserId(), storeId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除库房表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), storeId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 获取库房列表
     * @return
     */
    @RequestMapping(value = "getWmsStore", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getWmsStore() {
	List<WmsStore> list = wmsStoreService.selectWmsStore();
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
}
