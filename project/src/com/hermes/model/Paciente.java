package com.hermes.model;

public class Paciente {
	private int id;
	private String idDevice;
	private String nombre;
	private String apellido;
	private char sexo;

	public Paciente(int id, String idDevice, String nombre, String apellido, char sexo) {
		super();
		this.id = id;
		this.idDevice = idDevice;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
	}

	public Paciente() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(String idDevice) {
		this.idDevice = idDevice;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	
	public String toString(){
		return nombre+" "+apellido+" "+sexo;
	}
	
}
