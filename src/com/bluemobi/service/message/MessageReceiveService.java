package com.bluemobi.service.message;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【消息接收表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface MessageReceiveService extends MybatisBaseService {
	/**
	 * 消息数量查询
	 * 
	 * @param parmUp
	 */
	Integer selectMessagesNum(Map<String, Object> parmUp);

	/**
	 * 查询消息列表
	 * 
	 * @param parmUp
	 */
	public List<Map<String, Object>> selectMsgList(Map<String, Object> parmUp, Integer pageIndex, Integer pageSize);

	/**
	 * 根据多用户批量添加用户消息关系
	 * 
	 * @param parmUp
	 * @return
	 * @author huangzuoguo
	 * @date 2017年7月12日
	 * 
	 */
	public void addMessageReceives(Map<String, Object> parmUp);

	/**
	 * 批量 删除用户消息
	 * 
	 * @param split
	 *            用户消息Id
	 */
	void deletes(String[] split);

}
