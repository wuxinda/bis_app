package com.bluemobi.task;

import org.apache.log4j.Logger;

import com.bluemobi.conf.Config;
import com.bluemobi.serviceimpl.device.dp.DeviceDpManage;
import com.bluemobi.serviceimpl.device.dp.base.BuilderFactory;
import com.bluemobi.serviceimpl.device.dp.base.BuilderHcsDp;
import com.bluemobi.serviceimpl.message.push.AlarmPush;
import com.bluemobi.serviceimpl.message.push.BuilderPush;
import com.bluemobi.serviceimpl.message.push.PushManage;

/**
 * @author Riven 系统各类定时任务处理
 */
public class taskManage {
    // 日志输出对象
    private static Logger log = Logger.getLogger(taskManage.class);
    // quartz 是否开启标示
    public static final String quartzIsStart = Config.QUARTZ_IS_START;

    /**
     * 获取设备温湿度
     */
    public void handleHcsTask() {
	log.info("**********获取温湿度任务-begin**********");

	try {
	    // 是否开启任务标示
	    if (quartzIsStart.equals("1")) {
		log.info("**********获取温湿度线程开启**********");
		new Thread() {
		    @Override
		    public synchronized void start() {
			DeviceDpManage manage = new DeviceDpManage(new BuilderHcsDp(BuilderFactory.getBayiHcsDp()));
			manage.startBuildPart();
		    }
		}.start();
	    }
	} catch (Exception e) {
	    // 出错日志
	    log.error(e.getLocalizedMessage());
	}
	log.info("**********获取温湿度任务-end**********");
    }

    /**
     * 报警推送任务
     */
    public void handleAlmPushTask() {
	log.info("**********报警推送任务-begin**********");
	try {
	    // 是否开启任务标示
	    if (quartzIsStart.equals("1")) {
		log.info("**********报警推送线程开启**********");
		new Thread() {
		    @Override
		    public synchronized void start() {
			PushManage manage = new PushManage(new BuilderPush(new AlarmPush()));
			manage.startBuildPart();
		    }
		}.start();
	    }
	} catch (Exception e) {
	    // 出错日志
	    log.error(e.getLocalizedMessage());
	}
	log.info("**********报警推送任务-end**********");
    }
}
