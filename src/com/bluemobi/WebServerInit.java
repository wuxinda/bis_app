package com.bluemobi;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bluemobi.conf.Config;
import com.bluemobi.service.admin.AdminPermissionCodeService;
import com.bluemobi.service.system.SystemUrlService;

/**
 * 初始化缓存数据等
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2015-12-15 下午3:42:16
 * 
 */
@Component(value = "webServerInit")
public class WebServerInit {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(WebServerInit.class);

	@Autowired
	private AdminPermissionCodeService adminPermissionCodeService;
	@Autowired
	private SystemUrlService systemUrlService;
/*	@Autowired
	private JedisPoolManager jedisPoolManager;*/

	public boolean init(ServletContext context) {

		// beanutils 初始化设置
		ConvertUtils
				.register(
						new org.apache.commons.beanutils.converters.DateConverter(
								null), java.util.Date.class);
		ConvertUtils
				.register(
						new org.apache.commons.beanutils.converters.BigDecimalConverter(
								null), java.math.BigDecimal.class);

		// 1、设置全局参数
		LOGGER.info("开始初始化【全局参数】...");
		long b = System.currentTimeMillis();
		context.setAttribute("BASE_URL", Config.BASE_URL);
		context.setAttribute("STATIC_URL", Config.STATIC_URL);
		context.setAttribute("IMG_URL", Config.IMG_URL);
		context.setAttribute("SITE_NAME", Config.SITE_NAME);
		context.setAttribute("TEMP_IMG_URL", Config.TEMP_IMG_URL);
		long e = System.currentTimeMillis();
		LOGGER.info("初始化结束【全局参数】成功...耗时【{}】秒", new Object[] { (e - b) / 1000d });

		// 2、设置权限基本数据
		Map<String, String> permissionCodeMap = adminPermissionCodeService
				.getPermissionCodeMap();
		context.setAttribute("permissionCodeMap", permissionCodeMap);

		// 3、设置操作轨迹url配置数据
		Map<String, String> systemUrlMap = systemUrlService.getActionNameMap();
		context.setAttribute("systemUrlMap", systemUrlMap);

/*		// 添加缓存测试
		Jedis jedis = null;
		try {
			jedis = jedisPoolManager.getResource();
			String name = jedis.get("name");
			jedisPoolManager.returnResourceObject(jedis);	
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("缓存服务器处理失败Exception:【{}】",
					new Object[] { ex });
		} finally {
			jedisPoolManager.returnResourceObject(jedis);
		}*/

		return true;
	}

}
