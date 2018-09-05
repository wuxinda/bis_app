package com.bluemobi.service.ams;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.ams.AmsArchivesType;

/**
 * 【档案类型表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AmsArchivesTypeService extends MybatisBaseService {
    /**
     * 获取所有档案类型列表
     * @return
     */
    List<AmsArchivesType> selectAmsArchivesType();

    /**
     *同步档案类型
     *@Date:2017年5月12日
     *@author:Tony
     *@param paraMap
     *@return
     */
    String dictSync(Map<String, Object> paraMap);
}
