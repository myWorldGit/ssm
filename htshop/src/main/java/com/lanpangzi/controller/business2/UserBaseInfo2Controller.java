package com.lanpangzi.controller.business2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.pojo.Usersextend;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.OssInfoUtils;
import com.lanpangzi.utils.TokenUtil;
import com.lanpangzi.utils.UploadUtils;
import com.lanpanzi.service2.UserBaseInfoService2;
/**
 * 						个人用户基本信息修改控制器--扩展表
 * @author 帅气的老胡
 *	
 */
@Controller
@RequestMapping("/userInfoForm")
public class UserBaseInfo2Controller {
	public final static String IDCARD_PATH="/IDCARD";
	@Autowired
	private UserBaseInfoService2 userBaseInfoDao;
	
	@RequestMapping(value="/modifybaseinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm userBaseInfo(@RequestParam("token")String token,
			@RequestParam("username") String username,
			@RequestParam("idcard") String idcard,
			@RequestParam("sex") String sex) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		
		if(uid!=-1 && uid!=null) {
			Boolean flag = userBaseInfoDao.saveBaseInfo(uid, username, idcard, sex);
			if(flag==true) {
				form.setCode("1");
				form.setMessage("用户修改成功");
				return form;
			}else {
				form.setCode("2");
				form.setMessage("数据库操作异常");
				return form;
			}
		}else {
			form.setCode("0");
			form.setMessage("用户tonken 失效");
			return form;
		}
	}
	
	@RequestMapping(value="/findbaseinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findBaseInfo(@RequestParam("token")String token) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid =TokenUtil.getAppUID(token);
		if(uid!=-1 && uid!=null) {
			form.setCode("1");
			if(userBaseInfoDao.IsExist(uid)) {
				Map<String,String> mapInfo = userBaseInfoDao.findBaseInfo(uid);
				form.addData("infodata", mapInfo);
				return form;
			}
			Map<String,String> mapInfo2= new HashMap<>();
			mapInfo2.put("username", userBaseInfoDao.findUsernameById(uid));
			mapInfo2.put("sex", "" );
			mapInfo2.put("idcard", "" );
			form.addData("infodata", mapInfo2);
			return form;
		}
		form.setCode("0");
		form.setMessage("token异常");
		return form;
	}
	
	
	
	@RequestMapping(value="/modifypersonal",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm userPersonal(@RequestParam("token")String token,
			@RequestParam("address") String address,
			@RequestParam("education") String education,
			@RequestParam("marriage") String marriage) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		
		if(uid!=-1 && uid!=null) {
			Boolean flag = userBaseInfoDao.savePersonInfo(uid, address, education, marriage);
			if(flag==true) {
				form.setCode("1");
				form.setMessage("用户修改成功");
				return form;
			}else {
				form.setCode("2");
				form.setMessage("数据库操作异常");
				return form;
			}
		}else {
			form.setCode("0");
			form.setMessage("用户tonken 失效");
			return form;
		}
	}
	
	@RequestMapping(value="/findpersonalinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findPersonalInfo(@RequestParam("token")String token) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=-1 && uid!=null) {
		
			if(userBaseInfoDao.IsExist(uid)) {
				form.setCode("1");
				Map<String,String> mapInfo = userBaseInfoDao.findPersonalInfo(uid);
				form.addData("infodata", mapInfo);
				return form;
			}
			form.setCode("2");
			form.addData("infodata", null);
			return form;
		}
		form.setCode("0");
		form.setMessage("token异常");
		return form;
	}
	
	@RequestMapping(value="/findJobinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findJobInfo(@RequestParam("token")String token) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=-1 && uid!=null) {
		
			if(userBaseInfoDao.IsExist(uid)) {
				form.setCode("1");
				Map<String,String> mapInfo = userBaseInfoDao.findJobInfo(uid);	
				form.addData("infodata", mapInfo);
				return form;
			}
			form.setCode("2");
			form.addData("infodata", null);
			return form;
		}
		form.setCode("0");
		form.setMessage("token异常");
		return form;
	}
	
	@RequestMapping(value="/findcontactinfo",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findcontactInfo(@RequestParam("token")String token) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		if(uid!=-1 && uid!=null) {
			
			if(userBaseInfoDao.IsExist(uid)) {
				Map<String,String> mapInfo = userBaseInfoDao.findContactInfo(uid);
				form.addData("infodata", mapInfo);
				form.setCode("1");
				return form;
			}
			form.setCode("2");
			form.addData("infodata", null);
			return form;
		}
		form.setCode("0");
		form.setMessage("token异常");
		return form;
	}
	
	@RequestMapping(value="/modifyjob",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm userObjInfo(@RequestParam("token")String token,
			@RequestParam("job") String job,
			@RequestParam("income") String income,
			@RequestParam("working") String working) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		
		if(uid!=-1  && uid!=null) {
			Boolean flag = userBaseInfoDao.saveJobInfo(uid, job, income, working);
			if(flag==true) {
				form.setCode("1");
				form.setMessage("用户修改成功");
				return form;
			}else {
				form.setCode("2");
				form.setMessage("数据库操作异常");
				return form;
			}
		}else {
			form.setCode("0");
			form.setMessage("用户tonken 失效");
			return form;
		}
	}
	@RequestMapping(value="/modifycontact",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm userContactInfo(@RequestParam("token")String token,
			@RequestParam("contactname") String contactname,
			@RequestParam("contactphone") String contactphone,
			@RequestParam("relation") String relation) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid = TokenUtil.getAppUID(token);
		
		if(uid!=-1 && uid!=null) {
			Boolean flag = userBaseInfoDao.saveContactInfo(uid,
					contactname, contactphone, relation);
			if(flag==true) {
				form.setCode("1");
				form.setMessage("用户修改成功");
				return form;
			}else {
				form.setCode("2");
				form.setMessage("数据库操作异常");
				return form;
			}
		}else {
			form.setCode("0");
			form.setMessage("用户tonken 失效");
			return form;
		}
	}
	@RequestMapping(value="/saveIdcard",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm idcardUploadImg (HttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		Integer uid =TokenUtil.getAppUID(request.getParameter("token"));
		@SuppressWarnings("deprecation")
		String idcardPath=request.getRealPath(OssInfoUtils.getIMAGE_PATH())+IDCARD_PATH;
		MultipartHttpServletRequest mRequest = 
						(MultipartHttpServletRequest) request; 
		if(uid!=null && uid != -1) {
	        List<MultipartFile> idcardimgs =mRequest.getFiles("idcardimg");
			Usersextend userEx= userBaseInfoDao.findcardImgById(uid);
			//清空旧相片
			System.out.println(userEx);
			List<String> OldFilename =Arrays.asList(userEx.getIdcardA(),
									userEx.getIdcardB(),userEx.getIdcardC());
			UploadUtils.clearOldImage(idcardPath, OldFilename);
			//上传到服务器
			Usersextend targetUpload =new Usersextend();
			targetUpload.setIdcardA(UploadUtils.uploadToServer(idcardPath,idcardimgs.get(0)));
			targetUpload.setIdcardB(UploadUtils.uploadToServer(idcardPath,idcardimgs.get(1)));
			targetUpload.setIdcardC(UploadUtils.uploadToServer(idcardPath,idcardimgs.get(2)));
			targetUpload.setEid(uid);
			if(userBaseInfoDao.saveIdcardInfo(targetUpload)==false) {
				form.setCodeAndMessage("2", "数据库异常");
				return form;
			}
			form.setCode("1");
			form.setMessage("OJBK上传成功");
			return form;
		}else {
			form.setCode("0");
			form.setMessage("token无效");
			return form;
		}
		
	}
	
	/**********查询**********/
	@RequestMapping(value="/byIdcardimg",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm findIdcradImg(@RequestParam("token")String token
			,HttpServletRequest request) {
		MobileJsonForm form =new MobileJsonForm();
		Integer uid =TokenUtil.getAppUID(token);
		if(uid!=-1&&uid!=null) {
			String localUrl =request.getRequestURL().toString().
					replace("userInfoForm/byIdcardimg", "")+
					OssInfoUtils.getIMAGE_PATH()+IDCARD_PATH;
			Usersextend userex =userBaseInfoDao.findcardImgById(uid);
			form.setCode("1");
			if(userex.getIdcardA()==null ||
					userex.getIdcardB()==null ||
					userex.getIdcardC()==null||
					userex.getIdcardA().equals("")  ||
					userex.getIdcardB().equals("")  ||
					userex.getIdcardC().equals("") ) {
				System.out.println("JOIN");
				form.setCodeAndMessage("2","目前无数据");
				return form;
			}
			form.addData("justImage", localUrl+userex.getIdcardA());
			form.addData("backImage", localUrl+userex.getIdcardB());
			form.addData("handImage", localUrl+userex.getIdcardC());
			return form;
		}else {
			form.setCode("0");
			form.setMessage("token过期或者无效");
			return form;
		}
	}
	
	
}
