package com.fatiny.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.pojo.Blog;
import com.fatiny.pojo.Category;
import com.fatiny.pojo.Contact;
import com.fatiny.pojo.Logo;
import com.fatiny.pojo.Page;
import com.fatiny.pojo.Tag;
import com.fatiny.pojo.Visitor;
import com.fatiny.sdk.gee.GeetestConfig;
import com.fatiny.sdk.gee.GeetestLib;
import com.fatiny.service.BlogService;
import com.fatiny.service.CategorieService;
import com.fatiny.service.ContactService;
import com.fatiny.service.LogoService;
import com.fatiny.service.TagService;
import com.fatiny.util.AddressUtils;
import com.fatiny.util.Content;
import com.fatiny.util.DateUtil;
import com.fatiny.util.LogContext;
import com.fatiny.util.StringUtil;
import com.fatiny.util.UtilTools;
import com.fatiny.vo.AppData;

@Controller
@RequestMapping("/front")
public class FrontController {
	
	private static Logger log = LogContext.LOG_MODULE_HOME;

	/**
	@RequestMapping(value = "/index1.htm",method = RequestMethod.GET)
	public String home(Model model){
		//logo
		int id = 1;
		Logo logo = this.loservice.getById(id);
		model.addAttribute(Content.SINGLE_LOGO, logo);
		
		//latest project
		List<Project> pros = this.proService.findByLimitMax();
		model.addAttribute(Content.RECENT_PROJECT, pros);
		
		//contacts
		String  hql = "from Contact";
		List<Contact> cons = this.conService.findByLimitMax(hql,3);
		model.addAttribute(Content.HOME_CONTACT, cons);
		
		//contacts
		hql = "from Blog b order by b.viewTimes desc";
		List<Blog> blogs = this.bservice.findListByLimitFirst(hql, 4);
		model.addAttribute(Content.HOME_BLOG, blogs);
		return "/front/index";
	}
	
	@RequestMapping(value = "/project.htm",method = RequestMethod.GET)
	public String project(Model model,HttpServletRequest request){
		String proId = request.getParameter("proId");
		log.info("进入产品"+proId);
		
		Project pro = null;
		if (proId != null) {
			pro = this.proService.getById(Integer.parseInt(proId));
		}else{
			pro = this.proService.getLatest();
		}
		//latest project
		//Project pro = this.proService.getLatest();
		model.addAttribute(Content.SINGLE_PROJECT, pro);
		//所有游戏列表
		List<Project> pros = this.proService.getAll();
		model.addAttribute(Content.ALL_PROJECT, pros);
		
		//log.info("pro:"+pro);
		return "/front/project";
	}
	
	
	@RequestMapping(value = "/about.htm",method = RequestMethod.GET)
	public String about(Model model){
		log.info("about me");
		return "/front/about";
	}
	*/
	
	/**
	 * 有图模式
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/posts_with_media.htm",method = RequestMethod.GET)
	//@RequestMapping(value = "/index.htm",method = RequestMethod.GET)
	public String postsWithImages(HttpServletRequest request,Model model){
		find(request, model, 0);
		return "/front/posts_with_images";
	}
	
	/**
	 * 主页
	 * @param request
	 * @param model
	 * @return
	 */
	//@RequestMapping(value = "/posts_with_media.htm",method = RequestMethod.GET)
	@RequestMapping(value = "/index.htm",method = RequestMethod.GET)
	public String posts(HttpServletRequest request,Model model){
		return postsWithImages(request, model);
//		find(request, model, 0);
//		return "/front/posts_with_images";
	}
	
