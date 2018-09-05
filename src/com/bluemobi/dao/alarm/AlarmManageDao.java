package com.bluemobi.dao.alarm;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【报警管理表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AlarmManageDao extends MyBatisBaseDao {
    /**
     * 获取报警统计数据
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> selectAlarmStatis(Map<String, Object> map);

    /**
     * 年月日报警统计
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getAlarmSata(Map<String, Object> map);
    
    /**
     * 报警统计
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getAlarmCount();
}
