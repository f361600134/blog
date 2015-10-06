package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.Project;

public interface ProjectService {
	List<Project> findByLimitMax();
	List<Project> findByNameParam(String hql,String paramName,Object value);
	void delete(Integer id);
	void saveOrUpdate(Project p);
	Project getById(Integer id);
	Project getLatest();
	List<Project> getAll();
}
