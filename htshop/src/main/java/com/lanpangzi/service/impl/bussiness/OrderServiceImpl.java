package com.lanpangzi.service.impl.bussiness;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanpangzi.mapper.business.OrderMapper;
import com.lanpangzi.pojo.Orders;
import com.lanpanzi.service.api2.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private OrderMapper  ordersDao;
	
	
	@Override
	public List<Orders> findAllOrdersByUid(Integer uid, Integer cstate) {
		// TODO Auto-generated method stub
		return ordersDao.findAllOrdersByUid(uid, cstate);
	}

	@Override
	public Orders findOrderDetailByOid(Integer oid) {
		// TODO Auto-generated method stub
		return ordersDao.findOrderDetailByOid(oid);
	}

	@Override
	public Boolean createOrder(Orders order) {
		// TODO Auto-generated method stub
		order.setOrdertime(new Date(System.currentTimeMillis()));
		return ordersDao.createOrder(order);
	}

	@Override
	public Boolean deleteOrderByOid(Integer oid) {
		// TODO Auto-generated method stub
		return ordersDao.deleteOrderByOid(oid);
	}

}
