package com.hermes.dao;

import java.sql.ResultSet;

import com.hermes.model.Paciente;

public interface IPacienteDAO {
	
	public Paciente createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Paciente paciente);
	public void actualizar(Paciente paciente);
}
