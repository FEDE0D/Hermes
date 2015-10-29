package com.hermes.model.Categoria;

import java.sql.ResultSet;

public interface ICategoriaDAO {
	public Categoria createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Categoria categoria);
	public void actualizar(Categoria categoria);
}
