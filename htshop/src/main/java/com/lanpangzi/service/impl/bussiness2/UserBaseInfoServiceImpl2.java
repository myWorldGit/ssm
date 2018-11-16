package com.lanpangzi.service.impl.bussiness2;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.UserBaseInfo2Mapper;
import com.lanpangzi.pojo.Usersextend;
import com.lanpanzi.service2.UserBaseInfoService2;
@Service
public class UserBaseInfoServiceImpl2 implements UserBaseInfoService2 {

	@Autowired
	private UserBaseInfo2Mapper userBaseInfoDao;
	
	@Override
	public Boolean saveBaseInfo(Integer uid, String username, String idcard, String sex) {
		if(userBaseInfoDao.isFirstUserEx(uid)==null?false:true) {
			return userBaseInfoDao.updateBaseUserInfo(uid, idcard, sex)
			&&userBaseInfoDao.updateBaseUsername(uid, username);
		}
			
		return userBaseInfoDao.updateBaseUsername(uid, username)
			&&userBaseInfoDao.insertBaseUserInfo(uid, idcard, sex);
		
	}

	@Override
	public Boolean savePersonInfo(Integer uid, String address, String education, String marriage) {
		if(userBaseInfoDao.isFirstUserEx(uid)==null?false:true) {
			return userBaseInfoDao.updatePersonalInfo(uid, address, education, marriage);
		}
		return userBaseInfoDao.insertPersonalInfo(uid, address, education, marriage);
	}

	@Override
	public Boolean saveJobInfo(Integer uid, String job, String income, String working) {
		if(userBaseInfoDao.isFirstUserEx(uid)==null?false:true) {
			return userBaseInfoDao.updateJobInfo(uid, job, income, working);
		}
		return userBaseInfoDao.insertJobInfo(uid, job, income, working);
	}

	@Override
	public Boolean saveContactInfo(Integer uid, String contactname, String contactphone, String relation) {
		if(userBaseInfoDao.isFirstUserEx(uid)==null?false:true) {
			return userBaseInfoDao.updateContactInfo(uid, contactname, contactphone, relation);
		}
		return userBaseInfoDao.insertContactInfo(uid, contactname, contactphone, relation);
	}

	@Override
	public Usersextend findcardImgById(Integer uid) {
		return userBaseInfoDao.findIdcardImg(uid);
	}

	@Override
	public Boolean saveIdcardInfo(Usersextend userex) {
		if(userBaseInfoDao.isFirstUserEx(userex.getEid())==null?false:true) {
			return userBaseInfoDao.updateIdCardImg(userex);
		}
		return userBaseInfoDao.insertIdcardImg(userex);
	}
	// 基本
	@Override
	public Map<String,String> findBaseInfo(Integer uid) {
		Map<String,String> map =new HashMap<>();
		Usersextend userex = userBaseInfoDao.findBaseUserInfo(uid);
		String username = userBaseInfoDao.findBaseUsername(uid);
		map.put("username", username);
		map.put("sex", userex.getSex());
		map.put("idcard", userex.getIdcard());
		return map;
	}

	@Override
	public Map<String,String> findPersonalInfo(Integer uid) {
		Map<String,String> map =new HashMap<>();
		Usersextend userex=userBaseInfoDao.findPersonalInfo(uid);
		map.put("address", userex.getAddress());
		map.put("education", userex.getEducation());
		map.put("marriage", userex.getMarriage());
		return map;
	}

	@Override
	public Map<String,String> findJobInfo(Integer uid) {
		Map<String,String> map =new HashMap<>();
		Usersextend userex=userBaseInfoDao.findJobInfo(uid);
		map.put("job", userex.getJob());
		map.put("income", userex.getIncome());
		map.put("working", userex.getWorking());
		return map;
	}

	@Override
	public Map<String,String> findContactInfo(Integer uid) {
		Map<String,String> map =new HashMap<>();
		Usersextend userex=userBaseInfoDao.findContactInfo(uid);
		map.put("contactname", userex.getContactname());
		map.put("contactphone", userex.getContactphone());
		map.put("relation", userex.getRelation());
		return map;
	}

	@Override
	public Boolean IsExist(Integer uid) {
		return userBaseInfoDao.isFirstUserEx(uid)==null?false:true;
	}

	@Override
	public String findUsernameById(Integer uid) {
		return userBaseInfoDao.findBaseUsername(uid);
	}

}
