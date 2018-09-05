package com.bluemobi.service.system;

import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【请求url，对应的请求名，用于通过url显示用户操作】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public interface SystemUrlService extends MybatisBaseService {

	/**
	 * 获取行为轨迹url对应动作名称map
	 * @return
	 */
	Map<String,String> getActionNameMap();

}
