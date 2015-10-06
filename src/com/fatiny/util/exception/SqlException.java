package com.fatiny.util.exception;
 

public class SqlException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tableName;
	private String info;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	public SqlException(String tableName, String info) {
		super();
		this.tableName = tableName;
		this.info = info;
	}
	
	@Override
	public String toString() {
		return "util.db.SqlException [表:" + tableName + ", 异常原因:" + info +"]";
	}
	
	
}
