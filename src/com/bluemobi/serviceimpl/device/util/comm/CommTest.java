package com.bluemobi.serviceimpl.device.util.comm;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 基类
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
 * @date 2014-11-5
 */
public class CommTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<CommPortIdentifier> list = SerialReaderTest.getPorts();
		for (CommPortIdentifier port : list) {
			System.out.println(port.getName());
		}
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(SerialReaderTest.PARAMS_PORT, "COM3"); // 端口名称
		params.put(SerialReaderTest.PARAMS_RATE, 9600); // 波特率
		params.put(SerialReaderTest.PARAMS_TIMEOUT, 1000); // 设备超时时间 1秒
		params.put(SerialReaderTest.PARAMS_DELAY, 200); // 端口数据准备时间 1秒
		params.put(SerialReaderTest.PARAMS_DATABITS, SerialPort.DATABITS_8); // 数据位
		params.put(SerialReaderTest.PARAMS_STOPBITS, SerialPort.STOPBITS_1); // 停止位
		params.put(SerialReaderTest.PARAMS_PARITY, SerialPort.PARITY_NONE); // 无奇偶校验
		SerialReaderTest sr = new SerialReaderTest(params);
		//CommDataObserver bob = new CommDataObserver("bob");
		CommDataObserver joe = new CommDataObserver("joe");
		sr.addObserver(joe);
		//sr.addObserver(bob);

	}

}
