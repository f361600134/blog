package com.fatiny.service;

import java.util.List;

import com.fatiny.pojo.Picture;

public interface PictureService {
	List<Picture> findByNameParam(String hql,String paramName,Object value);
	void delete(Integer id);
	void saveOrUpdate(Picture b);
}
