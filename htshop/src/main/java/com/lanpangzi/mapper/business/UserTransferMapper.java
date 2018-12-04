package com.lanpangzi.mapper.business;

import com.lanpangzi.pojo.Limu;

public interface UserTransferMapper {
	public Boolean updateTransferFlag(Limu limu);
	public Boolean insertTransferFlag(Limu limu);
	
	
	public Boolean hasUserInTable(Integer uid);
}
