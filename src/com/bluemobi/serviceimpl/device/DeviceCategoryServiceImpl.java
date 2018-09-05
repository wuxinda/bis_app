package com.bluemobi.serviceimpl.device;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceCategoryActionpropertyDao;
import com.bluemobi.dao.device.DeviceCategoryDao;
import com.bluemobi.dao.device.DeviceCategoryLinkpropertyDao;
import com.bluemobi.po.device.DeviceCategoryActionproperty;
import com.bluemobi.po.device.DeviceCategoryLinkproperty;
import com.bluemobi.service.device.DeviceCategoryService;

/**
 * 【设备分类表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceCategoryService")
public class DeviceCategoryServiceImpl extends MybatisBaseServiceImpl implements DeviceCategoryService {

    @Autowired
    private DeviceCategoryDao deviceCategoryDao;
    @Autowired
    private DeviceCategoryLinkpropertyDao deviceCategoryLinkpropertyDao;
    @Autowired
    private DeviceCategoryActionpropertyDao deviceCategoryActionpropertyDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceCategoryDao;
    }
	/**
	 * 更新连接属性绑定信息
	 * @param linkpropertyIds
	 * @param categoryId
	 */
	public void updateCategoryLinkproperty(String linkpropertyIds, Integer categoryId){
		Map<String, Object> map = new HashMap<String, Object>();
        //根据分类id查找分类绑定设备连接属性集合
		map.put("categoryId", categoryId);
		List<DeviceCategoryLinkproperty> categoryLinkpropertyList = deviceCategoryLinkpropertyDao
				.selectObjectList(map);
		//删除分类绑定的连接属性
		if (categoryLinkpropertyList != null
				&& !categoryLinkpropertyList.isEmpty()) {
			deviceCategoryLinkpropertyDao.deleteByCategoryId(categoryLinkpropertyList);
		}

		// 数据分割
		String[] ids = linkpropertyIds.split(",");
		List<DeviceCategoryLinkproperty> categoryLinkpropertyes = new ArrayList<DeviceCategoryLinkproperty>();
		DeviceCategoryLinkproperty deviceCategoryLinkproperty = null;
		for (int i = 0; i < ids.length; i++) {
			deviceCategoryLinkproperty = new DeviceCategoryLinkproperty();
			deviceCategoryLinkproperty.setLinkpropertyId(Integer.parseInt(ids[i]
					.substring(0, ids[i].length())));
			deviceCategoryLinkproperty.setCategoryId(categoryId);
			categoryLinkpropertyes.add(deviceCategoryLinkproperty);
		}
		deviceCategoryLinkpropertyDao.insertDeviceCategoryLinkpropertyes(categoryLinkpropertyes);
	}
	/**
	 * 更新操作属性绑定信息
	 * @param actionpropertyIds
	 * @param categoryId
	 */
	public void updateCategoryActionproperty(String actionpropertyIds, Integer categoryId){
		Map<String, Object> map = new HashMap<String, Object>();
		//根据分类id查找分类绑定设备操作属性集合
		map.put("categoryId", categoryId);
		List<DeviceCategoryActionproperty> categoryActionpropertyList = deviceCategoryActionpropertyDao
				.selectObjectList(map);
		//删除分类绑定的操作属性
		if (categoryActionpropertyList != null
				&& !categoryActionpropertyList.isEmpty()) {
			deviceCategoryActionpropertyDao.deleteByCategoryId(categoryActionpropertyList);
		}
		
		// 数据分割
		String[] ids = actionpropertyIds.split(",");
		List<DeviceCategoryActionproperty> categoryActionpropertyes = new ArrayList<DeviceCategoryActionproperty>();
		DeviceCategoryActionproperty deviceCategoryActionproperty = null;
		for (int i = 0; i < ids.length; i++) {
			deviceCategoryActionproperty = new DeviceCategoryActionproperty();
			deviceCategoryActionproperty.setActionpropertyId(ids[i]
					.substring(0, ids[i].length()));
			deviceCategoryActionproperty.setCategoryId(String.valueOf(categoryId));
			categoryActionpropertyes.add(deviceCategoryActionproperty);
		}
		deviceCategoryActionpropertyDao.insertDeviceCategorycategoryActionpropertyes(categoryActionpropertyes);
	}
}
