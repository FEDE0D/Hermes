package com.hermes.model;

import java.util.List;

public interface IGenericDAO<T> {
	
	public String getNameClass();
	public List<T> getAll();
	public T getById(int id);
	public boolean deteleById(int id);

}
