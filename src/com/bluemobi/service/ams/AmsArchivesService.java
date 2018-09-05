package com.bluemobi.service.ams;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.ams.AmsArchives;

import net.sf.json.JSONArray;

/**
 * 【档案详情表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesService extends MybatisBaseService {

	/**
	 * 档案同步接口
	 * 
	 * @param paraMap
	 * @return
	 */
	public String confirmarchivesSync(List<Map<String, Object>> datass);

	/**
	 * 数据字典同步
	 * 
	 * @Date:2017年5月12日
	 * @author:Tony
	 * @param paraMap
	 * @return
	 */
	public String confirmdictSync(Map<String, Object> paraMap);

	/**
	 * 获取档案统计
	 * 
	 */
	Map<String, Object> getCountAms(Map<String, Object> Map);

	/**
	 * rfid上下架数据同步
	 * 
	 * @Date:2017年5月12日
	 * @author:Tony
	 * @param paraMap
	 * @return
	 */
	public String confirmrfidsxjSync(List<Map<String, Object>> datass);
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
   Map<String,Object> searchArchives(Map<String,Object> map);
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
     * 档案分类调档统计图表
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAmsArchivesByCategory(Map<String,Object> map);
    
    /**
     * 库房分类调档统计图表
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAmsArchivesByStoreId(Map<String,Object> map);
    
    /**
     * 获取所有档案数量
     * @return
     */
    Integer selectCountAll();
    
    /**
     * 档案异常数据查询
     * @return
     */
    Map<String,Object> selectAlarmArchives();
}
