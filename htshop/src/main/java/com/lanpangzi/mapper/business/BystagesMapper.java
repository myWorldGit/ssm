package com.lanpangzi.mapper.business;

import java.util.List;

import com.lanpangzi.pojo.Bystages;

public interface BystagesMapper {
	
	/**
	 * 查询所有分期类型
	 * @return
	 */
	public List<Bystages> findAllBystages();
	/**
	 * id查分期类型
	 * @param sid
	 * @return
	 */
	public Bystages findBystagesById(Integer sid);
	/**
	 * 添加一个
	 * @param bystages
	 * @return
	 */
	public Boolean addBystages(Bystages bystages);
	/**
	 * 清空
	 * @return
	 */
	public Boolean clearBystages();
	/**
	 * 删除
	 * @param sid
	 * @return
	 */
	public Boolean deleteBystages(Integer sid);
	/**
	 * 修改
	 * @param bystages
	 * @return
	 */
	public Boolean updateBystages(Bystages bystages);
	


	
	
}
