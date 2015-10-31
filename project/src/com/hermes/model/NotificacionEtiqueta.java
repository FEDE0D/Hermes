package com.hermes.model;

public class NotificacionEtiqueta {
	
	public NotificacionEtiqueta(){}
	public NotificacionEtiqueta(Long idNotificacion, Long idEtiqueta) {
		super();
		this.idNotificacion = idNotificacion;
		this.idEtiqueta = idEtiqueta;
	}

	public Long getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(Long idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public Long getIdEtiqueta() {
		return idEtiqueta;
	}

	public void setIdEtiqueta(Long idEtiqueta) {
		this.idEtiqueta = idEtiqueta;
	}

	private Long idNotificacion, idEtiqueta;
	
}
