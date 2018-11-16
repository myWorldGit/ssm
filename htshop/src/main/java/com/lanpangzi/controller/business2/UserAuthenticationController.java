package com.lanpangzi.controller.business2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.pojo.Users;
import com.lanpangzi.utils.AliMessageSend;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpanzi.service2.UserAuthenticationService;


@RequestMapping("/userAuthentication")
@Controller
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationService userAuthenticationDao;
	/**
  	* 	登陆认证
  	* @param phone
  	* @param password
  	* @return
  	*/	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public MobileJsonForm userLogin(@RequestParam("phone")String phone,
			@RequestParam("password")String password) {
		System.out.println(phone+password);
		Users user =userAuthenticationDao.findLoginAuthentication(phone, password);
		if(user==null) {
			return new MobileJsonForm("0","用户名密码不正确");
		}else {
			MobileJsonForm form = new MobileJsonForm("1");
			System.out.println(user.toString());
			form.addData("user", user);
			form.addData("token",TokenUtil.createToken(user.getUid()));			
			return form;
		}
	}
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public MobileJsonForm userRegister(@RequestParam("phone")String phone,
			@RequestParam("password") String password) {
		String isRegister =userAuthenticationDao.findIsRegisterAuthentication(phone);
		if(isRegister==null && phone.length()==11) {			
			userAuthenticationDao.addRegisterAuthentication(phone, password);
			MobileJsonForm form = new MobileJsonForm("1");
			form.setMessage("成功注册");
			return form;
		}else {
			return new MobileJsonForm("0","手机号已经被注册");
		}
		
	}
	/**
	  *   将验证码给redis
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/Veritycode",method=RequestMethod.POST)
	public MobileJsonForm getVerityCode(@RequestParam("phone")String phone) {
		//生成随机验证码
		String isRegister =userAuthenticationDao.findIsRegisterAuthentication(phone);
		MobileJsonForm form =new MobileJsonForm();

		if(isRegister!=null) {
			form.setCodeAndMessage("0", "此号码已被占用");
			return form;
		}
		String random =System.currentTimeMillis()+"";
		String code =  random.substring(random.length()-5);

		AliMessageSend.sendSms(phone, code);
		form.setCodeAndMessage("1", "此号码可用");
		form.addData("verityCode", code);
		return form;
		
	}
	
	
	
}
