package com.bluemobi.serviceimpl.ams;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.ams.AmsArchivesFilingmethodDao;
import com.bluemobi.service.ams.AmsArchivesFilingmethodService;

/**
 * 【档案立卷方式表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-05
 * 
 */
@Service(value = "amsArchivesFilingmethodService")
public class AmsArchivesFilingmethodServiceImpl extends MybatisBaseServiceImpl implements AmsArchivesFilingmethodService {

    @Autowired
    private AmsArchivesFilingmethodDao amsArchivesFilingmethodDao;

    @Override
    public MyBatisBaseDao getDao() {
	return amsArchivesFilingmethodDao;
    }

    /**
     * 立卷方式同步
     * @Date:2017年5月12日
     * @author:Tony
     * @param paraMap
     * @return
     */
    @Override
    public String dictSync(Map<String, Object> paraMap) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("sortOrder", paraMap.get("dataValue"));
	List<Map<String, Object>> amsArchivesType = this.selectMapList(map);
	if (paraMap.get("type").equals("1")) {// 增加
	    if (amsArchivesType.size() > 0) {
		return "请求失败，标识已经存在!";
	    }
	    map.put("sortOrder", String.valueOf(paraMap.get("dataValue")));
	    map.put("ctime", new Date());
	    map.put("creator", String.valueOf(paraMap.get("userId")));
	    map.put("name", String.valueOf(paraMap.get("dataName")));
	    map.put("remark", String.valueOf(paraMap.get("dataName")));
	    this.insert(map);
	    return null;
	} else if (paraMap.get("type").equals("2")) {
	    if (amsArchivesType.size() > 0) {
		this.delete(amsArchivesType.get(0));
		return null;
	    }
	    return "请求失败，要删除的字典标识不存在";
	} else if (paraMap.get("type").equals("3")) {
	    if (amsArchivesType.size() > 0) {
		Map<String, Object> result = amsArchivesType.get(0);
		result.put("sortOrder", paraMap.get("dataValue"));
		result.put("name", paraMap.get("dataName"));
		result.put("ctime", new Date());
		result.put("creator", paraMap.get("userId"));
		result.put("mtime", new Date());
		result.put("modifier", paraMap.get("userId"));
		this.update(result);
		return null;
	    }
	    return "请求失败，要修改的字典标识不存在！";
	} else {
	    return "请求失败：您的同步类型<<" + paraMap.get("type") + ">>不存在";
	}

    }

}
