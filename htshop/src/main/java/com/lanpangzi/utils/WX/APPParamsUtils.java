package com.lanpangzi.utils.WX;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.github.wxpay.sdk.WXPayUtil;

public class APPParamsUtils {
	
	private String PrepayId_url;
	private String key;
	private String mchid;
	private String appid; 
	
	
	public String getPrepayId_url() {
		return PrepayId_url;
	}
	public void setPrepayId_url(String prepayId_url) {
		PrepayId_url = prepayId_url;
	}
	/**
	 * 随机字符串
	 * @return
	 */
	public String getRandomString() {
		return WXPayUtil.generateNonceStr();
	}
	/**
	 * 时间戳
	 * @return
	 */
	public String getTimestamp() {
		return String.valueOf(System.currentTimeMillis());
	}
	/**
	 * 获取签名   
	 * @return
	 * @throws Exception 
	 */
	public String getSign(final Map<String, String> data) throws Exception {
		return WXPayUtil.generateSignature(data, this.key);
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMchid() {
		return mchid;
	}
	public void setMchid(String mchid) {
		this.mchid = mchid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	/**
	 * 获取prepayid
	 * @return
	 * @throws Exception 
	 */
	public  String getprepayId(String body,String oid) throws Exception {
		Map<String,String> params =new HashMap<>();
		params.put("appid", this.appid);
		params.put("mch_id", this.mchid);
		params.put("nonce_str", this.getRandomString());
		params.put("body", body); 
		params.put("out_trade_no", oid);
	    // 这里写的金额为1 分到时修改
		params.put("total_fee", "1");
		params.put("spbill_create_ip", InetAddress.getLocalHost().getHostName());
		params.put("notify_url", "");
		params.put("trade_type", "APP");
		params.put("sign", this.getSign(params));
		String xmlParams = WXPayUtil.mapToXml(params); 
		return HttpClientUtils.postXML(PrepayId_url, xmlParams);
	}
	
	
	
}
