package com.bluemobi.serviceimpl.device.shelve;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.log4j.Logger;

public class ShelveControlByTcp {
	private static Logger log = Logger.getLogger(ShelveControlByTcp.class);

	/************************ 打开 按钮按下弹起 *****************/
	public static byte[] OPEN_TO_ONE = { (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x05,
			(byte) 0x00, (byte) 0x07, (byte) 0xff, (byte) 0x00 };
	public static byte[] OPEN_TO_ZERO = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x05, (byte) 0x00, (byte) 0x07, (byte) 0x00, (byte) 0x00 };

	/************************ 关闭 按下弹起 *****************/
	public static byte[] CLOSE_TO_ONE = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x05, (byte) 0x00, (byte) 0x04, (byte) 0xff, (byte) 0x00 };

	public static byte[] CLOSE_TO_ZERO = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x05, (byte) 0x00, (byte) 0x04, (byte) 0x00, (byte) 0x00 };

	/************************ 停止/取消 按钮 按下弹起 *****************/
	public static byte[] STOP_TO_ONE = { (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x05,
			(byte) 0x00, (byte) 0x06, (byte) 0xFF, (byte) 0x00 };

	public static byte[] STOP_TO_ZERO = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x05, (byte) 0x00, (byte) 0x06, (byte) 0x00, (byte) 0x00 };

	/************************ 通风按钮按下弹起 *****************/
	public static byte[] WIND_TO_ONE = { (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x05,
			(byte) 0x00, (byte) 0x03, (byte) 0xff, (byte) 0x00 };

	public static byte[] WIND_TO_ZERO = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x05, (byte) 0x00, (byte) 0x03, (byte) 0x00, (byte) 0x00 };

	/************************ 通道 *****************/
	// 通道
	private static byte[] OPEN_CHANNEL_DEFAULT = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x06, (byte) 0x01, (byte) 0xc1, (byte) 0x00, (byte) 0x01 };

	/************************ 温湿度 *****************/

	private static byte[] HUMID__DEFAULT = { (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01,
			(byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x04 };

	public static byte[] channelToByteArray(int channel) {
		byte b = (byte) channel;
		byte[] bytes = OPEN_CHANNEL_DEFAULT;
		bytes[bytes.length - 1] = b;
		return bytes;
	}

	public static void open(String ip, int port, int channel)
			throws IOException, InterruptedException {
		Socket socket = new Socket(ip, port);
		OutputStream os = socket.getOutputStream();
		os.write(channelToByteArray(channel));
		Thread.sleep(200);
		os.write(OPEN_TO_ONE);
		Thread.sleep(200);
		os.write(OPEN_TO_ZERO);
		os.flush();

		InputStream is = socket.getInputStream();

		byte[] buf = new byte[1024];
		int length = 0;
		int read = is.read(buf);
		// String str = new String(buf,0,is.read(buf),StandardCharsets.UTF_8);
		System.out.println(read);
		socket.close();
	}

	/**
	 * 密集架关闭
	 * @param ip
	 * @param port
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void close(String ip, int port) throws IOException,
			InterruptedException {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			socketForIms.connect(address, 2000);
			socketForIms.setSoTimeout(5000);
			os = socketForIms.getOutputStream();
			os.write(STOP_TO_ONE);
			Thread.sleep(100L);
			os.write(STOP_TO_ZERO);
			Thread.sleep(100L);
			os.write(CLOSE_TO_ONE);
			Thread.sleep(100L);
			os.write(CLOSE_TO_ZERO);
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes,
						StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取关闭密集架的返回值失败：", e1);
			}
			log.info("密集架合拢成功");
		} catch (Exception e) {
			log.error("密集架合拢失败", e);

			if (os != null) {
				try {
					os.close();
				} catch (Exception e1) {
					log.error("密集架合拢接口OutputStream关闭失败", e1);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e1) {
					log.error("密集架合拢接口InputStream关闭失败", e1);
				}
			}
			if (socketForIms != null)
				try {
					socketForIms.close();
				} catch (IOException e1) {
					log.error("密集架合拢接口Socket关闭失败", e1);
				}
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架合拢接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架合拢接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
				} catch (IOException e) {
					log.error("密集架合拢接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架合拢返回值:" + str);
	}

	public static void stopOrCancel(String ip, int port) throws IOException,
			InterruptedException {
		Socket socket = new Socket(ip, port);
		OutputStream os = socket.getOutputStream();
		os.write(STOP_TO_ONE);
		Thread.sleep(200);
		os.write(STOP_TO_ZERO);
		os.flush();

		InputStream is = socket.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		// String str = new String(buf,0,is.read(buf));
		// System.out.println(str);
		socket.close();
	}

	public static void reset(String ip, int port) throws IOException,
			InterruptedException {
		ShelveControlByTcp.stopOrCancel(ip, port);
	}

	public static void vent(String ip, int port) throws IOException,
			InterruptedException {
		Socket socket = new Socket(ip, port);
		OutputStream os = socket.getOutputStream();
		os.write(WIND_TO_ONE);
		Thread.sleep(200);
		os.write(WIND_TO_ZERO);
		os.flush();

		InputStream is = socket.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		// String str = new String(buf,0,is.read(buf));
		socket.close();
	}

	public static void humid(String ip, int port) {

		try {
			Socket socket = new Socket(ip, port);
			OutputStream os = socket.getOutputStream();
			os.write(HUMID__DEFAULT);
			os.flush();

			InputStream is = socket.getInputStream();
			byte[] buf = new byte[1024];
			int length = 0;
			// String str = new String(buf,0,is.read(buf));
			// System.out.println(str);
			socket.close();
		} catch (Exception e) {
			log.error("", e);
		}

	}

	public static void main(String[] args) throws Exception {

		byte[] byteArray = ShelveControlByTcp.channelToByteArray(5);
		String str = Arrays.toString(byteArray);
		ShelveControlByTcp.stopOrCancel("192.168.1.19", 192);
		Thread.sleep(200);
		ShelveControlByTcp.vent("192.168.1.19", 192);
		// ShelveControlByTcp.close("192.168.217.21", 192);
		// ShelveControlByTcp.humid("192.168.217.21", 192);
		// System.out.println(str);

	}

}
