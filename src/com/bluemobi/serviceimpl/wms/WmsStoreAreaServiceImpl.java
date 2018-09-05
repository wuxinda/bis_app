package com.bluemobi.serviceimpl.wms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.wms.WmsStoreAreaDao;
import com.bluemobi.po.wms.WmsStoreArea;
import com.bluemobi.service.wms.WmsStoreAreaService;

/**
 * 【库房分区表】 服务类 实现类
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
@Service(value = "wmsStoreAreaService")
public class WmsStoreAreaServiceImpl extends MybatisBaseServiceImpl implements WmsStoreAreaService {

    @Autowired
    private WmsStoreAreaDao wmsStoreAreaDao;

    @Override
    public MyBatisBaseDao getDao() {
        return wmsStoreAreaDao;
    }
    /**
     * 通过库房id获取库区列表
     */
    @Override
    public List<WmsStoreArea> getStoreAreaByStoreId(Integer storeId) {
	Map<Object, Object> map = new HashMap<Object, Object>();
	map.put("storeId", storeId);
	return this.selectObjectList(map);
    }

}
