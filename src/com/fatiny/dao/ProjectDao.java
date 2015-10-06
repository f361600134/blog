package com.fatiny.dao;

import java.util.List;
import com.fatiny.pojo.Project;

public interface ProjectDao {
	List<Project> findByLimitMax(String hql,int max);
	List<Project> findByNameParam(String hql,String paramName,Object value);
	void delete(Integer id);
	void saveOrUpdate(Project b);
	Project getById(Integer id);
	List<Project> getAll();
}
