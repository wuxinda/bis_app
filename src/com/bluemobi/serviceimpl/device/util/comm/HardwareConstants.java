package com.bluemobi.serviceimpl.device.util.comm;

public class HardwareConstants {
	
	public static final int MAX_HCS_CLIENT = 15; //温湿度从设备数量
	
	public static final String HCS = "HCS"; //温湿度
	public static final String DMS = "DMS"; //光盘
	public static final String FCS = "FCS"; //消防
	public static final String ILS = "ILS"; //灯光
	public static final String VMS = "VMS"; //视频
	public static final String ACS = "ACS"; //门禁
	public static final String BGS = "BGS"; //广播
	public static final String FAS = "FAS"; //消毒
	public static final String SZS = "SZS"; //新风
	public static final String WLD = "WLD"; //漏水
	public static final String AIR = "AIR"; //空调
	public static final String IMS = "IMS"; //密集架
	public static final String OPEN = "OPEN"; //开
	public static final String CLOSE = "CLOSE"; //关
	
	//触发设备事件
	public static final String LEFTOPEN = "LEFTOPEN"; //左开
	public static final String RIGHTOPEN = "RIGHTOPEN"; //右开
	public static final String STOP = "STOP"; //停止
	public static final String VENT = "VENT"; //通风
	public static final String CLOSURE = "CLOSURE"; //合拢
	
	public static final String HUM_LOWER = "HUM_LOWER"; //温度下限
	public static final String HUM_UPPER = "HUM_UPPER"; //温度上限
	public static final String TEMP_LOWER = "TEMP_LOWER"; //湿度下限
	public static final String TEMP_UPPER = "TEMP_UPPER"; //湿度上限

}
