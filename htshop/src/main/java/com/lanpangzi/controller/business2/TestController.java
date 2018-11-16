package com.lanpangzi.controller.business2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public String test0101(MultipartHttpServletRequest request) {
		System.out.println("in to");
		return "true";
	}
}
