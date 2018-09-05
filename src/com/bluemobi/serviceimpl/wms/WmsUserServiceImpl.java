package com.bluemobi.serviceimpl.wms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.wms.WmsUserDao;
import com.bluemobi.po.wms.WmsUser;
import com.bluemobi.service.wms.WmsUserService;

/**
 * 【库房用户表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-07
 * 
 */
@Service(value = "wmsUserService")
public class WmsUserServiceImpl extends MybatisBaseServiceImpl implements WmsUserService {

    @Autowired
    private WmsUserDao wmsUserDao;

    @Override
    public MyBatisBaseDao getDao() {
        return wmsUserDao;
    }

	/**
	 * 查询库房用户实现方法
	 * @param map
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月10日
	 * 
	 */
	@Override
	public List<Map<String, Object>> selectWmsAllUser(Map<String, Object> map) {
		return wmsUserDao.selectWmsAllUser(map);
	}

	/**
	 * 查询用户所有库房
	 * @param map
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月11日
	 * 
	 */
	@Override
	public List<Integer> selectUserStoreIds(String userId) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("userId", userId);
		// 查询得到用户所管理的库房
		List<WmsUser> storeList = new ArrayList<WmsUser>();
		List<Integer> storeIds = new ArrayList<Integer>();
		storeIds.add(-1);
		storeList = selectObjectList(reqMap);
		if (storeList != null && storeList.size() > 0) {
			for (WmsUser store : storeList) {
				storeIds.add(store.getStoreId());
			}
		}
		return storeIds;
	}

	/**
	 * 根据档案获取权限内用户
	 * @param map
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月12日
	 * 
	 */
	@Override
	public List<Map<String,Object>> selectUserByAms(Map<String, Object> map) {
		return wmsUserDao.selectUserByAms(map);
	}

}
