package com.bluemobi.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

/**
 * 导出Excel通用Bean
 * @author heweiwen
 * 2015-12-16 下午5:40:42
 */
public class ExcelBean {

    /**
     * 生产excel文件 不需要合并单元格的表格
     * @author HeWeiwen
     * 2015-11-11
     * @param excelTO 带入数据对象 
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void exportDataToExcel(ExcelTO excelTO,HttpServletResponse response) throws UnsupportedEncodingException{
        ExcelList el = new ExcelList();
        response.setContentType("application/vnd.ms-excel;charset=GBK");
        String name = new String((excelTO.getResultName()).getBytes("gbk"),"iso8859-1");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            el.generateExcel(excelTO.getTitleName(), excelTO.getResultList(), os);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
 
    /**
     * 生产excel文件 有需要合并单元格的表格
     * @author HeWeiwen
     * 2015-12-16
     * @param excelTO
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void exportDataToExcelCells(ExcelTO excelTO,HttpServletResponse response) throws UnsupportedEncodingException{
        ExcelList el = new ExcelList();
        response.setContentType("application/vnd.ms-excel;charset=GBK");
        String name = new String((excelTO.getResultName()).getBytes("gbk"),"iso8859-1");
        response.setHeader("Content-disposition", "attachment; filename=" + name);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            el.generateExcel(excelTO.getTitleName(), excelTO.getTitleName2(), excelTO.getTitleName3(), excelTO.getResultList(), os);
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }
}
