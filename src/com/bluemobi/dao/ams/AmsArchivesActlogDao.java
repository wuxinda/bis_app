package com.bluemobi.dao.ams;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【档案操作纪录表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesActlogDao extends MyBatisBaseDao {

    /**
     * 批量插入档案操作记录
     * 
     * @param parmMapin
     */
    void insertArchivesActlogs(Map<String, Object> parmMapin);
    /**
     * 批量插入档案操作记录3.1
     * 
     * @param parmMap
     */
    void insertAmsActlogs(Map<String, Object> parmMap);

    /**
     * 获取最新年月日各自档案操作记录
     * 
     * @param parmMapin
     * @return
     */
    List<Map<String, Object>> getNewInOutAms(Map<String, Object> parmMapin);

}
