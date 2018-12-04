package com.lanpangzi.service.impl.bussiness2;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.UserAuthenticationMapper;
import com.lanpangzi.pojo.Users;
import com.lanpanzi.service.service2.UserAuthenticationService;
@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
	
	@Autowired
	private UserAuthenticationMapper userAuthenticationDao;
	
	@Override
	public Users findLoginAuthentication(String phone, String password) {
		return userAuthenticationDao.findLogin(phone, password);
	}
	@Override
	public Boolean addRegisterAuthentication(String phone, String password) {
		return userAuthenticationDao.addRegister(phone,
				password,new Date(System.currentTimeMillis()));
	}
	@Override
	public String findIsRegisterAuthentication(String phone) {
		return userAuthenticationDao.findIsRegister(phone);
	}
	@Override
	public Boolean modifyHeaderPhoto(Users user) {
		// TODO Auto-generated method stub
		return userAuthenticationDao.modifyHeaderPhoto(user);
	}
	@Override
	public Boolean modifyforgetPassword(String phone, String password) {
		// TODO Auto-generated method stub
		return userAuthenticationDao.modifyforgetPassword(phone,password);
	}

}
