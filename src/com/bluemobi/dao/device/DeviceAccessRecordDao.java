package com.bluemobi.dao.device;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
public interface DeviceAccessRecordDao extends MyBatisBaseDao {
	/**
     * 获取门禁数据列表
     */
	List<Map<String,Object>> getAccessDataList(Map<String,Object> map);
	/**
     * 按时间统计门禁数据列表
     */
	List<Map<String,Object>> getAccessDataCount(Map<String,Object> map);
}
