package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.ProjectDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Project;

@Component
public class ProjectDaoImpl extends BaseDao implements ProjectDao{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByNameParam(String hql, String paramName,
			Object value) {
		return (List<Project>)super.findByNameParam(hql, paramName, value);
	}

	@Override
	public void delete(Integer id) {
		super.delete(Project.class, id);
	}

	@Override
	public void saveOrUpdate(Project b) {
		super.addorupdate(b);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByLimitMax(String hql, int max) {
		return ((List<Project>)super.findByLimitMax(hql, max));
	}

	@Override
	public Project getById(Integer id) {
		return (Project) super.getById(Project.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getAll() {
		return (List<Project>)super.getAll(Project.class);
	}
}
