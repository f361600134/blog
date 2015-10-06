package com.fatiny.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fatiny.dao.PictureDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Picture;

@Component
public class PictureDaoImpl extends BaseDao implements PictureDao{
	@SuppressWarnings("unchecked")
	@Override
	public List<Picture> findByNameParam(String hql, String paramName,
			Object value) {
		return (List<Picture>)super.findByNameParam(hql, paramName, value);
	}

	@Override
	public void delete(Integer id) {
		super.delete(Picture.class, id);
	}

	@Override
	public void saveOrUpdate(Picture b) {
		super.addorupdate(b);
	}
}
