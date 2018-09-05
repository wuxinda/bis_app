package com.bluemobi;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 初始化缓存数据等
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-12-15 下午3:42:16
 * 
 */
@Component(value = "serverInit")
public class ServerInit { 

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerInit.class);

    public boolean init() {
    	LOGGER.info("开始初始化...");
    	
        // beanutils 初始化设置
        ConvertUtils.register(new org.apache.commons.beanutils.converters.DateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.BigDecimalConverter(null), java.math.BigDecimal.class);
        
        LOGGER.info("初始化结束...");
        return true;
    }

}
