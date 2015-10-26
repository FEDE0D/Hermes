package com.hermes.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class MainView {

	private JFrame frmHermesVMonitor;
	private JTextField txtBuscar;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final Action action = new SwingAction();
	private JToolBar toolBar_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmHermesVMonitor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHermesVMonitor = new JFrame();
		frmHermesVMonitor.setMinimumSize(new Dimension(640, 480));
		frmHermesVMonitor.setTitle("Hermes Monitor");
		frmHermesVMonitor.setBounds(100, 100, 450, 300);
		frmHermesVMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frmHermesVMonitor.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		toolBar.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EmptyBorder(8, 8, 8, 8)));
		panelUsuario.setSize(new Dimension(128, 96));
		panel_1.add(panelUsuario, BorderLayout.WEST);
		panelUsuario.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblIcon = new JLabel("");
		panelUsuario.add(lblIcon);
		lblIcon.setIcon(new ImageIcon(MainView.class.getResource("/icons/usuario_64.png")));
		
		JLabel lblName = new JLabel("Federico Pacheco");
		panelUsuario.add(lblName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.add(panel_2, BorderLayout.CENTER);
		
		txtBuscar = new JTextField();
		txtBuscar.setHorizontalAlignment(SwingConstants.LEFT);
		txtBuscar.setText("buscar");
		txtBuscar.setToolTipText("Busca por palabras");
		txtBuscar.setColumns(30);
		
		JLabel lblFiltros = new JLabel("Filtros");
		
		JButton btnMore = new JButton("mas filtros");
		btnMore.setAction(action);
		btnMore.setMargin(new Insets(2, 4, 2, 4));
		btnMore.setHorizontalTextPosition(SwingConstants.LEFT);
		btnMore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				getFiltrosAvanzados().setVisible(!getFiltrosAvanzados().isVisible());
			}
		});
		btnMore.setIcon(new ImageIcon(MainView.class.getResource("/icons/more_16.png")));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFiltros)
						.addComponent(txtBuscar, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
						.addComponent(btnMore, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(9)
					.addComponent(lblFiltros)
					.addGap(18)
					.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnMore, GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setAutoCreateGaps(true);
		gl_panel_2.setAutoCreateContainerGaps(true);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JButton btnNotification = new JButton("1");
		btnNotification.setMinimumSize(new Dimension(64, 25));
		panel_3.add(btnNotification, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frmHermesVMonitor.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		toolBar_1 = new JToolBar();
		toolBar_1.setVisible(false);
		toolBar_1.setFloatable(false);
		panel.add(toolBar_1, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new CompoundBorder(null, new EmptyBorder(8, 8, 8, 8)));
		toolBar_1.add(panel_10);
		panel_10.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_10.add(panel_4, BorderLayout.WEST);
		panel_4.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5);
		
		JLabel lblContenido = new JLabel("Contenido");
		panel_5.add(lblContenido);
		
		JComboBox comboBox = new JComboBox();
		panel_5.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Entusiasmado", "Contento", "Alegre", "Molesto", "*"}));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_6);
		
		JLabel lblContexto = new JLabel("Contexto");
		panel_6.add(lblContexto);
		
		JComboBox comboBox_1 = new JComboBox();
		panel_6.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Pista", "Establo-Terapia", "Hogar", "*"}));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);
		
		JLabel lblCategoria = new JLabel("Categoria");
		panel_7.add(lblCategoria);
		
		JComboBox comboBox_2 = new JComboBox();
		panel_7.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"*", "Emociones", "<Predeterminada>"}));
		
		JPanel panel_9 = new JPanel();
		panel_10.add(panel_9, BorderLayout.CENTER);
		panel_9.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.RIGHT);
		panel_9.add(panel_8);
		
		JLabel lblNio = new JLabel("Niño");
		panel_8.add(lblNio);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Luis", "Mariela", "Juan"}));
		panel_8.add(comboBox_3);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_11.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panel_9.add(panel_11);
		
		JLabel lblDesde = new JLabel("desde");
		panel_11.add(lblDesde);
		
		textField = new JTextField();
		panel_11.add(textField);
		textField.setColumns(10);
		
		JLabel lblHasta = new JLabel("hasta");
		panel_11.add(lblHasta);
		
		textField_1 = new JTextField();
		panel_11.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_12.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panel_9.add(panel_12);
		
		JLabel label = new JLabel("desde");
		panel_12.add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_12.add(textField_2);
		
		JLabel label_1 = new JLabel("hasta");
		panel_12.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_12.add(textField_3);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EmptyBorder(4, 4, 4, 4));
		panel_10.add(panel_13, BorderLayout.SOUTH);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JLabel lblEtiquetas = new JLabel("Etiquetas");
		panel_13.add(lblEtiquetas, BorderLayout.NORTH);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_13.add(scrollPane_2);
		
		JList list = new JList();
		list.setLayoutOrientation(JList.VERTICAL_WRAP);
		scrollPane_2.setViewportView(list);
		list.setVisibleRowCount(5);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Importante", "Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8", "Importante", "Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8", "Importante", "Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8", "Importante", "Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Fecha/Hora", "Ni\u00F1o", "Contenido", "Contexto", "Etiquetas"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(SMALL_ICON, new ImageIcon(MainView.class.getResource("/icons/more_16.png")));
			putValue(NAME, "filtros avanzados");
			putValue(SHORT_DESCRIPTION, "Ver los filtros avanzados");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	public JToolBar getFiltrosAvanzados() {
		return toolBar_1;
	}
}
