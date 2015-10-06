package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.Link;

public interface LinkService {
	void delete(Integer id);
	void saveOrUpdate(Link b);
	List<Link> getAll();
}
