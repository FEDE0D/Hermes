package com.hermes.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.hermes.dao.sqlite.EtiquetaDAO;
import com.hermes.model.Etiqueta;

/**
 * @author federico
 *
 */
public class DialogEtiquetas extends JDialog {
	private JList list;

	/**
	 * Create the dialog.
	 */
	public DialogEtiquetas() {
		setTitle("Editar etiquetas");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new CompoundBorder(null, new EmptyBorder(2, 8, 2, 8)));
				panel.add(panel_1);
				panel_1.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblEtiquetas = new JLabel("Etiquetas");
					panel_1.add(lblEtiquetas, BorderLayout.NORTH);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					panel_1.add(scrollPane, BorderLayout.CENTER);
					{
						list = new JList();
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						scrollPane.setViewportView(list);
						list.setModel(new AbstractListModel() {
							String[] values = new String[] {"Importante", "Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8"};
							public int getSize() {
								return values.length;
							}
							public Object getElementAt(int index) {
								return values[index];
							}
						});
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1, BorderLayout.SOUTH);
				{
					JButton btnAgregar = new JButton("Agregar");
					btnAgregar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							String respuesta = JOptionPane.showInputDialog("Ingrese el nombre de la nueva etiqueta");
							if (respuesta != null && respuesta.matches("\\w+")) {
								Etiqueta etiqueta = new Etiqueta(-1L, respuesta);
								new EtiquetaDAO().guardar(etiqueta);
								update();
							} else if (respuesta != null) {
								JOptionPane.showMessageDialog(null, "El nombre de la etiqueta es inválido.");
							}
						}
					});
					panel_1.add(btnAgregar);
				}
				{
					JButton btnEliminar = new JButton("Eliminar");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							Object selected = getListEtiquetas().getSelectedValue();
							if (selected != null) {
								Etiqueta e = (Etiqueta) selected;
								if (JOptionPane.showConfirmDialog(null, "¿Eliminar la etiqueta '"+e.getDescripcion()+"' ?")==JOptionPane.OK_OPTION){
									new EtiquetaDAO().deteleById(e.getId());									
									update();
								}
							}
						}
					});
					panel_1.add(btnEliminar);
				}
				{
					JButton btnRenombrar = new JButton("Renombrar");
					btnRenombrar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent ae) {
							Object selected = getListEtiquetas().getSelectedValue();
							if (selected != null){
								Etiqueta e = (Etiqueta) selected;
								String respuesta = JOptionPane.showInputDialog("Ingrese el nuevo nombre para la etiqueta '"+e.getDescripcion()+"'");
								if (respuesta != null && respuesta.matches("\\w+")){
									e.setDescripcion(respuesta);
									new EtiquetaDAO().actualizar(e);
									update();
								} else if (respuesta != null){
									JOptionPane.showMessageDialog(null, "El nombre de la etiqueta es inválido.");
								}
							}
						}
					});
					panel_1.add(btnRenombrar);
				}
			}
		}
	}
	
	public void update(){
		getListEtiquetas().setListData(new EtiquetaDAO().getAll().toArray());
	}
	
	public JList getListEtiquetas() {
		return list;
	}
}
