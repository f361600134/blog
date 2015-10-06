package com.fatiny.pojo;

import java.util.Collections;
import java.util.List;

/**
 * 定制类,用于Ajax返回前端一些信息.
 * @author Jemery
 * @param <T>
 */
public class JqGrid<T> {
	
    private List<T> colModel;    //得到实际数据的数组搜索    
    private int bid; 			//前台获取不到bid,只能这样
	private Integer rows;       //每页中的记录行数    
    private Integer record;    	//总记录数   
    private Integer page;       //当前页码数    
    private Integer total;    	//总页数    
    private String sord;        //排序的方式    
    private String sidx;        //用于排序的列名   
    private String search;      //是否用于查询的请求
    
    public List<T> getColModel() {
		return colModel;
	}
	public void setColModel(List<T> colModel) {
		this.colModel = colModel;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	@Override
	public String toString() {
		return "JqGrid [colModel=" + colModel + ", rows=" + rows
				+ ", record=" + record + ", page=" + page + ", total=" + total
				+ ", sord=" + sord + ", sidx=" + sidx + ", search=" + search
				+ "]";
	}
	
	public JqGrid() {
		super();
		this.colModel = Collections.emptyList();
	}
	
	public JqGrid(List<T> gridModel, Integer rows, Integer record,
			Integer page, Integer total, String sord, String sidx, String search) {
		super();
		this.colModel = Collections.emptyList();;
		this.rows = rows;
		this.record = record;
		this.page = page;
		this.total = total;
		this.sord = sord;
		this.sidx = sidx;
		this.search = search;
	}
	 
    
}
