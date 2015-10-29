package com.hermes.model.Paciente;

import java.sql.ResultSet;

public interface IPacienteDAO {
	
	public Paciente createInstance(ResultSet resultado);
	public String getNameClass();
	public void guardar(Paciente paciente);
	public void actualizar(Paciente paciente);
}
