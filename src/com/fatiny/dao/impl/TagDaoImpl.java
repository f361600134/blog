package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.TagDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Tag;

@Component
public class TagDaoImpl extends BaseDao implements TagDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> findByNameParam(String hql, String paramName, Object value) {
		return (List<Tag>)super.findByNameParam(hql, paramName, value);
	}

	@Override
	public void delete(Integer id) {
		super.delete(Tag.class,id);
	}

	@Override
	public void saveOrUpdate(Tag b) {
		super.addorupdate(b);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> getAll() {
		return (List<Tag>)super.getAll(Tag.class);
	}
}
