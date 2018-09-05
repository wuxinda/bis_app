package com.bluemobi.controller.message;

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
import com.bluemobi.po.message.MessageInfo;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.message.MessageInfoService;
import com.bluemobi.service.message.MessageReceiveService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.MsgServiceUtil;

/**
 * 【消息表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("messageInfo")
public class MessageInfoController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageInfoController.class);

    @Autowired
    private MessageInfoService messageInfoService;
    @Autowired
    private MessageReceiveService messageReceiveService;
    @Autowired
    private AdminUserService adminUserService;

    /**
     * 将请求参数中的字符串转换成日期格式
     * 
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 请求参数中的日期字符串格式
	CustomDateEditor editor = new CustomDateEditor(df, false);
	binder.registerCustomEditor(Date.class, editor);
    }

    /**
     * 进入【消息表】主页
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
	// 加载公共数据
	initIndex(model);
	return "message/info.index";
    }

    /**
     * 分页查询【消息表】
     * 
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
	Page<Map<String, Object>> page = messageInfoService.page(map, pageIndex, pageSize);
	return page;
    }

    /**
     * 查询【消息表】详情
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer messageId) {
	// 加载公共数据
	initIndex(model);
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("messageId", messageId);
	model.addAttribute("messageInfo", messageInfoService.selectObject(map));
	return "message/info.detail";
    }

    /**
     * 进入新增【消息表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
	// 加载公共数据
	initIndex(model);
	return "message/info.edit";
    }

    /**
     * 新增【消息表】数据
     * 
     * @param messageInfo
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addMessageInfo(@ModelAttribute MessageInfo messageInfo, BindingResult result) {
	try {
		Integer res = MsgServiceUtil.messageService(messageInfo);
		if(res!=null&&res==0){
			return ResultTO.newFailResultTO("添加失败", null);
		}
	    LOGGER.info("用户【{}】添加消息表数据【{}】成功", new Object[] { this.getUserId(), messageInfo });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】添加消息表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), messageInfo, e });
	    return ResultTO.newFailResultTO("添加失败", null);
	}
	return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 进入修改【消息表】页面
     * 
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer messageId) {
	// 加载公共数据
	initIndex(model);
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("messageId", messageId);
	model.addAttribute("messageInfo", messageInfoService.selectObject(map));
	return "message/info.edit";
    }

    /**
     * 修改【消息表】数据
     * 
     * @param messageInfo
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editMessageInfo(@ModelAttribute MessageInfo messageInfo, BindingResult result) {
	try {
	    messageInfoService.update(messageInfo);
	 // 处理消息,推送到所有在线用户，保存到接收表中关联所有领导用户
	    List<Map<String, Object>> UserList = adminUserService.selectMapList(null);
	    List<Integer> userIds = new ArrayList<Integer>();
	    for (Map<String, Object> list : UserList) {
		userIds.add((Integer.parseInt(String.valueOf(list.get("userId")))));
	    }
	    // 用户接收消息跟新
	    Map<String, Object> parmUp = new HashMap<String, Object>();
	    parmUp.put("messageId", messageInfo.getMessageId());
	    parmUp.put("isDel", 0);
	    parmUp.put("status", 0);
	    parmUp.put("mtime", new Date());
	    parmUp.put("userId", userIds);
	    messageReceiveService.addMessageReceives(parmUp);
	    // 如果需要推送就推送给所有在线用户
	    if (messageInfo.getIsPush().equals("1"))
		MsgServiceUtil.sendPush(messageInfo.getType()+":"+messageInfo.getTitle());
	    LOGGER.info("用户【{}】修改消息表数据【{}】成功", new Object[] { this.getUserId(), messageInfo });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】修改消息表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), messageInfo, e });
	    return ResultTO.newFailResultTO("更新失败", null);
	}
	return ResultTO.newSuccessResultTO("更新成功", null);
    }

    /**
     * 删除【消息表】数据
     * 
     * @param messageId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteMessageInfo(Integer messageId) {
	Map<String, Object> map = new HashMap<String, Object>();
	try {
	    map.put("messageId", messageId);
	    messageInfoService.delete(map);
	    LOGGER.info("用户【{}】删除消息表数据【{}】成功", new Object[] { this.getUserId(), messageId });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】删除消息表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), messageId, e });
	    return ResultTO.newFailResultTO("删除失败", null);
	}
	return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 获取消息
     * 
     * @param userId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-08
     */
    @RequestMapping(value = "getMessageList", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getUserList(Map<String, Object> map) {
	List<MessageInfo> list = new ArrayList<MessageInfo>();
	try {
	    list = messageInfoService.selectObjectList(map);
	    LOGGER.info("用户【{}】获取消息表数据【{}】成功", new Object[] { this.getUserId(), null });
	} catch (Exception e) {
	    LOGGER.error("用户【{}】获取消息表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), null, e });
	    return ResultTO.newFailResultTO("获取失败", null);
	}
	return ResultTO.newSuccessResultTO("获取成功", list);
    }

}
