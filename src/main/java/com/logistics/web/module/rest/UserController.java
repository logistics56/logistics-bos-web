package com.logistics.web.module.rest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.module.dto.UserDTO;
import com.logistics.module.service.UserService;
import com.logistics.web.module.request.UserRequest;
import com.logistics.web.module.response.base.BaseResponse;
import com.logistics.web.module.response.base.ResponseCode;

/**
*
* @author 李振        E-mail:lizhn95@163.com
* @version 创建时间：2018年4月5日 上午11:35:07
* 
*/
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public BaseResponse login(@RequestBody UserRequest ref) {
		BaseResponse response = new BaseResponse();
		// 基于shiro实现登录
		Subject subject = SecurityUtils.getSubject();
		
		// 用户名和密码信息
		AuthenticationToken token = new UsernamePasswordToken(
						ref.getUserNum()+"", ref.getPassword());
		try {
			subject.login(token);
			// 登录成功
			// 将用户信息 保存到 Session
			Session session = subject.getSession();
			session.setTimeout(-1);
			
			response.setErrorMsg(ResponseCode.SUCCESS.getMsg());
			response.setResult(ResponseCode.SUCCESS.getCode());
			return response;
		} catch (AuthenticationException e) {
			// 登录失败
			response.setErrorMsg("输入信息有误，请重新输入");
			response.setResult(ResponseCode.FAILED.getCode());
			return response;
		}
	}
	
	@RequestMapping(value = "/logout", method = { RequestMethod.POST })
	public BaseResponse logout() {
		BaseResponse response = new BaseResponse();
		// 基于shiro实现登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		response.setErrorMsg(ResponseCode.SUCCESS.getMsg());
		response.setResult(ResponseCode.SUCCESS.getCode());
		return response;
		
	}
	
	@RequestMapping(value = "/getuser", method = { RequestMethod.POST })
	public BaseResponse getuser() {
		BaseResponse response = new BaseResponse();
		
		UserDTO user = (UserDTO) SecurityUtils.getSubject().getPrincipal(); 
		response.setUserName(user.getcUsername());
		response.setUserNum(user.getcId());
		response.setErrorMsg(ResponseCode.SUCCESS.getMsg());
		response.setResult(ResponseCode.SUCCESS.getCode());
		return response;
		
	}
	
	@RequestMapping(value = "/changepwd", method = { RequestMethod.POST })
	public BaseResponse changepwd(@RequestBody UserRequest ref) {
		BaseResponse response = new BaseResponse();
		
		UserDTO user = (UserDTO) SecurityUtils.getSubject().getPrincipal(); 
		
		int num =userService.updatePWD(user.getcId(), ref.getPassword());
		if(num == 1){
			// 基于shiro实现登录
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
			response.setErrorMsg(ResponseCode.SUCCESS.getMsg());
			response.setResult(ResponseCode.SUCCESS.getCode());
		}else{
			response.setErrorMsg("修改失败");
			response.setResult(ResponseCode.FAILED.getCode());
		}
		return response;
		
	}

}
