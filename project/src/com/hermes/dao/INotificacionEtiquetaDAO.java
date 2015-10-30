package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.NotificacionEtiqueta;


public interface INotificacionEtiquetaDAO {
	public NotificacionEtiqueta createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(NotificacionEtiqueta notificacionEtiqueta);
}
