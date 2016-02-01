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

import com.fatiny.util.DateUtil;

/**
 * 博客类,存储article等相关信息
 * @author Jemery
 *
 */
@Entity
@Table(name="blog")
public class Blog implements Serializable,Cloneable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int bid;			//主键id
	private String title;		//标题
	private String content;		//内容
	private String mdContent;	//原件
	private String author;		//作者
	private Date postDate;		//发表日期
	private String showDate;	//显示日期(为了不让发表日期在显示的时候频繁转换)
	private int cateId;			//文章类型
	private String media;		//多媒体
	private int viewTimes;		//阅读次数
	private int editorType;		//编辑器类型,UE or MK
	
	//编辑器静态公共类型
	public static final int UE	=	0;
	public static final int MK	=	1;
	
	//不做入库处理的数据
	private int count;		//评论数量
	private List<Tag> tags;	//标签集合
	private List<String> medias;//无需入库,前端使用
	
	@Id
	@GeneratedValue
	@Column(name="blogid")
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="content",columnDefinition="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="mdcontent",columnDefinition="text")
	public String getMdContent() {
		return mdContent;
	}
	public void setMdContent(String mdContent) {
		this.mdContent = mdContent;
	}
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="postdate")
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
		setShowDate(postDate);
	}
	@Column(name="showdate")
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public void setShowDate(Date postDate) {
		this.showDate = DateUtil.getBiasDay(postDate);
	}
	@Transient
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Column(name="cateid",columnDefinition="int default 0")
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	@Column(name="viewTimes",columnDefinition="int default 0")
	public int getViewTimes() {
		return viewTimes;
	}
	public void setViewTimes(int viewTimes) {
		this.viewTimes = viewTimes;
	}
	@Column(name="media")
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	@Column(name="editorType",columnDefinition="int default 0")
	public int getEditorType() {
		return editorType;
	}
	public void setEditorType(int editorType) {
		this.editorType = editorType;
	}
	
	@Transient
	public List<String> getMedias() {
		return medias;
	}
	public void setMedias(List<String> medias) {
		this.medias = medias;
	}
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	public Blog() {
		this.medias=new ArrayList<String>();
	}
	
	/*
	public Blog(String content) {
		this.content = content;
		this.medias=new ArrayList<String>();
	}
	*/
	
	public Blog(String title, String content, String author,String mdContent) {
		super();
		this.title = title;
		this.content = content;
		this.author = author;
		this.mdContent = mdContent;
		this.medias=new ArrayList<String>();
	}
	
	
	public Blog(String title, String content,String mdContent,int editorType) {
		super();
		this.title = title;
		this.content = content;
		this.mdContent = mdContent;
		this.editorType = editorType;
		this.medias=new ArrayList<String>();
	}
	
	/*
	public Blog(int bid, String title, String content, String author,
			Date postDate, List<Integer> type) {
		super();
		this.bid = bid;
		this.title = title;
		this.content = content;
		this.author = author;
		this.postDate = postDate;
		this.medias=new ArrayList<String>();
	}*/
	
	@Override
	public Blog clone()  {
		Blog blog = null;
		try {
			blog = (Blog)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return blog;
	}
	
	@Override
	public String toString() {
		return "Blog [bid=" + bid + ", title=" + title + ", editorType=" + editorType + "]";
	}
	
	
}
