package com.bluemobi.serviceimpl.perception;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.perception.PerceptionHcsDao;
import com.bluemobi.service.perception.PerceptionHcsService;

/**
 * 【温湿度感知数据收集表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-03
 * 
 */
@Service(value = "perceptionHcsService")
public class PerceptionHcsServiceImpl extends MybatisBaseServiceImpl implements PerceptionHcsService {

	@Autowired
	private PerceptionHcsDao perceptionHcsDao;

	@Override
	public MyBatisBaseDao getDao() {
		return perceptionHcsDao;
	}

	@Override
	public List<Map<String, Object>> getHumitureReport(Map<String, Object> pmap) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (String.valueOf(pmap.get("time")).split("-").length == 3) {// 日报表
			map.put("type", 3);
			map.put("stime", pmap.get("time")+" 00:00:00");
			map.put("etime", pmap.get("time")+" 23:59:59");
		} else if (String.valueOf(pmap.get("time")).split("-").length == 2) {
			map.put("type", 2);
			map.put("stime", pmap.get("time")+"-01 00:00:00");
			map.put("etime", pmap.get("time")+"-31 23:59:59");
		} else if (String.valueOf(pmap.get("time")).split("-").length == 1) {
			map.put("type", 1);
			map.put("stime", pmap.get("time")+"-01-01 00:00:00");
			map.put("etime", pmap.get("time")+"-12-31 23:59:59");
		}
		if(pmap.get("storeId")!= null) {
			map.put("storeId", pmap.get("storeId"));
		}
		return perceptionHcsDao.getHumitureReport(map);
	}

	@Override
	public List<Map<String, Object>> getHumitureWms(Map<String, Object> map) {
		return perceptionHcsDao.getHumitureWms(map);
	}
    /**
     * 获取日温湿度
     * 
     * @param map
     * @return
     */
    public List<Map<String, Object>> getHumByDay(Map<String, Object> map) {
    	List<Map<String,Object>> list= perceptionHcsDao.getHumByDay(map);
    	List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
    	Map<String,Object> map2=null;
    	Integer time=null;
    	String humidity=null;
    	String temperature=null;
    	for(int i=0;i<24;i++) {
    		map2= new HashMap<String,Object>();
    		map2.put("temperature", "/");
    		map2.put("humidity", "/");
    		map2.put("time",String.valueOf(i));
    		for(Map<String,Object> m:list) {
    			temperature=String.valueOf(m.get("temperature"));
    			humidity=String.valueOf(m.get("humidity"));
    			time=Integer.parseInt(String.valueOf(m.get("time")));
    			if(time==i) {
    				map2.put("temperature",temperature);
            		map2.put("humidity", humidity);
            		map2.put("time",time);
            		break;
    			}
    		}
    		list2.add(map2);
    	}
		return list2;
	}
    /**
     * 获取月温湿度
     * 
     * @param map
     * @return
     */
    public List<Map<String, Object>> getHumByMon(Map<String, Object> map) {
    	Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
       	List<Map<String,Object>> list= perceptionHcsDao.getHumByMon(map);
    	List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
    	Map<String,Object> map2=null;
    	Integer time=null;
    	String humidity=null;
    	String temperature=null;
    	for(int i=1;i<aCalendar.getActualMaximum(Calendar.DATE)+1;i++) {
    		map2= new HashMap<String,Object>();
    		map2.put("temperature", "/");
    		map2.put("humidity", "/");
    		map2.put("time",String.valueOf(i));
    		for(Map<String,Object> m:list) {
    			temperature=String.valueOf(m.get("temperature"));
    			humidity=String.valueOf(m.get("humidity"));
    			time=Integer.parseInt(String.valueOf(m.get("time")));
    			if(time==i) {
    				map2.put("temperature",temperature);
            		map2.put("humidity", humidity);
            		map2.put("time",time);
            		break;
    			}
    		}
    		list2.add(map2);
    	}
		return list2;
	}
    /**
     * 获取年温湿度
     * 
     * @param map
     * @return
     */
    public List<Map<String, Object>> getHumByYear(Map<String, Object> map) {
       	List<Map<String,Object>> list= perceptionHcsDao.getHumByYear(map);
    	List<Map<String,Object>> list2=new ArrayList<Map<String,Object>>();
    	Map<String,Object> map2=null;
    	Integer time=null;
    	String humidity=null;
    	String temperature=null;
    	for(int i=1;i<13;i++) {
    		map2= new HashMap<String,Object>();
    		map2.put("temperature", "/");
    		map2.put("humidity", "/");
    		map2.put("time",String.valueOf(i));
    		for(Map<String,Object> m:list) {
    			temperature=String.valueOf(m.get("temperature"));
    			humidity=String.valueOf(m.get("humidity"));
    			time=Integer.parseInt(String.valueOf(m.get("time")));
    			if(time==i) {
    				map2.put("temperature",temperature);
            		map2.put("humidity", humidity);
            		map2.put("time",time);
            		break;
    			}
    		}
    		list2.add(map2);
    	}
		return list2;
	}
}
