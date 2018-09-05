package com.bluemobi.serviceimpl.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.system.SystemBankDao;
import com.bluemobi.service.system.SystemBankService;

/**
 * 【银行基础数据，银行编号，银行名称】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-10
 * 
 */
@Service(value = "systemBankService")
public class SystemBankServiceImpl extends MybatisBaseServiceImpl implements SystemBankService {

    @Autowired
    private SystemBankDao systemBankDao;

    @Override
    public MyBatisBaseDao getDao() {
        return systemBankDao;
    }

}
