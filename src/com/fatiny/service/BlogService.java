package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.Blog;


public interface BlogService {
	
	int getCount();
	void delete(Integer id);
	void saveOrUpdate(Blog b);
	void updateViewTimes(Blog b);
	Blog getById(Integer id);
	List<Blog> getAll();
	Blog findByLimitFirst(String hql,int max);
	List<Blog> findListByLimitFirst(String hql,int max);
	List<Blog> findByLimit(String hql,int first, int max);
	List<Blog> findByLimit(String text,String hql,int first, int max);
//	List<Blog> findByNameParam(String hql,String param ,Object value);
//	List<Blog> findByNameParam(String hql,String[] param ,Object[] value);
}
