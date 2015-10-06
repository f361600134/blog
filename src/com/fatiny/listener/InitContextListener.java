package com.fatiny.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.fatiny.util.Config;

public class InitContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		//TODO
	}

	/**
	 * 暂时只能从servlet中获取到根路径了.传给config加载函数.统一加载
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg) {
		String rootPath = arg.getServletContext().getRealPath("/");
		String context = arg.getServletContext().getContextPath();
		
		Config.initPath(rootPath, context);
	}

}
