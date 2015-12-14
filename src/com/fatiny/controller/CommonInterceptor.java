package com.fatiny.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fatiny.util.LogContext;
import com.fatiny.vo.CommonData;

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
//		getRequestURL方法返回客户端发出请求时的完整URL。
//	　　	getRequestURI方法返回请求行中的资源名部分。
//	　　	getQueryString 方法返回请求行中的参数部分。
//	　　	getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
//	　　	getRemoteAddr方法返回发出请求的客户机的IP地址。
//	　　	getRemoteHost方法返回发出请求的客户机的完整主机名。
//	　　	getRemotePort方法返回客户机所使用的网络端口号。
//	　　	getLocalAddr方法返回WEB服务器的IP地址。
//	　　	getLocalName方法返回WEB服务器的主机名。
		//get ip
		String host = request.getRemoteHost();
		int post = request.getRemotePort();
		String addr = request.getRemoteAddr();
		String user = request.getRemoteUser();
		System.out.printf("host=%s,post=%d,addr=%s,user=%s", host, post, addr, user);
		System.out.println();
		
		String uri = request.getRequestURI();
		String localAddr = request.getLocalAddr();
		String localName = request.getLocalName();
		int localPort = request.getLocalPort();
		String method = request.getMethod();
		System.out.printf("uri=%s,localAddr=%s,localName=%s,localPort=%d, method=%s", uri, localAddr, localName, localPort, method);
		System.out.println();
		
		int ctn = request.getContentLength();
		String ctnPath = request.getContextPath();
		String ctnType = request.getContentType();
		System.out.printf("ctn:%d,ctnPath:%s,ctnType:%s", ctn, ctnPath, ctnType);
		System.out.println();
//		request.getCookies();
//		request.getDispatcherType();
//		request.getInputStream();
//		request.getUserPrincipal();
//		request.getHeaderNames();
//		request.getSession();
//		request.getServletContext();
//		request.getAsyncContext();
//		request.getAttributeNames();
		
		String servletPath = request.getServletPath();
		int serverPort = request.getServerPort();
		String serverName = request.getServerName();
		String requestUri = request.getRequestURI();
		String scheme = request.getScheme();
		System.out.printf("servletPath:%s, serverPort:%d, serverName:%s,serverUri:%s, scheme:%s", servletPath,serverPort,serverName,requestUri,scheme );
		System.out.println();
		//end
		String url = request.getServletPath();
//		log.info("url==="+url);
        //先对url进行判断,是登录路径直接使其通过
//		if (url.equals("/admin/login.htm") || url.equals("/editor/ckeditor.htm")|| url.equals("/editor/ueeditor.htm")
//			|| url.equals("/index/index.htm")) return true;
        
		CommonData.ipSet.add(addr);
		
        if (url.equals("/admin/login.htm") || !ctnPath.equals("/admin"))	return true;
		String str = (String) request.getSession().getAttribute("loginUser");
        if(str==null){
        	//绝对路径
        	response.sendRedirect(request.getContextPath()+"/admin/login.htm");
			return false;
        }
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
