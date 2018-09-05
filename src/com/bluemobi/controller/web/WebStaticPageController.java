package com.bluemobi.controller.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.controller.web.core.BaseController;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.util.RequestParamUtil;


/**
 * web端静态页面跳转管理控制层
 * @author chenbin
 */
@Controller
@RequestMapping("/webJump")
public class WebStaticPageController extends BaseController{
	   @Autowired
	    private WmsStoreDao wmsStoreDao;
	/**
	 * 首页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request,Model model) {
		return "web/index";
	}
	/**
	 * 首页面跳转
	 * @param model
	 * @return
	 */
	@RequestMapping("/index2")
	public String index2(HttpServletRequest request,Model model) {
		return "web/index2";
	}
	/**
	 * 馆藏量跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/holding")
	public String holding(HttpServletRequest request,Model model) {
		return "web/holding";
	}
	/**
	 * 库房布局图跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/layout")
	public String layout(HttpServletRequest request,Model model) {
		return "web/layout";
	}
	/**
	 * 温湿度页面跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/humiture")
	public String humiture(HttpServletRequest request,Model model) {
	    return "web/humiture";
	}
	/**
	 * 视频跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/video")
	public String video(HttpServletRequest request,Model model) {
	    return "web/hkvideo";
	}
	/**
	 * 档案出入跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/archive")
	public String archive(HttpServletRequest request,Model model) {
	    return "web/archive";
	}
	/**
	 * 报警跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/alarm")
	public String alarm(HttpServletRequest request,Model model) {
	    return "web/alarm";
	}
	/**
	 * 能耗跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ecs")
	public String ecs(HttpServletRequest request,Model model) {
		return "web/ecs";
	}
	/**
	 * rfid跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/rfid")
	public String rfid(HttpServletRequest request,Model model) {
		return "web/rfid";
	}
	/**
	 * 密集架跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ims")
	public String ims(HttpServletRequest request,Model model) {
	    return "web/imsControl";
	}
	
	
	/**
	 * rfid盘点跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/access")
	public String rfidpd(HttpServletRequest request,Model model,HttpSession session) {
		return "web/access";
	}
	/**
	 * 沈华杰密集架操作跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/deviceop1")
	public String deviceop1(HttpServletRequest request,Model model) {
		return "web/deviceop1";
	}
	@RequestMapping("/deviceop")
	public String deviceop(HttpServletRequest request,Model model) {
		return "web/deviceop";
	}
	/**
	 * 沈华杰门禁操作跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/deviceop2")
	public String deviceop2(HttpServletRequest request,Model model) {
		return "web/deviceop2";
	}
	/**
	 * 沈华杰档案操作日志跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/smartlog1")
	public String smartlog1(HttpServletRequest request,Model model) {
		return "web/smartlog1";
	}
	
	/**
	 * 异常报警日志页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/alarmError")
	public String alarmError(HttpServletRequest request,Model model) {
		return "web/alarm_error";
	}
	/**
	 * 待处理作日志跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/smartlogPending")
	public String smartlogPending(HttpServletRequest request,Model model) {
		return "web/smartlog_pending";
	}
	/**
	 * 待处理作日志跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/pendingList")
	public String pendingList(HttpServletRequest request,Model model) {
		return "web/ywcz_pending";
	}
	/**
	 * 待处理作日志跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/pendingList2")
	public String pendingList2(HttpServletRequest request,Model model) {
		return "web/ywcz_pending2";
	}
	/**
	 * 沈华杰跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/smartlog2")
	public String smartlog2(HttpServletRequest request,Model model) {
		return "web/smartlog2";
	}
	/**
	 * 林阳温湿度跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/hump")
	public String aaa(HttpServletRequest request,Model model) {
		return "web/hump";
	}
	/**
	 * 轴承泪跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ywcz_dacx")
	public String ywcz_dacx(HttpServletRequest request,Model model) {
		return "web/ywcz_dacx";
	}
	/**
	 * 轴承泪跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/znfx_dahx")
	public String znfx_dahx(HttpServletRequest request,Model model) {
		return "web/znfx_dahx";
	}
	/**
	 * 轴承泪跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/zntj_datj")
	public String zntj_datj(HttpServletRequest request,Model model) {
		return "web/zntj_datj";
	}
	/**
	 * 轴承泪跳转
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/zntj_ddtj")
	public String zntj_ddtj(HttpServletRequest request,Model model) {
		return "web/zntj_ddtj";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_dasq")
	public String ams_dasq(HttpServletRequest request,Model model) {
	    return "web/ams_dasq";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_cdsq")
	public String ams_cdsq(HttpServletRequest request,Model model) {
	    return "web/ams_cdsq";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_cdsq2")
	public String ams_cdsq2(HttpServletRequest request,Model model) {
	    return "web/ams_cdsq2";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_cdsq3")
	public String ams_cdsq3(HttpServletRequest request,Model model) {
	    return "web/ams_cdsq3";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_cdsq4")
	public String ams_cdsq4(HttpServletRequest request,Model model) {
	    return "web/ams_cdsq4";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_dapd")
	public String ams_dapd(HttpServletRequest request,Model model) {
	    return "web/ams_dapd";
	}
	/**
	 * 档案管理中心模块页面地址********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ams_rwlb")
	public String ams_rwlb(HttpServletRequest request,Model model) {
	    return "web/ams_rwlb";
	}
	/**
	 * 图片上传********************************************************************************
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/img")
	public String img(HttpServletRequest request,Model model) {
	    return "web/img";
	}
}
