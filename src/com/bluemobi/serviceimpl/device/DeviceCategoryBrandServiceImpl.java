package com.bluemobi.serviceimpl.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceCategoryBrandDao;
import com.bluemobi.service.device.DeviceCategoryBrandService;

/**
 * 【设备分类绑定品牌表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "deviceCategoryBrandService")
public class DeviceCategoryBrandServiceImpl extends MybatisBaseServiceImpl implements DeviceCategoryBrandService {

    @Autowired
    private DeviceCategoryBrandDao deviceCategoryBrandDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceCategoryBrandDao;
    }
	
	/**根据设备分类id获取品牌列表
	 * @param categoryId
	 * @return
	 */
	public List<Map<String,Object>> getBrandByCategoryId(Integer categoryId){
		return deviceCategoryBrandDao.getBrandByCategoryId(categoryId);
	}
}
