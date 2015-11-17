package com.hermes.main;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
import com.hermes.model.Etiqueta;
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
	private int notificaciones_sin_mostrar = 0;
	
	private ViewManager(){
		mainView = new MainView();
	}
	
	public static ViewManager getInstance() {
		if (_instance == null)
			_instance = new ViewManager();
		return _instance;
	}
	
	public void startMainView() {
		clear();
		mainView.showView();
	}
	
	public void startDialogEtiquetas(){
		DialogEtiquetas de = new DialogEtiquetas();
		de.update();
		de.setLocationRelativeTo(null);
		de.setVisible(true);
		filtro();
	}
	
	public void startDialogEtiquetasNotificacion(Notificacion n){
		DialogEtiquetasNotificacion den = new DialogEtiquetasNotificacion(n);
		den.update();
		den.setLocationRelativeTo(null);
		den.setVisible(true);
		filtro();
	}
	
	
	/**
	 * @param n Notificacion 
	 */
	public void showNotification(Notificacion n){
		// GUARDAR NOTIFICACION
		new NotificacionDAO().guardar(n);
		
		// UPDATE VIEW
		if (mainView.getFiltrosAvanzados().isVisible()){
			notificaciones_sin_mostrar++;
		} else {
			notificaciones_sin_mostrar = 0;
			filtro();
		}
		showNotificationMessage();
	}
	
	private void showNotificationMessage(){
		if (notificaciones_sin_mostrar > 0){
			String message = "Hay ("+notificaciones_sin_mostrar+") notificaciones nuevas.";
			mainView.getLblNotificacion().setText(message);
			mainView.getLblNotificacion().setForeground(Color.RED);
			ViewManager.playSndNotification();
		}else{
			String message = "No hay notificaciones nuevas.";
			mainView.getLblNotificacion().setText(message);
			mainView.getLblNotificacion().setForeground(Color.BLACK);
		}
	}
	
	/**
	 * Refresca la vista del monitor.
	 * <br>
	 * Se vuelve a cargar desde la bbdd los datos y se pide a los componentes que se dibujen.
	 */
	public void filtro(){
		try{
			long id_paciente = ((Paciente) mainView.getComboBoxPaciente().getSelectedItem()).getId();
			long id_contenido = ((Contenido) mainView.getComboBoxContenido().getSelectedItem()).getId();
			long id_categoria = ((Categoria) mainView.getComboBoxCategoria().getSelectedItem()).getId();
			long id_contexto = ((Contexto) mainView.getComboBoxContexto().getSelectedItem()).getId();
			List<Long> id_etiquetas = new ArrayList<Long>(); for (Object e : mainView.getListEtiquetas().getSelectedValuesList()) id_etiquetas.add(((Etiqueta) e).getId());
			String dateFrom = mainView.getTextFieldFechaDesde().getText();
			String dateTo = mainView.getTextFieldFechaHasta().getText();
			String timeFrom = mainView.getTextFieldHoraDesde().getText();
			String timeTo = mainView.getTextFieldHoraHasta().getText();
			
			List<Notificacion> filtro = new NotificacionDAO().filtrar(id_contenido, id_contexto, id_categoria,id_paciente, id_etiquetas, dateFrom, dateTo, timeFrom, timeTo);
			mainView.getTableNotificaciones().setModel(new TableNotificacionesModel(filtro));
			mainView.getTableNotificaciones().getColumnModel().getColumn(5).setMinWidth(0);
			mainView.getTableNotificaciones().getColumnModel().getColumn(5).setMaxWidth(0);
			mainView.getTableNotificaciones().getColumnModel().getColumn(5).setResizable(false);
			mainView.getTableNotificaciones().getRowSorter().toggleSortOrder(0);
			mainView.getTableNotificaciones().getRowSorter().toggleSortOrder(0);
		} catch(Exception e) {
			// POR ALGUNA RAZON TIRA ArrayOutOfBounds para acceder a la columa 5 de la table notificaciones (es la que guarda el objeto)
		}
		
	}
	
	public void hideFilters() {
		this.mainView.getFiltrosAvanzados().setVisible(false);
	}
	
	public void showFilters(){
		this.mainView.getFiltrosAvanzados().setVisible(true);
	}
	
	
	
	public void clear(){
		// LIST ETIQUETAS
		mainView.getListEtiquetas().setListData(new EtiquetaDAO().getAll().toArray());
		// TABLA NOTIFICACIONES
		mainView.getTableNotificaciones().setModel(new TableNotificacionesModel(new NotificacionDAO().getAll()));
		mainView.getTableNotificaciones().getColumnModel().getColumn(5).setMinWidth(0);
		mainView.getTableNotificaciones().getColumnModel().getColumn(5).setMaxWidth(0);
		mainView.getTableNotificaciones().getColumnModel().getColumn(5).setResizable(false);
		
		mainView.getTableNotificaciones().getRowSorter().toggleSortOrder(0);
		mainView.getTableNotificaciones().getRowSorter().toggleSortOrder(0);
		// COMBOBOXs FILTROS
		List<Contenido> contenidos = new ArrayList<Contenido>();
		contenidos.add(new Contenido(0, 0, 0, 0, "Todos", null, null));
		contenidos.addAll(new ContenidoDAO().getAll());
		mainView.getComboBoxContenido().setModel(new ComboBoxModel<Contenido>(contenidos));
		
		List<Contexto> contextos = new ArrayList<Contexto>();
		contextos.add(new Contexto(0, 0, 0, "Todos"));
		contextos.addAll(new ContextoDAO().getAll());
		mainView.getComboBoxContexto().setModel(new ComboBoxModel<Contexto>(contextos));
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		categorias.add(new Categoria(0, 0, 0, "Todas"));
		categorias.addAll(new CategoriaDAO().getAll());
		mainView.getComboBoxCategoria().setModel(new ComboBoxModel<Categoria>(categorias));
		
		List<Paciente> pacientes = new ArrayList<Paciente>();
		pacientes.add(new Paciente(0, "Todos", "", '*'));
		pacientes.addAll(new PacienteDAO().getAll());
		mainView.getComboBoxPaciente().setModel(new ComboBoxModel<Paciente>(pacientes));
		
		mainView.getTextFieldFechaDesde().setText("");;
		mainView.getTextFieldFechaHasta().setText("");
		mainView.getTextFieldHoraDesde().setText("");
		mainView.getTextFieldHoraHasta().setText("");
		
		notificaciones_sin_mostrar = 0;
		showNotificationMessage();
	}
	
	public void updateFiltros(){
		
	}
	
	private class TableNotificacionesModel extends DefaultTableModel{
		
		public TableNotificacionesModel(List<Notificacion> notificaciones){
			Object[] columns = new String[] {"Fecha/Hora", "Paciente", "Contenido", "Contexto", "Etiquetas", "NotificacionObj"};
			Object[][] data = new Object[notificaciones.size()][6];
			for (int i= 0; i < notificaciones.size(); i++) {
				data[i][0] = notificaciones.get(i).getDateReceived() +" "+ notificaciones.get(i).getTimeReceived();
				data[i][1] = new PacienteDAO().getById(notificaciones.get(i).getIdPaciente());
				data[i][2] = new ContenidoDAO().getById(notificaciones.get(i).getIdContenido());
				data[i][3] = new ContextoDAO().getById(notificaciones.get(i).getIdContexto());
				data[i][4] = new NotificacionEtiquetaDAO().getEtiquetasParaNotificacion(notificaciones.get(i).getId());
				data[i][5] = notificaciones.get(i);
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
	
	private static void playSndNotification(){
		try {
			AudioStream stream = new AudioStream(ViewManager.class.getResourceAsStream("/sounds/notification.wav"));
			AudioPlayer.player.start(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
