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
import com.bluemobi.po.alarm.AlarmType;
import com.bluemobi.service.alarm.AlarmMessageConfigService;
import com.bluemobi.service.alarm.AlarmTypeService;
import com.bluemobi.to.ResultTO;



/**
 * 【报警类型】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
@Controller
@RequestMapping("alarmType")
public class AlarmTypeController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmTypeController.class);
    
    @Autowired
    private AlarmTypeService alarmTypeService;
    @Autowired
    private AlarmMessageConfigService alarmMessageConfigService;
    

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
     * 进入【报警类型】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/type.index";
    }
    
    /**
     * 分页查询【报警类型】
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
        Page<Map<String, Object>> page = alarmTypeService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【报警类型】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer alarmTypeId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("alarmTypeId", alarmTypeId); 
        model.addAttribute("alarmType", alarmTypeService.selectObject(map));
        return "alarm/type.detail";
    }
    
    /**
     * 进入新增【报警类型】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/type.edit";
    }
    
    /**
     * 新增【报警类型】数据
     * @param alarmType
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAlarmType(@ModelAttribute AlarmType alarmType, BindingResult result) {
        try {
            alarmType.setCreator(this.getUserId());
            alarmType.setCtime(new Date());
            alarmType.setModifier(this.getUserId());
            alarmType.setMtime(new Date());
            alarmTypeService.insert(alarmType);
            LOGGER.info("用户【{}】添加报警类型数据【{}】成功", new Object[] { this.getUserId(), alarmType } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加报警类型数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmType, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【报警类型】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer alarmTypeId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alarmTypeId", alarmTypeId); 
        model.addAttribute("alarmType", alarmTypeService.selectObject(map));
        return "alarm/type.edit";
    }
    
    /**
     * 修改【报警类型】数据
     * @param alarmType
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAlarmType(@ModelAttribute AlarmType alarmType, BindingResult result) {
        try {
            alarmType.setModifier(this.getUserId());
            alarmType.setMtime(new Date());
            alarmTypeService.update(alarmType);
            LOGGER.info("用户【{}】修改报警类型数据【{}】成功", new Object[] { this.getUserId(), alarmType } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改报警类型数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmType, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【报警类型】数据
     * @param alarmTypeId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAlarmType(Integer alarmTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("alarmTypeId", alarmTypeId); 
            alarmTypeService.delete(map);
            LOGGER.info("用户【{}】删除报警类型数据【{}】成功", new Object[] { this.getUserId(), alarmTypeId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除报警类型数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmTypeId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 添加报警消息推送规则
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-03
     */
    @RequestMapping(value = "msgPushCfg", method = RequestMethod.GET)
    public String msgPushCfg(Model model, Integer alarmTypeId,String alarmTypeName) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("alarmType", alarmTypeId); 
        map.put("alarmTypeId", alarmTypeId); 
        model.addAttribute("alarmTypeId", alarmTypeId);
        model.addAttribute("alarmTypeName",alarmTypeService.selectObject(map)!=null?((AlarmType)alarmTypeService.selectObject(map)).getAlarmTypeName():"");
        model.addAttribute("alarmMessageConfig", alarmMessageConfigService.selectObjectList(map).size()>0?alarmMessageConfigService.selectObjectList(map).get(0):null);
        return "alarm/type.config";
    }
}
