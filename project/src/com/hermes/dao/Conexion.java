package com.hermes.dao;

import java.sql.*;
import com.hermes.main.Hermes;

public class Conexion {

	private static Conexion conexion;
	private Connection enlace;

	public static Conexion getConexion() {
		if (conexion == null)
			conexion = new Conexion();
		return conexion;
	}

	public Connection getEnlace() {
		return this.enlace;
	}

	private Conexion() {
		try {
			Class.forName("org.sqlite.JDBC");
			this.enlace = DriverManager.getConnection("jdbc:sqlite:"+Hermes.getDatabaseFilePath()); 
		} catch (ClassNotFoundException e) {
			System.out.println("no se encontr� el driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("no se pudo conectar a la BD:");
			e.printStackTrace();
		}
	}

	public void CerrarConexion() {
		try {
			this.enlace.close();
			conexion = null;
		} catch (SQLException e) {
			System.out.println("no se pudo cerrar la BD");
			e.printStackTrace();
		}
	}

	public void ejecutarUpdate(String sql) {
		try {
			Statement consulta = this.enlace.createStatement();
			consulta.executeUpdate(sql);
			consulta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet ejecutarQuery(String sql) {
		try {
			Statement consulta = this.enlace.createStatement();
			ResultSet resultado = consulta.executeQuery(sql);
			return resultado;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
