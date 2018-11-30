package com.lanpanzi.service2;


import java.util.Map;
import com.lanpangzi.pojo.Borrow;

public interface BorrowAuthenticationService2 {
	
	public Boolean saveAuthDiscernImage(Integer uid ,String img);
	public String getAuthDiscernInfo(Integer uid);
	
	public Map<String ,String> findAutheAllStates(Integer uid);
	
	public Boolean IsExist(Integer uid);
	
	public Boolean saveBankAuthentication(Borrow borrow);
	public Boolean saveAliPayAuthentication(Borrow borrow);
	public Boolean savePersonalAuthentication(Borrow borrow);
	
	public String findBankActiveState(Integer uid);
	public String findAliPayActiveState(Integer uid);
	public String findPersonalActiveState(Integer uid);
	
	public Map<String, String> findBankAuthentication(Integer uid);
	public Map<String, String> findAliPayAuthentication(Integer uid);
	public Map<String, String> findPersonalAuthentication(Integer uid);
}