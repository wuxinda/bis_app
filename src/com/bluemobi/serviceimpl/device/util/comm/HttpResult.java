package com.bluemobi.serviceimpl.device.util.comm;

public class HttpResult {
	public boolean isResult() {
		return Result;
	}
	public void setResult(boolean result) {
		Result = result;
	}
	public String getResponse() {
		return Response;
	}
	public void setResponse(String response) {
		Response = response;
	}
	boolean Result;
	String Response;
}
