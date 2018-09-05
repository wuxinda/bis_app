package com.bluemobi.dao.wms;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.wms.WmsStore;

/**
 * 【库房表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface WmsStoreDao extends MyBatisBaseDao {
	/**
	 * 通过库房id查询库房信息
	 * @param map
	 * @return
	 */
	Map<String,Object> selectObjectById(Map<String,Object> map);
	/**
	 * 通过库房id查询库房信息
	 * @param map
	 * @return
	 */
	Map<String,Object> selectStoreIdByCode(Map<String,Object> map);
	/**
     * 根据库房名获取库房
     * @param parmMap
     * @return
     * @author huangzuoguo
     * @date 2017年7月12日
     * 
     */
    public Map<String, Object> getStoreIdByName(Map<String, Object> parmMap);
    /**
	 * 通过库房id列表查询库房信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectStoreMsgByStoresId(Map<String,Object> map);
	/**
	 * 通过库房id列表查询库房信息
	 * @param map
	 * @return
	 */ 
	List<WmsStore> selectObjectListByUser(Map<String,Object> map);
	/**
	 * 通过库房id列表查询库房信息
	 * @param map
	 * @return
	 */ 
	WmsStore selectObjectByCode(WmsStore wmsStore);
}
