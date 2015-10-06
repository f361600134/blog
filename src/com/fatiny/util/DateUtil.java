package com.fatiny.util;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
public class DateUtil {
	
	private static Logger log = LogContext.LOG_MODULE_UTILS;

	/** 格式：年－月－日 小时：分钟：秒  **/
	public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";

	/** 格式：年－月－日 小时：分钟 **/
	public static final String FORMAT_TWO = "yyyy-MM-dd HH:mm";

	/** 格式：年月日 小时分钟秒 **/
	public static final String FORMAT_THREE = "yyyyMMdd-HHmmss";

	/** 格式：年－月－日 **/
	public static final String DATE_FORMAT_LINE = "yyyy-MM-dd";
	
	/** 格式：月/日/年*/
	public static final String DATE_FORMAT_BIAS = "MM/dd/yyyy";
	
	/** 格式：月/日/年 at 时:分 ap/pm **/
	public static final String DATE_FORMAT_SPRCIFIC = "MM/dd/yyyy 'at' HH:mm a E";
	
	/** 格式：月－日 **/
	public static final String SHORT_DATE_FORMAT = "MM-dd";

	/** 格式：小时：分钟：秒 **/
	public static final String LONG_TIME_FORMAT = "HH:mm:ss";
	
    /** yyyyMMddHHmmss 紧凑类型 **/
	public static final String LONG_DATE_COMPACT_FORMAT = "yyyyMMddHHmmss" ;
	
	/*--------------------------------------------------------*/
	/* Jemery
	/*--------------------------------------------------------*/ 
	/**
	 * 获取当前的日期<p>
	 * 格式：月/日/年
	 * @return String
	 */
	public static String getBiasDay() {
		return DateUtil.DateToString(new Date(), DateUtil.DATE_FORMAT_BIAS);
	}
	
	/**
	 * blog左侧显示专用
	 * @param date
	 * @return
	 */
	public static String getBiasDay(Date date) {
		return DateUtil.DateToString(date, DateUtil.DATE_FORMAT_BIAS);
	}
	
	/**
	 * 具体的一个时间,很厉害<p>
	 * 格式：月/日/年 at 时:分 ap/pm
	 */
	public static String getSpecificDate() {
		return DateUtil.DateToString(new Date(), DateUtil.DATE_FORMAT_SPRCIFIC);
	}
	
	/**
	 * 具体的一个时间,很厉害<p>
	 * 格式：月/日/年 at 时:分 ap/pm
	 */
	public static String getSpecificDate(Date date) {
		return DateUtil.DateToString(date, DateUtil.DATE_FORMAT_SPRCIFIC);
	}
	/**
	 * 把日期转换为字符串
	 * @param date
	 * @return
	 */
	public static String DateToString(Date date, String format) {
		String result = "";
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			result = formater.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 把符合日期格式的字符串转换为日期类型
	 * @param dateStr
	 * @return
	 */
	public static Date StringtoDate(String dateStr, String format) {
		Date d = null;
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			formater.setLenient(false);
			d = formater.parse(dateStr);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return d;
	}
	
	/**
	 * 使用format来判断是否是时间日期.专用.
	 * 不报异常
	 * @param date
	 * @return
	 */
	public static boolean isDate(String dateStr){
		SimpleDateFormat formater = new SimpleDateFormat(DATE_FORMAT_BIAS);
		try {
			 formater.parse(dateStr);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
