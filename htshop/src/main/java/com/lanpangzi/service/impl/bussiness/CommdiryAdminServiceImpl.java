package com.lanpangzi.service.impl.bussiness;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business.CommodiryAdminMapper;
import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;
import com.lanpanzi.service2.api.CommodiryAdminSerivce;
@Service
public class CommdiryAdminServiceImpl implements CommodiryAdminSerivce{
	@Autowired
	private CommodiryAdminMapper commodiryAdminDao;
	
	@Override
	public List<Classify> findAllClassify() {
		return commodiryAdminDao.findAllClassify();
	}

	@Override
	public Integer addCommodiry(Commodiry commodiry) {
		if(commodiryAdminDao.addCommodiry(commodiry)==true)
			return commodiryAdminDao.prevInesrtId();
		return null;
	}

	@Override
	public Boolean deleteCommodiryById(Integer cid) {
		commodiryAdminDao.deleteDetailsByCid(cid);
		
		return 	commodiryAdminDao.deleteCommodiryById(cid);
	
	}

	@Override
	public Boolean updateCommodiry(Commodiry commodiry) {
		return commodiryAdminDao.updateCommodiry(commodiry);
	}

	@Override
	public Integer insertDetails(Destail detail) {
		if(commodiryAdminDao.insertDetails(detail)==true)
			return commodiryAdminDao.prevInesrtId();
		return null;
	}

	@Override
	public Boolean updateDetailById(Destail detail) {
		return commodiryAdminDao.updateDetailById(detail);
	}

	@Override
	public List<Commodiry> conditionBykeyWord(Integer tid ,String keyword,Integer page) {
		// TODO Auto-generated method stub
		return commodiryAdminDao.findBykeyword(tid,keyword,page);
	}

	@Override
	public Integer getCommodiryByPageCount(Integer tid) {
		return commodiryAdminDao.getCommodiryByPageCount(tid);
	}
	@Override
	public Integer getCommodiryKeywordByPageCount(Integer tid,String keyword) {
		return commodiryAdminDao.getCommodiryKeywordByPageCount(tid,keyword);
	}

	@Override
	public List<String> findColorArray(Integer cid) {
		String colors = commodiryAdminDao.findCommodryColors(cid);	
		
		return Arrays.asList(colors.split(";"));
	}

	@Override
	public Boolean modifyCommodiryColors(String color, Integer cid) {
		
		return commodiryAdminDao.modifyCommodiryColors(color,cid);
	}


}
