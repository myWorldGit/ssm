package com.lanpangzi.utils.common;

import java.util.Properties;

import com.lanpangzi.utils.redis.PropertiesUtils;

public class LimuInfomationUtils {
	public static final String api_key;
	public static final String api_secret;
	public static final String version;
	static {
		Properties prop = PropertiesUtils.getProperties("limu.properties");
		api_key = prop.getProperty("api_key");
		api_secret = prop.getProperty("api_secret");
		version =prop.getProperty("version");
	}
}