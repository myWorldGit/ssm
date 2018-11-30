package com.lanpangzi.service.impl.bussiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business.LimuMapper;
import com.lanpangzi.pojo.Limu;
import com.lanpanzi.service2.api.LimuAuthenitcationService;
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
	
	
}
