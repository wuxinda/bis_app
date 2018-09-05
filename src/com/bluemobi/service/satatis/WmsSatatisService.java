package com.bluemobi.service.satatis;

import java.util.List;
import java.util.Map;


/**
 * 档案统计接口
 * @author Administrator
 *
 */
public interface WmsSatatisService {    
    
    /**
     * 获取档案统计数据
     * @param map
     * @return
     */
    Map<String,Object> selectAmsArchivesStatis(Map<String,Object> map);
    /**
     * 获取环境统计数据
     * @param map
     * @return
     */
    Map<String,Object> selectHumitureStatis(Map<String,Object> map);
    /**
     * 获取报警统计数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAlarmStatis(Map<String,Object> map);

}

