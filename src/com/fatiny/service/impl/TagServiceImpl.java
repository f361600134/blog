package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.TagDao;
import com.fatiny.pojo.Tag;
import com.fatiny.service.TagService;

@Component
public class TagServiceImpl implements TagService{
	private TagDao dao;
	public TagDao getDao() {return dao;}
	@Resource
	public void setDao(TagDao dao) {this.dao = dao;}
	
	@Override
	public List<Tag> findByNameParam(String hql, String paramName, Object value) {
		return this.dao.findByNameParam(hql, paramName, value);
	}

	@Override
	public void delete(Integer id) {
		this.dao.delete(id);
	}
	
	@Override
	public void saveOrUpdate(Tag b) {
		this.dao.saveOrUpdate(b);
	}

	@Override
	public List<Tag> getAll() {
		return this.dao.getAll();
	}

}
