package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.UserDao;
import com.fatiny.pojo.User;
import com.fatiny.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	public UserDao getUserDao() {return userDao;}
	@Resource
	public void setUserDao(UserDao userDao) {this.userDao = userDao;}

	@Override
	public void add(User u) {
		this.userDao.add(u);
	}

	@Override
	public void update(User u) {
		this.userDao.update(u);
	}
	
	@Override
	public void saveOrUpdate(User u) {
		this.userDao.saveOrUpdate(u);
	}
	
	@Override
	public void delete(Integer Id) {
		this.userDao.delete(Id);
	}
	
	@Override
	public User getById(Integer Id) {
		return userDao.getById(Id);
	}

	@Override
	public User login(String hql) {
		return userDao.login(hql);
	}
	@Override
	public List<User> getAll(String hql) {
		return userDao.getAll(hql);
	}
}
