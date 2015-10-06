package com.fatiny.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 博客标题,标题简语
 * @author Jemery
 *
 */
@Entity
@Table(name="logo")
public class Logo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;		//标题
	private String slogan;		//标语简语
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name="slogan")
	public String getSlogan() {
		return slogan;
	}
	public void setSlogan(String slogan) {
		this.slogan = slogan;
	}
	
	public Logo() {	}
	
	public Logo(int id, String title, String slogan) {
		super();
		this.id = id;
		this.title = title;
		this.slogan = slogan;
	}
	
	@Override
	public String toString() {
		return "Logo [id=" + id + ", title=" + title + ", slogan=" + slogan
				+ "]";
	}
}
