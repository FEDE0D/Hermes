package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.Contexto;

public interface IContextoDAO {
	public Contexto createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Contexto contexto);
	public void actualizar(Contexto contexto);
}
