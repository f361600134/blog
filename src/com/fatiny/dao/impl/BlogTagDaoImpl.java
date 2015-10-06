package com.fatiny.dao.impl;

import org.springframework.stereotype.Component;

import com.fatiny.dao.BlogTagDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.BlogTag;

@Component
public class BlogTagDaoImpl extends BaseDao implements BlogTagDao{

	@Override
	public void delete(Integer id) {
		super.delete(BlogTag.class, id);
	}

	@Override
	public void saveOrUpdate(BlogTag b) {
		super.addorupdate(b);
	}

}
