package com.fatiny.dao;

import com.fatiny.pojo.Logo;

public interface LogoDao {
	
	void update(Logo lo);
	
	Logo getById(int id);
	
}
