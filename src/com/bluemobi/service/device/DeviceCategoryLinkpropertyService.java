package com.bluemobi.service.device;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【设备分类绑定连接属性表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceCategoryLinkpropertyService extends MybatisBaseService {
	
	/**根据设备分类id获取设备连接属性
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getLinkByCategoryId(Integer categoryId);
}
