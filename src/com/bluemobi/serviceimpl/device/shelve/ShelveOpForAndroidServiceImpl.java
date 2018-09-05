package com.bluemobi.serviceimpl.device.shelve;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bluemobi.service.device.shelve.ShelveOpForAndroidService;

@Service("shelveOpForAndroidService")
public class ShelveOpForAndroidServiceImpl implements ShelveOpForAndroidService {
	private static Logger log = Logger.getLogger(ShelveOpForAndroidServiceImpl.class);

	public static String IMS_START_SYMBOL = "START";// 操作起始标识
	public static String IMS_OP_VERSION = "02";// 接口版本
	public static String IMS_OP_COMM_LEFTOPEN = "LEFTOPEN";// 操作命令-左开
	public static String IMS_OP_COMM_RIGHTOPEN = "RIGHTOPEN";// 操作命令-右开
	public static String IMS_OP_COMM_VENTILATION = "VENTILATION";// 操作命令-通风
	public static String IMS_OP_COMM_CLOSED = "CLOSED";// 操作命令-合拢
	public static String IMS_OP_COMM_STOP = "STOP";// 操作命令-停止
	public static String IMS_END_SYMBOL = "END";// 操作结束标识
	public static String IMS_SEPARATE_SYMBOL = "*";// 操作结束标识

	private static int socketConnTimeout = 2000;// socket连接时间
	private static int socketSoTimeout = 2000;// 连接时长
	
