package com.bluemobi.controller.alarm;
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
import com.bluemobi.po.alarm.AlarmHcsdeviceConfig;
import com.bluemobi.service.alarm.AlarmHcsdeviceConfigService;
import com.bluemobi.to.ResultTO;



/**
 * 【温湿度设备报警配置表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
@Controller
@RequestMapping("alarmHcsdeviceConfig")
public class AlarmHcsdeviceConfigController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmHcsdeviceConfigController.class);
    
    @Autowired
    private AlarmHcsdeviceConfigService alarmHcsdeviceConfigService;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【温湿度设备报警配置表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/hcsdeviceConfig.index";
    }
    
    /**
     * 分页查询【温湿度设备报警配置表】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = alarmHcsdeviceConfigService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【温湿度设备报警配置表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer alarmDevconfId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("alarmDevconfId", alarmDevconfId); 
        model.addAttribute("alarmHcsdeviceConfig", alarmHcsdeviceConfigService.selectObject(map));
        return "alarm/hcsdeviceConfig.detail";
    }
    
    /**
     * 进入新增【温湿度设备报警配置表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/hcsdeviceConfig.edit";
    }
    
    /**
     * 新增【温湿度设备报警配置表】数据
     * @param alarmHcsdeviceConfig
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAlarmHcsdeviceConfig(@ModelAttribute AlarmHcsdeviceConfig alarmHcsdeviceConfig, BindingResult result) {
        try {
            alarmHcsdeviceConfigService.insert(alarmHcsdeviceConfig);
            LOGGER.info("用户【{}】添加温湿度设备报警配置表数据【{}】成功", new Object[] { this.getUserId(), alarmHcsdeviceConfig } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加温湿度设备报警配置表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmHcsdeviceConfig, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【温湿度设备报警配置表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer alarmDevconfId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alarmDevconfId", alarmDevconfId); 
        model.addAttribute("alarmHcsdeviceConfig", alarmHcsdeviceConfigService.selectObject(map));
        return "alarm/hcsdeviceConfig.edit";
    }
    
    /**
     * 修改【温湿度设备报警配置表】数据
     * @param alarmHcsdeviceConfig
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAlarmHcsdeviceConfig(@ModelAttribute AlarmHcsdeviceConfig alarmHcsdeviceConfig, BindingResult result) {
        try {
            alarmHcsdeviceConfigService.update(alarmHcsdeviceConfig);
            LOGGER.info("用户【{}】修改温湿度设备报警配置表数据【{}】成功", new Object[] { this.getUserId(), alarmHcsdeviceConfig } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改温湿度设备报警配置表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmHcsdeviceConfig, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【温湿度设备报警配置表】数据
     * @param alarmDevconfId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAlarmHcsdeviceConfig(Integer alarmDevconfId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("alarmDevconfId", alarmDevconfId); 
            alarmHcsdeviceConfigService.delete(map);
            LOGGER.info("用户【{}】删除温湿度设备报警配置表数据【{}】成功", new Object[] { this.getUserId(), alarmDevconfId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除温湿度设备报警配置表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmDevconfId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
