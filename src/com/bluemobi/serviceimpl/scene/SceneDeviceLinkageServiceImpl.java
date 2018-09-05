package com.bluemobi.serviceimpl.scene;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.scene.SceneDeviceLinkageDao;
import com.bluemobi.service.scene.SceneDeviceLinkageService;

/**
 * 【场景模式设备联动表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2017-01
 * 
 */
@Service(value = "sceneDeviceLinkageService")
public class SceneDeviceLinkageServiceImpl extends MybatisBaseServiceImpl implements SceneDeviceLinkageService {

    @Autowired
    private SceneDeviceLinkageDao sceneDeviceLinkageDao;

    @Override
    public MyBatisBaseDao getDao() {
        return sceneDeviceLinkageDao;
    }

}
