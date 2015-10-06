package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.CategoryDao;
import com.fatiny.pojo.Category;
import com.fatiny.service.CategorieService;

@Component
public class CategorieServiceImpl implements CategorieService{

	@Override
	public void saveOrUpdate(Category c) {
		this.cateDao.saveOrUpdate(c);
	}

	@Override
	public void delete(Integer id) {
		this.cateDao.delete(id);
	}

	@Override
	public List<Category> getAll() {
		return this.cateDao.getAll();
	}
	
	private CategoryDao cateDao;
	public CategoryDao getCateDao() {
		return cateDao;
	}
	@Resource
	public void setCateDao(CategoryDao cateDao) {
		this.cateDao = cateDao;
	}
	
	
	
}
