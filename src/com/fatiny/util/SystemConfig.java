package com.fatiny.util;

public class SystemConfig {
	/**项目根目录**/
	public static String ROOT_PATH;
	/**项目所在目录**/
	public static String REAL_PATH;
	
	public static void init(){
	}
	
	public static void initPath(String rootPath,String context){
		ROOT_PATH = rootPath;
		REAL_PATH = rootPath.replaceFirst(context, "");
	}
}
