package com.lanpanzi.service.api2;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Orders;

public interface OrderService {
	public List<Orders> findAllOrdersByUid(@Param("uid")Integer uid,
			@Param("cstate")Integer cstate);
	public Orders findOrderDetailByOid(Integer oid);
	public Boolean createOrder(Orders order);
	public Boolean deleteOrderByOid(Integer oid);

}
