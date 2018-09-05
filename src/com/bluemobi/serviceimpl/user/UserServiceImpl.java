package com.bluemobi.serviceimpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.user.UserDao;
import com.bluemobi.service.user.UserService;

/**
 * 【用户表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
@Service(value = "userService")
public class UserServiceImpl extends MybatisBaseServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public MyBatisBaseDao getDao() {
        return userDao;
    }

}
