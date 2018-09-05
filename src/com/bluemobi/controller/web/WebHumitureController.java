package com.bluemobi.controller.web;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.device.DeviceManageDao;
import com.bluemobi.dao.perception.PerceptionHcsDao;
import com.bluemobi.dao.wms.WmsStoreAreaDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.perception.PerceptionHcs;
import com.bluemobi.service.perception.PerceptionHcsService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.DateUtil;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * WEB【温湿度纪录表】控制器
 * 
 * 
 */
@Controller(value = "WebHumitureController")
@RequestMapping("web/humiture")
public class WebHumitureController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebHumitureController.class);

	@Autowired
	private PerceptionHcsDao perceptionHcsDao;
	@Autowired
	private PerceptionHcsService perceptionHcsService;
	@Autowired
	private DeviceManageDao deviceManageDao;
	@Autowired
	private WmsStoreDao wmsStoreDao;
	@Autowired
	private WmsStoreAreaDao wmsStoreAreaDao;

	/**
	 * 查询最新温湿度纪录
	 * 
	 */
	@RequestMapping(value = "getNewHumiture")
	@ResponseBody
	public ResultTO getNewHumiture(HttpServletRequest request) {
		LOGGER.info("*************获取温湿度纪录-begin*****************");
		// 返回结果级
		List<Map<String, Object>> result = null;
		try {
			// 查询首页档案类型和温湿度
			result = perceptionHcsDao.getWmsAvgHumTem(null);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取温湿度纪录出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取温湿度纪录-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", result);

	}

	/**
	 * 温湿度报表
	 * 
	 */
	@RequestMapping(value = "getHumitureReport")
	@ResponseBody
	public ResultTO getHumitureReport(HttpServletRequest request) {
		LOGGER.info("*************获取温湿度报表-begin*****************");
		Map<String, Object> map = new HashMap<String, Object>();
		String time = RequestParamUtil.getEncodeParam(request, "time");
		if (StringUtils.isEmpty(time)) {
			return ResultTO.newFailResultTO("时间不能为空", null);
		}
		map.put("time", time);
		// 返回结果级
		Map<String, Object> rmap = new HashMap<String, Object>();
		List<Map<String, Object>> dresult = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> wresult = new ArrayList<Map<String, Object>>();
		try {
			// 查询温湿度报表
			dresult = perceptionHcsService.getHumitureReport(map);
			rmap.put("data", dresult);
			wresult = perceptionHcsService.getHumitureWms(rmap);
			rmap.put("wmslist", wresult);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取温湿度报表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************获取温湿度报表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", rmap);

	}

	/**
	 * 温湿度导出excel
	 * 
	 */
	@RequestMapping(value = "toexcel")
	@ResponseBody
	public ResultTO toexcel(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/vnd.ms-excel");
		LOGGER.info("*************导出温湿度报表-begin*****************");
		Map<String, Object> map = new HashMap<String, Object>();
		String time = RequestParamUtil.getEncodeParam(request, "time");
		if (StringUtils.isEmpty(time)) {
			return ResultTO.newFailResultTO("时间不能为空", null);
		}
		map.put("time", time);
		String filename = "温湿度报表";
		// 返回结果级
		Map<String, Object> rmap = new HashMap<String, Object>();
		List<Map<String, Object>> dresult = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> wresult = new ArrayList<Map<String, Object>>();
		if (time.split("-").length == 3) {// 日报表
			filename="库房温湿度日报表";
			rmap.put("rows", 28);
			rmap.put("shijian", "时间（小时）");
			rmap.put("filename", filename);
		} else if (time.split("-").length == 2) {
			filename="库房温湿度月报表";
			rmap.put("rows", 35);
			rmap.put("shijian", "时间（天数）");
			rmap.put("filename", filename);
		} else if (time.split("-").length == 1) {
			filename="库房温湿度年报表";
			rmap.put("rows", 16);
			rmap.put("shijian", "时间（月份）");
			rmap.put("filename", filename);
		}
		rmap.put("time", time);
		try {
			// 查询温湿度报表
			dresult = perceptionHcsService.getHumitureReport(map);
			rmap.put("data", dresult);
			wresult = perceptionHcsService.getHumitureWms(rmap);
			rmap.put("wmslist", wresult);
			// 处理下载
			String userAgent = request.getHeader("User-Agent"); 
			//针对IE或者以IE为内核的浏览器：
			if (userAgent.contains("MSIE")||userAgent.contains("Trident")) {
				filename = java.net.URLEncoder.encode(filename, "UTF-8");
			} else {
			//非IE浏览器的处理：
				filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
			}
			response.setHeader("Content-disposition", "attachment;filename=" + filename + "_" + time + ".xls");
			OutputStream ouputStream = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			wb = this.excel(rmap);
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("导出温湿度报表出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************导出温湿度报表-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", rmap);

	}

	public HSSFWorkbook excel(Map<String, Object> rmap) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("温湿度报表数据");
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setWrapText(true);
		HSSFCellStyle styles = wb.createCellStyle();
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		styles.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styles.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styles.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styles.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styles.setWrapText(true);
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 15);
		font.setColor(HSSFColor.BLACK.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		HSSFFont fonts = wb.createFont();
		fonts.setFontHeightInPoints((short) 25);
		fonts.setColor(HSSFColor.BLACK.index);
		fonts.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fonts.setFontName("黑体");
		for (int x = 0; x < Integer.parseInt(String.valueOf(rmap.get("rows"))); x++) {
			sheet.autoSizeColumn((short) x);
			HSSFRow row = sheet.createRow((int) x);// 创建第一行
			if (x == 0) {// 表头
				styles.setFont(fonts);
				row.setHeight((short) (25 * 50));
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(String.valueOf(rmap.get("filename")));
				cell.setCellStyle(styles);
				for (int a = 1; a <= (((List<Map<String, Object>>) rmap.get("wmslist")).size()) * 2; a++) {
					HSSFCell cells = row.createCell(a);
					cells.setCellStyle(styles);
					sheet.setColumnWidth(a - 1, 20 * 256); // 第一个参数代表列id(从0开始),第2个参数代表宽度值
				}
				sheet.addMergedRegion(
						new CellRangeAddress(0, 0, 0, (((List<Map<String, Object>>) rmap.get("wmslist")).size()) * 2));
				continue;
			}
			style.setFont(font);
			if (x == 1) {// 时间
				row.setHeight((short) (25 * 20));
				HSSFCell cell = row.createCell(0);
				cell.setCellValue("报表时间:" + rmap.get("time"));
				cell.setCellStyle(style);
				for (int a = 1; a <= (((List<Map<String, Object>>) rmap.get("wmslist")).size()) * 2; a++) {
					HSSFCell cells = row.createCell(a);
					cells.setCellStyle(style);
				}
				sheet.addMergedRegion(
						new CellRangeAddress(1, 1, 0, (((List<Map<String, Object>>) rmap.get("wmslist")).size()) * 2));
				continue;
			}
			row.setHeight((short) (25 * 20));
			if (x == 2) {// 库房
				HSSFCell cell = row.createCell(0);// 第一列
				cell.setCellStyle(style);
				cell.setCellValue("库房");
				for (int y = 0; y < Integer
						.parseInt(String.valueOf(((List<Map<String, Object>>) rmap.get("wmslist")).size())); y++) {
					sheet.addMergedRegion(new CellRangeAddress(x, x, 2 * y + 1, 2 * y + 2));
					HSSFCell cells = row.createCell(2 * y + 1);
					cells.setCellStyle(style);
					row.createCell(2 * y + 2).setCellStyle(style);
					List<Map<String, Object>> wresult = (List<Map<String, Object>>) rmap.get("wmslist");
					Map<String, Object> ss = wresult.get(y);
					cells.setCellValue(String.valueOf(ss.get("name")));
				}
				continue;
			}
			if (x == 3) {// 列头。温度湿度
				HSSFCell cell = row.createCell(0);// 第一列
				cell.setCellStyle(style);
				cell.setCellValue(String.valueOf(rmap.get("shijian")));
				for (int y = 1; y <= Integer
						.parseInt(String.valueOf(((List<Map<String, Object>>) rmap.get("wmslist")).size())) * 2; y++) {
					HSSFCell cells = row.createCell(y);
					cells.setCellStyle(style);
					if (y % 2 == 1) {
						cells.setCellValue("温度(平均)");
					} else {
						cells.setCellValue("湿度(平均)");
					}
				}
				continue;
			}
			// 具体温湿度数据
			Integer xx;
			HSSFCell cells = row.createCell(0);// 创建第一列时间
			cells.setCellStyle(style);
			if (String.valueOf(rmap.get("rows")).equals("28")) {
				cells.setCellValue(String.valueOf(x - 4));
				xx = x - 4;
			} else {
				cells.setCellValue(String.valueOf(x - 3));
				xx = x - 3;
			}
			// 具有库房数据的第一列
			for (int y = 0; y < Integer
					.parseInt(String.valueOf(((List<Map<String, Object>>) rmap.get("wmslist")).size())); y++) {
				HSSFCell cellw = row.createCell(y * 2 + 1);
				HSSFCell cellss = row.createCell(y * 2 + 2);
				cellw.setCellStyle(style);
				cellss.setCellStyle(style);
				for (int z = 0; z < ((List<Map<String, Object>>) rmap.get("data")).size(); z++) {
					List<Map<String, Object>> wresult = (List<Map<String, Object>>) rmap.get("data");
					Map<String, Object> ss = wresult.get(z);
					List<Map<String, Object>> wresults = (List<Map<String, Object>>) rmap.get("wmslist");
					Map<String, Object> sss = wresults.get(y);
					if (String.valueOf(ss.get("storeId")).equals(String.valueOf(sss.get("storeId")))
							&& Integer.parseInt(String.valueOf(ss.get("date"))) == xx) {
						cellw.setCellValue(String.valueOf(ss.get("tems")) + "℃");
						cellss.setCellValue(String.valueOf(ss.get("hums")) + "%Rh");
					}
				}
			}
		}
		return wb;
	}
	
	/**
	 * 温湿度报表
	 * 
	 */
	@RequestMapping(value = "saveHumiture")
	@ResponseBody
	public ResultTO saveHumiture(HttpServletRequest request) {
		LOGGER.info("*************温湿度持久化保存-begin*****************");
		PerceptionHcs perceptionHcs = new PerceptionHcs();
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> storeCodeMap = new HashMap<>();
		Map<String,String> storeAreaCodeMap = new HashMap<>();
		List<Map<String,Object>> list = null;
		Map<String,Object> storeList = null;
		Map<String,Object> storeAreaList = null;
		//库房Id
		String storeId = RequestParamUtil.getEncodeParam(request, "storeId");
		if (StringUtils.isEmpty(storeId)) {
			return ResultTO.newFailResultTO("库房Id不能为空", null);
		}
		//库房Id
		String storeAreaId = RequestParamUtil.getEncodeParam(request, "storeAreaId");
		if (StringUtils.isEmpty(storeAreaId)) {
			return ResultTO.newFailResultTO("库区Id不能为空", null);
		}
		
		//温度
		String temperature = RequestParamUtil.getEncodeParam(request, "temperature");
		if (StringUtils.isEmpty(temperature)) {
			return ResultTO.newFailResultTO("温度不能为空", null);
		}
		//湿度
		String humidity = RequestParamUtil.getEncodeParam(request, "humidity");
		if (StringUtils.isEmpty(humidity)) {
			return ResultTO.newFailResultTO("湿度不能为空", null);
		}
		int deviceId=0;
		String StoreAreaCode= storeId+"-"+storeAreaId;

		storeCodeMap.put("storeId", storeId);
		storeAreaCodeMap.put("stroreAreaId", StoreAreaCode);
		
		
		try {
			
			storeList = wmsStoreDao.selectStoreIdByCode(storeCodeMap);
			storeAreaList = wmsStoreAreaDao.selectStoreAreaIdByCode(storeAreaCodeMap);
			
			//添加参数
			map.put("storeId",storeList.get("storeId"));
			map.put("stroreAreaId",storeAreaList.get("stroreAreaId"));
			list = deviceManageDao.getDeviceForSSAId(map);
			for(Map<String,Object> map1:list) {
				deviceId = (int) map1.get("deviceId");
			}
			perceptionHcs.setDeviceId(deviceId);
			perceptionHcs.setTemperature(temperature);
			perceptionHcs.setHumidity(humidity);
			perceptionHcs.setCollectDate(new Date());
			perceptionHcsDao.insert(perceptionHcs);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("温湿度持久化保存出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("请求失败！", null);
		}
		LOGGER.info("*************温湿度持久化保存-end*****************");
		return ResultTO.newSuccessResultTO("请求成功", null);

	}
	
}
