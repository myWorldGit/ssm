package com.lanpangzi.controller.admin;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.pojo.Limu;
import com.lanpangzi.pojo.LimuInfomation;
import com.lanpangzi.pojo.Users;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.common.CommonUtils;
import com.lanpangzi.utils.limu.LAJILimuCommonUtils;
import com.lanpangzi.utils.limu.LimuInfoTextFileUploadUtils;
import com.lanpangzi.utils.limu.LimuInfomationUtils;
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
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/getlimuInfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getLimuAllByToken(Limu limu,HttpServletRequest request) {
		MobileJsonForm form =new MobileJsonForm();  
		//是否已经保存信息  如果有数据库拿  没有就从limu拿
		boolean flag = limuDao.isSubmitInfomation(limu.getLid())!=null;
		if(!flag) {
			System.out.println("not");
			String taobao = LAJILimuCommonUtils.getInfoByToken(limu.getAlitoken(),
					LimuInfomationUtils.TAOBAO);
			//String credit = LAJILimuCommonUtils.getInfoByToken(limu.getBanktoken(),
			//		LimuInfomationUtils.CREDIT);
			String mobile = LAJILimuCommonUtils.getInfoByToken(limu.getPhonetoken(),
					LimuInfomationUtils.MOBILE);
			form.addData("taobao", taobao);
			//form.addData("credit", credit);    
			form.addData("mobile", mobile);
			try {
				String realPath = request.getRealPath("/limuinfo/text");
				String tabaofile = 
						LimuInfoTextFileUploadUtils.uploadTextFile(realPath+"/taobao", taobao);
				String mobilefile = 
						LimuInfoTextFileUploadUtils.uploadTextFile(realPath+"/mobile", mobile);
				form.addData("taobao", taobao);
				//form.addData("credit", credit);    
				form.addData("mobile", mobile);
			limuDao.insertInfo(new LimuInfomation(limu.getLid(),
					"/taobao/"+tabaofile,"/mobile/"+mobilefile,"credit"));
			}catch (Exception e) { 
				form.setCodeAndMessage("2", "database exception  or  File exception");
				e.printStackTrace();
				return form;
			}
			form.setCodeAndMessage("1", "success");
			return form;
		}
		String realPath = request.getRealPath("/limuinfo/text");
		LimuInfomation info = limuDao.getLimuinfomation(limu.getLid());
		String taobaojson = LimuInfoTextFileUploadUtils.
				readTextFileByPath(realPath+info.getTaobao());
		String mobilejson = LimuInfoTextFileUploadUtils.
				readTextFileByPath(realPath+info.getMobile());
		if(taobaojson==null||mobilejson==null) {
			form.setCodeAndMessage("2", "file operaction exception");
			return form;
		}
		form.addData("taobao", taobaojson);
		//form.addData("credit", credit);
		form.addData("mobile", mobilejson);
		form.setCodeAndMessage("1", "success already save to database");
		return form;  
	}
}  















