package com.bluemobi.dao.perception;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.perception.PerceptionHcs;

/**
 * 【温湿度感知数据收集表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
public interface PerceptionHcsDao extends MyBatisBaseDao {

    /**
     * 获取设备最后以一条log
     * 
     * @param map
     * @return
     */
    public List<PerceptionHcs> getDevicelastHcsLog(Map<String, Object> map);

    /**
     * 获取库房实时平均温湿度 参数为库房id
     * 
     * @param storeId
     *            库房Id 为null时返回所有库房各自的平均温湿度
     * @return
     */
    public List<Map<String, Object>> getWmsAvgHumTem(Map<String, Object> map);

    /**
     * 查询温湿度报表年月日个最新的记录
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getHumitureReport(Map<String, Object> map);
    /**
     * 查询具有温湿度数据的库房列表
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getHumitureWms(Map<String, Object> map);
    
    /**
     * 获取当天温湿度最大最小平均值
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getMaxMinAvgHcsForToday(Map<String, Object> map);
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
