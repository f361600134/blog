package com.fatiny.dao.impl;

import org.springframework.stereotype.Component;

import com.fatiny.dao.VisitorDao;
import com.fatiny.dao.common.BaseDao;
import com.fatiny.pojo.Visitor;

/**
 * VisitorDao 实现类
 * @author Jeremy
 * @create 2015.11.23
 * @version 1.0
 */
@Component
public class VisitorDaoImpl extends BaseDao implements VisitorDao{

	@Override
	public void delete(Integer id) {
		super.delete(Visitor.class, id);
	}

	@Override
	public void saveOrUpdate(Visitor b) {
		super.addorupdate(b);
	}
}
