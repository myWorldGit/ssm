package com.lanpanzi.service.api2;


import com.lanpangzi.pojo.Limu;
public interface UserTransferService {
	public Boolean saveTransferAmount(Limu limu) throws Exception;
	public Boolean hasInTable(Integer uid);
}
