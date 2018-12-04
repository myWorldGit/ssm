package com.lanpangzi.utils.WX;

import java.util.HashMap;
import java.util.Iterator; 
import java.util.Map;
import java.util.Map.Entry; 
import java.util.Set;
import java.util.SortedMap; 
import java.util.TreeMap;
import org.dom4j.Document; 
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


import com.github.wxpay.sdk.WXPay;

public class WXService {
	 private WXPay wxpay;
	 private WXPayConfigImpl config; 
	 private static WXService INSTANCE; 
	 private WXService() throws Exception {
		 config = WXPayConfigImpl.getInstance();
		 wxpay = new WXPay(config); 
	 }
	 public static WXService getInstance() throws Exception {
		 if (INSTANCE == null) {
			 synchronized (WXPayConfigImpl.class) {
				 if (INSTANCE == null) { 
					 INSTANCE = new WXService(); 
				 } 
			 }
		 }
		 return INSTANCE; 
	 } 
	 /**
	  * 微信下单
	  * @param out_trade_no
	  * @param body
	  * @param money
	  * @param applyNo
	  * @return
	  */ 
     public String doUnifiedOrder(String out_trade_no, String body, Double money, String applyNo) {
    	 String amt = String.valueOf(money * 100);
         HashMap<String, String> data = new HashMap<String, String>();
         data.put("body", body);
         data.put("out_trade_no", out_trade_no);
         data.put("device_info", "web");
         data.put("fee_type", "CNY");
         data.put("total_fee", amt.substring(0, amt.lastIndexOf(".")));
         data.put("spbill_create_ip", config.getSpbillCreateIp());
         data.put("notify_url", config.getNotifUrl());
         data.put("trade_type", config.getTradeType());
         data.put("product_id", applyNo);
         System.out.println(String.valueOf(money * 100));
         try {
        	 Map<String, String> r = wxpay.unifiedOrder(data); 
        	 return r.get("code_url");
         } catch (Exception e) { 
        	 e.printStackTrace(); 
        	 return null; 
         }

     }
     
     /**
      * 退款 已测试
      */
     public void doRefund(String out_trade_no, String total_fee) { 
    	 String amt = String.valueOf(Double.parseDouble(total_fee) * 100);
    	 HashMap<String, String> data = new HashMap<String, String>(); 
    	 data.put("out_trade_no", out_trade_no); 
    	 data.put("out_refund_no", out_trade_no);
    	 data.put("total_fee", amt.substring(0, amt.lastIndexOf(".")));
    	 data.put("refund_fee", amt.substring(0, amt.lastIndexOf("."))); 
    	 data.put("refund_fee_type", "CNY");
    	 data.put("op_user_id", config.getMchID()); 
    	 try { 
    		 Map<String, String> r = wxpay.refund(data);
    		 System.out.println(r);
    	 } catch (Exception e) { 
    		 e.printStackTrace();
    	 } 
     }
     /**
      * 微信验签接口
      * 
      * @param out_trade_no
      * @param body
      * @param money
      * @param applyNo
      * @return
      * @throws DocumentException 
      */
     public boolean checkSign(String strXML) throws DocumentException {
    	 SortedMap<String, String> smap = new TreeMap<String, String>();
    	 Document doc = DocumentHelper.parseText(strXML); 
    	 Element root = doc.getRootElement(); 
    	 for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
    		 Element e = (Element) iterator.next();
    		 smap.put(e.getName(), e.getText()); 
    	 } 
    	 return isWechatSign(smap,config.getKey());
     }
     private boolean isWechatSign(SortedMap<String, String> smap,String apiKey) { 
    	 StringBuffer sb = new StringBuffer(); 
    	 Set<Entry<String, String>> es = smap.entrySet(); 
    	 Iterator<Entry<String, String>> it = es.iterator();
    	 while (it.hasNext()) { 
    		 Entry<String, String> entry = it.next();
    		 String k = (String) entry.getKey();
    		 String v = (String) entry.getValue(); 
    		 if (!"sign".equals(k) && null != v && !"".equals(v) && !"key".equals(k)) {
    			 sb.append(k + "=" + v + "&"); 
    		 } 
    	 } 
    	 sb.append("key=" + apiKey); /** 验证的签名 */ 
    	 String sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
    	 /** 微信端返回的合法签名 */ 
    	 String validSign = ((String) smap.get("sign")).toUpperCase();
    	 return validSign.equals(sign); 
    }
     
  
  
    
 
}
