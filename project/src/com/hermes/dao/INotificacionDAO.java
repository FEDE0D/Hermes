package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.Notificacion;


public interface INotificacionDAO {
	public Notificacion createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Notificacion etiqueta);
	public void actualizar(Notificacion etiqueta);
}
