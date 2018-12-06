package com.lanpangzi.controller.business2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.pojo.Users;
import com.lanpangzi.utils.AliMessageSend;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.UploadUtils;
import com.lanpangzi.utils.WX.MD5Util;
import com.lanpanzi.service.service2.UserAuthenticationService;


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
			@RequestParam("password")String password, HttpServletRequest request) {
		
		password = MD5Util.MD5Encode(password);
		Users user =userAuthenticationDao.findLoginAuthentication(phone, password);
		if(user==null) {
			return new MobileJsonForm("0","用户名密码不正确");
		}else {
			MobileJsonForm form = new MobileJsonForm("1");
			//System.out.println(user.toString());
			if(user.getPhoto()!=null&&!user.getPhoto().equals("")) {
				String localUrl = request.getScheme()+"://"+request.getServerName()+":"
						+request.getServerPort()+request.getContextPath();
				user.setPhoto(localUrl+"/userphoto"+user.getPhoto());
			}
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
			password = MD5Util.MD5Encode(password);
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
	/**
	 * 用户更该头像
	 */
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value="/changePhoto",method=RequestMethod.POST)
	public MobileJsonForm changeHaederPhoto(String token,
			MultipartHttpServletRequest request) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(null!=uid &&-1!=uid) {
			MultipartFile multifile = request.getFile("photo");
			//System.out.println(request.getScheme()+"://"+request.getServerName()+":"
		//	+request.getServerPort()+request.getContextPath());
			// 优化图片内存  略
			String realPath = request.getRealPath("userphoto");
			String filename = UploadUtils.uploadToServer(realPath, multifile);
			Users user =new Users(uid,filename);
			Boolean flag = userAuthenticationDao.modifyHeaderPhoto(user);
			if(flag!=true) {
				form.setCodeAndMessage("2", "database fail");
				return form;
			} 
			String url = request.getScheme()+"://"+request.getServerName()+":"+
			request.getServerPort()+request.getContextPath()+"/userphoto";
			form.addData("photo", url+filename);
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	
	/**
	 * 用户更该头像
	 */
	@ResponseBody
	@RequestMapping(value="/changePwd",method=RequestMethod.POST)
	public MobileJsonForm changePassword(String token,String password) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(null!=uid &&-1!=uid) {
			password = MD5Util.MD5Encode(password);
			Users user =new Users(uid,null,password);
			Boolean flag = userAuthenticationDao.modifyHeaderPhoto(user);
			if(flag!=true) {
				form.setCodeAndMessage("2", "database fail");
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	
	
	/**
	 * 忘记密码  验证码
	 */
	@ResponseBody
	@RequestMapping(value="/verifyPwd",method=RequestMethod.POST)
	public MobileJsonForm changepwsVerifyCode(String phone) {
		
		String isRegister =userAuthenticationDao.findIsRegisterAuthentication(phone);
		MobileJsonForm form =new MobileJsonForm();
		if(isRegister==null) {
			form.setCodeAndMessage("2", "此号码未注册");
			return form;
		}
		String random =System.currentTimeMillis()+"";
		String code =  random.substring(random.length()-5);
		AliMessageSend.sendSms(phone, code);
		form.setCodeAndMessage("1", "success");
		form.addData("verityCode", code);
		return form;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/forgetPwd",method=RequestMethod.POST)
	public MobileJsonForm forgetPassword(String phone,String password) {
		MobileJsonForm form =new MobileJsonForm();
		password = MD5Util.MD5Encode(password);
		if(userAuthenticationDao.modifyforgetPassword(phone,password)!=true) {
			form.setCodeAndMessage("2", "database exception");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	
}
