package com.bluemobi.serviceimpl.device.util.comm;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.TooManyListenersException;

import org.apache.log4j.Logger;

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
public class SerialReaderTest extends Observable implements Runnable, SerialPortEventListener {
	
	Logger logger = Logger.getLogger(getClass());

	static CommPortIdentifier portId;
	int delayRead = 200;
	int numBytes; // buffer中的实际数据字节数
	private static byte[] readBuffer = new byte[4096]; // 4k的buffer空间,缓存串口读入的数据
	@SuppressWarnings("rawtypes")
	static Enumeration portList;
	InputStream inputStream;
	SerialPort serialPort;
	HashMap<String, Object> serialParams;

	// 端口读入数据事件触发后,等待n毫秒后再读取,以便让数据一次性读完
	public static final String PARAMS_DELAY = "delay read"; // 延时等待端口数据准备的时间
	public static final String PARAMS_TIMEOUT = "timeout"; // 超时时间
	public static final String PARAMS_PORT = "port name"; // 端口名称
	public static final String PARAMS_DATABITS = "data bits"; // 数据位
	public static final String PARAMS_STOPBITS = "stop bits"; // 停止位
	public static final String PARAMS_PARITY = "parity"; // 奇偶校验
	public static final String PARAMS_RATE = "rate"; // 波特率

	public SerialReaderTest(){
		if (serialParams==null)
			serialParams = new HashMap<String, Object>();
		serialParams.put(SerialReaderTest.PARAMS_PORT, "COM1"); // 端口名称
		serialParams.put(SerialReaderTest.PARAMS_RATE, 9600); // 波特率
		serialParams.put(SerialReaderTest.PARAMS_TIMEOUT, 1000); // 设备超时时间 1秒
		serialParams.put(SerialReaderTest.PARAMS_DELAY, 200); // 端口数据准备时间 1秒
		serialParams.put(SerialReaderTest.PARAMS_DATABITS, SerialPort.DATABITS_8); // 数据位
		serialParams.put(SerialReaderTest.PARAMS_STOPBITS, SerialPort.STOPBITS_1); // 停止位
		serialParams.put(SerialReaderTest.PARAMS_PARITY, SerialPort.PARITY_NONE); // 无奇偶校验
	}
	
	public SerialReaderTest(String portName, int rate){
		if (serialParams==null)
			serialParams = new HashMap<String, Object>();
		serialParams.put(SerialReaderTest.PARAMS_PORT, portName); // 端口名称
		serialParams.put(SerialReaderTest.PARAMS_RATE, rate); // 波特率
	}
	
	/**
	 * 初始化端口操作的参数.
	 * 
	 * 
	 * @see
	 */
	public SerialReaderTest(HashMap<String, Object> params) {
		serialParams = params;
		connect();
	}

	public void connect() {
		String portName = "";
		try {
			// 参数初始化
			int timeout = Integer.parseInt(serialParams.get(PARAMS_TIMEOUT).toString());
			int rate = Integer.parseInt(serialParams.get(PARAMS_RATE).toString());
			int dataBits = Integer.parseInt(serialParams.get(PARAMS_DATABITS).toString());
			int stopBits = Integer.parseInt(serialParams.get(PARAMS_STOPBITS).toString());
			int parity = Integer.parseInt(serialParams.get(PARAMS_PARITY).toString());
			delayRead = Integer.parseInt(serialParams.get(PARAMS_DELAY).toString());
			portName = serialParams.get(PARAMS_PORT).toString();
			// 打开端口
			portId = CommPortIdentifier.getPortIdentifier(portName);
			serialPort = (SerialPort) portId.open(this.getClass().getName(), timeout);
			inputStream = serialPort.getInputStream();
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			serialPort.setSerialPortParams(rate, dataBits, stopBits, parity);
		} catch (TooManyListenersException e) {
			String errorMsg = "系统异常: 端口监听者过多";
			logger.error(errorMsg, e);
		} catch (NoSuchPortException e) {
			String errorMsg = "系统异常|SYSRROR: 没有检测到串口|NOT FOUND SERIAL[" + portName + "]";
			logger.error(errorMsg, e);
		} catch (PortInUseException e) {
			String errorMsg = "系统异常: 串口[" + portName + "]被占用";
			logger.error(errorMsg, e);
		} catch (UnsupportedCommOperationException e) {
			String errorMsg = "系统异常: 串口操作命令不支持";
			logger.error(errorMsg, e);
		} catch (IOException e) {
			String errorMsg = "系统异常: 串口打开异常";
			logger.error(errorMsg, e);
		}
		Thread readThread = new Thread(this);
		readThread.start();
	}
	
