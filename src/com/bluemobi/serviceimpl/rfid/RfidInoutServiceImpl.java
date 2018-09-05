package com.bluemobi.serviceimpl.rfid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.rfid.RfidInoutDao;
import com.bluemobi.service.rfid.RfidInoutService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
@Service(value = "rfidInoutService")
public class RfidInoutServiceImpl extends MybatisBaseServiceImpl implements RfidInoutService {
	private final SimpleDateFormat sdf=new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.US);
	private final SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Autowired
    private RfidInoutDao rfidInoutDao;

    @Override
    public MyBatisBaseDao getDao() {
        return rfidInoutDao;
    }
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	public List<Map<String,Object>> searchRfidInout(Map<String,Object> map)  throws Exception{
		List<Map<String,Object>> list=rfidInoutDao.searchRfidInout(map);
		for (Map<String, Object> map2 : list) {
    		map2.put("ctime", sdf1.format(sdf.parse(map2.get("ctime").toString())));
		}
		return list;
	}
	/**
	 * 按查询rfid出入库数据
	 * @return
	 */
	public Map<String,Object> getRfidInoutList(Map<String,Object> map){
		List<Map<String,Object>> list=rfidInoutDao.getRfidInoutList(map);
		int rfidCount=rfidInoutDao.getRfidInoutListcount(map);
		Map<String,Object> resmap=new HashMap<String,Object>();
		resmap.put("data", list);
		resmap.put("count", rfidCount);
		return resmap;
	}
}
