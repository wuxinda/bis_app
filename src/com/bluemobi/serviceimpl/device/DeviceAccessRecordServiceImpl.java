package com.bluemobi.serviceimpl.device;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.device.DeviceAccessRecordDao;
import com.bluemobi.service.device.DeviceAccessRecordService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-12
 * 
 */
@Service(value = "deviceAccessRecordService")
public class DeviceAccessRecordServiceImpl extends MybatisBaseServiceImpl implements DeviceAccessRecordService {

    @Autowired
    private DeviceAccessRecordDao deviceAccessRecordDao;

    @Override
    public MyBatisBaseDao getDao() {
        return deviceAccessRecordDao;
    }
    /**
     * 获取门禁数据列表
     */
	@Override
	public List<Map<String, Object>> getAccessDataList(Map<String, Object> map) {
		List<Map<String,Object>> list=deviceAccessRecordDao.getAccessDataList(map);
		return list;
	}
	/**
     * 按时间统计门禁数据列表
     */
	public List<Map<String,Object>> getAccessDataCount(Map<String,Object> map) {
		List<Map<String,Object>> time=deviceAccessRecordDao.getAccessDataCount(map);
		return time;
	};
}
