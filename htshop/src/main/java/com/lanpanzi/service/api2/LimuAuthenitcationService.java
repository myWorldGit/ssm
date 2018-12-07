package com.lanpanzi.service.api2;

import java.util.List;

import com.lanpangzi.pojo.Limu;
import com.lanpangzi.pojo.LimuInfomation;

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

	public Boolean isSubmitInfomation(Integer lid);

	public Boolean insertInfo(LimuInfomation limuInfomation);

	public LimuInfomation getLimuinfomation(Integer lid);
	
}
