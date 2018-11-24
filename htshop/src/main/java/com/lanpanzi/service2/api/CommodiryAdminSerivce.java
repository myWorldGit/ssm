package com.lanpanzi.service2.api;

import java.util.List;

import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;

public interface CommodiryAdminSerivce {
	public List<Classify>findAllClassify();
	
	//插入返回id
	public Integer addCommodiry(Commodiry commodiry);
	//删除商品  按照id  同时还会删除所有的外键约束
	
	public Boolean deleteCommodiryById(Integer cid);

	public Boolean updateCommodiry(Commodiry commodiry);

	public Integer insertDetails(Destail detail);

	public Boolean updateDetailById(Destail detail);

}
