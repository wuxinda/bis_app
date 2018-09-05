package com.bluemobi.serviceimpl.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.message.MessageReceiveDao;
import com.bluemobi.service.message.MessageReceiveService;

/**
 * 【消息接收表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "messageReceiveService")
public class MessageReceiveServiceImpl extends MybatisBaseServiceImpl implements MessageReceiveService {

	@Autowired
	private MessageReceiveDao messageReceiveDao;

	@Override
	public MyBatisBaseDao getDao() {
		return messageReceiveDao;
	}

	@Override
	public Integer selectMessagesNum(Map<String, Object> parmUp) {
		return messageReceiveDao.pageCount(parmUp);
	}

	@Override
	public List<Map<String, Object>> selectMsgList(Map<String, Object> parmUp, Integer pageIndex, Integer pageSize) {
		// 档案列表参数
		if (pageIndex != null && pageSize != null) {
			parmUp.put("offset", (pageIndex - 1) * pageSize);// 第几页（起始量）
			parmUp.put("rows", pageSize);// 每页条数（偏移量）
		}
		return messageReceiveDao.selectMsgByParam(parmUp);
	}

	@Override
	public void deletes(String[] split) {
		Map<String, Object> parmUp = new HashMap<String, Object>();
		parmUp.put("messageReciveId", split);
		parmUp.put("status", "-1");
		messageReceiveDao.updates(parmUp);

	}

	/**
	 * 根据多用户批量添加用户消息关系
	 * 
	 * @param parmUp
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月12日
	 * 
	 */
	@Override
	public void addMessageReceives(Map<String, Object> parmUp) {
		messageReceiveDao.addMessageReceives(parmUp);
	}

}
