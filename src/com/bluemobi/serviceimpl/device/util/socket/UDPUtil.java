package com.bluemobi.serviceimpl.device.util.socket;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.bluemobi.serviceimpl.device.util.HardwareError;
import com.bluemobi.serviceimpl.device.util.HardwareResult;

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
 * @date 2014-10-17
 */
public class UDPUtil {

	private static Logger log = Logger.getLogger(UDPUtil.class);

	public static final int TIMEOUT = 5 * 1000;
	public static final int MAXTRIES = 5;

	public static HardwareResult read(String host, int port) {
		HardwareResult hr = new HardwareResult();
		StringBuffer sb = new StringBuffer();
		// 服务器地址
		DatagramSocket dgsocket = null;
		try {
			byte[] bRcv = new byte[256];
			// UDP客户端
			dgsocket = new DatagramSocket();			
			// 接收数据阻塞时间
			dgsocket.setSoTimeout(TIMEOUT);			
			// 接收的数据报包
			DatagramPacket receiverPacket = new DatagramPacket(bRcv, bRcv.length);			
			// 尝试接收次数
			int tries = 0;
			boolean received = false;
			do {
				try {
					log.info("Received response data from server...");
					// 从服务器端接收数据
					dgsocket.receive(receiverPacket);					
					received = true;					
				} catch (InterruptedIOException e) {
					tries++;
					log.info("Timed out," + (MAXTRIES - tries) + " more ties");
				}
			} while (!received && tries < 5);
			if (received){
				hr = new HardwareResult(HardwareError.SUCCESS, HardwareError.S_SUCCESS);
				hr.setResponse(sb.toString());			
			}else{
				hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
				hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			}			
		} catch (UnknownHostException uhe) {
			hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
			hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			log.error("",uhe);
		} catch (SocketException se) {
			hr.setResultCode(HardwareError.ERR_SOCKET);
			hr.setErrorInfo(HardwareError.S_ERR_SOCKET);
			log.error("",se);
		} catch (IOException ioe) {
			hr.setResultCode(HardwareError.ERR_IO);
			hr.setErrorInfo(HardwareError.S_ERR_IO);
			log.error("",ioe);
		} finally {
			if (dgsocket!=null)
				dgsocket.close();
		}		
		return hr;
	}
	
	public static HardwareResult write(String host, int port, String command) {
		HardwareResult hr = new HardwareResult();
		StringBuffer sb = new StringBuffer();
		// 服务器地址
		InetAddress serverAddress = null;
		DatagramSocket socket = null;
		try {
			serverAddress = InetAddress.getByAddress(host.getBytes());
			byte[] byteToSend = command.getBytes();
			// UDP客户端
			socket = new DatagramSocket();
			
			// 接收数据阻塞时间
			socket.setSoTimeout(TIMEOUT);
			// 发送数据报包
			DatagramPacket sendPacket = new DatagramPacket(byteToSend, byteToSend.length,
					serverAddress, port);
			// 接收的数据报包
			DatagramPacket receiverPacket = new DatagramPacket(new byte[byteToSend.length],
					byteToSend.length);
			
			// 尝试接收次数
			int tries = 0;
			boolean received = false;
			do {
				// 向服务器端发送数据
				socket.send(sendPacket);
				try {
//					log.info("Received response data from server...");
					// 从服务器端接收数据
					socket.receive(receiverPacket);
					if (!receiverPacket.getAddress().equals(serverAddress)) {
						throw new IOException("Received packet from an unknown source");
					}
					received = true;
					
				} catch (InterruptedIOException e) {
					tries++;
//					log.info("Timed out," + (MAXTRIES - tries) + " more ties");
				}

			} while (!received && tries < MAXTRIES);
			
			if (received){
				hr = new HardwareResult(HardwareError.SUCCESS, HardwareError.S_SUCCESS);
				hr.setResponse(sb.toString());			
			}else{
				hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
				hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			}			
		} catch (UnknownHostException uhe) {
			hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
			hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			log.error("",uhe);
		} catch (SocketException se) {
			hr.setResultCode(HardwareError.ERR_SOCKET);
			hr.setErrorInfo(HardwareError.S_ERR_SOCKET);
			log.error("",se);
		} catch (IOException ioe) {
			hr.setResultCode(HardwareError.ERR_IO);
			hr.setErrorInfo(HardwareError.S_ERR_IO);
			log.error("",ioe);
		} finally {
			if (socket!=null)
				socket.close();
		}		
		return hr;
	}

	public static void main(String[] args) {
		HardwareResult hr = UDPUtil.read("192.168.1.100", 8080);
		System.out.println(hr.getResponse());
	}
	
}
