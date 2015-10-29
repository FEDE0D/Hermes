package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.ContenidoContexto;

public interface IContenidoContextoDAO {
	public ContenidoContexto createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(ContenidoContexto contenidoContexto);
}
