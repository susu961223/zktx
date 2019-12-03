package com.xiaoshu.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtils
{
  public static String getNewYear()
  {
    Calendar cal = Calendar.getInstance();
    String year = String.valueOf(cal.get(1));
    return year;
  }
  
  public static String getNewMouth()
  {
    Calendar cal = Calendar.getInstance();
    String month = "";
    if ((cal.get(2) + 1 >= 1) && (cal.get(2) + 1 <= 9)) {
      month = "0" + String.valueOf(cal.get(2) + 1);
    } else {
      month = String.valueOf(cal.get(2) + 1);
    }
    return month;
  }
  
  public static String getNewDay()
  {
    Calendar cal = Calendar.getInstance();
    String day = "";
    if ((cal.get(5) >= 0) && (cal.get(5) <= 9)) {
      day = "0" + String.valueOf(cal.get(5));
    } else {
      day = String.valueOf(cal.get(5));
    }
    return day;
  }
  
  public static String getDate()
  {
    Date date = new Date();
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String nowdate = sdf1.format(date);
    return nowdate;
  }
  
  public static String getDates(Date date)
  {
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    String nowdate = sdf1.format(date);
    return nowdate;
  }
  
  public static String getCalendar()
  {
    Calendar now = Calendar.getInstance();
    String year = String.valueOf(now.get(1));
    String month = String.valueOf(now.get(2) + 1);
    String day = String.valueOf(now.get(5));
    String hours = String.valueOf(now.get(11));
    String minute = String.valueOf(now.get(12));
    String second = String.valueOf(now.get(13));
    return year + month + day + hours + minute + second;
  }
  
  public static String getRandomString(int length)
  {
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++)
    {
      int number = random.nextInt(3);
      long result = 0L;
      switch (number)
      {
      case 0: 
        result = Math.round(Math.random() * 25.0D + 65.0D);
        
        sb.append(String.valueOf((char)(int)result));
        break;
      case 1: 
        result = Math.round(Math.random() * 25.0D + 97.0D);
        sb.append(String.valueOf((char)(int)result));
        break;
      case 2: 
        sb.append(String.valueOf(new Random().nextInt(10)));
      }
    }
    return sb.toString();
  }
}
