package com.hermes.main;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class Server {
	
	private HttpServer server = null;
	private HttpContext context = null;
	
	public Server(String contextName, int port){
		
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			context = server.createContext("/"+contextName, new NotifyHandler());
			server.setExecutor(Executors.newCachedThreadPool());
		}
		
	}
	
	public void start(){
		server.start();
	}
	
	private class NotifyHandler implements HttpHandler {

		@Override
		public void handle(HttpExchange t) throws IOException {
			String requestBody = "";
			Scanner s = new Scanner(t.getRequestBody());
			s.useDelimiter("\\A");
			requestBody = s.hasNext()? s.next() : "...";
			s.close();
			
			System.out.println("REQUEST BODY: "+requestBody);
			
			int statusCode = 200;
			String responseBody = "...";
			
			// PROCESS JSON INFO
			JSONParser jparser = new JSONParser();
			try {
				JSONObject root = (JSONObject) jparser.parse(requestBody);
				JSONArray notifications = (JSONArray) root.get("notifications");
				
				JSONObject o = null; StringReader reader = null;
				for (int i = 0; i< notifications.size(); i++){
					o = (JSONObject) notifications.get(i);
					reader = new StringReader(o.toJSONString());
					
					Hermes.getMessageReader().read(reader);
				}
				
				statusCode = 200;
				responseBody = "Solicitud procesada!\n\n";
				
			} catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);
				
				statusCode = 400;
				responseBody = "Solicitud incorrecta! \n\n"+sw.toString();
				
				e.printStackTrace();
			}
			
			t.sendResponseHeaders(statusCode, responseBody.length());
			OutputStream os = t.getResponseBody();
			os.write(responseBody.getBytes());
			os.close();
			
		}
	}

}
