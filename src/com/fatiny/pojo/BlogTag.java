package com.fatiny.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blogtag")
public class BlogTag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int tid;
	private int bid;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="tid")
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	
	@Column(name="bid")
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public BlogTag() {}
	public BlogTag(int tid, int bid) {
		this.tid = tid;
		this.bid = bid;
	}
	public BlogTag(int id, int tid, int bid) {
		super();
		this.id = id;
		this.tid = tid;
		this.bid = bid;
	}
	
	@Override
	public String toString() {
		return "blogTag [id=" + id + ", tid=" + tid + ", bid=" + bid + "]";
	}
	
	public static void main(String[] args) {
		String [] str = new String[]{"1","2"};
		List<String> list=Arrays.asList(str);
		System.out.println(list);
	}
	
	
}
