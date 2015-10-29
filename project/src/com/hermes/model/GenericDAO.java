package com.hermes.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenericDAO<T> implements IGenericDAO{
	
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


}
