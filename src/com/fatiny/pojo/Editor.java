package com.fatiny.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Editor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String mdContent;
	private String title;
	private String content;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Editor() {}
	
	public String getMdContent() {
		return mdContent;
	}
	public void setMdContent(String mdContent) {
		this.mdContent = mdContent;
	}
	public Editor(int id, String title, String content,String mdContent) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.mdContent = mdContent;
	}
//	@Override
//	public String toString() {
//		return "Editor [id=" + id + ", category=" + category + ", title="
//				+ title + ", content=" + content + "]";
//	}
	
	@Override
	public String toString() {
		return content;
	}
	
	
}
