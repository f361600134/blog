package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.VisitorDao;
import com.fatiny.pojo.Visitor;
import com.fatiny.service.VisitorService;

@Component
public class VisitorServiceImpl implements VisitorService{
	
	private VisitorDao dao;
	public VisitorDao getDao() {return dao;}
	@Resource
	public void setDao(VisitorDao dao) {this.dao = dao;}

	@Override
	public void delete(Integer id) {
		this.dao.delete(id);
	}

	@Override
	public void saveOrUpdate(Visitor b) {
		this.dao.saveOrUpdate(b);
	}

	@Override
	public List<Visitor> getAll() {
		//return this.dao.getAll();
		return null;
	}

}
