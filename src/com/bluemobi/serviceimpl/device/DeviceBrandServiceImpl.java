package com.bluemobi.serviceimpl.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceBrandDao;
import com.bluemobi.dao.device.DeviceCategoryBrandDao;
import com.bluemobi.po.device.DeviceCategoryBrand;
import com.bluemobi.service.device.DeviceBrandService;

/**
 * 【设备品牌表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceBrandService")
public class DeviceBrandServiceImpl extends MybatisBaseServiceImpl implements
		DeviceBrandService {

	@Autowired
	private DeviceBrandDao deviceBrandDao;
	@Autowired
	private DeviceCategoryBrandDao deviceCategoryBrandDao;

	@Override
	public MyBatisBaseDao getDao() {
		return deviceBrandDao;
	}

	/**
	 * 更新分类属性绑定信息
	 * 
	 * @param categoryIds
	 * @param brandId
	 */
	@Override
	public void updateCategoryProperty(String categoryIds, Integer brandId) {
		Map<String, Object> map = new HashMap<String, Object>();
        //根据品牌id查找品牌绑定设备分类集合
		map.put("brandId", brandId);
		List<DeviceCategoryBrand> deviceCategoryBrandList = deviceCategoryBrandDao
				.selectObjectList(map);
		//删除品牌绑定的设备分类
		if (deviceCategoryBrandList != null
				&& !deviceCategoryBrandList.isEmpty()) {
			deviceCategoryBrandDao.deleteByBrandId(deviceCategoryBrandList);
		}

		// 数据分割
		String[] ids = categoryIds.split(",");
		List<DeviceCategoryBrand> brandCategories = new ArrayList<DeviceCategoryBrand>();
		DeviceCategoryBrand deviceCategoryBrand = null;
		for (int i = 0; i < ids.length; i++) {
			deviceCategoryBrand = new DeviceCategoryBrand();
			deviceCategoryBrand.setCategoryId(Integer.parseInt(ids[i]
					.substring(0, ids[i].length())));
			deviceCategoryBrand.setBrandId(brandId);
			brandCategories.add(deviceCategoryBrand);
		}
		deviceCategoryBrandDao.insertDeviceBrandCategories(brandCategories);
	}

}
