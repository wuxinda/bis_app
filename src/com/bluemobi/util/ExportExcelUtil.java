package com.bluemobi.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bluemobi.util.excel.ExcelList;
/**
 * 导出excel工具类
 * @author haoj 309444359@qq.com
 * @date 2016-9-26 下午3:14:33
 */
public class ExportExcelUtil {
	
	/**
	 * 导出Excel
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-26 下午3:14:54
	 * @param fileName 文件名
	 * @param titleList title集合
	 * @param dataList 数据集合
	 * @param response 
	 */
	public static void export(String fileName,List<String> titleList, List<Object[]> dataList, HttpServletResponse response){
        ExcelList el = new ExcelList();
        response.setContentType("application/vnd.ms-excel;charset=UTF8");
        OutputStream os = null;
        try {
        	 String fileName2 = new String((fileName).getBytes("gbk"),"iso8859-1");
             response.setHeader("Content-disposition", "attachment; filename=" + fileName2);
             
            os = response.getOutputStream();
            el.generateExcel(titleList, dataList, os);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
        	if(os!=null){
        		try {os.close();} catch (IOException e) {e.printStackTrace();}
        	}
        }
    }
	

}
