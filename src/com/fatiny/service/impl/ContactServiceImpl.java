package com.fatiny.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.ContactDao;
import com.fatiny.pojo.Contact;
import com.fatiny.service.ContactService;

@Component
public class ContactServiceImpl implements ContactService{

	
	private ContactDao conDao;
	public ContactDao getBlogDao() {return conDao;}
	@Resource
	public void setBlogDao(ContactDao conDao) {
		this.conDao = conDao;
	}
	
	@Override
	public Contact getById(Integer id){
		return this.conDao.getById(id);
	}
	

	/**
	 * 获取到所有留言,不能用sql查询,每次查询十个
	 * 
	 */
	@Override
	public List<Contact> getAll() {
		return this.conDao.getAll();
	}

	@Override
	public List<Contact> find(String hql) {
		return this.conDao.find(hql);
	}
	@Override
	public void saveOrUpdate(Contact c,int bid,int cid) {
		//留言方面,设置外键引用是0.不被任何文章引用
		c.setPostDate(new Date());
		c.setBid(bid);
		c.setCid(cid);
		this.conDao.saveOrUpdate(c);
	}
	
	@Override
	public List<Contact> findByNameParam(String hql, String param, Object value) {
		return this.conDao.findByNameParam(hql, param, value);
	}
	
	@Override
	public void delete(Integer id) {
		this.conDao.delete(id);
	}
	
	@Override
	public List<Contact> findByLimitMax(String hql,int max) {
		//String  hql = "from Contact c order by c.viewtimes desc";
		//String  hql = "from Contact";
		return this.conDao.findByLimitMax(hql,3);
	}

}
