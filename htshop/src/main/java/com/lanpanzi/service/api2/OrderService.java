package com.lanpanzi.service.api2;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.lanpangzi.pojo.Orders;

public interface OrderService {
	public List<Orders> findAllOrdersByUid(@Param("uid")Integer uid,
			@Param("cstate")Integer cstate,@Param("page")Integer page);
	public Orders findOrderDetailByOid(Integer oid);
	public Boolean createOrder(Orders order);
	public Boolean deleteOrderByOid(Integer oid);
	public List<Map<String,String>> findreadryExpress(Integer page);
	public Boolean modifyStateByOid(@Param("cstate") Integer  cstate ,
			@Param("oid")Integer oid);
	public Integer findreadryExpressCount();
	public Boolean updateExpressAndCompany(Integer oid, String express, String company);

}
