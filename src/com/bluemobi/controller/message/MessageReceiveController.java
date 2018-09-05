package com.bluemobi.controller.message;
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
import com.bluemobi.po.message.MessageReceive;
import com.bluemobi.service.message.MessageReceiveService;
import com.bluemobi.to.ResultTO;



/**
 * 【消息接收表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("messageReceive")
public class MessageReceiveController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageReceiveController.class);
    
    @Autowired
    private MessageReceiveService messageReceiveService;
    

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
     * 进入【消息接收表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "message/receive.index";
    }
    
    /**
     * 分页查询【消息接收表】
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
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = messageReceiveService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【消息接收表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer receiveId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("receiveId", receiveId); 
        model.addAttribute("messageReceive", messageReceiveService.selectObject(map));
        return "message/receive.detail";
    }
    
    /**
     * 进入新增【消息接收表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "message/receive.edit";
    }
    
    /**
     * 新增【消息接收表】数据
     * @param messageReceive
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addMessageReceive(@ModelAttribute MessageReceive messageReceive, BindingResult result) {
        try {
            messageReceiveService.insert(messageReceive);
            LOGGER.info("用户【{}】添加消息接收表数据【{}】成功", new Object[] { this.getUserId(), messageReceive } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加消息接收表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), messageReceive, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【消息接收表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer receiveId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("receiveId", receiveId); 
        model.addAttribute("messageReceive", messageReceiveService.selectObject(map));
        return "message/receive.edit";
    }
    
    /**
     * 修改【消息接收表】数据
     * @param messageReceive
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editMessageReceive(@ModelAttribute MessageReceive messageReceive, BindingResult result) {
        try {
            messageReceiveService.update(messageReceive);
            LOGGER.info("用户【{}】修改消息接收表数据【{}】成功", new Object[] { this.getUserId(), messageReceive } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改消息接收表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), messageReceive, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【消息接收表】数据
     * @param receiveId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteMessageReceive(Integer receiveId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("receiveId", receiveId); 
            messageReceiveService.delete(map);
            LOGGER.info("用户【{}】删除消息接收表数据【{}】成功", new Object[] { this.getUserId(), receiveId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除消息接收表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), receiveId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    
}
