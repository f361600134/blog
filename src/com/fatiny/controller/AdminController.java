package com.fatiny.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fatiny.pojo.User;
import com.fatiny.service.BlogService;
import com.fatiny.service.UserService;
import com.fatiny.util.Content;
import com.fatiny.util.LogContext;
import com.fatiny.util.StringUtil;
import com.fatiny.util.db.SimpleQueryUtil;

@Controller
@RequestMapping("/admin")
@SessionAttributes(value = "loginUser")
public class AdminController {
	private static Logger log = LogContext.LOG_MODULE_USER;
	
	/**
	 * 跳转到项目有管理
	 * @return
	 */
	@RequestMapping(value = "/project.htm",method = RequestMethod.GET)
	public String project(){
		//log.info("进入系统....");
		return "/admin/project";
	}
	
	/**
	 * 跳转到登陆
	 * @return
	 */
	@RequestMapping(value = "/login.htm",method = RequestMethod.GET)
	public String login(){
		//log.info("进入系统....");
		return "/admin/login";
	}
	
	/**
	 * 跳转到编写文章页面
	 * @return
	 */
	@RequestMapping(value = "/edit.htm",method = RequestMethod.GET)
	public String edit(){
		log.info("进入写文....");
		return "/admin/edit";
	}
	
	/**
	 * 跳转到文章管理页面
	 * @return
	 */
	@RequestMapping(value = "/log.htm",method = RequestMethod.GET)
	public String log(Model model){
		log.info("文章管理...");
//		List<Blog> blogs= this.bservice.getAll();
//		model.addAttribute(Content.ALL_BLOGS,blogs);
		return "/admin/log";
	}
	
	/**
	 * 跳转类别管理
	 * @return
	 */
	@RequestMapping(value = "/category.htm",method = RequestMethod.GET)
	public String category(Model model){
		return "/admin/category";
	}
	
	/**
	 * 跳转类别管理
	 * @return
	 */
	@RequestMapping(value = "/tag.htm",method = RequestMethod.GET)
	public String tag(Model model){
		return "/admin/tag";
	}
	
	/**
	 * 连接管理
	 * @return
	 */
	@RequestMapping(value = "/link.htm",method = RequestMethod.GET)
	public String link(Model model){
		return "/admin/link";
	}
	
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping(value = "/contact.htm",method = RequestMethod.GET)
	public String contact(Model model){
		return "/admin/contact";
	}
	
	
	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMapping(value = "/index.htm",method = RequestMethod.GET)
	public String index(Model model){
		model.addAttribute("system",System.getProperties()); //操作系统环境
		return "/admin/index";
	}
	
	
	
	@RequestMapping(value="/login.htm",method = RequestMethod.POST)
	public String login(HttpServletRequest request,String userName,String password,Model model){
		User user = null;
		//客户端首先对字符串进行验证,服务器要二次验证.
				log.info("服务器首先对字符串进行验证,userName="+userName+",password="+password);
		if (!StringUtil.isBlank(userName) && !StringUtil.isBlank(password)) {
			if(StringUtil.regexWord(userName) && StringUtil.regexWord(password)) {
				String condition1 =  " username = '"+userName+"'";
				//对密码进行暗文显示
				//String condition2 =  " password = password('"+password+"')";
				
				String condition2 =  " password = '"+password+"'";
				String hql = SimpleQueryUtil.getSelectHql(User.class, condition1,condition2);
				log.info("登陆系统.... hql:"+hql);
				
				user = uservice.login(hql);
				//log.info("登陆系统.... 验证结果="+ user);
			}
		}
		if (user != null) {
			model.addAttribute("user", user);			 			//玩家session	
			model.addAttribute("system",System.getProperties()); 	//操作系统环境
			request.getSession().setAttribute("loginUser", "user"); //后台登陆
			return "/admin/index";
		}
		model.addAttribute(Content.ERRORMSG, Content.WRONG_LOGIN_MESSAGE);
		return "/admin/login";
	} 
	
	
	private BlogService bservice;
	private UserService uservice;
	public BlogService getBservice() {return bservice;}
	public UserService getUservice() {return uservice;}
	@Resource
	public void setBservice(BlogService bservice) {this.bservice = bservice;}
	@Resource
	public void setUservice(UserService uservice) {this.uservice = uservice;}
}
