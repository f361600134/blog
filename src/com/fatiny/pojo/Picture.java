package com.fatiny.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="picture")
public class Picture implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;			//id
	private String picUrl;	//图片路径
//	private int proId;
//	private Project pro;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {return id;}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name="picurl")
	public String getPicUrl() {return picUrl;}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
//	@ManyToOne(cascade={},fetch = FetchType.LAZY)
//	@JoinColumns({  
//     @JoinColumn(name = "id",nullable = false, insertable = false, updatable = false)   
//	})  
//	public Project getPro() {return pro;}
//	public void setPro(Project pro) {
//		this.pro = pro;
//	}

//	@Column(name="proid")
//	public int getProId() {return proId;}
//	public void setProId(int proId) {
//		this.proId = proId;
//	}
	
	@Override
	public String toString() {
		return "Picture [id=" + id + ", picUrl=" + picUrl + "]";
	}
	
	public Picture(){}
	public Picture(String picUrl) {
		super();
		this.picUrl = picUrl;
	}
}