	public void close(){
		
		serialPort.close();
	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @see
	 */
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Method declaration
	 * 
	 * 
	 * @param event
	 * 
	 * @see
	 */
	public void serialEvent(SerialPortEvent event) {
		try {
			// 等待1秒钟让串口把数据全部接收后在处理
			Thread.sleep(delayRead);
			System.out.print("serialEvent[" + event.getEventType() + "]    ");
		} catch (InterruptedException e) {
			logger.error("",e);
		}
		switch (event.getEventType()) {
		case SerialPortEvent.BI: // 10
		case SerialPortEvent.OE: // 7
		case SerialPortEvent.FE: // 9
		case SerialPortEvent.PE: // 8
		case SerialPortEvent.CD: // 6
		case SerialPortEvent.CTS: // 3
		case SerialPortEvent.DSR: // 4
		case SerialPortEvent.RI: // 5
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2
			break;
		case SerialPortEvent.DATA_AVAILABLE: // 1
			try {
				// 多次读取,将所有数据读入
				// while (inputStream.available() > 0) {
				// numBytes = inputStream.read(readBuffer);
				// }
				numBytes = inputStream.read(readBuffer);
				changeMessage(readBuffer, numBytes);
			} catch (IOException e) {
				logger.error("",e);
			}
			break;
		}
	}

	// 通过observer pattern将收到的数据发送给observer
	// 将buffer中的空字节删除后再发送更新消息,通知观察者
	public void changeMessage(byte[] message, int length) {
		setChanged();
		byte[] temp = new byte[length];
		System.arraycopy(message, 0, temp, 0, length);
		// System.out.println("msg[" + numBytes + "]: [" + new String(temp) +
		// "]");
		notifyObservers(temp);
	}

	@SuppressWarnings("rawtypes")
	public static List<CommPortIdentifier> getPorts() {
		List<CommPortIdentifier> list = new ArrayList<CommPortIdentifier>();
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier portIdentifier = (CommPortIdentifier) portEnum.nextElement();
			list.add(portIdentifier);
			System.out.println(portIdentifier.getName() + " - "
					+ getPortTypeName(portIdentifier.getPortType()));
		}
		return list;
	}
	
	public static CommPortIdentifier getPort(String portName) {
//		if ( StringUtil.isEmpty(portName))
//			return null;
		List<CommPortIdentifier> list = getPorts();		
		for (CommPortIdentifier port : list) {
			if (portName.equalsIgnoreCase(port.getName())) {
				return port;
			}
		}
		return null;
	}

	public static String getPortTypeName(int portType) {
		switch (portType) {
		case CommPortIdentifier.PORT_I2C:
			return "I2C";
		case CommPortIdentifier.PORT_PARALLEL:
			return "Parallel";
		case CommPortIdentifier.PORT_RAW:
			return "Raw";
		case CommPortIdentifier.PORT_RS485:
			return "RS485";
		case CommPortIdentifier.PORT_SERIAL:
			return "Serial";
		default:
			return "unknown type";
		}
	}

	/**
	 * @return A HashSet containing the CommPortIdentifier for all serial ports
	 *         that are not currently being used.
	 */
	@SuppressWarnings("rawtypes")
	public static HashSet<CommPortIdentifier> getAvailableSerialPorts() {
		HashSet<CommPortIdentifier> h = new HashSet<CommPortIdentifier>();
		Enumeration thePorts = CommPortIdentifier.getPortIdentifiers();
		while (thePorts.hasMoreElements()) {
			CommPortIdentifier com = (CommPortIdentifier) thePorts.nextElement();
			switch (com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				try {
					CommPort thePort = com.open("CommUtil", 50);
					thePort.close();
					h.add(com);
				} catch (PortInUseException e) {
					System.out.println("Port, " + com.getName() + ", is in use.");
				} catch (Exception e) {
					System.out.println("Failed to open port " + com.getName() + e);
				}
			}
		}
		return h;
	}

}
