package com.bluemobi.serviceimpl.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceCategoryActionpropertyDao;
import com.bluemobi.service.device.DeviceCategoryActionpropertyService;

/**
 * 【设备分类绑定操作属性表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceCategoryActionpropertyService")
public class DeviceCategoryActionpropertyServiceImpl extends MybatisBaseServiceImpl implements DeviceCategoryActionpropertyService {

    @Autowired
    private DeviceCategoryActionpropertyDao deviceCategoryActionpropertyDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceCategoryActionpropertyDao;
    }
	
	/**根据设备分类id获取设备操作属性
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getActionByCategoryId(Integer categoryId){
		return deviceCategoryActionpropertyDao.getActionByCategoryId(categoryId);
	}

}
