package com.bluemobi.serviceimpl.edition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.edition.EditionInfoDao;
import com.bluemobi.service.edition.EditionInfoService;

/**
 * 【对版本信息的管理包括版本地址url，版本类别,版本号，版本名称等信息的管理】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-06
 * 
 */
@Service(value = "editionInfoService")
public class EditionInfoServiceImpl extends MybatisBaseServiceImpl implements EditionInfoService {

    @Autowired
    private EditionInfoDao editionInfoDao;

    @Override
    public MyBatisBaseDao getDao() {
        return editionInfoDao;
    }

}
