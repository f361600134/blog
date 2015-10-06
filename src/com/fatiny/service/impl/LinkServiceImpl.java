package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.LinkDao;
import com.fatiny.pojo.Link;
import com.fatiny.service.LinkService;

@Component
public class LinkServiceImpl implements LinkService{
	
	private LinkDao dao;
	public LinkDao getDao() {return dao;}
	@Resource
	public void setDao(LinkDao dao) {this.dao = dao;}

	@Override
	public void delete(Integer id) {
		this.dao.delete(id);
	}

	@Override
	public void saveOrUpdate(Link b) {
		this.dao.saveOrUpdate(b);
	}

	@Override
	public List<Link> getAll() {
		return this.dao.getAll();
	}

}
