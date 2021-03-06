package com.lanpangzi.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lanpangzi.pojo.Commodiry;
import com.lanpangzi.pojo.Destail;
import com.lanpangzi.utils.MobileJsonForm;
import com.lanpangzi.utils.UploadUtils;
import com.lanpanzi.service.api2.CommodiryAdminSerivce;
import com.lanpanzi.service.service2.CommodiryService2;
import com.lanpanzi.service.service2.OtherInfomationService2;
  
@Controller
@RequestMapping("/adminCommodiry")
public class CommodiryAdminController {
	public final static String C_IMG_DIR="/adminCommodiry/addCommodiry";
	@Autowired
	private CommodiryService2 commodiryAdminDao;
	@Autowired
	private OtherInfomationService2 otherInfoDao;
	
	@Autowired
	private CommodiryAdminSerivce commodiryAdminExDao;
	
	@RequestMapping(value="/getinit",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getInitPage(Integer tid, Integer page,HttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		List<Commodiry> commodirys = commodiryAdminDao.getCommodiryByPage(tid, page);
		String localPath = request.getRequestURL().toString().
				replace("/adminCommodiry/getinit", "")+C_IMG_DIR;
		for(Commodiry c :commodirys) {
			c.setPhoto(localPath +c.getPhoto());
		}
		form.addData("datasum", commodiryAdminExDao.getCommodiryByPageCount(tid));
		form.addData("commodirys", commodirys);
		form.addData("classifys", commodiryAdminExDao.findAllClassify());
		form.setCodeAndMessage("1","success");
		return form;
	}
	
	
	//查找单个商品回显
	@RequestMapping(value="/findSingle",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getSingleCommodiry(Integer cid,HttpServletRequest request) {
		
		MobileJsonForm form = new MobileJsonForm();
		Commodiry commodiry = commodiryAdminDao.getCommodiryDetailsById(cid);
		String localUrl = request.getRequestURL().toString().
				replace("/adminCommodiry/findSingle", "")+C_IMG_DIR;
		commodiry.setPhoto(localUrl+commodiry.getPhoto());
		form.addData("commodiry", commodiry);
		form.setCodeAndMessage("1","success");
		return form;
	}
	

	//查找所有详情图片
	@RequestMapping(value="/findDetails",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm getDetailsImgCommodiry(Integer cid,HttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		String localUrl  = request.getRequestURL().toString().
			replace("/adminCommodiry/findDetails", "")+C_IMG_DIR;
		form.addData("cid", cid);
		List<Destail> details = commodiryAdminDao.findAllDetails(cid);
		for(Destail d :details) {
			d.setImage(localUrl + d.getImage());
		}  
		form.addData("color", commodiryAdminExDao.findColorArray(cid));
		form.addData("commodiry", details);
		form.setCodeAndMessage("1","success"); 
		return form;
	}
	//删除单个详情图片
	@RequestMapping(value="/delDetails",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm deleteDetailsImgCommodiry(Integer did,String img) {
		MobileJsonForm form = new MobileJsonForm();
		System.out.println(did);
		Boolean flag=commodiryAdminDao.deleteDetailsById(did);
		if(flag == false) {
			form.setCodeAndMessage("2","exception");
			return form;
		}
		//System.out.println(img);该图片为旧文件  可清理旧文件操作  略  
		form.setCodeAndMessage("1","success");
		return form;
	}
	
	//插入单个详情图片
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/insertDetail",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm insertDetailsImgCommodiry(Integer cid,
			MultipartHttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		MultipartFile multifile = request.getFile("file");
	    Destail detail = new Destail();
		String realPath = request.getRealPath(C_IMG_DIR);
		try {	
			BufferedImage bufferedImage =ImageIO.read(multifile.getInputStream());
			detail.setHeight(bufferedImage.getHeight());
			detail.setWidth(bufferedImage.getWidth());
			detail.setCommodiry(new Commodiry(cid));
			detail.setImage(UploadUtils.uploadToServer(realPath, multifile));
			Integer did = commodiryAdminExDao.insertDetails(detail);
			detail.setDid(did);
			System.out.println(detail);
			if(did==null) {
				form.setCodeAndMessage("2", "数据库异常");
				return form;
			}
		} catch (IOException e) {
			e.printStackTrace();
			form.setCodeAndMessage("2", "exception");
			return form;
		}
		String localUrl = request.getRequestURL().toString().
				replace("/adminCommodiry/insertDetail","")+C_IMG_DIR;
		detail.setImage(localUrl+detail.getImage());
		form.addData("detail", detail);
		form.setCodeAndMessage("1","success");
		return form;
	}
	//修改单个详情图片
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/updateDetail",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm updateDetailsImgCommodiry(Integer did,String img
			,MultipartHttpServletRequest request) {
		MobileJsonForm form = new MobileJsonForm();
		String localUrl = request.getRequestURL().toString().
				replace("/adminCommodiry/updateDetail", "")+C_IMG_DIR;
		String oldfile= img.replace(localUrl, "");
		String realPath =request.getRealPath(C_IMG_DIR);
		
		MultipartFile multifile = request.getFile("file");
		//上数据库
		Destail detail =new Destail();
		BufferedImage bufferedImage;
		try {
			
			bufferedImage = ImageIO.read(multifile.getInputStream());
			detail.setHeight(bufferedImage.getHeight());
			detail.setWidth(bufferedImage.getWidth());
			detail.setDid(did);
			detail.setImage(UploadUtils.uploadToServer(realPath, multifile));
			if(commodiryAdminExDao.updateDetailById(detail)!=true) {
				form.setCodeAndMessage("2", "exception");
				return form;
			}
			UploadUtils.clearOldImage(realPath, Arrays.asList(oldfile));
		} catch (IOException e) {
			e.printStackTrace();  
			form.setCodeAndMessage("2", "exception");
			return form;
		}
		detail.setImage(localUrl+detail.getImage());
		form.addData("detail",detail);
		form.setCodeAndMessage("1","success");
		return form;
	}	
	
	
	
	//商品插入
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/addCommodiry",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm addCommodiry(Commodiry commodiry ,
			MultipartHttpServletRequest multiServlet) {
		
		String realPath = multiServlet.getRealPath("/adminCommodiry/addCommodiry");
		MultipartFile multiFile = multiServlet.getFile("file");
		String filename = UploadUtils.uploadToServer(realPath, multiFile);
		commodiry.setPhoto(filename);
		//放到数据库
		MobileJsonForm form = new MobileJsonForm();
		Integer cid = commodiryAdminExDao.addCommodiry(commodiry);
		if(cid==null) {
			form.setCodeAndMessage("2", "数据库异常");
			return form;
		}
		commodiry.setCid(cid);
		commodiry.setPhoto(multiServlet.getRequestURL().toString()+filename);
		//数据回显
		form.addData("commodiry", commodiry);
		form.setCodeAndMessage("1","success");
		return form;
	}
	
	
	
	//商品删除
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/deleteCommodiry",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm deleteCommodiry(Integer cid ,String photo,
			HttpServletRequest request) {
		System.out.println(cid + photo);
		MobileJsonForm form =new MobileJsonForm();
		commodiryAdminExDao.deleteCommodiryById(cid);
		String url = request.getRequestURL().toString().
				replace("/adminCommodiry/deleteCommodiry", "")+C_IMG_DIR;
		String oldfile = photo.replace(url, "");
		System.out.println(oldfile);
		UploadUtils.clearOldImage(request.getRealPath(C_IMG_DIR), Arrays.asList(oldfile));
		form.setCodeAndMessage("1","success");
			return form;
	}
	
