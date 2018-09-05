package com.bluemobi.service.device;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
public interface DeviceAccessRecordService extends MybatisBaseService {
	/**
     * 获取门禁数据列表
     */
	List<Map<String,Object>> getAccessDataList(Map<String,Object> map);
	/**
     * 按时间统计门禁数据列表
     */
	List<Map<String,Object>> getAccessDataCount(Map<String,Object> map);
}
