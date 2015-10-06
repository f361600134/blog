package com.fatiny.util;

public class Config {
	/**博客每页显示数量*/
	public static int PAGESIZE_BLOG;
	/**留言板每页显示数量*/
	public static int PAGESIZE_CONTANT;
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
