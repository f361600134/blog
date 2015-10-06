package com.fatiny.service;

import java.util.List;
import com.fatiny.pojo.Contact;

public interface ContactService {
	
	void delete(Integer id);
	void saveOrUpdate(Contact c,int bid,int cid);
	Contact getById(Integer id);
	List<Contact> getAll();
	List<Contact> find(String hql);
	List<Contact> findByNameParam(String hql,String param ,Object value);
	List<Contact> findByLimitMax(String hql,int max);	
}
