package com.bluemobi.controller.ams;
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
import com.bluemobi.po.ams.AmsArchivesActlog;
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;



/**
 * 【档案操作纪录表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("amsArchivesActlog")
public class AmsArchivesActlogController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesActlogController.class);
    
    @Autowired
    private AmsArchivesActlogService amsArchivesActlogService;
    

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
     * 进入【档案操作纪录表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ams/archivesActlog.index";
    }
    
    /**
     * 分页查询【档案操作纪录表】
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
    public Page<Map<String, Object>> page(@ModelAttribute AmsArchivesActlog amsArchivesActlog,String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "".equals(amsArchivesActlog.getType()) ? null:amsArchivesActlog.getType());
        map.put("status", "".equals(amsArchivesActlog.getStatus()) ? null:amsArchivesActlog.getStatus());
        Page<Map<String, Object>> page = amsArchivesActlogService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【档案操作纪录表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer actlogId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("actlogId", actlogId); 
        model.addAttribute("amsArchivesActlog", amsArchivesActlogService.selectObject(map));
        return "ams/archivesActlog.detail";
    }
    
    /**
     * 进入新增【档案操作纪录表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ams/archivesActlog.edit";
    }
    
    /**
     * 新增【档案操作纪录表】数据
     * @param amsArchivesActlog
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAmsArchivesActlog(@ModelAttribute AmsArchivesActlog amsArchivesActlog, BindingResult result) {
    	amsArchivesActlog.setCreator(this.getUserId());
    	amsArchivesActlog.setCtime(DateUtil.getCurrentDate());
    	amsArchivesActlog.setStatus("0");
    	amsArchivesActlog.setUserId(this.getUserId());
        try {
            amsArchivesActlogService.insert(amsArchivesActlog);
            LOGGER.info("用户【{}】添加档案操作纪录表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesActlog } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加档案操作纪录表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesActlog, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【档案操作纪录表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer actlogId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("actlogId", actlogId); 
        model.addAttribute("amsArchivesActlog", amsArchivesActlogService.selectObject(map));
        return "ams/archivesActlog.edit";
    }
    
    /**
     * 修改【档案操作纪录表】数据
     * @param amsArchivesActlog
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAmsArchivesActlog(@ModelAttribute AmsArchivesActlog amsArchivesActlog, BindingResult result) {
        try {
            amsArchivesActlogService.update(amsArchivesActlog);
            LOGGER.info("用户【{}】修改档案操作纪录表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesActlog } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改档案操作纪录表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesActlog, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【档案操作纪录表】数据
     * @param actlogId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAmsArchivesActlog(Integer actlogId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("actlogId", actlogId); 
            amsArchivesActlogService.delete(map);
            LOGGER.info("用户【{}】删除档案操作纪录表数据【{}】成功", new Object[] { this.getUserId(), actlogId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除档案操作纪录表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), actlogId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
