package com.bluemobi.serviceimpl.device.util.comm;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public class StringUtil {
	private static Logger log = Logger.getLogger(StringUtil.class);
	/**
	 * 取得UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().toUpperCase();
	}

	/**
	 * trim
	 * 
	 * @param string
	 * @return
	 */
	public static String trim(String string) {
		if (string == null)
			return "";
		else
			return string.trim();
	}

	/**
	 * isEmpty
	 * 
	 * @param string
	 * @return boolean
	 */
	public static boolean isEmpty(String string) {
		if (string ==null)
			return true;
		if ("".equals(string.trim()) || "null".equals(string.trim()))
			return true;
		else
			return false;
	}

	/**
	 * string2Integer
	 * 
	 * @param string
	 * @return Integer
	 */
	public static Integer string2Integer(String string) {
		String str = trim(string);
		if ("".equals(str))
			str = "0";
		if (str == null)
			str = "0";
		return new Integer(str);
	}

	/**
	 * integer2String
	 * 
	 * @param Integer
	 * @return String
	 */
	public static String integer2String(Integer value) {
		String result = "";
		if (value != null)
			result = String.valueOf(value.intValue());
		return result;
	}
	
	/**
	 * 
	 * 字段转换为INT
	 *
	 * @param value
	 * @return
	 */
	public static int field2Int(Object value) {
		if (null == value) return 0;
		BigDecimal bd = (BigDecimal) value;
		return bd.intValue();
	}
	public static String field2String(Object value) {
		if (null==value) return null;
		return (String) value;
	}
	public static Double field2Double(Object value) {
		if (null==value) return 0.0;
		BigDecimal bd = (BigDecimal) value;
		return bd.doubleValue();
	}

	/**
	 * string2Int
	 * 
	 * @param string
	 * @return int
	 */
	public static int string2Int(String string) {
		String str = trim(string);
		if ("".equals(str))
			str = "0";
		return Integer.parseInt(str);
	}

	/**
	 * string2Double
	 * 
	 * @param string
	 * @return double
	 */
	public static double string2Double(String string) {
		String str = trim(string);
		if ("".equals(str))
			str = "0.0";
		return Double.parseDouble(str);
	}

	/**
	 * Encode a string using Base64 encoding. Used when storing passwords as
	 * cookies.
	 * 
	 * This is weak encoding in that anyone can use the decodeString routine to
	 * reverse the encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return new String(encoder.encodeBuffer(str.getBytes())).trim();
	}

	/**
	 * Decode a string using Base64 encoding.
	 * 
	 * @param str
	 * @return String
	 */
	public static String decodeString(String str) {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (Exception io) {
			return "";
		}
	}

	public static String formatTrim(String str) {
		String re = "";
		StringBuffer sb = new StringBuffer("");
		if (str == null)
			return "";
		try {
			String[] s = str.split(";");
			for (int i = 0; i < s.length; i++) {
				if (!"".equals(s[i])) {
					sb.append(s[i]);
					sb.append(";");
				}
			}
			re = sb.toString();
			if (re.endsWith(";"))
				re = re.substring(0, re.length() - 1);
			return re;
		} catch (Exception io) {
			return "";
		}
	}
	
	/**
	 * 设置默认值（为NULL时用空值代替）
	 * @param value
	 * @return
	 */
	public static String defaultString(String value) {
		return value==null || value.trim().equals("null") ? "" : value.trim();
	}

	/**
	 * 删除字符串中的子字符串
	 * @param str
	 * @param substr
	 * @return
	 */
	public static String delete(String str, String substr) {
		return str.replaceAll(substr, "");		
	}

	/**
	 * 删除字符串中的子字符串，并且原来的分隔符不变
	 * 
	 * @param str
	 * @param substr
	 * @param separator
	 * @return
	 */
	public static String delete(String str, String substr, String separator) {
		String[] sList = str.trim().split(separator);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sList.length; i++) {
			if (sList[i] != null && sList[i].length() > 0
					&& !substr.equals(sList[i]))
				sb.append(sList[i].trim()).append(separator);
		}
		if (sb.length() > 0)
			sb.delete(sb.length() - separator.length(), sb.length());
		return sb.toString();
	}

    /**
     * 转化为2位小数点的金额格式
     * @param price
     * @return
     */
    public static String turnPriceFormat(String price){
		if(price==null || "".equals(price)){
			return "0.00";
		}else{
			int k=price.indexOf(".");
			if(k>=0){
				String s=price+"00";
				return s.substring(0,k+3);
			}else{
				return price+".00";
			}
		}
    }
    
    /**
     * 实现小数的精度变换
     * @param value
     * @param scale
     * @return
     */
    public static double formatDigit(double value, int scale) {
    	BigDecimal bd = new BigDecimal(value);
    	bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);   
        double d = bd.doubleValue();
        bd = null;
        return d;
    }
    /**
     * 实现小数的精度变换
     * @param value
     * @param scale
     * @return
     */
    public static float formatDigitFloat(float value, int scale) {
    	BigDecimal bd = new BigDecimal(value);
    	bd = bd.setScale(scale, BigDecimal.ROUND_HALF_UP);   
    	float d = bd.floatValue();
        bd = null;
        return d;
    }
    public static String digitToString(double value, int scale) {
    	return String.valueOf(formatDigit(value, scale));
    }
    
    public static String formatDigit(int value, int len) {
    	if (value < 0) value = 0;
    	String res = "";
    	for (int i=0; i<len-String.valueOf(value).length(); i++) {
    		res += "0";
		}
    	return res + value;
    }
    
    /*
     * 从jsp传过来的中文参数需要在servlet中调用此方法将其转换为中文。
     */
    public static String isoToGB2312(String param)
    {
    	String result="";
    	try {
    		if(param!=null && !param.equals(""))
    			result=new String(param.getBytes("ISO-8859-1"),"GB2312");
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
		}
		return result;
    }
    
    /**
	 * 取得字符串靠左的部分
	 * @param str
	 * @param size
	 * @return
	 */
	public static String left(String str, int size) {
		if (str==null) return null;
		if (str.length()<size) 
			size = str.length();
		return str.substring(0, size);
	}
	
	/**
	 * 取得字符串靠右的部分
	 * @param str
	 * @param size
	 * @return
	 */
	public static String right(String str, int size) {
		if (str==null) return null;
		if (str.length()<size) 
			size = str.length();
		return str.substring(str.length() - size, str.length());
	}
    
	public static String getSQL(String sql) {
		return "select * from (select rownum startrow, t.* from (" + sql
				+ ") t where rownum<=?) where startrow>? ";
	}
	
	/**
	 * 将一个string转化为int型，如value为空或者，转换异常，返回指定的默认值
	 * <pre>
	 * StringUtils.toInteger(&quot;&quot;, 3);//return 3 value is empty;
	 * StringUtils.toInteger(&quot;aa&quot;, 3);//return 3 value is exception;
	 * </pre>
	 * @param value 要转换的string value
	 * @param defaultValue 当string为空，或者转换失败时，传回的默认值
	 * @return
	 */
	public static int toInt(String value, int defaultValue) {
		try {
			if (isEmpty(value)) {
				return defaultValue;
			}
			return Integer.parseInt(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	public static int toInt(String value) {
		try {
			if (isEmpty(value)) {
				return 0;
			}
			return Integer.parseInt(value);
		} catch (Exception e) {
			return 0;
		}
	}
	public static Integer toInteger(String value, Integer defaultValue) {
		try {
			if (isEmpty(value)) {
				return defaultValue;
			}
			return Integer.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public static Long toLong(String value) {
		try {
			if (isEmpty(value)) {
				return new Long(0);
			} 
			return Long.valueOf(value);
		} catch (Exception e) {
			return new Long(0);
		}
	}
	
	public static Double toDouble(String value) {
		try {
			if (isEmpty(value)) {
				return new Double(0);
			}
			return Double.valueOf(value);
		} catch (Exception e) {
			return new Double(0);
		}
	}
	
	public static Float toFloat(String value) {
		try {
			if (isEmpty(value)) {
				return new Float(0);
			}
			return Float.valueOf(value);
		} catch (Exception e) {
			return new Float(0);
		}
	}
	
	/**
	 * URL编码字符串
	 * @param s
	 * @return
	 */
	public static String encryptUrl(String s) {
		try {
			return URLEncoder.encode(s, "gbk");
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
			return "";
		}
	}

	/**
	 * URL解码字符串
	 * @param s
	 * @return
	 */
	public static String decryptUrl(String s) {
		try {
			return URLDecoder.decode(s, "gbk");
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
			return "";
		}
	}
	
	public static String convertString(String param, String from, String to) {
    	String result="";
    	try {
    		if(param!=null && !param.equals(""))
    			result=new String(param.getBytes(from), to);
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
		}
		return result;
    }
	
	/**
	 * 获取客户端类型
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String getClientExplorerType(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		String explorer = "非主流浏览器";
		if (isIE(request)) {
			int index = userAgent.indexOf("msie");
			explorer = userAgent.substring(index, index + 8);
		} else if (isChrome(request)) {
			int index = userAgent.indexOf("chrome");
			explorer = userAgent.substring(index, index + 12);
		} else if (isFirefox(request)) {
			int index = userAgent.indexOf("firefox");
			explorer = userAgent.substring(index, index + 11);
		}
		return explorer.toUpperCase();
	}
	
	/**
	 * 判断是否是IE浏览器
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isIE(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isIe = true;
		int index = userAgent.indexOf("msie");
		if (index == -1) {
			isIe = false;
		}
		return isIe;
	}

	/**
	 * 判断是否是Chrome浏览器
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isChrome(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isChrome = true;
		int index = userAgent.indexOf("chrome");
		if (index == -1) {
			isChrome = false;
		}
		return isChrome;
	}

	/**
	 * 判断是否是Firefox浏览器
	 * 
	 * @param userAgent
	 * @return
	 */
	public static boolean isFirefox(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		boolean isFirefox = true;
		int index = userAgent.indexOf("firefox");
		if (index == -1) {
			isFirefox = false;
		}
		return isFirefox;
	}
	
	public static String dir2Url(String dir) {
		if (!isEmpty(dir)) {
			char[] ss = dir.toCharArray();
			StringBuffer sb = new StringBuffer();
			for (int i=0; i<ss.length; i++) {
				if (ss[i]==File.separatorChar) {
					ss[i] = '/';
				}
				sb.append(ss[i]);
			}
			return sb.toString();
		}
		return "";
	}
	
	public static String toJson(String code, String name) {
		return "{\"code\":\""+code+"\",\"name\":\""+name+"\"}";
	}
	
	public static String fillZero(String value, int length) {
		String s;
		s = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
		s = s + value;
		s = s.substring(s.length() - length, s.length());
		return s;
	}
	
	public static void main(String[] args) throws Exception {
		//System.out.println( fillZero("01", 5) );
		//String SQL_QUERY = "SELECT {0} FROM DMS_DISK_ARCHIVES a INNER JOIN BSC_DEVICE b ON b.deviceid=a.deviceid INNER JOIN AMS_ARCHIVES_BRIEF c ON c.archiveid=a.archiveid WHERE";
		
		//System.out.println(MessageFormat.format(SQL_QUERY, "a.*,b.*,c.*"));
		
		System.out.println( StringUtil.defaultString("/login.do,/login/directlogin.do").contains("/login/directlogin.do") );
	}
}

