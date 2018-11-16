package com.lanpangzi.controller.business2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.pojo.Address;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpanzi.service2.AddressService2;

@Controller
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService2 addressDao;
	
	@RequestMapping(value="findbyAll",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAddressByAll(@RequestParam("token") String token) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid != null && uid != -1) {
			form.addData("address", addressDao.findAllAddress(uid));
			form.setCode("1");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	
	@RequestMapping(value="findbyId",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAddressById(@RequestParam("token") String token,
			@RequestParam("aid") Integer aid) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid != null && uid != -1) {
			
			form.addData("address", addressDao.findAddrById(aid));
			form.setCode("1");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	
	@RequestMapping(value="addbyId",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm addAddress(@RequestParam("token") String token,
			Address addr) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid!=null && uid != -1) {
			addr.setUid(uid);
			Integer oldId =null ;
			if(addressDao.findDefaultAddressId(uid)==null) {
				addr.setIsDefault("1");
			}else {
				oldId = addressDao.findDefaultAddressId(uid);
			}
			Boolean flag =addressDao.insertAddrById(addr);
			//如果没有默认地址  则首次添加的变成默认地址
			addressDao.updateDefaultAddress(oldId, "0");
			
			if(flag!=true) {
				form.setCodeAndMessage("2","数据库异常  数据不存在");
				return form;
			}
			form.setCodeAndMessage("1","success");
			return form;
		}
		form.setCodeAndMessage("0", "tonken失效");
		return form;
	}
	
	@RequestMapping(value="deletebyId",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm deleteAddressById(@RequestParam("token") String token,
			@RequestParam("aid") Integer aid ) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid!=null && uid != -1) {
		
			Boolean flag =addressDao.deleteAddrById(aid);
			if(flag==false) {
				form.setCodeAndMessage("2","数据库异常  数据不存在");
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("0", "tonken失效");
		return form;
	}
	
	
	
	@RequestMapping(value="defaultStatus",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyDefaultStatus(@RequestParam("token") String token,
			@RequestParam("aid") Integer aid) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid!=null && uid != -1) {
			//默认地址
			
			Integer oldId = addressDao.findDefaultAddressId(uid);
		
			Boolean flag =addressDao.updateDefaultAddress(aid, "1");
			if(flag==false) {
				form.setCodeAndMessage("2","数据库操作异常");
				return form;
			}
			if(oldId!=null) {
				addressDao.updateDefaultAddress(oldId, "0");
			}
			
			form.setCodeAndMessage("1","success");
			return form;
		}
		form.setCodeAndMessage("0", "tonken失效");
		return form;
	}
	
	@RequestMapping(value="updatebyid",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyAddressById(@RequestParam("token") String token,
					Address addr) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid!=null && uid != -1) {
			String status = addr.getIsDefault();
			Integer oldId =null;
			if(status!=null && status.equals("1")) {
				oldId = addressDao.findDefaultAddressId(uid);
			}
			//默认地址
			Boolean flag =addressDao.modifyAddress(addr);
			if(flag==false) {
				form.setCodeAndMessage("2","数据库操作异常");
				return form;
			}
			if(oldId!=null) {
				addressDao.updateDefaultAddress(oldId, "0");
			}
			form.setCodeAndMessage("1","success");
			return form;
		}
		form.setCodeAndMessage("0", "tonken失效");
		return form;
	}
	
	
	
	
	
	
}
