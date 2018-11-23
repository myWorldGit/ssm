package com.lanpangzi.controller.business2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.pojo.Usersextend;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.UploadUtils;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	@ResponseBody 
	public void test0101(HttpServletRequest request) {
		System.out.println(request.getServerName());
		System.out.println(request.getServerPort());
		System.out.println(request.getContextPath());
		
	}
}
