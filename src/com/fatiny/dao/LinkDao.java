package com.fatiny.dao;

import java.util.List;
import com.fatiny.pojo.Link;

public interface LinkDao {
	void delete(Integer id);
	void saveOrUpdate(Link b);
	List<Link> getAll();
}
