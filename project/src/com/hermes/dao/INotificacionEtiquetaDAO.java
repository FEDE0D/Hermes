package com.hermes.dao;

import java.sql.ResultSet;
import java.util.List;

import com.hermes.model.Etiqueta;
import com.hermes.model.NotificacionEtiqueta;


public interface INotificacionEtiquetaDAO {
	public NotificacionEtiqueta createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(NotificacionEtiqueta notificacionEtiqueta);
	public List<Etiqueta> getEtiquetasParaNotificacion(Long idNotificacion);
}
