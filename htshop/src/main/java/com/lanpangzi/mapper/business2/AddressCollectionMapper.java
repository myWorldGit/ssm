package com.lanpangzi.mapper.business2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Address;

public interface AddressCollectionMapper {
	//查询所有的收藏地址
	public List<Address> findAllById(@Param("uid") Integer uid);
	//找到默认地址  默认地址字段标记为1 返回他的aid
	public Integer findDefaultAddress(@Param("uid")Integer uid);
	//查找单个地址回显
	public Address findAddressById(@Param("aid")Integer aid);
	//用户添加一个地址
	public Boolean addAddress(@Param("addr") Address addr);
	//删除地址 
	public Boolean deleteAddressById(@Param("aid")Integer aid);
	//修改地址
	public Boolean updateGeneralAddress(@Param("addr")Address addr);
	//修改默认地址   1默认  0  取消
	public Boolean updateUndoDefaultAddress(@Param("aid")Integer aid,
			@Param("flag")String flag);
}
