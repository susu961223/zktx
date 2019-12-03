package com.xiaoshu.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	/**
	 * 获取系统时间
	 * */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		String nowdate=sdf1.format(date);
		return nowdate;
	}
	/**
	 * 获取日历
	 * */
	public static String getCalendar() {
		Calendar now = Calendar.getInstance();  
		String year=String.valueOf(now.get(Calendar.YEAR));
		String month=String.valueOf((now.get(Calendar.MONTH) + 1)) ;
		String day=String.valueOf(now.get(Calendar.DAY_OF_MONTH));
		String hours=String.valueOf(now.get(Calendar.HOUR_OF_DAY));
		String minute=String.valueOf(now.get(Calendar.MINUTE));
		String second=String.valueOf(now.get(Calendar.SECOND));
		return year+month+day+hours+minute+second;
	}
	/**
	 * 产生随机数
	 * */
	public static String getRandomString(int length){
	    //产生随机数
	    Random random=new Random();
	    StringBuffer sb=new StringBuffer();
	    //循环length次
	    for(int i=0; i<length; i++){
	      //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
	      int number=random.nextInt(3);
	      long result=0;
	      switch(number){
		      //如果number产生的是数字0；
		      case 0:
		        //产生A-Z的ASCII码
		        result=Math.round(Math.random()*25+65);
		        //将ASCII码转换成字符
		        sb.append(String.valueOf((char)result));
		      break;
		      case 1:
		          //产生a-z的ASCII码
		          result=Math.round(Math.random()*25+97);
		          sb.append(String.valueOf((char)result));
		      break;
		      case 2:
		          //产生0-9的数字
		          sb.append(String.valueOf(new Random().nextInt(10)));
		      break; 
	      }
	    }
	    return sb.toString();
	  }
}
