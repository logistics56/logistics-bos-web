package com.logistics.web.module.request;
/**
*
* @author 李振        E-mail:lizhn95@163.com
* @version 创建时间：2018年4月5日 上午11:54:55
* 
*/
public class UserRequest {
	
	private int userNum;
	
	private String password;

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserRequest [userNum=" + userNum + ", password=" + password + "]";
	}
	
	

}
