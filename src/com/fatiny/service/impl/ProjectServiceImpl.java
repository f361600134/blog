package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.ProjectDao;
import com.fatiny.pojo.Picture;
import com.fatiny.pojo.Project;
import com.fatiny.service.ProjectService;
import com.fatiny.util.Content;
import com.fatiny.util.StringUtil;

@Component
public class ProjectServiceImpl implements ProjectService {

	/**
	 * 按照时间排序,越新的时间排在最前
	 * 查询到6个项目放在主页
	 * 不满6个按最大返回
	 */
	@Override
	public List<Project> findByLimitMax() {
		String hql = "from Project p order by p.finishDate desc ";
		return dao.findByLimitMax(hql, 6);
	}
	@Override
	public List<Project> findByNameParam(String hql, String paramName,
			Object value) {
		return dao.findByNameParam(hql, paramName, value);
	}
	@Override
	public void delete(Integer id) {
		this.dao.delete(id);
	}
	@Override
	public void saveOrUpdate(Project p) {
		String [] pics= StringUtil.split(p.getPics(), ",");
		Picture picture = null;
		for (String pic : pics) {
			picture = new Picture(pic);
			p.addPictures(picture);
		}
		//检测描述有否句号
		if(StringUtil.checksyn(p.getCategories(),Content.CONFIG_FIELD_PERIOD)){
			p.setDetail(p.getCategories()+p.getDetail());
		}else {
			p.setDetail(p.getCategories()+"。"+p.getDetail());
		}
		this.dao.saveOrUpdate(p);
	}
	@Override
	public Project getById(Integer id) {
		return this.dao.getById(id);
	}
	@Override
	public Project getLatest() {
		String hql = "from Project p order by p.finishDate desc ";
		List<Project> projects = this.dao.findByLimitMax(hql, 1);
		return projects.isEmpty()?null:projects.get(0);
	}
	@Override
	public List<Project> getAll() {
		return this.dao.getAll();
	}
	
	private ProjectDao dao;
	public ProjectDao getDao() {return dao;}
	@Resource
	public void setDao(ProjectDao dao) {
		this.dao = dao;
	}


}
