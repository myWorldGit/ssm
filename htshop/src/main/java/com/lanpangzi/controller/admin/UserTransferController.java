package com.lanpangzi.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wxpay.sdk.WXPayUtil;
import com.lanpangzi.pojo.Limu;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.WX.APPParamsUtils;
import com.lanpangzi.utils.WX.HttpClientUtils;
import com.lanpanzi.service.api2.UserTransferService;
@RequestMapping("/transfer")
@Controller
public class UserTransferController {
	@Autowired
	private APPParamsUtils appParamsInfo;
	@Autowired
	private UserTransferService userTransferDao;
	
	@RequestMapping(value="/payAmount" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm payAmount(String token) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
		if(uid!=null&& uid!=-1) { 
			Limu limu =new Limu(uid,520);    
			Boolean flag=false;
			try {  
				//int i = 1/0;  支付
				flag = userTransferDao.saveTransferAmount(limu);
			} catch (Exception e) {
				e.printStackTrace(); 
				flag= false;
			}
			if(flag==false) {
				form.setCodeAndMessage("2", "database exception");
				return form;
			}
			form.setCodeAndMessage("1", "success"); 
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	
	@RequestMapping(value="/payAll" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm payAllAmount(String token,Double price,Integer oid) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid =TokenUtil.getAppUID(token);
		if(uid!=null && uid!= -1) {
			Map<String,String> params =new HashMap<>();
			params.put("appid", appParamsInfo.getAppid()); 
			params.put("partnerid", appParamsInfo.getMchid());
			params.put("prepayid", appParamsInfo.getRandomString());
			params.put("package", "Sign=WXPay");
			params.put("noncestr", appParamsInfo.getRandomString());
			params.put("timestamp", appParamsInfo.getTimestamp());
			try {
				String res = this.appParamsInfo.getprepayId("miaoshuxinxi", "1");
				System.out.println(res);  
				System.out.println(WXPayUtil.xmlToMap(res).get("return_code"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			form.addData("payinfo", params);
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
}