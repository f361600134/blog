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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fatiny.util.DateUtil;

/**
 * 发表评论,留言类
 * 
 * @author Jemery
 *
 */
@Entity
@Table(name="contact")
public class Contact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int id;			//唯一id,自增id
	protected String name;		//姓名
	protected String email;		//邮箱
	protected String website;	//站点
	protected String message;	//留言内容
	protected Date postDate;	//发表时间
	
	protected int bid;			//外键文章引用
	protected int cid;			//外键回复引用
	
	protected String showDate;	//显示时间处理之后不必每次调用都转换postDate;

	
	//一对多,多对一
	private Contact cont;
	private List<Contact> conts;
	
	@ManyToOne(cascade={CascadeType.ALL})
	public Contact getCont() {return cont;}
	public void setCont(Contact cont) {
		this.cont = cont;
	}
	
	public void setConts(List<Contact> conts) {this.conts = conts;}
	@OneToMany(mappedBy = "cont", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Contact> getConts() {
		return conts;
	}
		
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	@Column(name="name")
	public String getName() {return name;}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="email")
	public String getEmail() {return email;}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="website")
	public String getWebsite() {return website;}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	@Column(name="message")
	public String getMessage() {return message;}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name="postDate")
	public Date getPostDate() {return postDate;}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
		setShowDate(postDate);
	}
	
	@Column(name="bid")
	public int getBid() {return bid;}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	@Column(name="cid")
	public int getCid() {return cid;}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
//	public Contact() {
//		super();
//		cont = new Contact();
//		conts = new ArrayList<Contact>();
//	}
	
	public Contact() {
		super();
		conts = new ArrayList<Contact>();
	}
	public Contact(String name, String email, String website, String message) {
		this.name = name; 
		this.email = email; 
		this.website = website; 
		this.message = message; 
		conts = new ArrayList<Contact>();
	}
	/*
	 * 不做缓存处理
	 */
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}
	public void setShowDate(Date postDate) {
		this.showDate = DateUtil.getSpecificDate(postDate);
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email
				+ ", website=" + website + ", message=" + message
				+ ", postDate=" + postDate + ", bid=" + bid + ", cid=" + cid
				+ ", showDate=" + showDate + ", cont=" + cont + ", conts="
				+ conts + "]";
	}
	
}
