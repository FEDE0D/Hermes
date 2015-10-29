package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.IContenidoDAO;
import com.hermes.model.Contenido;

public class ContenidoDAO  extends GenericDAO<Contenido> implements IContenidoDAO{

	private Conexion conexion;
	public ContenidoDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public Contenido createInstance(ResultSet resultado) {
		Contenido p=null;
		try {
			p = new Contenido(resultado.getInt("id"),resultado.getInt("idEnTablet"),resultado.getInt("idTablet"),  resultado.getInt("idCategoria"), resultado.getString("descripcion"), resultado.getString("img"), resultado.getString("sonido") );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "Contenido";
	}

	@Override
	public void guardar(Contenido contenido) {
		String sql= "INSERT INTO CONTENIDO (IDENTABLET,IDTABLET,DESCRIPCION,IMG,SONIDO,IDCATEGORIA) VALUES ( " 
				  + "'" + contenido.getIdEnTablet() + "' ," 
				  + "'" + contenido.getIdTablet() + "' ," 
				  + "'" + contenido.getDescripcion() + "' ," 
				  + "'" + contenido.getImg() + "' ," 
				  + "'" + contenido.getSonido() + "' ," 
				  + "'" + contenido.getIdCategoria() + "' ) ";
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
	public void actualizar(Contenido contenido) {
		String sql= "UPDATE CONTEXTO SET idEnTablet= " + contenido.getIdEnTablet() + 
				" idTablet =" + contenido.getIdTablet() + 
				" descripcion =" + contenido.getDescripcion() + 
				" img =" + contenido.getImg() + 
				" sonido =" + contenido.getSonido() + 
				" idCategoria= "+ contenido.getIdCategoria() +
				"WHERE id= " + contenido.getId();
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
