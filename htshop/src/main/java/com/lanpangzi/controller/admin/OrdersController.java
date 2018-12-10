package com.lanpangzi.controller.admin;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.lanpangzi.pojo.Orders;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.ali.AliPayUtils;
import com.lanpanzi.service.api2.OrderService;


@RestController
@RequestMapping("/order")
public class OrdersController {
	@Autowired
	private OrderService orderDao;
	@Autowired
	private AliPayUtils aliPayDao;
	
	@RequestMapping("/changeOrderstate")
	@ResponseBody
	public MobileJsonForm modifyOrderState(Integer oid, Integer cstate, String token) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token); 
		if (uid != null && uid != -1) {
			if (orderDao.modifyStateByOid(cstate, oid) != true) {
				
				form.setCodeAndMessage("2", "database fail");
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;

	}
	
	@ResponseBody
	@RequestMapping(value="/delOrder",method=RequestMethod.POST)
	public MobileJsonForm deleteOrderById(String token,Integer oid) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid != null && uid !=-1) {
			Boolean flag = orderDao.deleteOrderByOid(oid);
			if(flag==true) {
				form.setCodeAndMessage("1", "success");
				return form;
			}else {
				form.setCodeAndMessage("2", "数据库已无该数据");
				return form;
			}
		}
		
		form.setCodeAndMessage("2", "token异常");
		return form;
	}
	
	@ResponseBody
	@RequestMapping(value="/findOrder",method=RequestMethod.POST)
	public MobileJsonForm findDetailOrderById(String token,Integer oid,HttpServletRequest
			request) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid != null && uid !=-1) {
			Orders order = orderDao.findOrderDetailByOid(oid);
			order.setPhoto(request.getRequestURL().toString()
					.replace("/order/allOrder", "")+CommodiryAdminController.C_IMG_DIR+order.getPhoto());
			form.setCodeAndMessage("1", "success");
			form.addData("order", order);
			return form;
			
		}
		
		form.setCodeAndMessage("2", "token异常");
		return form;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/aliPayOrder",method=RequestMethod.POST)
	public MobileJsonForm createOrderById(String token,Orders order,
			HttpServletRequest request) {  
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid !=-1) {
			
			if(orderDao.createOrder(order)!=true) {
				form.setCodeAndMessage("2", "db异常");
				return form;
			}  
			System.out.println(order.getOid());
			String notifyurl = request.getRequestURL().toString().replace("/order/aliPayOrder", "")+
					"/transfer/aliPayNotify";
			String res = aliPayDao.alipayInterface(order.getCname(),
					String.valueOf(order.getPrice()), String.valueOf(order.getOid()),
					notifyurl);
			form.addData("payinfo", res);
			form.setCodeAndMessage("1", "success");
			return form; 
		}
		      
		form.setCodeAndMessage("2", "token异常");
		return form;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/allOrder",method=RequestMethod.POST)
	public MobileJsonForm findAllOrdersByUid(String token,Integer page,
			HttpServletRequest request) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid !=-1) {
			String prefix = request.getRequestURL().toString()
					.replace("/order/allOrder", "")+CommodiryAdminController.C_IMG_DIR;
			String state = request.getParameter("state");
			List <Orders> list = new ArrayList<Orders>();
			if(state!=null && !state.equals("")) {	
				list = orderDao.findAllOrdersByUid(uid, Integer.parseInt(state),page);
			}else {
				list = orderDao.findAllOrdersByUid(uid, null,page);
			}
			for(Orders order : list) {
				order.setPhoto(prefix+order.getPhoto());
			}
			form.addData("orders", list);
			form.setCodeAndMessage("1", "success");
			return form;
		}
		
		form.setCodeAndMessage("2", "token异常");
		return form;
	}

}
