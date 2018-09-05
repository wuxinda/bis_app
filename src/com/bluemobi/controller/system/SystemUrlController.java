package com.bluemobi.controller.system;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.bluemobi.po.system.SystemUrl;
import com.bluemobi.service.system.SystemUrlService;
import com.bluemobi.to.ResultTO;



/**
 * 【请求url，对应的请求名，用于通过url显示用户操作】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
@Controller
@RequestMapping("systemUrl")
public class SystemUrlController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemUrlController.class);
    
    @Autowired
    private SystemUrlService systemUrlService;
    

    
    /**
     * 进入【请求url，对应的请求名，用于通过url显示用户操作】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "system/url.index";
    }
    
    /**
     * 分页查询【请求url，对应的请求名，用于通过url显示用户操作】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(SystemUrl systemUrl, int pageSize, int pageIndex,HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //name当成url参数处理 前端url参数有冲突
    	map.put("url", "".equals(systemUrl.getName()) ? null : systemUrl.getName());
    	map.put("urlType", "".equals(systemUrl.getUrlType()) ? null : systemUrl.getUrlType());
    	map.put("status", "".equals(systemUrl.getStatus()) ? null : systemUrl.getStatus());
        Page<Map<String, Object>> page = systemUrlService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【请求url，对应的请求名，用于通过url显示用户操作】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("id", id); 
        model.addAttribute("systemUrl", systemUrlService.selectObject(map));
        return "system/url.detail";
    }
    
    /**
     * 进入新增【请求url，对应的请求名，用于通过url显示用户操作】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "system/url.edit";
    }
    
    /**
     * 新增【请求url，对应的请求名，用于通过url显示用户操作】数据
     * @param systemUrl
     * @return ResultTO
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addSystemUrl(@ModelAttribute SystemUrl systemUrl, BindingResult result) {
        try {
            systemUrlService.insert(systemUrl);
            LOGGER.info("用户【{}】添加请求url，对应的请求名，用于通过url显示用户操作数据【{}】成功", new Object[] { this.getUserId(), systemUrl } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加请求url，对应的请求名，用于通过url显示用户操作数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), systemUrl, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【请求url，对应的请求名，用于通过url显示用户操作】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer id) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id); 
        model.addAttribute("systemUrl", systemUrlService.selectObject(map));
        return "system/url.edit";
    }
    
    /**
     * 修改【请求url，对应的请求名，用于通过url显示用户操作】数据
     * @param systemUrl
     * @return ResultTO
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editSystemUrl(@ModelAttribute SystemUrl systemUrl, BindingResult result) {
        try {
            systemUrlService.update(systemUrl);
            LOGGER.info("用户【{}】修改请求url，对应的请求名，用于通过url显示用户操作数据【{}】成功", new Object[] { this.getUserId(), systemUrl } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改请求url，对应的请求名，用于通过url显示用户操作数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), systemUrl, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【请求url，对应的请求名，用于通过url显示用户操作】数据
     * @param id
     * @return ResultTO
     * @author AutoCode
     * @date 2016-09
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteSystemUrl(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("id", id); 
            systemUrlService.delete(map);
            LOGGER.info("用户【{}】删除请求url，对应的请求名，用于通过url显示用户操作数据【{}】成功", new Object[] { this.getUserId(), id });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除请求url，对应的请求名，用于通过url显示用户操作数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), id, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
