package com.lanpangzi.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class MobileJsonForm {
	private String code;
	private String message;
	private Map<String,Object> data=new LinkedHashMap<>();
	
	//直接插入Data
	public void addData(String key,Object value) {
		data.put(key, value);
	}
	
	public void setCodeAndMessage(String code,String message) {
		this.code = code;
		this.message=message;
	}

	public MobileJsonForm() {}
	public MobileJsonForm(String code) {
		this.code = code;
	}
	public MobileJsonForm(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public MobileJsonForm(String code, String message, Map<String, Object> data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
