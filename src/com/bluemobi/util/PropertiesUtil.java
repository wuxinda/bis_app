package com.bluemobi.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;

public class PropertiesUtil {

	protected static Properties prop = new Properties();

	private final static PropertyResourceBundle config = (PropertyResourceBundle) PropertyResourceBundle
			.getBundle("hardware");

	public static void load(String fileName) {
		InputStream stream = null;
		try {
			stream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
			if (stream != null)
				prop.load(stream);
		} catch (IOException e) {
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public static String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }
	
	public static String getProperty(String key) {
        return prop.getProperty(key);
    }
	
	public final static String get(String property) {
		try {
			return config.getString(property);
		} catch (Exception e) {
			System.out.println("Load System Property error := " + e.getMessage());
			return null;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String aa = PropertiesUtil.get("ZKACCESS.WEBSERVICE");
//		System.out.println(aa);
		
		PropertiesUtil.load("jdbc.properties");
		String url = PropertiesUtil.getProperty("jdbc.url");
		System.out.println(url);
	}
}
