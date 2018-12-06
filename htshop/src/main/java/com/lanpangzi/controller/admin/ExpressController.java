package com.lanpangzi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.WX.KdniaoTrackQueryAPI;
import com.lanpanzi.service.api2.OrderService;

@Controller
@RequestMapping("/express")
public class ExpressController {
	@Autowired
	KdniaoTrackQueryAPI kdniaoDao;
	@Autowired
	OrderService orderDao;
	
	@RequestMapping(value="/findexp",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findExpressByProductNumber(String express,String company,
			String token) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid !=-1 ) {
			try {
				
				form.addData("express",kdniaoDao.getOrderTracesByJson(company, express));
				form.setCodeAndMessage("1", "success");
				return form;
			} catch (Exception e) {
				e.printStackTrace();
				form.setCodeAndMessage("2", "search exception");
				return form;
			}
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
}
