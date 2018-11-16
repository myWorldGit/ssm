package com.lanpanzi.service2;

import java.util.List;
import java.util.Map;

import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;

public interface CommodiryService2 {
	/**
	 * 查出商品浏览的页面  每个产品4条     数量逆序
	 * @return
	 */
	public List<Classify> findAllCommodiry();
	
	/**
	 *  查找单个详细信息
	 */
	public Commodiry findSingleCommodiry(Integer cid);
	/**
	 * 分页
	 */
	public List<Map<String,String>>findCommodiryByPages(Integer tid ,Integer page);
}
