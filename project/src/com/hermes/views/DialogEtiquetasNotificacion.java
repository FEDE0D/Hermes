package com.hermes.views;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.hermes.dao.sqlite.EtiquetaDAO;
import com.hermes.dao.sqlite.NotificacionEtiquetaDAO;
import com.hermes.model.Etiqueta;
import com.hermes.model.Notificacion;
import com.hermes.model.NotificacionEtiqueta;
import javax.swing.ListSelectionModel;

/**
 * @author federico
 *
 */
public class DialogEtiquetasNotificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane_1;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JList list_1;
	private JList list;
	private Notificacion model;

	/**
	 * Create the dialog.
	 */
	public DialogEtiquetasNotificacion(Notificacion model) {
		this.model = model;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Cambiar etiquetas");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			scrollPane_1 = new JScrollPane();
			{
				list_1 = new JList();
				list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list_1.setModel(new AbstractListModel() {
					String[] values = new String[] {"Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				scrollPane_1.setViewportView(list_1);
			}
		}
		{
			panel = new JPanel();
			
			JButton btnAdd = new JButton(">");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Etiqueta selected = (Etiqueta) getListEtiquetas().getSelectedValue();
					if (selected != null){
						NotificacionEtiqueta ne = new NotificacionEtiqueta(DialogEtiquetasNotificacion.this.model.getId(), selected.getId());
						new NotificacionEtiquetaDAO().guardar(ne);
						update();
					}
				}
			});
			btnAdd.setToolTipText("Agregar etiqueta");
			
			JButton btnRemove = new JButton("<");
			btnRemove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Etiqueta selected = (Etiqueta) getListEtiquetasNotificacion().getSelectedValue();
					if (selected != null){
						new NotificacionEtiquetaDAO().deteleById(DialogEtiquetasNotificacion.this.model.getId(), selected.getId());
						update();
					}
				}
			});
			btnRemove.setToolTipText("Quitar etiqueta");
			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(28)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
							.addComponent(btnRemove, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE))
						.addGap(106))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(71)
						.addComponent(btnAdd)
						.addGap(67)
						.addComponent(btnRemove)
						.addContainerGap(77, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
		}
		{
			scrollPane = new JScrollPane();
			{
				list = new JList();
				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				list.setModel(new AbstractListModel() {
					String[] values = new String[] {"Importante"};
					public int getSize() {
						return values.length;
					}
					public Object getElementAt(int index) {
						return values[index];
					}
				});
				scrollPane.setViewportView(list);
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void update(){
		List<Etiqueta> etiquetas = new EtiquetaDAO().getAll();
		List<Etiqueta> etiquetasN = new NotificacionEtiquetaDAO().getEtiquetasParaNotificacion(model.getId());
		
		getListEtiquetas().setListData(etiquetas.toArray());
		getListEtiquetasNotificacion().setListData(etiquetasN.toArray());
	}
	
	public JList getListEtiquetas() {
		return list_1;
	}
	public JList getListEtiquetasNotificacion() {
		return list;
	}
}
