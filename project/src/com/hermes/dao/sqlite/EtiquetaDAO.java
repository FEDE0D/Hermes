package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.IEtiquetaDAO;
import com.hermes.model.Etiqueta;

public class EtiquetaDAO extends GenericDAO<Etiqueta> implements IEtiquetaDAO{
	private Conexion conexion;
	public EtiquetaDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public Etiqueta createInstance(ResultSet resultado) {
		Etiqueta p=null;
		try {
			p = new Etiqueta(resultado.getInt("id"),resultado.getString("descripcion"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "Etiqueta";
	}

	@Override
	public void guardar(Etiqueta etiqueta) {
		String sql= "INSERT INTO ETIQUETA (DESCRIPCION) VALUES ( " + "'" + etiqueta.getDescripcion() + "' ) ";
			try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			consulta.executeUpdate(sql);
			consulta.close();
			}
			catch (SQLException e1) {
			e1.printStackTrace();
			}
	}
	@Override
	public void actualizar(Etiqueta etiqueta) {
		String sql= "UPDATE ETIQUETA SET descripcion= '" + etiqueta.getDescripcion() + 
				"' WHERE id= " + etiqueta.getId();
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
