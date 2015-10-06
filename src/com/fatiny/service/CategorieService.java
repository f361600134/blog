package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.Category;

public interface CategorieService {
	
	void saveOrUpdate(Category c);
	
	void delete(Integer id);
	
	List<Category> getAll();
}
