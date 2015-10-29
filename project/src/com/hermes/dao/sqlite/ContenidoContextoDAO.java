package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.IContenidoContextoDAO;
import com.hermes.model.ContenidoContexto;

public class ContenidoContextoDAO  extends GenericDAO<ContenidoContexto> implements IContenidoContextoDAO{

	private Conexion conexion;
	public ContenidoContextoDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public ContenidoContexto createInstance(ResultSet resultado) {
		ContenidoContexto p=null;
		try {
			p = new ContenidoContexto(resultado.getInt("idContenido"),resultado.getInt("idContexto") );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "ContenidoContexto";
	}

	@Override
	public void guardar(ContenidoContexto contenidoContexto) {
		String sql= "INSERT INTO ContenidoContexto (IDCONTENIDO,IDCONTEXTO) VALUES ( " + "'" + contenidoContexto.getIdContenido() + "' ," 
				  + "'" + contenidoContexto.getIdContexto() + "'  ) ";
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
