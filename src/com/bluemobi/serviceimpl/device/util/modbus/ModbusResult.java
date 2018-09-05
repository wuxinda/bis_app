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
public class ModbusResult {

	private String ResultCode; //结果代码1001 － 成功，其它失败
	private String ErrorInfo; //错误信息
	
	
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	public String getErrorInfo() {
		return ErrorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		ErrorInfo = errorInfo;
	}
	public boolean isSuccess(){
		return ModbusError.SUCCESS.equals(this.getResultCode());
	}
}
