package com.lanpangzi.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
/**
 * 用于阿里短信发送
 * @author Administrator
 *
 */
public class AliMessageSend {

	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	// private static String mobile = "电话";
	private static String accessKeyId = "0Vmqvetw5DjHaKHA";
	private static String accessKeySecret = "FmQcvowKtxpFkVSSWAcQw3BaApixWX";
	private static String signName = "蓝胖子";
	private static String templeteCode = "SMS_150577652";
//	private static String templeteCode = "短信模版Code(一般都是SMS_********格式)";

	// 调用短信接口
	// 发送短信方法
	public static SendSmsResponse sendSms(String phone,String code) {
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", product, domain);

			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();

			// 必填:待发送手机号
			request.setPhoneNumbers(phone);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(signName);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templeteCode);

			// 可选:模板中的变量替换JSON串,如模板内容为"尊敬的用户,您的验证码为${code}"时,此处的值为
//		String jsonParam = "{\"code\":\"1234\"}";
			

			String jsonParam = "{\"code\":\"" + code + "\"}";
			request.setTemplateParam(jsonParam);

			// hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			return sendSmsResponse;
		} catch (ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
