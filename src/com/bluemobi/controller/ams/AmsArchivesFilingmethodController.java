package com.bluemobi.controller.ams;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesFilingmethod;
import com.bluemobi.service.ams.AmsArchivesFilingmethodService;
import com.bluemobi.to.ResultTO;



/**
 * 【档案立卷方式表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-05
 * 
 */
@Controller
@RequestMapping("amsArchivesFilingmethod")
public class AmsArchivesFilingmethodController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesFilingmethodController.class);
    
    @Autowired
    private AmsArchivesFilingmethodService amsArchivesFilingmethodService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【档案立卷方式表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ams/archivesFilingmethod.index";
    }
    
     
    /**
     * 获取立卷方式列表
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "filingmethod", method = RequestMethod.GET)
    public ResultTO getFilingmethodList(Map<String, Object> map) {
    	List<AmsArchives> list = new ArrayList<AmsArchives>();
    	try {
    	    list = amsArchivesFilingmethodService.selectObjectList(null);
    	    LOGGER.info("用户【{}】获取立卷方式数据【{}】成功", new Object[] { this.getUserId(), null });
    	} catch (Exception e) {
    	    LOGGER.error("用户【{}】获取立卷方式表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), null, e });
    	    return ResultTO.newFailResultTO("获取失败", null);
    	}
    	return ResultTO.newSuccessResultTO("获取成功", list);
        }
    
    /**
     * 分页查询【档案立卷方式表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = amsArchivesFilingmethodService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【档案立卷方式表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer archivesFilingmethodId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("archivesFilingmethodId", archivesFilingmethodId); 
        model.addAttribute("amsArchivesFilingmethod", amsArchivesFilingmethodService.selectObject(map));
        return "ams/archivesFilingmethod.detail";
    }
    
    /**
     * 进入新增【档案立卷方式表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ams/archivesFilingmethod.edit";
    }
    
    /**
     * 新增【档案立卷方式表】数据
     * @param amsArchivesFilingmethod
     * @return ResultTO
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAmsArchivesFilingmethod(@ModelAttribute AmsArchivesFilingmethod amsArchivesFilingmethod, BindingResult result) {
        try {
        	Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortOrder", amsArchivesFilingmethod.getSortOrder());
			List<Map<String, Object>> amsArchivesTypes = amsArchivesFilingmethodService.selectMapList(map);
			if (amsArchivesTypes.size() > 0) {
				return ResultTO.newFailResultTO("添加失败,code已存在", null);
			}
            amsArchivesFilingmethodService.insert(amsArchivesFilingmethod);
            LOGGER.info("用户【{}】添加档案立卷方式表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesFilingmethod } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加档案立卷方式表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesFilingmethod, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【档案立卷方式表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer archivesFilingmethodId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("archivesFilingmethodId", archivesFilingmethodId); 
        model.addAttribute("amsArchivesFilingmethod", amsArchivesFilingmethodService.selectObject(map));
        return "ams/archivesFilingmethod.edit";
    }
    
    /**
     * 修改【档案立卷方式表】数据
     * @param amsArchivesFilingmethod
     * @return ResultTO
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAmsArchivesFilingmethod(@ModelAttribute AmsArchivesFilingmethod amsArchivesFilingmethod, BindingResult result) {
        try {
        	Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortOrder", amsArchivesFilingmethod.getSortOrder());
			List<Map<String, Object>> amsArchivesTypes = amsArchivesFilingmethodService.selectMapList(map);
			if (amsArchivesTypes.size() > 0) {
				return ResultTO.newFailResultTO("添加失败,code存在", null);
			}
            amsArchivesFilingmethodService.update(amsArchivesFilingmethod);
            LOGGER.info("用户【{}】修改档案立卷方式表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesFilingmethod } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改档案立卷方式表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesFilingmethod, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【档案立卷方式表】数据
     * @param archivesFilingmethodId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAmsArchivesFilingmethod(Integer archivesFilingmethodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("archivesFilingmethodId", archivesFilingmethodId); 
            amsArchivesFilingmethodService.delete(map);
            LOGGER.info("用户【{}】删除档案立卷方式表数据【{}】成功", new Object[] { this.getUserId(), archivesFilingmethodId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除档案立卷方式表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), archivesFilingmethodId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
