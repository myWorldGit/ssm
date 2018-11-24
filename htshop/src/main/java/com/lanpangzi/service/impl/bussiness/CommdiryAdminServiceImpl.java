package com.lanpangzi.service.impl.bussiness;

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

}
