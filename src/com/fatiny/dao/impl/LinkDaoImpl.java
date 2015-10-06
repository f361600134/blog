package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.LinkDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Link;

@Component
public class LinkDaoImpl extends BaseDao implements LinkDao{

	@Override
	public void delete(Integer id) {
		super.delete(Link.class, id);
	}

	@Override
	public void saveOrUpdate(Link b) {
		super.addorupdate(b);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Link> getAll() {
		return (List<Link>)super.getAll(Link.class);
	}

}
