package com.lanpangzi.service.impl.bussiness2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business2.AddressCollectionMapper;
import com.lanpangzi.pojo.Address;
import com.lanpanzi.service2.AddressService2;
@Service
public class AddressServiceImpl2 implements AddressService2 {
	@Autowired
	private AddressCollectionMapper addressCollecitonDao;
	
	@Override
	public Boolean updateDefaultAddress(Integer aid, String status) {
		return addressCollecitonDao.updateUndoDefaultAddress(aid, status);
	}

	@Override
	public Boolean deleteAddrById(Integer aid) {
		return addressCollecitonDao.deleteAddressById(aid);
	}

	@Override
	public Boolean insertAddrById(Address addr) {
		return addressCollecitonDao.addAddress(addr);
	}

	@Override
	public Boolean modifyAddress(Address addr) {
		return addressCollecitonDao.updateGeneralAddress(addr);
	}

	@Override
	public Address findAddrById(Integer aid) {
		return addressCollecitonDao.findAddressById(aid);
	}

	@Override
	public List<Address> findAllAddress(Integer uid) {
		return addressCollecitonDao.findAllById(uid);
	}

	@Override
	public Integer findDefaultAddressId(Integer uid) {
		return addressCollecitonDao.findDefaultAddress(uid);
	}

}
