package com.bluemobi.service.device;

import com.appcore.service.MybatisBaseService;

/**
 * 【设备分类表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceCategoryService extends MybatisBaseService {
	/**
	 * 更新连接属性绑定信息
	 * @param linkpropertyIds
	 * @param categoryId
	 */
	public void updateCategoryLinkproperty(String linkpropertyIds, Integer categoryId);
	/**
	 * 更新操作属性绑定信息
	 * @param actionpropertyIds
	 * @param categoryId
	 */
	public void updateCategoryActionproperty(String actionpropertyIds, Integer categoryId);
}
