package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.UserDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.User;

@Component
public class UserDaoImpl  extends BaseDao implements UserDao{

	@Override
	public void add(User u) {
		super.add(u);
	}

	@Override
	public void update(User u) {
		super.update(u);
	}

	@Override
	public void delete(Integer Id) {
		super.delete(User.class , Id);
	}

	@Override
	public User login(String sql) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) super.find(sql);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User getById(Integer Id) {
		return (User)super.getById(User.class, Id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll(String hql) {
		return (List<User>) super.find(hql);
	} 	
	 

	@Override
	public void saveOrUpdate(User u) {
		super.addorupdate(u);
	}

}
