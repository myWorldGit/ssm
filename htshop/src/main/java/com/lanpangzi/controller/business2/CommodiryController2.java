package com.lanpangzi.controller.business2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpanzi.service2.CommodiryService2;

@Controller
@RequestMapping("/commodiry")
public class CommodiryController2 {
	@Autowired
	private CommodiryService2 commodiryDao;

	@RequestMapping(value="showAll",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm showAllCommodiry(@RequestParam("token")String token) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid =  TokenUtil.getAppUID(token);
		if(uid!= null && uid!=-1) {
			form.addData("commodirys", commodiryDao.findAllCommodiry());
			form.setCodeAndMessage("1", "查询成功");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
		
	}
	@RequestMapping(value="detailbyid",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm detailCommodiry(@RequestParam("token")String token,
			@RequestParam("cid")Integer cid) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid =  TokenUtil.getAppUID(token);
		if(uid!= null && uid!=-1) {
			form.addData("commodiry", commodiryDao.findSingleCommodiry(cid));
			form.setCodeAndMessage("1", "查询成功");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
		
	}
	@RequestMapping(value="bypage",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findCommodiryByPages(@RequestParam("token")String token,
			@RequestParam("tid")Integer tid,
			@RequestParam("page")Integer page) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid =  TokenUtil.getAppUID(token);
		if(uid!= null && uid!=-1) {
			form.addData("commodirys", commodiryDao.findCommodiryByPages(tid, page));
			form.setCodeAndMessage("1", "查询成功");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
		
	}
	
}
