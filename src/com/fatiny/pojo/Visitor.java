package com.fatiny.pojo;

import java.io.Serializable;

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
	private String address;		//解析IP地址获取的地理
	
	private int port;			//端口号
	private int times;			//访问次数
	private String device;		//机型,待定使用
	
	public Visitor() {
		super();
	}

	public Visitor(String ip, String address) {
		super();
		this.ip = ip;
		this.address = address;
	}
	
	public Visitor(String ip, String address, int port, int times, String device) {
		super();
		this.ip = ip;
		this.address = address;
		this.port = port;
		this.times = times;
		this.device = device;
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
	
	//@Column(name="port")
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	//@Column(name="times")
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	//@Column(name="device")
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", ip=" + ip + ", address=" + address + ", port=" + port + ", times=" + times
				+ ", device=" + device + "]";
	}
	
}
