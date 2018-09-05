package com.bluemobi.dao.rfid;

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
public interface RfidIntyDao extends MyBatisBaseDao {
	/**
	 * 	按天查询rfid出入库数据
	 * @return
	 */
	List<Map<String,Object>> getRfidIntyByday(Map<String,Object> map);
	 /**
     * 
     * 查询rfid盘点总量
     * @return
     */
    
    Map<String,Object> getCountRfid(Map<String,Object> map);
    /**
     * 按状态查询盘点信息
     */
	List<Map<String,Object>> getRfidByType(Map<String,Object> map);
	/**
	 * 获取搜索界面的总数
	 */
	int pageSearchCount(Map<String,Object> map);
	 /**
		 * 获取最新rfid记录
		 * 
		 */
		List<Map<String, Object>> getNewRfid();
		/**
		 * 按查询rfid出入库数据
		 * @return
		 */
		List<Map<String,Object>> getRfidIntyList(Map<String,Object> map);
		/**
		 * 按查询rfid出入库数据页数统计
		 * @return
		 */
		int getRfidIntyListcount(Map<String,Object> map);
		/**
		 * 按时间段查询rfid盘点记录
		 * @return
		 */
		List<Map<String,Object>> getRfidIntyByTimeArea(Map<String,Object> map);	
		
		List<Map<String,Integer>> getSumCuoDang();
}
