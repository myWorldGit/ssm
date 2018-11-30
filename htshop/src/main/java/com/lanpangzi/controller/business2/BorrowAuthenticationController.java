package com.lanpangzi.controller.business2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.pojo.Borrow;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.UploadUtils;
import com.lanpangzi.utils.common.CommonUtils;
import com.lanpanzi.service2.BorrowAuthenticationService2;
/**
 * 		认证信息
 * @author 帅气的老胡
 *
 */
@RequestMapping("/borrowAuthentication")
@Controller
public class BorrowAuthenticationController {
	public static final String DISCERN_PATH = "/borrowAuthentication/getdiscern";
	@Autowired
	private BorrowAuthenticationService2 borrowAuthenticationDao;

	
	@RequestMapping(value="/modifyalipayAuth",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyAliPayAuth(@RequestParam("token")String token,
			MultipartHttpServletRequest muiltRequest) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
		if(uid!=null && uid !=-1) {
			List<MultipartFile> multifiles =muiltRequest.getFiles("aliPay");
			@SuppressWarnings("deprecation")
			String realPath =muiltRequest.getRealPath("borrowAuthentication/findalipay");
			List<String>filenames =new ArrayList<>(multifiles.size());
			for(int i=0; i<multifiles.size(); i++) {
				MultipartFile multifile = multifiles.get(i);
				filenames.add(UploadUtils.uploadToServer(realPath, multifile));
			}
			//如果储存成功则清除里面的相片
			Borrow borrow = new Borrow();
			if(filenames.size()!=4) {   //四张相片
				form.setCodeAndMessage("0","相片数量不足");
				return form;
			}
			borrow.setBid(uid);
			borrow.setPayA(filenames.get(0));
			borrow.setPayB(filenames.get(1));
			borrow.setPayC(filenames.get(2));
			borrow.setPayD(filenames.get(3));
			Map<String,String> map =borrowAuthenticationDao.findAliPayAuthentication(uid);
			List<String> oldFiles = CommonUtils.mapToList(map);
			if(borrowAuthenticationDao.saveAliPayAuthentication(borrow)==false) {
				form.setCodeAndMessage("2", "数据库异常");
				return form;
			}
			UploadUtils.clearOldImage(realPath, oldFiles);
			
			System.out.println(realPath);
			form.setCodeAndMessage("1", "上传成功");
			return form;
		}
		
		
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	@RequestMapping(value="/modifybankAuthen",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyBankAuth(@RequestParam("token")String token
			,MultipartHttpServletRequest multiRequest) {
		
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
	
		if(uid !=null && uid !=-1) {
			List<MultipartFile> multiFiles =multiRequest.getFiles("bankimg");
			@SuppressWarnings("deprecation")
			String realPath = multiRequest.getRealPath("borrowAuthentication/findbank");
			
			List<String> filenames =new ArrayList<>(multiFiles.size());
			for(MultipartFile multiFile :multiFiles) {
				filenames.add(UploadUtils.uploadToServer(realPath, multiFile));
			}
			Borrow borrow =new Borrow();
			borrow.setBid(uid);
			borrow.setBankA(filenames.get(0));
			borrow.setBankB(filenames.get(1));
			Map<String,String> map = borrowAuthenticationDao.findBankAuthentication(uid);
			UploadUtils.clearOldImage(realPath, CommonUtils.mapToList(map));
			if(borrowAuthenticationDao.saveBankAuthentication(borrow)==false) {
				form.setCodeAndMessage("2", "数据库操作失败");
				return form;
			}
			form.setCodeAndMessage("1", "修改操作成功");
			return form;
	
			
		}
		
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	
	//修改个人认证信息
	@RequestMapping(value="/modifypersonalAuth",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyPersonalAuth(@RequestParam("token")String token
			,@RequestParam("authName")String authName
			,@RequestParam("authPhone")String authPhone
			,@RequestParam("authIdcard")String authIdcard
			,@RequestParam("authpassword")String authpassword) {
		Borrow borrow =new Borrow(authPhone,authIdcard,authName,authpassword);
		Integer uid =TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
	
		if(uid !=null && uid!=-1) {
			borrow.setBid(uid);
			form.setCodeAndMessage("1","success");
			borrowAuthenticationDao.savePersonalAuthentication(borrow);
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/********查找*/
	@RequestMapping(value="/findpersonal",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findPersonalAuth(@RequestParam("token")String token) {
		Integer uid =TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
		if(uid !=null && uid!=-1) {
			form.setCodeAndMessage("1","success");
			form.addData("authInfo", borrowAuthenticationDao.
					findPersonalAuthentication(uid));
			return form;
		}
		form.addData("authInfo", "");
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	@RequestMapping(value="/findalipay",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAliPayAuth(@RequestParam("token")String token,
			HttpServletRequest request) {
		Integer uid =TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
		if(uid !=null && uid!=-1) {
			Map<String,String> map=borrowAuthenticationDao.findAliPayAuthentication(uid);
			String localUrl = request.getRequestURL().toString();
			if(map.get("payimg1")==null || map.get("payimg1").equals("")) {
				form.setCodeAndMessage("2","空字段");
				//form.addData("authInfo","");
				return form;
			}
			for(String key: map.keySet()) {
				map.put(key, localUrl+map.get(key));
			}
			form.setCodeAndMessage("1","success");
			form.addData("authInfo",map);
			return form;
		}
		form.addData("authInfo", "");
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	@RequestMapping(value="/findbank",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findBankAuth(@RequestParam("token")String token
			,HttpServletRequest request) {
		Integer uid =TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
		Map<String,String> map=borrowAuthenticationDao.findBankAuthentication(uid);
		String localUrl = request.getRequestURL().toString();
	
		if(uid !=null && uid!=-1) {
			if(map.get("justbank")==null || map.get("justbank").equals("")) {
				form.setCodeAndMessage("2","没有数据");
				form.addData("authInfo", "");
				return form;
			}
			for(String filename : map.keySet()) {
				map.put(filename,localUrl+map.get(filename));
			}
			form.setCodeAndMessage("1","success");
			form.addData("authInfo", map);
			return form;
		}
		form.addData("authInfo", "");
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	
	
	@RequestMapping(value="/allstate",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAllAuthStates(@RequestParam("token")String token) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid !=null && uid!=-1) {
			form.addData("states",borrowAuthenticationDao.findAutheAllStates(uid));
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/setdiscern",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm setDiscernInfo(@RequestParam("token")String token,
			MultipartHttpServletRequest request) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid !=null && uid!=-1) {
			MultipartFile multifile = request.getFile("myseifface");
			String realPath = request.getRealPath(DISCERN_PATH);
			String oldFile = borrowAuthenticationDao.getAuthDiscernInfo(uid);
			String filename = UploadUtils.uploadToServer(realPath, multifile);
			if(borrowAuthenticationDao.saveAuthDiscernImage(uid, filename)!=true) {
				form.setCodeAndMessage("2", "数据库异常");
				return form;
			}
			if(oldFile!=null) {
				UploadUtils.clearOldImage(realPath, Arrays.asList(oldFile));
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}

	@RequestMapping(value="/getdiscern",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findDiscernInfo(@RequestParam("token")String token,
			HttpServletRequest request) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form = new MobileJsonForm();
		if(uid !=null && uid!=-1) {
			if(!borrowAuthenticationDao.IsExist(uid)) {
				form.setCodeAndMessage("2", "没有数据");
				return form; 
			}
			String discern = borrowAuthenticationDao.getAuthDiscernInfo(uid);
			
			if(discern==null || discern.equals("")) {
				form.setCodeAndMessage("2", "没有数据");
				return form; 
			}
			form.addData("discern",	request.getRequestURL().toString()+ discern);
			form.setCodeAndMessage("1", "success");
			return form; 
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	/**
	 * 垃圾立木的token保存在下面
	 */
	
	
	
	
	
	
	
}
