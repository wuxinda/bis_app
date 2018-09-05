package com.bluemobi.serviceimpl.device.util.modbus;

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
public class ModbusResultResponse extends ModbusResult {
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
