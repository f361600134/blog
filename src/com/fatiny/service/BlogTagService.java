package com.fatiny.service;

import com.fatiny.pojo.BlogTag;

public interface BlogTagService {
	void delete(Integer id);
	void saveOrUpdate(BlogTag b);
}
