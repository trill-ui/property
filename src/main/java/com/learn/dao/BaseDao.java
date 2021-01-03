package com.learn.dao;

import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 */
public interface BaseDao<T> {
	//保存
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	//修改
	int update(T t);
	
	int update(Map<String, Object> map);
	//修改
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(Object[] id);
    //查询
	T queryObject(Object id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
