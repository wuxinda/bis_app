package com.bluemobi.serviceimpl.device.util.modbus;

import org.apache.log4j.Logger;

import com.bluemobi.serviceimpl.device.util.HardwareError;
import com.bluemobi.serviceimpl.device.util.HardwareResult;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.FunctionCode;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.util.queue.ByteQueue;

/**
 * <p>
 * Title: Modbus TCP/IP 实现类
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
 * @date 2014-10-12
 */
public class ModbusTCP extends ModbusAbstract {

	Logger log = Logger.getLogger(this.getClass());

	private ModbusFactory modbusFactory = new ModbusFactory();

	public ModbusTCP() {
		if (modbusFactory == null) {
			modbusFactory = new ModbusFactory();
		}
	}

	public IpParameters getIpParams(String ip, int port) {
		IpParameters params = new IpParameters();
		params.setHost(ip);
		if (port != 502) {
			params.setPort(port);
		}
		return params;
	}

	/**
	 * 
	 * @param functionCode
	 *            ：主要包含以下四个值 READ_COILS = 1; READ_DISCRETE_INPUTS = 2;
	 *            READ_HOLDING_REGISTERS = 3; READ_INPUT_REGISTERS = 4;
	 * @param ip
	 * @param port
	 * @param slaveId
	 * @param start
	 * @param len
	 * @return
	 */
	public HardwareResult read(byte functionCode, String ip, int port, int slaveId, int start,
			int len) {
		HardwareResult hr = new HardwareResult();
		if (functionCode < 1 || functionCode > 4) {
			log.info("Modbus TCP/IP FunctionCode 必须在1 - 4之间.....");
			return new HardwareResult(HardwareError.ERR_FUNCTIONCODE,
					HardwareError.S_ERR_FUNCTIONCODE);
		}
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse modbusResponse = null;
		master = modbusFactory.createTcpMaster(params, true);

		ByteQueue byteQueue = null;
		try {
			master.init();
			log.info("Modbus TCP/IP Read Init Success.....");

			switch (functionCode) {
			case FunctionCode.READ_COILS:
				modbusResponse = readCoil(master, slaveId, start, len);
				break;
			case FunctionCode.READ_DISCRETE_INPUTS:
				modbusResponse = readDiscreteInput(master, slaveId, start, len);
				break;
			case FunctionCode.READ_HOLDING_REGISTERS:
				modbusResponse = readHoldingRegisters(master, slaveId, start, len);
				break;
			case FunctionCode.READ_INPUT_REGISTERS:
				modbusResponse = readInputRegisters(master, slaveId, start, len);
				break;
			}

			if (modbusResponse != null && !modbusResponse.isException()) {
				byteQueue = new ByteQueue(len);
				modbusResponse.write(byteQueue);

				log.info("FunctionCode=" + modbusResponse.getFunctionCode() + ", SlaveId="
						+ modbusResponse.getSlaveId());
				log.info("Read Length: " + byteQueue.size());
				log.info(byteQueue);
				hr = new HardwareResult(HardwareError.SUCCESS, HardwareError.S_SUCCESS,
						byteQueue.toString());
			} else {
				hr = new HardwareResult(HardwareError.ERR_READ_REGISTERS,
						HardwareError.S_ERR_READ_REGISTERS + modbusResponse.getExceptionCode() + ":"
								+ modbusResponse.getExceptionMessage());
			}
		} catch (Exception e) {
			log.error("Modbus TCP/IP Read Exception..." + e);
			hr = new HardwareResult(HardwareError.ERR_INIT,
					HardwareError.S_ERR_INIT);
		} finally {
			master.destroy();
		}
		return hr;
	}

	// 05 WRITE_COIL
	public ModbusResponse writeCoil(String ip, int port, int slaveId, int offset, boolean value) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = writeCoil(master, slaveId, offset, value);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_COIL Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_COIL Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 06 WRITE_REGISTER
	public ModbusResponse writeRegister(String ip, int port, int slaveId, int offset, int value) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = writeRegister(master, slaveId, offset, value);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_REGISTER Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_REGISTER Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 07 READ_EXCEPTION_STATUS
	public ModbusResponse readExceptionStatus(String ip, int port, int slaveId) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = readExceptionStatus(master, slaveId);
			if (response.isException()) {
				log.info("Modbus TCP/IP READ_EXCEPTION_STATUS Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP READ_EXCEPTION_STATUS Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 15 WRITE_COILS
	public ModbusResponse reportSlaveId(String ip, int port, int slaveId) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = reportSlaveId(master, slaveId);
			if (response.isException()) {
				log.info("Modbus TCP/IP REPORT_SLAVE_ID Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP REPORT_SLAVE_ID Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 16 WRITE_REGISTERS
	public ModbusResponse writeCoils(String ip, int port, int slaveId, int start, boolean[] values) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = writeCoils(master, slaveId, start, values);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_COILS Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_COILS Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 17 REPORT_SLAVE_ID
	public ModbusResponse writeRegisters(String ip, int port, int slaveNo, int start, short[] values) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = writeRegisters(master, slaveNo, start, values);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_REGISTERS Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_REGISTERS Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 22 WRITE_MASK_REGISTER
	public ModbusResponse writeMaskRegister(String ip, int port, int slaveNo, int offset, int and,
			int or) {
		IpParameters params = getIpParams(ip, port);
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createTcpMaster(params, true);
		try {
			master.init();

			response = writeMaskRegister(master, slaveNo, offset, and, or);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_MASK_REGISTER Exception Response:"
						+ response.getExceptionMessage());
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCeK_REGISTER Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	public static void main(String[] args) throws Exception {
		// short[] values =
		// {0x91,0x0,0x0,0x0,0x0,0x6,0x1,0x6,0x1,0xc1,0x0,0x5,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0};
		// short[] values = {0x1,0x3,0x25,0xE5,0x0,0x4,0x5E,0xF2};
		// short[] values = {0x1,0x5,0x0,0x4,0xF,0xF,0x0,0x0};
		// short[] values = {0x1,0x1,0x0,0x0,0x1,0x1,0xFC,0xA};
		String ip = "127.0.0.1";
		int port = 502;
		int slaveId = 1;
		int start = 0;
		int len = 8;
		// short[] values = { 0x1, 0x1, 0x0, 0x0, 0x1, 0x1, 0xA, 0xFC };

		ModbusTCP mc = new ModbusTCP();
		// mc.writeRegisters(ip, port, slaveId, start, values);
		mc.writeRegister(ip, port, slaveId, 1, 3);

		HardwareResult hr = mc.read(FunctionCode.READ_HOLDING_REGISTERS, ip, port, slaveId, start, len);
		System.out.println(hr.getResponse());
	}
}
