package com.fatiny.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.PictureDao;
import com.fatiny.pojo.Picture;
import com.fatiny.service.PictureService;
@Component
public class PrictureServiceImpl implements PictureService {

	@Override
	public List<Picture> findByNameParam(String hql, String paramName,
			Object value) {
		return dao.findByNameParam(hql, paramName, value);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void saveOrUpdate(Picture b) {
		dao.saveOrUpdate(b);
	}
	
	private PictureDao dao;
	public PictureDao getDao() {return dao;}
	@Resource
	public void setDao(PictureDao dao) {this.dao = dao;}
}
