package com.lanpangzi.utils.limu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.github.wxpay.sdk.WXPayUtil;

public class LimuInfoTextFileUploadUtils {
	public static String uploadTextFile(String realPath ,String content) {
		
		String filename = getFilePath()+".text";
		File target = new File(realPath);
		if(!target.exists()) {
			target.mkdirs();
		}
		BufferedWriter buffWriter=null;
		try {
			buffWriter = new BufferedWriter(
					new FileWriter(new File(target,filename)));
					buffWriter.write(content);
					buffWriter.flush();
					
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(buffWriter!=null)
				try {
					buffWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return filename;
	}
	//UUID生成文件名
	public static String getFilePath() {
		return WXPayUtil.generateNonceStr();
	}
	public static String readTextFileByPath(String string) {
		StringBuilder bufferString  =new StringBuilder();
		String buf =null;
		BufferedReader bufferReader = null;
		try {
		    bufferReader = new BufferedReader(new FileReader(new File(string)));
		    while(null!=(buf=bufferReader.readLine())) {
			   bufferString.append(buf);
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bufferString.toString();
	}
	
	
	
	
}
