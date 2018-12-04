package com.lanpangzi.mapper.business2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;


public interface Commodiry2Mapper {
	/**
	 * 查询某类商品的4条信息  销量逆序   用做分步
	 * @param tid  商品类id
	 * @return
	 */
	public List<Commodiry> findAllCommodiry(Integer tid);
	/**
	 *   按照商品id查询一个商品对应的详细信息
	 * @param cid  商品id
	 * @return
	 */
	public Commodiry findCommodiryDetails(Integer cid);
	/**
	 * 分页查询一个商品列表
	 * @param tid  商品类型id
	 * @param page   按照销量逆序 获取8个商品
	 * @return
	 */
	public List<Commodiry> findPageCommodiry(@Param("tid")Integer tid ,
				@Param("page") Integer page);
	public List<Destail> getAllDetails(Integer cid);
	//根据did删除详情图片
	public Boolean deleteDetailsById(Integer did);
	
	//两个推荐
	public List<Map<String, String>> getByAmount4Commodiry();  
	public List<Map<String, String>> getByCount2Commodiry();
	public List<Map<String, String>> getBykeywordByCommodirys(
			@Param("keyword") String keyword,@Param("page") Integer page);
	
	
}
