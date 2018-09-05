package com.bluemobi.controller.log;
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
import com.bluemobi.po.log.LogUserAction;
import com.bluemobi.service.log.LogUserActionService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;



/**
 * 【用户行为日志表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-10 11:10:47
 * 
 */
@Controller
@RequestMapping("logUserAction")
public class LogUserActionController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUserActionController.class);
    
    @Autowired
    private LogUserActionService logUserActionService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【用户行为日志表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "log/userAction.index";
    }
    
    /**
     * 分页查询【用户行为日志表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(LogUserAction logUserAction,HttpServletRequest request, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
    	map.put("logType", "".equals(logUserAction.getLogType()) ? null : logUserAction.getLogType());
    	map.put("userid", "".equals(logUserAction.getUserid()) ? null : logUserAction.getUserid());
    	//acitonName当成url参数处理 前端url参数有冲突
    	map.put("url", "".equals(logUserAction.getActionName()) ? null : logUserAction.getActionName());
		//是否按时间统计，用于sql条件区分
        String islogTime = "1";
		//开始时间
		String startPayTime = RequestParamUtil.getEncodeParam(request, "startPayTime");
		if (StringUtils.isEmpty(startPayTime)) {
			islogTime = null;
		}
		//结束时间
		String endPayTime = RequestParamUtil.getEncodeParam(request, "endPayTime");
		if (StringUtils.isEmpty(endPayTime)) {
			islogTime = null;
		}
        map.put("startPayTime", startPayTime);
        map.put("endPayTime", endPayTime);
        map.put("logTime", islogTime);
        Page<Map<String, Object>> page = logUserActionService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【用户行为日志表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer logId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("logId", logId); 
        model.addAttribute("logUserAction", logUserActionService.selectObject(map));
        return "log/userAction.detail";
    }
    
    /**
     * 进入新增【用户行为日志表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "log/userAction.edit";
    }
    
    /**
     * 新增【用户行为日志表】数据
     * @param logUserAction
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addLogUserAction(@ModelAttribute LogUserAction logUserAction, BindingResult result) {
        try {
            logUserActionService.insert(logUserAction);
            LOGGER.info("用户【{}】添加用户行为日志表数据【{}】成功", new Object[] { this.getUserId(), logUserAction } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加用户行为日志表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), logUserAction, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【用户行为日志表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer logId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logId", logId); 
        model.addAttribute("logUserAction", logUserActionService.selectObject(map));
        return "log/userAction.edit";
    }
    
    /**
     * 修改【用户行为日志表】数据
     * @param logUserAction
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editLogUserAction(@ModelAttribute LogUserAction logUserAction, BindingResult result) {
        try {
            logUserActionService.update(logUserAction);
            LOGGER.info("用户【{}】修改用户行为日志表数据【{}】成功", new Object[] { this.getUserId(), logUserAction } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改用户行为日志表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), logUserAction, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【用户行为日志表】数据
     * @param logId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-03-10 11:10:47
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteLogUserAction(Integer logId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("logId", logId); 
            logUserActionService.delete(map);
            LOGGER.info("用户【{}】删除用户行为日志表数据【{}】成功", new Object[] { this.getUserId(), logId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除用户行为日志表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), logId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
