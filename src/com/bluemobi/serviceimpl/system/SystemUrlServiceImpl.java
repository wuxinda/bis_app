package com.bluemobi.serviceimpl.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.system.SystemUrlDao;
import com.bluemobi.po.system.SystemUrl;
import com.bluemobi.service.system.SystemUrlService;

/**
 * 【请求url，对应的请求名，用于通过url显示用户操作】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
@Service(value = "systemUrlService")
public class SystemUrlServiceImpl extends MybatisBaseServiceImpl implements SystemUrlService {

    @Autowired
    private SystemUrlDao systemUrlDao;

    @Override
    public MyBatisBaseDao getDao() {
        return systemUrlDao;
    }
	/**
	 * 获取行为轨迹url对应动作名称map
	 * @return
	 */
	public Map<String,String> getActionNameMap(){
		Map<String,String> actionNameMap = new HashMap<String,String>();
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("status",1);
	    List<SystemUrl> systemUrlList = systemUrlDao.selectObjectList(map);
	    for(SystemUrl systemUrl : systemUrlList){
	    	actionNameMap.put(systemUrl.getUrl(), systemUrl.getName());
	    }
		return actionNameMap;
	}
}
