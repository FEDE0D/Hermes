package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.IContextoDAO;
import com.hermes.model.Contexto;

public class ContextoDAO  extends GenericDAO<Contexto> implements IContextoDAO{

	private Conexion conexion;
	public ContextoDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public Contexto createInstance(ResultSet resultado) {
		Contexto p=null;
		try {
			p = new Contexto(resultado.getInt("id"),resultado.getInt("idEnTablet"),resultado.getInt("idTablet"),  resultado.getString("descripcion") );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "Contexto";
	}

	@Override
	public void guardar(Contexto contexto) {
		String sql= "INSERT INTO CONTEXTO (IDENTABLET,IDTABLET,DESCRIPCION) VALUES ( " + "'" + contexto.getIdEnTablet() + "' ," 
				  + "'" + contexto.getIdTablet() + "' ," 
				  + "'" + contexto.getDescripcion() + "' ) ";
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
	public void actualizar(Contexto contexto) {
		String sql= "UPDATE CONTEXTO SET "+
				"idEnTablet= '" + contexto.getIdEnTablet() + 
				"',  idTablet= '" + contexto.getIdTablet() + 
				"', descripcion= '"+ contexto.getDescripcion() +
				"' WHERE id= " + contexto.getId();
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

