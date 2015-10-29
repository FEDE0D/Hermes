package com.hermes.model.Etiqueta;

import java.sql.ResultSet;


public interface IEtiquetaDAO {
	public Etiqueta createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Etiqueta etiqueta);
	public void actualizar(Etiqueta etiqueta);
}
