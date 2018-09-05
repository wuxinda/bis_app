package com.bluemobi.util;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import com.google.gson.Gson;

/**
 * 
 * <p>
 * JSON操作类
 * </p>
 * 
 * @author jianghaidong
 * @date 2011-10-13 上午11:46:50
 * @since 1.0
 */
public class JsonUtil {

	private static Logger log = Logger.getLogger(JsonUtil.class);

	public static ObjectMapper objMapper = new ObjectMapper();
	
	public static Gson gson = new Gson();

	/**
	 * 对象转换成字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		try {
			return objMapper.writeValueAsString(obj);
		} catch (Exception e) {
			log.error("Exception..." + e.toString(), e);
		}
		return "";
	}

	/**
	 * JSON字符串转换成对象
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static <T> T toObject(String jsonStr, Class<T> cls) {
		try {
			return objMapper.readValue(jsonStr, cls);
		} catch (Exception e) {
			log.error("Exception..." + e.toString(), e);
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param typeReference
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		try {
			return objMapper.readValue(json, typeReference);
		} catch (Exception e) {
			log.error("Exception..." + e.toString(), e);
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param javaType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, JavaType javaType) {
		try {
			return objMapper.readValue(json, javaType);
		} catch (Exception e) {
			log.error("Exception..." + e.toString(), e);
		}
		return null;
	}
	
	public static String toGson(Object obj) {		
		return gson.toJson(obj);
	}
	
	public static <T> T fromObject(String json, Class<T> cls) {
		return gson.fromJson(json, cls);
	}

	public static void main(String[] args) {
	}
}
