package com.fatiny.util.db;

import com.fatiny.util.exception.SqlException;
import com.fatiny.util.exception.SqlExceptionEvent;

public class SimpleQueryUtil {
	
	/** 获取简单hibernate hql 普通语句*/
	public static String getSelectHql(String tableName)throws SqlException{
		if(tableName == null || tableName == "")
		{
			throw new SqlException(tableName,SqlExceptionEvent._notable);
		}
		StringBuilder sb = new StringBuilder("from "); 
		sb.append(tableName); 
		return sb.toString();
	}
	
	/** 获取hibernate hql 带查询条件语句*/
	public static String getSelectHql(String tableName,String ...conditions){
		String condition = "1 = 1 ";
		if(tableName == null || tableName == "")
		{
			throw new SqlException(tableName,SqlExceptionEvent._notable);
		}
		StringBuilder sb = new StringBuilder("from ");
		sb.append(tableName).append(" where ").append(condition);
		for (String str : conditions) {
			sb.append("and ");
			sb.append(str+" ");
		} 
		return sb.toString();
	}
	
	/** 获取hibernate hql 带查询条件语句*/
	@SuppressWarnings("rawtypes")
	public static String getSelectHql(Class c,String ...conditions){
		
		String clazz = c.getName();
		if(clazz == null || clazz == "")
		{
			throw new SqlException(clazz,SqlExceptionEvent._notable);
		}
		
		String condition = "1 = 1 ";
		StringBuilder sb = new StringBuilder("from ");
		sb.append(clazz+" ");
		sb.append("where "+condition);
		for (String str : conditions) {
			sb.append("and ");
			sb.append(str+" ");
		} 
		return sb.toString();
	}
	
	
	public static void main(String[] args) {
		String hql = SimpleQueryUtil.getSelectHql("user", " username ="+"a"," password="+"b");
		
		System.out.println(hql);
		
	}
	
}
