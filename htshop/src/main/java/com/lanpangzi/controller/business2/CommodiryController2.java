package com.lanpangzi.controller.business2;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.controller.admin.CommodiryAdminController;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpanzi.service.api2.SystemInfomationService;
import com.lanpanzi.service.service2.CommodiryService2;

@Controller
@RequestMapping("/commodiry")
public class CommodiryController2 {
	@Autowired
	private CommodiryService2 commodiryDao;
	@Autowired
	private SystemInfomationService systemInfomationDao;
	
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
	
	
	
	//推荐 2条  商品销量逆序
	@RequestMapping(value="/bycount2" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getByCountRecommendCommodiry(HttpServletRequest request,
			 String token) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form =new  MobileJsonForm();
		if(uid!=null && uid!=-1) {
			List<Map<String,String>> list =commodiryDao.getByCount2Commodiry();
			if(list !=null &&list.size()>0) {
				String localUrl = request.getScheme()+"://"+request.getServerName()+":"+
						request.getServerPort()+request.getContextPath()
						+CommodiryAdminController.C_IMG_DIR;
				for(Map<String,String> m :list) {
					m.put("photo", localUrl+m.get("photo"));
				}
				System.out.println(uid);
				form.addData("infocount", systemInfomationDao.getUnreadInfomationCount(uid));
				form.addData("commodirys",list);
				form.setCodeAndMessage("1", "success");
				return form;
			}	
			form.addData("infocount", systemInfomationDao.getUnreadInfomationCount(uid));
			form.setCodeAndMessage("2", "null data");
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form; 
		
	}
	//额度推荐   价格逆序 
	@RequestMapping(value="/byamount4" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getByAmountRecommendCommodiry(HttpServletRequest request,
			String token) {
		MobileJsonForm form =new  MobileJsonForm();
		Integer uid =TokenUtil.getAppUID(token);
		if(uid!=null &&uid != -1) {
			List<Map<String,String>> list= commodiryDao.getByAmount4Commodiry();
			if(list !=null &&list.size()>0) {
				String localUrl = request.getScheme()+"://"+request.getServerName()+":"+
				request.getServerPort()+request.getContextPath()+CommodiryAdminController.C_IMG_DIR;
				for(Map<String,String> m :list) { 
					m.put("photo", localUrl+m.get("photo"));
				}
				form.addData("maxAmount", commodiryDao.getUserMaxAmount(uid));
				form.addData("commodirys",list);
				form.setCodeAndMessage("1", "success");
				return form;
			}
			form.setCodeAndMessage("2", "null data");
			form.addData("maxAmount", commodiryDao.getUserMaxAmount(uid));
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	
	}
	//用户搜索
	@RequestMapping(value="/keyword" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findkeywordByCommodirys(String keyword,Integer page,HttpServletRequest request) {
		MobileJsonForm form =new MobileJsonForm();
		List<Map<String,String>> commodirys = commodiryDao.findBykeywordByCommodirys(keyword,page);
		String localUrl = request.getScheme()+"://"+request.getServerName()+":"+
			request.getServerPort()+request.getContextPath()+CommodiryAdminController.C_IMG_DIR;
		for(Map<String,String> m : commodirys) {
			m.put("photo",localUrl +m.get("photo"));
		}
		form.addData("commodirys", commodirys);
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	
}
