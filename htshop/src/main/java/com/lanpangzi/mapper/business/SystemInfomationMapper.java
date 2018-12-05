package com.lanpangzi.mapper.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Information;

public interface SystemInfomationMapper {
	//插入系统消息
	public Boolean insertGeneralSystemInfo(Information info);
	//删除系统消息
	public Boolean deleteGeneralSystemInfo(Integer iid);
	//改变已读状态
	public Boolean modifyInfoState(Integer iid);
	//获取一个用户的所有消息
	public List<Information> findAllInfomation(@Param("uid")Integer uid,@Param("page") Integer page);
	//详情id
	public Information findDetailInfomationById(Integer iid);
	//未读信息
	public Integer getUnreadInfoCount(Integer uid);
	public Boolean clearUserfomationsById(Integer uid);
}
