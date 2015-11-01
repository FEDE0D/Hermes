package com.hermes.main;

import java.util.ArrayList;
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
	
	private ViewManager(){
		mainView = new MainView();
	}
	
	public static ViewManager getInstance() {
		if (_instance == null)
			_instance = new ViewManager();
		return _instance;
	}
	
	public void startMainView() {
		update();
		mainView.showView();
	}
	
	public void startDialogEtiquetas(){
		DialogEtiquetas de = new DialogEtiquetas();
		de.update();
		de.setLocationRelativeTo(null);
		de.setVisible(true);
		update();
	}
	
	public void startDialogEtiquetasNotificacion(Notificacion n){
		DialogEtiquetasNotificacion den = new DialogEtiquetasNotificacion(n);
		den.update();
		den.setLocationRelativeTo(null);
		den.setVisible(true);
		update();
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
		// LIST ETIQUETAS
		mainView.getListEtiquetas().setListData(new EtiquetaDAO().getAll().toArray());
		// TABLA NOTIFICACIONES
		mainView.getTableNotificaciones().setModel(new TableNotificacionesModel(new NotificacionDAO().getAll()));
		mainView.getTableNotificaciones().removeColumn(mainView.getTableNotificaciones().getColumnModel().getColumn(5));
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
	}
	
	public void filtroCambio(){
		System.out.println("Cambio el filtro!");
	}
	
	private class TableNotificacionesModel extends DefaultTableModel{
		
		public TableNotificacionesModel(List<Notificacion> notificaciones){
			Object[] columns = new String[] {"Fecha/Hora", "Paciente", "Contenido", "Contexto", "Etiquetas", "NotificacionObj"};
			Object[][] data = new Object[notificaciones.size()][6];
			for (int i= 0; i < notificaciones.size(); i++) {
				data[i][0] = notificaciones.get(i).getDateReceived() +" "+ notificaciones.get(i).getTimeReceived();
				data[i][1] = new PacienteDAO().getById(notificaciones.get(i).getIdPaciente());
				data[i][2] = new ContenidoDAO().getById(notificaciones.get(i).getIdContenido());
				data[i][3] = new ContenidoDAO().getById(notificaciones.get(i).getIdContexto());
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
	
}
