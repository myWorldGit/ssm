package com.lanpangzi.service.impl.bussiness2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.BorrowAuthenticationMapper;
import com.lanpangzi.pojo.Borrow;
import com.lanpanzi.service2.BorrowAuthenticationService2;

/**
 * 0  代表未审核状态  1审核通过状态
 * @author 帅气的老胡
 *
 */
@Service
public class BorrowAuthenticationServiceImpl2 implements BorrowAuthenticationService2{
	@Autowired
	private BorrowAuthenticationMapper borrowAuthenticationDao;
	@Override
	public Boolean IsExist(Integer uid) {
		return borrowAuthenticationDao.IsAuthentication(uid)==null?false:true;
	}
	
	@Override
	public Boolean saveBankAuthentication(Borrow borrow) {
		if(IsExist(borrow.getBid()))
			return borrowAuthenticationDao.updateBankAuthentication(borrow);
		return borrowAuthenticationDao.insertBankAuthentication(borrow);
	}

	@Override
	public Boolean saveAliPayAuthentication(Borrow borrow) {
		if(IsExist(borrow.getBid()))
			return borrowAuthenticationDao.updateAliPayAuthentication(borrow);
		return borrowAuthenticationDao.insertAliPayAuthentication(borrow);
	}

	@Override
	public Boolean savePersonalAuthentication(Borrow borrow) {
		System.out.println(borrow.getBid());
		if(IsExist(borrow.getBid()))
			return borrowAuthenticationDao.updatePersonalAuthentication(borrow);
		return borrowAuthenticationDao.insertPersonalAuthentication(borrow);
	}

	@Override
	public String findBankActiveState(Integer uid) {
		if(!IsExist(uid))
			return "0";
		return borrowAuthenticationDao.findBankActiveState(uid);
	}

	@Override
	public String findAliPayActiveState(Integer uid) {
		if(!IsExist(uid))
			return "0";
		return borrowAuthenticationDao.findAliPayActiveState(uid);
	}

	@Override
	public String findPersonalActiveState(Integer uid) {
		if(!IsExist(uid))   
			return "0";
		return borrowAuthenticationDao.findPersonalActiveState(uid);
		
	}

	@Override
	public Map<String,String> findBankAuthentication(Integer uid) {
		Map<String,String> map =new HashMap<>();
		if(IsExist(uid)) {  //如果表建立了   就查询表中数据
			Borrow borrow = borrowAuthenticationDao.findBankAuthentication(uid);
			map.put("justbank", borrow.getBankA());
			map.put("backbank", borrow.getBankB());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, String> findAliPayAuthentication(Integer uid) {
		Map<String,String> map =new HashMap<>();
		if(IsExist(uid)) {  //如果表建立了   就查询表中数据
			Borrow borrow = borrowAuthenticationDao.findAliPayAuthentication(uid);
			map.put("payimg1", borrow.getPayA());
			map.put("payimg2", borrow.getPayB());
			map.put("payimg3", borrow.getPayC());
			map.put("payimg4", borrow.getPayD());
			return map;
		}
		return map;
	}

	@Override
	public Map<String, String> findPersonalAuthentication(Integer uid) {
		Map<String,String> map =new HashMap<>();
		if(IsExist(uid)) {  //如果表建立了   就查询表中数据
			Borrow borrow = borrowAuthenticationDao.findPersonalAuthentication(uid);
			map.put("authName", borrow.getOperatorName());
			map.put("authIdcard", borrow.getOperatorIdcard());
			map.put("authPhone", borrow.getOperatorPhone());
			map.put("authpassword", borrow.getIsAgreement());
			return map;
		}
		return map;
	}

}
