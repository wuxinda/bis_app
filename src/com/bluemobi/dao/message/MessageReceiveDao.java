package com.bluemobi.dao.message;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;

/**
 * 【消息接收表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
public interface MessageReceiveDao extends MyBatisBaseDao {
    /**
     * 批量添加发送信息
     * @param parmUp
     */
    void addMessageReceives(Map<String, Object> parmUp);
    /**
     * 查询消息列表
     * @param parmUp
     */
    public List<Map<String, Object>> selectMsgByParam(Map<String, Object> parmUp);
    /**
     * 批量更新消息
     * @param split
     */
    void updates(Map<String, Object> parmUp);
}
