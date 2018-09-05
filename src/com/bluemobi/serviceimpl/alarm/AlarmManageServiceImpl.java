package com.bluemobi.serviceimpl.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.alarm.AlarmManageDao;
import com.bluemobi.po.alarm.AlarmManage;
import com.bluemobi.service.alarm.AlarmManageService;
import com.bluemobi.tk.mybatis.mapper.alarm.AlarmManageMapper;

/**
 * 【报警管理表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "alarmManageService")
public class AlarmManageServiceImpl extends MybatisBaseServiceImpl implements AlarmManageService {
	private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private AlarmManageDao alarmManageDao;
	
	@Autowired
	private AlarmManageMapper alarmManageMapper;
	
	@Override
	public MyBatisBaseDao getDao() {
		return alarmManageDao;
	}

	@Override
	public Map<String, Object> getAlarmSata(Map<String, Object> req) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = alarmManageDao.getAlarmSata(req);
		map.put("status", list);
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		list1 = alarmManageDao.selectAlarmStatis(req);
		map.put("type", list1);
		return map;
	}

	/**
	 * 产生报警服务类 同一种未处理报警不重复产生
	 * 
	 * @param req
	 * @return
	 */
	@Override
	public Map<String, Object> addAlarmData(List<AlarmManage> alarmManages) {
		if(alarmManages!=null&&alarmManages.size()>0) {
			for(AlarmManage alarmManage:alarmManages) {
				//查询该设备该类型的报警未处理的报警是否已经存在
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("alarmType", alarmManage.getAlarmType());
				map.put("deviceId", alarmManage.getDeviceId());
				map.put("status", alarmManage.getStatus());
				List<Object> list = this.selectObjectList(map);
				if(list.size()>0) {
					continue;
				}else {
					this.insert(alarmManage);
				}
			}
		}
		return null;
	}
    /**
     * 报警统计
     * 
     * @param map
     * @return
     */
    public List<Map<String, Object>> getAlarmCount(){
    	List<Map<String,Object>> list=alarmManageDao.getAlarmCount();
		return list;
    }
    
    /**
     * 通用mapper insert
     */
    public Integer insertAlarmManage(AlarmManage alarmManage) {
    	return alarmManageMapper.insertSelective(alarmManage);
    }
    
    /**
     * 查询设备异常报警信息
     */
    public List<Map<String,Object>> selectAlarmManageByDevice(Map<String,Object> map) throws Exception{
    	List<Map<String,Object>> result=alarmManageMapper.selectAlarmManageByDevice(map);
    	for (Map<String, Object> map2 : result) {
    		map2.put("ctime", sdf.format(sdf.parse(map2.get("ctime").toString())));
		}
    	return result;
    }
}
