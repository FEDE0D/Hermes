package com.hermes.dao.sqlite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.hermes.dao.Conexion;
import com.hermes.dao.INotificacionEtiquetaDAO;
import com.hermes.model.Etiqueta;
import com.hermes.model.NotificacionEtiqueta;

public class NotificacionEtiquetaDAO  extends GenericDAO<NotificacionEtiqueta> implements INotificacionEtiquetaDAO{

	private Conexion conexion;
	public NotificacionEtiquetaDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public NotificacionEtiqueta createInstance(ResultSet resultado) {
		NotificacionEtiqueta p=null;
		try {
			p = new NotificacionEtiqueta(resultado.getLong("idNotificacion"),resultado.getLong("idEtiqueta") );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "NotificacionEtiqueta";
	}

	@Override
	public void guardar(NotificacionEtiqueta notificacionEtiqueta) {
		String sql= "INSERT INTO NotificacionEtiqueta (IDNOTIFICACION,IDETIQUETA) VALUES ( " + "'" + notificacionEtiqueta.getIdNotificacion() + "' ," 
				  + "'" + notificacionEtiqueta.getIdEtiqueta() + "'  ) ";
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
	public List<Etiqueta> getEtiquetasParaNotificacion(Long idNotificacion) {
		
		List<Etiqueta> etiquetas = new Etiqueta.ListaEtiquetas();
		
		String sql = "SELECT NE.idNotificacion, E.id, E.descripcion "
				+ "FROM notificacionetiqueta NE "
				+ "INNER JOIN etiqueta E ON (NE.idEtiqueta = E.id) "
				+ "WHERE NE.idNotificacion = ?";
		PreparedStatement query = null;
		try {
			query = this.conexion.getEnlace().prepareStatement(sql);
			query.setLong(1, idNotificacion);
			ResultSet result = query.executeQuery();
			while (result.next()){
				etiquetas.add(new EtiquetaDAO().createInstance(result));
			}
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return etiquetas;
	}
	
	/**
	 * @return objeto {@link NotificacionEtiqueta} representado por idNotificacion y idEtiqueta o null si no existe.
	 * 
	 * */
	public NotificacionEtiqueta findById(Long idNotificacion, Long idEtiqueta) {
		NotificacionEtiqueta ne = null;
		
		String sql = "SELECT * "
				+ "FROM notificacionetiqueta "
				+ "WHERE idNotificacion = ? AND idEtiqueta = ?";
		
		PreparedStatement query = null;
		try {
			query = conexion.getEnlace().prepareStatement(sql);
			query.setLong(1, idNotificacion);
			query.setLong(2, idEtiqueta);
			ResultSet result = query.executeQuery();
			ne = new NotificacionEtiquetaDAO().createInstance(result);
			query.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ne;
	}
	
	public boolean deteleById(Long idNotificacion, Long idEtiqueta){
		String sql= "DELETE "
				+ "FROM notificacionetiqueta "
				+ "WHERE idNotificacion = '"+idNotificacion+"' AND idEtiqueta = '"+idEtiqueta+"' ";
		Statement query = null;
		int result = -1;
		try {
			query = this.conexion.getEnlace().createStatement();
			result = query.executeUpdate(sql);
			query.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return result == 1;
	}
	
}
