package com.lanpangzi.mapper.business2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Commodiry;


public interface Commodiry2Mapper {
	/**
	 * 通过种类id筛选  销量排序逆序
	 */
	public List<Map<String,String>> findAllCommodiry(Integer tid);
	/**
	 * 根据商品id查询单个 详情    分步对应多个颜色
	 */
	public Commodiry findCommodiryDetails(Integer cid);
	/**
	 * 分页一次十条条  
	 */
	public List<Map<String,String>> findCommodiryByPage(@Param("tid") Integer tid,
			@Param("page")Integer begin);

}
