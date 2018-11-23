package com.lanpangzi.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	/**\
	 * 取文件后缀
	 * @param fileName
	 * @return
	 */
	public static String suffixName(String fileName) {
		int pointIndex =fileName.lastIndexOf('.');
		return fileName.substring(pointIndex).toLowerCase();
	}
	/**
	 * hash打散目录
	 * @param uuid
	 * @return
	 */
	public static String hashPath() {
		String uuid =UUID.randomUUID().toString().replace("-", "").toUpperCase();
		return "/"+uuid.charAt(0)+"/"+uuid.charAt(1)+"/"+uuid;
	}
	/**
	 * 不打散  针对小量
	 * @return
	 */
	public static String generalPath() {
		return "/"+UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	public static String uploadToServer(String realPath,MultipartFile multifile) {
		
			String suffix =UploadUtils.suffixName(multifile.getOriginalFilename());
			String Indatabase = UploadUtils.hashPath()+suffix;
			File target =new File(realPath + Indatabase);
			if(!target.exists()) {
				target.mkdirs();
			}
			try {
				multifile.transferTo(target);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		return Indatabase;
	}
	public static void clearOldImage(String realPath ,List<String> OldFileName) {
		for(String file: OldFileName) {
			if(file!=null || file!= ""  )
				new File(realPath+file).delete();
		}
	}
	public static String smallUpload(String realPath, MultipartFile multifile) {
		String suffix =UploadUtils.suffixName(multifile.getOriginalFilename());
		String fileName =generalPath()+suffix;
		File target = new File(realPath+fileName);
		if(!target.exists()) {
			target.mkdirs();
		}
		try {
			multifile.transferTo(target);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
