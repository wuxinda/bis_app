package com.bluemobi.controller.ams;
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
import com.appcore.util.SessionManager;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.dao.admin.AdminUserDao;
import com.bluemobi.dao.ams.AmsArchivesAuditDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesAudit;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.ams.AmsArchivesAuditService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;
import com.bluemobi.util.MsgServiceUtil;
import com.bluemobi.util.RequestParamUtil;



/**
 * 【档案申请审批表】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Controller
@RequestMapping("amsArchivesAudit")
public class AmsArchivesAuditController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesAuditController.class);
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AmsArchivesAuditService amsArchivesAuditService;
    @Autowired
	private AmsArchivesAuditDao amsArchivesAuditDao;
    @Autowired
	private AdminUserDao adminUserDao;
    @Autowired
   	private AmsArchivesDao amsArchivesDao;
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
     * 进入【档案申请审批表】主页
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ams/archivesAudit.index";
    }
    
    /**
     * 分页查询【档案申请审批表】
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
    public Page<Map<String, Object>> page(@ModelAttribute AmsArchivesAudit amsArchivesAudit,String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "".equals(amsArchivesAudit.getType()) ? null:amsArchivesAudit.getType());
        map.put("status", "".equals(amsArchivesAudit.getStatus()) ? null:amsArchivesAudit.getStatus());
        Page<Map<String, Object>> page = amsArchivesAuditService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【档案申请审批表】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer auditId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("auditId", auditId); 
        model.addAttribute("amsArchivesAudit", amsArchivesAuditService.selectObject(map));
        return "ams/archivesAudit.detail";
    }
    
    /**
     * 进入新增【档案申请审批表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "ams/archivesAudit.edit";
    }
    
    /**
     * 新增【档案申请审批表】数据
     * @param amsArchivesAudit
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAmsArchivesAudit(@ModelAttribute AmsArchivesAudit amsArchivesAudit, BindingResult result) {
    	amsArchivesAudit.setCreator(this.getUserId());
    	amsArchivesAudit.setCtime(DateUtil.getCurrentDate());
    	amsArchivesAudit.setMtime(DateUtil.getCurrentDate());
    	amsArchivesAudit.setStatus("0");
    	amsArchivesAudit.setUserId(this.getUserId());
        try {
            amsArchivesAuditService.insert(amsArchivesAudit);
            LOGGER.info("用户【{}】添加档案申请审批表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesAudit } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加档案申请审批表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesAudit, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【档案申请审批表】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer auditId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("auditId", auditId); 
        model.addAttribute("amsArchivesAudit", amsArchivesAuditService.selectObject(map));
        return "ams/archivesAudit.edit";
    }
    
    /**
     * 修改【档案申请审批表】数据
     * @param amsArchivesAudit
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAmsArchivesAudit(@ModelAttribute AmsArchivesAudit amsArchivesAudit, BindingResult result) {
    	amsArchivesAudit.setModifier(this.getUserId());
    	amsArchivesAudit.setMtime(DateUtil.getCurrentDate());
        try {
            amsArchivesAuditService.update(amsArchivesAudit);
            LOGGER.info("用户【{}】修改档案申请审批表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesAudit } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改档案申请审批表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesAudit, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【档案申请审批表】数据
     * @param auditId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAmsArchivesAudit(Integer auditId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("auditId", auditId); 
            amsArchivesAuditService.delete(map);
            LOGGER.info("用户【{}】删除档案申请审批表数据【{}】成功", new Object[] { this.getUserId(), auditId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除档案申请审批表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), auditId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 查询最新档案操作纪录
     * 
     */
    @RequestMapping(value = "getAmsArchiveAudit")
    @ResponseBody
    public ResultTO getAmsArchiveAudit(HttpServletRequest request) {
    	List<Map<String, String>> list=amsArchivesAuditService.getAmsArchiveAudit();
	return ResultTO.newSuccessResultTO("请求成功", list);

    }
    /**
     * 查询最新档案操作纪录统计
     * 
     */
    @RequestMapping(value = "getAmsArchiveAuditCount")
    @ResponseBody
    public ResultTO getAmsArchiveAuditCount(HttpServletRequest request) {
    	Map<String, String> count=amsArchivesAuditService.getAmsArchiveAuditCount();
	return ResultTO.newSuccessResultTO("请求成功", count);

    }
    /**
     * 查询最新档案操作纪录统计
     * 
     */
    @RequestMapping(value = "getArchivesAuditCount")
    @ResponseBody
    public ResultTO getArchivesAuditCount(HttpServletRequest request) {
    	List<Map<String, String>> count=amsArchivesAuditService.getArchivesAuditCount();
	return ResultTO.newSuccessResultTO("请求成功", count);

    }
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    @RequestMapping(value = "getArchivesAuditList")
    @ResponseBody
    public ResultTO getArchivesAuditList(HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String,Object>();
    	String type=RequestParamUtil.getEncodeParam(request, "type");
    	if(type!=null) {
    		map.put("type",type);
    	}
    	String status=RequestParamUtil.getEncodeParam(request, "status");
    	if(status!=null) {
    		map.put("status",status);
    	}
    	String page=RequestParamUtil.getEncodeParam(request, "page");
    	if(page!=null) {
    		map.put("page",(Integer.parseInt(page)-1)*10);
    	}
    	String userId=RequestParamUtil.getEncodeParam(request, "userId");
    	if(userId!=null) {
    		map.put("userId",userId);
    		Map<String,Object> resMap=adminUserService.selectMap(userId);
    		String storeId=String.valueOf(resMap.get("merchant_id"));
    		if(storeId!=null) {
        		map.put("storeId",storeId);
        	}
    	}
    	
    List<Map<String, String>> count=amsArchivesAuditDao.getArchivesAuditList(map);
	return ResultTO.newSuccessResultTO("请求成功", count);
    }
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    @RequestMapping(value = "insertArchivesAudit")
    @ResponseBody
    public ResultTO insertArchivesAudit(HttpServletRequest request){
    	AmsArchivesAudit amsArchivesAudit=new AmsArchivesAudit();
    	String archivesId=RequestParamUtil.getEncodeParam(request, "archivesId");
    	if(archivesId==null) {
    		return ResultTO.newFailResultTO("archivesId不能为空", null);
    	}
    	String type=RequestParamUtil.getEncodeParam(request, "type");
    	if(type==null) {
    		return ResultTO.newFailResultTO("type不能为空", null);
    	}
    	String fullname=RequestParamUtil.getEncodeParam(request, "fullname");
    	if(fullname!=null) {
    		amsArchivesAudit.setApplyUser(fullname);
    	}
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMDD");
    	String da=sdf.format(date);
    	int count=amsArchivesAuditDao.getAmsArchivesAuditCountToday();
    	String s=String.format("%05d", count);
    	amsArchivesAudit.setApplyNo("NB"+da+s);
    	amsArchivesAudit.setArchivesId(Integer.parseInt(archivesId));
    	amsArchivesAudit.setType(type);
    	amsArchivesAudit.setApplyUser(fullname);
    	amsArchivesAudit.setStatus("0");
    	amsArchivesAudit.setCtime(date);
    	amsArchivesAuditDao.insert(amsArchivesAudit);
    	AmsArchives amsArchives=new AmsArchives();
    	amsArchives.setArchivesId(Integer.parseInt(archivesId));
    	amsArchives=amsArchivesDao.selectObject(amsArchives);
    	String msg=null;
    	if(type.equals("0")) {
    		amsArchives.setInflag(5);
    		msg="您有未处理的借档信息";
    	}else if(type.equals("1")){
    		amsArchives.setInflag(6);
    		msg="您有未处理的还档信息";
    	}
    	amsArchivesDao.update(amsArchives);
    	Map<String,Object> storeId =new HashMap<String,Object>(); 
    	storeId.put("storeId", amsArchives.getStroreId());
    	List<Integer> UserId=adminUserDao.getAdminUserByStoreId(storeId);
    	MsgServiceUtil.sendPushByUser(msg, UserId);
	return ResultTO.newSuccessResultTO("请求成功");
    } 
    @RequestMapping(value = "sendMsgs")
    @ResponseBody
    public ResultTO sendMsgs(HttpServletRequest request){
    	String id=RequestParamUtil.getEncodeParam(request, "storeId");
    	if(id==null) {
    		return ResultTO.newFailResultTO("storeId不能为空", null);
    	}
    	Map<String,Object> storeId =new HashMap<String,Object>(); 
    	storeId.put("storeId", id);
    	List<Integer> UserId=adminUserDao.getAdminUserByStoreId(storeId);
    	MsgServiceUtil.sendPushByUser("发送成功", UserId);
	return ResultTO.newSuccessResultTO("发送成功");
    }
}
