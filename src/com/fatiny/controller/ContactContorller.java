package com.fatiny.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fatiny.pojo.Contact;
import com.fatiny.pojo.JqGrid;
import com.fatiny.service.ContactService;
import com.fatiny.util.LogContext;
import com.fatiny.util.UtilTools;

@Controller
@RequestMapping("/contact")
public class ContactContorller {
	private static Logger log = LogContext.LOG_MODULE_CONTACT;
	
	/**
	 * 评论删除,修改
	 * @return
	 */
	@RequestMapping(value = "/oper.htm",method = RequestMethod.POST)
	public void operation(Contact contact,HttpServletRequest request,HttpServletResponse response){
		String oper = request.getParameter("oper");
		
		log.info("留言操作:"+oper);
		if(oper.equals("edit") ){
			this.conService.saveOrUpdate(contact,contact.getBid(),contact.getCid());
		}else if(oper.equals("del")){
			String id = request.getParameter("id");
			this.conService.delete(Integer.valueOf(id));
		} 
		
		UtilTools.renderJson(response, oper);
	}
	
	/**
	 * 获取所有列表
	 * @return
	 */
	@RequestMapping(value = "/list.htm",method = RequestMethod.POST)
	public void list(HttpServletResponse response, int page,int rows){
		log.info("list....");
		List<Contact> cos = this.conService.getAll();
		
		JqGrid<Contact> rst=new JqGrid<Contact>();
		log.info("查询到的留言....coss是不是null:"+(cos==null));
		rst.setPage(page);
		rst.setRows(rows);
		rst.setRecord(30);
		rst.setTotal(1);
		rst.setColModel(cos);
		
		UtilTools.renderJson(response, rst);
	}

	private ContactService conService;
	public ContactService getConService() {return conService;}
	@Resource
	public void setConService(ContactService conService) {this.conService = conService;}
	
}
