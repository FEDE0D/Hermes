package com.hermes.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.hermes.dao.sqlite.EtiquetaDAO;
import com.hermes.dao.sqlite.NotificacionDAO;
import com.hermes.model.Etiqueta;
import com.hermes.model.Notificacion;
import com.hermes.views.DialogEtiquetas;
import com.hermes.views.DialogEtiquetasNotificacion;
import com.hermes.views.MainView;

/**
 * 
 * @author federico
 * <p>Se encarga de generar todas las vistas de la aplicación y de asociarlas con sus modelos (?)</p>
 * <p>Además recibe objetos de tipo {@link Notificacion} los guarda y actualiza las vistas.</p>
 */
public class ViewManager {

	private static ViewManager _instance;
	
	private ViewManager(){
		
	}
	
	public static ViewManager getInstance() {
		if (_instance == null)
			_instance = new ViewManager();
		return _instance;
	}
	
	public void startMainView(){
		MainView window = new MainView();
		
		window.getListEtiquetas().setListData(new EtiquetaDAO().getAll().toArray());
		window.getTableNotificaciones().setModel(new TableNotificacionesModel());
		
		window.showView();
	}
	
	public void startDialogEtiquetas(){
		DialogEtiquetas de = new DialogEtiquetas();
		
		de.getListEtiquetas().setListData(new EtiquetaDAO().getAll().toArray());
		
		de.setLocationRelativeTo(null);
		de.setVisible(true);
	}
	
	public void startDialogEtiquetasNotificacion(){
		DialogEtiquetasNotificacion den = new DialogEtiquetasNotificacion();
		den.setLocationRelativeTo(null);
		den.setVisible(true);
	}
	
	
	/**
	 * @param n Notificacion 
	 */
	public void showNotification(Notificacion n){
		// TODO guardar notification
		System.out.println(n);
		update();
	}
	
	/**
	 * Refresca la vista del monitor.
	 * <br>
	 * Se vuelve a cargar desde la bbdd los datos y se pide a los componentes que se dibujen.
	 */
	public void update(){
		
	}
	
	private class TableNotificacionesModel extends DefaultTableModel{
		
		public TableNotificacionesModel(){
			List<Notificacion> notificaciones = new NotificacionDAO().getAll();
			Object[] columns = new String[] {"Fecha/Hora", "Niño", "Contenido", "Contexto", "Etiquetas"};
			Object[][] data = new Object[notificaciones.size()][5];
			for (int i= 0; i < notificaciones.size(); i++){
				data[i][0] = notificaciones.get(i).getDate();
				data[i][1] = notificaciones.get(i).getIdPaciente();
				data[i][2] = notificaciones.get(i).getIdContenido();
				data[i][3] = notificaciones.get(i).getIdContexto();
				data[i][4] = "...";
			}
			this.setDataVector(data, columns);
		}
		
	}
	
}
