package com.bluemobi.serviceimpl.ams;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.ams.AmsArchivesActlogDao;
import com.bluemobi.dao.ams.AmsArchivesAuditDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.service.ams.AmsArchivesActlogService;

/**
 * 【档案操作纪录表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "amsArchivesActlogService")
public class AmsArchivesActlogServiceImpl extends MybatisBaseServiceImpl implements AmsArchivesActlogService {

    @Autowired
    private AmsArchivesActlogDao amsArchivesActlogDao;
    @Autowired
    private AmsArchivesAuditDao amsArchivesAuditDao;
    @Autowired
    private AmsArchivesDao amsArchivesDao;

    @Override
    public MyBatisBaseDao getDao() {
	return amsArchivesActlogDao;
    }

    @Override
    public void insertArchivesActlogs(String[] archivesIdsIn, String type, String userId, String storeId, String areaId) {
	Map<String, Object> parmMapin = new HashMap<String, Object>();
	parmMapin.put("archivesIds", archivesIdsIn);
	parmMapin.put("status", 1);
	parmMapin.put("type", type);
	parmMapin.put("userId", userId);
	parmMapin.put("ctime", new Date());
	parmMapin.put("modifier", userId);
	parmMapin.put("mtime", new Date());
	parmMapin.put("wmsstoreId", storeId);
	parmMapin.put("storeareaId", areaId);
	if (archivesIdsIn.length > 0)
	    try {
		amsArchivesActlogDao.insertArchivesActlogs(parmMapin);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

    }

    @Override
    public List<Map<String, Object>> getNewInOutAms(Map<String, Object> map) {
	return amsArchivesActlogDao.getNewInOutAms(map);
    }

    @Override
    public void insertAmsActlogs(Map<String, Object> parmMap) {
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();// 返回列表
	list = amsArchivesAuditDao.getApplyAms(parmMap);
	if (list.size() > 0)
	    try {
		Map<String, Object> parmMapin = new HashMap<String, Object>();
		parmMapin.put("lists", list);
		parmMapin.put("status", 1);
		parmMapin.put("userId", parmMap.get("userId"));
		parmMapin.put("ctime", new Date());
		parmMapin.put("modifier", parmMap.get("userId"));
		parmMapin.put("mtime", new Date());
		Map<String, Object> ls = amsArchivesDao.selectMap(list.get(0).get("archivesId"));
		if (ls != null) {
		    parmMapin.put("wmsstoreId", ls.get("stroreId"));
		    parmMapin.put("storeareaId", ls.get("stroreAreaId"));
		}
		amsArchivesActlogDao.insertAmsActlogs(parmMapin);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

    }
}
