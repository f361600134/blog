package com.fatiny.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.dao.CategoryDao;
import com.fatiny.pojo.Category;
import com.fatiny.pojo.JqGrid;
import com.fatiny.util.LogContext;
import com.fatiny.util.UtilTools;

/**
 * @Description
 * @bug 这个类有bug, 直接调用的Dao, 没有经过service.要修改 
 * @author Jeremy
 * @date 2015年12月3日 下午9:55:39 
 * @version V1.0
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	private static Logger log = LogContext.LOG_MODULE_CATEGORY;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.POST)
	public void list(HttpServletResponse response,int page,int rows){
		List<Category> cates = this.dao.getAll();
		JqGrid<Category> rst=new JqGrid<Category>();
		rst.setPage(page);
		rst.setRows(11);
		rst.setRecord(30);
		rst.setTotal(1);
		rst.setColModel(cates);
		UtilTools.renderJson(response, rst);
		log.info("category --> list.htm:"+rst);
//		return "admin/category";
	}
	
	/**
	 * 分類操作
	 * @return
	 */
	@RequestMapping(value = "/oper.htm",method = RequestMethod.POST)
	public void operation(HttpServletRequest request,HttpServletResponse response){
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		log.info("分類操作:"+oper+",id:"+id+",name:"+name);
		
		if(oper.equals("edit") ){
			this.dao.saveOrUpdate(new Category(Integer.valueOf(id),name));
		}else if(oper.equals("add")){
			this.dao.saveOrUpdate(new Category(name));
		}else if(oper.equals("del")){
			this.dao.delete(Integer.valueOf(id));
		} 
		UtilTools.renderJson(response, oper);
	}
	
	
	
	private CategoryDao dao;
	public CategoryDao getDao() {return dao;}
	@Resource
	public void setDao(CategoryDao dao) {this.dao = dao;}
}
