package com.fatiny.dao;

import java.util.List;
import com.fatiny.pojo.Blog;


/**
 * 日志接口类
 * 提供基本增删改查功能
 * @author Jemery
 */
public interface BlogDao {

	int getCount(String hql);
	void delete(Integer id);
	void saveOrUpdate(Blog b);
	Blog getById(Integer id);
	List<Blog> getAllBlog();
	List<Blog> findByLimitFirst(String hql,int first);
	List<Blog> getByLimit(String hql,int param ,int value);
	List<Blog> getByNameParam(String hql,String paramName,Object value);
	List<Blog> getByNameParam(String hql,String[] param ,Object[] value);
	
}
