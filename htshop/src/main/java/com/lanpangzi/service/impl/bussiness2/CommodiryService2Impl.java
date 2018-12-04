package com.lanpangzi.service.impl.bussiness2;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business.LimuMapper;
import com.lanpangzi.mapper.business2.ClassifyMapper2;
import com.lanpangzi.mapper.business2.Commodiry2Mapper;
import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;
import com.lanpanzi.service.service2.CommodiryService2;

@Service
public class CommodiryService2Impl implements CommodiryService2{
	@Autowired
	private Commodiry2Mapper commodiryDao;
	@Autowired
	private ClassifyMapper2 classifyDao;
	@Autowired
	private LimuMapper limuDao;
	
	/**
	 * 查询所有的 商品 分类  其中包含4个商品
	 */
	@Override
	public List<Classify> getShowAllCommodiry() {
		return classifyDao.findAllClassify();
	}
	/**
	 * 查询详情商品 by   ID
	 */
	@Override
	public Commodiry getCommodiryDetailsById(Integer cid) {
		
		return commodiryDao.findCommodiryDetails(cid);
	}
	@Override
	public List<Commodiry> getCommodiryByPage(Integer tid, Integer page) {
		
		return commodiryDao.findPageCommodiry(tid, page*8);
	}
	@Override
	public List<Destail> findAllDetails(Integer cid) {
		// TODO Auto-generated method stub
		return commodiryDao.getAllDetails(cid);
	}
	@Override
	public Boolean deleteDetailsById(Integer did) {
		return commodiryDao.deleteDetailsById(did);
	}
	@Override
	public List<Map<String, String>> getByAmount4Commodiry() {
		return commodiryDao.getByAmount4Commodiry();
	}
	@Override
	public List<Map<String, String>> getByCount2Commodiry() {
		// TODO Auto-generated method stub
		return commodiryDao.getByCount2Commodiry();
	}
	@Override
	public Integer getUserMaxAmount(Integer uid) {
		// TODO Auto-generated method stub
		return limuDao.getUserMaxAmount(uid);
	}
	@Override
	public List<Map<String, String>> findBykeywordByCommodirys(String keyword, Integer page) {
		return commodiryDao.getBykeywordByCommodirys(keyword,page*8);
	}

}
