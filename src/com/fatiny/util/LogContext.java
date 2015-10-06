package com.fatiny.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录管理器
 * 记录整个生命周期
 * @author Janlr028
 *
 */
public class LogContext {
	
	/** 模块 - UTIL */
	private static final String MODULE_UTILS = "MODULE_UTILS";
	/** 模块 - USER */
	private static final String MODULE_USER = "MODULE_USER";
	/** 模块 - 拦截器 */
	private static final String MODULE_INTERCEPTER = "INTERCEPTER";
	/** 模块 - EDITOR */
	private static final String MODULE_EDITOR = "MODULE_EDITOR";
	/** 模块 - HOME */
	private static final String MODULE_HOME = "MODULE_HOME";
	/** 模块 - BLOG */
	private static final String MODULE_BLOG = "MODULE_BLOG";
	/** 模块 - CONTACT */
	private static final String MODULE_CONTACT = "MODULE_CONTACT";
	/** 模块- CATEGORY */
	private static final String MODULE_CATEGORY = "MODULE_CATEGORY";
	/** 模块- TAG */
	private static final String MODULE_TAG	 = "MODULE_TAG";
	/** 模块- LINK */
	private static final String MODULE_LINK	 = "MODULE_LINK";
	
	/** 日志- UTILS */
	public static final Logger LOG_MODULE_UTILS = LoggerFactory.getLogger(MODULE_UTILS);	
	/** 日志 - USER */
	public static final Logger LOG_MODULE_USER = LoggerFactory.getLogger(MODULE_USER);	
	/** 日志 - 拦截器 */
	public static final Logger LOG_MODULE_INTERCEPTER = LoggerFactory.getLogger(MODULE_INTERCEPTER);	
	/** 日志- EDITOR */
	public static final Logger LOG_MODULE_EDITOR = LoggerFactory.getLogger(MODULE_EDITOR);	
	/** 日志- HOME */
	public static final Logger LOG_MODULE_HOME = LoggerFactory.getLogger(MODULE_HOME);
	/** 日志- BLOG */
	public static final Logger LOG_MODULE_BLOG = LoggerFactory.getLogger(MODULE_BLOG);
	/** 日志- BLOG */
	public static final Logger LOG_MODULE_CONTACT = LoggerFactory.getLogger(MODULE_CONTACT);
	/** 日志- CATEGORY */
	public static final Logger LOG_MODULE_CATEGORY = LoggerFactory.getLogger(MODULE_CATEGORY);
	/** 日志- TAG */
	public static final Logger LOG_MODULE_TAG = LoggerFactory.getLogger(MODULE_TAG);
	/** 日志- MODULE_LINK */
	public static final Logger LOG_MODULE_LINK = LoggerFactory.getLogger(MODULE_LINK);
	
	
}
