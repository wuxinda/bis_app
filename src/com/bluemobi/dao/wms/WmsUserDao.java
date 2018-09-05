package com.bluemobi.dao.wms;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【库房用户表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-07
 * 
 */
public interface WmsUserDao extends MyBatisBaseDao {
	
    /**
     * 获取库房用户
     * @param parmMap
     * @return
     * @author huangzuoguo
     * @date 2017年7月10日
     * 
     */
    public List<Map<String, Object>> selectWmsAllUser(Map<String, Object> parmMap);
    /**
     * 根据档案获取该档案管理员用户
     * @param parmMap
     * @return
     * @author huangzuoguo
     * @date 2017年7月12日
     * 
     */
    public List<Map<String, Object>> selectUserByAms(Map<String, Object> parmMap);
}
