package com.lanpangzi.service.impl.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business.LimuMapper;
import com.lanpangzi.pojo.Limu;
import com.lanpangzi.pojo.LimuInfomation;
import com.lanpanzi.service.api2.LimuAuthenitcationService;
@Service
public class LimuAuthenitcationServiceImpl implements LimuAuthenitcationService {
	@Autowired
	private LimuMapper limuMapper;
	
	@Override
	public Boolean isHasLimuInfo(Integer uid) {
		return limuMapper.isHasLimuInfo(uid)!=null?true:false;
	}

	@Override
	public Boolean saveLimuTokens(Limu limu,Boolean active) {
		if(active) {
			return limuMapper.updateLimuToken(limu);
		}
		return limuMapper.insertLimuToken(limu);
	}

	@Override
	public List<Limu> getLimuAutheritcation() {
		return limuMapper.getLimuAutheritcationInfo();
	}

	@Override
	public Integer getBypagesCounts() {
		return limuMapper.getBypagesCount();
	}

	@Override
	public Boolean clearByUidLimuTokens(Integer uid) {
		return limuMapper.clearLimuTokens(uid);
	}
	//是否提交过信息给数据库  没有则从limu 接口中去拿
	@Override
	public Boolean isSubmitInfomation(Integer lid) {
		// TODO Auto-generated method stub
		return limuMapper.isSubmitLimuInfomation(lid);
	}

	@Override
	public Boolean insertInfo(LimuInfomation limuInfomation) {
		// TODO Auto-generated method stub
		return limuMapper.insertLimuInfomation(limuInfomation);
	}

	@Override
	public LimuInfomation getLimuinfomation(Integer lid) {
		// TODO Auto-generated method stub
		return limuMapper.getLimuInfomaiton(lid);
	}
	
	
}
