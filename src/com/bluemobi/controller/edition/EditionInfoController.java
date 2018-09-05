package com.bluemobi.controller.edition;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.bluemobi.conf.Config;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.edition.EditionInfo;
import com.bluemobi.po.message.MessageInfo;
import com.bluemobi.service.edition.EditionInfoService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.MsgServiceUtil;



/**
 * 【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】控制器
 * @author AutoCode 309444359@qq.com
 * @date 2017-06
 */
@Controller("editionInfoController")
@RequestMapping("editionInfo")
public class EditionInfoController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(EditionInfoController.class);
    
    @Autowired
    private EditionInfoService editionInfoService;
    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-06
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//请求参数中的日期字符串格式
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 进入【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "edition/info.index";
    }
    
    /**
     * 分页查询【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = editionInfoService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer editionId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("editionId", editionId); 
        model.addAttribute("editionInfo", editionInfoService.selectObject(map));
        return "edition/info.detail";
    }
    /**
     * 查询【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】详情
     * 用于查询回调单个对象的信息
     * @param model
     * @return string
     * @author toki
     * @date 2017-09
     */
    @RequestMapping(value = "detailById", method = RequestMethod.GET)
    @ResponseBody
    public ResultTO detailById(Model model, Integer editionId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("editionId", editionId); 
        EditionInfo edition=editionInfoService.selectObject(map);
        edition.setEditionUrl(Config.TEMP_IMG_URL+edition.getEditionUrl());	
        return ResultTO.newFailResultTO(edition);
    }
    /**
     * 进入新增【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "edition/info.edit";
    }
    
    /**
     * 新增【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】数据
     * @param editionInfo
     * @return ResultTO
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addEditionInfo(@ModelAttribute EditionInfo editionInfo, BindingResult result,@RequestParam(value = "editionUrl", required = false) MultipartFile[] avatar) {
   	  System.out.print(avatar);
   	Map<String, Object> maps = new HashMap<String, Object>();
   	maps.put("editionType", editionInfo.getEditionType());
	maps.put("sunType", editionInfo.getSunType());
   	maps.put("editionNumber", editionInfo.getEditionNumber());
	List<Map<String, Object>> resultss = editionInfoService.selectObjectList(maps);
	if(resultss.size()>0){
		 return ResultTO.newFailResultTO("添加失败,版本号已存在", null);
	}
   	if (avatar != null && avatar.length > 0) {
		// 上传图片方法
		Map<String, Object> uploadResultMap = uploadImage(avatar,
				BaseConstant.USER_AVATAR_IMAGE);
		if (uploadResultMap != null
				&& (Boolean) uploadResultMap.get("flag")) {
			editionInfo.setEditionUrl(uploadResultMap.get("imageUrl").toString());
		}
	}  	  
        try {
        	editionInfo.setEditionCreator(this.getUserId());
        	editionInfo.setEditionCreatetime(new Date());
        	if(editionInfo.getIsPublish()==0){
        		Map<String, Object> map = new HashMap<String, Object>();
                map.put("isPublish", 0); 
            	map.put("editionType", editionInfo.getEditionType());
            	map.put("sunType", editionInfo.getSunType());
            	List<Map<String, Object>> results = editionInfoService.selectObjectList(map);
            	if(results.size()>0){
            		 return ResultTO.newFailResultTO("添加失败,只能有一个发布版本", null);
            	}
            	
        	}
            editionInfoService.insert(editionInfo);
            if(editionInfo.getIsPublish()==0){
            	MessageInfo messageInfo = new MessageInfo();
            	messageInfo.setContent(this.getUserName() + "发布了新版本，版本号："+editionInfo.getEditionNumber());
            	messageInfo.setCtime(new Date());
            	messageInfo.setIsDel(0);
            	messageInfo.setIsPush(1);
            	messageInfo.setType(1);
            	messageInfo.setTitle("版本发布");
            	//推送给所有用户，并处理关系
            	MsgServiceUtil.messageService(messageInfo);
            }
            LOGGER.info("用户【{}】添加对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】成功", new Object[] { this.getUserId(), editionInfo } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), editionInfo, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer editionId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("editionId", editionId); 
        model.addAttribute("editionInfo", editionInfoService.selectObject(map));
        return "edition/info.edit";
    }
    
    /**
     * 修改【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】数据
     * @param editionInfo
     * @return ResultTO
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editEditionInfo(@ModelAttribute EditionInfo editionInfo, BindingResult result) {
        try {
        	Map<String, Object> maps = new HashMap<String, Object>();
        	maps.put("editionType", editionInfo.getEditionType());
        	maps.put("sunType", editionInfo.getSunType());
           	maps.put("editionNumber", editionInfo.getEditionNumber());
        	List<EditionInfo> resultss = editionInfoService.selectObjectList(maps);
        	resultss.remove(editionInfo);
        	if(resultss.size()>0){
        		 return ResultTO.newFailResultTO("更新失败,版本号已存在", null);
        	}
        	editionInfo.setEditionUpdater(this.getUserId());
        	editionInfo.setEditionCreatetime(new Date());
        	if(editionInfo.getIsPublish()==0){
        		Map<String, Object> map = new HashMap<String, Object>();
                map.put("isPublish", 0); 
                map.put("editionType", editionInfo.getEditionType());
            	map.put("sunType", editionInfo.getSunType());
            	List<EditionInfo> results = editionInfoService.selectObjectList(map);
            	if(results.size()>0&&(!String.valueOf(results.get(0).getEditionId()).equals(String.valueOf(editionInfo.getEditionId())))){
            		 return ResultTO.newFailResultTO("更新失败,只能有一个发布版本", null);
            	}
        	}
            editionInfoService.update(editionInfo);
            if(editionInfo.getIsPublish()==0){
            	MessageInfo messageInfo = new MessageInfo();
            	messageInfo.setContent(this.getUserName() + "更新了新版本，版本号："+editionInfo.getEditionNumber());
            	messageInfo.setCtime(new Date());
            	messageInfo.setIsDel(0);
            	messageInfo.setIsPush(1);
            	messageInfo.setType(1);
            	messageInfo.setTitle("版本发布");
            	//推送给所有用户，并处理关系
            	MsgServiceUtil.messageService(messageInfo);
            }
            LOGGER.info("用户【{}】修改对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】成功", new Object[] { this.getUserId(), editionInfo } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), editionInfo, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】数据
     * @param editionId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteEditionInfo(Integer editionId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("editionId", editionId); 
            editionInfoService.delete(map);
            LOGGER.info("用户【{}】删除对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】成功", new Object[] { this.getUserId(), editionId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), editionId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 发布版本
     * @param editionId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-06
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publishEditionInfo(String editionId,String isPublish) {
    	ResultTO  to = null;
    	try {  		
    		EditionInfo editionInfo = editionInfoService.selectObject(editionId);   		
    		editionInfo.setIsPublish(Integer.parseInt(isPublish));
    		to = this.EditionInfo(editionInfo,null);
    		LOGGER.info("用户【{}】发布/取消发布【{}】成功", new Object[] { this.getUserId(), editionId });
    	} catch (Exception e) {
    		LOGGER.error("用户【{}】发布/取消发布【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), editionId, e });
    		return ResultTO.newFailResultTO(to.getMsg(), null);
    	}
    	return ResultTO.newSuccessResultTO(to.getMsg(), null);
    }


public ResultTO EditionInfo(@ModelAttribute EditionInfo editionInfo, BindingResult result) {
    try {
    	Map<String, Object> maps = new HashMap<String, Object>();
    	maps.put("editionType", editionInfo.getEditionType());
    	maps.put("sunType", editionInfo.getSunType());
       	maps.put("editionNumber", editionInfo.getEditionNumber());
    	List<EditionInfo> resultss = editionInfoService.selectObjectList(maps);
    	resultss.remove(editionInfo);
    	if(resultss.size()>0){
    		 return ResultTO.newFailResultTO("操作失败,版本号已存在", null);
    	}
    	editionInfo.setEditionUpdater(this.getUserId());
    	editionInfo.setEditionCreatetime(new Date());
    	if(editionInfo.getIsPublish()==0){
    		Map<String, Object> map = new HashMap<String, Object>();
            map.put("isPublish", 0); 
            map.put("editionType", editionInfo.getEditionType());
        	map.put("sunType", editionInfo.getSunType());
        	List<EditionInfo> results = editionInfoService.selectObjectList(map);
        	if(results.size()>0&&(!String.valueOf(results.get(0).getEditionId()).equals(String.valueOf(editionInfo.getEditionId())))){
        		 return ResultTO.newFailResultTO("操作失败,该应用类型只能有一个发布版本", null);
        	}
    	}
        editionInfoService.update(editionInfo);
        if(editionInfo.getIsPublish()==0){
        	MessageInfo messageInfo = new MessageInfo();
        	messageInfo.setContent(this.getUserName() + "发布了新版本，版本号："+editionInfo.getEditionNumber());
        	messageInfo.setCtime(new Date());
        	messageInfo.setIsDel(0);
        	messageInfo.setIsPush(1);
        	messageInfo.setType(1);
        	messageInfo.setTitle("版本发布");
        	//推送给所有用户，并处理关系
        	MsgServiceUtil.messageService(messageInfo);
        }
        LOGGER.info("用户【{}】修改对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】成功", new Object[] { this.getUserId(), editionInfo } );
    } catch (Exception e) {
        LOGGER.error("用户【{}】修改对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), editionInfo, e });
        return ResultTO.newFailResultTO("操作失败", null);
    }
    return ResultTO.newSuccessResultTO("操作成功", null);
}
}