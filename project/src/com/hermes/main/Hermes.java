package com.hermes.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.json.simple.parser.JSONParser;

import com.hermes.model.Notificacion;
import com.hermes.utils.FileUtils;

/**
 * @author federico
 *
 */
public class Hermes {
	
	private static String OS;
	private static String workingDirectory;
	
	private static final String appFolderName = ".hermes";
	private static final String databaseFileName = "database.db";
	private static final String localDatabaseFilePath = "database/bbdd.db";
	
	private static ViewManager viewManager;
	private static MessageReader messageReader;
	private static Server server;
	
	
	public static String getAppFolderPath(){
		return workingDirectory+"/"+appFolderName;
	}
	
	public static String getDatabaseFilePath(){
		return workingDirectory+"/"+appFolderName+"/"+databaseFileName;
	}
	
	public static String getLocalDatabaseFilePath(){
		return localDatabaseFilePath;
	}
	
	public static ViewManager getViewManager(){
		return viewManager;
	}
	
	public static MessageReader getMessageReader(){
		return messageReader;
	}
	
	public static Server getServer(){
		return server;
	}
	
	private static void initHermes() {
		OS = (System.getProperty("os.name")).toUpperCase();
		if (OS.contains("WIN"))
			workingDirectory = System.getenv("AppData");
		else
			workingDirectory = System.getProperty("user.home");
		
		// Crear la carpeta de la aplicacion, si no existe
		File appFolder = new File(getAppFolderPath());
		appFolder.mkdirs();
		
		// Copiar el archivo de base de datos inicial, si no existe
		File dbfile = new java.io.File(getDatabaseFilePath());
		if (!dbfile.exists()){
			FileUtils.copy("database/database.db", getDatabaseFilePath());
		}
		
		Properties p = new Properties();
		String serverContextName = "";
		int serverListenPort = -1;
		try {
			p.load(Hermes.class.getResourceAsStream("/config/conf.properties"));
			serverListenPort = Integer.valueOf(p.getProperty("port"));
			
			if (serverListenPort <= 0)
				throw new Exception("Server Listen Port is missing or is wrong!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		viewManager = ViewManager.getInstance();
		messageReader = new MessageReader();
		new Thread(new ServerRunnable(serverContextName, serverListenPort)).start();
		
	}
	
	private static class ServerRunnable implements Runnable {

		private String context;
		private int port;
		
		public ServerRunnable(String context, int port) {
			this.context = context;
			this.port = port;
		}
		
		@Override
		public void run() {
			server = new Server(context, port);
			server.start();
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		Hermes.initHermes();
		viewManager.startMainView();
		
	}

}
