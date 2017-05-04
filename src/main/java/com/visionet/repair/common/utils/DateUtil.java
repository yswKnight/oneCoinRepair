package com.visionet.repair.common.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	/**
	 * 得到一个月中的最后一天或一个月的天数
	 * @param year  年
	 * @param month 月份
	 * @return int 返回天数
	 */
	public static int findMaxDay(int year,int month){
		Calendar time=Calendar.getInstance();    
		time.clear();    
		time.set(Calendar.YEAR,year); //year 为 int    
		time.set(Calendar.MONTH,month-1);//注意,Calendar对象默认一月为0              
		int day=time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数   
		return day;
	}
	/**
	 * Calendar转化为Date
	 * @param cal Calendar
	 * @return Date 返回的日期
	 */
	public static Date calendarToDate(Calendar cal){ 
		Date date=cal.getTime(); 
		return date;
	}
	/**
	 * Date转化为Calendar 
	 * @param date 需要转化的日期
	 * @return Calendar 返回的Calendar类型
	 */
	public static Calendar dateToCalendar(Date date){
		Calendar cal=Calendar.getInstance();    
		cal.setTime(date);
		return cal;
	}
	/**
	 * 格式化输出日期时间
	 * @param date 日期
	 * @param pattern  输出格式
	 * @return 返回的String
	 */
	public static String dateToStr(Date date,String pattern){
		SimpleDateFormat df=new SimpleDateFormat(pattern);    
		String time=df.format(date); 
		return time;
	};
	/**
	 * 计算两个日期间的相差天数
	 * @param startday 开始时间
	 * @param endday 结束时间
	 * @return 得到的相差天数(始终为正)
	 */
	public static int getDaysBetween(Date startday,Date endday){
		if(startday.after(endday)){    
            Date cal=startday;    
            startday=endday;    
            endday=cal;    
        }           
        long sl=startday.getTime();    
        long el=endday.getTime();          
        long ei=el-sl;              
        return (int)(ei/(1000*60*60*24));
	}
	
	/**
	 * 默认显示日期的格式 
	 */
    public static final String DATAFORMAT_STR = "yyyy-MM-dd";      
     /**
      * 默认显示日期的格式           
      */
    public static final String YYYY_MM_DATAFORMAT_STR = "yyyy-MM";      
    /**
     * 默认显示日期时间的格式      
     */      
    public static final String DATATIMEF_STR = "yyyy-MM-dd HH:mm:ss";      
     /**
      *  默认显示简体中文日期的格式       
      */   
    public static final String ZHCN_DATAFORMAT_STR = "yyyy年MM月dd日";      
    /**
     *  默认显示简体中文日期时间的格式     
     */     
    public static final String ZHCN_DATATIMEF_STR = "yyyy年MM月dd日HH时mm分ss秒";      
    /**
     *  默认显示简体中文日期时间的格式      
     */     
    public static final String ZHCN_DATATIMEF_STR_4yMMddHHmm = "yyyy年MM月dd日HH时mm分";      
          
    private static DateFormat dateFormat = null;      
          
    private static DateFormat dateTimeFormat = null;      
          
    private static DateFormat zhcnDateFormat = null;      
          
    private static DateFormat zhcnDateTimeFormat = null;      
    static{      
        dateFormat = new SimpleDateFormat(DATAFORMAT_STR);      
        dateTimeFormat = new SimpleDateFormat(DATATIMEF_STR);      
        zhcnDateFormat = new SimpleDateFormat(ZHCN_DATAFORMAT_STR);      
        zhcnDateTimeFormat = new SimpleDateFormat(ZHCN_DATATIMEF_STR);      
    }      
          
    private static DateFormat getDateFormat(String formatStr){      
        if(formatStr.equalsIgnoreCase(DATAFORMAT_STR)){      
            return dateFormat;      
        }else if(formatStr.equalsIgnoreCase(DATATIMEF_STR)){      
            return dateTimeFormat;      
        }else if(formatStr.equalsIgnoreCase(ZHCN_DATAFORMAT_STR)){      
            return zhcnDateFormat;      
        }else if(formatStr.equalsIgnoreCase(ZHCN_DATATIMEF_STR)){      
            return zhcnDateTimeFormat;      
        }else{      
            return new SimpleDateFormat(formatStr);      
        }      
    }      
          
    /**    
     * 按照默认显示日期时间的格式"yyyy-MM-dd HH:mm:ss"，转化dateTimeStr为Date类型    
     * dateTimeStr必须是"yyyy-MM-dd HH:mm:ss"的形式    
     * @param dateTimeStr    
     * @return 返回的日期类型   
     */     
    public static Date getDate(String dateTimeStr){      
        return getDate(dateTimeStr, DATATIMEF_STR);      
    }      
          
    /**    
     * 按照默认formatStr的格式，转化dateTimeStr为Date类型    
     * dateTimeStr必须是formatStr的形式    
     * @param dateTimeStr    
     * @param formatStr    
     * @return 返回的日期类型   
     */     
    public static Date getDate(String dateTimeStr, String formatStr){      
        try{      
            if(dateTimeStr == null || dateTimeStr.equals("")){      
                return null;      
            }      
            DateFormat sdf = getDateFormat(formatStr);      
            java.util.Date d = sdf.parse(dateTimeStr);      
            return d;      
        }catch (ParseException e){      
            throw new RuntimeException(e);      
        }      
    }      
          
    /**    
     * 将YYYYMMDD转换成Date日期    
     * @param date  需要转换的字符串  
     * @return  返回的日期类型  
     * @throws Exception
     */     
    public static Date transferDate(String date) throws Exception{      
        if (date == null || date.length() < 1){
        	return null; 
        }                 
        if (date.length() != 8){
        	throw new Exception("日期格式错误");  
        }                    
        String con = "-";                    
        String yyyy = date.substring(0, 4);      
        String mm = date.substring(4, 6);      
        String dd = date.substring(6, 8);                  
        int month = Integer.parseInt(mm);      
        int day = Integer.parseInt(dd);      
        if (month < 1 || month > 12 || day < 1 || day > 31){
        	throw new Exception("日期格式错误"); 
        }                  
        String str = yyyy + con + mm + con + dd;      
        return DateUtil.getDate(str, DateUtil.DATAFORMAT_STR);      
    }      
   
          
    /**    
     * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串    
     * @param date  需要转换的日期  
     * @return    返回的字符串
     */     
    public static String dateToDateString(Date date){      
        return dateToDateString(date, DATATIMEF_STR);      
    }      
          
    /**    
     * 将Date转换成formatStr格式的字符串    
     * @param date  需要转换的日期  
     * @param formatStr 转换的格式   
     * @return    返回的字符串
     */     
    public static String dateToDateString(Date date, String formatStr){      
        DateFormat df = getDateFormat(formatStr);      
        return df.format(date);      
    }      
          
    /**    
     * 返回一个yyyy-MM-dd HH:mm:ss 形式的日期时间字符串中的HH:mm:ss    
     * @param dateTime   需要转换的字符串 
     * @return    返回的字符串
     */     
    public static String getTimeString(String dateTime){      
        return getTimeString(dateTime, DATATIMEF_STR);      
    }      
          
    /**    
     * 返回一个formatStr格式的日期时间字符串中的HH:mm:ss    
     * @param dateTime  需要转换的字符串 
     * @param formatStr  转换的格式  
     * @return   返回的字符串 
     */     
    public static String getTimeString(String dateTime, String formatStr){      
        Date d = getDate(dateTime, formatStr);      
        String s = dateToDateString(d);      
        return s.substring(DATATIMEF_STR.indexOf('H'));      
    }      
          
    /**    
     * 获取当前日期yyyy-MM-dd的形式    
     * @return   返回的字符串 
     */     
    public static String getCurDate(){      
        //return dateToDateString(new Date(),DATAFORMAT_STR);      
        return dateToDateString(Calendar.getInstance().getTime(), DATAFORMAT_STR);      
    }      
          
    /**    
     * 获取当前日期yyyy年MM月dd日的形式    
     * @return    返回的字符串
     */     
    public static String getCurZhCNDate(){      
        return dateToDateString(new Date(), ZHCN_DATAFORMAT_STR);      
    }      
          
    /**    
     * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式    
     * @return    返回的字符串
     */     
    public static String getCurDateTime(){      
        return dateToDateString(new Date(), DATATIMEF_STR);      
    }      
          
    /**    
     * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式    
     * @return    返回的字符串
     */     
    public static String getCurZhCNDateTime(){      
        return dateToDateString(new Date(), ZHCN_DATATIMEF_STR);      
    }      
          
    /**    
     * 获取日期d的days天后的一个Date    
     * @param d   开始日期 
     * @param days  添加天数  
     * @return    返回的新日期
     */     
    public static Date getInternalDateByDay(Date d, int days){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        now.add(Calendar.DATE, days);      
        return now.getTime();      
    }      
    /**    
     * 获取日期d的months月后的一个Date    
     * @param d   开始日期 
     * @param months  添加月数  
     * @return    返回的新日期
     */        
    public static Date getInternalDateByMon(Date d, int months){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        now.add(Calendar.MONTH, months);      
        return now.getTime();      
    }      
    /**    
     * 获取日期d的years天后的一个Date    
     * @param d   开始日期 
     * @param years  添加年数  
     * @return    返回的新日期
     */        
    public static Date getInternalDateByYear(Date d, int years){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        now.add(Calendar.YEAR, years);      
        return now.getTime();      
    }      
    /**    
     * 获取日期d的sec秒后的一个Date    
     * @param d   开始日期 
     * @param sec  添加秒数  
     * @return    返回的新日期
     */        
    public static Date getInternalDateBySec(Date d, int sec){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        now.add(Calendar.SECOND, sec);      
        return now.getTime();      
    }      
    /**    
     * 获取日期d的min分钟后的一个Date    
     * @param d   开始日期 
     * @param min  添加分钟数  
     * @return    返回的新日期
     */        
    public static Date getInternalDateByMin(Date d, int min){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        now.add(Calendar.MINUTE, min);      
        return now.getTime();      
    }      
    /**    
     * 获取日期d的hours天后的一个Date    
     * @param d   开始日期 
     * @param hours  添加小时数  
     * @return    返回的新日期
     */        
    public static Date getInternalDateByHour(Date d, int hours){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        now.add(Calendar.HOUR_OF_DAY, hours);      
        return now.getTime();      
    }      
          
    /**    
     * 根据一个日期字符串，返回日期格式，目前支持4种    yyyy-MM-dd，yyyy-MM-dd，HH:mm:ss yyyy年MM月dd日，yyyy年MM月dd日HH时mm分ss秒
     * 如果都不是，则返回null    
     * @param DateString 传入的字符串   
     * @return    返回的字符串
     */     
    public static String getFormateStr(String DateString){      
        String patternStr1 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}"; //"yyyy-MM-dd"      
        String patternStr2 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"; //"yyyy-MM-dd HH:mm:ss";      
        String patternStr3 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";//"yyyy年MM月dd日"      
        String patternStr4 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[0-9]{1,2}时[0-9]{1,2}分[0-9]{1,2}秒";//"yyyy年MM月dd日HH时mm分ss秒"      
              
        Pattern p = Pattern.compile(patternStr1);      
        Matcher m = p.matcher(DateString);      
        boolean b = m.matches();      
        if (b){
        	return DATAFORMAT_STR;
        }                      
        p = Pattern.compile(patternStr2);      
        m = p.matcher(DateString);      
        b = m.matches();      
        if (b){
        	return DATATIMEF_STR; 
        }                   
        p = Pattern.compile(patternStr3);      
        m = p.matcher(DateString);      
        b = m.matches();      
        if (b){
        	return ZHCN_DATAFORMAT_STR;
        }                   
        p = Pattern.compile(patternStr4);      
        m = p.matcher(DateString);      
        b = m.matches();      
        if (b){
        	return ZHCN_DATATIMEF_STR;
        }                     
        return null;      
    }      
          
    /**    
     * 将一个"yyyy-MM-dd HH:mm:ss"字符串，转换成"yyyy年MM月dd日HH时mm分ss秒"的字符串    
     * @param dateStr 需要转换的字符串   
     * @return  返回的字符串
     */     
    public static String getZhCNDateTime(String dateStr){      
        Date d = getDate(dateStr);      
        return dateToDateString(d, ZHCN_DATATIMEF_STR);      
    }      
          
    /**    
     * 将一个"yyyy-MM-dd"字符串，转换成"yyyy年MM月dd日"的字符串    
     * @param dateStr  需要转换的字符串  
     * @return 返回的字符串 
     */     
    public static String getZhCNDate(String dateStr){      
        Date d = getDate(dateStr, DATAFORMAT_STR);      
        return dateToDateString(d, ZHCN_DATAFORMAT_STR);      
    }      
          
    /**    
     * 将dateStr从fmtFrom转换到fmtTo的格式    
     * @param dateStr   需要转换的字符串 
     * @param fmtFrom   原始格式 
     * @param fmtTo    需要转换的格式
     * @return    返回的字符串
     */     
    public static String getDateStr(String dateStr, String fmtFrom, String fmtTo){      
        Date d = getDate(dateStr, fmtFrom);      
        return dateToDateString(d, fmtTo);      
    }      
          
    /**    
     * 比较两个"yyyy-MM-dd HH:mm:ss"格式的日期，之间相差多少毫秒,time2-time1    
     * @param time1   开始时间 
     * @param time2   结束时间
     * @return   返回相关的毫秒 
     */     
    public static long compareDateStr(String time1, String time2){      
        Date d1 = getDate(time1);      
        Date d2 = getDate(time2);      
        return d2.getTime() - d1.getTime();      
    }      
          
    /**    
     * 将小时数换算成返回以毫秒为单位的时间    
     * @param hours    小时数
     * @return    返回的毫秒数
     */     
    public static long getMicroSec(BigDecimal hours) {      
        BigDecimal bd;      
        bd = hours.multiply(new BigDecimal(3600 * 1000));      
        return bd.longValue();      
    }      
          
    /**    
     * 获取Date中的分钟    
     * @param d   日期 
     * @return    返回的分钟
     */     
    public static int getMin(Date d){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        return now.get(Calendar.MINUTE);      
    }      
          
    /**    
     * 获取Date中的小时(24小时)    
     * @param d    日期 
     * @return    返回的小时（24时制）
     */     
    public static int getHour(Date d){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        return now.get(Calendar.HOUR_OF_DAY);      
    }      
          
    /**    
     * 获取Date中的秒    
     * @param d    日期 
     * @return    返回的秒
     */     
    public static int getSecond(Date d){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        return now.get(Calendar.SECOND);      
    }      
          
    /**    
     * 获取xxxx-xx-xx的日    
     * @param d    日期
     * @return    返回的日
     */     
    public static int getDay(Date d){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        return now.get(Calendar.DAY_OF_MONTH);      
    }      
          
    /**    
     * 获取月份，1-12月    
     * @param d    日期
     * @return    返回的月份
     */     
    public static int getMonth(Date d){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        return now.get(Calendar.MONTH) + 1;      
    }      
          
    /**    
     * 获取19xx,20xx形式的年    
     * @param d  日期  
     * @return   返回的年（4位数） 
     */     
    public static int getYear(Date d){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        now.setTime(d);      
        return now.get(Calendar.YEAR);      
    }      
          
    /**    
     * 得到d的上个月的年份+月份,如200505    
     * @return    返回的字符串
     */     
    public static String getYearMonthOfLastMon(Date d){      
        Date newdate = getInternalDateByMon(d, -1);      
        String year = String.valueOf(getYear(newdate));      
        String month = String.valueOf(getMonth(newdate));      
        return year + month;      
    }      
          
    /**    
     * 得到当前日期的年和月如200509    
     * @return String   返回字符串 
     */     
    public static String getCurYearMonth(){      
        Calendar now = Calendar.getInstance(TimeZone.getDefault());      
        String DATE_FORMAT = "yyyyMM";      
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);      
        sdf.setTimeZone(TimeZone.getDefault());      
        return (sdf.format(now.getTime()));      
    }      
    /**
     * 得到给定日期 的下一个月1号的日期     
     * @param year 指定的年
     * @param month 指定的月
     * @return 返回的新日期
     * 时间：2013-4-7 上午11:06:54
     * 作者：李晓健
     */
    public static Date getNextMonth(String year, String month){      
        String datestr = year + "-" + month + "-01";      
        Date date = getDate(datestr, DATAFORMAT_STR);      
        return getInternalDateByMon(date, 1);      
    }      
    /**
     * 得到给定日期的是一个月的1号      
     * @param year 指定的年
     * @param month 指定的月
     * @return 返回的新日期
     * 时间：2013-4-7 上午11:08:19
     * 作者：李晓健
     */
    public static Date getLastMonth(String year, String month){      
        String datestr = year + "-" + month + "-01";      
        Date date = getDate(datestr, DATAFORMAT_STR);      
        return getInternalDateByMon(date, -1);      
    }      
          
    /**    
     * 得到日期d，按照页面日期控件格式，如"2001-3-16"    
     * @param d  日期  
     * @return   返回的字符串 
     */     
    public static String getSingleNumDate(Date d){      
        return dateToDateString(d, DATAFORMAT_STR);      
    }      
          
    /**    
     * 得到d半年前的日期,"yyyy-MM-dd"    
     * @param d    日期
     * @return    返回的字符串
     */     
    public static String getHalfYearBeforeStr(Date d){      
        return dateToDateString(getInternalDateByMon(d, -6), DATAFORMAT_STR);      
    }      
          
    /**    
     * 得到当前日期D的月底的前/后若干天的时间,<0表示之前，>0表示之后    
     * @param d   日期 
     * @param days   相关的天数（可以为负） 
     * @return    返回的天数据
     */     
    public static String getInternalDateByLastDay(Date d, int days){                   
        return dateToDateString(getInternalDateByDay(d, days), DATAFORMAT_STR);      
    }      
          
    /**    
     * 日期中的年月日相加    
     *  @param field int  需要加的字段  年 月 日    
     * @param amount int 加多少    
     * @return String   返回的字符串 
     */     
    public static String addDate(int field, int amount){      
        int temp = 0;      
        if (field == 1){      
            temp = Calendar.YEAR;      
        }      
        if (field == 2){      
            temp = Calendar.MONTH;      
        }      
        if (field == 3){      
            temp = Calendar.DATE;      
        }      
              
        String Time = "";      
        try{      
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
            Calendar cal = Calendar.getInstance(TimeZone.getDefault());      
            cal.add(temp, amount);      
            Time = sdf.format(cal.getTime());      
            return Time;      
        }catch (Exception e){      
            e.printStackTrace();      
            return null;      
        }      
              
    }      
    /**
     * 得到 Calendar 的毫秒数              
     * @param cal
     * @return 返回的毫秒
     * 时间：2013-4-7 上午11:12:41
     * 作者：李晓健
     */
    public static String getStringDate(Calendar cal){                   
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");      
        return format.format(cal.getTime());      
    }
    /**
     * 根据年和周获取这周的第一天
     */
     public static Date getFirstDayOfWeekByYearAndWeek(){
    	 Calendar c = Calendar.getInstance();  
	     c.set(Calendar.YEAR, 2014);  
	     c.set(Calendar.WEEK_OF_YEAR, 16);  
	     c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);//设置周一  
	     c.setFirstDayOfWeek(Calendar.MONDAY);
	     return c.getTime();
     } 
     /**
      * 获取一个日期是星期几
      * @author 李晓健
      * @date 2014年4月11日 上午11:10:51
      * @param date
      * @return
      */
     public static int getDayOfWeek(Date date){
    	 Calendar cal = dateToCalendar(date);
    	 int weekday=cal.get(Calendar.DAY_OF_WEEK)-1;
    	 return weekday==0?7:weekday;
     }
    /**    
     * @param args    
     */     
	public static void main(String[] args){ 
//    	System.out.println(getDaysBetween(new Date("2013/03/01"), new Date("2013/03/31"))); 
    	System.out.println(getCurDate());
    }      
}
