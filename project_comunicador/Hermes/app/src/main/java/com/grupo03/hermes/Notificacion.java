package com.grupo03.hermes;

import android.database.Cursor;
import android.util.Log;

import com.grupo03.hermes.db.Database;

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
        // database.addPendiente(a, b, c, d, e, f);

        sendPendientes();
    }
    private static void sendPendientes(){
        // TODO recover from database all the notificacions that are marked as !sent.
        // TODO make the JSON array object.
        /*
            cursor = database.getPendientes();
            for elementos del cursor {
                JSON object = new JsonObject();
                object.put("id", cursor.getString(cursor.getColumnIndex("id"));
                object.put("nombre", cursor.getString(cursor.getColumnIndex("nombre"));
                object.put("apellido", cursor.getString(cursor.getColumnIndex("apellido"));
                object.put("sexo", cursor.getString(cursor.getColumnIndex("sexo"));
                object.put("idPictograma", cursor.getString(cursor.getColumnIndex("idPictograma"));
                object.put("idContexto", cursor.getString(cursor.getColumnIndex("idContexto"));
                object.put("fecha", cursor.getString(cursor.getColumnIndex("fecha"));
                object.put("hora", cursor.getString(cursor.getColumnIndex("hora"));

                jsonarray.add(object);
            }
         */

        String json_message = "{\"notifications\":[{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":3,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":2},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":3,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":1},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":3,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":3,\"idContenido\":2,\"idContexto\":2},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":2},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":3,\"idContenido\":1,\"idContexto\":2},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":3,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":2,\"idContexto\":3},\"idTablet\":\"3232322\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":2,\"idContenido\":1,\"idContexto\":3},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"},{\"date\":\"1969-12-31\",\"data\":{\"idPaciente\":1,\"idContenido\":1,\"idContexto\":1},\"idTablet\":\"130230212\",\"time\":\"21:00:00\",\"type\":\"NOTIFICATION\"}]}";

        // TODO send the message to the monitor
        Sender sender = new Sender(json_message);
        new Thread(sender).start();
        if (sender.isSent()){
        // TODO mark the notifications as 'sent'.
        //    cursor.reset();
        //    for elementos del cursor {
        //        database.markAsSent(cursor.getString(cursor.getColumnIndex("id"));
        //    }
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
            Log.i("HERMES", "MONITOR IP: "+getMonitorURL());
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
                        Log.i("HERMES", "SENT: " + message);
                        Log.i("HERMES", "RESPONSE CODE: "+conn.getResponseCode());
                    }

                }
            } catch (IOException e) {
                Log.e("HERMES", "Error al abrir conexión con monitor!");
                e.printStackTrace();
            }

        }
    }

}
