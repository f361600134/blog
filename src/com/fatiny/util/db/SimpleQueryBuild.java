package com.fatiny.util.db;

import java.util.LinkedList;

import com.fatiny.util.exception.SqlException;
import com.fatiny.util.exception.SqlExceptionEvent;
public class SimpleQueryBuild {
	
	private String tableName;
	private LinkedList<String> columnList;
	private LinkedList<String> conditionList;
	private static final String defaultCondition = "1 = 1";
	
	public SimpleQueryBuild(String tableName, LinkedList<String> columnList,
			LinkedList<String> conditionList) {
		super();
		this.tableName = tableName;
		this.columnList = columnList;
		this.conditionList = conditionList;
	}
	
	public SimpleQueryBuild(String tableName) {
		super();
		this.tableName = tableName;
		this.columnList = new LinkedList<String>();
		this.conditionList = new LinkedList<String>();
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * 添加多个sql值
	 * @param columns
	 */
	public void addColumns(String ...columns) {
		if(columns!=null)
		{
			for(String c : columns)
				this.columnList.add(this.tableName+"."+c);
		}
	}
	
	/**
	 * 添加一个sql值
	 * @param columns
	 */
	public SimpleQueryBuild addColumn(String columns) {
		if (columns != null) 
			this.columnList.add(this.tableName+"."+columns);
		
		return this;
	}
	
	/**
	 * 添加多个sql条件
	 * @param columns
	 */
	public void addConditions(String ...conditions) {
		if(conditions!=null)
		{
			for(String c : conditions)
				this.conditionList.add(c);
		}
	}
	
	/**
	 * 添加一个sql条件
	 * @param columns
	 */
	public SimpleQueryBuild addCondition(String condition) {
		if(condition!=null) 
				this.conditionList.add(condition);
		
		return this;
	}
	
	/**
	 * 
	* <p>Title: buildQuery</p>
	* <p>Description: 构造简单的select查询语句,单表查询</p>
	* @return
	* @throws SqlException
	 */
	public String buildSimpleQuery() throws SqlException
	{	
		if(this.tableName == null || this.tableName == "")
		{
			throw new SqlException(this.tableName,SqlExceptionEvent._notable);
		}
		
		StringBuilder sb = new StringBuilder("SELECT ");
		for(String filed : this.columnList)
		{
			sb.append(filed);
			if(!filed.equals(columnList.getLast()))
				sb.append(",");
			else
				sb.append(" ");
		}
		//条件为空
		if (this.columnList.isEmpty()) 
			sb.append("* ");
		
		sb.append("FROM ");
		sb.append(tableName+" ");
		sb.append("WHERE ");
		sb.append(SimpleQueryBuild.defaultCondition);
		
		for(String condition : this.conditionList)
		{
			sb.append(" AND ");
			sb.append(condition);
		}
		this.clear();
		return sb.toString();
	}
	
	
	
	/**
	 * 构建Hibernate Hql语句
	 */
	public String buildHqlQuery() throws SqlException{
		
		if(this.tableName == null || this.tableName == "")
		{
			throw new SqlException(this.tableName,SqlExceptionEvent._notable);
		}
		
		StringBuilder sb = new StringBuilder("FROM ");
		sb.append(tableName+" ");
		sb.append("WHERE ");
		sb.append(SimpleQueryBuild.defaultCondition);
		
		for(String condition : this.conditionList)
		{
			sb.append(" AND ");
			sb.append(condition);
		}
		this.clear();
		return sb.toString();
	}
	
	private void clear(){
		this.columnList.clear();
		this.conditionList.clear();
	}
	
}
