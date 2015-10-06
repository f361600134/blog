package com.fatiny.dao.common;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

/**
 * 基础操作Dao
 * @author Janlr028
 *
 */
@Component
public class BaseDao {
	
	private HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/** 添加操作 **/
	public void add(Object o){
		hibernateTemplate.save(o);
	}
	
	/** 删除操作 **/
	private void delete(Object o){
		hibernateTemplate.delete(o);
	}
	
	/**根据id删除对象*/
	public void delete(Class<?> c,Serializable Id){
		delete(getById(c, Id));
	}
	
	/**批量删除**/
	public void deleAll(List<?> list){
		hibernateTemplate.deleteAll(list);
	}
	
	/**
	 * 改变某一对象
	 * @param o
	 * @param Id
	 */
	public void update(Object o){
		hibernateTemplate.update(o);
	}
	
	/**修改(在session中已存在这个对象的修改)**/
	public void merge(Object o) {
		hibernateTemplate.merge(o);
	}
	
	/** 添加或者修改操作 **/
	public void addorupdate(Object o){
		hibernateTemplate.saveOrUpdate(o);
	}
	
	/**根据id获取对象*/
	public Object getById(Class<?> c,Serializable Id){
		return hibernateTemplate.get(c, Id);
	}
	 
	/**
	 * 通过Hql查询<br>
	 * @param hql 语句
	 * 
	 */
	public List<?> find(String hql){
		return hibernateTemplate.find(hql);
	}
	
	/**
	 * 通过hsql查询 一个参数
	 * @param hql
	 * @param value
	 * @return
	 */
	public List<?> find(String hql,Object value){
		return hibernateTemplate.find(hql, value);
	}
	
	/**
	 * 通过hsql查询 多个参数
	 * @param hql
	 * @param values
	 * @return
	 */
	public List<?> find(String hql,Object... values){
		return hibernateTemplate.find(hql, values);
	}
	
	/**
	 * 通过Hql查询<br>
	 * @param hql 语句
	 * 
	 */
	public List<?> findByNameParam(String hql,String paramName,Object value){
		return hibernateTemplate.findByNamedParam(hql, paramName, value);
	}
	
	/**
	 * 通过Hql查询<br>
	 * @param hql 语句
	 * 
	 */
	public List<?> findByNameParam(String hql,String[] paramName,Object[] values){
		return hibernateTemplate.findByNamedParam(hql, paramName, values);
	}
	
	/**
	 * 获取所有
	 */
	public List<?> getAll(Class<?> c) {
		return hibernateTemplate.find("from " + c.getName());
	}
	 
	/** 批量修改 */
	public void bulkUpdate(String hql, Object... objects) {
		hibernateTemplate.bulkUpdate(hql, objects);
	}
	
	/**获取数量*/
	public int getCount(String hql) {
		//Integer count = (Integer)hibernateTemplate.find(hql).listIterator().next();
		return ((Long)hibernateTemplate.iterate(hql).next()).intValue();
	}
	
	/**
	 * by limit
	 * @param hql
	 * @param max
	 * @return
	 */
	public List<?> findByLimitMax(String hql,int max){
		//需要手动关闭session
		//Session session = this.hibernateTemplate.getSessionFactory().openSession();
		//不需要手动关闭session
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(max);
		session.cancelQuery();
		return query.list();
	}
	
	/**
	 * by limit
	 * @param hql
	 * @param first
	 * @param max
	 * @return
	 */
	public List<?> findByLimit(String hql,int first,int max){
		Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		session.cancelQuery();
		return query.list();
	}
	 
}
