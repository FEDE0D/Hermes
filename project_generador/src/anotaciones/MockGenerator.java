package anotaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class MockGenerator {
	
		public static <T> List<T> createMockInstances(Class<T> notificacion, int cant){ 
			List<Notificacion> listado = new ArrayList<Notificacion>();
			for(int i = 1; i< cant; i++ ){
				Notificacion n = new Notificacion();
				 for (Field field : notificacion.getDeclaredFields()) {
						if (field.isAnnotationPresent(MockStringAttribute.class)) {
							Annotation annotation = field.getAnnotation(MockStringAttribute.class);
							MockStringAttribute test = (MockStringAttribute) annotation;
							String name= field.getName();
							String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
								try {
									int index= new Random().nextInt(test.value().length);
									notificacion.getMethod(methodName, Long.class).invoke(n, (long)test.value()[index]);
								} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
										| NoSuchMethodException | SecurityException e) {
									e.printStackTrace();
								}
						}
						else if (field.isAnnotationPresent(MockDateAttribute.class)) {
							Date date = new Date(i);
							String name= field.getName();
							String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
								try {
									notificacion.getMethod(methodName, Date.class).invoke(n, date );
								} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
										| NoSuchMethodException | SecurityException e) {
									e.printStackTrace();
								}
						}
						else if (field.isAnnotationPresent(MockTimeAttribute.class)) {
							Time time = new Time(i);
							String name= field.getName();
							String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
								try {
									notificacion.getMethod(methodName, Time.class).invoke(n, time );
								} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
										| NoSuchMethodException | SecurityException e) {
									e.printStackTrace();
								}
						}
					}
				 listado.add(n); 
				}
			return (List<T>) listado;
		}
		public static void main(String[] args) throws UnknownHostException, IOException {
			System.out.println("main");
			List<Notificacion> notificaciones = createMockInstances(Notificacion.class, 44);
			
			String message;
			JSONObject json = new JSONObject();

			JSONArray notifications = new JSONArray();
			JSONArray notification = new JSONArray();
			for (Notificacion notificacion : notificaciones) {
				JSONObject item = new JSONObject();
				item.put("date", notificacion.getDate().toString());
				item.put("time", notificacion.getTime().toString());
				item.put("idTablet", notificacion.getIdDevice().toString());
				item.put("type", "NOTIFICATION");
					JSONObject data = new JSONObject();
					data.put("idPaciente", notificacion.getIdPaciente());
					data.put("idContenido", notificacion.getIdContenido());
					data.put("idContexto", notificacion.getIdContexto());
				item.put("data", data);
				notifications.add(item);
			};
			json.put("notifications", notifications);

			message = json.toString();
			
			System.out.println(message);
			
			String url = "http://localhost:1199/hermes";
			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(30000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");
			conn.setDoOutput(true);
			
			OutputStream os = conn.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write(message);
			bw.flush();
			bw.close();
			os.close();
			
			conn.connect();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer html = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();
			
			System.out.println("Contenido URL... \n" + html.toString());
			System.out.println("Hecho");
			
			
					  
	
		}
}
