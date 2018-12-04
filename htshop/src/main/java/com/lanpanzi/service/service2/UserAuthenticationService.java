package com.lanpanzi.service.service2;

import com.lanpangzi.pojo.Users;

public interface UserAuthenticationService {
	/**
	 * 	登陆
	 */
	public Users findLoginAuthentication(String phone,String password) ;
	/**
	 * 	注册
	 */
	public Boolean addRegisterAuthentication(String phone,String password);
	/**
	 * 	查找手机号是否被注册
	 */
	public String findIsRegisterAuthentication(String phone);
	public Boolean modifyHeaderPhoto(Users user);
	public Boolean modifyforgetPassword(String phone, String password);

}
