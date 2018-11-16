package com.lanpangzi.service.impl.bussiness2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.OtherInfomationMapper;
import com.lanpangzi.pojo.Other;
import com.lanpanzi.service2.OtherInfomationService2;

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
	public Boolean insertOtherInfo(Other other) {
		// TODO Auto-generated method stub
		return otherInfomationDao.insertOtherInfoById(other);
	}

	@Override
	public Boolean deleteValueById(Integer oid) {
		// TODO Auto-generated method stub
		return otherInfomationDao.deleteOtherInfoById(oid);
	}


}
