package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.Etiqueta;


public interface IEtiquetaDAO {
	public Etiqueta createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Etiqueta etiqueta);
	public void actualizar(Etiqueta etiqueta);
}
