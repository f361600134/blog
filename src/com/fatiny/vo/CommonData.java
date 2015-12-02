package com.fatiny.vo;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description 
 * @author Jeremy
 * @date 2015年11月24日 上午12:21:37 
 * @version V1.0
 */
public class CommonData {
	
	//只记录1小时内玩家IP地址信息
	public static Set<String> ipSet = new HashSet<String>();
	static{
		ipSet.add("219.136.134.157");
		ipSet.add("125.70.11.136");
		ipSet.add("119.81.157.66");
		ipSet.add("112.74.85.106");
		ipSet.add("103.9.116.204");
	}
	
	
}
