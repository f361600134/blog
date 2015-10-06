package com.fatiny.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fatiny.util.LogContext;

public class CommonInterceptor implements HandlerInterceptor {
	private static Logger log = LogContext.LOG_MODULE_INTERCEPTER;
	
	public CommonInterceptor() {}
	/**
	 * 在业务处理器处理请求之前被调用
	 * 如果返回false
	 *   从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 * 如果返回true
	 *   执行下一个拦截器,直到所有的拦截器都执行完毕
	 *   再执行被拦截的Controller
	 *   然后进入拦截器链,
	 *   从最后一个拦截器往回执行所有的postHandle()
	 *   接着再从最后一个拦截器往回执行所有的afterCompletion()
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//get ip
//		String host = request.getRemoteHost();
//		int post = request.getRemotePort();
//		String addr = request.getRemoteAddr();
//		String user = request.getRemoteUser();
		
//		System.out.println(request.getMethod()); //get post
		
//		System.out.printf("host=%s,post=%d,addr=%s,user=%s", host,post,addr,user);
		//end
		
//        String url = request.getServletPath();
//        log.info("url==="+url);
//        //先对url进行判断,是登录路径直接使其通过
//        if (url.equals("/user/login.htm") || url.equals("/editor/ckeditor.htm")|| url.equals("/editor/ueeditor.htm")
//        		|| url.equals("/index/index.htm")) return true;
//        
//		String str = (String) request.getSession().getAttribute("loginUser");
//        if(str==null ){
//        	//绝对路径
//        	response.sendRedirect(request.getContextPath()+"/user/login.htm");
//			return false;
//        }
		return true;
	}

	//在业务处理器处理请求执行完成后,生成视图之前执行的动作 
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//log.info("==============执行顺序: 2、postHandle================");
	}

	/**
	 * 在DispatcherServlet完全处理完请求后被调用 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//对response进行处理,下发绝对路径
//		log.info("==============执行顺序: 3、afterCompletion================");
	}
	

}
