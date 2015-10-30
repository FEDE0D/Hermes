package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.INotificacionEtiquetaDAO;
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
			p = new NotificacionEtiqueta(resultado.getInt("idNotificacion"),resultado.getInt("idEtiqueta") );
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
}
