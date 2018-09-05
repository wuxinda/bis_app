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
import com.bluemobi.po.wms.WmsStoreArea;
import com.bluemobi.service.wms.WmsStoreAreaService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;



/**
 * 【库房分区表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("wmsStoreArea")
public class WmsStoreAreaController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(WmsStoreAreaController.class);
    
    @Autowired
    private WmsStoreAreaService wmsStoreAreaService;
    @Autowired
    private WmsStoreService wmsStoreService;

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
     * 进入【库房分区表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "wms/storeArea.index";
    }
    
    /**
     * 分页查询【库房分区表】
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
        Page<Map<String, Object>> page = wmsStoreAreaService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【库房分区表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer stroreAreaId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("stroreAreaId", stroreAreaId); 
        model.addAttribute("wmsStoreArea", wmsStoreAreaService.selectObject(map));
        return "wms/storeArea.detail";
    }
    
    /**
     * 进入新增【库房分区表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
      //获取库房列表
        model.addAttribute("wmsStore",wmsStoreService.selectWmsStore());
        return "wms/storeArea.edit";
    }
    
    /**
     * 新增【库房分区表】数据
     * @param wmsStoreArea
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addWmsStoreArea(@ModelAttribute WmsStoreArea wmsStoreArea, BindingResult result) {
    	//设置添加人和添加时间
    	wmsStoreArea.setCreator(this.getUserId());
    	wmsStoreArea.setCtime(DateUtil.getCurrentDate());
        try {
            wmsStoreAreaService.insert(wmsStoreArea);
            LOGGER.info("用户【{}】添加库房分区表数据【{}】成功", new Object[] { this.getUserId(), wmsStoreArea } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加库房分区表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsStoreArea, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【库房分区表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer stroreAreaId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stroreAreaId", stroreAreaId); 
        model.addAttribute("wmsStoreArea", wmsStoreAreaService.selectObject(map));
        //获取库房列表
        model.addAttribute("wmsStore",wmsStoreService.selectWmsStore());
        return "wms/storeArea.edit";
    }
    
    /**
     * 修改【库房分区表】数据
     * @param wmsStoreArea
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editWmsStoreArea(@ModelAttribute WmsStoreArea wmsStoreArea, BindingResult result) {
    	//设置添加人和添加时间
    	wmsStoreArea.setModifier(this.getUserId());
    	wmsStoreArea.setMtime(DateUtil.getCurrentDate());
        try {
            wmsStoreAreaService.update(wmsStoreArea);
            LOGGER.info("用户【{}】修改库房分区表数据【{}】成功", new Object[] { this.getUserId(), wmsStoreArea } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改库房分区表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), wmsStoreArea, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【库房分区表】数据
     * @param stroreAreaId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteWmsStoreArea(Integer stroreAreaId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("stroreAreaId", stroreAreaId); 
            wmsStoreAreaService.delete(map);
            LOGGER.info("用户【{}】删除库房分区表数据【{}】成功", new Object[] { this.getUserId(), stroreAreaId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除库房分区表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), stroreAreaId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 根据库房获取库区
     */
    @RequestMapping(value = "getStoreAreaByStoreId", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getStoreAreaByStoreId(Integer storeId){
	List<WmsStoreArea> list = wmsStoreAreaService.getStoreAreaByStoreId(storeId);
	return ResultTO.newSuccessResultTO("获取成功", list);
    }
}
