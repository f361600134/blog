package com.fatiny.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fatiny.pojo.Visitor;
import com.fatiny.util.AddressUtils;
import com.fatiny.util.LogContext;
import com.fatiny.vo.CommonData;
import com.useragentutils.tools.Browser;
import com.useragentutils.tools.OperatingSystem;
import com.useragentutils.tools.UserAgent;

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
//		log.info("看看preHandle会打印多少次!");
		String url = request.getServletPath();
		String ctnPath = request.getContextPath();
		
        if (url.equals("/admin/login.htm") || !ctnPath.equals("/admin"))	
        	return true;
        
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
	* 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
	* 该方法将在整个请求完成之后, 也就是DispatcherServlet渲染了视图执行， 
	* 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
	* @change: 不知道在这里使用远程连接解析地域信息合不合理, 先这样处理.看看会不会宕机
	*/ 
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long beginTime = System.currentTimeMillis();
	
		/*通过IP解析地理位置*/
		String ip = AddressUtils.getIp(request);
		Visitor visitor = CommonData.visitorMap.get(ip);
		if (visitor == null) {
			//如果缓存没有当前访问者,则生成一个新的visitor
			String address = AddressUtils.getGeoAddress(ip);
			String dizhi = AddressUtils.getIpInfo(ip);
			
			/*获取用户的浏览器信息*/
			String agent = request.getHeader("user-agent");
			UserAgent uAgent = new UserAgent(agent);
			OperatingSystem os = uAgent.getOperatingSystem();
			Browser browser = uAgent.getBrowser();
			String osName = os.getName();
			String browserName =browser.getName();
			visitor = new Visitor(ip, address, dizhi, osName, browserName);
		}else{
			//如果缓存存在当前访问者,则改变最新/后访问时间.
			visitor.refresh();
		}
		//put替代新的visitor
		CommonData.visitorMap.put(ip, visitor);
		log.info("处理记录用户信息用时:"+(System.currentTimeMillis()-beginTime));
	}
	
	
//	/** 
//    * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。
//    * 该方法将在整个请求完成之后, 也就是DispatcherServlet渲染了视图执行， 
//     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。 
//     */ 
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		//对response进行处理,下发绝对路径
//		long beginTime = System.currentTimeMillis();
//		getRequestURL方法返回客户端发出请求时的完整URL。
//	　　	getRequestURI方法返回请求行中的资源名部分。
//	　　	getQueryString 方法返回请求行中的参数部分。
//	　　	getPathInfo方法返回请求URL中的额外路径信息。额外路径信息是请求URL中的位于Servlet的路径之后和查询参数之前的内容，它以“/”开头。
//	　　	getRemoteAddr方法返回发出请求的客户机的IP地址。
//	　　	getRemoteHost方法返回发出请求的客户机的完整主机名。
//	　　	getRemotePort方法返回客户机所使用的网络端口号。
//	　　	getLocalAddr方法返回WEB服务器的IP地址。
//	　　	getLocalName方法返回WEB服务器的主机名。
//		//get ip
//		String host = request.getRemoteHost();
//		int post = request.getRemotePort();
//		String addr = request.getRemoteAddr();
//		String user = request.getRemoteUser();
//		System.out.printf("host=%s,post=%d,addr=%s,user=%s", host, post, addr, user);
//		System.out.println();
//		
//		String uri = request.getRequestURI();
//		String localAddr = request.getLocalAddr();
//		String localName = request.getLocalName();
//		int localPort = request.getLocalPort();
//		String method = request.getMethod();
//		System.out.printf("uri=%s,localAddr=%s,localName=%s,localPort=%d, method=%s", uri, localAddr, localName, localPort, method);
//		System.out.println();
//		
//		int ctn = request.getContentLength();
//		String ctnPath = request.getContextPath();
//		String ctnType = request.getContentType();
//		System.out.printf("ctn:%d,ctnPath:%s,ctnType:%s", ctn, ctnPath, ctnType);
//		System.out.println();
//		request.getCookies();
//		request.getDispatcherType();
//		request.getInputStream();
//		request.getUserPrincipal();
//		request.getHeaderNames();
//		request.getSession();
//		request.getServletContext();
//		request.getAsyncContext();
//		request.getAttributeNames();
//		
//		String servletPath = request.getServletPath();
//		int serverPort = request.getServerPort();
//		String serverName = request.getServerName();
//		String requestUri = request.getRequestURI();
//		String scheme = request.getScheme();
//		System.out.printf("servletPath:%s, serverPort:%d, serverName:%s,serverUri:%s, scheme:%s", servletPath,serverPort,serverName,requestUri,scheme );
//		
//		/*获取用户的浏览器信息*/
//		String agent = request.getHeader("user-agent");
//		System.out.println("agent: "+agent);
//		
//		String host = request.getHeader("host");
//		System.out.println("host: "+host);
//		String connection = request.getHeader("connection");
//		System.out.println("connection: "+connection);
//		String accept = request.getHeader("accept");
//		System.out.println("accept: "+accept);
//		String referer = request.getHeader("referer");
//		System.out.println("referer: "+referer);
//		String accept_encoding = request.getHeader("accept-encoding");
//		System.out.println("accept-encoding: "+accept_encoding);
//		String accept_language = request.getHeader("accept-language");
//		System.out.println("accept-language: "+accept_language);
//		String cookie = request.getHeader("cookie");
//		System.out.println("cookie: "+cookie);
//		
//		UserAgent uAgent = new UserAgent(agent);
//		OperatingSystem os = uAgent.getOperatingSystem();
//		
//		System.out.println("==========OperatingSystem==========="+(System.currentTimeMillis()-beginTime));
//		System.out.println("ID: "+os.getId());
//		System.out.println("Name: "+os.getName());
//		System.out.println("DeviceType: "+os.getDeviceType());
//		System.out.println("Group: "+os.getGroup());
//		System.out.println("Manufacturer: "+os.getManufacturer());
//		
//		System.out.println("==========Browser===========");
//		Browser browser = uAgent.getBrowser();
//		System.out.println("ID: "+browser.getId());
//		System.out.println("Name: "+browser.getName());
//		System.out.println("Group: "+browser.getGroup());
//		System.out.println("Manufacturer: "+browser.getManufacturer());
//		
//		System.out.println("==========End==========="+(System.currentTimeMillis()-beginTime));
//		System.out.println();
//		//end
//		String url = request.getServletPath();
//		System.out.println(url);
//		log.info("url==="+url);
//        //先对url进行判断,是登录路径直接使其通过
//		if (url.equals("/admin/login.htm") || url.equals("/editor/ckeditor.htm")|| url.equals("/editor/ueeditor.htm")
//			|| url.equals("/index/index.htm")) return true;
//        
//		CommonData.ipSet.add(AddressUtils.getIp(request));
//		
//	}
	

}
