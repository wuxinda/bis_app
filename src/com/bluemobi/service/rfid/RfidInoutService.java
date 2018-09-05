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
public interface RfidInoutService extends MybatisBaseService {
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	List<Map<String,Object>> searchRfidInout(Map<String,Object> map) throws Exception;
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	Map<String,Object> getRfidInoutList(Map<String,Object> map);
}
