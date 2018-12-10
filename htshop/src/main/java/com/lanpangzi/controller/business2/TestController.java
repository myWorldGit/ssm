package com.lanpangzi.controller.business2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanpangzi.utils.ali.AliPayUtils;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired(required=true)
	private AliPayUtils alipay;
	  
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody 
	public String test0101(HttpServletRequest request) {
		return "";
	}
}
