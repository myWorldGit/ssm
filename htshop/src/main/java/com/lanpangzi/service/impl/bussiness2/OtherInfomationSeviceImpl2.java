package com.lanpangzi.service.impl.bussiness2;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.OtherInfomationMapper;
import com.lanpangzi.pojo.Other;
import com.lanpanzi.service.service2.OtherInfomationService2;

@Service
public class OtherInfomationSeviceImpl2 implements OtherInfomationService2 {
	@Autowired
	private OtherInfomationMapper otherInfomationDao;

	@Override
	public Boolean clearByKeyAllInfo(String key) {
		// TODO Auto-generated method stub
		return otherInfomationDao.deleteOtherInfoByType(key);
	}

	@Override
	public List<Other> findValuesByKey(String key) {
		// TODO Auto-generated method stub
		return otherInfomationDao.findOtherInfoByType(key);
	}

	@Override
	public Boolean updateValueById(Integer oid, String value) {
		// TODO Auto-generated method stub
		return otherInfomationDao.updateOtherInfoById(oid, value);
	}

	@Override
	public Integer insertOtherInfo(Other other) {
		// TODO Auto-generated method stub
		//插入成功  返回他的id   否则null
		return otherInfomationDao.insertOtherInfoById(other)==true?
				otherInfomationDao.getprevInsertID():null;
	}

	@Override
	public Boolean deleteValueById(Integer oid) {
		// TODO Auto-generated method stub
		return otherInfomationDao.deleteOtherInfoById(oid);
	}

	@Override
	public String findSingleInfo(String key) {
		return otherInfomationDao.findSingleInfo(key);
	}

	@Override
	public String findOtherById(Integer oid) {
		// TODO Auto-generated method stub
		return otherInfomationDao.findvalueById(oid);
	}

	@Override
	public Boolean savePreAmount(String preAmount) {
		// TODO Auto-generated method stub
		return otherInfomationDao.insertOtherInfoById(new Other(preAmount,"preAmount",
				new Date(System.currentTimeMillis())));
	}


}
