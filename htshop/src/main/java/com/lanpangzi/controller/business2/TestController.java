package com.lanpangzi.controller.business2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.pojo.Usersextend;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.UploadUtils;
import com.lanpangzi.utils.WX.APPParamsUtils;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private APPParamsUtils AppParamsInfo;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody 
	public String test0101(HttpServletRequest request) {
		return AppParamsInfo.getAppid()+AppParamsInfo.getKey()+AppParamsInfo.getMchid();
	}
}
