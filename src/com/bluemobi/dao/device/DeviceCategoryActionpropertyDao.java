package com.bluemobi.dao.device;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.device.DeviceCategoryActionproperty;

/**
 * 【设备分类绑定操作属性表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceCategoryActionpropertyDao extends MyBatisBaseDao {
	/**
	 * 根据categoryId批量删除操作属性信息
	 * @param categoryActionpropertyList
	 */
	void deleteByCategoryId(List<DeviceCategoryActionproperty> categoryActionpropertyList);

	/**
	 * 根据categoryId批量增加操作属性信息
	 * @param categoryActionpropertyes
	 */
	void insertDeviceCategorycategoryActionpropertyes(List<DeviceCategoryActionproperty> categoryActionpropertyes);
	
	/**根据设备分类id获取设备操作属性
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getActionByCategoryId(Integer categoryId);

}
