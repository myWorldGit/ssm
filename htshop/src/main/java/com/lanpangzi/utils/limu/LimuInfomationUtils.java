package com.lanpangzi.utils.limu;

import java.util.Properties;

import com.lanpangzi.utils.redis.PropertiesUtils;

public class LimuInfomationUtils {
	public final static String MOBILE="mobile";
	public final static String TAOBAO="taobao";
	public final static String CREDIT="credit";
	
	public static final String api_key;
	public static final String api_secret;
	public static final String version;
	public static final String url;
	static {
		Properties prop = PropertiesUtils.getProperties("limu.properties");
		api_key = prop.getProperty("api_key");
		api_secret = prop.getProperty("api_secret");
		version =prop.getProperty("version");
		url = prop.getProperty("url");
	}
}
