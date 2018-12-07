package com.lanpangzi.mapper.business;

import java.util.List;

import com.lanpangzi.pojo.Limu;
import com.lanpangzi.pojo.LimuInfomation;

public interface LimuMapper {
	/**
	 * 根据token查看是否有付过定金   且存在token有值    分页10条
	 */
	public List<Limu> getLimuAutheritcationInfo();
	//查询分页总数量
	public Integer getBypagesCount();
	/**
	   通过  或者不通过清空token
	 */
	public Boolean clearLimuTokens(Integer uid);
	/**
	 * 
	 */
	public Boolean insertLimuToken(Limu limu);
	/**
	 * 
	 * @param limu
	 * @return
	 */
	public Boolean updateLimuToken(Limu limu);
	
	public Boolean isHasLimuInfo(Integer uid);
	
	//获取立木申请的最大额度
	public Integer getUserMaxAmount(Integer uid);
	//判断是否在limu中拿过消息
	public Boolean isSubmitLimuInfomation(Integer lid);
	public Boolean insertLimuInfomation(LimuInfomation limuInfomation);
	public LimuInfomation getLimuInfomaiton(Integer lid);
}
