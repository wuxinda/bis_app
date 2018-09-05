package com.bluemobi.service.device;

import com.appcore.service.MybatisBaseService;

/**
 * 【设备品牌表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceBrandService extends MybatisBaseService {
	/**
	 * 更新分类属性绑定信息
	 * @param categoryIds
	 * @param brandId
	 */
	void updateCategoryProperty(String categoryIds, Integer brandId);
}
