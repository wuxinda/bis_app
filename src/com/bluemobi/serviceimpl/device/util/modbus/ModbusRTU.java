package com.bluemobi.serviceimpl.device.util.modbus;

import org.apache.log4j.Logger;

import com.bluemobi.serviceimpl.device.util.HardwareError;
import com.bluemobi.serviceimpl.device.util.HardwareResult;
import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.FunctionCode;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.util.queue.ByteQueue;

/**
 * <p>
 * Title: Modbus RTU 实现类
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
public class ModbusRTU extends ModbusAbstract {

	Logger log = Logger.getLogger(this.getClass());

	private ModbusFactory modbusFactory = new ModbusFactory();

	private String commPortId; // COM口
	private int baudRate; // 波特率
	private int parity; // 位检查（N:None、O：Odd、E：Even）
	private int dataBits; // 每个位bits数（7 or 8）
	private int stopBits; // 每个位stop bits（1 or 2）
	
	public ModbusRTU(){
		
	}

	public ModbusRTU(String commPortId, int baudRate, int parity, int dataBits, int stopBits) {
		if (modbusFactory == null) {
			modbusFactory = new ModbusFactory();
		}
		this.commPortId = commPortId;
		this.baudRate = baudRate;
		this.parity = parity;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
	}
	
	public SerialParameters getSerialParams() {
		SerialParameters params = new SerialParameters();
		params.setCommPortId(commPortId);
		params.setBaudRate(baudRate);
		params.setDataBits(dataBits); // Data Bit：7、8等
		params.setStopBits(stopBits); // Stop Bit：1、2等
		params.setParity(parity); // Parity：None、Odd、Even
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
	public HardwareResult read(byte functionCode, String commPortId, int baudRate, int slaveId,
			int start, int len) {
		HardwareResult hr = new HardwareResult();
		if (functionCode < 1 || functionCode > 4) {
			log.info("Modbus TCP/IP FunctionCode 必须在1 - 4之间.....");
			return new HardwareResult(HardwareError.ERR_FUNCTIONCODE,
					HardwareError.S_ERR_FUNCTIONCODE);
		}
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse modbusResponse = null;
		master = modbusFactory.createRtuMaster(params);

		ByteQueue byteQueue = null;
		try {
			master.setTimeout(500);
			master.setRetries(0);

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
						HardwareError.S_ERR_READ_REGISTERS + modbusResponse.getExceptionCode()
								+ ":" + modbusResponse.getExceptionMessage());
			}
		} catch (Exception e) {
			log.error("Modbus TCP/IP Read Exception..." + e);
			hr = new HardwareResult(HardwareError.ERR_INIT, HardwareError.S_ERR_INIT);
		} finally {
			master.destroy();
		}
		return hr;
	}

	// 05 WRITE_COIL
	public ModbusResponse writeCoil(String commPortId, int baudRate, int slaveId, int offset,
			boolean value) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP WRITE_COIL Init Success.....");

			response = writeCoil(master, slaveId, offset, value);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_COIL Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP WRITE_COIL Success");
			}
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_COIL Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 06 WRITE_REGISTER
	public ModbusResponse writeRegister(String commPortId, int baudRate, int slaveId, int offset,
			int value) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP WRITE_REGISTER Init Success.....");

			response = writeRegister(master, slaveId, offset, value);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_REGISTER Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP WRITE_REGISTER Success");
			}
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_REGISTER Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 07 READ_EXCEPTION_STATUS
	public ModbusResponse readExceptionStatus(String commPortId, int baudRate, int slaveId) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP READ_EXCEPTION_STATUS Init Success.....");

			response = readExceptionStatus(master, slaveId);
			if (response.isException()) {
				log.info("Modbus TCP/IP READ_EXCEPTION_STATUS Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP READ_EXCEPTION_STATUS Success");
			}
		} catch (Exception e) {
			log.error("Modbus TCP/IP READ_EXCEPTION_STATUS Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 15 WRITE_COILS
	public ModbusResponse reportSlaveId(String commPortId, int baudRate, int slaveId) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP REPORT_SLAVE_ID Init Success.....");

			response = reportSlaveId(master, slaveId);
			if (response.isException()) {
				log.info("Modbus TCP/IP REPORT_SLAVE_ID Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP REPORT_SLAVE_ID Success");
			}
		} catch (Exception e) {
			log.error("Modbus TCP/IP REPORT_SLAVE_ID Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}

	// 16 WRITE_REGISTERS
	public ModbusResponse writeCoils(String commPortId, int baudRate, int slaveId, int start,
			boolean[] values) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP WRITE_COILS Init Success.....");

			response = writeCoils(master, slaveId, start, values);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_COILS Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP WRITE_COILS Success");
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
	public ModbusResponse writeRegisters(String commPortId, int baudRate, int slaveId, int start,
			short[] values) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP WRITE_REGISTERS Init Success.....");

			response = writeRegisters(master, slaveId, start, values);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_REGISTERS Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP WRITE_REGISTERS Success");
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
	public ModbusResponse writeMaskRegister(String commPortId, int baudRate, int slaveId,
			int offset, int and, int or) {
		SerialParameters params = getSerialParams();
		ModbusMaster master = null;
		ModbusResponse response = null;
		master = modbusFactory.createRtuMaster(params);
		try {
			master.init();
			log.info("Modbus TCP/IP WRITE_MASK_REGISTER Init Success.....");

			response = writeMaskRegister(master, slaveId, offset, and, or);
			if (response.isException()) {
				log.info("Modbus TCP/IP WRITE_MASK_REGISTER Exception Response:"
						+ response.getExceptionMessage());
			} else {
				log.info("Modbus TCP/IP WRITE_MASK_REGISTER Success");
			}
			return response;
		} catch (Exception e) {
			log.error("Modbus TCP/IP WRITE_MASK_REGISTER Exception:" + e);
		} finally {
			master.destroy();
		}
		return response;
	}
}
