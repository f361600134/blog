package com.fatiny.util;

import java.util.Map;
import org.springframework.web.context.WebApplicationContext;


public class SpringUtil {
	
	private static WebApplicationContext applicationContext;
	
	public static void setApplicationContext(
			WebApplicationContext applicationContext) {
		SpringUtil.applicationContext = applicationContext;
	}
	
	public static Object getBean(String name){
		Object obj = null;
		if(applicationContext != null){
			obj = applicationContext.getBean(name);
		}
		return obj;
		
	}
	
	public static Map<?,?> getBeansOfType(Class<?> clz){
		Map<?,?> obj = null;
		if(obj == null){
			if(applicationContext != null){
				obj = applicationContext.getBeansOfType(clz);
			}
		}
		return obj;
	}
	
	public static <T> T getBean(Class<T> cls){
		if(applicationContext != null){
			return applicationContext.getBean(cls);
		}
		return null;
	}
}
