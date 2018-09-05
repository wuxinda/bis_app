package com.bluemobi.service.ams;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【档案操作纪录表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesActlogService extends MybatisBaseService {

    /**
     * 根据档案id和操作类型批量插入操作记录
     * 
     * @param archivesIdsIn
     * @param string操作类型
     * @param userId操作人
     */
    void insertArchivesActlogs(String[] archivesIdsIn, String type, String userId, String storeId, String areaId);

    /**
     * 获取年月日分时段档案进出统计
     * 
     * @param object
     * @return
     */
    List<Map<String, Object>> getNewInOutAms(Map<String, Object> map);

    /**
     * 
     * 批量插入档案操作记录3.1
     * @param parmMap
     */
    void insertAmsActlogs(Map<String, Object> parmMap);

    
}
