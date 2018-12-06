package com.lanpangzi.utils.limu;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanpangzi.utils.common.StringUtils;


public class JsonUtils {

    /**
     * json转化为bean
     *
     * @param json
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String json,Class<T> beanClass){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(json,beanClass);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    /**
     * bean转化为json
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static String beanToJson(Object bean) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(bean);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    
    
    public static JsonNode toJsonNode(String  json) {
        JsonNode rootNode = null;
        try{
            if(StringUtils.isNotBlank(json)) {
                ObjectMapper objectMapper = new ObjectMapper();
                rootNode = objectMapper.readValue(json, JsonNode.class);
            }
        }
        catch (JsonParseException ex){
            System.out.println("格式不正确："+json);
        }
        catch (Exception ex){
            System.out.println("toJsonNode：执行异常"+json);
        }
        return rootNode;
    }

	public static String getJsonValue(JsonNode root, String key) {
		 String retValue = "";
	        try{
	            if(root != null){
	                retValue = root.has(key) //是否含有该Key值
	                        ?root.get(key).toString().replaceAll("\"","") //含有Key 判断是否是字符串
	                        : "";//不含有Key
	                if("null".equals(retValue)){
	                    retValue = "";
	                }
	            }
	        }catch (Exception ex){
	            System.out.println("获取value值异常: Key:" +key);
	        }
        return retValue;
	}

	public static String getJsonValue(String json, String key) {
		String retValue = "";
        try{

            if(StringUtils.isNotBlank(json)){
                JsonNode rootNode = toJsonNode(json);
                retValue = getJsonValue(rootNode, key);
            }
        }
        catch (Exception ex){
            System.out.println("获取value值异常: Key:" +key +"    json:"+ json);
        }

        return retValue;
	}
}