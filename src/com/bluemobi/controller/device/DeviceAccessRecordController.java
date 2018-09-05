package com.bluemobi.controller.device;
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

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.dao.device.DeviceAccessRecordDao;
import com.bluemobi.po.device.DeviceAccessRecord;
import com.bluemobi.service.device.DeviceAccessRecordService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;



/**
 * 【】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
@Controller
@RequestMapping("deviceAccessRecord")
public class DeviceAccessRecordController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceAccessRecordController.class);
    
    @Autowired
    private DeviceAccessRecordService deviceAccessRecordService;
    @Autowired
    private DeviceAccessRecordDao deviceAccessRecordDao;
    

    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/accessRecord.index";
    }
    
    /**
     * 分页查询【】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = deviceAccessRecordService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer recordId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("recordId", recordId); 
        model.addAttribute("deviceAccessRecord", deviceAccessRecordService.selectObject(map));
        return "device/accessRecord.detail";
    }
    
    /**
     * 进入新增【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "device/accessRecord.edit";
    }
    
    /**
     * 新增【】数据
     * @param deviceAccessRecord
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addDeviceAccessRecord(@ModelAttribute DeviceAccessRecord deviceAccessRecord, BindingResult result) {
        try {
            deviceAccessRecordService.insert(deviceAccessRecord);
            LOGGER.info("用户【{}】添加数据【{}】成功", new Object[] { this.getUserId(), deviceAccessRecord } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceAccessRecord, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer recordId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("recordId", recordId); 
        model.addAttribute("deviceAccessRecord", deviceAccessRecordService.selectObject(map));
        return "device/accessRecord.edit";
    }
    
    /**
     * 修改【】数据
     * @param deviceAccessRecord
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editDeviceAccessRecord(@ModelAttribute DeviceAccessRecord deviceAccessRecord, BindingResult result) {
        try {
            deviceAccessRecordService.update(deviceAccessRecord);
            LOGGER.info("用户【{}】修改数据【{}】成功", new Object[] { this.getUserId(), deviceAccessRecord } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), deviceAccessRecord, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【】数据
     * @param recordId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-12
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteDeviceAccessRecord(Integer recordId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("recordId", recordId); 
            deviceAccessRecordService.delete(map);
            LOGGER.info("用户【{}】删除数据【{}】成功", new Object[] { this.getUserId(), recordId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), recordId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 获取门禁数据列表
     */
    @RequestMapping(value = "getAccessDataList", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getAccessDataList(HttpServletRequest request) {
    	Integer pageIndex=Integer.parseInt(RequestParamUtil.getEncodeParam(request, "pageIndex"))-1;
    	Integer pageSize=Integer.parseInt(RequestParamUtil.getEncodeParam(request, "pageSize"));
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("offset", pageIndex*pageSize);
    	map.put("rows", pageSize);
    	List<Map<String,Object>> list=deviceAccessRecordService.getAccessDataList(map);
    	Map<String,Object> data=new HashMap<String,Object>();
    	data.put("data",list);
    	int count=deviceAccessRecordDao.pageCount(map);
    	data.put("count", count);
		return ResultTO.newSuccessResultTO(data);
    }
    /**
     * 获取门禁数据统计
     */
    @RequestMapping(value = "getAccessDataCount", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getAccessDataCount() {
    	Map<String,Object> map=new HashMap<String,Object>();
    	map.put("type", 1);
    	List<Map<String,Object>> year=deviceAccessRecordService.getAccessDataCount(map);
    	map.put("type", 2);
    	List<Map<String,Object>> month=deviceAccessRecordService.getAccessDataCount(map);
    	map.put("type", 3);
    	List<Map<String,Object>> day=deviceAccessRecordService.getAccessDataCount(map);
    	Map<String,Object> list=new HashMap<String,Object>();
    	list.put("year", year);
    	list.put("month", month);
    	list.put("day", day);
		return ResultTO.newSuccessResultTO(list);
    	
    }
}
