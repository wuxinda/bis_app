package com.bluemobi.dao.device;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.device.DeviceCategoryLinkproperty;

/**
 * 【设备分类绑定连接属性表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface DeviceCategoryLinkpropertyDao extends MyBatisBaseDao {

	/**
	 * 根据categoryId批量删除连接属性信息
	 * @param categoryLinkpropertyList
	 */
	void deleteByCategoryId(List<DeviceCategoryLinkproperty> categoryLinkpropertyList);

	/**
	 * 根据categoryId批量增加连接属性信息
	 * @param categoryLinkpropertyes
	 */
	void insertDeviceCategoryLinkpropertyes(List<DeviceCategoryLinkproperty> categoryLinkpropertyes);
	/**根据设备分类id获取设备连接属性
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getLinkByCategoryId(Integer categoryId);
}
