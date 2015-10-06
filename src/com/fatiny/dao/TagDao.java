package com.fatiny.dao;

import java.util.List;

import com.fatiny.pojo.Tag;

public interface TagDao {
	
	List<Tag> findByNameParam(String hql,String paramName,Object value);
	void delete(Integer id);
	void saveOrUpdate(Tag b);
	List<Tag> getAll();
}
