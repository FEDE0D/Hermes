package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.Contenido;


public interface IContenidoDAO {

	public Contenido createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Contenido contenido);
	public void actualizar(Contenido contenido);
}
