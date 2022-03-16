package com.camcar.customer.service;

import java.util.List;


public interface ServiceDefinition<T> {

	boolean create(T entity);
	boolean update(int id, T entity);
	boolean delete(int id);
	T selectById(int id);
	List<T> selectAll();
	
}