	/*只用于八益样品间－密集架电源通电断电命令 on 2018.1.2 by chenb */
	//密集架通电
	public static byte[] IMS_OP_COMM_TD = { (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x05,
		(byte) 0x00, (byte) 0x00, (byte) 0xff, (byte) 0x00 };
	//密集架断电
	public static byte[] IMS_OP_COMM_DD = { (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x06, (byte) 0x01, (byte) 0x05,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
	
	@Override
	public boolean leftOpen(String ip, int port, int channel) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			StringBuffer comm = new StringBuffer();
			comm.append(IMS_START_SYMBOL);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_VERSION);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_COMM_LEFTOPEN);
			comm.append(IMS_SEPARATE_SYMBOL);
			String strCol = "01";
			if (channel < 1 || channel > 99) {
				log.error("密集架左开-列号不对");
				return false;
			} else if (channel < 10) {
				strCol = "0" + String.valueOf(channel);
			} else {
				strCol = String.valueOf(channel);
			}
			comm.append(strCol);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_END_SYMBOL);
			log.info("密集架操作命令:" + comm.toString());
			os.write(ShelveOpForAndroidServiceImpl.stringToAscii(comm.toString()));
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架左开的返回值失败：", e1);
			}
			log.info("密集架左开成功");
		} catch (Exception e) {
			log.error("密集架左开失败", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架左开接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架左开接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
					socketForIms = null;
				} catch (IOException e) {
					log.error("密集架左开接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架左开返回值:" + str);
		return true;
	}

	@Override
	public boolean rightOpen(String ip, int port, int channel) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			StringBuffer comm = new StringBuffer();
			comm.append(IMS_START_SYMBOL);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_VERSION);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_COMM_RIGHTOPEN);
			comm.append(IMS_SEPARATE_SYMBOL);
			String strCol = "01";
			if (channel < 1 || channel > 99) {
				log.error("密集架右开-列号不对");
				return false;
			} else if (channel < 10) {
				strCol = "0" + String.valueOf(channel);
			} else {
				strCol = String.valueOf(channel);
			}
			comm.append(strCol);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_END_SYMBOL);
			log.info("密集架操作命令:" + comm.toString());
			os.write(ShelveOpForAndroidServiceImpl.stringToAscii(comm.toString()));
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架右开的返回值失败：", e1);
			}
			log.info("密集架右开成功");
		} catch (Exception e) {
			log.error("密集架右开失败", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架右开接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架右开接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
				} catch (IOException e) {
					log.error("密集架右开接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架右开返回值:" + str);
		return true;
	}

	@Override
	public boolean vent(String ip, int port) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			StringBuffer comm = new StringBuffer();
			comm.append(IMS_START_SYMBOL);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_VERSION);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_COMM_VENTILATION);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append("00");
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_END_SYMBOL);
			log.info("密集架操作命令:" + comm.toString());
			os.write(ShelveOpForAndroidServiceImpl.stringToAscii(comm.toString()));
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架通风的返回值失败：", e1);
			}
			log.info("密集架通风成功");
		} catch (Exception e) {
			log.error("密集架通风失败", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架通风接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架通风接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
				} catch (IOException e) {
					log.error("密集架通风接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架通风返回值:" + str);
		return true;
	}

	@Override
	public boolean close(String ip, int port) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			StringBuffer comm = new StringBuffer();
			comm.append(IMS_START_SYMBOL);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_VERSION);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_COMM_CLOSED);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append("00");
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_END_SYMBOL);
			log.info("密集架操作命令:" + comm.toString());
			os.write(ShelveOpForAndroidServiceImpl.stringToAscii(comm.toString()));
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架合拢的返回值失败：", e1);
			}
			log.info("密集架合拢成功");
		} catch (Exception e) {
			log.error("密集架合拢失败", e);
			return false;
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
		return true;
	}

	@Override
	public boolean stop(String ip, int port) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			StringBuffer comm = new StringBuffer();
			comm.append(IMS_START_SYMBOL);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_VERSION);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_OP_COMM_STOP);
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append("00");
			comm.append(IMS_SEPARATE_SYMBOL);
			comm.append(IMS_END_SYMBOL);
			log.info("密集架操作命令:" + comm.toString());
			os.write(ShelveOpForAndroidServiceImpl.stringToAscii(comm.toString()));
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架停止的返回值失败：", e1);
			}
			log.info("密集架停止成功");
		} catch (Exception e) {
			log.error("密集架停止失败", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架停止接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架停止接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
				} catch (IOException e) {
					log.error("密集架停止接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架停止返回值:" + str);
		return true;
	}

	public static byte[] stringToAscii(String value) {
		try {
			return value.getBytes("US-ASCII");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean openPws(String ip, int port) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			log.info("密集架操作命令:" + IMS_OP_COMM_TD.toString());
			os.write(IMS_OP_COMM_TD);
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架通电的返回值失败：", e1);
			}
			log.info("密集架通电成功");
		} catch (Exception e) {
			log.error("密集架通电失败", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架通电接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架通电接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
				} catch (IOException e) {
					log.error("密集架通电接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架通电返回值:" + str);
		return true;
	}
	@Override
	public boolean closePws(String ip, int port) {
		Socket socketForIms = new Socket();
		SocketAddress address = new InetSocketAddress(ip, port);
		OutputStream os = null;
		InputStream is = null;
		String str = "";
		try {
			try {
				socketForIms.connect(address, socketConnTimeout);
				socketForIms.setSoTimeout(socketSoTimeout);
				os = socketForIms.getOutputStream();
			} catch (Exception e) {
				log.error("设备连接失败", e);
				throw e;
				// return false;
			}
			log.info("密集架操作命令:" + IMS_OP_COMM_TD.toString());
			os.write(IMS_OP_COMM_DD);
			os.flush();
			is = socketForIms.getInputStream();
			try {
				byte[] buf = new byte[1024];
				int numReadedBytes = is.read(buf, 0, buf.length);
				str = new String(buf, 0, numReadedBytes, StandardCharsets.US_ASCII);
			} catch (Exception e1) {
				log.error("获取密集架断电的返回值失败：", e1);
			}
			log.info("密集架断电成功");
		} catch (Exception e) {
			log.error("密集架断电失败", e);
			return false;
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.error("密集架断电接口OutputStream关闭失败", e);
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.error("密集架断电接口InputStream关闭失败", e);
				}
			}
			if (socketForIms != null) {
				try {
					socketForIms.close();
				} catch (IOException e) {
					log.error("密集架断电接口Socket关闭失败", e);
				}
			}
		}
		log.info("密集架断电返回值:" + str);
		return true;
	}
	@Override
	public boolean openByArchivesId(String ip, int port, int archivesId) {
		// 1.判断archivesId是否有值，如果为－1，默认第一列做开
		if(archivesId==-1){
			return this.leftOpen(ip, port, 1);
		}
		// 2.根据archivesId档案id获取档案所在密集架的列号以及判定是左开还是右开操作(后续作国补充，这里默认执行第一列左开)
		return this.leftOpen(ip, port, 1);
	}

	/**
	 * @Title: main @Description: @param args @return void 返回类型 @throws
	 */
	public static void main(String[] args) {
		// ShelveOpForAndroidServiceImpl imp = new ShelveOpForAndroidServiceImpl();
		// imp.leftOpen("192.168.1.155", 10086, 2);
		// imp.rightOpen("192.168.1.155", 10086, 2);
		// imp.vent("192.168.1.155", 10086);
		// imp.close("192.168.1.155", 10086);
		// imp.stop("192.168.1.155", 10086);
		// for(int i=0; i<1000; i++){
		// imp.leftOpen("192.168.1.155", 10086, 2);
		// System.out.println("i==" + i);
		// }
	}
}
