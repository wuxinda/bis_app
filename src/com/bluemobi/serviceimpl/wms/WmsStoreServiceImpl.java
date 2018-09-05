package com.bluemobi.serviceimpl.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.dao.perception.PerceptionHcsDao;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.wms.WmsStore;
import com.bluemobi.service.wms.WmsStoreService;

/**
 * 【库房表】 服务类 实现类
 * 
 * @author Riven
 * @date 2016-11
 * 
 */
@Service(value = "wmsStoreService")
public class WmsStoreServiceImpl extends MybatisBaseServiceImpl implements WmsStoreService {
    @Autowired
    private AmsArchivesDao amsArchivesDao;
    @Autowired
    private WmsStoreDao wmsStoreDao;
    @Autowired
    private PerceptionHcsDao perceptionHcsDao;

    @Override
    public MyBatisBaseDao getDao() {
	return wmsStoreDao;
    }

    /**
     * 获取领导端首页库房统计数据
     * 
     * @param storeId
     * @return
     */
    public Map<String, Object> getVmsStatisData(String storeId) {
	Random rand = new Random();
	// 返回结果集
	Map<String, Object> resultVmsMap = new HashMap<String, Object>();
	// 初始化数据
	resultVmsMap.put("inStoreNum", "0");
	resultVmsMap.put("outStoreNum", "0");
	// 统计库房温度 -－－获取单个库房所有温湿度设备计算平均温湿度
	try {
		Map<String, Object> ssp = new HashMap<String, Object>();
		ssp.put("storeId", storeId);
	    List<Map<String, Object>> humtep = perceptionHcsDao.getWmsAvgHumTem(ssp);
	    if (humtep.size() > 0) {
		resultVmsMap.put("temperature", String.valueOf(humtep.get(0).get("avgtem")));
		resultVmsMap.put("humidity", String.valueOf(humtep.get(0).get("avghum")));
	    } else {
		resultVmsMap.put("temperature", String.valueOf("无数据"));
		resultVmsMap.put("humidity", String.valueOf("无数据"));
	    }
	    // 统计库房存放档案的总容量 -－－逻辑后续写
	    resultVmsMap.put("tCapacity", String.valueOf(rand.nextInt(1000) + 1000));
	    // 统计库房已存档案数目 -－－逻辑后续写
	    resultVmsMap.put("uCapacity", String.valueOf(rand.nextInt(800) + 200));
	    // 获取在库和出库数量
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("storeId", storeId);
	    List<Map<String, Object>> inflagrmaps = amsArchivesDao.selectInflagStatis(map);
	    for (Map<String, Object> rmap : inflagrmaps) {
		String inflag = String.valueOf(rmap.get("inflag"));
		// 0 表示在库
		if ("0".equals(inflag)) {
		    resultVmsMap.put("inStoreNum", String.valueOf(rmap.get("count")));
		    // 1表示出库
		} else if ("1".equals(inflag)) {
		    resultVmsMap.put("outStoreNum", String.valueOf(rmap.get("count")));
		}
	    }
	    return resultVmsMap;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

    /**
     * 获取所有库房列表
     */
    @Override
    public List<WmsStore> selectWmsStore() {
	Map<Object, Object> map = new HashMap<Object, Object>();
	List<WmsStore> list = this.selectObjectList(map);
	return list;
    }

    /**
     * 获取库房信息服务实现类
     */
    @Override
    public Map<String, Object> selectStoreInfo(Map<String, Object> map) {
	Random rand = new Random();
	// 返回结果集
	Map<String, Object> resultVmsMap = new HashMap<String, Object>();
	try {
	    // 1.统计库房温度
	    List<Map<String, Object>> humtep = perceptionHcsDao.getWmsAvgHumTem(map);
	    if (humtep.size() > 0) {
		resultVmsMap.put("temperature", String.valueOf(humtep.get(0).get("avgtem")));
		resultVmsMap.put("humidity", String.valueOf(humtep.get(0).get("avghum")));
	    } else {
		resultVmsMap.put("temperature", String.valueOf("无数据"));
		resultVmsMap.put("humidity", String.valueOf("无数据"));
	    }
	    // 2.统计库房门禁记录
	    List<Map<String, Object>> rinoutLog = new ArrayList<Map<String, Object>>();
	    Map<String, Object> AmsType = new HashMap<String, Object>();
	    AmsType.put("inoutName", "刘备");
	    AmsType.put("inoutTime", "2016-10-10 11:21:10");
	    AmsType.put("inoutType", 1);
	    rinoutLog.add(AmsType);
	    Map<String, Object> AmsType1 = new HashMap<String, Object>();
	    AmsType1.put("inoutName", "关羽");
	    AmsType1.put("inoutTime", "2016-10-10 08:10:02");
	    AmsType1.put("inoutType", 0);
	    rinoutLog.add(AmsType1);
	    Map<String, Object> AmsType2 = new HashMap<String, Object>();
	    AmsType2.put("inoutName", "张飞");
	    AmsType2.put("inoutTime", "2016-10-10 18:09:20");
	    AmsType2.put("inoutType", 1);
	    rinoutLog.add(AmsType2);
	    resultVmsMap.put("inoutLog", rinoutLog);

	    //3.库房内库区具体数据
	    List<Map<String, Object>> rstoreAeraData = new ArrayList<Map<String, Object>>();
	    // 第一个库区返回结果集
	    Map<String, Object> storeAeraData = new HashMap<String, Object>();
	    // 统计库房库区名 -－－逻辑后续写
	    storeAeraData.put("storeAeraName", "第一库区");
	    // 统计库房库区列数 -－－逻辑后续写
	    storeAeraData.put("columnNum", String.valueOf(rand.nextInt(80) + 10));
	    // 统计库房库区存放档案的总容量 -－－逻辑后续写
	    storeAeraData.put("tCapacity", String.valueOf(rand.nextInt(1000) + 1000));
	    // 统计库房库区已存档案数目 -－－逻辑后续写
	    storeAeraData.put("uCapacity", String.valueOf(rand.nextInt(800) + 200));
	    
	    // 获取库房库区档案分类数据
	    List<Map<String, Object>> amsTypermaps = amsArchivesDao.selectAmsTypeStatis(map);
	    storeAeraData.put("archivesType", amsTypermaps);
	    rstoreAeraData.add(storeAeraData);

	    //storeAeraData.put("storeAeraName", "第二库区");
	    rstoreAeraData.add(storeAeraData);
	    // 返回库房库区数据
	    resultVmsMap.put("storeAeraData", rstoreAeraData);

	    return resultVmsMap;
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }
}
