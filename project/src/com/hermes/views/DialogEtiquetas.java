package com.hermes.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Point;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ListSelectionModel;

/**
 * @author federico
 *
 */
public class DialogEtiquetas extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogEtiquetas dialog = new DialogEtiquetas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
			getContentPane().add(panel, BorderLayout.NORTH);
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
						JList list = new JList();
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
					panel_1.add(btnAgregar);
				}
				{
					JButton btnEliminar = new JButton("Eliminar");
					panel_1.add(btnEliminar);
				}
				{
					JButton btnRenombrar = new JButton("Renombrar");
					panel_1.add(btnRenombrar);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
