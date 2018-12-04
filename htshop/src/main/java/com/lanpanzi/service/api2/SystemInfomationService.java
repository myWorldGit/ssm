package com.lanpanzi.service.api2;

import java.util.List;

import com.lanpangzi.pojo.Information;

public interface SystemInfomationService {
	//插入系统消息
	public Boolean insertGeneralSystemInfomation(Information info);
	//删除系统消息
	public Boolean deleteGeneralSystemInfomation(Integer iid);
	//改变已读状态
	public Boolean modifyInfoStateInfomation(Integer iid);
	//获取一个用户的所有消息
	public List<Information> findAllInfomation(Integer uid);
	//详情id
	public Information findDetailInfomationById(Integer iid);
	//未读信息
	public Integer getUnreadInfomationCount(Integer uid);
	//清空该用户的消息
	public Boolean clrearUserAllfomations(Integer uid);
	
}
