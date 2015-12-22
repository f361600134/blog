package com.fatiny.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fatiny.pojo.User;
import com.fatiny.service.UserService;
import com.fatiny.util.Content;
import com.fatiny.util.LogContext;
import com.fatiny.util.StringUtil;
import com.fatiny.util.db.SimpleQueryUtil;


@Controller
@RequestMapping("/user")
@SessionAttributes(value = "loginUser")
public class UserController {

	private static Logger log = LogContext.LOG_MODULE_USER;
	
	public void delete(){}

	/**
	 * 详细信息
	 */
	@RequestMapping(value = "/detail/{userId}.htm" ,method = RequestMethod.GET)
	public String getById(@PathVariable int userId,Model model){
		User user = this.uservice.getById(userId);
		model.addAttribute("user",user);
		return "user/detail";
	}
	
	/**
	 * 直接跳转到添加页面
	 */
	@RequestMapping(value = "/add.htm" ,method = RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new User());
		return "user/updateOrAdd";
	}
	
	/**
	 * 直接跳转到添加页面
	 */
	@RequestMapping(value = "/add.htm" ,method = RequestMethod.POST)
	public String add(User user,BindingResult binding,Model model){
		if(binding.hasErrors()){
			log.info("添加失败~");
			return "user/updateOrAdd";
		}
		this.uservice.saveOrUpdate(user);
		log.info("添加成功~");
		return "redirect:/user/show.htm";
	}
	 
	/**
	 * 通过id删除
	 */
	@RequestMapping(value = "/delete/{userId}.htm",method = RequestMethod.GET)
	public String deleteById(@PathVariable int userId){
		this.uservice.delete(userId);
		return "redirect:/user/show.htm";
	}
	
	/**
	 * 进入修改页面,要通过id获取到对象经具体信息
	 * @param userId
	 * @param model
	 */
	@RequestMapping(value = "/update/{userId}.htm",method = RequestMethod.GET)
	public String toUpdate(@PathVariable int userId,Model model){
		log.info("去更新页面,userId="+userId);
		User user = this.uservice.getById(userId);
		model.addAttribute("user", user);
		return "user/updateOrAdd";
	}
	
	@RequestMapping(value = "/update/{userId}.htm",method = RequestMethod.POST)
	public String update(@PathVariable int userId,User user,BindingResult br){
		user.setUserId(userId);
		log.info("更新,userId="+userId+",user="+user);
		if(br.hasErrors()){
			log.info("更新出错了");
			return "/user/adduser";
		}
		this.uservice.update(user);
		return "redirect:/user/show.htm";
	}

	@RequestMapping(value = "/show.htm" ,method = RequestMethod.GET)
	public String show(Model model){
//		String hql = SimpleQueryUtil.getSelectHql(User.class.getName());
//		log.info("显示列表.... hql:"+hql);
//		
//		List<User> userlist = uservice.getAll(hql);
//		log.info("显示列表.... 查到的结果集:"+userlist);
//		model.addAttribute("userlist",userlist);
		init(model);
		return "/user/show";
	}
	
	@RequestMapping(value = "/angel_000.htm" ,method = RequestMethod.GET)
	public String angel_000(Model model){
		System.out.println("=======");
		return "/user/vindicate";
	}
	
	@Deprecated
	@RequestMapping(value="/login.htm",method = RequestMethod.POST)
	public String login(String userName,String password,Model model){
		User user = null;
		//客户端首先对字符串进行验证,服务器要二次验证.
		log.info("服务器首先对字符串进行验证,userName="+userName+",password="+password);
		if (StringUtil.regexWord(userName) && StringUtil.regexWord(password)) {
			String condition1 =  " username = '"+userName+"'";
			//对密码进行暗文显示
			//String condition2 =  " password = password('"+password+"')";
			
			String condition2 =  " password = '"+password+"'";
			String hql = SimpleQueryUtil.getSelectHql(User.class, condition1,condition2);
			log.info("登陆系统.... hql:"+hql);
			
			user = uservice.login(hql);
			log.info("登陆系统.... 验证结果="+user);
		}
		if (user != null) {
			model.addAttribute("loginUser", userName);
			return "redirect:/user/show.htm";
		}
		return "/user/error";
	}
	
	@RequestMapping(value="/Sinalogin.htm",method = RequestMethod.POST)
	public void Sinalogin(HttpServletRequest request,Model model){
		User user = null;
		String sinaName = request.getParameter("sinaName");
		String url = request.getParameter("url");
		log.info("使用新浪登陆...sinaName:"+sinaName+",url:"+url);
		user = new User(sinaName,url);
		request.getSession().setAttribute(Content.SINGLE_USER, user);
	}

	@RequestMapping(value="/QQlogin.htm",method = RequestMethod.GET)
	public void QQlogin(HttpServletRequest request,Model model){
		User user = null;
		log.info("使用QQ登录...");
		String QQinfo = request.getParameter("QQinfo");
		org.json.JSONObject json = new org.json.JSONObject(QQinfo);
		String qqname = (String) json.get("nickname");
		String figureurl = (String) json.get("figureurl");
		log.info("使用QQ登录...qqname:"+qqname+",figureurl:"+figureurl);
		user = new User(qqname,figureurl);
		request.getSession().setAttribute(Content.SINGLE_USER, user);
	}

	@RequestMapping(value = "/login.htm",method = RequestMethod.GET)
	public String login(){
		log.info("进入系统....");
		return "/user/login";
	}

	/**
	 * 初始化信息
	 */
	private void init(Model model){
		String hql = SimpleQueryUtil.getSelectHql(User.class.getName());
		log.info("显示列表.... hql:"+hql);
		
		List<User> userlist = uservice.getAll(hql);
		log.info("显示列表.... 查到的结果集:"+userlist);
		model.addAttribute("userlist",userlist);
	}
	 
	
	private UserService uservice; 
	public UserService getUservice() {
		return uservice;
	}
	@Resource
	public void setUservice(UserService uservice) {
		this.uservice = uservice;
	}
}
