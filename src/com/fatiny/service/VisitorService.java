package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.Visitor;

/**
 * Visitor 业务接口类
 * @author Jeremy
 * @create 2015.11.23
 */
public interface VisitorService {
	void delete(Integer id);
	void saveOrUpdate(Visitor b);
	List<Visitor> getAll();
}
