package com.bluemobi.serviceimpl.message.push;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.appcore.context.AppContext;
import com.bluemobi.dao.admin.AdminUserDao;
import com.bluemobi.dao.alarm.AlarmManageDao;
import com.bluemobi.dao.alarm.AlarmMessageConfigDao;
import com.bluemobi.dao.message.MessageInfoDao;
import com.bluemobi.dao.message.MessageReceiveDao;
import com.bluemobi.po.alarm.AlarmManage;
import com.bluemobi.po.alarm.AlarmMessageConfig;
import com.bluemobi.po.message.MessageInfo;
import com.bluemobi.util.MsgServiceUtil;

/**
 * 报警推送具体处理类
 * 
 * @author Riven
 * 
 */

public class AlarmPush extends AbstractPush {
    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmPush.class);
    private AlarmManageDao alarmManageDao = AppContext.getBean("alarmManageDao");
    private AlarmMessageConfigDao alarmMessageConfigDao = AppContext.getBean("alarmMessageConfigDao");
    private MessageInfoDao messageInfoDao = AppContext.getBean("messageInfoDao");
    private MessageReceiveDao messageReceiveDao = AppContext.getBean("messageReceiveDao");
    private AdminUserDao adminUserDao = AppContext.getBean("adminUserDao");
    List<AlarmManage> alarmList = null;

    /**
     * 检查是否要推送
     * 
     */
    @SuppressWarnings("deprecation")
    @Override
    public void check() {
	// 查询得到所有未处理报警记录
	Map<String, Object> parm = new HashMap<String, Object>();
	parm.put("status", 0);
	try {
	    alarmList = alarmManageDao.selectObjectList(parm);
	} catch (Exception e) {
	    LOGGER.error("-----------------获取报警列表出错----------------");
	    e.printStackTrace();
	}
	for (AlarmManage alarmManage : alarmList) {
	    Map<String, Object> parm1 = new HashMap<String, Object>();
	    parm1.put("alarmType", alarmManage.getAlarmType());
	    List<AlarmMessageConfig> alarmMessageConfigList = null;
	    try {
		alarmMessageConfigList = alarmMessageConfigDao.selectObjectList(parm1);
	    } catch (Exception e) {
		LOGGER.error("-----------------获取报警配置标准出错----------------");
		e.printStackTrace();
	    }
	    if (alarmMessageConfigList.size() > 0) {
		AlarmMessageConfig alarmMessageConfig = alarmMessageConfigList.get(0);
		if (!StringUtils.isEmpty(alarmMessageConfig.getIspush())) {
		    if (alarmMessageConfig.getIspush() == 1) {
			alarmList.remove(alarmManage);
			continue;
		    }
		}
		// 判断是否工作日推送 0.是1.否
		if (!StringUtils.isEmpty(alarmMessageConfig.getIswork())) {
		    if (alarmMessageConfig.getIswork() == 0) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date(System.currentTimeMillis()));
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			if (dayOfWeek == 0 || dayOfWeek == 6) {
			    alarmList.remove(alarmManage);
			    continue;
			}
		    }
		}

		// 判断与上次推送时间间隔是否超过设定时间
		if (!StringUtils.isEmpty(alarmMessageConfig.getIntervals())) {
		    long cha = 0;
		    if (!StringUtils.isEmpty(alarmManage.getMtime())) {
			cha = (new Date().getTime() - alarmManage.getMtime().getTime()) / (1000 * 60);
			if (cha < alarmMessageConfig.getIntervals()) {
			    alarmList.remove(alarmManage);
			    continue;
			}
		    }
		}
		// 判断是否在单日设定的时间范围内
		if (!StringUtils.isEmpty(alarmMessageConfig.getStarpush()) && !StringUtils.isEmpty(alarmMessageConfig.getEndpush())) {
		    if (alarmMessageConfig.getStarpush() < alarmMessageConfig.getEndpush()) {
			if (new Date().getHours() > alarmMessageConfig.getEndpush() || new Date().getHours() < alarmMessageConfig.getStarpush()) {
			    alarmList.remove(alarmManage);
			    continue;
			}
		    }
		    if (alarmMessageConfig.getStarpush() > alarmMessageConfig.getEndpush()) {
			if (new Date().getHours() > alarmMessageConfig.getEndpush() && new Date().getHours() < alarmMessageConfig.getStarpush()) {
			    alarmList.remove(alarmManage);
			    continue;
			}
		    }
		}
	    }
	}
    }

    /**
     * 具体推送
     */
    @Override
    public void push() {
	if (alarmList.size() > 0) {
	    for (AlarmManage alarmManage : alarmList) {
		MessageInfo messageInfo = new MessageInfo();
		messageInfo.setContent(alarmManage.getDeviceName() + alarmManage.getRemark());
		messageInfo.setCtime(new Date());
		messageInfo.setIsDel(0);
		messageInfo.setIsPush(1);
		messageInfo.setTitle("报警消息");
		messageInfo.setType(2);
		try {
		    messageInfoDao.insert(messageInfo);
		} catch (Exception e) {
		    LOGGER.error("-----------------插入消息出错----------------");
		    e.printStackTrace();
		}
		// 如果需要推送就推送给所有在线用户
		if (messageInfo.getIsPush().toString().equals("1")) {
		    Map<String, Object> result = new HashMap<String, Object>();
		    result.put("type", messageInfo.getType());
		    result.put("msg", "您有一条报警消息：" + messageInfo.getContent());
		    Map<String, Object> parm2 = new HashMap<String, Object>();
		    parm2.put("status", alarmManage.getAlarmType());
		    List<AlarmMessageConfig> alarmMessageConfigList = null;
		    try {
			alarmMessageConfigList = alarmMessageConfigDao.selectObjectList(parm2);
		    } catch (Exception e) {
			LOGGER.error("-----------------获取报警配置标准出错----------------");
			e.printStackTrace();
		    }
		    AlarmMessageConfig alarmMessageConfig = alarmMessageConfigList.get(0);
		    List<Integer> userIds = new ArrayList<Integer>();
		    try {
			if (alarmMessageConfigList.size() > 0 && !StringUtils.isEmpty(alarmMessageConfig.getUserType())) {
			    Map<String, Object> parmUs = new HashMap<String, Object>();
			    parmUs.put("userType", alarmMessageConfig.getUserType());
			    if(alarmMessageConfig.getUserType()==0){
				parmUs.put("userType", null);
			    }
			    List<Map<String, Object>> UserList = adminUserDao.selectMapList(parmUs);
			    for (Map<String, Object> list : UserList) {
				userIds.add((Integer.parseInt(String.valueOf(list.get("userId")))));
			    }
			    MsgServiceUtil.sendPushByUser(result.toString(), userIds);
			} else {
			    List<Map<String, Object>> UserList = adminUserDao.selectMapList(null);
			    for (Map<String, Object> list : UserList) {
				userIds.add((Integer.parseInt(String.valueOf(list.get("userId")))));
			    }
			    MsgServiceUtil.sendPush(result.toString());
			}
		    } catch (NumberFormatException e) {
			LOGGER.error("-----------------获取用户出错----------------");
			e.printStackTrace();
		    }
		    Map<String, Object> parmUp = new HashMap<String, Object>();
		    parmUp.put("messageId", messageInfo.getMessageId());
		    parmUp.put("isDel", 0);
		    parmUp.put("status", 0);
		    parmUp.put("mtime", new Date());
		    parmUp.put("userId", userIds);
		    try {
			if (userIds.size() > 0) {
			    messageReceiveDao.addMessageReceives(parmUp);
			}
			alarmManage.setMtime(new Date());
			alarmManageDao.update(alarmManage);
		    } catch (Exception e) {
			LOGGER.error("-----------------插入发送消息或者更新报警信息出错----------------");
			e.printStackTrace();
		    }
		}
	    }
	}
    }
}
