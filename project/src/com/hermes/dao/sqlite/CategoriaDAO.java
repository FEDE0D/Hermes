package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.ICategoriaDAO;
import com.hermes.model.Categoria;

public class CategoriaDAO  extends GenericDAO<Categoria> implements ICategoriaDAO{

	private Conexion conexion;
	public CategoriaDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public Categoria createInstance(ResultSet resultado) {
		Categoria p=null;
		try {
			p = new Categoria(resultado.getInt("id"),resultado.getInt("idEnTablet"),resultado.getInt("idTablet"),  resultado.getString("descripcion") );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "Categoria";
	}

	@Override
	public void guardar(Categoria categoria) {
		String sql= "INSERT INTO CATEGORIA (IDENTABLET,IDTABLET,DESCRIPCION) VALUES ( " + "'" + categoria.getIdEnTablet() + "' ," 
				  + "'" + categoria.getIdTablet() + "' ," 
				  + "'" + categoria.getDescripcion() + "' ) ";
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
	public void actualizar(Categoria categoria) {
		String sql= "UPDATE CONTEXTO SET idEnTablet= " + categoria.getIdEnTablet() + 
				" idTablet =" + categoria.getIdTablet() + 
				" descripcion= "+ categoria.getDescripcion() +
				"WHERE id= " + categoria.getId();
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
