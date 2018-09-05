package com.bluemobi.controller.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.device.DeviceManageDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.service.device.DeviceManageService;
import com.bluemobi.service.device.DeviceStoreLinkpropertyValueService;
import com.bluemobi.to.ResultTO;

/**
 * WEB【视频纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebVideoController")
@RequestMapping("web/video")
public class WebVideoController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebVideoController.class);

	@Autowired
	private DeviceManageDao deviceManageDao;
	@Autowired
	private WmsStoreDao wmsStoreDao;
	@Autowired
	private DeviceManageService deviceManageService;
	@Autowired
	private DeviceStoreLinkpropertyValueService deviceStoreLinkpropertyValueService;

	/**
	 * 查询最新视频纪录
	 * 
	 */
	@RequestMapping(value = "getVideoList")
	@ResponseBody
	public ResultTO getVideoList(HttpServletRequest request) {
		LOGGER.info("*************获取视频纪录-begin*****************");
		// 返回结果级
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 查询视频
			List<Map<String, Object>> store = wmsStoreDao.selectObjectList(null);
			result.put("store", store);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("categoryId", 1);
			List<Map<String, Object>> video = deviceManageDao.selectObjectList(map);
			result.put("video", video);
			List<Map<String, Object>> count = deviceManageDao.selectStoreDevNum(map);
			result.put("count", count);
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("deviceType", 1);
			map1.put("valueName", "通道号");
			List<Map<String, Object>> channel = deviceManageDao.getDevLinkValue(map1);
			result.put("channel", channel);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取视频纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取视频纪录-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);

	}

	/**
	 * 获取视频连接参数
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getVideoLinkVal")
	@ResponseBody
	public ResultTO getvideolinkval(HttpServletRequest request) {
		LOGGER.info("*************获取视频连接属性-begin*****************");
		// 返回结果级
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 查询视频
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("deviceType", 1);
			List<Map<String, Object>> channel = deviceManageDao.selectMapList(map1);
			if (channel != null && channel.size() > 0) {
				Map<String, Object> ld = channel.get(0);
				List<Map<String, Object>> val = deviceManageService
						.getLinkListByDeviceId(Integer.parseInt(String.valueOf(ld.get("deviceId"))));
				if (val != null) {
					for (Map<String, Object> va : val) {
						if (va.get("name").equals("登录用户名")) {
							result.put("loginname", va.get("val"));
						}
						if (va.get("name").equals("登录密码")) {
							result.put("loginpwd", va.get("val"));
						}
						if (va.get("name").equals("服务器地址")) {
							result.put("loginip", va.get("val"));
						}
						if (va.get("name").equals("端口号")) {
							result.put("loginport", va.get("val"));
						}
						if (va.get("name").equals("通道号")) {
							result.put("chanal", va.get("val"));
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取视频获取视频连接属性出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取视频获取视频连接属性-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);

	}

	/**
	 * 获取设备通道号按照排序号排序
	 * 
	 */
	@RequestMapping(value = "getDeviceChanal")
	@ResponseBody
	public ResultTO getDeviceChanal(HttpServletRequest request) {
		LOGGER.info("*************获取设备通道号-begin*****************");
		// 返回结果级
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			// 查询视频通道号
			list = deviceStoreLinkpropertyValueService.selectDeviceChanal(null);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取设备通道号出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取设备通道号-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", list);

	}

	/**
	 * 视频插件下载
	 * 
	 */
	@RequestMapping(value = "getVideoplugin")
	@ResponseBody
	public ResultTO getVideoplugin(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("*************视频插件下载-begin*****************");
		// 插件保存在/bis_app/WebRoot/resources/plugins/HKWS/util/codebase/WebComponents.exe
		String filePath = request.getServletContext().getRealPath("/resources/plugins/HKWS/util/codebase/WebComponents.exe");
		// 得到要下载的文件
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				request.setAttribute("message", "您要下载的资源已被删除！！");
				return ResultTO.newFailResultTO("请求失败！", null);
			}
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition",
					"attachment;filename=" + URLEncoder.encode("WebComponents.exe", "UTF-8"));
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in = new FileInputStream(filePath);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("*************视频插件下载-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);

	}
}
