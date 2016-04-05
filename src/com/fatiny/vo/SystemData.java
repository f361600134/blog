package com.fatiny.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SystemData {
	//地址资源文件路径
	
	public static String geoPathData;
	
	@Value("${geo.geoPath}")
	public void setPrivateName(String geoPath) {
		SystemData.geoPathData = geoPath;
		System.out.println("=============>"+SystemData.geoPathData);
	}  
}




