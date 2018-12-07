package com.lanpangzi.utils.limu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

import com.lanpangzi.utils.WX.HttpClientUtils;
import com.lanpangzi.utils.common.MDUtil;
import com.lanpangzi.utils.common.StringUtils;

public class LAJILimuCommonUtils {
	private static final String METHOD="api.common.getResult";
	/**
	 * 按照这个进行查询业务
	 * @param token   token的东西
	 * @param bizType  业务类型
	 * @return
	 */
	public static String getInfoByToken(String token,String bizType) {
		List<BasicNameValuePair> params = getPararms(token, bizType);
		return HttpClientUtils.doPostLimu(LimuInfomationUtils.url,params);
	}
	public static List<BasicNameValuePair> getPararms(String token,String bizType){
		List<BasicNameValuePair> params =new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("method", METHOD));
		params.add(new BasicNameValuePair("apiKey", LimuInfomationUtils.api_key));
		params.add(new BasicNameValuePair("version", LimuInfomationUtils.version));
		params.add(new BasicNameValuePair("bizType", bizType));
		params.add(new BasicNameValuePair("token", token));
		String sign = getSign(params);
		params.add(new BasicNameValuePair("sign", sign));
		return params;
	}
	
	public static String getSign(List<BasicNameValuePair> reqParam) {
		StringBuffer sbb = new StringBuffer();
        int index = 1;
        for (BasicNameValuePair nameValuePair : reqParam) {
            sbb.append(nameValuePair.getName() + "=" + nameValuePair.getValue());
            if (reqParam.size() != index) {
                sbb.append("&");
            }
            index++;
        }
        String sign = sbb.toString();

        Map<String, String> paramMap = new HashMap<String, String>();
        String ret = "";
        if (!StringUtils.isEmpty(sign)) {
            String[] arr = sign.split("&");
            for (int i = 0; i < arr.length; i++) {
                String tmp = arr[i];
                if (-1 != tmp.indexOf("=")) {
                    paramMap.put(tmp.substring(0, tmp.indexOf("=")), tmp.substring(tmp.indexOf("=") + 1, tmp.length()));
                }

            }
        }
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(paramMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                int ret = 0;
                ret = o1.getKey().compareTo(o2.getKey());
                if (ret > 0) {
                    ret = 1;
                } else {
                    ret = -1;
                }
                return ret;
            }
        });

        StringBuilder sbTmp = new StringBuilder();
        for (Map.Entry<String, String> mapping : list) {
            if (!"sign".equals(mapping.getKey())) {
                sbTmp.append(mapping.getKey()).append("=").append(mapping.getValue()).append("&");
            }
        }
        sbTmp.setLength(sbTmp.lastIndexOf("&"));
        sbTmp.append(LimuInfomationUtils.api_secret);
        ret = MDUtil.SHA1(sbTmp.toString());
        return ret;
    }

}
