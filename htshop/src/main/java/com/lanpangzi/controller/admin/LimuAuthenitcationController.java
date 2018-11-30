package com.lanpangzi.controller.admin;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.pojo.Limu;
import com.lanpangzi.pojo.Users;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.common.CommonUtils;
import com.lanpangzi.utils.common.LAJILimuCommonUtils;
import com.lanpangzi.utils.common.LimuInfomationUtils;
import com.lanpanzi.service2.api.LimuAuthenitcationService;

@Controller
@RequestMapping(value="/limutoken")
public class LimuAuthenitcationController {

	
	@Autowired 
	private LimuAuthenitcationService limuDao;
	
	
	@RequestMapping(value="/ispass",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAllPassInfo() {
		MobileJsonForm form =new MobileJsonForm();
		form.addData("items", limuDao.getLimuAutheritcation());
		form.addData("counts", limuDao.getBypagesCounts());
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	@RequestMapping(value="/setToken",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm saveToken(String token,String value,String key) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=null && uid!=-1) {
			Boolean active = limuDao.isHasLimuInfo(uid);
			Limu limu = new Limu(uid);
			System.out.println(active);
			
			try {
			Method method = limu.getClass().
					getDeclaredMethod("set"+CommonUtils.firstWordToBig(key),String.class);
			method.invoke(limu, value);
			limuDao.saveLimuTokens(limu, active);
			} catch (Exception  e) {
				e.printStackTrace(); 
				form.setCodeAndMessage("2", "反射装配异常");
				return form;
			} 
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("2", "token异常");
		return form;
	}
	
	

	@RequestMapping(value="/isPay",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getToken(String token,Integer state) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		//清空token状态
		if(uid!=null && uid!=-1) {
			limuDao.saveLimuTokens( new Limu(uid,state), limuDao.isHasLimuInfo(uid));
			form.setCodeAndMessage("1", "success");
			return form;
		} 
		form.setCodeAndMessage("2", "token fail");
		return form;
	}
	
	

	
	
	
	
	
	
	
	/****
	 * limu   token查询资料  返回它的签名
	 */
	@RequestMapping(value="/getSign",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getLimuAliByToken(String Otoken,String bizType) {
		//System.out.println(Otoken + bizType);
		MobileJsonForm form = new MobileJsonForm();
		List<BasicNameValuePair> list =new ArrayList<BasicNameValuePair>(); 
		list.add(new BasicNameValuePair("version",LimuInfomationUtils.version));
		list.add(new BasicNameValuePair("apiKey", LimuInfomationUtils.api_key));
		list.add(new BasicNameValuePair("method", "api.common.getResult"));
		list.add(new BasicNameValuePair("bizType", bizType));
		list.add(new BasicNameValuePair("token", Otoken));
		form.addData("sign",LAJILimuCommonUtils.getSign(list));
		form.addData("apiKey",LimuInfomationUtils.api_key);
		form.addData("method","api.common.getResult");
		form.addData("bizType",bizType);
		form.addData("token",Otoken);
		form.addData("version",LimuInfomationUtils.version);

		form.setCodeAndMessage("1", "success");
		return form;
	}
}















