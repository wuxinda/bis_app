package com.bluemobi.serviceimpl.device.util.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

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
 * @date 2014-10-28
 */
public class SocketChannelThread implements Runnable {
	Logger log = Logger.getLogger(this.getClass());
	private Selector selector;
	
	private String receiveData;

	public SocketChannelThread(Selector selector) {
		this.selector = selector;
		new Thread(this).start();
	}

	public void run() {
		try {
			while (selector.select() > 0) {
				// 遍历每个有可用IO操作Channel对应的SelectionKey
				for (SelectionKey sk : selector.selectedKeys()) {
					// 如果该SelectionKey对应的Channel中有可读的数据
					if (sk.isReadable()) {
						// 使用NIO读取Channel中的数据
						SocketChannel sc = (SocketChannel) sk.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						sc.read(buffer);
						buffer.flip();
						// 将字节转化为为UTF-8的字符串
						String receivedString = Charset.forName("UTF-8").newDecoder()
								.decode(buffer).toString();

						// 控制台打印出来
						System.out.println("接收到来自服务器" + sc.socket().getRemoteSocketAddress()
								+ "的信息:" + receivedString);
						
						setReceiveData(receivedString);
						// 为下一次读取作准备
						sk.interestOps(SelectionKey.OP_READ);
					}
					// 删除正在处理的SelectionKey
					selector.selectedKeys().remove(sk);
				}
			}
		} catch (IOException ex) {
			log.error("",ex);
		}
	}

	public String getReceiveData() {
		return receiveData;
	}

	public void setReceiveData(String receiveData) {
		this.receiveData = receiveData;
	}	
}
