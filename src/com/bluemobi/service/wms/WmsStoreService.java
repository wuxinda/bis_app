package com.bluemobi.service.wms;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.wms.WmsStore;

/**
 * 【库房表】 服务类 接口
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
public interface WmsStoreService extends MybatisBaseService {
	

    /**
     * 获取领导端首页库房统计数据
     * @param storeId
     * @return
     */
    public Map<String, Object> getVmsStatisData(String storeId);
    
    /**
     * 获取所有库房列表
     * @return
     */
    List<WmsStore> selectWmsStore();
    /**
     * 获取库房信息
     * @return
     */
    Map<String, Object> selectStoreInfo(Map<String, Object> map);
}
