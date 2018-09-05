package com.bluemobi.service.wms;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【库房用户表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-07
 * 
 */
public interface WmsUserService extends MybatisBaseService {

	/**
	 * 查询库房用户
	 * @param map
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月10日
	 * 
	 */
	public List<Map<String,Object>> selectWmsAllUser(Map<String, Object> map);
	/**
	 * 查询用户所有库房
	 * @param map
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月11日
	 * 
	 */
	public List<Integer> selectUserStoreIds(String userId);
	/**
	 * 根据档案获取所属用户
	 * @param userId
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月12日
	 * 
	 */
	public List<Map<String,Object>> selectUserByAms(Map<String, Object> map);
	

}
