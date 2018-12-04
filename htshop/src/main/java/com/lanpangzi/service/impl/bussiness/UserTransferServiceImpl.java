package com.lanpangzi.service.impl.bussiness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.lanpangzi.mapper.business.UserTransferMapper;
import com.lanpangzi.pojo.Limu;
import com.lanpanzi.service.api2.UserTransferService;
@Service
public class UserTransferServiceImpl  implements UserTransferService{
	
	@Autowired 
	private UserTransferMapper transferDao;
	
	@Transactional(rollbackFor=Exception.class)  
	@Override
	public Boolean saveTransferAmount (Limu limu)  throws Exception {
		if(this.hasInTable(limu.getLid())) {
			return transferDao.updateTransferFlag(limu);
		}
		return transferDao.insertTransferFlag(limu);	
	}     

	@Override
	public Boolean hasInTable(Integer uid) {
		return transferDao.hasUserInTable(uid)==null?false:true;
	}
	
}
