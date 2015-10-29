package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hermes.dao.Conexion;
import com.hermes.dao.IGenericDAO;

public class GenericDAO<T> implements IGenericDAO<T>{
	
	private Conexion conexion;
	public GenericDAO(){
		this.conexion = Conexion.getConexion();
	}
	public  T createInstance(ResultSet nuevo){
		return null;
	}
	public String getNameClass(){
		return "GenericDao";
	}
	public List<T> getAll(){
		String sql= "select * from "+ this.getNameClass();
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			ResultSet resultado= consulta.executeQuery(sql);
			List<T> listado  = new ArrayList<T>();
			while(resultado.next()){
				listado.add(this.createInstance(resultado));
			}
			resultado.close();
			return listado;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public T getById(int id){
		String sql= "select * from "+ this.getNameClass() + " where id = "+ id + " limit 1";
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			ResultSet resultado= consulta.executeQuery(sql);
			T obj= null;
			while(resultado.next()){
				obj= (this.createInstance(resultado));
			}
			resultado.close();
			return obj;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public boolean deteleById(int id){
		String sql= "DELETE from "+ this.getNameClass() + " where id = "+ id;
		boolean eliminado= false;
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			int res= consulta.executeUpdate(sql);
			if (res== 1) eliminado= true;
			consulta.close();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return eliminado;
	}
	
	/**
	 * SOLO LO PUEDEN USAR objetos Contexto/Contenido/Categoria.
	 * Esto sirve para identificar si existe un objeto personalizado por un paciente dentro del monitor
	 *
	 * @param  idTablet es el ID de la tablet de un paciente.
	 * @param  idEnTablet es el ID con que se guardo un Contexto/Contenido/Categoria dentro de una tablet
	 * @return   Retorna objeto un tipo Contexto/Contenido/Categoria 
	 */
	public T getByIdEnTabletANDIdTablet(int idTablet, int idEnTablet){
		String sql= "select * from "+ this.getNameClass() + " where idEnTablet = "+ idEnTablet + " AND idTablet= "+idTablet+" limit 1";
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			ResultSet resultado= consulta.executeQuery(sql);
			T obj= null;
			while(resultado.next()){
				obj= (this.createInstance(resultado));
			}
			resultado.close();
			return obj;
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}
