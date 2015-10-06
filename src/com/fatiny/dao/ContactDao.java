package com.fatiny.dao;

import java.util.List;

import com.fatiny.pojo.Contact;


public interface ContactDao {
	
	void saveOrUpdate(Contact c);
	Contact getById(Integer id);
	List<Contact> getAll();
	List<Contact> find(String hql);
	List<Contact> findByNameParam(String hql,String param ,Object value);
	List<Contact> findByLimitMax(String sql,int max);
	void delete(Integer id);
	int getCount(String hql);
	
}
