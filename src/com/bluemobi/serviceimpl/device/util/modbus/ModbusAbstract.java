package com.bluemobi.serviceimpl.device.util.modbus;

import org.apache.log4j.Logger;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsRequest;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsResponse;
import com.serotonin.modbus4j.msg.ReadExceptionStatusRequest;
import com.serotonin.modbus4j.msg.ReadExceptionStatusResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.ReadInputRegistersRequest;
import com.serotonin.modbus4j.msg.ReadInputRegistersResponse;
import com.serotonin.modbus4j.msg.ReportSlaveIdRequest;
import com.serotonin.modbus4j.msg.ReportSlaveIdResponse;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.modbus4j.msg.WriteMaskRegisterRequest;
import com.serotonin.modbus4j.msg.WriteMaskRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;
import com.serotonin.modbus4j.msg.WriteRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;

/**
 * <p>
 * Title: Modbus 抽象类
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
public abstract class ModbusAbstract {
	Logger log = Logger.getLogger(this.getClass());
	//功能01
	protected ReadCoilsResponse readCoil(ModbusMaster master, int slaveId, int start, int len) {
		try {
			ReadCoilsRequest request = new ReadCoilsRequest(slaveId, start, len);
			ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);
			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能02
	protected ReadDiscreteInputsResponse readDiscreteInput(ModbusMaster master, int slaveId,
			int start, int len) {
		try {
			ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
			ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能03
	protected ReadHoldingRegistersResponse readHoldingRegisters(ModbusMaster master, int slaveId,
			int start, int len) {
		try {
			ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start,
					len);
			ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master
					.send(request);
			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能04
	protected ReadInputRegistersResponse readInputRegisters(ModbusMaster master, int slaveId,
			int start, int len) {
		try {
			ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, start, len);
			ReadInputRegistersResponse response = (ReadInputRegistersResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能05
	protected WriteCoilResponse writeCoil(ModbusMaster master, int slaveId, int offset, boolean value) {
		try {
			WriteCoilRequest request = new WriteCoilRequest(slaveId, offset, value);
			WriteCoilResponse response = (WriteCoilResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能06
	protected WriteRegisterResponse writeRegister(ModbusMaster master, int slaveId, int offset, int value) {
		try {
			WriteRegisterRequest request = new WriteRegisterRequest(slaveId, offset, value);
			WriteRegisterResponse response = (WriteRegisterResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能07
	protected ReadExceptionStatusResponse readExceptionStatus(ModbusMaster master, int slaveId) {
		try {
			ReadExceptionStatusRequest request = new ReadExceptionStatusRequest(slaveId);
			ReadExceptionStatusResponse response = (ReadExceptionStatusResponse) master
					.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能17
	protected ReportSlaveIdResponse reportSlaveId(ModbusMaster master, int slaveId) {
		try {
			ReportSlaveIdRequest request = new ReportSlaveIdRequest(slaveId);
			ReportSlaveIdResponse response = (ReportSlaveIdResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能15
	protected WriteCoilsResponse writeCoils(ModbusMaster master, int slaveId, int start, boolean[] values) {
		try {
			WriteCoilsRequest request = new WriteCoilsRequest(slaveId, start, values);
			WriteCoilsResponse response = (WriteCoilsResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能16
	protected WriteRegistersResponse writeRegisters(ModbusMaster master, int slaveNo, int start,
			short[] values) {
		try {
			WriteRegistersRequest request = new WriteRegistersRequest(slaveNo, start, values);
			WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
	//功能22
	protected WriteMaskRegisterResponse writeMaskRegister(ModbusMaster master, int slaveId, int offset, int and,
			int or) {
		try {
			WriteMaskRegisterRequest request = new WriteMaskRegisterRequest(slaveId, offset, and,
					or);
			WriteMaskRegisterResponse response = (WriteMaskRegisterResponse) master.send(request);

			return response;
		} catch (ModbusTransportException e) {
			log.error("",e);
		}
		return null;
	}
}
