package com.bluemobi.dao.device;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.device.DeviceCategoryBrand;

/**
 * 【设备分类绑定品牌表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceCategoryBrandDao extends MyBatisBaseDao {

	/**
	 * 根据brandId批量删除分类信息
	 * @param deviceCategoryBrandList
	 */
	void deleteByBrandId(List<DeviceCategoryBrand> deviceCategoryBrandList);

	/**
	 * 根据brandId批量增加分类信息
	 * @param brandCategories
	 */
	void insertDeviceBrandCategories(List<DeviceCategoryBrand> brandCategories);
	
	/**根据设备分类id获取品牌列表
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getBrandByCategoryId(Integer categoryId);
}
