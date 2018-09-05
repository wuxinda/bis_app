package com.bluemobi.serviceimpl.device.light.common;

import org.apache.log4j.Logger;

import com.bluemobi.serviceimpl.device.util.HardwareResult;
import com.bluemobi.serviceimpl.device.util.socket.SocketUtil;

/**
 * <p>
 * Title: 灯光操作 LRMS V1.1
 * </p>
 * <p>
 * Descrhosttion:
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
 * @date 2014-10-20
 */
public class LRMS {
	
	Logger log = Logger.getLogger(this.getClass());

	// 识别码定义符
	public static final String SYMBOL_R = "R"; // 开关识别号
	public static final String SYMBOL_D = "D"; // 调光识别号
	public static final String SYMBOL_RQ = "RQ"; // 查询区域
	public static final String SYMBOL_SCP = "SCP"; // 面板定义
	public static final String SYMBOL_SCT = "SCT"; // 时间定义
	public static final String SYMBOL_SEP = "SEP"; // 启动面板模式
	public static final String SYMBOL_SET = "SET"; // 启动时间定义模式

	/**
	 * 开关Relay ：R0101R01RE     R (识别号）01（分区号）01（模块ID号）R01（Relay 1-8 Channel ID）RE（取返）
	 * 
	 * @param symbol
	 *            ：识别号（R）
	 * @param areaId
	 *            ：分区号（01）
	 * @param modelId
	 *            ：模块号（01）
	 * @param channelId
	 *            ：渠道号（01）
	 * @param open
	 *            ：（True：RE 取返）
	 */
	public HardwareResult relay(String host, int port, String areaId, String modelId, String channelId,
			boolean open) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_R);
		sb.append(areaId);
		sb.append(modelId);
		sb.append("R").append(channelId);
		sb.append("RE");
		String command = sb.toString();

		SocketUtil su = new SocketUtil();
		log.info("发送指令:"+command);
		HardwareResult hr = su.read2(host, port, command);
		
		return hr;
	}

	/**
	 * 调光Dimmer：D0101D0ADL80   D（识别号）01（分区号）01（模块ID号）D0A（Dimmer 09—0C Channel ID）DL(DL固定识别符） 80（调光值）
	 * 
	 * @param symbol
	 * @param areaId
	 * @param modelId
	 * @param channelId（09-0C）
	 * @param dimValue
	 */
	public HardwareResult dimmer(String host, int port, String areaId, String modelId, String channelId,
			int dimValue) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_D);
		sb.append(areaId);
		sb.append(modelId);
		sb.append("D").append(channelId);
		sb.append("DL");
		sb.append(dimValue);
		String command = sb.toString();
		log.info("发送指令:"+command);
		SocketUtil su = new SocketUtil();
		HardwareResult hr = su.read2(host, port, command);
		
		return hr;
	}

	/**
	 * 查询区域所有状态值：RQ02   RQ(识别号) 02 （分区号） 注意：分区号 01-32（十进制）
	 * 
	 * @param symbol
	 * @param areaId	分区号 01-32（十进制）
	 */
	public HardwareResult query(String host, int port, String areaId) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_RQ);
		sb.append(areaId);
		String command = sb.toString();
		SocketUtil su = new SocketUtil();
		log.info("发送指令:"+command);
		HardwareResult hr = su.read2(host, port, command);
		
		return hr;
	}

	/**
	 * 面板模式定义：SCP（识别符）01（分区号）01（面板模式号）01（分区模块号）
	 * 	00N01N11（Relay状态为：0/1 N为占位符）10203040（Dimmer09值为10，Dimmer0A值为20，16个模块如始类推。
     *  每模式共295个字节，前7个位：SCP（识别符）01（分区号）01（面板模式号） 后面288个字节为执行数据。(每分区最大模式为 16)
	 * 
	 * @param symbol
	 * @param areaId
	 */
	public HardwareResult scp(String host, int port, String areaId, String modelId, String data) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_SCP);
		sb.append(areaId);
		sb.append(modelId);
		sb.append(data);
		String command = sb.toString();
		SocketUtil su = new SocketUtil();
		HardwareResult hr = su.read2(host, port, command);
		return hr;
	}

	/**
	 * 时间定义模式：SCT（识别符）01（分区号）01（模式号）1000001（星期定义）120000（时间）01（分区模块号）
	 * 	00N01N11（Relay状态为：0/1 N为占位符）10NN3040（Dimmer09值为10，Dimmer0A值NN为占位符)，16个模块如始类推。
     *	每模式共308个字节，前7个位：SCP（识别符）01（分区号）01（模式号）1000001（星期定义）120000（时间） 
     *  后面288个字节为执行数据。(每分区最大模式数为 16)
	 * 
	 * @param symbol
	 * @param areaId
	 * @param modelId
	 * @param data
	 */
	public HardwareResult sct(String host, int port, String areaId, String modelId, String week, String time,
			String data) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_SCT);
		sb.append(areaId);
		sb.append(modelId);
		sb.append(week);
		sb.append(time);
		sb.append(data);
		String command = sb.toString();
		SocketUtil su = new SocketUtil();
		return su.read2(host, port, command);
	}

	/**
	 * 启动面板模式 ： SEP0102  SEP（识别符） 01 （分区号） 02 (分区面板模式ID)
	 * 
	 * @param symbol
	 * @param areaId
	 * @param modelId
	 * 
	 * 例如：SEP0102
	 */
	public HardwareResult sep(String host, int port, String areaId, String modelId) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_SEP);
		sb.append(areaId);
		sb.append(modelId);
		String command = sb.toString();
		SocketUtil su = new SocketUtil();
		return su.read2(host, port, command);
	}

	/**
	 * 启动时间定义模式 ： SET0102  SET（识别符） 01 （分区号） 02 (时间定义模式模式ID)
	 * 
	 * @param symbol
	 * @param areaId
	 * @param modelId
	 * 
	 * 例如：SET0102
	 */
	public HardwareResult set(String host, int port, String areaId, String modelId) {
		StringBuffer sb = new StringBuffer();
		sb.append(SYMBOL_SET);
		sb.append(areaId);
		sb.append(modelId);
		String command = sb.toString();
		SocketUtil su = new SocketUtil();
		return su.read2(host, port, command);
	}
}
