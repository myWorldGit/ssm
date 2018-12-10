package com.lanpangzi.utils.ali;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.lanpangzi.pojo.Limu;

public class AliPayUtils {
	private static final String CHARSET = "utf-8";
	private static final String FORMAT = "json";
	private static final String SIGN_TYPE = "RSA2";

	private String url;
	private String appId;
	private String appPrivateKey;
	private String appPublicKey;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppPrivateKey() {
		return appPrivateKey;
	}

	public void setAppPrivateKey(String appPrivateKey) {
		this.appPrivateKey = appPrivateKey;
	}

	public String getAppPublicKey() {
		return appPublicKey;
	}

	public void setAppPublicKey(String appPublicKey) {
		this.appPublicKey = appPublicKey;
	}

	public static String getCharset() {
		return CHARSET;
	}

	public static String getFormat() {
		return FORMAT;
	}

	public static String getSignType() {
		return SIGN_TYPE;
	}

	@Override
	public String toString() {
		return "AliPayUtils [url=" + url + ", appId=" + appId + ", appPrivateKey=" + appPrivateKey + ", appPublicKey="
				+ appPublicKey + "]";
	}

	public String alipayInterface(String cname,String amount, String outtradeno,String notify) {
		AlipayClient alipayClient = new DefaultAlipayClient(url, appId, appPrivateKey, FORMAT, CHARSET, appPublicKey,
				SIGN_TYPE);
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody("我是测试数据");

		model.setSubject(cname);
		model.setOutTradeNo(outtradeno);
		model.setTimeoutExpress("30m");
		model.setTotalAmount(amount);
		model.setProductCode("QUICK_MSECURITY_PAY");
		//System.out.println(notify);
		request.setNotifyUrl(notify);
		request.setBizModel(model);
		try {
			// 这里和普通的接口调用不同，使用的是sdkExecute
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
			//System.out.println(response.getBody());
			if (response.isSuccess()) {
				System.out.println("调用成功");
			} else {
				System.out.println("调用失败");
			}
			if (response.isSuccess()) {
				return response.getBody();
			} else {
				return "fail";
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return "fail";
		}
	}

	public Integer aliPayCallback(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		try {
			// 调用SDK验证签名
			boolean flag = AlipaySignature.rsaCheckV1(params, this.appPublicKey,
					CHARSET, "RSA2");
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("out_trade_no"+out_trade_no);
			System.out.println("trade_no"+trade_no);
			System.out.println("trade_status"+trade_status);
			System.out.println("total_amount"+total_amount);
			
			
			if (trade_status!=null && trade_status.equals("TRADE_SUCCESS")) {
				return Integer.parseInt(out_trade_no);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public Limu aliPayPreAmountCallback(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		try {
			// 调用SDK验证签名
			boolean flag = AlipaySignature.rsaCheckV1(params, this.appPublicKey,
					CHARSET, "RSA2");
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
			System.out.println("out_trade_no"+out_trade_no);
			System.out.println("trade_no"+trade_no);
			System.out.println("trade_status"+trade_status);
			System.out.println("total_amount"+total_amount);
			
			
			if (trade_status!=null && trade_status.equals("TRADE_SUCCESS")) {
				Limu limu= new Limu();
				limu.setLid(Integer.valueOf(out_trade_no));
				limu.setIshaspay(Integer.valueOf(total_amount));
				return limu;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
