package com.lanpangzi.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OssInfoUtils {
	public final static String FILE_PATH="OSS.properties";
	public static String IMAGE_PATH;
  	static {
  		InputStream in =Thread.currentThread().
  			getContextClassLoader().getResourceAsStream(FILE_PATH);
  		try {
			Properties prop =new Properties();
			prop.load(in);
			IMAGE_PATH = prop.getProperty("imagePath");	
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
  	}
	  
	public static String getIMAGE_PATH() {
		return IMAGE_PATH;
	}

}
