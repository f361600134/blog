package com.fatiny.vo;

import java.util.HashMap;
import com.fatiny.pojo.Visitor;

/**
 * @Description 
 * @author Jeremy
 * @date 2015年11月24日 上午12:21:37 
 * @version V1.0
 */
public class AppData {
	
	//只记录1小时内玩家IP地址信息
	public static HashMap<String, Visitor> visitorMap = new HashMap<String, Visitor>();
	
	
}
