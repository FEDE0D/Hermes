package com.hermes.model;

public class Categoria {

	private int id, idEnTablet, idTablet;
	private String descripcion;
	
	public Categoria(int id, int idEnTablet, int idTablet, String descripcion) {
		super();
		this.id = id;
		this.idEnTablet = idEnTablet;
		this.idTablet = idTablet;
		this.descripcion = descripcion;
	}
	public Categoria(){}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdEnTablet() {
		return idEnTablet;
	}
	public void setIdEnTablet(int idEnTablet) {
		this.idEnTablet = idEnTablet;
	}
	public int getIdTablet() {
		return idTablet;
	}
	public void setIdTablet(int idTablet) {
		this.idTablet = idTablet;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
