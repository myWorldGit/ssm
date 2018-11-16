package com.lanpangzi.mapper.business2;

import java.util.Date;

import org.apache.ibatis.annotations.Param;


import com.lanpangzi.pojo.Users;

public interface UserAuthenticationMapper {
	/**
	 *		 用户登陆
	 * @return
	 */
	public Users findLogin(@Param("phone")String phone
							,@Param("password")String password);
	/**
	 *	 用户注册
	 * @return
	 */
	public Boolean addRegister(@Param("phone")String phone
							,@Param("password")String password
							,@Param("createTime")Date createtime);
	/**
	 *  查看是否电话号码被注册
	 */
	public String findIsRegister(@Param("phone")String phone);
	
	
	
}
