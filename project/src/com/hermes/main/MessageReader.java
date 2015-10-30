package com.hermes.main;

import java.io.IOException;
import java.io.Reader;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hermes.model.Notificacion;

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
					
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void readNotification(JSONObject root){
		JSONObject data = (JSONObject) root.get("data");
		
		Notificacion n = new Notificacion();
		n.setTimestamp(Timestamp.valueOf((String) root.get("timestamp")));
		n.setIdTablet((String) root.get("idTablet"));
		n.setIdPaciente((Long) data.get("idPaciente"));
		n.setIdContenido((Long) data.get("idContenido"));
		n.setIdContexto((Long) data.get("idContexto"));
		
		// TODO guardar la nueva notificacion
		
		ViewManager.getInstance().showNotification(n);
		
	}
	
	private void readABMContenido(JSONObject root){
		
	}
}
