package com.fatiny.service;

import com.fatiny.pojo.Logo;

public interface LogoService {
	
	Logo getById(int id);
	void update(Logo lo);
	
}
