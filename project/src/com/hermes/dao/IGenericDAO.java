package com.hermes.dao;

import java.util.List;

public interface IGenericDAO<T> {
	
	public String getNameClass();
	public List<T> getAll();
	public T getById(Long id);
	public boolean deteleById(Long id);

}
