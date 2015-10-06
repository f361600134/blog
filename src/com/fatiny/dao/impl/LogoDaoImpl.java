package com.fatiny.dao.impl;

import org.springframework.stereotype.Component;

import com.fatiny.dao.LogoDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Logo;

/**
 * 修改Logo信息栏,标语...后续可增加其他
 * @author Jemery
 *
 */
@Component
public class LogoDaoImpl extends BaseDao implements LogoDao{

	@Override
	public void update(Logo lo) {
		super.update(lo);
	}

	@Override
	public Logo getById(int id) {
		return (Logo)super.getById(Logo.class, id);
	}
	
	

}
