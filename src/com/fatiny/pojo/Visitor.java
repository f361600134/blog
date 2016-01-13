package com.fatiny.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用于记录玩家来访用户,IP地址信息.访问页面次数
 * @author Jeremy
 * @version 1.0
 * @create 2015.11.21
 */

@Entity
@Table(name = "visitor")
public class Visitor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;				//唯一id
	private String ip;			//用户的IP地址
	private String address;		//GEO解析IP地址获取的地理为位置
	private String dizhi;		//淘宝解析的地址
	private Date date;			//最新的访问时间
	private String device;		//系统机型
	private String browser;		//浏览器
	private int times;			//访问次数
	
	public Visitor() {
		super();
	}
	
	/**
	 * @Description
	 * @author Jeremy
	 * @date 2015年12月25日 下午4:19:14 
	 * @version V1.0
	 * @param ip 实际ip地址
	 * @param address Geo解析的地址
	 * @param device 使用设备
	 * @param browser 使用的浏览器
	 */
	public Visitor(String ip, String address, String device, String browser) {
		super();
		this.ip = ip;
		this.address = address;
		this.date = new Date();
		this.device = device;
		this.browser = browser;
		this.times = 1;
	}
	
	/**
	 * @Description
	 * @author Jeremy
	 * @date 2015年12月25日 下午4:19:14 
	 * @version V1.0
	 * @param ip 实际ip地址
	 * @param address Geo解析的地址
	 * @param dizhi Ali解析的地址
	 * @param device 使用设备
	 * @param browser 使用的浏览器
	 */
	public Visitor(String ip, String address, String dizhi, String device, String browser) {
		super();
		this.ip = ip;
		this.address = address;
		this.dizhi = dizhi;
		this.date = new Date();
		this.device = device;
		this.browser = browser;
		this.times = 1;
	}

	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name="ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="times")
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	@Column(name="device")
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

	@Column(name="date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="browser")
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	@Column(name="dizhi")
	public String getDizhi() {
		return dizhi;
	}
	public void setDizhi(String dizhi) {
		this.dizhi = dizhi;
	}
	
	/**
	 * @Description 刷新当前访问者的最新信息
	 * @author Jeremy
	 * @date 2015年12月23日 下午3:51:41 
	 * @version V1.0
	 */
	public void refresh(){
		this.date = new Date();
		this.times +=1;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", ip=" + ip + ", address=" + address + ", date=" + date + ", device=" + device
				+ ", browser=" + browser + ", times=" + times + "]";
	}
	
}