	//商品修改
	@RequestMapping(value="/updateCommodiry",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm updateCommodiry(Commodiry commodiry ,
			MultipartHttpServletRequest multiServlet) {
		System.out.println(commodiry);
		MobileJsonForm form =new MobileJsonForm();
		MultipartFile multiFile = multiServlet.getFile("file");
		if(multiFile!=null) {  //修改了图片
			//图片的文件夹
			String localUrl = multiServlet.getRequestURL().toString().
					replace("/adminCommodiry/updateCommodiry", "")+C_IMG_DIR;
			//旧文件
			String oldFile = commodiry.getPhoto().replace(localUrl, "");
			@SuppressWarnings("deprecation")
			String realPath = multiServlet.getRealPath(C_IMG_DIR);
			//上传
			System.out.println(realPath);
			String target = UploadUtils.uploadToServer(realPath, multiFile);
			commodiry.setPhoto(target);
			System.out.println(commodiry.getPhoto());
			//上传数据库
			if(commodiryAdminExDao.updateCommodiry(commodiry)==false) {
				form.setCodeAndMessage("2","数据库操作异常");
				return form;
			}
			UploadUtils.clearOldImage(realPath, Arrays.asList(oldFile));
			//回显路径
			commodiry.setPhoto(localUrl+target);
			form.addData("commodiry", commodiry);  //修改的数据回显
			form.setCodeAndMessage("1","success");
			return form;
		}
		String oldfile = commodiry.getPhoto();
		commodiry.setPhoto(null);
		if(commodiryAdminExDao.updateCommodiry(commodiry)==false) {
			form.setCodeAndMessage("2","shujuku异常");
			return form;
		}	
		commodiry.setPhoto(oldfile);
		form.addData("commodiry", commodiry);  //修改的数据回显
		form.setCodeAndMessage("1","success");
		return form;
	}
	
	@RequestMapping(value="/keyword",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm keywordSearch(Integer tid,HttpServletRequest request,Integer page,
			String keyword) {
		//查询获取  总数+ limit8个数据
		String localUrl =request.getRequestURL().toString()
				.replace("/adminCommodiry/keyword", "")+C_IMG_DIR;
		List<Commodiry>list =null;
		if(keyword!=null&& !keyword.equals("")) {
			list=commodiryAdminExDao.conditionBykeyWord(tid,keyword,page);
			for(Commodiry c:list) {
				c.setPhoto(localUrl+c.getPhoto());
			}
			MobileJsonForm form = new  MobileJsonForm();
			form.addData("datasum", commodiryAdminExDao.getCommodiryKeywordByPageCount(tid,keyword));
			form.addData("commodirys", list);
			form.setCodeAndMessage("1", "success");
			return form;
		}
		//查询获取  总数+ limit8个数据
		List<Commodiry> commodirys = commodiryAdminDao.getCommodiryByPage(tid, page);
		for(Commodiry c:commodirys) {
			c.setPhoto(localUrl+c.getPhoto());
		}
		MobileJsonForm form = new  MobileJsonForm();
		form.addData("datasum", commodiryAdminExDao.getCommodiryByPageCount(tid));
		form.addData("commodirys", commodirys);
		form.setCodeAndMessage("1", "success");
		
		return form;
	}
	
	
	@RequestMapping(value="/modifycolor",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyCommodifyColors(String color,Integer cid) {
		MobileJsonForm form = new MobileJsonForm();
		if(commodiryAdminExDao.modifyCommodiryColors(color,cid)) {
			form.setCodeAndMessage("1", "success");	
			return form;
		}	
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	@RequestMapping(value="/preAmount",method=RequestMethod.POST)
	@ResponseBody
	public MobileJsonForm modifyPreAmount(String preAmount) {
		MobileJsonForm form = new MobileJsonForm();
		otherInfoDao.clearByKeyAllInfo("preAmount");
		if(otherInfoDao.savePreAmount(preAmount)!=true) {
			form.setCodeAndMessage("2", "database exception");
			return form;
		}
	
		form.setCodeAndMessage("1", "success");
		return form;
	}
	
	
	
		
}
