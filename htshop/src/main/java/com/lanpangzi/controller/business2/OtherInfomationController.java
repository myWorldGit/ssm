package com.lanpangzi.controller.business2;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.mapper.business2.OtherInfomationMapper;
import com.lanpangzi.pojo.Other;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.UploadUtils;
import com.lanpanzi.service.service2.OtherInfomationService2;

@Controller
@RequestMapping("/other")
public class OtherInfomationController {
	public static final String FILE_DIR ="/otherFile";
	@Autowired
	private OtherInfomationService2 otherInfoDao;
	
	
	/**
	 * 轮播图
	 */
	@RequestMapping(value="/getsowingMap",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getSowingMap(HttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		List<Other> others= otherInfoDao.findValuesByKey("sowingMap");
		for(Other other: others) {
			other.setValue(request.getRequestURL().toString().replace("/other/getsowingMap"
					, "")+FILE_DIR+other.getValue());
		}
		form.addData("preAmount", otherInfoDao.findSingleInfo("preAmount"));
		form.addData("sowingMap", others);	
		form.setCodeAndMessage("1","success");
		return form;
	}
	
	
	@RequestMapping(value="/addsowingMap",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm addSowingMap(MultipartHttpServletRequest request) {
		@SuppressWarnings("deprecation")
		String realPath = request.getRealPath(FILE_DIR);
		MultipartFile multiFile = request.getFile("file");
		String filename = UploadUtils.smallUpload(realPath, multiFile);
		Integer oid = otherInfoDao.insertOtherInfo(new Other(filename,"sowingMap",new Date(System.currentTimeMillis())));
		MobileJsonForm form = new MobileJsonForm(); 
		if(oid==null) {
			form.setCodeAndMessage("2","数据库插入失败");
		}
		form.setCodeAndMessage("1","success");
		String localUrl = request.getRequestURL().toString().replace("/other/addsowingMap", "");
		form.addData("oid",oid);
		System.out.println(localUrl+FILE_DIR+filename);
		form.addData("value",localUrl+FILE_DIR+filename);
		return form;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/delsowingMap",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm delSowingMap(Integer oid,HttpServletRequest request) {
		MobileJsonForm form =new MobileJsonForm();
		String oldfile = otherInfoDao.findOtherById(oid);
		if(oldfile!=null && !oldfile.equals("")) {
			System.out.println(oid);
			UploadUtils.clearOldImage(request.getRealPath(FILE_DIR), Arrays.asList(oldfile));
		}
		if(otherInfoDao.deleteValueById(oid)!=true) {
			form.setCodeAndMessage("2", "数据无数据删除");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	
	
	
	/**
	 * 根据key查询 values
	 * @param key
	 * @return
	 */
	@RequestMapping(value="/getuploadMap",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getuploadMap(HttpServletRequest request) {
		String localUrl = request.getRequestURL().toString().
				replace("/other/getuploadMap", "")+FILE_DIR;
		MobileJsonForm form = new MobileJsonForm();
		form.setCodeAndMessage("1", "success");
		form.addData("areaRight",localUrl+ otherInfoDao.findSingleInfo("areaRight"));
		form.addData("areaLeft", localUrl+ otherInfoDao.findSingleInfo("areaLeft"));
		form.addData("recommend1",localUrl+ otherInfoDao.findSingleInfo("recommend1"));
		form.addData("recommend2",localUrl+ otherInfoDao.findSingleInfo("recommend2"));
		form.addData("recommend3",localUrl+ otherInfoDao.findSingleInfo("recommend3"));
		form.addData("hotLeft",localUrl+ otherInfoDao.findSingleInfo("hotLeft"));
		form.addData("hotRightUp",localUrl+ otherInfoDao.findSingleInfo("hotRightUp"));
		form.addData("hotRightDown",localUrl+ otherInfoDao.findSingleInfo("hotRightDown"));
		return form;
	}
	@RequestMapping(value="/addsystemInfo",method=RequestMethod.POST)
	@ResponseBody
	public Integer addSystemInfomation(@RequestParam("content")String content) {
		System.out.println("join");
		Integer oid =otherInfoDao.insertOtherInfo(new Other(content,"systemInfo"
				,new Date(System.currentTimeMillis())));
		return oid;
	}
	
	
	@RequestMapping(value="/getsystemInfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getSystemInfomation() {
		MobileJsonForm form =new MobileJsonForm();
		form.addData("data", otherInfoDao.findValuesByKey("systemInfo"));
		form.setCode("1");
		return form;
	}
	
	@RequestMapping(value="/updateinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm updateSystemInfomation(@RequestParam("oid")Integer oid,
			@RequestParam("value")String value) {
		System.out.println("i am update");
		MobileJsonForm form =new MobileJsonForm();
		if(otherInfoDao.updateValueById(oid, value)!=true) {
			form.setCodeAndMessage("2", "数据无数据删除");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	@RequestMapping(value="/clearinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm clearSystemInfomation() {
		MobileJsonForm form =new MobileJsonForm();
		if(otherInfoDao.clearByKeyAllInfo("systemInfo")!=true) {
			form.setCodeAndMessage("2", "数据无数据删除");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	@RequestMapping(value="/delsystemInfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm deleteSystemInfomation(Integer oid) {
		MobileJsonForm form =new MobileJsonForm();
		if(otherInfoDao.deleteValueById(oid)!=true) {
			form.setCodeAndMessage("2", "数据无数据删除");
			return form;
		}
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/uploadimg",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm uploadImg(MultipartHttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		String realPath =request.getRealPath(FILE_DIR);
		String localUrl = request.getRequestURL().toString().replace("/other/uploadimg", "");
		//获取该key值
		String key = request.getParameter("location");
		//根据key查找对应的属性  如果有删除  没有就算了
		System.out.println(realPath);
		List<Other> oldInfo =  otherInfoDao.findValuesByKey(key);
		String filename= UploadUtils.smallUpload(realPath, request.getFile("file"));
		if(oldInfo!=null && oldInfo.size()>0) {  //严谨性本应该加循环
			Other other =oldInfo.get(0);
			UploadUtils.clearOldImage(realPath,Arrays.asList(other.getValue()));
			otherInfoDao.deleteValueById(other.getOtherid());
		}
		otherInfoDao.insertOtherInfo(new Other(filename,key,
				new Date(System.currentTimeMillis())));
		form.addData("location", key); 
		System.out.println(localUrl+FILE_DIR+filename);
		form.addData("image",localUrl+FILE_DIR+filename);
		form.setCodeAndMessage("1", "success");
		return form;
	}
}
