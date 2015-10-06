package com.fatiny.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.pojo.Blog;
import com.fatiny.pojo.Category;
import com.fatiny.pojo.Contact;
import com.fatiny.pojo.Page;
import com.fatiny.pojo.Tag;
import com.fatiny.service.BlogService;
import com.fatiny.service.CategorieService;
import com.fatiny.service.ContactService;
import com.fatiny.service.LogoService;
import com.fatiny.service.ProjectService;
import com.fatiny.service.TagService;
import com.fatiny.util.Content;
import com.fatiny.util.DateUtil;
import com.fatiny.util.LogContext;
import com.fatiny.util.StringUtil;
import com.fatiny.util.UtilTools;

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
	}*/
	
	/**
	 * 有图模式
	 * @param request
	 * @param model
	 * @return
	 */
	//@RequestMapping(value = "/posts_with_media.htm",method = RequestMethod.GET)
	@RequestMapping(value = "/index.htm",method = RequestMethod.GET)
	public String postsWithImages(HttpServletRequest request,Model model){
		find(request, model, 0);
		return "/front/posts_with_images";
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
	
	private void find(HttpServletRequest request, Model model,int blogType) {
		long beginTime = System.currentTimeMillis();
		//接受请求
		String cateid = request.getParameter("cateid");
		String text = request.getParameter("text");
		String date = request.getParameter("date");
		String tagid = request.getParameter("tagid");

		log.info("请求blog");
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
			
			//blog主体内容request
			model.addAttribute(Content.PAGE,page);
			//翻页标签request
			model.addAttribute(Content.BLOG_LINK,appedLink(tagid, cateid, text, date, blogType,page));
			
			/*侧边栏使用.这部分可以改成,登陆是就存入session.*/
			//分类管理session,
			if (request.getSession().getAttribute(Content.ALL_CATEGORIES) == null){
				List<Category> Categories = this.cateService.getAll();
				request.getSession().setAttribute(Content.ALL_CATEGORIES,Categories);
			}
			//标签session
			if (request.getSession().getAttribute(Content.ALL_TAG) == null){
				List<Tag> tags = this.tagService.getAll();
				request.getSession().setAttribute(Content.ALL_TAG,tags);
			}
			//最热门文章session
			if (request.getSession().getAttribute(Content.POPULAR_BLOG) == null){
				hql = "from Blog b order by b.viewTimes desc";
				List<Blog> tags = this.bservice.findListByLimitFirst(hql, 3);
				request.getSession().setAttribute(Content.POPULAR_BLOG,tags);
			}
			//最新的project
//			if (request.getSession().getAttribute(Content.RECENT_PROJECT) == null){
//				List<Project> pros = this.proService.findByLimitMax();
//				model.addAttribute(Content.RECENT_PROJECT, pros);
//			}
			/**/
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

	
	
	@RequestMapping(value = "/single_post.htm" ,method = RequestMethod.GET)
	public String single(HttpServletRequest request,Model model){
		long beginTime = System.currentTimeMillis();
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
//		blog.setViewTimes(blog.getViewTimes()+1);
		this.bservice.updateViewTimes(blog);
		
		model.addAttribute(Content.SINGLE_BLOG, blog);
		//log.info("blog:"+blog.getContent());
		//首先通过当前文章id,获取到所有的一级评论
		hql = "from Contact c where c.cid = 0 and c.bid=:bid";
		List<Contact> contacts = (List<Contact>) this.conService.findByNameParam(hql, "bid", bid);
		
		//然后通过当前一级评论获取二级评论
		hql = "from Contact c where c.cid=:cid";
		for (Contact contact : contacts) {
			List<Contact> replys = (List<Contact>)this.conService.findByNameParam(hql, "cid", contact.getId());
			contact.setConts(replys);
		}
		
		model.addAttribute(Content.ALL_CONTACT, contacts);
		
		//分类管理session,
		if (request.getSession().getAttribute(Content.ALL_CATEGORIES) == null) {
			List<Category> Categories = this.cateService.getAll();
			model.addAttribute(Content.ALL_CATEGORIES,Categories);
		}
		log.info("The single total time"+(System.currentTimeMillis()-beginTime));
		
		return "/front/single_post";
	}
	
	/**
	 * 博客评论页面的表单提交
	 * @param request
	 * @param model
	 * @param con
	 * @param r
	 */
	@RequestMapping(value = "/single-post.htm" ,method = RequestMethod.POST)
	public void single(HttpServletRequest request,Model model,Contact con){
		log.info("提交联系方式="+con);
		//如果有文章id,则修改.
		int bid = Integer.parseInt(request.getParameter("bid")); //文章id插入Contact表
		int cid = Integer.parseInt(request.getParameter("cid")); //回复id插入reply表
		this.conService.saveOrUpdate(con,bid,cid);
		single(request, model);
	}
	
	@RequestMapping(value = "/contacts.htm" ,method = RequestMethod.GET)
	public String contact(Model model){
		//请求blog所有数据
		log.info("联系我们");
		return "/front/contacts";
	}

	@RequestMapping(value = "/contacts.htm" ,method = RequestMethod.POST)
	public String contact(HttpServletRequest request,Model model,Contact con){
		log.info("提交联系方式="+con);
		log.info("=="+request.getParameter("name"));
		//留言方面,设置外键引用是0.不被任何文章引用
		this.conService.saveOrUpdate(con,0,0);
		return "redirect:/front/contacts.htm";
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
