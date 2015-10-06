package com.fatiny.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fatiny.vo.UserEnum;
 
/**
 * 用户隐私信息类
 * @author Janlr028
 */
@Entity
@Table(name="user")
public class User implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;		 //用户唯一id
	private String userName; //登录名
	private String password; //登陆密码
	private UserEnum level;	 //权限等级
	private String nickName; //显示昵称
	private	String icon;	//头像
	private long registTime; //注册时间
	private long lastloginTime; //最后登录时间
	

	@Id
	@GeneratedValue
	@Column(name="userid")
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@Column(name="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="level")
	public UserEnum getLevel() {
		return level;
	}
	public void setLevel(UserEnum level) {
		this.level = level;
	}
	@Column(name="registTime")
	public long getRegistTime() {
		return registTime;
	}
	public void setRegistTime(long registTime) {
		this.registTime = registTime;
	}
	
	@Column(name="lastloginTime")
	public long getLastloginTime() {
		return lastloginTime;
	}
	public void setLastloginTime(long lastloginTime) {
		this.lastloginTime = lastloginTime;
	}
	
	@Column(name="icon")
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Column(name="nickName")
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	/**
	 * 数据库获取使用
	 * @param userId
	 * @param userName
	 * @param passWord
	 * @param level
	 */
	public User(int userId, String userName, String password, UserEnum level) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.level = level;
	}
	
	/**
	 * 摆设看缘分,什么时候用
	 */
	public User() {	}
	
//	/**
//	 * 注册使用
//	 * @param userName
//	 * @param passWord
//	 */
//	public User(String userName, String password) {
//		super();
//		this.userName = userName;
//		this.password = password; 
//	}
	

	public User(String nickName, String icon) {
		super();
		this.nickName = nickName;
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", level=" + level + ", nickName="
				+ nickName + ", icon=" + icon + ", registTime=" + registTime
				+ ", lastloginTime=" + lastloginTime + "]";
	}
	
}
