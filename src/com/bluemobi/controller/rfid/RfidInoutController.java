package com.bluemobi.controller.rfid;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.bluemobi.dao.rfid.RfidInoutDao;
import com.bluemobi.dao.rfid.RfidIntyDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.rfid.RfidInout;
import com.bluemobi.service.rfid.RfidInoutService;
import com.bluemobi.service.rfid.RfidIntyService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;
import com.bluemobi.util.excel.excel;



/**
 * 【】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
@Controller
@RequestMapping("rfidInout")
public class RfidInoutController extends AbstractBackController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RfidInoutController.class);
    
    @Autowired
    private RfidInoutService rfidInoutService;
    @Autowired
    private RfidIntyService rfidIntyService;
    @Autowired
    private RfidIntyDao rfidIntyDao;
    @Autowired
    private RfidInoutDao rfidInoutDao;
    /**
     * 将请求参数中的字符串转换成日期格式
     * @param request
     * @param binder
     * @return string
     * @author AutoCode
     * @date 2017-10
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
     * @date 2017-10
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        return "rfid/inout.index";
    }
    
    /**
     * 分页查询【】
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     * @return Page<Map<String,Object>>
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", key);
        Page<Map<String, Object>> page = rfidInoutService.page(map,pageIndex, pageSize);
        return page;
    }
    
    /**
     * 查询【】详情
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Integer rfidInoutId) {
        // 加载公共数据
        initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("rfidInoutId", rfidInoutId); 
        model.addAttribute("rfidInout", rfidInoutService.selectObject(map));
        return "rfid/inout.detail";
    }
    
    /**
     * 进入新增【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        return "rfid/inout.edit";
    }
    
    /**
     * 新增【】数据
     * @param rfidInout
     * @return ResultTO
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addRfidInout(@ModelAttribute RfidInout rfidInout, BindingResult result) {
        try {
            rfidInoutService.insert(rfidInout);
            LOGGER.info("用户【{}】添加数据【{}】成功", new Object[] { this.getUserId(), rfidInout } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】添加数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), rfidInout, e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }
    
    /**
     * 进入修改【】页面
     * @param model
     * @return string
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer rfidInoutId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rfidInoutId", rfidInoutId); 
        model.addAttribute("rfidInout", rfidInoutService.selectObject(map));
        return "rfid/inout.edit";
    }
    
    /**
     * 修改【】数据
     * @param rfidInout
     * @return ResultTO
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editRfidInout(@ModelAttribute RfidInout rfidInout, BindingResult result) {
        try {
            rfidInoutService.update(rfidInout);
            LOGGER.info("用户【{}】修改数据【{}】成功", new Object[] { this.getUserId(), rfidInout } );
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), rfidInout, e });
            return ResultTO.newFailResultTO("更新失败", null);
        }
        return ResultTO.newSuccessResultTO("更新成功", null);
    }
    
    /**
     * 删除【】数据
     * @param rfidInoutId
     * @return ResultTO
     * @author AutoCode
     * @date 2017-10
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteRfidInout(Integer rfidInoutId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("rfidInoutId", rfidInoutId); 
            rfidInoutService.delete(map);
            LOGGER.info("用户【{}】删除数据【{}】成功", new Object[] { this.getUserId(), rfidInoutId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), rfidInoutId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }
    /**
     * 档案出入日志
     */
    @RequestMapping(value = "searchRfidInout")
    @ResponseBody
    public ResultTO searchRfidInout(HttpServletRequest request)  throws Exception{
    	Map<String,Object> map=new HashMap<String,Object>();
    	String data=RequestParamUtil.getEncodeParam(request, "data");
    	//获取页码
    	String page=RequestParamUtil.getEncodeParam(request, "page");
    	if(data!=null) {
    		map.put("page",Integer.parseInt(page)*3+1 );
    	}
    	List<Map<String,Object>> list = rfidInoutService.searchRfidInout(map);
    	map.put("list", list);
    	map.put("count", list.size());
		return ResultTO.newSuccessResultTO(map);
    	
    }
    /**
     * 档案出入日志
     */
    @RequestMapping(value = "getRfidInoutList")
    @ResponseBody
    public ResultTO getRfidInoutList(HttpServletRequest request) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String type=RequestParamUtil.getEncodeParam(request, "type");
    	if(type!=null&&type!="") {
    		map.put("type",type );
    	}
    	String title=RequestParamUtil.getEncodeParam(request, "archiveName");
    	if(title!=null&&title!="") {
    		map.put("title","%"+title+"%" );
    	}
    	String stroreId=RequestParamUtil.getEncodeParam(request, "store");
    	if(stroreId!=null&&stroreId!="") {
    		map.put("stroreId",stroreId);
    	}else {
    		AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
      		map.put("enName",user.getEnName());
    	}
    	String stroreAreaId=RequestParamUtil.getEncodeParam(request, "storeArea");
    	if(stroreAreaId!=null&&stroreAreaId!="") {
    		map.put("stroreAreaId",stroreAreaId );
    	}
    	String keepyear=RequestParamUtil.getEncodeParam(request, "keepyear");
    	if(keepyear!=null&&keepyear!="") {
    		map.put("keepyear",keepyear);
    	}    	
    	String security=RequestParamUtil.getEncodeParam(request, "security");
    	if(security!=null&&security!="") {
    		map.put("security",security );
    	}
    	String archivesTypeId=RequestParamUtil.getEncodeParam(request, "archiveType");
    	if(archivesTypeId!=null&&archivesTypeId!="") {
    		map.put("archivesTypeId",archivesTypeId);
    	}
    	String timeStart=RequestParamUtil.getEncodeParam(request, "timeStart");
    	if(timeStart!=null&&timeStart!="") {
    		map.put("timeStart",timeStart);
    	}
    	String timeEnd=RequestParamUtil.getEncodeParam(request, "timeEnd");
    	if(timeEnd!=null&&timeEnd!="") {
    		map.put("timeEnd",timeEnd);
    	}
    	String pageIndex = RequestParamUtil.getEncodeParam(request, "pageIndex");
    	if(pageIndex!=null&&pageIndex!="") {
    		map.put("pageIndex",(Integer.parseInt(pageIndex)-1)*9);
    	}
    	Map<String,Object> list=null;
    	if(type.equals("2")) {
    		list = rfidIntyService.getRfidIntyList(map);
    	}else {
    		list = rfidInoutService.getRfidInoutList(map);
    	}
    	
    	
		return ResultTO.newSuccessResultTO(list);
    }
    /**
     * 档案出入日志
     */
    @RequestMapping(value = "getRfidtoexcel")
    @ResponseBody
    public ResultTO getRfidtoexcel(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,Object> map=new HashMap<String,Object>();
    	String type=RequestParamUtil.getEncodeParam(request, "type");
    	if(type!=null&&type!="") {
    		map.put("type",type );
    	}
    	String title=RequestParamUtil.getEncodeParam(request, "archiveName");
    	if(title!=null&&title!="") {
    		map.put("title","%"+title+"%" );
    	}
    	String stroreId=RequestParamUtil.getEncodeParam(request, "store");
    	if(stroreId!=null&&stroreId!="") {
    		map.put("stroreId",stroreId);
    	}else {
    		AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
      		map.put("enName",user.getEnName());
    	}
    	String stroreAreaId=RequestParamUtil.getEncodeParam(request, "storeArea");
    	if(stroreAreaId!=null&&stroreAreaId!="") {
    		map.put("stroreAreaId",stroreAreaId );
    	}
    	String keepyear=RequestParamUtil.getEncodeParam(request, "keepyear");
    	if(keepyear!=null&&keepyear!="") {
    		map.put("keepyear",keepyear);
    	}    	
    	String security=RequestParamUtil.getEncodeParam(request, "security");
    	if(security!=null&&security!="") {
    		map.put("security",security );
    	}
    	String archivesTypeId=RequestParamUtil.getEncodeParam(request, "archiveType");
    	if(archivesTypeId!=null&&archivesTypeId!="") {
    		map.put("archivesTypeId",archivesTypeId);
    	}
    	String timeStart=RequestParamUtil.getEncodeParam(request, "timeStart");
    	if(timeStart!=null&&timeStart!="") {
    		map.put("timeStart",timeStart);
    	}
    	String timeEnd=RequestParamUtil.getEncodeParam(request, "timeEnd");
    	if(timeEnd!=null&&timeEnd!="") {
    		map.put("timeEnd",timeEnd);
    	}
    	List<Map<String,Object>> list=null;
    	String sheetName=null;
    	String titleName=null;
    	String fileName=null;
    	String[] getName= {"startTime","tittle","storeplace","username","remark"};
    	String[] columnName= {"操作时间","档案名称","操作地点","操作人员","备注"};
    	 List<String[]> dataList =new ArrayList<String[]>();
    	if(type.equals("2")) {
    		list = rfidIntyDao.getRfidIntyList(map);
    		sheetName="盘点操作记录";
    		titleName="盘点操作记录";
    		fileName="pdjl"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
    	}else if(type.equals("1")){
    		list = rfidInoutDao.getRfidInoutList(map);
    		sheetName="还档操作记录";
    		titleName="还档操作记录";
    		fileName="hdcz"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
    	}else if(type.equals("0")){
    		list = rfidInoutDao.getRfidInoutList(map);
    		sheetName="借档操作记录";
    		titleName="借档操作记录";
    		fileName="jdcz"+new SimpleDateFormat("YYYYMMDDHHmmSS").format(new Date());
    	}
    	for(Map<String,Object> m:list) {
    		String[] strs=new String[getName.length];
    		for (int i = 0; i < getName.length; i++) {
    			String var=String.valueOf(m.get(getName[i]));
    			if(m.get(getName[i])==null) {
    				var=" ";
    			}
    			strs[i]=var;
			}
    		dataList.add(strs);
    	}
    		try {
    			excel.ExportWithResponse(sheetName, titleName, fileName, columnName, dataList, response);
			} catch (Exception e) {
				 LOGGER.info("用户导出失败");
				e.printStackTrace();
			}
		return ResultTO.newSuccessResultTO(list);
    }
}
