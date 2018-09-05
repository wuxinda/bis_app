/**
 * FileName:IDSequenceUtil.java
 * Author: Administrator
 * Create: 2014年8月11日
 * Last Modified: 2014年8月11日
 * Version: V1.0 
 */
package com.bluemobi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 序号生产工具类（只针对固定规则生成的序号)
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月11日
 */
public class IDSequenceUtil {

	public static final int DEFAULT_PLACE_LONG = 10;
	public static final int DEFAULT_PLACE = 8;
	public static final int DEFAULT_PLACE_SHORT = 6;

	/** 
	 * @Title: getRandomString 
	 * @Description: 生产随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	/** 
	 * @Title: geUUID 
	 * @Description: 生成32位的UUID 
	 * @return
	 */
	public static String geUUID(){
		 return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/** 
	 * @Title: getOrderNo 
	 * @Description: 生成订单编号  业务编号+时间+5位生成编号
	 * @return
	 */
	public static synchronized String getOrderNo() {
		long orderNum = 0l;
		String date = null;
		String str = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum = new Double(Math.random()*100000).intValue();
		long orderNo = Long.parseLong((date)) * 10000;
		orderNo += orderNum;
		return orderNo+"";
	}
	
	/**
	 * @Title: getUserName 
	 * @Description: 生成用户名  
	 * @return
	 */
	public static String getUserName(String phone) {
		return "lm_"+phone;
	}
	
}
