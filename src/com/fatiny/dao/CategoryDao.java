package com.fatiny.dao;

import java.util.List;
import com.fatiny.pojo.Category;

public interface CategoryDao {
	
	void saveOrUpdate(Category c);
	
	void delete(Integer id);
	
	List<Category> getAll();
	
}
