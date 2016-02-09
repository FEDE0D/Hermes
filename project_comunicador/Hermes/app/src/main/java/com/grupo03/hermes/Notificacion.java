package com.grupo03.hermes;

import android.database.Cursor;
import android.util.Log;

import com.grupo03.hermes.db.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by federico on 08/02/16.
 */
public class Notificacion {

    private static String IP_ADRESS_MONITOR = "";
    private static String PORT_MONITOR = "";

    public static void enviar(final Pictograma pictograma) {
        // TODO get data for the pictograma from database
        String nombre = "Juanito";
        String apellido = "Arcoiris";
        String sexo = "M";
        int idPictograma = 1;
        int idContexto = 1;
        Date date = new Date(0L);
        Time time = new Time(0L);

        // TODO store in database
        Database db = new Database(MainActivity.applicationContext);
        db.addPendiente(nombre, apellido, sexo,idPictograma+"", idContexto+"", date.toString(), time.toString());

        sendPendientes();
    }
    private static void sendPendientes(){
        // recover from database all the notificacions that are marked as !sent.
        // make the JSON array object.

        String message = "";
        Cursor cursor = new Database(MainActivity.applicationContext).getPendientes();
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray notifications = new JSONArray();
            while (!cursor.isAfterLast()){
                JSONObject row = new JSONObject();

                row.put("date", cursor.getString(cursor.getColumnIndex("fecha")));
                row.put("time", cursor.getString(cursor.getColumnIndex("hora")));
                row.put("idTablet", "ASD-123");
                row.put("type", "NOTIFICATION");
                row.put("date", cursor.getString(cursor.getColumnIndex("fecha")));

                JSONObject data = new JSONObject();
                data.put("idPaciente", 1);
                data.put("idContenido", 1);
                data.put("idContexto", 1);

                row.put("data", data);
                notifications.put(row);

                Log.i("HERMES", "Pendiente: " + cursor.getString(cursor.getColumnIndex("id")) + cursor.getString(cursor.getColumnIndex("nombreAlumno")));
                cursor.moveToNext();
            }
            jsonObject.put("notifications", notifications);
            message = jsonObject.toString();
        } catch (JSONException e) { e.printStackTrace(); }

        // send the message to the monitor
        Sender sender = new Sender(message);
        Thread senderThread = new Thread(sender);
        senderThread.start();
        while(senderThread.isAlive()){ }

        if (sender.isSent()){
            // mark the notifications as 'sent'.
            cursor.moveToFirst();
            Database dbUpdate = new Database(MainActivity.applicationContext);
            while (!cursor.isAfterLast()) {
                dbUpdate.setPendienteAsSent(cursor.getString(cursor.getColumnIndex("id")));
                cursor.moveToNext();
            }
        }

    }

    private static class Sender implements Runnable {

        private String message;
        private boolean sent = false;

        public Sender(String message){
            this.message = message;
        }

        public boolean isSent(){
            return sent;
        }

        private String getMonitorURL(){
            Database database = new Database(MainActivity.applicationContext);
            Cursor cursor = database.getConfiguracion();
            while (!cursor.isAfterLast()) {
                if (cursor.getString(cursor.getColumnIndex("clave")).equals("ipMonitor")) {
                    IP_ADRESS_MONITOR = cursor.getString(cursor.getColumnIndex("valor"));
                } else if (cursor.getString(cursor.getColumnIndex("clave")).equals("puertoMonitor")) {
                    PORT_MONITOR = cursor.getString(cursor.getColumnIndex("valor"));
                }
                cursor.moveToNext();
            }
            return "http://"+IP_ADRESS_MONITOR+":"+PORT_MONITOR+"/";
        }

        @Override
        public void run() {
            Log.i("HERMES", "MONITOR IP: " + getMonitorURL());
            URL obj = null; try { obj = new URL(getMonitorURL()); } catch (MalformedURLException e) { e.printStackTrace(); }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) obj.openConnection();
                if (conn != null){
                    conn.setReadTimeout(30000);
                    conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                    conn.addRequestProperty("User-Agent", "Mozilla");
                    conn.addRequestProperty("Referer", "hermes.com");
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    if (os != null){
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                        bw.write(message);
                        bw.flush();
                        bw.close();
                        os.close();
                    }

                    if (conn.getResponseCode() > 0){
                        sent = true;
                    }

                    Log.i("HERMES", "SENT: " + message);
                    Log.i("HERMES", "RESPONSE CODE: "+conn.getResponseCode());

                }
            } catch (IOException e) {
                Log.e("HERMES", "Error al abrir conexión con monitor!");
                e.printStackTrace();
            }

        }
    }

}
