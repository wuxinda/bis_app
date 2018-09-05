package com.bluemobi.controller.alarm;
import java.util.HashMap;
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
import com.bluemobi.po.alarm.AlarmMessageConfig;
import com.bluemobi.service.alarm.AlarmMessageConfigService;
import com.bluemobi.to.ResultTO;



/**
 * 【报警消息推送规则配置表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
@Controller
@RequestMapping("alarmMessageConfig")
public class AlarmMessageConfigController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmMessageConfigController.class);
    
    @Autowired
    private AlarmMessageConfigService alarmMessageConfigService;
    

    
    /**
     * 进入【报警消息推送规则配置表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/messageConfig.index";
    }
    
    /**
     * 分页查询【报警消息推送规则配置表】
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
        Page<Map<String, Object>> page = alarmMessageConfigService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【报警消息推送规则配置表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer almmsgcfgId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("almmsgcfgId", almmsgcfgId); 
        model.addAttribute("alarmMessageConfig", alarmMessageConfigService.selectObject(map));
        return "alarm/messageConfig.detail";
    }
    
    /**
     * 进入新增【报警消息推送规则配置表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/messageConfig.edit";
    }
    
    /**
     * 新增【报警消息推送规则配置表】数据
     * @param alarmMessageConfig
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAlarmMessageConfig(@ModelAttribute AlarmMessageConfig alarmMessageConfig, BindingResult result) {
        try {
            alarmMessageConfigService.insert(alarmMessageConfig);
            LOGGER.info("用户【{}】添加报警消息推送规则配置表数据【{}】成功", new Object[] { this.getUserId(), alarmMessageConfig } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加报警消息推送规则配置表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmMessageConfig, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【报警消息推送规则配置表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer almmsgcfgId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("almmsgcfgId", almmsgcfgId); 
        model.addAttribute("alarmMessageConfig", alarmMessageConfigService.selectObject(map));
        return "alarm/messageConfig.edit";
    }
    
    /**
     * 修改【报警消息推送规则配置表】数据
     * @param alarmMessageConfig
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAlarmMessageConfig(@ModelAttribute AlarmMessageConfig alarmMessageConfig, BindingResult result) {
        try {
            alarmMessageConfigService.update(alarmMessageConfig);
            LOGGER.info("用户【{}】修改报警消息推送规则配置表数据【{}】成功", new Object[] { this.getUserId(), alarmMessageConfig } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改报警消息推送规则配置表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmMessageConfig, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【报警消息推送规则配置表】数据
     * @param almmsgcfgId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAlarmMessageConfig(Integer almmsgcfgId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("almmsgcfgId", almmsgcfgId); 
            alarmMessageConfigService.delete(map);
            LOGGER.info("用户【{}】删除报警消息推送规则配置表数据【{}】成功", new Object[] { this.getUserId(), almmsgcfgId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除报警消息推送规则配置表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), almmsgcfgId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