	/**
	 * 无图模式
	 * 这段代码虽然功能实现了,但是写的太烂了!fuck
	 * 根据类型查询数量.这如果通过浏览器地址栏修改参数查询不到数据.
	 * 所以考虑浏览器url加密.其次这里的代码要重构.复杂性比较高.
	 * @param tid
	 */
	@RequestMapping(value = "/posts_without_media.htm",method = RequestMethod.GET)
	public String postsWithoutImages(HttpServletRequest request,Model model){
		find(request, model, 1);
		return "/front/posts_without_images";
	}
	
	
	/**
	 * @Description 查找到博客渲染到页面
	 * @author Jeremy
	 * @date 2016年1月21日 下午6:43:26 
	 * @version V1.0
	 * @param request
	 * @param model
	 * @param blogType
	 */
	private void find(HttpServletRequest request, Model model,int blogType) {
		long beginTime = System.currentTimeMillis();
		//接受请求
		String cateid = request.getParameter("cateid");
		String text = request.getParameter("text");
		String date = request.getParameter("date");
		String tagid = request.getParameter("tagid");

		//分页参数
		String spagenum = request.getParameter("pagenum");
		Page<Blog> page = new Page<Blog>(StringUtil.getInt(spagenum));
		page.setTotalNum(this.bservice.getCount());
		//组装sql
		String hql = null;
		if (tagid != null) {
			hql = "from Blog b where b.bid in (select bt.bid from BlogTag bt where bt.tid = "+tagid+") order by b.postDate desc";
		}else {
			//组装hql和参数
			hql = creactQuery(cateid, text,date);
		}
		
		try {
			List<Blog> list = this.bservice.findByLimit(text,hql, page.getPageNum()*page.getPageSize(),page.getPageSize());
			page.setList(list);
			log.info("请求blog"+list);
			//blog主体内容request
			model.addAttribute(Content.PAGE,page);
			//翻页标签request
			model.addAttribute(Content.BLOG_LINK,appedLink(tagid, cateid, text, date, blogType,page));
			
			/*侧边栏使用.这部分可以改成,登陆是就存入session.*/
			//log
			//logo
			if (request.getSession().getAttribute(Content.SINGLE_LOGO) == null) {
				Logo logo = this.loservice.getById(1);
				request.getSession().setAttribute(Content.SINGLE_LOGO, logo);
			}
			//分类管理session,
			//if (request.getSession().getAttribute(Content.ALL_CATEGORIES) == null)
			{
				List<Category> Categories = this.cateService.getAll();
				model.addAttribute(Content.ALL_CATEGORIES,Categories);
			}
			//标签session
			if (request.getSession().getAttribute(Content.ALL_TAG) == null){
				List<Tag> tags = this.tagService.getAll();
				request.getSession().setAttribute(Content.ALL_TAG,tags);
			}
			//最热门文章session
			//if (request.getSession().getAttribute(Content.POPULAR_BLOG) == null)
			{
				hql = "from Blog b order by b.viewTimes desc";
				List<Blog> tags = this.bservice.findListByLimitFirst(hql, 3);
				request.getSession().setAttribute(Content.POPULAR_BLOG,tags);
			}
			//最新的project
//			if (request.getSession().getAttribute(Content.RECENT_PROJECT) == null){
//				List<Project> pros = this.proService.findByLimitMax();
//				model.addAttribute(Content.RECENT_PROJECT, pros);
//			}
			log.info("The total time"+(System.currentTimeMillis()-beginTime));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * @param blogType=0:with;else without;
	 */
	private String appedLink(String tagid,String cateid, String text,String date,int blogType,Page<Blog> page){
		
		StringBuilder olderLink = null;
		StringBuilder newerLink = null;
		
		if (blogType == 0) {
			olderLink = new StringBuilder(Content.withhref);
			newerLink = new StringBuilder(Content.withhref);
		}else{
			olderLink = new StringBuilder(Content.withouthref);
			newerLink = new StringBuilder(Content.withouthref);
		}
		
		olderLink.append(page.getProPage());
		newerLink.append(page.getNextPage());
		
		if (tagid != null) {
			olderLink.append("&tagid=").append(tagid);
			newerLink.append("&tagid=").append(tagid);
		}else {
			if (cateid != null) {
				olderLink.append("&cateid=").append(cateid);
				newerLink.append("&cateid=").append(cateid);
			}
			if (text != null) {
				olderLink.append("&text=").append(text);
				newerLink.append("&text=").append(text);
			}
			if (date != null) {
				olderLink.append("&date=").append(date);
				newerLink.append("&date=").append(date);
			}
		}
		String older = UtilTools.format(Content.olink, olderLink.toString());
		String newer = UtilTools.format(Content.nlink, newerLink.toString());
		log.info(older+"\n"+newer);
		return (older+newer);
	}
	
	/**
	 * @Description 组装所需要的查询语句
	 * @author Jeremy
	 * @date 2016年1月21日 上午11:22:58 
	 * @version V1.0
	 * @param cateid
	 * @param text
	 * @param date
	 * @return
	 */
	private String creactQuery(String cateid, String text,String date) {
		//组装语句
		StringBuilder hql = new StringBuilder("from Blog b ");
		ArrayList<String> list = new ArrayList<String>();
		
		if (cateid != null) {
			list.add(" b.cateId ="+cateid);
		}
		if (text != null) {
			StringBuilder sb = new StringBuilder(" b.title like '%"+text+"%'");
			sb.append(" or b.mdContent like '%"+text+"%'");
			
			if (UtilTools.isDate(text)) {
				sb.append(" or b.postDate like '%"+text+"%'");
			}
			if (DateUtil.isDate(text)) {
				sb.append(" or b.showDate like '%"+text+"%'");
			}
			list.add(sb.toString());
		}
		if (date != null) {
			list.add(" b.showDate like '%"+date+"%'");
		}
		boolean boo = false;
		//and拼接
		for (String condition : list) {
			boo = appendWhereIfNeed(hql, boo);
			hql.append(condition);
		}
		hql.append(" order by b.postDate desc");
		log.info("模糊查询hql:"+ hql);
		return hql.toString();
	}
	
	/**
	 * 判断拼接where或者and
	 * @param hql
	 * @param boo
	 * @return
	 */
	private boolean appendWhereIfNeed(StringBuilder hql,boolean boo){
		if (!boo) {
			hql.append(" where");
			return true;
		}else {
			hql.append(" and");
		}
		return true;
	}

	/**
	 * @Description 请求单个文章
	 * @author Jeremy
	 * @date 2016年1月21日 下午6:09:23 
	 * @version V1.0
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/single_post.htm" ,method = RequestMethod.GET)
	public String single(HttpServletRequest request,Model model){
		//请求blog所有数据
		log.info("请求blog单个详细数据");
		int bid = Integer.parseInt(request.getParameter("bid"));
		String oper = request.getParameter("oper");
		
		//获取blog对象
		String hql = null;
		Blog blog = null;
		
		if (oper != null) {
			if (oper.equals("next"))
				hql = "from Blog b where b.bid < "+bid+" order by bid desc";
			else if (oper.equals("previous")) 
				hql = "from Blog b where b.bid > "+bid;
			
			blog = this.bservice.findByLimitFirst(hql,1);
		}
		if(blog == null){
			blog = this.bservice.getById(bid);
		}
		//阅读量+1
		this.bservice.updateViewTimes(blog);
		
		model.addAttribute(Content.SINGLE_BLOG, blog);
		
		//分类管理session,
		if (request.getSession().getAttribute(Content.ALL_CATEGORIES) == null) {
			List<Category> Categories = this.cateService.getAll();
			model.addAttribute(Content.ALL_CATEGORIES,Categories);
		}
		return "/front/single_post";
	}
	
	/**
	 * @Description 请求单个文章所有的评论,用于前端ajax显示
	 * @author Jeremy
	 * @date 2016年1月21日 下午6:09:23 
	 * @version V1.0
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/single_posts_comments.htm" ,method = RequestMethod.GET)
	public String single_posts_comments(HttpServletRequest request,Model model){
		//首先通过当前文章id,获取到所有的一级评论
		int bid = Integer.parseInt(request.getParameter("bid"));
		refreshComments(model, bid);
		return "/front/single_post_comments";
	}
	
	/**
	 * 博客评论页面的表单提交
	 * @param request
	 * @param model
	 * @param con
	 * @param r
	 */
	@RequestMapping(value = "/single_posts_comments.htm" ,method = RequestMethod.POST)
	public String single(HttpServletRequest request,HttpServletResponse response,Model model, Contact con){
		log.info("提交联系方式="+con);
		if (!verifyCaptcha(request, response)) {
			log.info("验证不正确~~");
		}else{
			log.info("~~验证正确~~");
		}
		//如果有文章id,则修改.
		int bid = Integer.parseInt(request.getParameter("bid")); //文章id插入Contact表
		int cid = Integer.parseInt(request.getParameter("cid")); //回复id插入reply表
		this.conService.saveOrUpdate(con,bid,cid);
		refreshComments(model, bid);
		return "/front/single_post_comments";
	}
	
	
	/**
	 * @Description 刷新客户端留言页面
	 * @author Jeremy
	 * @date 2016年1月22日 下午5:51:50 
	 * @version V1.0
	 * @param model
	 * @param bid
	 */
	public void refreshComments(Model model, int bid) {
		//首先通过当前文章id,获取到所有的一级评论
		String hql = "from Contact c where c.cid = 0 and c.bid=:bid";
		List<Contact> contacts = (List<Contact>) this.conService.findByNameParam(hql, "bid", bid);
		
		//然后通过当前一级评论获取二级评论
		hql = "from Contact c where c.cid=:cid";
		for (Contact contact : contacts) {
			List<Contact> replys = (List<Contact>)this.conService.findByNameParam(hql, "cid", contact.getId());
			contact.setConts(replys);
		}
		model.addAttribute(Content.ALL_CONTACT, contacts);
	}
	
	
	@RequestMapping(value = "/contacts.htm" ,method = RequestMethod.GET)
	public String contact(){
		//请求blog所有数据
		//log.info("联系我们");
		return "/front/contacts";
	}
	
	@RequestMapping(value = "/404.htm" ,method = RequestMethod.GET)
	public String notfound(){
		return "/front/404";
	}
	
	@RequestMapping(value = "/test.htm" ,method = RequestMethod.GET)
	public String test(){
		return "/front/test";
	}
	
	/**
	 * @Description 这部分代码可以单独成类, 所有要用的地方全部调用这个函数即可.
	 * @author Jeremy
	 * @return
	 */
	@RequestMapping(value = "/startCaptcha.htm" ,method = RequestMethod.GET)
	public void startCaptcha(HttpServletRequest request, HttpServletResponse response){
		try {
			//请求blog所有数据
			log.info("加载验证码");
			GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
			String resStr = "{}";
			
			//自定义userid
			String userid = "test";

			//进行验证预处理
			int gtServerStatus = gtSdk.preProcess(userid);
			
			//将服务器状态设置到session中
			request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
			//将userid设置到session中
			request.getSession().setAttribute("userid", userid);
			
			resStr = gtSdk.getResponseStr();
			PrintWriter out = response.getWriter();
			out.println(resStr);
			//return "/front/contacts";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 做验证码校验
	 * @param request
	 * @param response
	 */
	protected boolean verifyCaptcha(HttpServletRequest request, HttpServletResponse response){
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
			
		//从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		//从session中获取userid
		String userid = (String)request.getSession().getAttribute("userid");
		
		int gtResult = 0;

		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
			System.out.println("gt-server正常，向gt-server进行二次验证");
		} else {
			// gt-server非正常情况下，进行failback模式验证
			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
		}
		return gtResult == 1 ? true : false;
	}
	
	@RequestMapping(value = "/contacts.htm" ,method = RequestMethod.POST)
	public String contact(HttpServletRequest request,Model model,Contact con){
		log.info("提交联系方式="+con);
		log.info("=="+request.getParameter("name"));
		//留言方面,设置外键引用是0.不被任何文章引用
		this.conService.saveOrUpdate(con,0,0);
		return "redirect:/front/contacts.htm";
	}
	
	@RequestMapping(value = "/searchIP.htm" ,method = RequestMethod.GET)
	public String localIP(HttpServletRequest request, Model model){
		log.info("=========searchIP GET==========");
		String ip = AddressUtils.getIp(request);
		Visitor visitor = AppData.visitorMap.get(ip);
		if (visitor == null) {
			visitor = AddressUtils.createVisitorByIp(request, ip);
		}
		if(request.getSession().getAttribute(Content.LOCAL_VISITOR) == null)
			request.getSession().setAttribute(Content.LOCAL_VISITOR, visitor);
		return "/front/searchIP";
	}
	
	@RequestMapping(value = "/searchIP.htm" ,method = RequestMethod.POST)
	public void searchIP(HttpServletRequest request, HttpServletResponse response, Model model){
		String text = null;
		String ip = String.valueOf(request.getParameter("ip")); //文章id插入Contact表)
		if (!StringUtil.isBlank(ip)){
			Visitor visitor = AppData.visitorMap.get(ip);
			if (visitor == null) {
				visitor = AddressUtils.createVisitorByIp(request, ip);
			}
			//生成返回内容
			text = "<h5>IP: ?<br/></h5>\n<h6>Geo Address: ?\n<br/>Ali Address: ?<br/></h6>"; 
			text = UtilTools.format(text, ip, visitor.getAddress(), visitor.getDizhi());
			
		}else{
			text = "<h5>输入地址好像错了,二货~~</h5>";
		}
		//通过ajax返回给前端
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			text = URLDecoder.decode(text, "utf-8"); //把Ajax的传值，转换成utf-8
			out.print(text); 
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private LogoService loservice;
	private BlogService bservice;
	private ContactService conService;
	private CategorieService cateService;
//	private ProjectService proService;
	private TagService tagService;

	public ContactService getConService() {return conService;}
	public BlogService getBservice() {return bservice;}
	public LogoService getLoservice() {return loservice;}
	public CategorieService getCateService() {return cateService;}
//	public ProjectService getProService() {return proService;}
	public TagService getTagService() {return tagService;}

	
	@Resource
	public void setLoservice(LogoService loservice) {this.loservice = loservice;}
	@Resource
	public void setConService(ContactService conService) {this.conService = conService;}
	@Resource
	public void setBservice(BlogService bservice) {this.bservice = bservice;}
	@Resource
	public void setCateService(CategorieService cateService) {this.cateService = cateService;}
//	@Resource
//	public void setProService(ProjectService proService) {this.proService = proService;}
	@Resource
	public void setTagService(TagService tagService) {this.tagService = tagService;}

}
