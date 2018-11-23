package com.lanpanzi.service2;

import java.util.List;

import com.lanpangzi.pojo.Other;

public interface OtherInfomationService2 {
	
	public String findSingleInfo(String key);
	/**
	 * 更具key删除内容
	 * @param key
	 * @return
	 */
	public Boolean clearByKeyAllInfo(String key);
	/**
	 * 根据key查询list
	 * @param key
	 * @return
	 */
	public List<Other> findValuesByKey(String key);

	public Boolean updateValueById(Integer oid ,String value);
	
	public Integer insertOtherInfo(Other other);
	
	public Boolean deleteValueById(Integer oid);
	public String findOtherById(Integer oid);
}
