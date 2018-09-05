package com.bluemobi.dao.ecs;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
public interface EcsManageDao extends MyBatisBaseDao {
	/**
	 * 获取能耗总数
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTotalData(Map<String, Object> map);
	/**
	 * 获取库房能耗统计
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getStoreEcs(Map<String, Object> map);
	/**
	 * 获取分项实际能耗统计
	 * 
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> getTypeEcs(Map<String, Object> map);
}
