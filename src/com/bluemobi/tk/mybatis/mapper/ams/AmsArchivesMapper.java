package com.bluemobi.tk.mybatis.mapper.ams;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.bluemobi.base.mapper.BaseMapper;
import com.bluemobi.po.ams.AmsArchives;

public interface AmsArchivesMapper extends BaseMapper<AmsArchives>{
	
	/**
	 * 档案分类调档统计图
	 * @return
	 */
	List<Map<String,Object>> selectAmsArchivesByCategory(Map<String,Object> map);
	
	/**
	 * 库房分类调档统计图
	 * @return
	 */
	List<Map<String,Object>> selectAmsArchivesByStoreId(Map<String,Object> map);

	/**
	 * 档案异常数据查询
	 * @return
	 */
	List<Map<String,Object>> selectAlarmArchives();
}
