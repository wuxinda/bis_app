package com.bluemobi.service.rfid;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
public interface RfidIntyService extends MybatisBaseService {
	/**
	 * 	按天查询rfid出入库数据
	 * @return
	 */
	Map<String,Object> getRfidIntyByday(Map<String,Object> map);
	/**
	 * 
	 * 查询rfid盘点的占比
	 * @param map
	 * @return
	 */
	Map<String,Object> getRfid(Map<String,Object> map);
	/**
     * 按状态查询盘点信息
     */
	List<Map<String,Object>> getRfidByType(Map<String,Object> map);
	/**
	 * 获取总条数
	 * @param map
	 * @return
	 */
	public int getMaxPage(Map<String,Object> map);
	/**
	 * 获取最新rfid记录
	 * 
	 */
	List<Map<String, Object>> getNewRfid();
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	Map<String,Object> getRfidIntyList(Map<String,Object> map);
}
