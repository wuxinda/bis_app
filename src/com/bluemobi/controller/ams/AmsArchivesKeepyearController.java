package com.bluemobi.controller.ams;

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
import com.bluemobi.po.ams.AmsArchivesKeepyear;
import com.bluemobi.service.ams.AmsArchivesKeepyearService;
import com.bluemobi.to.ResultTO;

/**
 * 【档案保管年限 】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-05
 * 
 */
@Controller
@RequestMapping("amsArchivesKeepyear")
public class AmsArchivesKeepyearController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesKeepyearController.class);

    @Autowired
    private AmsArchivesKeepyearService amsArchivesKeepyearService;

    /**
     * 将请求参数中的字符串转换成日期格式
     * 
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 请求参数中的日期字符串格式
	CustomDateEditor editor = new CustomDateEditor(df, false);
	binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 进入【档案保管年限 】主页
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
	// 加载公共数据
	initIndex(model);
	return "ams/archivesKeepyear.index";
    }
   
    @RequestMapping(value="/keepyear",method=RequestMethod.GET)
    @ResponseBody
    public  ResultTO getKeepyearList(Map<String, Object> map){
    	System.out.println(amsArchivesKeepyearService.selectObjectList(null));
		return ResultTO.newSuccessResultTO("成功", amsArchivesKeepyearService.selectObjectList(null));	
    }
    
    /**
     * 分页查询【档案保管年限 】
     * 
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
	Page<Map<String, Object>> page = amsArchivesKeepyearService.page(map, pageIndex, pageSize);
	return page;
    }

    /**
     * 查询【档案保管年限 】详情
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer amsArchivesKeepyear) {
	// 加载公共数据
	initIndex(model);
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("amsArchivesKeepyear", amsArchivesKeepyear);
	model.addAttribute("amsArchivesKeepyear", amsArchivesKeepyearService.selectObject(map));
	return "ams/archivesKeepyear.detail";
    }

    /**
     * 进入新增【档案保管年限 】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
	// 加载公共数据
	initIndex(model);
	return "ams/archivesKeepyear.edit";
    }

    /**
     * 新增【档案保管年限 】数据
     * 
     * @param amsArchivesKeepyear
     * @return ResultTO
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAmsArchivesKeepyear(@ModelAttribute AmsArchivesKeepyear amsArchivesKeepyear, BindingResult result) {
	try {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortOrder", amsArchivesKeepyear.getSortOrder());
		List<Map<String, Object>> amsArchivesTypes = amsArchivesKeepyearService.selectMapList(map);
		if (amsArchivesTypes.size() > 0) {
			return ResultTO.newFailResultTO("添加失败,code已存在", null);
		}
	    amsArchivesKeepyearService.insert(amsArchivesKeepyear);
	    LOGGER.info("用户【{}】添加档案保管年限数据【{}】成功", new Object[] { this.getUserId(), amsArchivesKeepyear });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】添加档案保管年限数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesKeepyear, e });
	    return ResultTO.newFailResultTO("添加失败", null);
	}
	return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 进入修改【档案保管年限 】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer amsArchivesKeepyear) {
	// 加载公共数据
	initIndex(model);
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("amsArchivesKeepyear", amsArchivesKeepyear);
	model.addAttribute("amsArchivesKeepyear", amsArchivesKeepyearService.selectObject(map));
	return "ams/archivesKeepyear.edit";
    }

    /**
     * 修改【档案保管年限 】数据
     * 
     * @param amsArchivesKeepyear
     * @return ResultTO
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAmsArchivesKeepyear(@ModelAttribute AmsArchivesKeepyear amsArchivesKeepyear, BindingResult result) {
        try {
        	Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortOrder", amsArchivesKeepyear.getSortOrder());
			List<Map<String, Object>> amsArchivesTypes = amsArchivesKeepyearService.selectMapList(map);
			if (amsArchivesTypes.size() > 0) {
				return ResultTO.newFailResultTO("添加失败,code已存在", null);
			}
            amsArchivesKeepyearService.update(amsArchivesKeepyear);
            LOGGER.info("用户【{}】修改档案保管年限数据【{}】成功", new Object[] { this.getUserId(), amsArchivesKeepyear } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改档案保管年限数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesKeepyear, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }

    /**
     * 删除【档案保管年限 】数据
     * 
     * @param amsArchivesKeepyear
     * @return ResultTO
     * @author AutoCode
     * @date 2017-05
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAmsArchivesKeepyear(Integer amsArchivesKeepyear) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("amsArchivesKeepyear", amsArchivesKeepyear); 
            amsArchivesKeepyearService.delete(map);
            LOGGER.info("用户【{}】删除档案保管年限数据【{}】成功", new Object[] { this.getUserId(), amsArchivesKeepyear });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除档案保管年限数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesKeepyear, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
}
