package com.lanpanzi.service2;

import java.util.List;

import com.lanpangzi.pojo.Address;

public interface AddressService2 {
	
	
	public Integer findDefaultAddressId(Integer uid);
	/**
	 * 更改默认地址   
	 * @status  状态
	 */
	public Boolean updateDefaultAddress(Integer aid,String status);
	/**
	 * 删除一个地址
	 */
	public Boolean deleteAddrById(Integer aid);
	/**
	 * 插入一个地址
	 */
	public Boolean insertAddrById(Address addr);
	/**
	 * 修改单个地址
	 */
	public Boolean modifyAddress(Address addr);
	/**
	 * 单查一个地址
	 */
	public Address findAddrById(Integer aid);
	/**
	 * 查找所有的收藏地址
	 */
	public List<Address>findAllAddress(Integer uid);

}
