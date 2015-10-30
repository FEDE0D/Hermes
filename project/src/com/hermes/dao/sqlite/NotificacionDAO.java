package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hermes.dao.Conexion;
import com.hermes.dao.INotificacionDAO;
import com.hermes.model.Notificacion;

/**
 * @author federico
 *
 *	TODO rehacer!
 */
@Deprecated
public class NotificacionDAO implements INotificacionDAO {

	private Conexion conexion;
	
	public NotificacionDAO() {
		this.conexion = Conexion.getConexion();
	}
	
	@Override
	public String getNameClass() {
		return null;
	}
	
	/* 
	 * TODO BORRAR!
	 */
	@Override @Deprecated
	public List<Notificacion> getAll() {
		String sql = "SELECT * FROM notificacion";
		Statement query = null;
		ResultSet result = null;
		List<Notificacion> lista = new ArrayList<Notificacion>();
		try {
			query = conexion.getEnlace().createStatement();
			result = query.executeQuery(sql);
			while (result.next()){
				Notificacion n = new Notificacion();
				n.setId(result.getLong("id"));
				n.setIdTablet(result.getString("idTablet"));
				n.setIdContenido(result.getLong("idContenido"));
				n.setIdContexto(result.getLong("idContexto"));
				n.setIdPaciente(result.getLong("idPaciente"));
				n.setTimestamp(Timestamp.valueOf(result.getString("timestamp")));
				lista.add(n);
			}
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public Notificacion getById(int id) {
		return null;
	}

	@Override
	public boolean deteleById(int id) {
		return false;
	}

}
