package com.hermes.model;

public class Contenido {
	private int id, idEnTablet, idTablet, idCategoria;
	private String descripcion, img, sonido;

	public Contenido(int id, int idEnTablet, int idTablet, int idCategoria,
			String descripcion, String img, String sonido) {
		super();
		this.id = id;
		this.idEnTablet = idEnTablet;
		this.idTablet = idTablet;
		this.idCategoria = idCategoria;
		this.descripcion = descripcion;
		this.img = img;
		this.sonido = sonido;
	}

	public Contenido() {
	}

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

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getSonido() {
		return sonido;
	}

	public void setSonido(String sonido) {
		this.sonido = sonido;
	}

	public String toString(){
		return descripcion;
	}
	
}
