package com.bluemobi.push.jpush;

import java.util.Properties;

import com.appcore.conf.AbstractPropertiesConfig;

/**
 * 极光推送配置加载
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-10-16 下午5:42:41 
 *
 */
public class JpushConfig extends AbstractPropertiesConfig{
    
    private static final long serialVersionUID = 1L;
    
    private static Properties p = getProperties("jpush.properties");
    
    
    /**应用唯一标示*/
    public static final String APP_KEY = p.getProperty("APP_KEY");
    /**秘钥*/
    public static final String MASTER_SECRET = p.getProperty("MASTER_SECRET");
    /**最大重试次数*/
    public static final int MAX_RETRY_TIMES = Integer.parseInt(p.getProperty("MAX_RETRY_TIMES"));
    
    
    
}
