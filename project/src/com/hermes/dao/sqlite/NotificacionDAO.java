package com.hermes.dao.sqlite;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hermes.dao.Conexion;
import com.hermes.dao.INotificacionDAO;
import com.hermes.model.Etiqueta;
import com.hermes.model.Notificacion;

public class NotificacionDAO extends GenericDAO<Notificacion> implements INotificacionDAO {
	private Conexion conexion;
	public NotificacionDAO(){
		super();
		this.conexion = Conexion.getConexion();
	}
	@Override
	public Notificacion createInstance(ResultSet resultado) {
		Notificacion p=null;
		try {
			p = new Notificacion(resultado.getLong("id"),resultado.getLong("idDevice"),resultado.getLong("idContenido"),resultado.getLong("idContexto"),resultado.getLong("idPaciente"),
							resultado.getDate("date"),resultado.getTime("time"),resultado.getDate("dateReceived"),resultado.getTime("timeReceived"), resultado.getBoolean("visto"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	@Override
	public String getNameClass() {
		return "Notificacion";
	}

	@Override
	public void guardar(Notificacion notificacion) {
		String sql= "INSERT INTO NOTIFICACION (IDDEVICE, IDCONTENIDO,IDCONTEXTO,IDPACIENTE,DATE,TIME,DATERECEIVED,TIMERECEIVED,VISTO) VALUES ( " 
				+ "'" + notificacion.getIdDevice() + "' ," 
				+ "'" + notificacion.getIdContenido() + "' ,"
				+ "'" + notificacion.getIdContexto() + "' ," 
				+ "'" + notificacion.getIdPaciente() + "' ," 
				+ "'" + notificacion.getDate() + "' ," 
				+ "'" + notificacion.getTime() + "' ," 
				+ "'" + notificacion.getDateReceived() + "' ," 
				+ "'" + notificacion.getTimeReceived() + "' ," 
				+ "'" + notificacion.isVisto() + "' ) ";
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
	public void actualizar(Notificacion notificacion) {
		String sql= "UPDATE NOTIFICACION SET "+
				"IDDEVICE= " + notificacion.getIdDevice() + 
				" IDCONTENIDO =" + notificacion.getIdContenido() + 
				" IDCONTEXTO= "+ notificacion.getIdContexto() +
				" IDPACIENTE= "+ notificacion.getIdPaciente() +
				" DATE= "+ notificacion.getDate() +
				" TIME= "+ notificacion.getTime() +
				" DATERECEIVED= "+ notificacion.getDateReceived() +
				" TIMERECEIVED= "+ notificacion.getTimeReceived() +
				" VISTO= "+ notificacion.isVisto() +
				"WHERE id= " + notificacion.getId();
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
