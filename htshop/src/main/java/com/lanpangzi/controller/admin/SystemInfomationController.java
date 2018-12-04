package com.lanpangzi.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpanzi.service.api2.SystemInfomationService;

@Controller
@RequestMapping("/infomation")
public class SystemInfomationController {
	@Autowired
	private SystemInfomationService systemInfoDao;
	
	@RequestMapping(value="delinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm deleteInfomation(String token,Integer iid) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid!=-1) {
			if(systemInfoDao.deleteGeneralSystemInfomation(iid)!=true) {
				form.setCodeAndMessage("2", "database exception");
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	@RequestMapping(value="getinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getInfomation(String token,Integer iid) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid!=-1) {
			form.addData("infomation", systemInfoDao.findDetailInfomationById(iid));
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	@RequestMapping(value="read",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm falgAlreadyRead(String token,Integer iid) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid!=-1) {
			if(systemInfoDao.modifyInfoStateInfomation(iid)!=true) {
				form.setCodeAndMessage("2", "database exception");
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	
	@RequestMapping(value="allinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAllInfomations(String token) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid!=-1) {
			form.addData("infomations", systemInfoDao.findAllInfomation(uid));
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	

	@RequestMapping(value="clearinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm clearInfomations(String token) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid!=-1) {
			if(systemInfoDao.clrearUserAllfomations(uid)!=true) {
				form.setCodeAndMessage("2", "database exception");
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
}
