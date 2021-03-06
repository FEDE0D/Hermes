package com.hermes.model;

import java.util.ArrayList;

public class Etiqueta {
	private Long id;
	private String descripcion;

	public Etiqueta(Long id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Etiqueta() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String toString(){
		return descripcion;
	}
	
	public static class ListaEtiquetas extends ArrayList<Etiqueta> {

		public String toString() {
			String representacion = "";
			for (Etiqueta e : this) {
				representacion += e.getDescripcion() + ", ";
			}
			representacion = representacion.substring(0, Math.max(0, representacion.length() - 2));
			return representacion;
		}

	}
}
