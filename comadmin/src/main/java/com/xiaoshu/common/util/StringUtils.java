package com.xiaoshu.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	
	private static String result;
	
	public static String toString(Object Str) {
		if(Str != null) {
			result  = Str.toString();
		}else {
			result = " ";
		}
		return result;
	}
	
	public static String toDate(Date Str) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(Str != null) {
			result  = sdf.format(Str);
		}else {
			result = " ";
		}
		return result;
	}
}
