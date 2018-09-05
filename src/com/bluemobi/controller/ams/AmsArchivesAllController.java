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
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.rfid.RfidInoutDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.ams.AmsArchives;
import com.bluemobi.po.ams.AmsArchivesActlog;
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.service.ams.AmsArchivesFilingmethodService;
import com.bluemobi.service.ams.AmsArchivesKeepyearService;
import com.bluemobi.service.ams.AmsArchivesService;
import com.bluemobi.service.ams.AmsArchivesTypeService;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.wms.WmsStoreAreaService;
import com.bluemobi.service.wms.WmsStoreService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;
import com.bluemobi.util.RequestParamUtil;

@Controller
@RequestMapping("amsArchivesaAll")

public class AmsArchivesAllController extends AbstractBackController{
	  private static final Logger LOGGER = LoggerFactory.getLogger(AmsArchivesController.class);
	    private static final Logger LOGGER1 = LoggerFactory.getLogger(AmsArchivesActlogController.class);
	    @Autowired
	    private AmsArchivesService amsArchivesService;
	    @Autowired
	    private AmsArchivesActlogService amsArchivesActlogService;
	    @Autowired
	    private AmsArchivesTypeService amsArchivesTypeService;
	    @Autowired
	    private AmsArchivesFilingmethodService amsArchivesFilingMethodService;
	    @Autowired
	    private AmsArchivesKeepyearService amsArchivesKeepyearService;
	    @Autowired
	    private WmsStoreService wmsStoreService;
	    @Autowired
	    private WmsStoreAreaService wmsStoreAreaService;
	    @Autowired
	    private DeviceManageService deviceManageService;
	    @Autowired
	    private AmsArchivesDao amsArchivesDao;
	    @Autowired
	    private RfidInoutDao rfidInoutDao;    
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
	     * 进入【档案详情表】主页
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
		// 档案类型
		model.addAttribute("AmsArchivesType", amsArchivesTypeService.selectAmsArchivesType());
		// 库房
		model.addAttribute("wmsStore", wmsStoreService.selectWmsStore());
		return "ams/archives.index";
	    }

	    /**
	     * 分页查询【档案详情表】
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
	    public Page<Map<String, Object>> page(@ModelAttribute AmsArchives amsArchives, String key, int pageSize, int pageIndex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "".equals(amsArchives.getTitle()) ? null : amsArchives.getTitle());
		map.put("archivesTypeId", "".equals(amsArchives.getArchivesTypeId()) ? null : amsArchives.getArchivesTypeId());
		map.put("stroreId", "".equals(amsArchives.getStroreId()) ? null : amsArchives.getStroreId());
		map.put("stroreAreaId", "".equals(amsArchives.getStroreAreaId()) ? null : amsArchives.getStroreAreaId());
		map.put("inflag", "".equals(amsArchives.getInflag()) ? null : amsArchives.getInflag());
		Page<Map<String, Object>> page = amsArchivesService.page(map, pageIndex, pageSize);
		return page;
	    }

	    /**
	     * 查询【档案详情表】详情
	     * 
	     * @param model
	     * @return string
	     * @author AutoCode
	     * @date 2016-11
	     */
	    @RequestMapping(value = "detail", method = RequestMethod.GET)
	    public String detail(Model model, Integer archivesId) {
		// 加载公共数据
		initIndex(model);
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("archivesId", archivesId);
		model.addAttribute("amsArchives", amsArchivesService.selectObject(map));
		return "ams/archives.detail";
	    }

	    /**
	     * 进入新增【档案详情表】页面
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
		model.addAttribute("amsArchivesType", amsArchivesTypeService.selectAmsArchivesType());
		model.addAttribute("filingmethod",amsArchivesFilingMethodService.selectObjectList(null));
		model.addAttribute("keepyear", amsArchivesKeepyearService.selectObjectList(null));
		model.addAttribute("wmsStore", wmsStoreService.selectWmsStore());
		return "ams/archives.edit";
	    }
	    
	   
	    /**
	     * 新增【档案详情表】数据
	     * 
	     * @param amsArchives
	     * @return ResultTO
	     * @author AutoCode
	     * @date 2016-11
	     */
	    @RequestMapping(value = "add", method = RequestMethod.POST)
	    @ResponseBody
	    public ResultTO addAmsArchives(@ModelAttribute AmsArchives amsArchives, BindingResult result) {
		amsArchives.setCreator(this.getUserId());
		amsArchives.setCtime(DateUtil.getCurrentDate());
		amsArchives.setCheckStatus(0);
		amsArchives.setPlaybackstarttime(DateUtil.getCurrentDate());
		amsArchives.setInoutUserId(this.getUserId());
		amsArchives.setInflag(1);
		try {
		    amsArchivesService.insert(amsArchives);
		    LOGGER.info("用户【{}】添加档案详情表数据【{}】成功", new Object[] { this.getUserId(), amsArchives });
		} catch (Exception e) {
		    LOGGER.error("用户【{}】添加档案详情表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchives, e });
		    return ResultTO.newFailResultTO("添加失败", null);
		}
		return ResultTO.newSuccessResultTO("添加成功", null);
	    }

	    /**
	     * 进入修改【档案详情表】页面
	     * 
	     * @param model
	     * @return string
	     * @author AutoCode
	     * @date 2016-11
	     */
	    @RequestMapping(value = "edit", method = RequestMethod.GET)
	    public String edit(Model model, Integer archivesId) {
		// 加载公共数据
		initIndex(model);
		// 返回当前数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("archivesId", archivesId);
		AmsArchives amsArchives = amsArchivesService.selectObject(map);
		model.addAttribute("amsArchives", amsArchives);
		// 返回类型列表
		model.addAttribute("amsArchivesType", amsArchivesTypeService.selectAmsArchivesType());
		// 返回丽娟方式列表
		model.addAttribute("filingmethod", amsArchivesFilingMethodService.selectObjectList(null));
		// 返回保管年限列表
		model.addAttribute("keepyear", amsArchivesKeepyearService.selectObjectList(null));
		// 返回库房
		model.addAttribute("wmsStore", wmsStoreService.selectWmsStore());
		// 通过库房id得到库区列表
		model.addAttribute("wmsStoreArea", wmsStoreAreaService.getStoreAreaByStoreId(amsArchives.getStroreId()));
		// 通过库房库区id获得密集架列表
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("storeId", amsArchives.getStroreId());
		paramMap.put("stroreAreaId", amsArchives.getStroreAreaId());
		paramMap.put("categoryId", 5);
		// 查询库房设备列表
		model.addAttribute("storeColumns", deviceManageService.selectObjectList(paramMap));
		return "ams/archives.edit";
	    }

	    /**
	     * 修改【档案详情表】数据
	     * 
	     * @param amsArchives
	     * @return ResultTO
	     * @author AutoCode
	     * @date 2016-11
	     */
	    @RequestMapping(value = "edit", method = RequestMethod.POST)
	    @ResponseBody
	    public ResultTO editAmsArchives(@ModelAttribute AmsArchives amsArchives, BindingResult result) {
		amsArchives.setModifier(this.getUserId());
		amsArchives.setMtime(DateUtil.getCurrentDate());
		try {
		    amsArchivesService.update(amsArchives);
		    LOGGER.info("用户【{}】修改档案详情表数据【{}】成功", new Object[] { this.getUserId(), amsArchives });
		} catch (Exception e) {
		    LOGGER.error("用户【{}】修改档案详情表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchives, e });
		    return ResultTO.newFailResultTO("更新失败", null);
		}
		return ResultTO.newSuccessResultTO("更新成功", null);
	    }

	    /**
	     * 操作【档案详情表】数据删除、入库、上架、借阅、归还
	     * 
	     * @param archivesId
	     * @return ResultTO
	     * @author AutoCode
	     * @date 2016-11
	     */
	    @RequestMapping(value = "operate", method = RequestMethod.POST)
	    @ResponseBody
	    public ResultTO operateAmsArchives(Integer archivesId, String tittle) {
		Map<String, Object> map = new HashMap<String, Object>();
		AmsArchivesActlog amsArchivesActlog = new AmsArchivesActlog();
		try {
		    map.put("archivesId", archivesId);
		    AmsArchives amsArchives = amsArchivesService.selectObject(map);
		    if (tittle.equals("删除")) {// 盘点状态 0.待入库 1.入库审批中
					      // 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还
			amsArchivesService.delete(map);
		    } else if (tittle.equals("入库")) {
			amsArchives.setCheckStatus(4);
			amsArchives.setInflag(0);
			amsArchivesActlog.setType("0");
		    } else if (tittle.equals("上架")) {
			amsArchives.setCheckStatus(5);
			amsArchivesActlog.setType("2");
		    } else if (tittle.equals("借阅")) {
			amsArchives.setCheckStatus(6);
			amsArchives.setInflag(1);
			amsArchivesActlog.setType("1");
		    } else if (tittle.equals("归还")) {
			amsArchives.setCheckStatus(5);
			amsArchives.setInflag(0);
			amsArchivesActlog.setType("3");
		    }
		    if (!tittle.equals("删除")) {
			amsArchivesActlog.setArchivesId(archivesId);
			amsArchivesActlog.setCreator(this.getUserId());
			amsArchivesActlog.setCtime(DateUtil.getCurrDate());
			amsArchivesActlog.setStatus("1");// 审核状态 0.待审批 1.审核通过 2.审核拒绝
			amsArchivesActlog.setUserId(this.getUserId());
			amsArchivesActlog.setWmsstoreId(amsArchives.getStroreId());
			amsArchivesActlog.setStoreareaId(amsArchives.getStroreAreaId());
			amsArchivesService.update(amsArchives);
			amsArchivesActlogService.insert(amsArchivesActlog);
			LOGGER1.info("用户【{}】添加档案" + tittle + "操作纪录表数据【{}】成功", new Object[] { this.getUserId(), amsArchivesActlog });
		    }
		    LOGGER.info("用户【{}】" + tittle + "更新档案详情表数据【{}】成功", new Object[] { this.getUserId(), archivesId });
		} catch (Exception e) {
		    LOGGER.error("用户【{}】" + tittle + "更新档案详情表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), archivesId, e });
		    LOGGER1.error("用户【{}】添加档案" + tittle + "操作纪录表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), amsArchivesActlog, e });
		    return ResultTO.newFailResultTO("" + tittle + "失败", null);
		}
		return ResultTO.newSuccessResultTO("" + tittle + "成功", null);
	    }

	    /**
	     * 获取档案列表
	     * 
	     * @param userId
	     * @return ResultTO
	     * @author AutoCode
	     * @date 2016-08
	     */
	    @RequestMapping(value = "getAmsList", method = RequestMethod.POST)
	    @ResponseBody
	    public ResultTO getUserList(Map<String, Object> map) {
		List<AmsArchives> list = new ArrayList<AmsArchives>();
		try {
		    list = amsArchivesService.selectObjectList(map);
		    LOGGER.info("用户【{}】获取档案表数据【{}】成功", new Object[] { this.getUserId(), null });
		} catch (Exception e) {
		    LOGGER.error("用户【{}】获取档案表数据【{}】失败 Exception:【{}】", new Object[] { this.getUserId(), null, e });
		    return ResultTO.newFailResultTO("获取失败", null);
		}
		return ResultTO.newSuccessResultTO("获取成功", list);
	    }
	    /**
	     * 按库房查询档案的总藏量
	     */
	    @RequestMapping(value = "getCountAms", method = RequestMethod.POST)
	    @ResponseBody
	    public ResultTO getCountAms(HttpServletRequest request) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	String storeId=RequestParamUtil.getEncodeParam(request, "storeId");
	    	if(storeId!=null) {
	    		map.put("storeId",storeId );
	    	}
	    	String yearTime=RequestParamUtil.getEncodeParam(request, "yearTime");
	    	if(yearTime!=null) {
	    		map.put("yearTime",yearTime );
	    	}
	    	String monthTime=RequestParamUtil.getEncodeParam(request, "monthTime");
	    	if(yearTime!=null) {
	    		map.put("monthTime",monthTime);
	    	}
			return ResultTO.newSuccessResultTO(amsArchivesService.getCountAms(map));
	    	
	    }
		/**
		 * 按查询rfid出入库数据
		 * @return
		 */
	    @RequestMapping(value = "searchArchives")
	    @ResponseBody
	    public ResultTO searchArchives(HttpServletRequest request) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	String data=RequestParamUtil.getEncodeParam(request, "data");
	    	//获取搜索关键字
	    	if(data!=null) {
	    		map.put("data","%"+data+"%" );
	    	};
	    	//获取页码
	    	String page=RequestParamUtil.getEncodeParam(request, "page");
	    	if(data!=null) {
	    		map.put("page",(Integer.parseInt(page)-1)*3 );
	    	}
	    	Map<String,Object> list = amsArchivesService.searchArchives(map);
	    	
			return ResultTO.newSuccessResultTO(list);
	    	
	    }
		/**
		 * 按查询rfid出入库数据
		 * @return
		 */
	    @RequestMapping(value = "selectStrore")
	    @ResponseBody
	    public ResultTO selectStrore(HttpServletRequest request) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	String starttime=RequestParamUtil.getEncodeParam(request, "starttime");
	    	if(starttime!=null) {
	    		map.put("starttime",starttime);
	    	}
	    	String endtime=RequestParamUtil.getEncodeParam(request, "endtime");
	    	if(endtime!=null) {
	    		map.put("endtime",endtime);
	    	}
	    	List<Map<String,Object>> list = amsArchivesService.selectStrore(map);
			return ResultTO.newSuccessResultTO(list);
	    	
	    }
		/**
		 * 按查询rfid出入库数据
		 * @return
		 */
	    @RequestMapping(value = "selectInflag")
	    @ResponseBody
	    public ResultTO selectInflag(HttpServletRequest request) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	String starttime=RequestParamUtil.getEncodeParam(request, "starttime");
	    	if(starttime!=null) {
	    		map.put("starttime",starttime);
	    	}
	    	String endtime=RequestParamUtil.getEncodeParam(request, "endtime");
	    	if(endtime!=null) {
	    		map.put("endtime",endtime);
	    	}
	    	String type=RequestParamUtil.getEncodeParam(request, "type");
	    	if(type!=null) {
	    		map.put("type",type);
	    	}
	    	List<Map<String,Object>> list = amsArchivesService.selectInflag(map);
			return ResultTO.newSuccessResultTO(list);
	    	
	    }	
	    /**
		 * 按查询rfid出入库数据
		 * @return
		 */
	    @RequestMapping(value = "selectArchivesListFroPlace")
	    @ResponseBody
	    public ResultTO selectArchivesListFroPlace(HttpServletRequest request) {
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	String inflag=RequestParamUtil.getEncodeParam(request, "inflag");
	    	if(inflag!=null&&!inflag.equals("")) {
	    		map.put("inflag",inflag);
	    	}
	    	String name=RequestParamUtil.getEncodeParam(request, "name");
	    	if(name!=null&&!name.equals("")) {
	    		map.put("name",name);
	    	}
	    	String stroreId=RequestParamUtil.getEncodeParam(request, "stroreId");
	    	if(stroreId!=null&&!stroreId.equals("选择库房")) {
	    		map.put("stroreId",stroreId);
	    	}else {
	    		AdminUser user=SessionManager.getAttribute(request, AdminConstant.KEY_ADMIN_USER);
	      		map.put("enName",user.getEnName());
	    	}
	    	String stroreAreaId=RequestParamUtil.getEncodeParam(request, "stroreAreaId");
	    	if(stroreAreaId!=null&&!stroreAreaId.equals("选择库区")) {
	    		map.put("stroreAreaId",stroreAreaId);
	    	}
	    	String storeColumn=RequestParamUtil.getEncodeParam(request, "storeColumn");
	    	if(storeColumn!=null&&!storeColumn.equals("选择密集架")) {
	    		map.put("storeColumn",storeColumn);
	    	}
	    	String storeSection=RequestParamUtil.getEncodeParam(request, "storeSection");
	    	if(storeSection!=null&&!storeSection.equals("")) {
	    		map.put("storeSection",storeSection);
	    	}
	    	String storeLr=RequestParamUtil.getEncodeParam(request, "storeLr");
	    	if(storeLr!=null&&!storeLr.equals("")) {
	    		map.put("storeLr",storeLr);
	    	}
	    	String storeLayer=RequestParamUtil.getEncodeParam(request, "storeLayer");
	    	if(storeLayer!=null&&!storeLayer.equals("")) {
	    		map.put("storeLayer",storeLayer);
	    	}
	    	String archiveno=RequestParamUtil.getEncodeParam(request, "archiveno");
	    	if(archiveno!=null&&!archiveno.equals("")) {
	    		map.put("archiveno","%"+archiveno+"%");
	    	}
	    	List<Map<String,Object>> list = amsArchivesDao.selectArchivesListFroPlace(map);
	    	return ResultTO.newSuccessResultTO(list);
	    }
	    /**
		 * 按查询rfid出入库数据
		 * @return
		 */
	    @RequestMapping(value = "getArchiveYearAdd")
	    @ResponseBody
	    public ResultTO getArchiveYearAdd(HttpServletRequest request) {
	    	String year=new SimpleDateFormat("yyyy").format(new Date());
	    	Map<String,Object> map=new HashMap<String,Object>();
	    	map.put("year", year);
	    	Integer countjn = amsArchivesDao.getArchiveYearAdd(map);
	    	map.put("year", Integer.parseInt(year)-1);
	    	Integer countqn = amsArchivesDao.getArchiveYearAdd(map);
	    	map=new HashMap<String,Object>();
	    	if(countqn==0) {
	    		map.put("bfb", "100%");
	    	}else {
	    		Integer bfb=(countjn-countqn)*100/countqn;
	    		map.put("bfb", bfb+"%");
	    	}
	    	Integer syl = rfidInoutDao.getArchivessyl();
	    	if(syl==0) {
	    		map.put("syl","0%");
	    	}else {
	        	Integer countsyl=syl*100/countjn;
	        	map.put("syl", countsyl+"%");	
	    	}
	    	return ResultTO.newSuccessResultTO(map);
	    }
}
