package com.hermes.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		
		System.out.println("ACORDARSE DE CAMBIAR ESTO!");
//		if (!dbfile.exists()){
			FileUtils.copy("database/bbdd.db", getDatabaseFilePath());
//		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		Hermes.initHermes();
		viewManager = ViewManager.getInstance();
		viewManager.startMainView();
		
		
		// SIMULAR LLEGADA DE NOTIFICACIONES
		MessageReader reader = new MessageReader();
		
		
		FileReader freader1 = new FileReader("res/messages-test/hermes-message_01.json");
		FileReader freader2 = new FileReader("res/messages-test/hermes-message_02.json");
		FileReader freader3 = new FileReader("res/messages-test/hermes-message_03.json");
		
		reader.read(freader1);
		reader.read(freader2);
		reader.read(freader3);
		
		freader1.close();
		freader2.close();
		freader3.close();
	}

}
