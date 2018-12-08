package com.lanpangzi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.utils.MobileJsonForm;
import com.lanpanzi.service.api2.OrderService;

@Controller
@RequestMapping("adminfirst")
public class FirstPageAdminController {

	@Autowired
	private OrderService orderServiceDao;

	@RequestMapping("/initPage")
	@ResponseBody
	public MobileJsonForm getInitPage(Integer page) {
		MobileJsonForm form = new MobileJsonForm();
		try {
			form.addData("ordersum", orderServiceDao.findreadryExpressCount());
			form.addData("order", orderServiceDao.findreadryExpress(page));
		} catch (Exception e) {
			e.printStackTrace();
			form.setCodeAndMessage("2", "database fail");
			return form;
		}

		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	@RequestMapping("/byPage")
	@ResponseBody
	public MobileJsonForm getListOrderByPage(Integer page) {
		MobileJsonForm form = new MobileJsonForm();
		try {
			form.addData("order", orderServiceDao.findreadryExpress(page));
		} catch (Exception e) {
			e.printStackTrace();
			form.setCodeAndMessage("2", "database fail");
			return form;
		}

		form.setCodeAndMessage("1", "success");
		return form;
	}
	

	
	//admin 已发快递标记
	@RequestMapping("/alreadyPost")
	@ResponseBody
	public MobileJsonForm modifyOrderState(Integer oid) {
		MobileJsonForm form = new MobileJsonForm();
		if (orderServiceDao.modifyStateByOid(2, oid) != true) {
			form.setCodeAndMessage("2", "database fail");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;

	}
	
	
	@RequestMapping("/setExpress")
	@ResponseBody
	public MobileJsonForm updateExpressAndCompany(Integer oid,String express,String company) {
		MobileJsonForm form = new MobileJsonForm();
		if (orderServiceDao.updateExpressAndCompany(oid,express,company) != true) {
			form.setCodeAndMessage("2", "database fail");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
		
	}
}
