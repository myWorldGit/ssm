package com.lanpangzi.controller.business2;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.controller.admin.CommodiryAdminController;
import com.lanpangzi.pojo.Classify;
import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpanzi.service2.CommodiryService2;

@Controller
@RequestMapping("/commodiry")
public class CommodiryController2 {
	@Autowired
	private CommodiryService2 commodiryDao;
	
	@RequestMapping(value="/getdetail" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getSingleCommodiryDetails(String token,Integer cid
			,HttpServletRequest request) {
		Integer uid = TokenUtil.getAppUID(token);		
		MobileJsonForm form = new MobileJsonForm();
		if(uid != null && uid != -1) {
			form.addData("httpPrefix", request.getRequestURL().toString()
					.replace("/commodiry/getdetail", "")+CommodiryAdminController.C_IMG_DIR);
			form.addData("commodiry", commodiryDao.getCommodiryDetailsById(cid)); 
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	/**
	 * 	分类商品请求的数据
	 * @param token
	 * @param cid
	 * @return
	 */
	@RequestMapping(value="/getclassify" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getAllClassify(String token,HttpServletRequest request) {
		Integer uid = TokenUtil.getAppUID(token);		
		MobileJsonForm form = new MobileJsonForm();
		if(uid != null && uid != -1) {			
			form.addData("httpPrefix", request.getRequestURL().toString()
					.replace("/commodiry/getclassify", "")+CommodiryAdminController.C_IMG_DIR);
			form.addData("classifys", commodiryDao.getShowAllCommodiry()); 
			form.setCodeAndMessage("1", "success");
			return form;
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
	
	/**
	 * 	分类商品请求的数据
	 * @param token  认证码
	 * @param tid  商品类 id
 	 * @return
	 */
	@RequestMapping(value="/byPages" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getCommodiryByPages(String token,Integer tid,Integer page
			,HttpServletRequest request) {
		Integer uid = TokenUtil.getAppUID(token);		
		MobileJsonForm form = new MobileJsonForm();
		if(uid != null && uid != -1) {
			form.addData("httpPrefix", request.getRequestURL().toString()
					.replace("/commodiry/byPages", "")+CommodiryAdminController.C_IMG_DIR);
			form.addData("commodirys", commodiryDao.getCommodiryByPage(tid, page)); 
			form.setCodeAndMessage("1", "success");
			return form;													
		}
		form.setCodeAndMessage("0", "token异常");
		return form;
	}
}
