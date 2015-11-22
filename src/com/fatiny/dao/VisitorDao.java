package com.fatiny.dao;

import com.fatiny.pojo.Visitor;

/**
 * 访问者信息Dao
 * @author Jeremy
 * @version 1.0
 * @create 2015.11.21
 */
public interface VisitorDao {
	void delete(Integer id);
	void saveOrUpdate(Visitor b);
}
