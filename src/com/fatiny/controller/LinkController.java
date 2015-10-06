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
import com.fatiny.pojo.Link;
import com.fatiny.service.LinkService;
import com.fatiny.util.LogContext;
import com.fatiny.util.UtilTools;

@Controller
@RequestMapping("/link")
public class LinkController {
	private static Logger log = LogContext.LOG_MODULE_LINK;
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.POST)
	public void list(HttpServletResponse response,int page,int rows){
		List<Link> links = this.linkService.getAll();
		JqGrid<Link> rst=new JqGrid<Link>();
		rst.setPage(page);
		rst.setRows(rows);
		rst.setRecord(30);
		rst.setTotal(1);
		rst.setColModel(links);
		UtilTools.renderJson(response, rst);
		log.info("Link --> list.htm:"+rst);
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
		String site = request.getParameter("site");
		
		log.info("分類操作:"+oper+",id:"+id+",name:"+name);
		
		if(oper.equals("edit") ){
			this.linkService.saveOrUpdate(new Link(Integer.valueOf(id),name,site));
		}else if(oper.equals("add")){
			this.linkService.saveOrUpdate(new Link(name,site));
		}else if(oper.equals("del")){
			this.linkService.delete(Integer.valueOf(id));
		} 
		UtilTools.renderJson(response, oper);
	}
	
	private LinkService linkService;
	public LinkService getLinkService() {
		return linkService;
	}
	@Resource
	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}
	
}
