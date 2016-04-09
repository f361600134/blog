package com.fatiny.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fatiny.util.SpringUtil;
import com.fatiny.util.SystemConfig;
import com.fatiny.vo.AppData;

public class InitContextListener implements ServletContextListener{

	/**
	 * 监听器,销毁监听
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//TODO
	}

	/**
	 * 监听器,启动监听
	 * 暂时只能从servlet中获取到根路径了.传给config加载函数.统一加载
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg) {
		
		//系统路径
		String rootPath = arg.getServletContext().getRealPath("/");
		String context = arg.getServletContext().getContextPath();
		SystemConfig.initPath(rootPath, context);
		
		//Spring配置
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(arg.getServletContext());
		SpringUtil.setApplicationContext(wac);
		
		//app属性配置
		AppData.initAppData();
		
		
		//
		//SystemData data = (SystemData)wac.getBean("systemData");
		//System.out.println("=============>contextInitialized.data:"+data.getGeoPath());
		
	}

}
