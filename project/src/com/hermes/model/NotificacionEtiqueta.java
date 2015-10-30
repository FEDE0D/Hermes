package com.hermes.model;

public class NotificacionEtiqueta {
	
	public NotificacionEtiqueta(){}
	public NotificacionEtiqueta(int idNotificacion, int idEtiqueta) {
		super();
		this.idNotificacion = idNotificacion;
		this.idEtiqueta = idEtiqueta;
	}

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public int getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(int idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	private int idNotificacion, idEtiqueta;
	
}
