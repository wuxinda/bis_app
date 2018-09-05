package com.bluemobi.tk.mybatis.mapper.rfid;

import java.util.List;
import java.util.Map;

import com.bluemobi.base.mapper.BaseMapper;
import com.bluemobi.po.rfid.RfidInty;

public interface RfidIntyMapper extends BaseMapper<RfidInty>{
	
	/**
	 * 获取已盘点档案数量
	 * @return
	 */
	Integer selectCountRfidIntyFinishd();
	
	/**
	 * 获取盘点结果
	 * @return
	 */
	List<Map<String,Object>> selectCountRfidResult();
}
