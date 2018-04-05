package com.logistics.web.module.response.base;

import java.util.List;

import com.logistics.module.crm.dto.CustomerDTO;

public class BaseResponse {
	
	private String errorMsg;
	private int result;
	private String requestId;
	
	private String userName;
	private int userNum;
	
	/**
	 * 默认返回码为1：成功
	 */
	public BaseResponse(){
		this(ResponseCode.SUCCESS);
	}
	
	public BaseResponse(ResponseCode result){
		this(result.getCode(), result.getMsg());
	}

	public BaseResponse(int result, String msg) {
		this.result = result;
		this.errorMsg = msg;
	}
	
	public void success() {
		this.respond(ResponseCode.SUCCESS);
	}

	public void respond(ResponseCode code) {
		this.result = code.getCode();
		this.errorMsg = code.getMsg();
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public void writeErrorMsgWithResult(String errorMsg) {
		this.result = ResponseCode.WRITE_ERROR_MSG.getCode();
		this.errorMsg = errorMsg;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	public String getRequestId() {
		requestId = RandomNumber.generateUUID();
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	
}
