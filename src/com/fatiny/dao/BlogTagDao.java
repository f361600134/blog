package com.fatiny.dao;

import com.fatiny.pojo.BlogTag;

public interface BlogTagDao {
	void delete(Integer id);
	void saveOrUpdate(BlogTag b);
}
