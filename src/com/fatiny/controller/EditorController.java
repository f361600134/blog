package com.fatiny.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.pojo.Blog;
import com.fatiny.pojo.BlogTag;
import com.fatiny.pojo.Category;
import com.fatiny.pojo.Editor;
import com.fatiny.pojo.Project;
import com.fatiny.pojo.Tag;
import com.fatiny.service.BlogService;
import com.fatiny.service.BlogTagService;
import com.fatiny.service.CategorieService;
import com.fatiny.service.EditorService;
import com.fatiny.service.PictureService;
import com.fatiny.service.ProjectService;
import com.fatiny.service.TagService;
import com.fatiny.util.Content;
import com.fatiny.util.LogContext;
import com.fatiny.util.StringUtil;

@Controller
@RequestMapping("/editor")
public class EditorController {
	
	private static Logger log = LogContext.LOG_MODULE_EDITOR;
	 
	@RequestMapping(value = "/ueeditor.htm",method = RequestMethod.GET)
	public String ueeditor(Model model){
		log.info("进入ue编辑器....");
		//分类
		List<Category> Categories = this.cateService.getAll();
		model.addAttribute(Content.ALL_CATEGORIES,Categories);
		
		//标签
		List<Tag> tags = this.tagService.getAll();
		model.addAttribute(Content.ALL_TAG,tags);
		return "/user/ueEditor";
	}
	
	
	@RequestMapping(value = "/ueeditor2.htm",method = RequestMethod.GET)
	public String ueeditor2(Model model){
		log.info("进入ue编辑器....");
		return "/user/ueEditor2";
	}
	
	@RequestMapping(value = "/project.htm",method = RequestMethod.GET)
	public String project(Model model){
		log.info("进入ue编辑器....");
		return "/user/project";
	}
	
	/**
	 * UE 使用注入方式获取前端内容
	 * @param title
	 * @param content
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/project.htm",method = RequestMethod.POST)
	public void project(Project p,HttpServletRequest request,Model model){
		log.info("title="+p.getTitle()+" ,desc="+p.getCategories()
				+" ,downloadurl="+p.getDownloadurl()+" ,pics="+p.getPics());
		this.proService.saveOrUpdate(p);
		
	}
	
	
//	/**
//	 * UE 使用注入方式获取前端内容
//	 * @param title
//	 * @param content
//	 * @param category
//	 * @param model
//	 * @return
//	 */
//	@Deprecated
//	@RequestMapping(value = "/ueeditor.htm",method = RequestMethod.POST)
//	public String ueeditor(String title,String content,String category, Model model){
//		log.info("title="+title+" ,content="+content+" ,category="+category);
//		String str = StringUtil.escapeHTMLTag(content);
//		log.info("before ="+str);
//		str = StringUtil.unEscapeHTMLTag(content);
//		log.info("behind ="+str+",istrue="+content.equals(str));
//		model.addAttribute("content",content);
//		return "/user/ueShow";
//	}
	
	/**
	 * UE 使用注入方式获取前端内容
	 * @param title
	 * @param content
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ueeditor.htm",method = RequestMethod.POST)
	public String ueeditor(Editor e,HttpServletRequest request,Model model){
		String cate = request.getParameter("cate");
		String key = request.getParameter("key");
		
		Blog blog = new Blog(e.getTitle(),e.getContent(),e.getMdContent());
		blog.setPostDate(new Date());
		
		if (cate != null) 
			blog.setCateId(Integer.valueOf(cate));
		
		this.blogservice.saveOrUpdate(blog);
		//保存新的标签
		if (!StringUtil.isBlank(key)) {
			//首先添加到tag表
			List<Tag> bts = this.tagService.getAll();
			String [] tags = key.split(",");
			//截取数组
			tags = Arrays.copyOfRange(tags, 0, 3);
			for (String tag : tags) {
				int tid = find(bts,tag);
				if (tid == 0) {
					this.tagService.saveOrUpdate(new Tag(tag));
				}
			}
			List<Integer> list = new ArrayList<Integer>();
			bts = this.tagService.getAll();
			System.out.println("bts:"+bts+",tags:"+tags);
			for (String tag : tags) {
				int tid = find(bts,tag);
				list.add(tid);
			}
			for (Integer tid : list) {
				this.btService.saveOrUpdate(new BlogTag(tid, blog.getBid()));
			}
		}
		
		model.addAttribute("content",blog.getContent());
		return "/user/ueShow";
	}
	
	private int find(List<Tag> bts, String key){
		for (Tag t : bts) {
			if (t.getName().equals(key)) {
				return t.getId();
			}
		}
		return 0;
	}
	
	
	/**
	 * UE 使用request方式获取前端内容
	 * @return
	 */
//	@Deprecated
//	@RequestMapping(value = "/ueeditor.htm",method = RequestMethod.POST)
//	public String ueeditor(HttpServletRequest request,HttpServletResponse response){
//		String title = (String) request.getParameter("title");
//		String content = (String) request.getParameter("content");
//		String category = (String) request.getParameter("category");
//		
//		log.info("title="+title+" ,content="+content+" ,category="+category);
//		
//		
//		return "/user/ueEditor";
//	}
	
	/**
	 * CK编辑器入口
	 * @return
	 */
	@RequestMapping(value = "/ckeditor.htm",method = RequestMethod.GET)
	public String ckeditor(){
		log.info("进入ck编辑器....");
		return "/user/ckEditor";
	}
	
	/**
	 * CK 注入方式获取前端内容
	 * @param title
	 * @param content
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ckeditor.htm",method = RequestMethod.POST)
	public String ckeditor(String title,String content,String category, Model model){
		log.info("title="+title+" ,content="+content+" ,category="+category);
		String str = StringUtil.escapeHTMLTag(content);
		
//		log.info("before ="+str);
		
//		str = StringUtil.unEscapeHTMLTag(content);
		Blog blog = new Blog(title,content,"","Jeremy");
		blog.setPostDate(new Date());
		this.blogservice.saveOrUpdate(blog);
		
		log.info("behind ="+str+",istrue="+content.trim().equals(str));
		model.addAttribute("content",content);
		return "/user/ckShow";
	}
	
	private EditorService eservice;
	public EditorService getEservice() {return eservice;}
	@Resource
	public void setEservice(EditorService eservice) {this.eservice = eservice;}
	
	private BlogService blogservice;
	public BlogService getBlogservice() {return blogservice;}
	@Resource
	public void setBlogservice(BlogService blogservice) {
		this.blogservice = blogservice;
	}
	
	private CategorieService cateService;
	public CategorieService getCateService() {return cateService;}
	@Resource
	public void setCateService(CategorieService cateService) {this.cateService = cateService;}
	
	private TagService tagService;
	public TagService getTagService() {return tagService;}
	@Resource
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}
	
	private BlogTagService btService;
	public BlogTagService getBtService() {return btService;}
	@Resource
	public void setBtService(BlogTagService btService) {
		this.btService = btService;
	}
	
	private PictureService picService;
	public PictureService getPicService() {return picService;}
	@Resource
	public void setPicService(PictureService picService) {
		this.picService = picService;
	}
	
	private ProjectService proService;
	public ProjectService getProService() {return proService;}
	@Resource
	public void setProService(ProjectService proService) {
		this.proService = proService;
	}
	
	
}
