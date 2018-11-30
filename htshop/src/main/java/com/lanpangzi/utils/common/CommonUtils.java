package com.lanpangzi.utils.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtils{
	public static List<String> mapToList(Map<String,String> map){
		List<String> list =new ArrayList <String>();
		Set<String> sets = map.keySet();
		for (String key :sets) {
			list.add(map.get(key));
		}
		return list;
	}
	public static String firstWordToBig(String str) {
		return str.toUpperCase().charAt(0)+str.substring(1);
	}
}
