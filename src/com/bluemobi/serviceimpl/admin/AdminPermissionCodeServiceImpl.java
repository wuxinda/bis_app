package com.bluemobi.serviceimpl.admin;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminPermissionCodeDao;
import com.bluemobi.po.admin.AdminPermissionCode;
import com.bluemobi.service.admin.AdminPermissionCodeService;

/**
 * 【权限表代码表，用于控制前端的按钮显示】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
@Service(value = "adminPermissionCodeService")
public class AdminPermissionCodeServiceImpl extends MybatisBaseServiceImpl implements AdminPermissionCodeService {

    @Autowired
    private AdminPermissionCodeDao adminPermissionCodeDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminPermissionCodeDao;
    }

	/**
	 * 获取权限代码map，用于控制前端按钮显示
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-20 下午7:43:21
	 * @return
	 */
	@Override
	public Map<String, String> getPermissionCodeMap() {
		
		Map<String,String> permissionMap = new LinkedHashMap<String,String>();
	    Map<String,Object> map = new HashMap<String,Object>();
	    map.put("status",1);
	    List<AdminPermissionCode> codeList = adminPermissionCodeDao.selectObjectList(map);
	    for(AdminPermissionCode code:codeList){
	    	permissionMap.put(code.getCode(), code.getName());
	    }
	    return permissionMap;
	}

}
