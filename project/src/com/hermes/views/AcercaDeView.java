package com.hermes.views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class AcercaDeView extends JDialog {

	/**
	 * Create the dialog.
	 */
	public AcercaDeView() {
		setTitle("Acerca de...");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 8, 5, 8));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInfo = new JLabel("<html>\n<h1>Hermes</h1>\n<h3>Comunicador Digital para Personas portadoras de TEA</h3>\nDesarrollado por:\n<ul>\n<li>Federico Pacheco</li>\n<li>Ramiro Ferrari</li>\n</ul>\n\n<p>Para la Cátedra de Laboratorio de Software, Facultad de Informatica, UNLP.\n2015\n</p>\n</html>");
		lblInfo.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblInfo, BorderLayout.NORTH);
		
		JButton btnLink = new JButton("<html>\n<a href=\"https://github.com/FEDE0D/Hermes\">Codigo fuente en GitHub</a>\n</html>");
		btnLink.setFocusPainted(false);
		btnLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/FEDE0D/Hermes"));
				} catch (IOException | URISyntaxException e1) {
//					e1.printStackTrace();
				}
			}
		});
		btnLink.setContentAreaFilled(false);
		btnLink.setBorderPainted(false);
		btnLink.setBackground(UIManager.getColor("Label.background"));
		btnLink.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(btnLink, BorderLayout.SOUTH);

	}
}
