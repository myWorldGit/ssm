package com.lanpangzi.service.impl.bussiness2;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.ClassifyMapper2;
import com.lanpangzi.mapper.business2.Commodiry2Mapper;
import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpanzi.service2.CommodiryService2;

@Service
public class CommodiryService2Impl implements CommodiryService2{
	@Autowired
	private Commodiry2Mapper commodiryDao;
	@Autowired
	private ClassifyMapper2 classifyDao;

	@Override
	public List<Classify> findAllCommodiry() {
		return classifyDao.findAllClassify();
	}
	
	@Override
	public Commodiry findSingleCommodiry(Integer cid) {
		return commodiryDao.findCommodiryDetails(cid);
	}

	@Override
	public List<Map<String, String>> findCommodiryByPages(Integer tid ,Integer page) {
		
		return commodiryDao.findCommodiryByPage(tid, (page-1)*10);
	}


	
}
