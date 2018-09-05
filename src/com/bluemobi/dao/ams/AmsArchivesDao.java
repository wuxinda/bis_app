package com.bluemobi.dao.ams;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【档案详情表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesDao extends MyBatisBaseDao {
   
    /**
     * 获取档案操作统计数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectActStatis(Map<String,Object> map);
    /**
     * 获取档案在库状态统计数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectInflagStatis(Map<String,Object> map);
    /**
     * 获取档案保密等级统计数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectSecurityStatis(Map<String,Object> map);
    /**
     * 获取档案保管年限统计数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectKeepyearStatis(Map<String,Object> map);
    /**
     * 获取档案组卷方式统计数据
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAmsTypeStatis(Map<String,Object> map);
    /**
     * 获取档指定档案类型数量
     * @param map
     * @return
     */
    Integer selectAmsStatusCount(Map<String,Object> map);
    /**
     * 根据Id批量更新档案状态
     * @param map
     * @return
     */
    Integer updateAmsArchivesStatus(Map<String,Object> map);
    /**
     * 取消申请跟新档案
     * @param map
     * @return
     */
    Integer taskCancelUpdate(Map<String,Object> map);
    /**
     * 获取档案统计按库房分组
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAmsgroupByStore(Map<String,Object> map);
    /**
     * 通过档案号查询申请单档案列表3.1 
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAmsListByArchiveno(Map<String,Object> map);
    /**
     * 根据档案id更新档案在库数量
     * @param map type：0加1；1减1  
     * @return
     * @author huangzuoguo
     * @date 2017年7月20日
     * 
     */
    void updateAmsNowNumber(Map<String,Object> map);
    /**
     * 
     * 查询档案总量
     * @return
     */
    
    Map<String,Object> getCountAms(Map<String,Object> map);
    /**
     * 
     * 档案查询列表
     * @return
     */
    
    List<Map<String,Object>> getArchives(Map<String,Object> map);
    /**
     * 
     * 按档案类型查询总量
     * @return
     */
    
    List<Map<String,Object>> getArchivesCount(Map<String,Object> map);
    /**
     * 
     * 按在库状态查询总量
     * @return
     */
    
    List<Map<String,Object>> getInflagCount(Map<String,Object> map);
    /**
     * 
     * 案卷号查询
     * @return
     */
    
    List<Map<String,Object>> getArchivesQzh(Map<String,Object> map);
    /**
     * 
     * 按档案号和档案类型查询档案
     * @return
     */
    Map<String,Object> findArchivesByNoAndType(Map<String,Object> map);
    /**
     * 
     * 档案搜索
     * @return
     */
    List<Map<String,Object>> searchArchives(Map<String,Object> map);
    /**
     * 
     * 档案图表
     * @return
     */
    List<Map<String,Object>> selectStrore(Map<String,Object> map);
    /**
     * 
     * 档案在架图表
     * @return
     */
    List<Map<String,Object>> selectInflag(Map<String,Object> map);
    /**
     * 
     * 档案搜索
     * @return
     */
    int searchArchivesCount(Map<String,Object> map);
    /**
     * 
     * 档案搜索
     * @return
     */
    List<Map<String,Object>> selectArchivesListFroPlace(Map<String,Object> map);
    /**
     * 
     * 档案搜索
     * @return
     */
    int getArchiveYearAdd(Map<String,Object> map);
    /**
     * 
     * 档案搜索首页
     * @return
     */
    List<Map<String,Object>> indexFindArchives(Map<String,Object> map);
    /**
     * 
     * 档案搜索条数统计
     * @return
     */
    int indexFindArchivesCount(Map<String,Object> map);
}
