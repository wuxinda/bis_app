package com.bluemobi.service.wms;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.wms.WmsStoreArea;

/**
 * 【库房分区表】 服务类 接口
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
public interface WmsStoreAreaService extends MybatisBaseService {
/**
 * 通过库房id获取库区列表
 */
    List<WmsStoreArea> getStoreAreaByStoreId(Integer storeId);
}
