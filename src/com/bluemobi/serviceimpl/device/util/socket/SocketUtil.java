package com.bluemobi.serviceimpl.device.util.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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
public class SocketUtil {

	Logger log = Logger.getLogger(this.getClass());

	public HardwareResult read(String host, int port, String command) {
		HardwareResult hr = new HardwareResult();
		StringBuffer sb = new StringBuffer();		
		Writer writer = null;
		Reader reader = null;
		Socket socket = null;
//		Socket socket = new Socket();
//		SocketAddress address = new InetSocketAddress(host, port);
		try {
//			socket.connect(address, 3*1000);
			socket = new Socket(host,port);
			socket.setSoTimeout(1000);
			// 建立连接后就可以往服务端写数据了
			writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write(command);
			writer.flush();
			// 写完以后进行读操作
			reader = new InputStreamReader(socket.getInputStream());
			char chars[] = new char[1024];
			int len;
			while ((len = reader.read(chars)) != -1) {
				sb.append(new String(chars, 0, len));
			}
		} catch (UnknownHostException e) {
			hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
			hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			log.error("灯光接口",e);
		} catch (IOException e) {
			hr.setResultCode(HardwareError.ERR_IO);
			hr.setErrorInfo(HardwareError.S_ERR_IO);
			log.error("灯光接口",e);
		} finally {
			hr = new HardwareResult(HardwareError.SUCCESS, HardwareError.S_SUCCESS);
			hr.setResponse(sb.toString());
			log.info("SEND..." + command);
			log.info("RECEIVE..." + sb.toString());
			
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e1) {
				log.error("灯光接口",e1);
			}
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				log.error("灯光接口",e1);
			}

			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				// hr = new HardwareResult(HardwareError.ERR_IO,
				// HardwareError.ERR_IO_STR);
				log.error("",e);
			}
		}
		return hr;
	}
	
	public HardwareResult read2(String host, int port, String command) {
		HardwareResult hr = new HardwareResult();
		StringBuffer sb = new StringBuffer();		
		Writer writer = null;
		Reader reader = null;
//		Socket socket = null;
		Socket socket = new Socket();
		SocketAddress address = new InetSocketAddress(host, port);
		try {
			socket.connect(address, 5*1000);
//			socket = new Socket(host,port);
			socket.setSoTimeout(600);
			// 建立连接后就可以往服务端写数据了
			writer = new OutputStreamWriter(socket.getOutputStream());
			writer.write(command);
			writer.flush();
			// 写完以后进行读操作
			reader = new InputStreamReader(socket.getInputStream());
			char chars[] = new char[64];
//			sb.append(new String(chars, 0, reader.read(chars)));
			try {
				int len;  
				while ((len=reader.read(chars)) != -1) {  
					sb.append(new String(chars, 0, len)); 
				}
			} catch (Exception e) {
				log.error("灯光接口",e);
			}
			hr = new HardwareResult(HardwareError.SUCCESS, HardwareError.S_SUCCESS);
			hr.setSuccess(true);
		} catch (UnknownHostException e) {
			hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
			hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			hr.setSuccess(false);
			log.error("灯光接口",e);
		} catch (IOException e) {
			hr.setSuccess(false);
			hr.setResultCode(HardwareError.ERR_IO);
			hr.setErrorInfo(HardwareError.S_ERR_IO);
			log.error("灯光接口",e);
		} 
		finally {
			
			hr.setResponse(sb.toString());
			log.info("SEND..." + command);
			log.info("RECEIVE..." + sb.toString());
			
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e1) {
				log.error("灯光接口",e1);
			}
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
				log.error("灯光接口",e1);
			}

			try {
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				// hr = new HardwareResult(HardwareError.ERR_IO,
				// HardwareError.ERR_IO_STR);
				log.error("灯光接口",e);
			}
		}
		return hr;
	}

	public HardwareResult readEx(String host, int port, String command){
		HardwareResult hr = new HardwareResult();
		
		SocketChannel channel = null;
		try {
			//打开监听信道并设置为非阻塞模式
			channel = SocketChannel.open( new InetSocketAddress(host, port) );
			channel.configureBlocking(false);
			
			ByteBuffer writeBuf = ByteBuffer.wrap(command.getBytes());
			while (!channel.finishConnect()) {
				try {
					Thread.sleep(10);
				}catch(InterruptedException e){
					log.error("灯光接口",e);
				}
			}
			writeBuf.flip();
			channel.write(writeBuf);
			writeBuf.clear();
			channel.socket().shutdownOutput();
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ByteBuffer readBuf = ByteBuffer.allocate(1024);
			byte[] bytes;
			int byteRcv = 0;
			while ( (byteRcv = channel.read(readBuf)) != -1 ) {
				readBuf.flip();
				bytes = new byte[byteRcv];
				readBuf.get(bytes);
				baos.write(bytes);
				readBuf.clear();				
			}
			String response = new String(baos.toByteArray(), "UTF-8");
			channel.socket().shutdownInput();
			hr = new HardwareResult(HardwareError.SUCCESS, HardwareError.S_SUCCESS);
			hr.setResponse(response);
		} catch (UnknownHostException e) {
			hr.setResultCode(HardwareError.ERR_UNKNOWN_HOST);
			hr.setErrorInfo(HardwareError.S_ERR_UNKNOWN_HOST);
			log.error("灯光接口",e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("",e);
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				log.error("灯光接口",e);
			}
		}
		
		return hr;
	}
	
}
