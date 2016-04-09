package com.fatiny.util;

public class SystemConfig {
	/**项目根目录**/
	public static String ROOT_PATH;
	/**项目所在目录**/
	public static String REAL_PATH;
	/**项目名称**/
	public static String PROJECT_NAME;
	/***/
	
	public static void init(){}
	
	public static void initPath(String rootPath,String context){
		ROOT_PATH = rootPath;
		//if (context.contains("/")) {
		//	context = context.replace("/", "\\");
		//}
		context = context.contains("/") ? context.replace("/", "\\") : context;
		PROJECT_NAME = context;
		REAL_PATH = rootPath.replace(context, "");
	}
}
