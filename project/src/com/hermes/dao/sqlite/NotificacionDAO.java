package com.hermes.dao.sqlite;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.sqlite.util.StringUtils;

import com.hermes.dao.Conexion;
import com.hermes.dao.INotificacionDAO;
import com.hermes.model.Notificacion;
import com.sun.xml.internal.fastinfoset.util.StringArray;

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
			p = new Notificacion(
					resultado.getLong("id"),
					resultado.getLong("idDevice"),
					resultado.getLong("idContenido"),
					resultado.getLong("idContexto"),
					resultado.getLong("idPaciente"),
					Date.valueOf(resultado.getString("date")),
					Time.valueOf(resultado.getString("time")),
					Date.valueOf(resultado.getString("dateReceived")),
					Time.valueOf(resultado.getString("timeReceived")),
					resultado.getBoolean("visto")
				);
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
				+ "" + "date(current_timestamp, 'localtime')" + " ," 
				+ "" + "time(current_timestamp, 'localtime')" + " ," 
				+ "'" + notificacion.isVisto() + "' ) ";
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			consulta.executeUpdate(sql);
			consulta.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Override
	public void actualizar(Notificacion notificacion) {
		String sql= "UPDATE NOTIFICACION SET "+
				"IDDEVICE= '" + notificacion.getIdDevice() + 
				"', IDCONTENIDO ='" + notificacion.getIdContenido() + 
				"', IDCONTEXTO= '"+ notificacion.getIdContexto() +
				"', IDPACIENTE= '"+ notificacion.getIdPaciente() +
				"', DATE= '"+ notificacion.getDate() +
				"', TIME= '"+ notificacion.getTime() +
				"', DATERECEIVED= '"+ notificacion.getDateReceived() +
				"', TIMERECEIVED= '"+ notificacion.getTimeReceived() +
				"', VISTO= '"+ notificacion.isVisto() +
				"' WHERE id= " + notificacion.getId();
		try {
			Statement consulta = this.conexion.getEnlace().createStatement();
			consulta.executeUpdate(sql);
			consulta.close();
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
//	public List<Notificacion> filtrar(int idContenido, int idContexto, int idCategoria, int idPaciente, List idsEtiquetas){
//		String contenido="idContenido", contexto= "idContexto",categoria="idCategoria",paciente="idPaciente";
//		contenido+= (idContenido== 0) ? " > 0" : "= "+idContenido;
//		contexto+= (idContexto== 0) ? " > 0" : "= "+idContexto;
//		categoria+= (idCategoria== 0) ? " > 0" : "= "+idCategoria;
//		paciente+= (idPaciente== 0) ? " > 0" : "= "+idPaciente;
//		
//		String consultaEtiquetas="";
//		for (int i=0; i<idsEtiquetas.size(); i++) {
//			if(i==0) consultaEtiquetas+="(";
//			consultaEtiquetas+=" E.descripcion='"+idsEtiquetas.get(i)+"'";
//			if (i!=idsEtiquetas.size()-1)consultaEtiquetas+=" OR ";
//			else consultaEtiquetas+=") AND ";
//			//System.out.println(idsEtiquetas.get(i));
//		}
////		System.out.println(consultaEtiquetas);
//		String sql= "select  N.id,  N.idDevice, N.idContenido, N.idContexto, N.idPaciente, date,time, dateReceived, timeReceived, visto"
//				+ " from Notificacion as N "
//									 + "LEFT join Contenido as C on(N.idContenido = C.id)"
//									 + "LEFT join NotificacionEtiqueta as NE on(NE.idNotificacion = N.id )"
//									 + "LEFT join Etiqueta as E on(E.id = NE.idEtiqueta )"
//				+ "where "+consultaEtiquetas + contenido + " AND "+ contexto + " AND "+ categoria + " AND "+ paciente;
//		System.out.println(sql);
//		try {
//			Statement consulta = this.conexion.getEnlace().createStatement();
//			ResultSet resultado= consulta.executeQuery(sql);
//			List<Notificacion> listado  = new ArrayList<Notificacion>();
//			while(resultado.next()){
//				listado.add(this.createInstance(resultado));
//			}
//			resultado.close();
//			return listado;
//		}
//		catch (SQLException e1) {
//			e1.printStackTrace();
//		}
//		return null;
//		
//	}
	
	public List<Notificacion> filtrar(long idContenido, long idContexto, long idCategoria, long idPaciente, List<Long> idsEtiquetas, String dateFrom, String dateTo, String timeFrom, String timeTo) {
		List<Notificacion> listado = new ArrayList<Notificacion>();
		
		String sql = "SELECT  N.* "
				+ "FROM notificacion AS N "
				+ "INNER JOIN contenido AS C ON (N.idContenido = C.id) "
				+ "WHERE	idContenido" + 		((idContenido == 0)?" > 0":(" = "+idContenido))
				+ "			AND idContexto" +	((idContexto == 0)? " > 0":(" = "+idContexto))
				+ "			AND idCategoria" +	((idCategoria == 0)?" > 0":(" = "+idCategoria))
				+ "			AND idPaciente" +	((idPaciente == 0)? " > 0":(" = "+idPaciente));
		
		if (dateFrom != null && dateTo != null && !dateFrom.trim().isEmpty() && !dateTo.trim().isEmpty())
			sql += "		AND dateReceived BETWEEN \""+ dateFrom + "\" AND \""+ dateTo +"\" ";
		if (timeFrom != null && timeTo != null && !timeFrom.trim().isEmpty() && !timeTo.trim().isEmpty())
			sql += "		AND timeReceived BETWEEN \""+ timeFrom + "\" AND \""+ timeTo +"\" ";
		
		if (!idsEtiquetas.isEmpty()){
			String etiquetasOR = "";
			for (Long id : idsEtiquetas) etiquetasOR += "NE.idEtiqueta = "+ id +" OR ";
			etiquetasOR = etiquetasOR.substring(0, etiquetasOR.lastIndexOf(" OR "));
			
			sql += "		AND N.id IN ( "
				+ "				SELECT NE.idNotificacion "
				+ "				FROM notificacionetiqueta AS NE "
				+ "				WHERE	NE.idNotificacion = N.id "
				+ "						AND("
				+ 							etiquetasOR
				+ "						)"
				+ "			) ";
		}
		
		System.out.println(sql);
		
		try {
			Statement query = this.conexion.getEnlace().createStatement();
			ResultSet result = query.executeQuery(sql);
			while(result.next()){
				listado.add(this.createInstance(result));
			}
			result.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listado;
	}
	
}
