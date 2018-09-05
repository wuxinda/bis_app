package com.bluemobi.serviceimpl.rfid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.bluemobi.dao.ams.AmsArchivesDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.rfid.RfidIntyDao;
import com.bluemobi.service.rfid.RfidIntyService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
@Service(value = "rfidIntyService")
public class RfidIntyServiceImpl extends MybatisBaseServiceImpl implements RfidIntyService {

    @Autowired
    private RfidIntyDao rfidIntyDao;
    @Autowired
    private AmsArchivesDao AmsArchivesDao;
    @Override
    public MyBatisBaseDao getDao() {
        return rfidIntyDao;
    }
    /**
     * 获取rfid盘点
     */
	@Override
	public Map<String,Object> getRfidIntyByday(Map<String,Object> map) {
		List<Map<String,Object>> list1=null;
		List<Map<String,Object>> list2=null;
		List<Map<String,Object>> list3=null;
		map.put("type", 0);
		list1=rfidIntyDao.getRfidIntyByday(map);
		map.put("type", 1);
		list2=rfidIntyDao.getRfidIntyByday(map);
		map.put("type", 2);
		list3=rfidIntyDao.getRfidIntyByday(map);
		for(Map<String,Object> m1:list1) {
			for(Map<String,Object> m2:list2) {
				if(m1.get("time").equals(m2.get("time"))) {
					m1.put("c",m2.get("b"));
				}
			}
			for(Map<String,Object> m3:list3) {
				if(m1.get("time").equals(m3.get("time"))) {
					m1.put("d",m3.get("b"));
				}
			}
		}
		Map<String,Object> maps=new HashMap<String,Object>();
		Map<String,Object> all=new HashMap<String,Object>();
		int a=0;//
		int b=0;
		int c=0;
		int d=0;
		for(int i=0;i<list1.size();i++) {
			a=Integer.valueOf(list1.get(i).get("a").toString())+a;
			b=Integer.valueOf(list1.get(i).get("b").toString())+b;
			c=Integer.valueOf(list1.get(i).get("c").toString())+c;
			d=Integer.valueOf(list1.get(i).get("d").toString())+d;
		}
		all.put("a", a);
		all.put("b", b);
		all.put("c", c);
		all.put("d", d);
		maps.put("one", list1);
		maps.put("all", all);
		return maps;
	}
	/**
	 * 查询rfid盘点的占比
	 */
	@Override
	public Map<String, Object> getRfid(Map<String, Object> map) {
		Map<String,Object> amsMap=AmsArchivesDao.getCountAms(map);
		Map<String,Object> rfidMap=rfidIntyDao.getCountRfid(map);
		
		int rfid=Integer.parseInt(rfidMap.get("rfid").toString());
		int ams= (Integer.parseInt(amsMap.get("ams").toString()))-rfid;
		rfidMap.put("ams", ams);
		return rfidMap;
	}

	/**
     * 按状态查询盘点信息
     */
	public List<Map<String,Object>> getRfidByType(Map<String,Object> map){
		List<Map<String,Object>> list=null;
		list=rfidIntyDao.getRfidByType(map);
		for(Map<String,Object> e:list) {
			if(e.get("type").equals("0")) {
				e.put("type", "在架");
				e.put("status", "否");
			}else if(e.get("type").equals("1")) {
				e.put("type", "离架");
				e.put("status", "否");
			}else if(e.get("type").equals("2")) {
				e.put("type", "错架");
				e.put("status", "是");
			}else {
				e.put("type", "无状态信息");
				e.put("status", "无状态信息");
			}
		}
		return list;
	}
	/**
	 * 获取rfid总记录数
	 */
	public int getMaxPage(Map<String,Object> map){
		int page=rfidIntyDao.pageSearchCount(map);
		return page;
	}
	/**
	 * 获取最新rfid记录
	 * 
	 */
	 public List<Map<String, Object>> getNewRfid(){
		 List<Map<String,Object>> list=rfidIntyDao.getNewRfid();
		 
		return list;
	 }
		/**
		 * 按查询rfid出入库数据
		 * @return
		 */
		public Map<String,Object> getRfidIntyList(Map<String,Object> map){
			List<Map<String,Object>> list=rfidIntyDao.getRfidIntyList(map);
			int rfidCount=rfidIntyDao.getRfidIntyListcount(map);
			Map<String,Object> resmap=new HashMap<String,Object>();
			for(Map<String,Object> m:list) {
				m.put("type", 3);
			}
			resmap.put("data", list);
			resmap.put("count", rfidCount);
			return resmap;
		}
}
