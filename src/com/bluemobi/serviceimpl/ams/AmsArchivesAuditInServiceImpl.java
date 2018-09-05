package com.bluemobi.serviceimpl.ams;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.conf.Config;
import com.bluemobi.dao.ams.AmsArchivesAuditInDao;
import com.bluemobi.dao.ams.AmsArchivesDao;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.message.MessageInfo;
import com.bluemobi.service.admin.AdminUserService;
import com.bluemobi.service.ams.AmsArchivesActlogService;
import com.bluemobi.service.ams.AmsArchivesAuditInService;
import com.bluemobi.service.wms.WmsUserService;
import com.bluemobi.util.DateUtil;
import com.bluemobi.util.HttpRequestUtil;
import com.bluemobi.util.MsgServiceUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 【档案申请审批表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-11
 * 
 */
@Service(value = "amsArchivesAuditInService")
public class AmsArchivesAuditInServiceImpl extends MybatisBaseServiceImpl implements AmsArchivesAuditInService {

	@Autowired
	private AmsArchivesAuditInDao amsArchivesAuditInDao;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AmsArchivesDao amsArchivesDao;
	@Autowired
	private AmsArchivesActlogService amsArchivesActlogService;
	@Autowired
	private WmsUserService wmsUserService;

	@Override
	public MyBatisBaseDao getDao() {
		return amsArchivesAuditInDao;
	}

