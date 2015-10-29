package com.hermes.main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import com.hermes.uml.Etiqueta;
import com.hermes.uml.Notificacion;
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
		window.getListEtiquetas().setModel(new ModeloListaEtiquetas());
		window.showView();
	}
	
	public void startDialogEtiquetas(){
		DialogEtiquetas de = new DialogEtiquetas();
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
	
	
	private class ModeloListaEtiquetas extends AbstractListModel<Etiqueta>{
		
		private static final long serialVersionUID = 5996664679011962017L;
		private List<Etiqueta> lista;
		
		public ModeloListaEtiquetas() {
			lista = new ArrayList<Etiqueta>();
			lista.add(new Etiqueta());
			lista.add(new Etiqueta());
			lista.add(new Etiqueta());
			lista.add(new Etiqueta());
			lista.add(new Etiqueta());
		}
		
		@Override
		public Etiqueta getElementAt(int i) {
			return lista.get(i);
		}

		@Override
		public int getSize() {
			return lista.size();
		}
		
	}
	
}
