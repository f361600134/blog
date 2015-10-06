package com.fatiny.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fatiny.dao.LogoDao;
import com.fatiny.pojo.Logo;
import com.fatiny.service.LogoService;

@Component
public class LogoServiceImpl implements LogoService{
	
	private LogoDao logoDao;
	@Resource
	public void setLogoDao(LogoDao logoDao) {this.logoDao = logoDao;}
	public LogoDao getLogoDao() {return logoDao;}
	
	@Override
	public void update(Logo lo) {
		this.logoDao.update(lo);
		
	}
	@Override
	public Logo getById(int id) {
		return this.logoDao.getById(id);
	}
	
	
}
