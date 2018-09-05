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
import com.bluemobi.dao.ams.AmsArchivesAuditInDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesAuditIn;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.ams.AmsArchivesAuditInService;
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
@RequestMapping("amsArchivesAuditIn")
public class AmsArchivesAuditInController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesAuditInController.class);
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AmsArchivesAuditInService amsArchivesAuditInService;
    @Autowired
	private AmsArchivesAuditInDao amsArchivesAuditInDao;
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
        return "ams/archivesAuditIn.index";
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
    public Page<Map<String, Object>> page(@ModelAttribute AmsArchivesAuditIn amsArchivesAuditIn,String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "".equals(amsArchivesAuditIn.getType()) ? null:amsArchivesAuditIn.getType());
        map.put("status", "".equals(amsArchivesAuditIn.getStatus()) ? null:amsArchivesAuditIn.getStatus());
        Page<Map<String, Object>> page = amsArchivesAuditInService.page(map,pageIndex, pageSize);
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
    public String detail(Model model, Integer AuditInId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("AuditInId", AuditInId); 
        model.addAttribute("amsArchivesAuditIn", amsArchivesAuditInService.selectObject(map));
        return "ams/archivesAuditIn.detail";
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
        return "ams/archivesAuditIn.edit";
    }
    
    /**
     * 新增【档案申请审批表】数据
     * @param amsArchivesAuditIn
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAmsArchivesAuditIn(@ModelAttribute AmsArchivesAuditIn amsArchivesAuditIn, BindingResult result) {
    	amsArchivesAuditIn.setCreator(this.getUserId());
    	amsArchivesAuditIn.setCtime(DateUtil.getCurrentDate());
    	amsArchivesAuditIn.setMtime(DateUtil.getCurrentDate());
    	amsArchivesAuditIn.setStatus("0");
    	amsArchivesAuditIn.setUserId(this.getUserId());
        try {
            amsArchivesAuditInService.insert(amsArchivesAuditIn);
            LOGGER.info("用户【{}】添加档案申请审批表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesAuditIn } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加档案申请审批表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesAuditIn, e });
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
    public String edit(Model model, Integer AuditInId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("AuditInId", AuditInId); 
        model.addAttribute("amsArchivesAuditIn", amsArchivesAuditInService.selectObject(map));
        return "ams/archivesAuditIn.edit";
    }
    
    /**
     * 修改【档案申请审批表】数据
     * @param amsArchivesAuditIn
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAmsArchivesAuditIn(@ModelAttribute AmsArchivesAuditIn amsArchivesAuditIn, BindingResult result) {
    	amsArchivesAuditIn.setModifier(this.getUserId());
    	amsArchivesAuditIn.setMtime(DateUtil.getCurrentDate());
        try {
            amsArchivesAuditInService.update(amsArchivesAuditIn);
            LOGGER.info("用户【{}】修改档案申请审批表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesAuditIn } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改档案申请审批表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesAuditIn, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【档案申请审批表】数据
     * @param AuditInId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-11
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAmsArchivesAuditIn(Integer AuditInId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("AuditInId", AuditInId); 
            amsArchivesAuditInService.delete(map);
            LOGGER.info("用户【{}】删除档案申请审批表数据【{}】成功", new Object[] { this.getUserId(), AuditInId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除档案申请审批表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), AuditInId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 查询最新档案操作纪录
     * 
     */
    @RequestMapping(value = "getAmsArchiveAuditIn")
    @ResponseBody
    public ResultTO getAmsArchiveAuditIn(HttpServletRequest request) {
    	List<Map<String, String>> list=amsArchivesAuditInService.getAmsArchiveAuditIn();
	return ResultTO.newSuccessResultTO("请求成功", list);

    }
    /**
     * 查询最新档案操作纪录统计
     * 
     */
    @RequestMapping(value = "getAmsArchiveAuditInCount")
    @ResponseBody
    public ResultTO getAmsArchiveAuditInCount(HttpServletRequest request) {
    	Map<String, String> count=amsArchivesAuditInService.getAmsArchiveAuditInCount();
	return ResultTO.newSuccessResultTO("请求成功", count);

    }
    /**
     * 查询最新档案操作纪录统计
     * 
     */
    @RequestMapping(value = "getArchivesAuditInCount")
    @ResponseBody
    public ResultTO getArchivesAuditInCount(HttpServletRequest request) {
    	List<Map<String, String>> count=amsArchivesAuditInService.getArchivesAuditInCount();
	return ResultTO.newSuccessResultTO("请求成功", count);

    }
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    @RequestMapping(value = "getArchivesAuditInList")
    @ResponseBody
    public ResultTO getArchivesAuditInList(HttpServletRequest request){
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
    	
    List<Map<String, String>> count=amsArchivesAuditInDao.getArchivesAuditInList(map);
	return ResultTO.newSuccessResultTO("请求成功", count);
    }
    /**
     * 获取取档还档列表
     * 
     * @param parmMap
     * @return
     */
    @RequestMapping(value = "insertArchivesAuditIn")
    @ResponseBody
    public ResultTO insertArchivesAuditIn(HttpServletRequest request){
    	AmsArchivesAuditIn amsArchivesAuditIn=new AmsArchivesAuditIn();
    	String archivesId=RequestParamUtil.getEncodeParam(request, "archivesId");
    	if(archivesId==null) {
    		return ResultTO.newFailResultTO("archivesId不能为空", null);
    	}
    	String type=RequestParamUtil.getEncodeParam(request, "type");
    	if(type==null) {
    		return ResultTO.newFailResultTO("type不能为空", null);
    	}
    	AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
    	String fullname=String.valueOf(user.getFullname());
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMDD");
    	String da=sdf.format(date);
    	int count=amsArchivesAuditInDao.getAmsArchivesAuditInCountToday();
    	String s=String.format("%05d", count);
    	amsArchivesAuditIn.setApplyNo("NB"+da+s);
    	amsArchivesAuditIn.setArchivesId(Integer.parseInt(archivesId));
    	amsArchivesAuditIn.setType(type);
    	amsArchivesAuditIn.setApplyUser(fullname);
    	amsArchivesAuditIn.setUserId(user.getUserId());
    	amsArchivesAuditIn.setStatus("0");
    	amsArchivesAuditIn.setCtime(date);
    	amsArchivesAuditInDao.insert(amsArchivesAuditIn);
    	AmsArchives amsArchives=new AmsArchives();
    	amsArchives.setArchivesId(Integer.parseInt(archivesId));
    	amsArchives=amsArchivesDao.selectObject(amsArchives);
    	if(type.equals("0")) {
    		amsArchives.setInflag(3);
    	}else if(type.equals("1")){
    		amsArchives.setInflag(4);
    	}
    	
    	amsArchivesDao.update(amsArchives);
	return ResultTO.newSuccessResultTO("请求成功");
    } 
}
