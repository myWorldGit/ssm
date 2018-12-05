package com.lanpangzi.controller.business2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.aliyun.oss.OSSClient;
import com.lanpangzi.pojo.Usersextend;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.UploadUtils;
import com.lanpangzi.utils.WX.APPParamsUtils;
import com.lanpangzi.utils.WX.KdniaoTrackQueryAPI;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private KdniaoTrackQueryAPI kdniaoDao;
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody 
	public String test0101(HttpServletRequest request) {
		try {
			System.out.println(kdniaoDao.getOrderTracesByJson("ANE", "210001633605"));
			return kdniaoDao.getOrderTracesByJson("ANE", "210001633605");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return"";
		}
	}
}
