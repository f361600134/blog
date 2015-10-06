package com.fatiny.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.pojo.JqGrid;
import com.fatiny.pojo.Tag;
import com.fatiny.service.TagService;
import com.fatiny.util.LogContext;
import com.fatiny.util.UtilTools;

@Controller
@RequestMapping("/tag")
public class TagController {
	private static Logger log = LogContext.LOG_MODULE_LINK;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.POST)
	public void list(HttpServletResponse response,int page,int rows){
		List<Tag> cates = this.service.getAll();
		JqGrid<Tag> rst=new JqGrid<Tag>();
		rst.setPage(page);
		rst.setRows(rows);
		rst.setRecord(30);
		rst.setTotal(1);
		rst.setColModel(cates);
		UtilTools.renderJson(response, rst);
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
			this.service.saveOrUpdate(new Tag(Integer.valueOf(id),name));
		}else if(oper.equals("add")){
			this.service.saveOrUpdate(new Tag(name));
		}else if(oper.equals("del")){
			this.service.delete(Integer.valueOf(id));
		} 
		UtilTools.renderJson(response, oper);
	}
	
	
	
	private TagService service;
	
	public TagService getService() {
		return service;
	}
	@Resource
	public void setService(TagService service) {
		this.service = service;
	}

}
