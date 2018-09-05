package com.bluemobi.serviceimpl.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceCategoryLinkpropertyDao;
import com.bluemobi.service.device.DeviceCategoryLinkpropertyService;

/**
 * 【设备分类绑定连接属性表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceCategoryLinkpropertyService")
public class DeviceCategoryLinkpropertyServiceImpl extends MybatisBaseServiceImpl implements DeviceCategoryLinkpropertyService {

    @Autowired
    private DeviceCategoryLinkpropertyDao deviceCategoryLinkpropertyDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceCategoryLinkpropertyDao;
    }
	/**根据设备分类id获取设备连接属性
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getLinkByCategoryId(Integer categoryId){
		return deviceCategoryLinkpropertyDao.getLinkByCategoryId(categoryId);
	}
}
