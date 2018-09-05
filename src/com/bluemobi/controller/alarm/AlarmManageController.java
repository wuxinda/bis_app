package com.bluemobi.controller.alarm;
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
import com.bluemobi.po.alarm.AlarmManage;
import com.bluemobi.service.alarm.AlarmManageService;
import com.bluemobi.service.device.DeviceCategoryService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;



/**
 * 【报警管理表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("alarmManage")
public class AlarmManageController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmManageController.class);
    
    @Autowired
    private AlarmManageService alarmManageService;
    @Autowired
    private WmsStoreService wmsStoreService;
    @Autowired
    private DeviceCategoryService deviceCategoryService;
    

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
     * 进入【报警管理表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        //设备类型
        model.addAttribute("categoryId", deviceCategoryService.selectMapList(null));
        //库房
        model.addAttribute("wmsStore", wmsStoreService.selectWmsStore());
        return "alarm/manage.index";
    }
    
    /**
     * 分页查询【报警管理表】
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
    public Page<Map<String, Object>> page(@ModelAttribute AlarmManage alarmManage, String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("categoryId", "".equals(alarmManage.getCategoryId())?null:alarmManage.getCategoryId());
        map.put("storeId", "".equals(alarmManage.getStoreId())?null:alarmManage.getStoreId());
        map.put("stroreAreaId", "".equals(alarmManage.getStroreAreaId())?null:alarmManage.getStroreAreaId());
        map.put("level", "".equals(alarmManage.getLevel())?null:alarmManage.getLevel());
        map.put("status", "".equals(alarmManage.getStatus())?null:alarmManage.getStatus());
        Page<Map<String, Object>> page = alarmManageService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【报警管理表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer alarmId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("alarmId", alarmId); 
        model.addAttribute("alarmManage", alarmManageService.selectObject(map));
        return "alarm/manage.detail";
    }
    
    /**
     * 进入新增【报警管理表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "alarm/manage.edit";
    }
    
    /**
     * 新增【报警管理表】数据
     * @param alarmManage
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAlarmManage(@ModelAttribute AlarmManage alarmManage, BindingResult result) {
        try {
            alarmManageService.insert(alarmManage);
            LOGGER.info("用户【{}】添加报警管理表数据【{}】成功", new Object[] { this.getUserId(), alarmManage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加报警管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmManage, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【报警管理表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer alarmId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alarmId", alarmId); 
        model.addAttribute("alarmManage", alarmManageService.selectObject(map));
        return "alarm/manage.edit";
    }
    
    /**
     * 修改【报警管理表】数据
     * @param alarmManage
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAlarmManage(@ModelAttribute AlarmManage alarmManage, BindingResult result) {
        try {
            alarmManageService.update(alarmManage);
            LOGGER.info("用户【{}】修改报警管理表数据【{}】成功", new Object[] { this.getUserId(), alarmManage } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改报警管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmManage, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【报警管理表】数据
     * @param alarmId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAlarmManage(Integer alarmId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("alarmId", alarmId); 
            alarmManageService.delete(map);
            LOGGER.info("用户【{}】删除报警管理表数据【{}】成功", new Object[] { this.getUserId(), alarmId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除报警管理表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), alarmId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 报警统计
     * 
     */
    @RequestMapping(value = "getAlarmCount")
    @ResponseBody
    public ResultTO getAlarmCount(HttpServletRequest request) {
    	List<Map<String, Object>> list=alarmManageService.getAlarmCount();
	return ResultTO.newSuccessResultTO("请求成功", list);

    }
    
    /**
     * 查询设备异常报警信息
     * @param request
     * @return
     */
    @RequestMapping(value="selectAlarmManageByDevice")
    @ResponseBody
    public ResultTO selectAlarmManageByDevice(HttpServletRequest request) throws Exception{
    	Map<String,Object> map=new HashMap<>();
    	List<Integer> alarmTypes=new ArrayList<>();
    	alarmTypes.add(6);
    	map.put("alarmType", alarmTypes);
    	List<Map<String,Object>> list=alarmManageService.selectAlarmManageByDevice(map);
    	map.put("list", list);
    	map.put("count", list.size());
    	return ResultTO.newSuccessResultTO(map);
    }
    
    /**
     * 查询环境异常报警信息
     * @param request
     * @return
     */
    @RequestMapping(value="selectAlarmManageByEnvironment")
    @ResponseBody
    public ResultTO selectAlarmManageByEnvironment(HttpServletRequest request) throws Exception{
    	Map<String,Object> map=new HashMap<>();
    	List<Integer> alarmTypes=new ArrayList<>();
    	alarmTypes.add(2);//温度超上限
    	alarmTypes.add(3);//温度低下限
    	alarmTypes.add(4);//湿度超上限
    	alarmTypes.add(5);//湿度低下限
    	alarmTypes.add(6);//测试数据 可删除
    	map.put("alarmType", alarmTypes);
    	List<Map<String,Object>> list=alarmManageService.selectAlarmManageByDevice(map);
    	map.put("list", list);
    	map.put("count", list.size());
    	return ResultTO.newSuccessResultTO(map);
    }
}
