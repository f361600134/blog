package com.fatiny.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页定制类
 * @author Jemery
 * @param <T>
 */
public class Page<T> {
	
	//sql pageNum*pageSize,pageSize
	private int pageNum;		//当前的页码
	private List<T> list;		//需要显示的数据
	private int pageSize = 6;	//每页默认显示的数据量
	private int totalNum;		//总记录数量
	

	public Page(int pageNum) {
		this.pageNum = pageNum;
		this.list = new ArrayList<T>();
	}
	
	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize="
				+ pageSize + ", totalNum=" + totalNum + "]";
	}

	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = (totalNum/5)+(totalNum % 5 > 0 ? 1 : 0);
		check();
	}
	
	/**
	 * 是否可以下一页
	 * @return
	 */
	public boolean isHasNext(){
		//TODO
		return false;
	}
	/**
	 * 是否可以上一页
	 * @return
	 */
	public boolean isHasPro(){
		//TODO
		return false;
	}
	

	private void check(){
		if (pageNum > totalNum) {
			pageNum = totalNum;
		}
		if (pageNum < 0) {
			pageNum = 0;
		}
	}
	
	/**
	 * 上一页
	 * @return
	 */
	public int getProPage(){
		check();
		return pageNum - 1 < 0 ? 0 :pageNum - 1 ;
	}
	/**
	 * 下一页
	 * 
	 * @return
	 */
	public int getNextPage(){
		check();
		return pageNum+1 > this.totalNum ? this.totalNum : pageNum+1 ;
	}
	
	
}
