package com.lanpangzi.mapper.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Orders;

public interface OrderMapper {
	/**
	 * 根据uid查询所有的订单    按照状态
	 * @param uid  用户id  
	 * @param cstate 订单状态筛选
	 * @return  所有的订单信息 null待付   1支付    2待发    3待收    4完成
	 */
	public List<Orders> findAllOrdersByUid(@Param("uid")Integer uid,
			@Param("cstate")Integer cstate,@Param("page")Integer page);
	
	/**
	 * 根据oid查看单个详情订单
	 * @param oid
	 * @return
	 */
	public Orders findOrderDetailByOid(Integer oid);
	/**
	 *   创建订单
	 * @param order  订单的信息
	 * @return  是否创建成功
	 */
	public Boolean createOrder(Orders order);
	/**
	 * 根据oid  删除订单信息
	 * @param oid
	 * @return
	 */
	public Boolean deleteOrderByOid(Integer oid);

	
}
