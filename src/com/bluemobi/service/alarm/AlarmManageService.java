package com.bluemobi.service.alarm;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.alarm.AlarmManage;

/**
 * 【报警管理表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface AlarmManageService extends MybatisBaseService {

    /**
     * 报警统计服务类
     * @param req
     * @return
     */
    Map<String, Object> getAlarmSata(Map<String, Object> req);
    /**
     * 产生报警服务类
     * 同一种未处理报警不重复产生
     * @param req
     * @return
     */
    Map<String, Object> addAlarmData(List<AlarmManage> alarmManages);
    /**
     * 报警统计
     * 
     * @param map
     * @return
     */
    List<Map<String, Object>> getAlarmCount();
    
    /**
     * 通用mapper insert
     * @param alarmManage
     * @return
     */
    Integer insertAlarmManage(AlarmManage alarmManage);
    
    /**
     * 查询设备异常报警信息
     * @param map
     * @return
     */
    List<Map<String,Object>> selectAlarmManageByDevice(Map<String,Object> map) throws Exception;
}
