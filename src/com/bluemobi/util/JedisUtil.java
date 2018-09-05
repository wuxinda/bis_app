package com.bluemobi.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

import com.appcore.util.JsonUtil;
/**
 * Redis操作公共类
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2016-1-27 下午8:26:00 
 *
 */
public class JedisUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisUtil.class);
    
    /**
     * 将一组对象存到redis的一个map中
     * @author haojian
     * @date 2016-1-27 下午8:15:46 
     * @param objectMapName redis中的map的名字
     * @param objectMap  key：对象唯一id， value：对象
     * @return String
     */
    public static <K, V> String putObjectsToMap(Jedis jedis, String objectMapName, Map<K, V> objectMap) {
        
        if(objectMap.isEmpty()){
            LOGGER.error("objectMapName【{}】对应的objectMap为空，不能往redis中存储！", objectMapName);
            return null;
        }

        Map<String,String> map = new HashMap<String,String>();
        
        for(K objectKey : objectMap.keySet()){
            V object = objectMap.get(objectKey);
            String key = objectKey.toString();
            String value = JsonUtil.getJsonString(object);
            map.put(key, value);
        }
        String str = jedis.hmset(objectMapName, map);
        
        return str;
        
    }
    
    /**
     *  将一个对象存到redis的一个map中
     * @author haojian
     * @date 2016-1-27 下午9:10:31 
     * @param jedis
     * @param objectMapName
     * @param k
     * @param v
     * @return
     * @return Long
     */
    public static <K, V> Long putObjectToMap(Jedis jedis, String objectMapName, K k, V v) {
        
        String json = JsonUtil.getJsonString(v);
        Long l = jedis.hset(objectMapName, k.toString(), json);
        
        return l;
        
    }
    
    /**
     * 根据一组id列表从redis的map中获取一组对象
     * @author haojian
     * @date 2016-1-27 下午8:24:02 
     * @param jedis
     * @param objectMapName redis中的map的名字
     * @param ids 对象唯一id集合
     * @param clazz  对象对应的类
     * @return
     * @return List<T>
     */
    public static <T> List<T> getObjectsFromMapByIds(Jedis jedis, String objectMapName, Collection<String> ids, Class<T> clazz){
        
        List<String> jsonList = jedis.hmget(objectMapName, JedisUtil.collectionToStringArray(ids) );
        List<T> objectList = new ArrayList<T>();
        for(String json : jsonList){
            T object = JsonUtil.getObject(json, clazz);
            objectList.add(object);
        }
        return objectList;
        
    }
    
    
    /**
     * 根据一个id列表从redis的map中获取一个对象
     * @author haojian
     * @date 2016-1-27 下午9:04:10 
     * @param jedis
     * @param objectMapName redis中的map的名字
     * @param id 对象唯一id
     * @param clazz 对象对应的类
     * @return
     * @return T
     */
    public static <T> T getObjectFromMapById(Jedis jedis, String objectMapName, String id, Class<T> clazz){
        
        String json = jedis.hget(objectMapName, id);
        T object = JsonUtil.getObject(json, clazz);
        
        return object;
        
    }

    
    /**
     * 将主id和明细id关联起来
     * 
     * @author haojian
     * @date 2016-1-27 下午8:00:22
     * @param jedis
     * @param mainIdName 主id名称，作为存放子id列表的 key的前缀
     * @param mainId   主id
     * @param detailIds 子id集合
     * @return Long
     */
    public static <T> Long putMainIdAndDetailIds(Jedis jedis, String mainIdName, Object mainId, Collection<T> detailIds) {

        if(detailIds.isEmpty()){
            LOGGER.error("mainIdName【{}】对应的detailIds为空，不能往redis中存储！", mainIdName);
            return null;
        }

        String key = mainIdName + mainId;
        jedis.del(key);
        Long l = jedis.rpush(key, JedisUtil.collectionToStringArray(detailIds) );
        return l;
    }

    /**
     * 通过主id获取子id列表
     * 
     * @author haojian
     * @date 2016-1-27 下午8:02:29
     * @param jedis
     * @param mainIdName 主id名称，作为存放子id列表的 key的前缀
     * @param mainId 主id
     * @return
     * @return List<String>
     */
    public static List<String> getDetailIdsByMainId(Jedis jedis, String mainIdName, Object mainId) {

        List<String> detailIdList = jedis.lrange(mainIdName + mainId, 0, -1);

        return detailIdList;

    }
    
    /**
     * 将对象数组转换成字符串数组
     * 慎用  coll.toArray(ss)
     * @author haojian
     * @date 2016-1-28 下午2:13:50 
     * @param coll
     * @return
     * @return String[]
     */
    public static <T> String[] collectionToStringArray(Collection<T> coll){
        String[] ss = new String[coll.size()];
        int i = 0;
        for(T t:coll){
            ss[i] = t.toString();
            i++;
        }
        return ss;
    }

    /**
     * 将字符串集合装换成Integer数组
     * @author haojian
     * @date 2016-2-22 下午3:00:10 
     * @param list
     * @return
     * @return Integer[]
     */
    public static Integer[] strCollectionToIntegerArray(Collection<String> coll){
        
        Integer[] ii = new Integer[coll.size()];
        int i = 0;
        for(String s:coll){
            ii[i] = Integer.parseInt(s);
            i++;
        }
        return ii;
    }

}
