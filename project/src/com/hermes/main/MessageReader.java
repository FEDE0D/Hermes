package com.hermes.main;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hermes.dao.sqlite.PacienteDAO;
import com.hermes.model.Notificacion;
import com.hermes.model.Paciente;

enum TYPE {NOTIFICATION, ABM_CONTENIDO, ABM_CATEGORIA, ABM_CONTEXTO}
enum ABM_TYPE {ALTA, BAJA, MODIFICACION}

/**
 * 
 * @author federico
 *
 *	<p>Recibe y procesa mensajes desde la red.</p>
 *	<p>NOTA: se espera que se llame al metodo read() utilizando un objeto {@link Reader}, pero se puede adaptar para otro tipo de entradas (net socket?) </p>
 *	<p>Los mensajes pueden ser de tipo {@literal NOTIFICATION} o de ABM de contenido, categoria o contexto.</p>
 *	<p>Los mensajes de tipo {@literal NOTIFICATION} se convierten a objetos de tipos {@link Notificacion} se almacenan y luego se envían al modulo {@link ViewManager } para poder mostrarlos en el monitor.</p>
 */
public class MessageReader {

	public void read(Reader reader){
		JSONParser jparser = new JSONParser();
		try {
			JSONObject root = (JSONObject) jparser.parse(reader);
			if (root.containsKey("type")){
				TYPE type = TYPE.valueOf((String) root.get("type"));
				
				switch (type) {
				case NOTIFICATION:
					readNotification(root);
					break;
				case ABM_CONTENIDO:
					readABMContenido(root);
					break;
				default:
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void readNotification(JSONObject root){
		JSONObject data = (JSONObject) root.get("data");
		
		Notificacion n = new Notificacion();
		n.setDate(Date.valueOf((String) root.get("date")));
		n.setTime(Time.valueOf((String) root.get("time")));
		n.setIdDevice((String) root.get("idDevice"));
		n.setIdPaciente((Long) data.get("idPaciente"));
		n.setIdContenido((Long) data.get("idPictograma"));
		n.setIdContexto((Long) data.get("idContexto"));
		
		// TODO si no existe el alumno con id/idDevice de la notificación, guardarlo
		Paciente paciente = new PacienteDAO().getById(n.getIdPaciente(), n.getIdDevice());
		if (paciente == null) {
			paciente = new Paciente(n.getIdPaciente().intValue(),
					n.getIdDevice(), 
					(String) data.get("nombre"), 
					(String) data.get("apellido"),
					((String) data.get("sexo")).charAt(0));
			new PacienteDAO().guardar(paciente);
		}
		
		ViewManager.getInstance().showNotification(n);
		
	}
	
	private void readABMContenido(JSONObject root){
		
	}
}
