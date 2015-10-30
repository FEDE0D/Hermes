package com.hermes.main;

import java.sql.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import com.hermes.dao.sqlite.CategoriaDAO;
import com.hermes.dao.sqlite.ContenidoDAO;
import com.hermes.dao.sqlite.ContextoDAO;
import com.hermes.dao.sqlite.EtiquetaDAO;
import com.hermes.dao.sqlite.NotificacionDAO;
import com.hermes.dao.sqlite.NotificacionEtiquetaDAO;
import com.hermes.dao.sqlite.PacienteDAO;
import com.hermes.model.Categoria;
import com.hermes.model.Contenido;
import com.hermes.model.Contexto;
import com.hermes.model.Notificacion;
import com.hermes.model.Paciente;
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
	private MainView mainView;
	
	private ViewManager(){
		mainView = new MainView();
	}
	
	public static ViewManager getInstance() {
		if (_instance == null)
			_instance = new ViewManager();
		return _instance;
	}
	
	public void startMainView(){
		update();
		mainView.showView();
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
		// GUARDAR NOTIFICACION
		new NotificacionDAO().guardar(n);
		
		// UPDATE VIEW
		update();
	}
	
	/**
	 * Refresca la vista del monitor.
	 * <br>
	 * Se vuelve a cargar desde la bbdd los datos y se pide a los componentes que se dibujen.
	 */
	public void update(){
		mainView.getListEtiquetas().setListData(new EtiquetaDAO().getAll().toArray());
		mainView.getTableNotificaciones().setModel(new TableNotificacionesModel());
		mainView.getTableNotificaciones().getRowSorter().toggleSortOrder(0);
		mainView.getTableNotificaciones().getRowSorter().toggleSortOrder(0);
		mainView.getComboBoxContenido().setModel(new ComboBoxModel<Contenido>(new ContenidoDAO().getAll()));
		mainView.getComboBoxContexto().setModel(new ComboBoxModel<Contexto>(new ContextoDAO().getAll()));
		mainView.getComboBoxCategoria().setModel(new ComboBoxModel<Categoria>(new CategoriaDAO().getAll()));
		mainView.getComboBoxNiño().setModel(new ComboBoxModel<Paciente>(new PacienteDAO().getAll()));
	}
	
	private class TableNotificacionesModel extends DefaultTableModel{
		
		public TableNotificacionesModel(){
			List<Notificacion> notificaciones = new NotificacionDAO().getAll();
			Object[] columns = new String[] {"Fecha/Hora", "Niño", "Contenido", "Contexto", "Etiquetas"};
			Object[][] data = new Object[notificaciones.size()][5];
			for (int i= 0; i < notificaciones.size(); i++){
				data[i][0] = notificaciones.get(i).getDateReceived() +" "+ notificaciones.get(i).getTimeReceived();
				data[i][1] = new PacienteDAO().getById(notificaciones.get(i).getIdPaciente());
				data[i][2] = new ContenidoDAO().getById(notificaciones.get(i).getIdContenido());
				data[i][3] = new ContenidoDAO().getById(notificaciones.get(i).getIdContexto());
				data[i][4] = new NotificacionEtiquetaDAO().getEtiquetasParaNotificacion(notificaciones.get(i).getId());
			}
			this.setDataVector(data, columns);
		}
		
	}
	
	private class ComboBoxModel<T> extends DefaultComboBoxModel<T>{
		
		public ComboBoxModel(){
			
		}
		
		public ComboBoxModel(List<T> elementos) {
			this();
			agregarElementos(elementos);
		}
		
		public void agregarElementos(List<T> elementos){
			for (T e : elementos){
				addElement(e);
			}
		}
		
	}
	
}
