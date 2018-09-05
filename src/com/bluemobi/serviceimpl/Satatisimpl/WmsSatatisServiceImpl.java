package com.bluemobi.serviceimpl.Satatisimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.dao.alarm.AlarmManageDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.service.satatis.WmsSatatisService;
/**
 * 【统计】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:43
 *
 */
@Service(value="wmsSatatisService")
public class WmsSatatisServiceImpl implements WmsSatatisService{
    @Autowired
    private AmsArchivesDao amsArchivesDao;
    @Autowired
    private AlarmManageDao alarmManageDao;
    
    /**
     * 获取档案统计数据
     * @param map
     * @return
     */
    @Override
    public Map<String,Object> selectAmsArchivesStatis(Map<String,Object> map){
	Map<String,Object> reuMap = new HashMap<String,Object>();
	//初始入库数据
	reuMap.put("instore", 0);
	//初始上架数据
	reuMap.put("putaway", 0);
	//初始借阅数据
	reuMap.put("borrow", 0);
	//初始上架数据
	reuMap.put("giveback", 0);
	//获取档案操作统计数据
	List<Map<String,Object>> rmaps = amsArchivesDao.selectActStatis(map);
	for(Map<String,Object> rmap : rmaps){
	    String type  = (String)rmap.get("type");
	    //0 表示入库操作
	    if("0".equals(type)){
		reuMap.put("instore", rmap.get("count"));
	    //1表示借阅操作
	    }else if("1".equals(type)){
		reuMap.put("borrow", rmap.get("count"));
	    //2代表上架操作
	    }else if("2".equals(type)) {
		reuMap.put("putaway",  rmap.get("count"));
            //3代表归还操作
	    }else if("3".equals(type)) {
		reuMap.put("giveback",rmap.get("count"));
	    }
	}
	
	
	//获取档案在库状态数据统计详情
	List<Map<String,Object>> inflagrmaps = amsArchivesDao.selectInflagStatis(map);
	Map<String,Object> inflagMap = new HashMap<String,Object>();
	//初始化数据
	inflagMap.put("atstore", 0);
	inflagMap.put("outstore", 0);
	for(Map<String,Object> rmap : inflagrmaps){
	    String inflag  = String.valueOf(rmap.get("inflag"));
	  //0 表示在库
	    if("0".equals(inflag)){
		inflagMap.put("atstore", rmap.get("count"));
	    //1表示出库
	    }else if("1".equals(inflag)){
		inflagMap.put("outstore", rmap.get("count"));
	    }
	}
	//添加在库状态返回节点
	reuMap.put("inflag", inflagMap);
	
	
	//获取档案保密等级数据统计详情
	List<Map<String,Object>> securityrmaps = amsArchivesDao.selectSecurityStatis(map);
	Map<String,Object> securityMap = new HashMap<String,Object>();
	//初始花等级数据
	securityMap.put("common", 0);
	securityMap.put("cecret", 0);
	securityMap.put("confidential", 0);
	securityMap.put("supersecret", 0);
	for(Map<String,Object> rmap : securityrmaps){
	    String security  = String.valueOf(rmap.get("security"));
	    // 密级 0.普通 1.秘密 2.机密 3.绝密	
	    if("0".equals(security)){
		securityMap.put("common", rmap.get("count"));
	    }else if("1".equals(security)){
		securityMap.put("cecret", rmap.get("count"));
	    }else if("2".equals(security)){
		securityMap.put("confidential", rmap.get("count"));
	    }else if("3".equals(security)){
		securityMap.put("supersecret", rmap.get("count"));
	    }
	}
	//添加保密等级返回节点
	reuMap.put("security", securityMap);
	
	
	//获取档案保管年限数据统计详情
	List<Map<String,Object>> keepyearrmaps = amsArchivesDao.selectKeepyearStatis(map);
	Map<String,Object> keepyearMap = new HashMap<String,Object>();
	//初始花等级数据
	keepyearMap.put("permanent", 0);
	keepyearMap.put("thirtyYears", 0);
	keepyearMap.put("longTime", 0);
	keepyearMap.put("shortTime", 0);
	keepyearMap.put("tenYears", 0);
	for(Map<String,Object> rmap : keepyearrmaps){
	    String keepyear  = String.valueOf(rmap.get("keepyear"));
	 // 保管年限 0.永久 1.长期 2.短期 3.10年 4.30年	
	    if("2".equals(keepyear)){
		keepyearMap.put("permanent", rmap.get("count"));
	    }else if("3".equals(keepyear)){
		keepyearMap.put("longTime", rmap.get("count"));
	    }else if("4".equals(keepyear)){
		keepyearMap.put("shortTime", rmap.get("count"));
	    }else if("5".equals(keepyear)){
		keepyearMap.put("tenYears", rmap.get("count"));
	    }else if("6".equals(keepyear)){
		keepyearMap.put("thirtyYears", rmap.get("count"));
	    }
	}
	//添加保管年限返回节点
	reuMap.put("keepyear", keepyearMap);
	
	
	//获取档案案组卷方数据统计详情
	List<Map<String,Object>> amsTypermaps = amsArchivesDao.selectAmsTypeStatis(map);
	//添加案组卷方返回节点
	reuMap.put("archivesType", amsTypermaps);
    
	return reuMap;
    }
    /**
     * 获取环境统计数据
     * @param map
     * @return
     */
    @Override
    public Map<String,Object> selectHumitureStatis(Map<String,Object> map){
	Random rand = new Random();
	Map<String,Object> reuMap = new HashMap<String,Object>();
	//获取温湿度数据统计详情
	//List<Map<String,Object>> temperaturermaps = XXXXXXXX.XXXXXXXX(map);
	Map<String,Object> temperatureMap = new HashMap<String,Object>();
	//初始化数据
	temperatureMap.put("tNormal", rand.nextInt(50)+200);
	temperatureMap.put("tUnderUp", rand.nextInt(50)+100);
	temperatureMap.put("tUnderDown", rand.nextInt(50)+100);
	//添加温度节点
	reuMap.put("temperature", temperatureMap);
	
	
	//获取湿度数据统计详情
	//List<Map<String,Object>> humidityrmaps = XXXXXXXX.XXXXXXXX(map);
	Map<String,Object> humidityMap = new HashMap<String,Object>();
	//初始花等级数据
	humidityMap.put("hNormal", rand.nextInt(100)+200);
	humidityMap.put("hUnderUp", rand.nextInt(100)+100);
	humidityMap.put("hUnderDown", rand.nextInt(100)+100);
	
	//添加温湿度返回节点
	reuMap.put("humidity", humidityMap);
	
	
	//获取PM数据统计详情
	//List<Map<String,Object>> PMrmaps = XXXXXXXX.XXXXXXXX(map);
	Map<String,Object> PMMap = new HashMap<String,Object>();
	//初始花等级数据
	PMMap.put("best", rand.nextInt(100)+200);
	PMMap.put("good", rand.nextInt(100)+500);
	PMMap.put("mildPollute", rand.nextInt(100)+200);
	PMMap.put("seriousPollute", rand.nextInt(100)+200);
	PMMap.put("badlyPollute", rand.nextInt(100)+200);
	
	//添加PM返回节点
	reuMap.put("PM", PMMap);
	return reuMap;
    }
    /**
     * 获取报警统计数据
     * @param map
     * @return
     */
    @Override
    public List<Map<String,Object>> selectAlarmStatis(Map<String,Object> map){
	//获取报警数据统计详情
	List<Map<String,Object>> temperaturermaps = alarmManageDao.selectAlarmStatis(map);
	return temperaturermaps;
    }
    
    
    
}


