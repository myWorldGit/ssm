package com.lanpanzi.service2.api;

import java.util.List;

import com.lanpangzi.pojo.Limu;

public interface LimuAuthenitcationService {
	
	public Boolean isHasLimuInfo(Integer uid);
	
	public Boolean saveLimuTokens(Limu limu,Boolean active);
	
	//获取分页的信息
	public List<Limu> getLimuAutheritcation();
	//查询分页总数量
	public Integer getBypagesCounts();
	/**
	   通过  或者不通过清空token
	 */
	public Boolean clearByUidLimuTokens(Integer uid);
	
}
