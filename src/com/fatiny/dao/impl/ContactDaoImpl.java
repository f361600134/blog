package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.ContactDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Contact;

@Component
public class ContactDaoImpl extends BaseDao implements ContactDao{

	@Override
	public Contact getById(Integer id) {
		return (Contact)super.getById(Contact.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAll() {
		return (List<Contact>) super.getAll(Contact.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> find(String hql) {
		return (List<Contact>)super.find(hql);
	}

	@Override
	public void saveOrUpdate(Contact c) {
		super.addorupdate(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findByNameParam(String hql, String param, Object value) {
		return (List<Contact>)super.findByNameParam(hql, param, value);
	}

	@Override
	public void delete(Integer id) {
		super.delete(Contact.class,id);
	}

	@Override
	public int getCount(String hql) {
		return super.getCount(hql);
	}

	@Override
	public List<Contact> findByLimitMax(String hql, int max) {
		return (List<Contact>)super.findByLimitMax(hql,max);
	}

	
}
