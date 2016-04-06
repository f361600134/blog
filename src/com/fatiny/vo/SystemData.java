package com.fatiny.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class SystemData {
	//地址资源文件路径
	private String geoPath;
	
	@Value("${geo.geoPath}")
	public void setGeoPath(String geoPath) {this.geoPath = geoPath;}
	public String getGeoPath() {return geoPath;}
	
	
}




