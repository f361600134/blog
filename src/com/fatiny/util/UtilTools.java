package com.fatiny.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;

public class UtilTools {

	private static Logger log = LogContext.LOG_MODULE_UTILS;
	
	/**
	 * 把一个对象转成JSON格式，编码格式为UTF-8
	 */
	public static void renderJson(HttpServletResponse response, Object rst) {
		response.setCharacterEncoding("utf-8");
		try {
			String json = JSONObject.fromObject(rst).toString();
			response.getWriter().write(json);
		} catch (IOException e) {
			log.error(e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 把一个对象转成JSON格式，编码格式为UTF-8
	 */
	public static void renderJson(HttpServletResponse response, String rst) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(rst, true);
		response.setCharacterEncoding("utf-8");
		try {
			String json = JSONObject.fromObject(map).toString();
			response.getWriter().write(json);
		} catch (IOException e) {
			log.error(e.toString());
		}
	}
	
//	/**
//	 * 把一个Json格式,转map
//	 */
//	public static void json2Map(String jsonString){
////		String jsonString = "{\"ret\":0,\"msg\":\"\",\"is_lost\":0,\"nickname\":\"丶\",\"gender\":\"男\",\"province\":\"\",\"city\":\"加沙地带\",\"year\":\"2009\",\"figureurl\":\"http://qzapp.qlogo.cn/qzapp/101239300/1958485DA2A0BF925511C84B3B44AA55/30\",\"figureurl_1\":\"http://qzapp.qlogo.cn/qzapp/101239300/1958485DA2A0BF925511C84B3B44AA55/50\",\"figureurl_2\":\"http://qzapp.qlogo.cn/qzapp/101239300/1958485DA2A0BF925511C84B3B44AA55/100\",\"figureurl_qq_1\":\"http://q.qlogo.cn/qqapp/101239300/1958485DA2A0BF925511C84B3B44AA55/40\",\"figureurl_qq_2\":\"http://q.qlogo.cn/qqapp/101239300/1958485DA2A0BF925511C84B3B44AA55/100\",\"is_yellow_vip\":\"0\",\"vip\":\"0\",\"yellow_vip_level\":\"0\",\"level\":\"0\",\"is_yellow_year_vip\":\"0\"}";
////		System.out.println(jsonString);
//		org.json.JSONObject jsonObject = new org.json.JSONObject(jsonString);
//	    Map<String, Object> result = new HashMap<String, Object>();
//	    Iterator<?> iterator = jsonObject.keys();
//	    String key = null;
//	    Object value = null;
//
//	    while (iterator.hasNext()) {
//	        key = (String) iterator.next();
//	        value = jsonObject.get(key);
//	        result.put(key, value);
//	    }
//	    System.out.println(result);
//	    System.out.println(result.get("nickname"));;
//	}
	
	/**
	 * 比较强大的正则表达式判断日期,可以判断具体到时间.
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date){
		try {
			Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
			Matcher mat = pattern.matcher(date);
			return mat.matches();
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @param sql
	 * @param objs
	 * @return
	 */
	public static String format(String sql,Object ...objs){
		if (objs == null || objs.length == 0) {
			return sql;
		}
		if (sql == null) {
			return "NULL";
		}
		int i = 0;
		while (sql.indexOf("?") != -1 && i < objs.length) {
			if (objs[i] == null)
				sql = sql.replaceFirst("\\?", "");
			else{
				sql = sql.replaceFirst("\\?", String.valueOf(objs[i]));
			}
			i++;
		}
		return sql;
	}
	
	public static void main(String[] args) {
		String txt = "D:/Program Files/Java/Tomcat3264/webapps/wtpwebapps/blog/uploads/image/blog/201510/sm_1445486119274-image1.png";
		txt = txt.replaceFirst("D:/Program Files/Java/Tomcat3264/webapps/wtpwebapps/","");
		System.out.println(txt);
	}
	
	
	/**
	 * @param txt
	 * @param objs
	 * @return
	 */
	public static String formatForBlog(String txt,String tag){
		if (tag == null || tag.length() == 0) {
			return txt;
		}
		if (txt == null) {
			return "NULL";
		}
		while (txt.indexOf("?") != -1) {
			txt = txt.replaceFirst("\\?", String.valueOf(tag));
		}
		return txt;
	}
	
}	
