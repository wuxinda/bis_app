package com.bluemobi.conf;

import java.util.Properties;

import com.appcore.conf.AbstractPropertiesConfig;

/**
 *  配置加载
 * @author haojian
 * Apr 27, 2012 3:21:26 PM
 */
public class Config extends AbstractPropertiesConfig{
    
    private static final long serialVersionUID = 1L;
    
    private static Properties p = getProperties("config.properties");
    
    /**项目名*/
    public static final String SITE_NAME = p.getProperty("SITE_NAME");
    /**项目访问url*/
    public static final String BASE_URL = p.getProperty("BASE_URL");
    /**静态资源服务器地址，存放js库,css,网站原生图片 等*/
    public static final String STATIC_URL = p.getProperty("STATIC_URL");
    /**图片服务器地址，存放用户上传的图片、视频等*/
    public static final String IMG_URL = p.getProperty("IMG_URL");
    /**未登录等操作时，重定向地址*/
    public static final String SEND_REDIRECT = p.getProperty("SEND_REDIRECT");
    /**临时图片保存路径*/
    public static final String TEMP_IMG_URL = p.getProperty("TEMP_IMG_URL");
    /**taskhandle-quartz是否开启标示 1.开启 0.关闭*/
    public static final String QUARTZ_IS_START = p.getProperty("quartz.is.start");
    
    /**中信接口访问地址url*/
    public static final String ZX_API_URL = p.getProperty("ZX_URL");
    /**中信服务是否启用*/
    public static final String ZX_IS_STAR = p.getProperty("ZX_IS_STAR");
    
    
    
}
