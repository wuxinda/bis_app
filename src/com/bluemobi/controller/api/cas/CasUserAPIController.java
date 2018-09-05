package com.bluemobi.controller.api.cas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.util.SessionManager;
import com.appcore.util.StringUtil;
import com.appcore.util.UUIDService;
import com.bluemobi.apito.cas.CasUserTO;
import com.bluemobi.conf.Config;
import com.bluemobi.constant.AdminConstant;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.dao.wms.WmsStoreDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.util.RequestParamUtil;
import com.bluemobi.util.StringUtils;

/**
 * 【用户】控制器
 * @author Riven
 *
 */
@Controller(value = "casUserAPIController")
@RequestMapping("api/casUser")
public class CasUserAPIController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CasUserAPIController.class);

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private WmsStoreDao wmsStoreDao ;

	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public ResultTO login(HttpServletRequest request) {
		LOGGER.info("************* 登录-begin*****************");
		LOGGER.debug("请求ip【{}】，请求信息【{}】",
				new Object[] { request.getRemoteHost() });

		// 用户名
		String userName = RequestParamUtil.getEncodeParam(request, "userName");
		if (StringUtils.isEmpty(userName)) {
			return ResultTO.newFailResultTO("用户名不能为空", null);
		}

		// 密码
		String password = RequestParamUtil.getEncodeParam(request, "password");
		if (StringUtils.isEmpty(password)) {
			return ResultTO.newFailResultTO("密码不能为空", null);
		}
		//用户类型
		String userType = RequestParamUtil.getEncodeParam(request, "userType");
		if (StringUtils.isEmpty(password)) {
			return ResultTO.newFailResultTO("用户类型不能为空", null);
		}
		//返回结果
		CasUserTO casUserTO = null;
		AdminUser casUser = null;
		try {
			Map<String, Object> reqMap = new HashMap<String, Object>();
			// 2,校验用户是否已存在
			reqMap.put("userName", userName);
			List<AdminUser> casUserList = adminUserService.selectObjectList(reqMap);
			if (casUserList.isEmpty()) {
				return ResultTO.newFailResultTO("用户名不存在！", null);
			}
			//3.校验用户登录版本是否正确
			if (!casUserList.get(0).getUserType().toString().equals(userType.toString())) {
				if(casUserList.get(0).getUserType().toString().equals("1")){
					return ResultTO.newFailResultTO("您是领导用户，请登陆领导版本！", null);
				}else if(casUserList.get(0).getUserType().toString().equals("2")){
					return ResultTO.newFailResultTO("您是操作用户，请登陆操作版本！", null);
				}else if(casUserList.get(0).getUserType().equals("0")){
					//管理员用户登录
					LOGGER.info("*************管理员用户登录*****************");
				}
				
			}
			casUser = casUserList.get(0);
			String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(password)
					+ casUser.getSalt());
			if (!passwordMd5Md5.equals(casUser.getPassword())) {
				// 密码不正确
				LOGGER.info("用户【{}】登录的时候，密码【{}】不正确！");
				return ResultTO.newFailResultTO("密码不正确！", null);
			} else{
		        // 2、登录成功后操作
		        // 2.1、把user对象，保存到session中
		        // 用户登录成功后，需要通过sessionId设置用户属性到自定义session中
		        SessionManager.setAttribute(request, AdminConstant.KEY_ADMIN_USER, casUser);
		       //只返回要返回的字段
		        casUserTO = getCasUserTo(casUser,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("登录出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("登录失败！", null);
		}
		LOGGER.info("************* 登录-end*****************");
		return ResultTO.newSuccessResultTO("登录成功", casUserTO);
	}

	/**
	 * 注册
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "register")
	@ResponseBody
	public ResultTO register(HttpServletRequest request) {
		LOGGER.info("************* 用户注册-begin*****************");
		LOGGER.debug("请求ip【{}】，请求信息【{}】",
				new Object[] { request.getRemoteHost() });
		// 手机号
		String userName = RequestParamUtil.getEncodeParam(request, "userName");
		if (StringUtils.isEmpty(userName)) {
			return ResultTO.newFailResultTO("手机号不能为空", null);
		}

		// 密码
		String password = RequestParamUtil.getEncodeParam(request, "password");
		if (StringUtils.isEmpty(password)) {
			return ResultTO.newFailResultTO("密码不能为空", null);
		}

		// 用户类型 1、领导者，2、操作者
		String userType = RequestParamUtil.getEncodeParam(request, "userType");
		if (StringUtils.isEmpty(userType)) {
			return ResultTO.newFailResultTO("用户类型不能为空", null);
		}
		// 昵称	
		String nickname = RequestParamUtil.getEncodeParam(request, "nickname");
		if (StringUtils.isEmpty(nickname)) {
			return ResultTO.newFailResultTO("昵称不能为空", null);
		}
		try {
			
			Map<String, Object> reqMap = new HashMap<String, Object>();
			AdminUser casUser = new AdminUser();
			casUser.setUserName(userName);
			//手机号码为登录名
			casUser.setPhone(userName);
			casUser.setPassword(password);
			casUser.setFullname(nickname);
			casUser.setNickname(nickname);
			casUser.setUserType(Integer.parseInt(userType));
			// 2,校验用户是否已存在
			reqMap.put("userName", userName);
			List<Map<String, Object>> reqUser = adminUserService
					.selectMapList(reqMap);
			if (!reqUser.isEmpty()) {
				return ResultTO.newFailResultTO("该用户已经存在！请登陆！", null);
			}
			// 3，用户信息录入
			 adminUserService.regiest(casUser);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("注册出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("注册失败！", null);
		}
		LOGGER.info("************* 用户注册-end*****************");
		return ResultTO.newSuccessResultTO("注册成功！", null);
	}

	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "changePassword")
	@ResponseBody
	public ResultTO changePassword(HttpServletRequest request) {

		LOGGER.info("************* 修改密码-begin*****************");
		LOGGER.debug("请求ip【{}】，请求信息【{}】",
				new Object[] { request.getRemoteHost() });

		// 手机号
		String phone = RequestParamUtil.getEncodeParam(request, "phone");
		if (StringUtils.isEmpty(phone)) {
			return ResultTO.newFailResultTO("手机号不能为空", null);
		}

		// 密码
		String password = RequestParamUtil.getEncodeParam(request, "password");
		if (StringUtils.isEmpty(password)) {
			return ResultTO.newFailResultTO("密码不能为空", null);
		}
		//原始密码
		String oldPassword = RequestParamUtil.getEncodeParam(request, "oldPassword");
		if (StringUtils.isEmpty(oldPassword)) {
			return ResultTO.newFailResultTO("原始密码不能为空", null);
		}
		try {
			Map<String, Object> reqMap = new HashMap<String, Object>();
			// 2,校验用户是否已存在
			reqMap.put("phone", phone);
			List<AdminUser> casUserList = adminUserService.selectObjectList(reqMap);
			if (casUserList.isEmpty()) {
				return ResultTO.newFailResultTO("用户名不存在！", null);
			}			
			AdminUser casUser = casUserList.get(0);
			
			String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(oldPassword)
					+ casUser.getSalt());
			if (!passwordMd5Md5.equals(casUser.getPassword())) {
				// 密码不正确
				LOGGER.info("用户【{}】修改密码的时候，原始密码【{}】不正确！");
				return ResultTO.newFailResultTO("原始密码不正确！", null);
				
			//否则设置新密码
			} else{
			// 混淆码
			String salt = UUIDService.getUUID().subSequence(0, 6).toString();
			// 密码进行MD5加密
			String passwordMd5Md5New = StringUtil.md5(StringUtil.md5(password)
					+ salt);

			casUser.setPassword(passwordMd5Md5New);
			casUser.setSalt(salt);
			casUser.setMtime(new Date());
			adminUserService.update(casUser);
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("修改密码出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("修改密码失败！", null);
		}
		LOGGER.info("************* 修改密码-end*****************");
		return ResultTO.newSuccessResultTO("修改密码成功！", null);
	}
	/**
	 * @Title: getUserInfo
	 * @Description: 获取用户信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public ResultTO getUserInfo(HttpServletRequest request) {
		LOGGER.info("************* 获取用户信息-begin*****************");
		//返回结果
		CasUserTO casUserTO = null;
		AdminUser casUser = null;
		Map<String,Object> result = null;
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> StoreMap = new HashMap<>();
		Map<String,Object> reusltStoreMap = new HashMap<>();
		List<Integer> StoreList = new ArrayList<Integer>();
		List<Map<String,Object>> StoreListResult = null;
		try {
			// 用户id
			String userId = RequestParamUtil.getEncodeParam(request, "userId");
			if (StringUtils.isEmpty(userId)) {
				return ResultTO.newFailResultTO("用户id不能为空", null);
			}
			//根据id获取对象
			casUser = adminUserService.selectObject(userId);
			result = adminUserService.selectMap(userId);
			if(String.valueOf(result.get("enName")).indexOf(",")!=-1) {
				String[] storeIdList =String.valueOf(result.get("enName")).split(",");
				for(int i=0;i<storeIdList.length;i++) {
					StoreList.add(Integer.parseInt(storeIdList[i]));
				}
				StoreMap.put("list", StoreList);
				StoreListResult = wmsStoreDao.selectStoreMsgByStoresId(StoreMap);
				if(StoreListResult.size()>0) {
					String nameList = "";
					for(int j=0;j<storeIdList.length;j++) {
						for(int t=0;t<StoreListResult.size();t++) {
							if(storeIdList[j].equals(String.valueOf(StoreListResult.get(t).get("storeId")))) {
								if(t ==StoreListResult.size()-1) {
									nameList+=StoreListResult.get(t).get("name");
								}else {
									nameList+=StoreListResult.get(t).get("name")+",";
								}
							}
						}
					}
					result.put("name", nameList);
					if(null!=casUser)
						casUserTO = getCasUserTo(casUser,nameList);
				}
				
			}else {
				int storeIdList=Integer.parseInt(String.valueOf(result.get("enName")));
				StoreList.add(storeIdList);
				StoreMap.put("list", StoreList);
				StoreListResult = wmsStoreDao.selectStoreMsgByStoresId(StoreMap);
				if(StoreListResult.size()>0) {
						result.put("name", StoreListResult.get(0).get("name"));
				}
				if(null!=casUser)
					casUserTO = getCasUserTo(casUser,String.valueOf(StoreListResult.get(0).get("name")));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取用户信息出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("处理失败！", null);
		}
		LOGGER.info("************* 获取用户信息-end*****************");
		return ResultTO.newSuccessResultTO("处理成功！", casUserTO);
	}
	/**
	 * @Title: getUserInfo
	 * @Description: 获取用户全部信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/getUserAllInfo")
	@ResponseBody
	public ResultTO getUserAllInfo(HttpServletRequest request) {
		LOGGER.info("************* 获取用户全部信息-begin*****************");
		Map<String,Object> result = null;
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> StoreMap = new HashMap<>();
		Map<String,Object> reusltStoreMap = new HashMap<>();
		List<Integer> StoreList = new ArrayList<Integer>();
		List<Map<String,Object>> StoreListResult = null;
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		map.put("userId", userId);
		//返回结果
		try {
			result = adminUserService.selectMap(userId);
			if(result!=null&&result.size()>0) {
				if(String.valueOf(result.get("enName")).indexOf(",")!=-1) {
					String[] storeIdList =String.valueOf(result.get("enName")).split(",");
					for(int i=0;i<storeIdList.length;i++) {
						StoreList.add(Integer.parseInt(storeIdList[i]));
					}
					StoreMap.put("list", StoreList);
					StoreListResult = wmsStoreDao.selectStoreMsgByStoresId(StoreMap);
					if(StoreListResult.size()>0) {
						String nameList = "";
						for(int j=0;j<storeIdList.length;j++) {
							for(int t=0;t<StoreListResult.size();t++) {
								if(storeIdList[j].equals(String.valueOf(StoreListResult.get(t).get("storeId")))) {
									if(t ==StoreListResult.size()-1) {
										nameList+=StoreListResult.get(t).get("name");
									}else {
										nameList+=StoreListResult.get(t).get("name")+",";
									}
								}
							}
						}
						result.put("name", nameList);
					}
				}else {
					int storeIdList=Integer.parseInt(String.valueOf(result.get("enName")));
					StoreList.add(storeIdList);
					StoreMap.put("list", StoreList);
					StoreListResult = wmsStoreDao.selectStoreMsgByStoresId(StoreMap);
					if(StoreListResult.size()>0) {
							result.put("name", StoreListResult.get(0).get("name"));
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取用户全部信息出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("处理失败！", null);
		}
		LOGGER.info("************* 获取用户全部信息-end*****************");
		return ResultTO.newSuccessResultTO("处理成功！", result);
	}
	/**
	 * @Title: getImUserList
	 * @Description: 获取用户通讯录
	 * @param params
	 * @return
	 */
	@RequestMapping("/getImUserList")
	@ResponseBody
	public ResultTO getImUserList(HttpServletRequest request) {
		LOGGER.info("************* 获取用户通讯录-begin*****************");
		//返回结果
		List<CasUserTO> casUserTOList = new ArrayList<CasUserTO>();
		List<AdminUser> casUserList = null;
		try {
			//查询参数
			AdminUser parmUser = new AdminUser();
			//只查操作者 0.系统管理员  1、领导者 2、操作者
			parmUser.setUserType(null);
			//获取用户列表
			casUserList = adminUserService.selectObjectList(parmUser);
			//过滤返回字段
			for(AdminUser user : casUserList){
				CasUserTO casUserTO = new CasUserTO();
				casUserTO = getCasUserTo(user,null);
				casUserTOList.add(casUserTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("获取用户通讯录出现异常【{}】，请求ip【{}】，请求信息【{}】",
					new Object[] { e.getMessage(), request.getRemoteHost() });
			return ResultTO.newFailResultTO("处理失败！", null);
		}
		LOGGER.info("*************获取用户通讯录-end*****************");
		return ResultTO.newSuccessResultTO("处理成功！", casUserTOList);
	}
	
	/**
	 * 上传头像
	 * @param request
	 * @param avatar
	 * @return
	 */
	@RequestMapping("/updateUserImage")
	@ResponseBody
	public ResultTO updateUserImage(
			HttpServletRequest request,
			@RequestParam(value = "avatar", required = false) MultipartFile[] avatar) {
		LOGGER.info("************* 上传头像-begin*****************");
		// 用户id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		if (StringUtils.isEmpty(userId)) {
			return ResultTO.newFailResultTO("用户id不能为空", null);
		}
		//根据id获取对象
		AdminUser casUser = adminUserService.selectObject(userId);
		if (avatar != null && avatar.length > 0) {
			// 上传图片方法
			Map<String, Object> uploadResultMap = uploadImage(avatar,
					BaseConstant.USER_AVATAR_IMAGE);
			if (uploadResultMap != null
					&& (Boolean) uploadResultMap.get("flag")) {
				casUser.setAvatar(uploadResultMap.get("imageUrl").toString());
			}
		}
		casUser.setMtime(new Date());
		adminUserService.update(casUser);
		LOGGER.info("************* 上传头像-end*****************");

		return ResultTO.newSuccessResultTO("上传成功！", null);
	}
	
	/**
	 * 注销登陆
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public ResultTO logout(HttpServletRequest request) {
		LOGGER.info("************* 注销登陆-begin*****************");
		SessionManager.removeAttribute(request, AdminConstant.KEY_ADMIN_USER);
		
		LOGGER.info("登出成功");
		LOGGER.info("************* 注销登陆-begin*****************");
		return ResultTO.newSuccessResultTO("用户成功注销！", null);
	}
	/**
	 * 获取返回结果集
	 * @param adminUser
	 * @return
	 */
	public CasUserTO getCasUserTo(AdminUser adminUser,String nameList) {
		CasUserTO casUserTo = new CasUserTO();
		casUserTo.setUserId(adminUser.getUserId());
		casUserTo.setUserName(adminUser.getUserName());
		casUserTo.setPhone(adminUser.getPhone());
		casUserTo.setNickname(adminUser.getNickname());
		casUserTo.setEnName(adminUser.getEnName());
		casUserTo.setStoreName(nameList);
        //设置头像
	    if(!StringUtils.isEmpty(adminUser.getAvatar())){
			casUserTo.setAvatar(Config.TEMP_IMG_URL+adminUser.getAvatar());	
	    }
		casUserTo.setIsOnline(adminUser.getIsOnline());
		casUserTo.setUserType(adminUser.getUserType());
		return casUserTo;
	}
}
