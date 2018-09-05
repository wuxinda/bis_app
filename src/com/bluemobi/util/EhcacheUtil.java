package com.bluemobi.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appcore.util.ConfPathUtil;

/**
 * ehcache工具类
 * 
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2016-5-18 下午1:34:30
 * 
 */
public class EhcacheUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(EhcacheUtil.class);
    
    private static CacheManager manager = null;

    private static final String DEFAULT_CACHE_NAME = "defaultCache";

    static {
        init();
    }

    private static void init() {
        String confPath = ConfPathUtil.getConfPath();
        manager = CacheManager.newInstance(confPath + "cache/ehcache.xml");

    }

    /**
     * 从ehcache缓存中获取值
     * 
     * @author haojian
     * @date 2016-5-18 下午1:32:37
     * @param cacheName
     * @param cacheKey
     * @return
     * @throws Exception
     * @return T
     */
    public static <T> T get(String cacheName, String cacheKey) {

        Cache cache = manager.getCache(cacheName);

        Element e = cache.get(cacheKey);
        if (e == null) {
            return null;
        }
        
		@SuppressWarnings("unchecked")
		T t = (T) e.getObjectValue();
        
        LOGGER.debug("从缓存中获取数据！，缓存名:【{}】,唯一key：【{}】，数据:【{}】", new Object[]{cacheName, cacheKey, t} );
        
        return t;
    }

    /**
     * 设置值到ehcache缓存中
     * 
     * @author haojian
     * @date 2016-5-18 下午1:32:47
     * @param cacheName
     * @param cacheKey
     * @param t
     * @throws Exception
     * @return void
     */
    public static <T> void put(String cacheName, String cacheKey, T t) {

        LOGGER.info("添加数据到缓存！，缓存名:【{}】,唯一key：【{}】，数据:【{}】", new Object[]{cacheName, cacheKey, t} );
        
        Cache cache = manager.getCache(cacheName);

        Element element = new Element(cacheKey, t);
        
        cache.put(element);

    }

    /**
     * 从ehcache中删除一个值
     * 
     * @author haojian
     * @date 2016-5-18 下午1:41:11
     * @param cacheName
     * @param cacheKey
     * @return void
     */
    public static <T> void remove(String cacheName, String cacheKey) {

        Cache cache = manager.getCache(cacheName);
        
        cache.remove(cacheKey);

    }

    /**
     * 从默认的ehcache缓存中取值
     * 
     * @author haojian
     * @date 2016-5-18 下午4:06:33
     * @param cacheKey
     * @return
     * @return T
     */
    public static <T> T getFromDefault(String cacheKey) {
        T t = EhcacheUtil.get(EhcacheUtil.DEFAULT_CACHE_NAME, cacheKey);
        return t;
    }

    /**
     * 将值放入到默认的ehcache缓存中
     * 
     * @author haojian
     * @date 2016-5-18 下午4:06:50
     * @param cacheKey
     * @param t
     * @return void
     */
    public static <T> void putToDefault(String cacheKey, T t) {
        EhcacheUtil.put(EhcacheUtil.DEFAULT_CACHE_NAME, cacheKey, t);
    }

    /**
     * 从默认的ehcache缓存中删除值
     * 
     * @author haojian
     * @date 2016-5-18 下午4:07:15
     * @param cacheKey
     * @return void
     */
    public static <T> void removeFromDefault(String cacheKey) {
        EhcacheUtil.remove(EhcacheUtil.DEFAULT_CACHE_NAME, cacheKey);
    }

    public static void main(String[] args) {
        System.out.println(111);
        EhcacheUtil.put("advertCache", "key-1", "value-aaaaaaaaaaaaaaaaaaaa");
        System.out.println(222);
//        System.out.println(EhcacheUtil.get("advertCache", "key-1"));
        System.out.println(333);
    }

}
