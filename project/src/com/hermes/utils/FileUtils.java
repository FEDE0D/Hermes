package com.hermes.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.hermes.main.Hermes;

public class FileUtils {

	/**
	 * Copia un recurso dentro del jar a un archivo en disco
	 * @param resource recurso que se quiere copiar
	 * @param destination filepath a donde copiar el archivo
	 */
	public static void copy(String resource, String destination) {
		InputStream resStreamIn = Hermes.class.getClassLoader().getResourceAsStream(resource);
		OutputStream resStreamOut = null;
		File resDestFile = new File(destination);
		
		try {
			resStreamOut = new FileOutputStream(resDestFile);
			int readBytes;
			byte[] buffer = new byte[4096];
			while ((readBytes = resStreamIn.read(buffer)) > 0) {
				resStreamOut.write(buffer, 0, readBytes);
			}
			resStreamOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