	/**
	 * 根据用户分组获取审核列表
	 * 
	 * @param amsArchivesAuditIn
	 * @return
	 */
	public List<Map<String, Object>> getAuditInUserGroupList(Map<String, Object> parmMap) {
		// 档案列表参数
		if (parmMap.get("pageIndex") != null && parmMap.get("pageSize") != null) {
			parmMap.put("offset", (Integer.parseInt(String.valueOf(parmMap.get("pageIndex"))) - 1)
					* Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 第几页（起始量）
			parmMap.put("rows", Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 每页条数（偏移量）
		}
		return amsArchivesAuditInDao.getAuditInUserGroupList(parmMap);
	}

	/**
	 * 批量操作审核事项，只在领导端审批时使用
	 * 
	 * @param archivesIds
	 * @param handUserId
	 * @param handType
	 *            1.审核成功 2.审核拒绝
	 */
	@SuppressWarnings("unchecked")
	public void confirmHandAmsArchivesAuditIn(String[] AuditInIds, String handUserId, String handType) {
		// 组转参事
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 档案id集合
		parmMap.put("AuditInIds", AuditInIds);
		// 审核状态
		parmMap.put("status", handType);
		// 审核人
		if (handUserId != null)
			parmMap.put("modifier", Integer.parseInt(handUserId));
		// 审核时间
		parmMap.put("mtime", new Date());
		amsArchivesAuditInDao.handAmsArchivesAuditIn(parmMap);
		// 处理档案
		// 返回结果集,申请档案id//此处变更档案状态
		Map<String, Object> result1 = new HashMap<String, Object>();
		result1 = selectHandleAms(AuditInIds);
		String[] archivesIdsIn = new String[((List<Object>) result1.get("archivesIdsIn")).size()];
		for (int i = 0; i < ((List<Object>) result1.get("archivesIdsIn")).size(); i++) {
			archivesIdsIn[i] = String.valueOf(((List<Object>) result1.get("archivesIdsIn")).get(i));
		}
		String[] archivesIdsOut = new String[((List<Object>) result1.get("archivesIdsOut")).size()];
		for (int i = 0; i < ((List<Object>) result1.get("archivesIdsOut")).size(); i++) {
			archivesIdsOut[i] = String.valueOf(((List<Object>) result1.get("archivesIdsOut")).get(i));
		}
		// 更新档案状态
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (handType.equals("1")) {// 同意申请将档案状态变更为任务操作中 3.2如果是存档数量不变，取档数量也不便
									// 档案状态也不便（因为是多份）
			/*
			 * 这里是3.1以前的逻辑，是按照档案状态 paraMap.put("archivesIds", archivesIdsIn);
			 * paraMap.put("status", "10"); if (archivesIdsIn.length > 0)
			 * amsArchivesDao.updateAmsArchivesStatus(paraMap); paraMap.put("archivesIds",
			 * archivesIdsOut); if (archivesIdsOut.length > 0)
			 * amsArchivesDao.updateAmsArchivesStatus(paraMap);
			 */
		} else if (handType.equals("2")) {// 拒绝申请将档案状态恢复到之前要分出库和入库 3.2
											// 如果是存档档案数量不变，如果是取档，档案数量加1（在申请取档的时候档案数量减1了）
			/*
			 * 这里是3.1以前的逻辑，是按照档案状态 paraMap.put("archivesIds", archivesIdsIn);
			 * paraMap.put("status", "7"); if (archivesIdsIn.length > 0)
			 * amsArchivesDao.updateAmsArchivesStatus(paraMap); paraMap.put("archivesIds",
			 * archivesIdsOut); paraMap.put("status", "5"); if (archivesIdsOut.length > 0)
			 * amsArchivesDao.updateAmsArchivesStatus(paraMap);
			 */
			// 3.2更新档案数量 根据档案id更新档案库存量
			if (archivesIdsOut.length > 0) {
				paraMap.put("archivesIds", archivesIdsOut);
				paraMap.put("type", 0);// 加一
				amsArchivesDao.updateAmsNowNumber(paraMap);
			}

			if (archivesIdsIn.length > 0) {
				paraMap.put("archivesIds", archivesIdsIn);
				paraMap.put("type", 1);// 减一
				amsArchivesDao.updateAmsNowNumber(paraMap);
			}
		}
		MessageInfo messageInfo = new MessageInfo();
		// 获取是谁操作的档案
		Map<String, Object> parmMapu = new HashMap<String, Object>();
		parmMapu.put("userId", Integer.parseInt(handUserId));
		AdminUser resultUser = adminUserService.selectObject(parmMapu);
		if (handType.equals("1")) {
			messageInfo.setContent(resultUser.getFullname() + "审批通过了" + AuditInIds.length + "份档案申请，请留意！");
			messageInfo.setTitle("审批通过");
			messageInfo.setIsPush(1);
		} else if (handType.equals("2")) {
			messageInfo.setContent(resultUser.getFullname() + "审批拒绝了" + AuditInIds.length + "份档案申请，请留意！");
			messageInfo.setTitle("审批拒绝");
			messageInfo.setIsPush(1);
		} else if (handType.equals("3")) {
			messageInfo.setContent(resultUser.getFullname() + "执行了" + AuditInIds.length + "份档案申请任务，请留意！");
			messageInfo.setTitle("执行任务");
			messageInfo.setIsPush(0);
		}
		messageInfo.setCtime(new Date());
		messageInfo.setIsDel(0);
		messageInfo.setType(3);
		// 根据档案判断需要推送给哪些用户// 根据申请id查询得审核档案集合
		List<Map<String, Object>> amsList = new ArrayList<Map<String, Object>>();
		amsList = amsArchivesAuditInDao.selectHandleAms(parmMap);// 得到档案集合
		Map<String, Object> paraMaps = new HashMap<String, Object>();
		List<Integer> ls = new ArrayList<Integer>();
		if (amsList != null && amsList.size() > 0) {
			for (Map<String, Object> amsId : amsList) {
				ls.add(Integer.parseInt(String.valueOf(amsId.get("archivesId"))));// 得到档案id集合
			}
		}
		ls.add(-1);
		paraMaps.put("amsIds", ls);
		List<Map<String, Object>> userList = wmsUserService.selectUserByAms(paraMaps);// 得到用户集合
		List<Integer> userIdList = new ArrayList<Integer>();// 用户id集合
		if (userList != null && userList.size() > 0) {
			for (Map<String, Object> lss : userList) {
				if (handType.equals("3")) {// 执行任务推送给领导者 // 用户类型：0.系统管理员 1、领导者 2、操作者
					if (String.valueOf(lss.get("userType")).equals("1")) {
						userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
					}
				} else {// 其他都是领导审批 推送给操作者
					if (String.valueOf(lss.get("userType")).equals("2")) {
						userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
					}
				}

			}
		}
		MsgServiceUtil.messageService(messageInfo, userIdList);// 领导审批后推送
	}

	/**
	 * 批量操作审核(仅供3.0按通道提交任务使用)
	 * 
	 * @param archivesIds
	 * @param handUserId
	 * @param handType
	 */
	public void confirmAmsArchivesAuditIn(String[] AuditInIds, String handUserId, String handType) {
		// 组转参事
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 档案id集合
		parmMap.put("AuditInIds", AuditInIds);
		// 审核状态
		parmMap.put("status", handType);
		// 审核人
		if (handUserId != null)
			parmMap.put("modifier", Integer.parseInt(handUserId));
		// 审核时间
		parmMap.put("mtime", new Date());
		amsArchivesAuditInDao.handAmsArchivesAuditIn(parmMap);
		// 处理消息
		// 推送到所有在线领导用户，保存到接收表中关联所有领导用户
		// 获取所有领导用户Id
		Map<String, Object> parmUs = new HashMap<String, Object>();
		parmUs.put("userType", 2);// 用户类型：0.系统管理员 1、领导者 2、操作者
		List<AdminUser> UserList = adminUserService.selectObjectList(parmUs);
		List<Integer> userIds = new ArrayList<Integer>();
		for (AdminUser list : UserList) {
			userIds.add(list.getUserId());
		}
		MessageInfo messageInfo = new MessageInfo();
		// 组转参事
		Map<String, Object> parmMapu = new HashMap<String, Object>();
		parmMapu.put("userId", Integer.parseInt(handUserId));
		AdminUser resultUser = adminUserService.selectObject(parmMapu);
		if (handType.equals("1")) {
			messageInfo.setContent(resultUser.getFullname() + "审批通过了" + AuditInIds.length + "份档案申请");
			messageInfo.setTitle("审批通过");
			messageInfo.setIsPush(1);
		} else if (handType.equals("2")) {
			messageInfo.setContent(resultUser.getFullname() + "审批拒绝了" + AuditInIds.length + "份档案申请");
			messageInfo.setTitle("审批拒绝");
			messageInfo.setIsPush(1);
		} else if (handType.equals("3")) {
			messageInfo.setContent(resultUser.getFullname() + "执行了" + AuditInIds.length + "份档案申请任务");
			messageInfo.setTitle("执行任务");
			messageInfo.setIsPush(0);
		}
		messageInfo.setCtime(new Date());
		messageInfo.setIsDel(0);
		messageInfo.setType(3);
		// 根据档案判断需要推送给哪些用户// 根据申请id查询得审核档案集合
		List<Map<String, Object>> amsList = new ArrayList<Map<String, Object>>();
		amsList = amsArchivesAuditInDao.selectHandleAms(parmMap);// 得到档案集合
		Map<String, Object> paraMaps = new HashMap<String, Object>();
		List<Integer> ls = new ArrayList<Integer>();
		if (amsList != null && amsList.size() > 0) {
			for (Map<String, Object> amsId : amsList) {
				ls.add(Integer.parseInt(String.valueOf(amsId.get("archivesId"))));// 得到档案id集合
			}
		}
		ls.add(-1);
		paraMaps.put("amsIds", ls);
		List<Map<String, Object>> userList = wmsUserService.selectUserByAms(paraMaps);// 得到用户集合
		List<Integer> userIdList = new ArrayList<Integer>();// 用户id集合
		if (userList != null && userList.size() > 0) {
			for (Map<String, Object> lss : userList) {
				if (handType.equals("3")) {// 执行任务推送给领导者 // 用户类型：0.系统管理员 1、领导者 2、操作者
					if (String.valueOf(lss.get("userType")).equals("1")) {
						userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
					}
				} else {// 其他都是领导审批 推送给操作者
					if (String.valueOf(lss.get("userType")).equals("2")) {
						userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
					}
				}
			}
		}
		MsgServiceUtil.messageService(messageInfo, userIdList);// 领导审批后推送
	}

	/**
	 * 批量操作审核(3.1提交任务使用)
	 * 
	 * @param archivesIds
	 * @param handUserId
	 * @param handType
	 */
	public String confirmCommitTaskAmsArchivesAuditIn(String[] AuditInIds, String handUserId, String handType,
			Map<String, Object> parmMaps) {
		// 返回状态0，成功1，失败
		String res = "";
		// 组转参事
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 档案id集合
		parmMap.put("AuditInIds", AuditInIds);
		// 审核状态
		parmMap.put("status", handType);
		// 审核人
		if (handUserId != null)
			parmMap.put("modifier", Integer.parseInt(handUserId));
		// 审核时间
		parmMap.put("mtime", new Date());
		// 变更申请单状态
		amsArchivesAuditInDao.handAmsArchivesAuditIn(parmMap);
		// 出里档案和档案操作记录
		// 变更档案状态
		/*
		 * 3.1之前的逻辑 档案数量在申请的时候已经变更过此处无需变更
		 * amsArchivesAuditInDao.commitTaskUpdayeAms(parmMaps);
		 */
		// 调用中信接口，根据申请id进行相应的查询得到
		if (Config.ZX_IS_STAR.equals("1")) {
			List<Map<String, String>> param = null;
			param = amsArchivesAuditInDao.getOtherAPIparam(parmMap);
			try {
				res = HttpRequestUtil.sendPostRequest(Config.ZX_API_URL + "/szdag/api/otherapi/AuditInConfirm",
						"data=" + JSONArray.fromObject(param));
				JSONObject jsonObject = JSONObject.fromObject(res);
				if (String.valueOf(jsonObject.get("status")).equals("1")) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return String.valueOf(jsonObject.get("status"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return "1";
			}
		}

		// 添加档案操作记录
		amsArchivesActlogService.insertAmsActlogs(parmMaps);
		// 处理消息
		// 推送到所有在线领导用户，保存到接收表中关联所有领导用户
		// 获取所有领导用户Id
		Map<String, Object> parmUs = new HashMap<String, Object>();
		parmUs.put("userType", 2);// 用户类型：0.系统管理员 1、领导者 2、操作者
		List<AdminUser> UserList = adminUserService.selectObjectList(parmUs);
		List<Integer> userIds = new ArrayList<Integer>();
		for (AdminUser list : UserList) {
			userIds.add(list.getUserId());
		}
		MessageInfo messageInfo = new MessageInfo();
		// 组转参事
		Map<String, Object> parmMapu = new HashMap<String, Object>();
		parmMapu.put("userId", Integer.parseInt(handUserId));
		AdminUser resultUser = adminUserService.selectObject(parmMapu);
		if (handType.equals("1")) {
			messageInfo.setContent(resultUser.getFullname() + "审批通过了" + AuditInIds.length + "份档案申请");
			messageInfo.setTitle("审批通过");
			messageInfo.setIsPush(1);
		} else if (handType.equals("2")) {
			messageInfo.setContent(resultUser.getFullname() + "审批拒绝了" + AuditInIds.length + "份档案申请");
			messageInfo.setTitle("审批拒绝");
			messageInfo.setIsPush(1);
		} else if (handType.equals("3")) {
			messageInfo.setContent(resultUser.getFullname() + "执行了" + AuditInIds.length + "份档案申请任务");
			messageInfo.setTitle("执行任务");
			messageInfo.setIsPush(1);
		}
		messageInfo.setCtime(new Date());
		messageInfo.setIsDel(0);
		messageInfo.setType(3);
		// 根据档案判断需要推送给哪些用户// 根据申请id查询得审核档案集合
		List<Map<String, Object>> amsList = new ArrayList<Map<String, Object>>();
		amsList = amsArchivesAuditInDao.selectHandleAms(parmMap);// 得到档案集合
		Map<String, Object> paraMaps = new HashMap<String, Object>();
		List<Integer> ls = new ArrayList<Integer>();
		if (amsList != null && amsList.size() > 0) {
			for (Map<String, Object> amsId : amsList) {
				ls.add(Integer.parseInt(String.valueOf(amsId.get("archivesId"))));// 得到档案id集合
			}
		}
		ls.add(-1);
		paraMaps.put("amsIds", ls);
		List<Map<String, Object>> userList = wmsUserService.selectUserByAms(paraMaps);// 得到用户集合
		List<Integer> userIdList = new ArrayList<Integer>();// 用户id集合
		if (userList != null && userList.size() > 0) {
			for (Map<String, Object> lss : userList) {
				if (handType.equals("3")) {// 执行任务推送给领导者 // 用户类型：0.系统管理员 1、领导者 2、操作者
					if (String.valueOf(lss.get("userType")).equals("1")) {
						userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
					}
				} else {// 其他都是领导审批 推送给操作者
					if (String.valueOf(lss.get("userType")).equals("2")) {
						userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
					}
				}
			}
		}
		MsgServiceUtil.messageService(messageInfo, userIdList);// 领导审批后推送
		return res;
	}

	/**
	 * 批量添加申请事项3.0
	 * 
	 * @param archivesIds
	 *            档案集合
	 * @param creator
	 *            创建人
	 * @param userId
	 *            申请人
	 * @param type
	 *            0.存档 1.取档
	 */
	public void addAmsArchivesAuditIns(String creator, String userId, String type, String[] archivesIds,
			Map<String, Object> parmMaps) {
		// 组转参事
		Map<String, Object> parmMap = new HashMap<String, Object>();
		// 档案id集合
		parmMap.put("archivesIds", archivesIds);
		// 申请类型
		parmMap.put("type", type);
		// 申请状态初始为待审批 审核状态 0.待审批 1.审核通过 2.审核拒绝 3.已完成
		parmMap.put("status", 0);
		// 申请人
		parmMap.put("userId", Integer.parseInt(userId));
		// 创建人
		parmMap.put("creator", Integer.parseInt(creator));
		// 申请时间
		parmMap.put("ctime", new Date());
		// 申请单号
		parmMap.put("applyNo", DateUtil.getTimeStamp());
		// 申请人姓名
		AdminUser parmMapq = adminUserService.selectObject(parmMap);
		parmMap.put("applyUser", parmMapq.getFullname());
		// 调用dao层插入数据
		amsArchivesAuditInDao.addAmsArchivesAuditIns(parmMap);
		// 处理消息,推送到所有在线领导用户，保存到接收表中关联所有领导用户
		// 获取所有领导用户Id
		Map<String, Object> parmUs = new HashMap<String, Object>();
		parmUs.put("userType", 1);// 用户类型：0.系统管理员 1、领导者 2、操作者
		List<Map<String, Object>> UserList = adminUserService.selectMapList(parmUs);
		List<Integer> userIds = new ArrayList<Integer>();
		for (Map<String, Object> list : UserList) {
			userIds.add((Integer.parseInt(String.valueOf(list.get("userId")))));
		}
		MessageInfo messageInfo = new MessageInfo();
		// 组转参事
		Map<String, Object> parmMapu = new HashMap<String, Object>();
		parmMapu.put("userId", Integer.parseInt(userId));
		AdminUser resultUser = adminUserService.selectObject(parmMapu);
		messageInfo.setContent(
				resultUser.getFullname() + "发起了" + archivesIds.length + "份" + (type.equals("0") ? "存档" : "取档") + "申请");
		messageInfo.setCtime(new Date());
		messageInfo.setIsDel(0);
		messageInfo.setIsPush(1);
		messageInfo.setType(3);
		messageInfo.setTitle((type.equals("0") ? "存档" : "取档") + "申请");
		// 根据档案判断需要推送给哪些用户
		Map<String, Object> paraMaps = new HashMap<String, Object>();
		paraMaps.put("amsIds", archivesIds);
		List<Integer> amss = new ArrayList<Integer>();
		for (int i = 0; i < archivesIds.length; i++) {
			amss.add(Integer.parseInt(archivesIds[i]));
		}
		amss.add(-1);
		paraMaps.put("amsIds", amss);
		List<Map<String, Object>> userList = wmsUserService.selectUserByAms(paraMaps);// 得到用户集合
		List<Integer> userIdList = new ArrayList<Integer>();// 用户id集合
		if (userList != null && userList.size() > 0) {
			for (Map<String, Object> lss : userList) {
				if (String.valueOf(lss.get("userType")).equals("1")) {// 发起申请推送给领导者 // 用户类型：0.系统管理员 1、领导者 2、操作者
					userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
				}
			}
		}
		MsgServiceUtil.messageService(messageInfo, userIdList);// 发起申请后推送

		// 添加申请后改变档案表对应档案的状态，防止资源冲突//此处变更档案状态
		/*
		 * 3.1以前的逻辑 amsArchivesDao.updateAmsArchivesStatus(parmMaps);
		 */
		if (parmMap.get("type").equals("1")) {// 取档档案数量减一
			parmMaps.put("type", 1);
			amsArchivesDao.updateAmsNowNumber(parmMaps);
		} else if (parmMap.get("type").equals("0")) {// 存档档案数量加一
			parmMaps.put("type", 0);
			amsArchivesDao.updateAmsNowNumber(parmMaps);
		}
	}

	/**
	 * 档案操作获取任务详情服务实现类
	 */
	@Override
	public List<Map<String, Object>> selectTaskInfo(String userId, String status) {
		// 返回结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("status", status);
		// 通道档案
		List<Map<String, Object>> galleryList = amsArchivesAuditInDao.selectTaskInfo(parmMap);
		// 库房档案
		List<Map<String, Object>> storeAmss = amsArchivesAuditInDao.selectTaskStoreNum(parmMap);
		// 库区档案
		List<Map<String, Object>> areaAmss = amsArchivesAuditInDao.selectTaskStoreAreaNum(parmMap);
		for (Map<String, Object> storeAms : storeAmss) {
			Map<String, Object> storeData = new HashMap<String, Object>();
			storeData.put("storeId", storeAms.get("strore_id"));
			storeData.put("storeName", storeAms.get("storeName"));
			storeData.put("storeAmsCount", storeAms.get("count"));
			// 库房里库区结果集合
			List<Map<String, Object>> areaDatas = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> areaAms : areaAmss) {
				if (areaAms.get("strore_id").equals(storeAms.get("strore_id"))) {
					Map<String, Object> areaData = new HashMap<String, Object>();
					areaData.put("areaId", areaAms.get("area_id"));
					areaData.put("areaName", areaAms.get("areaName"));
					areaData.put("areaAmsCount", areaAms.get("count"));
					// 库区里通道结果集合
					List<Map<String, Object>> galleryDatas = new ArrayList<Map<String, Object>>();
					for (Map<String, Object> galleryData : galleryList) {
						if (galleryData.get("storeId").equals(storeAms.get("strore_id"))
								&& galleryData.get("areaId").equals(areaAms.get("area_id"))) {
							Map<String, Object> gallery = new HashMap<String, Object>();
							gallery.put("galleryNum", galleryData.get("galleryNum"));
							gallery.put("amsCount", galleryData.get("count"));
							gallery.put("deviceId", galleryData.get("deviceId"));
							gallery.put("storeLr", galleryData.get("storeLr"));
							galleryDatas.add(gallery);
						}
					}
					areaData.put("galleryData", galleryDatas);
					areaDatas.add(areaData);
				}
			}
			storeData.put("AreaData", areaDatas);
			result.add(storeData);
		}

		return result;
	}

	/**
	 * 执行任务接口服务类
	 */
	@Override
	public Map<String, Object> selectTaskExecute(Map<String, Object> parmMap) {
		// 返回结果集
		Map<String, Object> result = new HashMap<String, Object>();
		// 已完成参数
		Map<String, Object> finishedNumparm = new HashMap<String, Object>();
		finishedNumparm.put("userId", parmMap.get("userId"));
		finishedNumparm.put("status", 3);// 审核状态 0.待审批 1.审核通过 2.审核拒绝 3.已完成
		// 未完成参数
		Map<String, Object> willExecuteNumparm = new HashMap<String, Object>();
		willExecuteNumparm.put("userId", parmMap.get("userId"));
		willExecuteNumparm.put("status", 1);// 审核状态 0.待审批 1.审核通过 2.审核拒绝 3.已完成
		// 档案列表参数
		parmMap.put("offset", (Integer.parseInt(String.valueOf(parmMap.get("pageIndex"))) - 1)
				* Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 第几页（起始量）
		parmMap.put("rows", Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 每页条数（偏移量）
		result.put("finishedNum", amsArchivesAuditInDao.pageCount(finishedNumparm));
		result.put("willExecuteNum", amsArchivesAuditInDao.pageCount(willExecuteNumparm));
		result.put("amsList", amsArchivesAuditInDao.selectTaskExecute(parmMap));
		return result;
	}

	/**
	 * 提交任务
	 */
	@Override
	public Map<String, Object> selectTaskSubmit(Map<String, Object> parmMap) {
		// 返回结果集
		Map<String, Object> result = new HashMap<String, Object>();
		// 定义三个集合分别存放通道内申请单id，出库取档档案id，入库存档档案id
		List<Object> AuditInIds = new ArrayList<Object>();
		List<Object> archivesIdsIn = new ArrayList<Object>();// 存档、入库档案集合
		List<Object> archivesIdsOut = new ArrayList<Object>();// 取档、出库档案集合
		// 查询得到通道档案集合
		List<Map<String, Object>> amsList = new ArrayList<Map<String, Object>>();
		amsList = amsArchivesAuditInDao.selectTaskExecute(parmMap);
		for (Map<String, Object> list : amsList) {
			AuditInIds.add(list.get("AuditInId"));
			if (list.get("AuditInType").equals("0")) {// 0.存档 1.取档
				archivesIdsIn.add(list.get("archivesId"));
			} else if (list.get("AuditInType").equals("1")) {
				archivesIdsOut.add(list.get("archivesId"));
			}

		}
		result.put("AuditInIds", AuditInIds);
		result.put("archivesIdsIn", archivesIdsIn);
		result.put("archivesIdsOut", archivesIdsOut);
		return result;
	}

	/**
	 * 获取审批档案集合
	 */
	@Override
	public Map<String, Object> selectHandleAms(String[] parmMap) {
		// 返回结果集
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AuditInIds", parmMap);
		// 查询得审核档案集合
		List<Map<String, Object>> amsList = new ArrayList<Map<String, Object>>();
		amsList = amsArchivesAuditInDao.selectHandleAms(param);
		// 出库取档档案id，入库存档档案id
		List<Object> archivesIdsIn = new ArrayList<Object>();
		List<Object> archivesIdsOut = new ArrayList<Object>();
		for (Map<String, Object> list : amsList) {
			if (list.get("type").equals("0")) {// 0.存档 1.取档
				archivesIdsIn.add(list.get("archivesId"));
			} else if (list.get("type").equals("1")) {
				archivesIdsOut.add(list.get("archivesId"));
			}
		}
		result.put("archivesIdsIn", archivesIdsIn);
		result.put("archivesIdsOut", archivesIdsOut);
		return result;
	}

	@Override
	public Integer confirmTaskCancel(String[] split) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AuditInIds", split);
		param.put("status", "-1");
		// 更新档案---恢复到之前的状态
		amsArchivesDao.taskCancelUpdate(param);
		// 更新申请单
		amsArchivesAuditInDao.updates(param);
		return null;
	}

	@Override
	public Integer confirmGoHistory(String[] split, String[] amsIdin, String[] amsIdout) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("AuditInIds", split);
		param.put("status", "1");
		// 更新申请单 ---恢复到审核通过状态（待执行）
		amsArchivesAuditInDao.updates(param);
		// 更新档案 工作流变更为任务操作中 在库状态
		param.put("archivesIds", amsIdin);
		param.put("status", "10");
		param.put("inflag", "1");
		amsArchivesDao.updateAmsArchivesStatus(param);
		param.put("archivesIds", amsIdout);
		param.put("status", "10");
		param.put("inflag", "0");
		amsArchivesDao.updateAmsArchivesStatus(param);
		return null;
	}

	@Override
	public List<Map<String, Object>> getTaskList(Map<String, Object> parmMap) {
		parmMap.put("offset", (Integer.parseInt(String.valueOf(parmMap.get("pageIndex"))) - 1)
				* Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 第几页（起始量）
		parmMap.put("rows", Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 每页条数（偏移量）
		List<Map<String, Object>> taskList = new ArrayList<Map<String, Object>>();// 返回列表
		List<Map<String, Object>> taskRe = new ArrayList<Map<String, Object>>();// 未完成删除
		try {
			taskList = amsArchivesAuditInDao.getTaskList(parmMap);
			List<Map<String, Object>> taskListNum = new ArrayList<Map<String, Object>>();// 总数
			taskListNum = amsArchivesAuditInDao.getTaskListNum(parmMap);
			if (taskListNum != null && taskListNum.size() > 0) {
				for (Map<String, Object> task : taskList) {
					for (Map<String, Object> taskNum : taskListNum) {
						if (task.get("status").equals("1") && task.get("applyNo").equals(taskNum.get("applyNo"))) {// 查出所有未完成
							task.put("unfinished", task.get("count"));
							task.put("total", taskNum.get("count"));
							task.put("finish", Integer.parseInt(String.valueOf(taskNum.get("count")))
									- Integer.parseInt(String.valueOf(task.get("count"))));
						} else if (task.get("status").equals("3")
								&& task.get("applyNo").equals(taskNum.get("applyNo"))) {// 查出所有已完成
							task.put("finish", task.get("count"));
							task.put("total", taskNum.get("count"));
							task.put("unfinished", Integer.parseInt(String.valueOf(taskNum.get("count")))
									- Integer.parseInt(String.valueOf(task.get("count"))));
							if (!task.get("count").equals(taskNum.get("count"))) {
								taskRe.add(task);// 已完成时 如果已完成数不等于总数说明是部分完成要删掉
							}
						}
					}
					task.remove("count");
				}
				for (Map<String, Object> retask : taskRe) {
					taskList.remove(retask);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	/**
	 * 获取任务单里档案列表3.1
	 * 
	 * @param parmMap
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getApplyAmsList(Map<String, Object> parmMap) {
		List<Map<String, Object>> taskAmsList = new ArrayList<Map<String, Object>>();// 返回列表
		parmMap.put("offset", (Integer.parseInt(String.valueOf(parmMap.get("pageIndex"))) - 1)
				* Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 第几页（起始量）
		parmMap.put("rows", Integer.parseInt(String.valueOf(parmMap.get("pageSize"))));// 每页条数（偏移量）
		taskAmsList = amsArchivesAuditInDao.getApplyAmsList(parmMap);
		return taskAmsList;
	}

	/**
	 * 检查申请单和档案状态是否可以提交任务3.1
	 * 
	 * @param parmMap
	 * @return
	 */
	@Override
	public String checkstatus(Map<String, Object> parmMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();// 返回列表
		list = amsArchivesAuditInDao.getApplyAms(parmMap);
		for (Map<String, Object> lt : list) {// 盘点状态 0.待入库 1.入库审批中
			// 2.上架审批中3.借阅审批中4.待上架5.在库6.待归还7.出库8.待出库
			// 9.出库审批中10.任务操作中
			if (lt.get("status").equals("3")) {
				return "申请单状态不支持";
			}

			/*
			 * 3.1的逻辑 if (!String.valueOf(lt.get("check_status")).equals("10")) { return
			 * "档案在库状态不支持"; }
			 */
			if (lt.get("type").equals("0")) {// 存档
				if (Integer.parseInt(String.valueOf(lt.get("now_number"))) > Integer
						.parseInt(String.valueOf(lt.get("total_number")))) {
					return "请求失败，档案号为:" + lt.get("archiveno") + "的实际库存量已经大于总库存，请检查后再提交";
				}
			} else if (lt.get("type").equals("1")) {// 取档
				if (Integer.parseInt(String.valueOf(lt.get("now_number"))) < 0) {
					return "请求失败，档案号为:" + lt.get("total_number") + "的实际库存量已经小于0，请检查后再提交";
				}
			}
			if (!lt.get("type").equals("0") && !lt.get("type").equals("1")) {
				return "操作类型不支持";
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getGalleryList(String userId, String status, String applyNo) {
		// 返回结果集
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		// 参数格式化
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("userId", userId);
		parmMap.put("status", status);
		parmMap.put("applyNo", applyNo);
		parmMap.put("storeIds", wmsUserService.selectUserStoreIds(userId));
		// 通道档案
		List<Map<String, Object>> galleryList = amsArchivesAuditInDao.selectTaskGallaryList(parmMap);
		// 库房档案
		List<Map<String, Object>> storeAmss = amsArchivesAuditInDao.selectTaskStoreNums(parmMap);
		// 库区档案
		List<Map<String, Object>> areaAmss = amsArchivesAuditInDao.selectTaskStoreAreaNums(parmMap);
		for (Map<String, Object> storeAms : storeAmss) {
			Map<String, Object> storeData = new HashMap<String, Object>();
			storeData.put("storeId", storeAms.get("strore_id"));
			storeData.put("storeName", storeAms.get("storeName"));
			storeData.put("storeAmsCount", storeAms.get("count"));
			// 库房里库区结果集合
			List<Map<String, Object>> areaDatas = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> areaAms : areaAmss) {
				if (areaAms.get("strore_id").equals(storeAms.get("strore_id"))) {
					Map<String, Object> areaData = new HashMap<String, Object>();
					areaData.put("areaId", areaAms.get("area_id"));
					areaData.put("areaName", areaAms.get("areaName"));
					areaData.put("areaAmsCount", areaAms.get("count"));
					// 库区里通道结果集合
					List<Map<String, Object>> galleryDatas = new ArrayList<Map<String, Object>>();
					for (Map<String, Object> galleryData : galleryList) {
						if (galleryData.get("storeId").equals(storeAms.get("strore_id"))
								&& galleryData.get("areaId").equals(areaAms.get("area_id"))) {
							Map<String, Object> gallery = new HashMap<String, Object>();
							gallery.put("galleryNum", galleryData.get("galleryNum"));
							gallery.put("amsCount", galleryData.get("count"));
							gallery.put("deviceId", galleryData.get("deciveId"));
							gallery.put("storeLr", galleryData.get("storeLr"));
							gallery.put("AuditInIds", galleryData.get("AuditInId"));
							galleryDatas.add(gallery);
						}
					}
					areaData.put("galleryData", galleryDatas);
					areaDatas.add(areaData);
				}
			}
			storeData.put("AreaData", areaDatas);
			result.add(storeData);
		}

		return result;
	}

	/**
	 * 批量添加调档事项3.1
	 * 
	 */
	@Override
	public String confirmaddAmsApplys(Map<String, Object> parmMap) {
		// 整理更新档案参数
		Map<String, Object> parmMaps = new HashMap<String, Object>();
		String[] archivesIds = null;
		// 1.验证档案状态是否可以发起调档
		List<Map<String, Object>> amsList = new ArrayList<Map<String, Object>>();
		try {
			// 查询将要调取的档案   根据档案号和档案类型查询code查询
			amsList = amsArchivesDao.selectAmsListByArchiveno(parmMap);
			// 判断档案是否全部存在
			String[] archiveNos = (String[]) parmMap.get("archiveNos");
			if (amsList.size() < archiveNos.length) {
				List<String> getlist = new ArrayList<String>();
				for (int i = 0; i < amsList.size(); i++) {
					getlist.add(String.valueOf(amsList.get(i).get("archiveno")));
				}
				List<String> applylist = new ArrayList<String>();
				for (String ss : archiveNos) {
					applylist.add(ss);
				}
				for (String no : getlist) {
					applylist.remove(no);
				}
				return "请求失败，档案号为：" + applylist.toString() + "的档案不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "请求失败";
		}
		archivesIds = new String[amsList.size()];
		// 验证档案状态
		for (int i = 0; i < amsList.size(); i++) {
			archivesIds[i] = String.valueOf(amsList.get(i).get("archivesId"));
			if (parmMap.get("type").equals("0")) {// 存档
				/*
				 * 3.1以前的逻辑 if (!String.valueOf(amsList.get(i).get("checkStatus")).equals( "7"))
				 * { return "请求失败，档案号为:" + amsList.get(i).get("archiveno") +
				 * "状态非<<出库>>不能发起还档申请"; }
				 */
				if (Integer.parseInt(String.valueOf(amsList.get(i).get("nowNumber"))) == Integer
						.parseInt(String.valueOf(amsList.get(i).get("totalNumber")))
						|| Integer.parseInt(String.valueOf(amsList.get(i).get("nowNumber"))) > Integer
								.parseInt(String.valueOf(amsList.get(i).get("totalNumber")))) {
					return "请求失败，档案号为:" + amsList.get(i).get("archiveno") + "实际库存量已经等于或者大于总库存，请检查后再申请";
				}
			}
			if (parmMap.get("type").equals("1")) {// 取档
				/*
				 * 3.1以前的逻辑 if (!String.valueOf(amsList.get(i).get("checkStatus")).equals( "5"))
				 * { return "请求失败，档案号为:" + amsList.get(i).get("archiveno") +
				 * "状态非<<在库>>不能发起取档申请"; }
				 */
				if (Integer.parseInt(String.valueOf(amsList.get(i).get("nowNumber"))) == 0
						|| Integer.parseInt(String.valueOf(amsList.get(i).get("nowNumber"))) < 0) {
					return "请求失败，档案号为:" + amsList.get(i).get("archiveno") + "实际库存等于0或者小于0不能发起取档";
				}
			}
		}

		parmMaps.put("archivesIds", archivesIds);
		parmMaps.put("status", 10);
		// 验证通过，将申请添加至数据库持久化
		// 申请状态初始为待审批 审核状态 0.待审批 1.审核通过 2.审核拒绝 3.已完成
		parmMap.put("status", 1);
		// 申请时间
		parmMap.put("ctime", new Date());
		parmMap.put("archivesIds", archivesIds);
		// 申请人----------------------------------------------此处由于是外部申请，没有做用户同步，所以申请人就是默认admin
		parmMap.put("userId", 1);
		// 创建人
		parmMap.put("creator", 1);
		// 调用dao层插入数据
		try {
			amsArchivesAuditInDao.addAmsArchivesAuditIns(parmMap);
		} catch (Exception e) {
			e.printStackTrace();
			return "请求失败";
		}
		// 处理消息,推送到所有在线领导用户，保存到接收表中关联所有领导用户
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setContent("大厅工作人员为" + parmMap.get("applyUser") + "发起了" + amsList.size() + "份"
				+ (parmMap.get("type").equals("0") ? "存档" : "取档") + "申请");
		messageInfo.setCtime(new Date());
		messageInfo.setIsDel(0);
		messageInfo.setIsPush(1);
		messageInfo.setType(3);
		messageInfo.setTitle((parmMap.get("type").equals("0") ? "存档" : "取档") + "申请");
		// 根据档案判断需要推送给哪些用户
		Map<String, Object> paraMaps = new HashMap<String, Object>();
		List<Integer> amss = new ArrayList<Integer>();
		for (int i = 0; i < archivesIds.length; i++) {
			amss.add(Integer.parseInt(archivesIds[i]));
		}
		amss.add(-1);
		paraMaps.put("amsIds", amss);
		List<Map<String, Object>> userList = wmsUserService.selectUserByAms(paraMaps);// 得到用户集合
		List<Integer> userIdList = new ArrayList<Integer>();// 用户id集合
		if (userList != null && userList.size() > 0) {
			for (Map<String, Object> lss : userList) {
				if (String.valueOf(lss.get("userType")).equals("1")) {// 发起申请推送给领导者 // 用户类型：0.系统管理员 1、领导者 2、操作者
					userIdList.add(Integer.parseInt(String.valueOf(lss.get("userId"))));// 得到用户id集合
				}
			}
		}
		MsgServiceUtil.messageService(messageInfo, userIdList);// 发起申请后推送
		try {
			// 添加申请后改变档案表对应档案的状态，防止资源冲突
			/*
			 * 3.1之前的逻辑 amsArchivesDao.updateAmsArchivesStatus(parmMaps);
			 */
			// 变更档案数量
			if (parmMap.get("type").equals("1")) {// 取档档案数量减一
				parmMaps.put("type", 1);
				amsArchivesDao.updateAmsNowNumber(parmMaps);
			} else if (parmMap.get("type").equals("0")) {// 存档档案数量加一
				parmMaps.put("type", 0);
				amsArchivesDao.updateAmsNowNumber(parmMaps);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "请求失败";
		}
		return null;
	}

	/**
     * 档案出入库信息获取
     * 
     * @param parmMap
     * @return
     */
	public List<Map<String, String>> getAmsArchiveAuditIn() {
		List<Map<String, String>> list=amsArchivesAuditInDao.getAmsArchiveAuditIn();
		return list;
	}

	@Override
	public Map<String, String> getAmsArchiveAuditInCount() {
		Map<String, String> count=amsArchivesAuditInDao.getAmsArchiveAuditInCount();
		return count;
	}
    /**
     * 档案出入库信息统计
     * 
     * @param parmMap
     * @return
     */
    public List<Map<String, String>> getArchivesAuditInCount(){
    	List<Map<String, String>> count=amsArchivesAuditInDao.getArchivesAuditInCount();
    	
		return count;
    }

}
