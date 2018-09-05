package com.bluemobi.serviceimpl.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminMenuDao;
import com.bluemobi.po.admin.AdminMenu;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.service.admin.AdminMenuService;

/**
 * 【菜单表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-08
 * 
 */
@Service(value = "adminMenuService")
public class AdminMenuServiceImpl extends MybatisBaseServiceImpl implements AdminMenuService {

    @Autowired
    private AdminMenuDao adminMenuDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminMenuDao;
    }

	/**
	 * 保存菜单
	 * @author haoj 309444359@qq.com
	 * @date 2016-8-31 下午2:43:47
	 * @param adminMenu
	 * @param adminUser
	 */
	@Override
	public void insertAdminMenu(AdminMenu adminMenu, AdminUser adminUser) {
		
		int pid = adminMenu.getPid();
		if(pid==0){
			adminMenu.setGrade(1);//父级分类为0时候，菜单为一级菜单，否则为二级菜单
		}else{
			adminMenu.setGrade(2);//父级分类为0时候，菜单为一级菜单，否则为二级菜单
		}
		adminMenu.setCreator(adminUser.getUserId());
		adminMenu.setCtime(new Date());
		
		this.getDao().insert(adminMenu);
		
	}

	/**
	 * 获取所有一级菜单列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-10 下午7:47:23
	 * @return
	 */
	@Override
	public List<AdminMenu> selectFirstMenus() {

	    Map<Object, Object> map = new HashMap<Object, Object>();
	    map.put("pid", 0); 
	    List<AdminMenu> list = this.selectObjectList(map);
	    
		return list;
	}

	
	/**
	 * 通过一级菜单id获取所有的二级菜单
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-10 下午7:47:28
	 * @param firstMenuId
	 * @return
	 */
	@Override
	public List<AdminMenu> selectSecondMenusByFirstMenuId(int firstMenuId) {
	    
	    Map<Object, Object> map = new HashMap<Object, Object>();
	    map.put("pid", firstMenuId); 
	    List<AdminMenu> list = this.selectObjectList(map);
	    
		return list;
	}

	/**
	 * 通过二级菜单id获取一级菜单对象
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-10 下午7:47:41
	 * @param secondMenuId
	 * @return
	 */
	@Override
	public AdminMenu selectFirstMenuBySecondMenuId(int secondMenuId) {
	    Map<String, Object> map2 = new HashMap<String, Object>();
	    map2.put("menuId", secondMenuId);
	    AdminMenu adminMenu = this.selectObject(map2);
		return adminMenu;
	}
    

}
