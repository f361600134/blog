package com.fatiny.vo;

import java.io.File;
import java.util.HashMap;
import com.fatiny.pojo.Visitor;
import com.fatiny.util.SpringUtil;

/**
 * @Description 
 * @author Jeremy
 * @date 2015年11月24日 上午12:21:37 
 * @version V1.0
 */
public class AppData {
	
	//只记录1小时内玩家IP地址信息
	public static HashMap<String, Visitor> visitorMap;
	public static File geoFile;
	
	//初始化app系统配置属性
	public static void initAppData(){
		
		visitorMap = new HashMap<String, Visitor>(); 
		
		SystemData systemData = SpringUtil.getBean(SystemData.class);
		geoFile = new File(systemData.getGeoPath());
		
	}
	
	
	
	
}
