package com.bluemobi.dao.log;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
public interface LogUserImageDao extends MyBatisBaseDao {

	public List<Map<String,Object>> getImgUrl(Map<String,Object> map);
}
