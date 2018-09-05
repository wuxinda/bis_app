package com.bluemobi.util;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

public class StringUtils {
	public static String htmlEscape(String html) {
		return HtmlUtils.htmlEscape(html);
	}

	public static String javascriptEscape(String html) {
		return JavaScriptUtils.javaScriptEscape(html);
	}

	/**
	 * 过滤掉html中script和style标签的方法
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String escapeTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

		Pattern p_script = Pattern.compile(regEx_script,
				Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern
				.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 按字节长度截取字符串
	 * 
	 * @param str
	 *            将要截取的字符串参数
	 * @param byteLen
	 *            截取的字节长度
	 * @param symbol
	 *            字符串末尾补上的字符串
	 * @return 返回截取后的字符串
	 */
	public static String byteSubstr(String str, int byteLen, String symbol) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && byteLen > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		if (byteLen == reInt || (byteLen == reInt - 1))
			reStr += symbol;
		return reStr;
	}

	/**
	 * 判定对象是否为空
	 * 
	 * @param object
	 * @return true...null或者空 / false...not null或者非空
	 */
	public static boolean isEmpty(String object) {
		if (object == null) {
			return true;
		}

		if (object instanceof String) {
			if ("".equals(((String) object))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判定对象是否不为空
	 * 
	 * @param object
	 * @return true...not null或者非空 / false...null或者空
	 */
	public static boolean isNotEmpty(String object) {
		return !(isEmpty(object));
	}

	/**
	 * 搜索字符或子字符串的最后一次出现(不区分大小写)
	 * 
	 * @param object
	 *            搜索对象字符串
	 * @param compare
	 *            子字符串
	 * @return 最后一次出现的位置
	 */
	public static int lastIndexOfIgnoreCase(String object, String compare) {

		if (isEmpty(object) || isEmpty(compare)) {
			return -1;
		}

		String tempObject = object.toLowerCase();
		String tempCompare = compare.toLowerCase();

		return tempObject.lastIndexOf(tempCompare);

	}

	/**
	 * 判定对象是否为空
	 * 
	 * @param object1
	 *            对象1
	 * @param object2
	 *            对象2
	 * @return boolean
	 */
	public static boolean equals(String object1, String object2) {
		if (object1 == null) {
			return false;
		}

		return object1.equals(object2);
	}

	/**
	 * 对象为空时，返回空字符串；否则返回对象的字符串形式
	 * 
	 * @param object
	 * @return true...not null或者非空 / false...null或者空
	 */
	public static String nullToEmpty(Object object) {
		return object == null ? "" : String.valueOf(object);
	}

	/**
	 * 转UTF-8编码
	 * 
	 * @param object
	 * @return true...not null或者非空 / false...null或者空
	 */
	public static String toUTF8String(String object) {
		String utf8String = "";

		if (StringUtils.isEmpty(object)) {
			return utf8String;
		}

		try {
			utf8String = java.net.URLDecoder.decode(object, "UTF-8");
			object.getBytes("UTF-8").toString();
			utf8String = StringEscapeUtils.escapeJava(object);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return utf8String;
	}

	public static String getUTF8StringFromGBKString(String gbkStr) {
		return getUTF8BytesFromGBKString(gbkStr).toString();
	}

	public static byte[] getUTF8BytesFromGBKString(String gbkStr) {
		int n = gbkStr.length();
		byte[] utfBytes = new byte[3 * n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			int m = gbkStr.charAt(i);
			if (m < 128 && m >= 0) {
				utfBytes[k++] = (byte) m;
				continue;
			}
			utfBytes[k++] = (byte) (0xe0 | (m >> 12));
			utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
			utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
		}
		if (k < utfBytes.length) {
			byte[] tmp = new byte[k];
			System.arraycopy(utfBytes, 0, tmp, 0, k);
			return tmp;
		}
		return utfBytes;
	}

	public static String repalceQuotation(String objectString) {
		String reString = "";
		reString = objectString.replaceAll("\"", "\\\\\"");
		return reString;
	}

	/**
	 * 取得对象的String类型值
	 * 
	 * @param String
	 * @return 对象为空时返回空字符串
	 */
	public static String nullToString(Object object) {
		return object == null ? "" : object.toString();
	}

	@SuppressWarnings("rawtypes")
	public static String join(List arrayList, String separator) {
		Object[] array = arrayList.toArray();
		if (array == null) {
			return null;
		}
		int arraySize = array.length;
		int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
				.toString().length()) + 1) * arraySize);
		StringBuffer buf = new StringBuffer(bufSize);

		for (int i = 0; i < arraySize; i++) {
			if (i > 0) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * JSON字符串特殊字符处理，比如：“\A1;1300”
	 * 
	 * @param s
	 * @return String
	 */
	public static String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			// case '\n':
			// sb.append("\\n");
			// break;
			// case '\r':
			// sb.append("\\r");
			// break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串比较(不区分大小写)
	 * 
	 * @param object1
	 *            对象1
	 * @param object2
	 *            对象2
	 * @return boolean
	 */
	public static boolean equalsIgnoreCase(String object1, String object2) {
		if (object1 == null) {
			return false;
		}

		return object1.equalsIgnoreCase(object2);
	}

}
