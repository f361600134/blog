package com.fatiny.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.BlogTagDao;
import com.fatiny.pojo.BlogTag;
import com.fatiny.service.BlogTagService;

@Component
public class BlogTagServiceImpl implements BlogTagService{

	private BlogTagDao dao;
	public BlogTagDao getDao() {return dao;}
	@Resource
	public void setDao(BlogTagDao dao) {this.dao = dao;}

	@Override
	public void delete(Integer id) {
		this.dao.delete(id);
	}

	@Override
	public void saveOrUpdate(BlogTag b) {
		this.dao.saveOrUpdate(b);
	}

}
