package com.fatiny.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 标签,用于分类管理
 * @author Jemery
 */

@Entity
@Table(name="tag")
public class Tag implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;		//标签id
	private String name;//标签名称
	
	//不入库数据,配置多对一关系
	private Blog blog;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {return id;}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {return name;}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	public Blog getBlog() {return blog;}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	public Tag() {}
	public Tag(String name) {
		this.name = name;
	}
	public Tag(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
	}
	
	
}
