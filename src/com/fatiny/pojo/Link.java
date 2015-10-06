package com.fatiny.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 友情链接
 * @author Jemery
 */
@Entity
@Table(name="link")
public class Link implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;			//主键id
	private String name;	//网站名称
	private String site;	//主页地址
//	private String pic;		//博客大图
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	@Column(name="name")
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	@Column(name="site")
	public String getSite() {return site;}
	public void setSite(String site) {this.site = site;}
	
	public Link() {}
	public Link(String name, String site) {
		this.name = name;
		this.site = site;
	}
	public Link(int id, String name, String site) {
		super();
		this.id = id;
		this.name = name;
		this.site = site;
	}
	
	@Override
	public String toString() {
		return "link [id=" + id + ", name=" + name + ", site=" + site + "]";
	}
	

}
