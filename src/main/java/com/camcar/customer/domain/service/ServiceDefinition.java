package com.camcar.customer.domain.service;

import java.util.List;

public interface ServiceDefinition<T> {
	
	T create(T entity);
	boolean update(int id, T entity);
	boolean delete(int id);
	T selectById(int id);
	List<T> selectAll();

}
