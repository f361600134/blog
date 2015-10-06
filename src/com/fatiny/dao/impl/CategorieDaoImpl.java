package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.CategoryDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Category;

@Component
public class CategorieDaoImpl  extends BaseDao implements CategoryDao{

	@Override
	public void saveOrUpdate(Category c) {
		super.addorupdate(c);
	}

	@Override
	public void delete(Integer id) {
		super.delete(Category.class,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAll() {
		return (List<Category>) super.getAll(Category.class);
	}

	
}
