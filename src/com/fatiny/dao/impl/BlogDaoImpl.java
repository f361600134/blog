package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.BlogDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Blog;

@Component
public class BlogDaoImpl extends BaseDao implements BlogDao{

	@Override
	public void delete(Integer id) {
		super.delete(Blog.class,id);
	}

	@Override
	public void saveOrUpdate(Blog b) {
		super.addorupdate(b);
	}
	@Override
	public Blog getById(Integer id) {
		return (Blog)super.getById(Blog.class, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getAllBlog() {
		return (List<Blog>)super.getAll(Blog.class);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getByNameParam(String hql, String paramName, Object value) {
		return (List<Blog>)super.findByNameParam(hql, paramName, value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getByNameParam(String hql, String[] param, Object[] values) {
		return (List<Blog>)super.findByNameParam(hql, param, values);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> getByLimit(String hql, int first, int max) {
		return (List<Blog>) super.findByLimit(hql, first, max);
	}
	
	@Override
	public int getCount(String hql) {
		return super.getCount(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> findByLimitFirst(String hql, int max) {
		return (List<Blog>)super.findByLimitMax(hql, max);
		//List<Blog> blogs = (List<Blog>) super.findByLimitMax(hql, max);
		//return blogs.isEmpty() ? null : blogs.get(0);
	}

}
