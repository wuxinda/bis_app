package com.bluemobi.serviceimpl.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.StringUtil;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.dao.admin.AdminUserDao;
import com.bluemobi.dao.admin.AdminUserRoleDao;
import com.bluemobi.po.admin.AdminRole;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.admin.AdminUserRole;
import com.bluemobi.po.device.DeviceManage;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.to.admin.AdminMenuTO;
import com.bluemobi.to.admin.AdminUserTO;

/**
 * 【后台用户】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:43
 *
 */
@Service(value="adminUserService")
public class AdminUserServiceImpl extends MybatisBaseServiceImpl implements AdminUserService{
    
    @Autowired
    private AdminUserDao adminUserDao;
    
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return adminUserDao;
    }

    /**
     * 新增管理员用户
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:50:52
     * @param adminUser
     * @param roleIds
     * @param creator
     */
    public void insertUser(AdminUser adminUser, Integer[] roleIds, int creator) {
    	
    	//1、组装用户数据
        //混淆吗
        String salt = UUID.randomUUID().toString().subSequence(0, 6).toString();
        //密码进行MD5加密
        String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(adminUser.getPassword()) + salt);
        adminUser.setCreator(creator);
        adminUser.setCtime(new Date());
        adminUser.setPassword(passwordMd5Md5);
        adminUser.setSalt(salt);
        adminUser.setStatus(1);//默认状态为1
        adminUser.setIsOnline(0);
        //2、插入用户数据
        adminUserDao.insert(adminUser);
        
        //3、插入用户角色数据
        for(int roleId : roleIds){
        	AdminUserRole adminUserRole = new AdminUserRole();
        	adminUserRole.setUserId(adminUser.getUserId());
        	adminUserRole.setRoleId(roleId);
        	adminUserRole.setCreator(creator);
        	adminUserRole.setCtime(new Date());
        	adminUserRoleDao.insert(adminUserRole);
        }
        
    }

    /**
     * 修改管理员用户
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:51:03
     * @param user
     * @param roleIds
     * @param modifier
     */
    public void updateUser(AdminUser user, Integer[] roleIds, int modifier) {
    	//1、查询用户数据
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userId", user.getUserId());
        AdminUser adminUser = this.selectObject(map);
        
        //2、组装用户数据
        String pwd = "";
        if (!"".equals(adminUser.getPassword().trim())) {
            pwd = StringUtil.md5(user.getPassword())+adminUser.getSalt();
        }
        
        adminUser.setEmail(user.getEmail());
        adminUser.setFullname(user.getFullname());
        adminUser.setSex(user.getSex());
        adminUser.setNickname(user.getNickname());
        if (!"".equals(user.getPassword())) {
            adminUser.setPassword(StringUtil.md5(pwd));
        }
        adminUser.setStatus(user.getStatus());
        adminUser.setUserName(user.getUserName());
        adminUser.setModifier(modifier);
        adminUser.setMtime(new Date());
        
        //3、修改用户数据
        adminUserDao.update(adminUser);
        
        //4、删除用户角色数据
        Map<String,Object> map2 = new HashMap<String, Object>();
        map2.put("userId", adminUser.getUserId());
        adminUserRoleDao.delete(map2);
        
        //5、插入用户角色数据
        for(int roleId : roleIds){
        	AdminUserRole adminUserRole = new AdminUserRole();
        	adminUserRole.setUserId(adminUser.getUserId());
        	adminUserRole.setRoleId(roleId);
        	adminUserRole.setCreator(modifier);
        	adminUserRole.setCtime(new Date());
        	adminUserRoleDao.insert(adminUserRole);
        }
        
    }

    /**
     * 删除管理员用户
     * @author haoj 309444359@qq.com
     * @date 2016-9-17 下午11:51:42
     * @param userId
     */
    public void deleteUser(int userId) {
        //获得用户信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        AdminUser user = this.selectObject(map);
        if (user!=null) {
            //删除用户对应的权限关系 
        	
        }
        //删除用户
        adminUserDao.delete(map);
        
    }
    
    
    /**
     * 根据tokenId查询用户, 用于自动登录
     * 
     * @author haojian
     * @date 2016-6-24 下午2:16:26
     * @param userName
     * @return
     * @return AdminUser
     */
    @Override
    public AdminUser selectUserByTokenId(String tokenId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tokenId", tokenId);
        AdminUser casUser = adminUserDao.selectUserByTokenId(map);
        return casUser;
    }
    
	/**
	 * 获取用户菜单列表
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-11 下午9:59:29
	 * @param userId
	 * @param merchantId 平台商户id传 0 
	 * @return
	 */
	@Override
	public List<AdminMenuTO> selectAdminMenuTOList(int userId, int merchantId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		if(merchantId!=0){
			map.put("merchantId", merchantId);
		}
		List<AdminMenuTO> list = adminUserDao.selectAdminMenuTOList(map);
		return list;
	}
	
	/**
	 * 获得 用户在 某个菜单下面 的 权限按钮代码列表，用于页面显示或隐藏按钮
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-20 下午3:33:23
	 * @param userId
	 * @param url
	 * @return
	 */
	@Override
	public List<String> selectAdminPermissionCodeList(int userId, String url) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("url", url);
		List<String> list = adminUserDao.selectAdminPermissionCodeList(map);
		return list;
	}

	/**
	 * 查询用户角色
	 * @author haoj 309444359@qq.com
	 * @date 2016-9-17 下午4:46:27
	 * @param userId
	 * @return
	 */
	@Override
	public List<AdminRole> selectAdminRoleListByUserId(int userId) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<AdminRole> list = adminUserDao.selectAdminRoleListByUserId(map);
		return list;
	}

	@Override
	public Boolean changePassword(AdminUserTO user) {
		Map<String, Object> map = new HashMap<String, Object>();
    	map.put("userId", user.getUserId());
    	map.put("password", StringUtil.md5(user.getNewpass()));
    	map.put("modifier", user.getUserId());
    	map.put("mtime", new Date());
    	
    	adminUserDao.update(map);
    	return false;
	}

	/**
	 * 手机端用户注册
	 */
	@Override
	public AdminUser regiest(AdminUser casUser) {
    	//1、组装用户数据
        //混淆吗
        String salt = UUID.randomUUID().toString().subSequence(0, 6).toString();
        //密码进行MD5加密
        String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(casUser.getPassword()) + salt);
        //创建者默认为admin帐号
        casUser.setCreator(AdminConstant.USER_ADMIN_ID);
        casUser.setCtime(new Date());
        casUser.setPassword(passwordMd5Md5);
        casUser.setSalt(salt);
        casUser.setStatus(1);//默认状态为1
        casUser.setIsOnline(0);
        //2、插入用户数据
        adminUserDao.insert(casUser);
		return casUser;
	}

	@Override
	public void resetOnline(Map<String, Object> map) {
		adminUserDao.resetOnline(map);
	}
	/**
     * 获取所有人员列表
     */
	@Override
	public List<AdminUser> selectUserName() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		List<AdminUser> list = this.selectObjectList(map);
		return list;
	}    
}


