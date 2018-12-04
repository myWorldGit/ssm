package com.lanpangzi.utils.WX;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.github.wxpay.sdk.WXPayConfig;

public class WXPayConfigImpl implements WXPayConfig{
	private byte[] certData; 
	private static WXPayConfigImpl INSTANCE;
	private WXPayConfigImpl() throws Exception{
		String certPath = "F:\\weixin\\apiclient_cert.p12";
		File file = new File(certPath);
		InputStream certStream = new FileInputStream(file); 
		this.certData = new byte[(int) file.length()];
		certStream.read(this.certData);
		certStream.close();
	}
	public static WXPayConfigImpl getInstance() throws Exception {
		if(INSTANCE == null ) {
			synchronized (WXPayConfigImpl.class) {
				 if (INSTANCE == null) {
	                 INSTANCE = new WXPayConfigImpl();
	             }
			}
		}
		return INSTANCE;   
	}

	
	@Override
	public String getAppID() {
		// TODO Auto-generated method stub
		return "wx7d4693b4cfc09d61";
	}
	@Override
	public String getMchID() {
		// TODO Auto-generated method stub
		return "1510300281";
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "ce0eb94b923d4b7bab50a142ad38b5fb";
	}
	@Override
	public InputStream getCertStream() {
		// TODO Auto-generated method stub
		return new ByteArrayInputStream(this.certData);
	}
	@Override
	public int getHttpConnectTimeoutMs() {
		// TODO Auto-generated method stub
		return 2000;
	}
	@Override
	public int getHttpReadTimeoutMs() {
		// TODO Auto-generated method stub
		return 10000;
	}
	
	public String getNotifUrl() { return "微信通知回调的url接口"; } 
	public String getTradeType() { return "NATIVE"; }
	public String getPrimaryDomain() { return "api.mch.weixin.qq.com"; } 
	public String getAlternateDomain() { return "api2.mch.weixin.qq.com"; } 
	public int getReportWorkerNum() { return 1; } 
	public int getReportBatchSize() { return 2; }
	public String getSpbillCreateIp() { return "192.168.1.1"; }
		
	
	
}
