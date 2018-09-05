package com.bluemobi.service.admin;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.admin.AdminMenu;
import com.bluemobi.po.admin.AdminUser;

/**
 * 【菜单表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
public interface AdminMenuService extends MybatisBaseService {
	
	/**
	 * 保存菜单
	 * @author haoj 309444359@qq.com
	 * @date 2016-8-31 下午2:43:47
	 * @param adminMenu
	 * @param adminUser
	 */
	void insertAdminMenu(AdminMenu adminMenu, AdminUser adminUser);
	
	
	/**
	 * 获取所有一级菜单列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-10 下午7:47:23
	 * @return
	 */
	List<AdminMenu> selectFirstMenus();
	
	/**
	 * 通过一级菜单id获取所有的二级菜单
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-10 下午7:47:28
	 * @param firstMenuId
	 * @return
	 */
	List<AdminMenu> selectSecondMenusByFirstMenuId(int firstMenuId);

	/**
	 * 通过二级菜单id获取一级菜单对象
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-10 下午7:47:41
	 * @param secondMenuId
	 * @return
	 */
	AdminMenu selectFirstMenuBySecondMenuId(int secondMenuId);

	
	


}
