package com.lanpangzi.mapper.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;

public interface CommodiryAdminMapper {
	public List<Classify>findAllClassify();

	public Boolean  addCommodiry(@Param("c")Commodiry commodiry);

	public Integer prevInesrtId();
	
	//删除商品  先删除详情约束
	public Boolean deleteCommodiryById(Integer cid);
	public Boolean deleteDetailsByCid(Integer cid);

	public Boolean updateCommodiry(Commodiry commodiry);

	public Boolean insertDetails(Destail detail);

	public Boolean updateDetailById(Destail detail);
}
