package com.hermes.model;

public class Etiqueta {
	private int id;
	private String descripcion;
	
	public Etiqueta(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}
	public Etiqueta(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
