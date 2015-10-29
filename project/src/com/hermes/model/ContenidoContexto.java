package com.hermes.model;

public class ContenidoContexto {
	private int idContenido, idContexto;
	
	public ContenidoContexto(int idContenido, int idContexto) {
		super();
		this.idContenido = idContenido;
		this.idContexto = idContexto;
	}
	public ContenidoContexto(){}
	public int getIdContenido() {
		return idContenido;
	}

	public void setIdContenido(int idContenido) {
		this.idContenido = idContenido;
	}

	public int getIdContexto() {
		return idContexto;
	}

	public void setIdContexto(int idContexto) {
		this.idContexto = idContexto;
	}
	
}
