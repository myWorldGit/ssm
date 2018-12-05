package com.lanpangzi.service.impl.bussiness;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business.SystemInfomationMapper;
import com.lanpangzi.pojo.Information;
import com.lanpanzi.service.api2.SystemInfomationService;

@Service
public class SystemInfomationServiceImpl implements SystemInfomationService {
	
	@Autowired
	private SystemInfomationMapper systemInfoDao;
	
	@Override
	public Boolean insertGeneralSystemInfomation(Information info) {
		info.setCreatetime(new Date(System.currentTimeMillis()));
		return systemInfoDao.insertGeneralSystemInfo(info);
	}

	@Override
	public Boolean deleteGeneralSystemInfomation(Integer iid) {
		return systemInfoDao.deleteGeneralSystemInfo(iid);
	}

	@Override
	public Boolean modifyInfoStateInfomation(Integer iid) {
		return systemInfoDao.modifyInfoState(iid);
	}

	@Override
	public List<Information> findAllInfomation(Integer uid,Integer page) {
		return systemInfoDao.findAllInfomation(uid,page*10);
	}

	@Override
	public Information findDetailInfomationById(Integer iid) {
		return systemInfoDao.findDetailInfomationById(iid);
	}

	@Override
	public Integer getUnreadInfomationCount(Integer uid) {
		// TODO Auto-generated method stub
		return systemInfoDao.getUnreadInfoCount(uid);
	}

	@Override
	public Boolean clrearUserAllfomations(Integer uid) {
		return systemInfoDao.clearUserfomationsById(uid);
	}

}
