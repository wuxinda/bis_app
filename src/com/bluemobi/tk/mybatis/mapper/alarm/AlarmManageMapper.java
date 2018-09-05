package com.bluemobi.tk.mybatis.mapper.alarm;

import java.util.List;
import java.util.Map;

import com.bluemobi.base.mapper.BaseMapper;
import com.bluemobi.po.alarm.AlarmManage;

public interface AlarmManageMapper extends BaseMapper<AlarmManage>{
	
	/**
	 * 查询设备异常报警信息
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> selectAlarmManageByDevice(Map<String,Object> map);

}
