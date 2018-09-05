package com.bluemobi.service.perception;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【温湿度感知数据收集表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
public interface PerceptionHcsService extends MybatisBaseService {
    
    /**
     * 温湿度报表（只限于产品使用）
     * @param type
     * @param time
     * @return
     */
    List<Map<String, Object>> getHumitureReport(Map<String, Object> map);
    /**
     * 查询具有温湿度数据的库房列表
     * @param
     * @param
     * @return
     */
    List<Map<String, Object>> getHumitureWms(Map<String, Object> map);
    /**
     * 获取日温湿度
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getHumByDay(Map<String, Object> map);
    /**
     * 获取月温湿度
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getHumByMon(Map<String, Object> map);
    /**
     * 获取年温湿度
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getHumByYear(Map<String, Object> map);

}
