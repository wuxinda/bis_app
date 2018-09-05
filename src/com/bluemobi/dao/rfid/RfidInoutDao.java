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
public interface RfidInoutDao extends MyBatisBaseDao {
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	List<Map<String,Object>> searchRfidInout(Map<String,Object> map);
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	List<Map<String,Object>> getRfidInoutList(Map<String,Object> map);
	/**
	 * 按查询rfid出入库数据页数统计
	 * @return
	 */
	int getRfidInoutListcount(Map<String,Object> map);
	/**
	 * 按查询rfid出入库数据页数统计
	 * @return
	 */
	int getArchivessyl();
	/**
	 * 按时间段查询rfid出入记录
	 * @return
	 */
	List<Map<String,Object>> getRfidInoutByTimeArea(Map<String,Object> map);	
}
