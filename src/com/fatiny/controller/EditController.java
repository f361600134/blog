package com.fatiny.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.pojo.Blog;
import com.fatiny.pojo.JqGrid;
import com.fatiny.service.BlogService;
import com.fatiny.util.LogContext;
import com.fatiny.util.UtilTools;

/**
 * @author Jemery
 * 后台管理页面. 文章管理类.
 * 博客的新增,修改,删除等.
 */
@Controller
@RequestMapping("/edit")
public class EditController {
	private static Logger log = LogContext.LOG_MODULE_BLOG;

	/**
	 * 使用saveorupdate
	 * 因为前台使用ajax定时更新 数据.所以后台每次只是更新数据而已.不做插入操作
	 * @return
	 */
	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
	public void add(HttpServletRequest request, Blog blog){
		log.info("文章编辑....blog:"+blog);
		
		this.blogservice.saveOrUpdate(blog);
//		return "/admin/login";
	}
	
	/**
	 * list显示文章
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.POST)
	public void list(HttpServletResponse response, int page,int rows){
//		log.info("list....");
		List<Blog> blogs = this.blogservice.getAll();
		
		JqGrid<Blog> rst=new JqGrid<Blog>();
//		log.info("查询到的文章...."+blogs);
		rst.setPage(page);
		rst.setRows(rows);
		rst.setRecord(30);
		rst.setTotal(1);
		rst.setColModel(blogs);
		rst.setBid(blogs.get(0).getBid());
		UtilTools.renderJson(response, rst);
	}

	/**
	 * 文章删除
	 * @return 
	 */
	@RequestMapping(value = "/delete.htm",method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response){
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		try {
			this.blogservice.delete(Integer.valueOf(id));
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
		}
		UtilTools.renderJson(response, oper);
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/add.htm",method = RequestMethod.POST)
//	public void add2(String title,String content){
//		log.info("文章编辑....");
//		System.out.printf("title=%s,content=%s",title,content);
//		
////		return "/admin/login";
//	}
	
	private BlogService blogservice;
	public BlogService getBlogservice() {
		return blogservice;
	}
	@Resource
	public void setBlogservice(BlogService blogservice) {
		this.blogservice = blogservice;
	}
}
