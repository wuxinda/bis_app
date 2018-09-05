package com.bluemobi.dao.wms;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【库房分区表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface WmsStoreAreaDao extends MyBatisBaseDao {
	/**
	 * 通过库区id查询库房信息
	 * @param map
	 * @return
	 */
	Map<String,Object> selectStoreAreaIdByCode(Map<String,String> map);
	
	/**
	 * 通过库房id列表查询库区
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectStoreAreaIdByStores(Map<String,Object> map);

}
