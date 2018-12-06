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
import com.lanpanzi.service.api2.LimuAuthenitcationService;

@Controller
@RequestMapping(value="/limutoken")
public class LimuAuthenitcationController {

	
	@Autowired 
	private LimuAuthenitcationService limuDao;
	
	//页面的初始化
	@RequestMapping(value="/ispass",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findAllPassInfo() {
		MobileJsonForm form =new MobileJsonForm();
		form.addData("items", limuDao.getLimuAutheritcation());
		form.addData("counts", limuDao.getBypagesCounts());
		form.setCodeAndMessage("1", "success");
		return form;
	}
	//立木用户的插入token
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
	
	
	//进行插入或者修改
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
	@RequestMapping(value="/getlimuInfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getLimuAllByToken(Limu limu) {
		MobileJsonForm form =new MobileJsonForm();
		System.out.println(limu);
		
		
		
		
		form.setCodeAndMessage("1", "success");
		return form;  
	}
}















