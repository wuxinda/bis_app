package com.bluemobi.serviceimpl.ecs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.ecs.EcsManageDao;
import com.bluemobi.service.ecs.EcsManageService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-10
 * 
 */
@Service(value = "ecsManageService")
public class EcsManageServiceImpl extends MybatisBaseServiceImpl implements EcsManageService {

	@Autowired
	private EcsManageDao ecsManageDao;

	@Override
	public MyBatisBaseDao getDao() {
		return ecsManageDao;
	}

	/**
	 * 获取能耗总数
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTotalData(Map<String, Object> map) {
		return ecsManageDao.getTotalData(map);
	}

	/**
	 * 获取库房能耗统计
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getStoreEcs(Map<String, Object> map) {

		return ecsManageDao.getStoreEcs(map);
	}

	/**
	 * 获取分项实际能耗统计
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getTypeEcs(Map<String, Object> map) {

		return ecsManageDao.getTypeEcs(map);
	}

}
