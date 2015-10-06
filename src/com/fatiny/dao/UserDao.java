package com.fatiny.dao;

import java.util.List;

import com.fatiny.pojo.User;

/**
 * 用户隐私信息,只提供新增
 * @author Janlr028
 *
 */
public interface UserDao {
	
	void add(User u);
	
	void saveOrUpdate(User u);
	
	void update(User u);
	
	void delete(Integer id);
	
	User getById(Integer id);
	
	//void getById();
	
	User login(String hql);
	
	List<User> getAll(String hql);
	
}
