package com.bluemobi.serviceimpl.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.message.MessageSendDao;
import com.bluemobi.service.message.MessageSendService;

/**
 * 【消息发送表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "messageSendService")
public class MessageSendServiceImpl extends MybatisBaseServiceImpl implements MessageSendService {

    @Autowired
    private MessageSendDao messageSendDao;

    @Override
    public MyBatisBaseDao getDao() {
        return messageSendDao;
    }

}
