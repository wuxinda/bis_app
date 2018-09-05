package com.bluemobi.service.admin;

import java.util.Map;

import com.appcore.service.MybatisBaseService;

/**
 * 【权限表代码表，用于控制前端的按钮显示】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-09
 * 
 */
public interface AdminPermissionCodeService extends MybatisBaseService {

	/**
	 * 获取权限代码map，用于控制前端按钮显示
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-20 下午7:43:21
	 * @return
	 */
	Map<String,String> getPermissionCodeMap();
	
}
