package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.IPacienteDAO;
import com.hermes.model.Paciente;


public class PacienteDAO extends GenericDAO<Paciente> implements IPacienteDAO{
	private Conexion conexion;
	public PacienteDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	public Paciente createInstance(ResultSet resultado){
		Paciente p=null;
		try {
			p = new Paciente(resultado.getInt("id"),resultado.getString("idDevice"),resultado.getString("nombre"),resultado.getString("apellido"),  resultado.getString("sexo").charAt(0) );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	public String getNameClass(){
		return "Paciente";
	}
	
	public Paciente getById(Long id, String idDevice){
		Paciente p = null;
		
		String sql = "SELECT * FROM PACIENTE WHERE id="+id+" AND idDevice='"+idDevice+"'";
		
		try {
			Statement query = this.conexion.getEnlace().createStatement();
			ResultSet result= query.executeQuery(sql);
			while (result.next()){
				p = this.createInstance(result);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public void guardar(Paciente paciente){
		String sql= "INSERT INTO PACIENTE (ID,IDDEVICE,NOMBRE,APELLIDO,SEXO) VALUES ( "
				  								  + "'" + paciente.getId() + "' ,"
				  								  + "'" + paciente.getIdDevice() + "' ,"
												  + "'" + paciente.getNombre() + "' ," 
												  + "'" + paciente.getApellido() + "' ," 
												  + "'" + paciente.getSexo() + "' ) ";
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			consulta.executeUpdate(sql);
			consulta.close();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void actualizar(Paciente paciente){
		String sql= "UPDATE PACIENTE SET NOMBRE= '" + paciente.getNombre() + 
				"', APELLIDO ='" + paciente.getApellido() + 
				"', SEXO= '"+ paciente.getSexo() +
				"' WHERE id= " + paciente.getId() + " AND idDevice = '"+paciente.getIdDevice()+"'";
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			consulta.executeUpdate(sql);
			consulta.close();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}
