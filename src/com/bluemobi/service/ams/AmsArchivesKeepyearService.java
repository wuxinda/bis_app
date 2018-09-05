package com.bluemobi.service.ams;

import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【档案保管年限
】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-05
 * 
 */
public interface AmsArchivesKeepyearService extends MybatisBaseService {

    /**
     *同步保管年限
     *@Date:2017年5月12日
     *@author:Tony
     *@param paraMap
     *@return
     */
    String dictSync(Map<String, Object> paraMap);

}
