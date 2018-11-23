package com.lanpanzi.service2;

import java.util.List;

import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;

public interface CommodiryService2 {
	public List<Classify> getShowAllCommodiry();
	
	
	public Commodiry getCommodiryDetailsById(Integer cid);
	
	
	public List<Commodiry> getCommodiryByPage(Integer tid,Integer page);
	
	public List<Destail> findAllDetails(Integer cid);
	
	
	public Boolean deleteDetailsById(Integer did);
}
