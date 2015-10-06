package com.fatiny.dao;

import java.util.List;
import com.fatiny.pojo.Picture;

public interface PictureDao {
	List<Picture> findByNameParam(String hql,String paramName,Object value);
	void delete(Integer id);
	void saveOrUpdate(Picture b);
}
