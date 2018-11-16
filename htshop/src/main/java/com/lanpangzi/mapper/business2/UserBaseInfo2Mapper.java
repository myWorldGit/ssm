package com.lanpangzi.mapper.business2;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Usersextend;

public interface UserBaseInfo2Mapper {
	/**
	 * 是否第一次存储信息
	 */
	public Boolean isFirstUserEx(Integer uid);
	/**
	 * 第一次储存
	 */
	public Boolean insertBaseUserInfo (@Param("uid") Integer uid
			,@Param("idcard")String idcard
			,@Param("sex")String sex);
	/**
	 * 跟新基本信息 
	 */
	public Boolean updateBaseUserInfo (@Param("uid") Integer uid
			,@Param("idcard")String idcard
			,@Param("sex")String sex);
	public Usersextend findBaseUserInfo(@Param("uid") Integer uid);
	/**
	 * 因为不在同一个表所以只能分出一条语句
	 */
	public String findBaseUsername(@Param("uid") Integer uid);
	
	public Boolean updateBaseUsername(@Param("uid") Integer uid,
			@Param("username")String username);

	/*******************personal**************************/
	public Usersextend findPersonalInfo(@Param("uid") Integer uid);
	
	
	public Boolean updatePersonalInfo (@Param("uid") Integer uid
			,@Param("address")String address
			,@Param("education")String education
			,@Param("marriage")String marriage);
	public Boolean insertPersonalInfo (@Param("uid") Integer uid
			,@Param("address")String address
			,@Param("education")String education
			,@Param("marriage")String marriage);
	/*******************job**************************/
	public Usersextend findJobInfo(@Param("uid") Integer uid);

	
	public Boolean updateJobInfo(@Param("uid") Integer uid
			,@Param("job")String job
			,@Param("income")String income
			,@Param("working")String working);
	public Boolean insertJobInfo(@Param("uid") Integer uid
			,@Param("job")String job
			,@Param("income")String income
			,@Param("working")String working);
	/*******************contact**************************/
	public Usersextend findContactInfo(@Param("uid") Integer uid);

	
	public Boolean updateContactInfo(@Param("uid") Integer uid
			,@Param("contactname")String contactname
			,@Param("contactphone")String contactphone
			,@Param("relation")String relation);
	public Boolean insertContactInfo(@Param("uid") Integer uid
			,@Param("contactname")String contactname
			,@Param("contactphone")String contactphone
			,@Param("relation")String relation);
	/*********************************idcard**************/
	public Usersextend findIdcardImg(@Param("uid")Integer uid);
	public Boolean insertIdcardImg(@Param("userex") Usersextend userex);
	public Boolean updateIdCardImg(@Param("userex") Usersextend userex);
}
