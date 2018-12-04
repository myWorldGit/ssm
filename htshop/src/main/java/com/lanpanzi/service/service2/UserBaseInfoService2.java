package com.lanpanzi.service.service2;

import java.util.Map;

import com.lanpangzi.pojo.Usersextend;

public interface UserBaseInfoService2 {
	
	/**
	 *   修改基本信息  如身份证姓名等
	 * @return
	 */
	public Boolean saveBaseInfo(Integer uid,String username,String idcard,String sex);
	public Map<String, String> findBaseInfo(Integer uid);

	/**
	 * 修改个人信息
	 */
	public Boolean savePersonInfo(Integer uid,String address,
						String education,String marriage);
	public Map<String, String> findPersonalInfo(Integer uid);

	/**
	 * 修改工作信息
	 */
	public Boolean saveJobInfo(Integer uid,String job,
			String income,String working);
	public Map<String, String> findJobInfo(Integer uid);

	/**
	 * 修改联系人信息
	 */
	public Boolean saveContactInfo(Integer uid,String contactname,
			String contactphone,String relation);
	public Map<String, String> findContactInfo(Integer uid);

	/**
	 *  用户的身份证
	 */
	public Usersextend findcardImgById(Integer uid);
	public Boolean saveIdcardInfo(Usersextend userex);
	
	public Boolean IsExist(Integer uid);
	public String findUsernameById(Integer uid);
}
