package com.cts.restapidemo.models;

public class ErrorMessage {

	private String errMsg;

	public ErrorMessage(String errMsg) {
		super();
		this.errMsg = errMsg;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
		
}
