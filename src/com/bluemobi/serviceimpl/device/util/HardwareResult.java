package com.bluemobi.serviceimpl.device.util;

/**
 * <p>
 * Title: 硬件返回类
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
public class HardwareResult {

	private String ResultCode; // 结果代码1001 － 成功，其它失败
	private String ErrorInfo; // 错误信息
	private Object Response; // 返回信息
	private boolean Success;// 是否成功

	public HardwareResult() {

	}

	public HardwareResult(String ResultCode, String ErrorInfo) {
		this.ResultCode = ResultCode;
		this.ErrorInfo = ErrorInfo;
	}

	public HardwareResult(String ResultCode, String ErrorInfo, String Response) {
		this.ResultCode = ResultCode;
		this.ErrorInfo = ErrorInfo;
		this.Response = Response;
	}

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

	public boolean isSuccess() {
		Success = HardwareError.SUCCESS.equals(this.getResultCode());
		return Success;
	}

	public void setSuccess(boolean Success) {
		this.Success = Success;
	}

	public Object getResponse() {
		if (isSuccess()) {
			return Response;
		} else {
			return getErrorInfo();
		}
	}

	public void setResponse(Object response) {
		Response = response;
	}
}
