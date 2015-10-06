package com.fatiny.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="project")
public class Project implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;				//id
	private String title;		//标题
	private String categories;	//描述
	private String detail;		//详细描述
	private Date finishDate;	//项目完成时间
	private String downloadurl;	//下载链接
	
	//不入库
	private String pics;
	//图片路径不入库数据,配置多对一关系
	private List<Picture> pictures;
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {return id;}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="downloadurl")
	public String getDownloadurl() {return downloadurl;}
	public void setDownloadurl(String downloadurl) {
		this.downloadurl = downloadurl;
	}
	@Column(name="title")
	public String getTitle() {return title;}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="categories")
	public String getCategories() {return categories;}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	@Column(name="finishDate")
	public Date getFinishDate() {
		return finishDate;
	}
	@Column(name="detail")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Picture> getPictures() {return pictures;}
	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	public void addPictures(Picture picture) {
		this.pictures.add(picture);
	}
	
	@Transient
	public String getPics() {return pics;}
	public void setPics(String pics) {
		this.pics = pics;
	}
	
	@Override
	public String toString() {
		return "Project [id=" + id + ", title=" + title + ", categories=" + categories
				+ ", downloadurl=" + downloadurl + ", pics=" + pics
				+ ", pictures=" + pictures + "]";
	}
	public Project() {
		super();
		this.pictures = new ArrayList<Picture>();
	}
	
}
