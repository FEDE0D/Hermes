package com.hermes.model.Contexto;

import java.sql.ResultSet;

public interface IContextoDAO {
	public Contexto createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Contexto contexto);
	public void actualizar(Contexto contexto);
}
