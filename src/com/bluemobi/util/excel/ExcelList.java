package com.bluemobi.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelList {
	
	/**
	 * 导出excel文件
	 * @author HeWeiwen
	 * 2015-12-16
	 * @param title
	 * @param content
	 * @param os
	 */
	public void generateExcel(List<String> title, List<Object[]> content, OutputStream os) {
		//excel 程序默认创建的行数为65536 超过该值报错
		final int preSheetSize = 65535;
		
		WritableWorkbook book = null;
		try {
			
			book = Workbook.createWorkbook(os);
			
			int length = content.size();
			int page = length / preSheetSize + 1;
			
			for(int index = 0; index < page; index++){
				WritableSheet sheet = book.createSheet("第"+(index+1)+"页", index);
				Label label = null;
				if(title != null ){
					for(int i = 0; i < title.size(); i++){
						label = new Label(i,0,title.get(i).toString());
						sheet.addCell(label);
					}
				}
				
				List<Object[]> subList = content.subList(index * preSheetSize, content.size());
				if(subList.size() > preSheetSize){
					subList = content.subList(index * preSheetSize, (index + 1) * preSheetSize);
				}
				
				if(subList != null){
					for(int i = 0; i < subList.size(); i++){
						Object[] objs = (Object[]) subList.get(i);
						for(int j = 0;j < objs.length; j++){
							if(objs[j] != null){
								label = new Label(j, i+1, objs[j].toString());
								sheet.addCell(label);
							} else {
								label = new Label(j, i+1, String.valueOf(0));
								sheet.addCell(label);
							}
						}
					}
				}
				
			}
			
			
//			WritableSheet sheet = book.createSheet("第"+page+"页", 0);
//			Label label = null;
//			if(title != null ){
//				for(int i = 0; i < title.size(); i++){
//					label = new Label(i,0,title.get(i).toString());
//					sheet.addCell(label);
//				}
//			}
//			if(content!=null){
//				for(int i = 0;i<content.size();i++){
//					Object[] objs = (Object[]) content.get(i);
//					for(int j = 0;j<objs.length;j++){
//						if(objs[j]!=null){
//							label = new Label(j,i+1,objs[j].toString());
//							sheet.addCell(label);
//						} else {
//							label = new Label(j,i+1, String.valueOf(0));
//							sheet.addCell(label);
//						}
//					}
//				}
//			}
			
			book.write();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(book!=null){
				try {
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 需要合并单元格的导出表格
	 * @author HeWeiwen
	 * 2015-12-16
     * @param title 标题1
     * @param title1 标题2
     * @param title2 标题3
     * @param list 数据列表
	 * @param content
	 * @param os
	 */
	public void generateExcel(List<String> title1, List<String> title2,
			List<String> title3, List<Object[]> content, OutputStream os){
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(os);
			WritableSheet sheet = book.createSheet("第一页", 0);		
			Label label = null;
			if(title1 != null && title2 != null && title3 != null){
				for(int i = 0; i < title1.size(); i++){
					label = new Label(i, 0, title1.get(i).toString());
					sheet.addCell(label);
					
					//纵向合并i列的1-2两行
					sheet.mergeCells(i, 0, 0, 1);
				}
				
				int point = 0;
				for(int i = 0; i < title2.size(); i++){
					int cell = title1.size() + point * title3.size();
					
					  if(title2.get(i)!=null){
						label = new Label(cell, 0, title2.get(i).toString());
					  }else
					  {
					    label = new Label(cell, 0,"");
				       }
					sheet.addCell(label);
					
					//横向合并1行的cell到（cell + title3.size() - 1）列数
					sheet.mergeCells(cell, 0, cell + title3.size() - 1, 0);
					point++;
					
					for(int k = 0; k < title3.size(); k++){
						label = new Label(cell + k, 1, title3.get(k).toString());
						sheet.addCell(label);
					}
				}
				
				if(content!=null){
					for(int i = 0; i < content.size(); i++){
						Object[] objs = (Object[]) content.get(i);
						for(int j = 0; j < objs.length;j++){
							if(objs[j] != null){
								label = new Label(j, i+2, objs[j].toString());
								sheet.addCell(label);
							} else {
								label = new Label(j, i+2, String.valueOf(0));
								sheet.addCell(label);
							}
						}
					}
				}
				
			}
			
			book.write();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(book!=null){
				try {
					book.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
