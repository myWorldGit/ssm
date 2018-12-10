package com.lanpangzi.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.sql.dialect.odps.parser.OdpsExprParser;
import com.github.wxpay.sdk.WXPayUtil;
import com.lanpangzi.mapper.business.OrderMapper;
import com.lanpangzi.mapper.business2.OtherInfomationMapper;
import com.lanpangzi.pojo.Limu;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.WX.APPParamsUtils;
import com.lanpangzi.utils.ali.AliPayUtils;
import com.lanpanzi.service.api2.LimuAuthenitcationService;
import com.lanpanzi.service.api2.OrderService;
import com.lanpanzi.service.api2.UserTransferService;
@RequestMapping("/transfer")
@Controller
public class UserTransferController {
	@Autowired
	private AliPayUtils aliPayDao;
	@Autowired
	private OtherInfomationMapper preAmountDao;
	@Autowired
	private APPParamsUtils appParamsInfo;
	@Autowired
	private UserTransferService userTransferDao;
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping(value="/preANotifyUrl" ,method=RequestMethod.POST)
	@ResponseBody
	public void preANotifyUrl(HttpServletRequest request) {
		
		
		Limu limu = aliPayDao.aliPayPreAmountCallback(request);
		try {
			userTransferDao.saveTransferAmount(limu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 如果后台无设定定金则默认为200块钱
	 * @param token
	 * @return
	 */
	@RequestMapping(value="/alipayPremount" ,method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm payAmount(String token,HttpServletRequest request) {
		Integer uid = TokenUtil.getAppUID(token);
		MobileJsonForm form =new MobileJsonForm();
		if(uid!=null&& uid!=-1) { 
		String preAmount= preAmountDao.findSingleInfo("preAmount");
	
		Double payMoney =  preAmount==null?200.00:Double.parseDouble(preAmount);
		String payinfo = null;
			try {  
				String localurl = request.getScheme()+"://"+request.getServerName()+":"+
			request.getServerPort()+request.getContextPath()+"/transfer/preANotifyUrl";
				System.out.println(localurl);
				payinfo = aliPayDao.alipayInterface("预先支付定金",String.valueOf(payMoney),
						String.valueOf(uid), localurl);
			} catch (Exception e) {
				form.setCodeAndMessage("2", "exception"); 
				e.printStackTrace(); 
				return form;
			}
			form.addData("payinfo", payinfo);
			form.setCodeAndMessage("1", "success"); 
			return form;
		}
		form.setCodeAndMessage("2", "token exception");
		return form;
	}
	

	
	@RequestMapping(value="/aliPayNotify" ,method=RequestMethod.POST)
	@ResponseBody
	public void aliPayNotify(HttpServletRequest request) {
		System.out.println("join callback");
		Integer oid = aliPayDao.aliPayCallback(request);
		//给oid改变状态  待收货
		orderService.modifyStateByOid(1, oid);
		
	}
	
	
	
	
	
	@RequestMapping(value="/wxPay" ,method=RequestMethod.POST)
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
