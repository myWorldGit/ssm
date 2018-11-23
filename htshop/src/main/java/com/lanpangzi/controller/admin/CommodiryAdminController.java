package com.lanpangzi.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpanzi.service2.CommodiryService2;

@Controller
@RequestMapping("/adminCommodiry")
public class CommodiryAdminController {
	@Autowired
	private CommodiryService2 commodiryAdminDao;
	
	@RequestMapping(value="/getinit",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getInitPage(Integer tid, Integer page) {
		MobileJsonForm form = new MobileJsonForm();
		form.addData("commodirys", commodiryAdminDao.getCommodiryByPage(tid, page));
		form.setCodeAndMessage("1","success");
		return form;
	}
	
	
	//查找单个商品回显
	@RequestMapping(value="/findSingle",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getSingleCommodiry(Integer cid) {
		
		MobileJsonForm form = new MobileJsonForm();
		form.addData("commodiry", commodiryAdminDao.getCommodiryDetailsById(cid));
		form.setCodeAndMessage("1","success");
		return form;
	}
	

	//查找所有详情图片
	@RequestMapping(value="/findDetails",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getDetailsImgCommodiry(Integer cid) {
		MobileJsonForm form = new MobileJsonForm();
		form.addData("commodiry", commodiryAdminDao.findAllDetails(cid));
		form.setCodeAndMessage("1","success");
		return form;
	}
	//删除单个详情图片
	@RequestMapping(value="/delDetails",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm deleteDetailsImgCommodiry(Integer did) {
		MobileJsonForm form = new MobileJsonForm();
		System.out.println(did);
		Boolean flag=commodiryAdminDao.deleteDetailsById(did);
		if(flag == false) {
			form.setCodeAndMessage("2","exception");
			return form;
		}
		form.setCodeAndMessage("1","success");
		return form;
	}
		
}
