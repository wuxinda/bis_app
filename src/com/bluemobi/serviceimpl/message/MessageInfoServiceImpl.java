package com.bluemobi.serviceimpl.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.message.MessageInfoDao;
import com.bluemobi.service.message.MessageInfoService;

/**
 * 【消息表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "messageInfoService")
public class MessageInfoServiceImpl extends MybatisBaseServiceImpl implements MessageInfoService {

    @Autowired
    private MessageInfoDao messageInfoDao;

    @Override
    public MyBatisBaseDao getDao() {
        return messageInfoDao;
    }

}
