package com.lanpangzi.mapper.business2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Other;
/**
 *    为方便起见  此处不做详细处理   采用批量删除
 * @author 帅气的老胡
 *
 */
public interface OtherInfomationMapper {
	public Integer getprevInsertID();
	/**
	 * 根据类型查询其他信息
	 * @param types  类型名
	 * @return
	 */
	public List<Other> findOtherInfoByType(@Param("types")String types);
	/**
	 * 根据id删除其他的信息
	 */
	public Boolean deleteOtherInfoById(@Param("oid")Integer oid);
	/**
	 * 根据类型删除批量其他的信息
	 */
	public Boolean deleteOtherInfoByType(@Param("types")String types);
	/**
	 * 修改表属性
	 */
	public Boolean updateOtherInfoById(@Param("oid")Integer oid
			,@Param("value")String value);
	/**
	 * 插入表属性
	 */
	public Boolean insertOtherInfoById(@Param("other")Other other);
	/**
	 * 查询单个  逆序
	 */
	public String findSingleInfo(@Param("types")String types);
	public String findvalueById(Integer oid);
	
}
