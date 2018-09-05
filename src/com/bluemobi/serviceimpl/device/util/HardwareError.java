package com.bluemobi.serviceimpl.device.util;

/**
 * <p>
 * Title: 硬件错误信息类
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company: BAYI
 * </p>
 *
 * @author jianghaidong
 * @version 1.0
 * @date 2014-10-17
 */
public class HardwareError {
	
	public static final String SUCCESS = "1001";
	public static final String S_SUCCESS = "成功";
	
	
	public static final String ERR_NULL = "1002";
	public static final String S_ERR_NULL = "参数为空！";
	
	public static final String ERR_IO = "1003";
	public static final String S_ERR_IO = "输入输出错误";
	
	public static final String ERR_EXCEED = "1004";
	public static final String S_ERR_EXCEED = "参数值越界！";
	
	public static final String ERR_OBJ_NULL = "1005";
	public static final String S_ERR_OBJ_NULL = "对象为空！";
	
	public static final String ERR_PARAM_NULL = "1006";
	public static final String S_ERR_PARAM_NULL = "参数为空！";
	
	public static final String ERR_PARAM_CONFLICTS = "1007";
	public static final String S_ERR_PARAM_CONFLICTS = "参数冲突！";
	
	//以下为Modbus错误信息类
	public static final String ERR_INIT = "1201";
	public static final String S_ERR_INIT = "MODBUS初始化出错！";
	//public static final String ERR_TRANSPORT = "1203";
	
	public static final String ERR_FUNCTIONCODE = "1208";	
	public static final String S_ERR_FUNCTIONCODE = "FunctionCode 必须在1 - 4之间！";
	
	public static final String ERR_READ_REGISTERS = "1209";	
	public static final String S_ERR_READ_REGISTERS = "读寄存器信息出错！";
	
	public static final String ERR_CONCHANNEL = "1210";	
	public static final String S_ERR_CONCHANNEL = "连接通道错误！";	
	public static final String ERR_OPEN = "1211";	
	public static final String S_ERR_OPEN = "执行打开功能出错！";
	public static final String ERR_CLOSE = "1212";	
	public static final String S_ERR_CLOSE = "执行关闭功能出错！";	
	public static final String ERR_STOP = "1213";	
	public static final String S_ERR_STOP = "执行停止/取消功能出错！";
	public static final String ERR_VENT = "1214";	
	public static final String S_ERR_VENT = "执行通风功能出错！";
	
	public static final String ERR_RESUME = "1215";	
	public static final String S_ERR_RESUME = "执行恢复功能出错！";
	public static final String ERR_PAUSE = "1216";	
	public static final String S_ERR_PAUSE = "执行暂停功能出错！";
	
	public static final String ERR_STRAT = "1217";	
	public static final String S_ERR_STRAT = "执行启动功能出错！";
	public static final String ERR_REMOVE = "1218";	
	public static final String S_ERR_REMOVE = "执行删除功能出错！";
	
	public static final String ERR_MODIFY = "1219";	
	public static final String S_ERR_MODIFY = "执行修改功能出错！";
	
	
	
	public static final String ERR_RESUME_SCHEDULER = "1250";	
	public static final String S_ERR_RESUME_SCHEDULER = "启动调度功能出错！";
	
	public static final String ERR_STOPSCHEDULER = "1251";	
	public static final String S_ERR_STOPSCHEDULER = "停止调度功能出错！";
	
	public static final String ERR_SHUTDOWN_SCHEDULER = "1252";	
	public static final String S_ERR_SHUTDOWN_SCHEDULER = "关闭调度功能出错！";
	
	//以下为Socket错误信息类
	public static final String ERR_UNKNOWN_HOST = "1100";
	public static final String S_ERR_UNKNOWN_HOST = "未知服务器地址";
	
	public static final String ERR_SOCKET = "1101";
	public static final String S_ERR_SOCKET = "SOCKET连接错误";
	
	
	//以下为HTTP错误信息类
	public static final String ERR_UNKNOWN = "8000";
	public static final String S_ERR_UNKNOWN = "未知错误";

}
