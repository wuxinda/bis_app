/**
 * Project Name:nodo 
 * File Name:EnumUtil.java 
 * Package Name:com.bluemobi.util 
 * Date:2015年12月28日下午4:27:32 
 */
package com.bluemobi.util;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * ClassName: EnumUtil
 * Date: 2015年12月28日下午4:27:32

 * @author kevin
 * @version 
 * @since JDK 7
 */
public class EnumUtil {

	public static String getText(Class<?> ref, Object code) {
		return parseEnum(ref).get(String.valueOf(code).trim());
	}

	public static <T> Map<String, String> parseEnum(Class<T> ref) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		if (ref.isEnum()) {
			T[] ts = ref.getEnumConstants();
			for (T t : ts) {
				String text = getInvokeValue(t, "getDesc");
				Enum<?> tempEnum = (Enum<?>) t;
				if (text == null) {
					text = tempEnum.name();
				}
				String code = getInvokeValue(t, "getCode");
				if (code == null) {
					
					code = String.valueOf(tempEnum.ordinal()).trim();
				}
				map.put(code, text);
			}
		}
		return map;
	}

	static <T> String getInvokeValue(T t, String methodName) {
		Method method = MethodUtils.getAccessibleMethod(t.getClass(),
				methodName);
		if (null == method) {
			return null;
		}
		try {
			String text = String.valueOf(method.invoke(t)).trim();
			return text;
		} catch (Exception e) {
			return null;
		}
	}

}

