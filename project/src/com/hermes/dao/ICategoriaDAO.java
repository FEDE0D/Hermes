package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.Categoria;

public interface ICategoriaDAO {
	public Categoria createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Categoria categoria);
	public void actualizar(Categoria categoria);
}
