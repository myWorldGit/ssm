package com.lanpanzi.service.service2;

import java.util.List;
import java.util.Map;

import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;

public interface CommodiryService2 {
	public List<Classify> getShowAllCommodiry();
	
	
	public Commodiry getCommodiryDetailsById(Integer cid);
	
	
	public List<Commodiry> getCommodiryByPage(Integer tid,Integer page);
	
	public List<Destail> findAllDetails(Integer cid);
	
	
	public Boolean deleteDetailsById(Integer did);


	public List<Map<String,String>> getByAmount4Commodiry();


	public List<Map<String,String>> getByCount2Commodiry();


	public Integer getUserMaxAmount(Integer uid);


	public List<Map<String, String>> findBykeywordByCommodirys(String keyword, Integer page);
}
