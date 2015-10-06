package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.User;

/**
 * 成员业务接口
 * @author Janlr028
 *
 */
public interface UserService {
		
	void add(User u);
	void update(User u);
	void saveOrUpdate(User u);
	void delete(Integer Id);
	User getById(Integer Id);
	User login(String hql);
	List<User> getAll(String hql);
	
}
