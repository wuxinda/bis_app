package com.bluemobi.serviceimpl.ams;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.ams.AmsArchivesTypeDao;
import com.bluemobi.po.ams.AmsArchivesType;
import com.bluemobi.service.ams.AmsArchivesTypeService;

/**
 * 【档案类型表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "amsArchivesTypeService")
public class AmsArchivesTypeServiceImpl extends MybatisBaseServiceImpl implements AmsArchivesTypeService {

    @Autowired
    private AmsArchivesTypeDao amsArchivesTypeDao;

    @Override
    public MyBatisBaseDao getDao() {
	return amsArchivesTypeDao;
    }

    @Override
    public List<AmsArchivesType> selectAmsArchivesType() {
	Map<Object, Object> map = new HashMap<Object, Object>();
	List<AmsArchivesType> list = this.selectObjectList(map);
	return list;
    }

    /**
     * 档案类型数据同步
     * 
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
	    paraMap.put("sortOrder", String.valueOf(paraMap.get("dataValue")));
	    paraMap.put("ctime", new Date());
	    paraMap.put("creator", Integer.parseInt(String.valueOf(paraMap.get("userId"))));
	    paraMap.put("name", paraMap.get("dataName"));
	    paraMap.put("remark", paraMap.get("dataName"));
	    this.insert(paraMap);
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
