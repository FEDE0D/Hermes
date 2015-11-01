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

import com.hermes.dao.sqlite.ContextoDAO;
import com.hermes.dao.sqlite.NotificacionDAO;
import com.hermes.dao.sqlite.PacienteDAO;
import com.hermes.main.ViewManager;
import com.hermes.model.Contexto;
import com.hermes.model.Etiqueta;
import com.hermes.model.Notificacion;
import com.hermes.model.Paciente;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
/**
 * @author federico
 *
 */
public class MainView {

	private JFrame frmHermesVMonitor;
	private JTextField txtBuscar;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private final Action actionFiltrosAvanzados = new SwingAction();
	private JToolBar toolBar_1;
	private final Action actionSalir = new SwingAction_1();
	private final Action actionLimpiarFiltros = new SwingAction_2();
	private final Action actionEditarEtiquetas = new SwingAction_3();
	private JList list;
	private JComboBox comboBox_3;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;

	/**
	 * 
	 * */
	public void showView(){
		frmHermesVMonitor.setLocation(0, 0);
		frmHermesVMonitor.setVisible(true);
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
		frmHermesVMonitor.setMinimumSize(new Dimension(1024, 600));
		frmHermesVMonitor.setTitle("Hermes Monitor");
		frmHermesVMonitor.setBounds(100, 100, 450, 300);
		frmHermesVMonitor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmHermesVMonitor.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
		mntmSalir.setAction(actionSalir);
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnMenu.add(mntmSalir);
		
		JPanel panel_14 = new JPanel();
		frmHermesVMonitor.getContentPane().add(panel_14, BorderLayout.CENTER);
		panel_14.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel_14.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false);
		
		JPanel panel_1 = new JPanel();
		toolBar.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EmptyBorder(8, 8, 8, 8)));
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
		txtBuscar.setToolTipText("Busca por palabras");
		txtBuscar.setColumns(30);
		
		JLabel lblFiltros = new JLabel("Filtros");
		
		JButton btnMore = new JButton("mas filtros");
		btnMore.setAction(actionFiltrosAvanzados);
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
						.addComponent(txtBuscar, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
						.addComponent(btnMore, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(4)
					.addComponent(lblFiltros)
					.addGap(4)
					.addComponent(txtBuscar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(btnMore, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_panel_2.setAutoCreateGaps(true);
		gl_panel_2.setAutoCreateContainerGaps(true);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.EAST);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_14.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		toolBar_1 = new JToolBar();
		toolBar_1.setVisible(false);
		toolBar_1.setFloatable(false);
		panel.add(toolBar_1, BorderLayout.NORTH);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new CompoundBorder(null, new EmptyBorder(8, 8, 8, 8)));
		toolBar_1.add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{196, 171, 248, 0};
		gbl_panel_10.rowHeights = new int[]{111, 0, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new EmptyBorder(4, 4, 4, 4));
		GridBagConstraints gbc_panel_13 = new GridBagConstraints();
		gbc_panel_13.gridheight = 2;
		gbc_panel_13.fill = GridBagConstraints.BOTH;
		gbc_panel_13.insets = new Insets(0, 0, 5, 5);
		gbc_panel_13.gridx = 0;
		gbc_panel_13.gridy = 0;
		panel_10.add(panel_13, gbc_panel_13);
		panel_13.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_15 = new JPanel();
		panel_13.add(panel_15, BorderLayout.NORTH);
		
		JLabel lblEtiquetas = new JLabel("Etiquetas");
		panel_15.add(lblEtiquetas);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_13.add(scrollPane_2);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		scrollPane_2.setViewportView(list);
		list.setVisibleRowCount(5);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Importante", "Charlar con padre", "Avance", "Etc1", "Etc2", "Etc3", "Etc4", "Etc5", "Etc6", "Etc7", "Etc8"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnEditEtiquetas = new JButton("Editar");
		btnEditEtiquetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ViewManager.getInstance().startDialogEtiquetas();
			}
		});
		btnEditEtiquetas.setAction(actionEditarEtiquetas);
		panel_13.add(btnEditEtiquetas, BorderLayout.SOUTH);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new CompoundBorder(null, new EmptyBorder(0, 4, 0, 4)));
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.insets = new Insets(0, 0, 5, 5);
		gbc_panel_9.gridx = 1;
		gbc_panel_9.gridy = 0;
		panel_10.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_8 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_8.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_8);
		
		JLabel lblNio = new JLabel("Niño/a");
		panel_8.add(lblNio);
		
		comboBox_3 = new JComboBox();
		comboBox_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Luis", "Mariela", "Juan"}));
		panel_8.add(comboBox_3);
		
		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_11.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_11);
		
		JLabel lblHora = new JLabel("");
		lblHora.setIcon(new ImageIcon(MainView.class.getResource("/icons/time_16.png")));
		panel_11.add(lblHora);
		
		JLabel lblDesde = new JLabel("desde:");
		panel_11.add(lblDesde);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		panel_11.add(textField);
		textField.setColumns(10);
		
		JLabel lblHasta = new JLabel("hasta:");
		panel_11.add(lblHasta);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		panel_11.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_12 = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) panel_12.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		panel_9.add(panel_12);
		
		JLabel lblFecha = new JLabel("");
		lblFecha.setIcon(new ImageIcon(MainView.class.getResource("/icons/date_16.png")));
		panel_12.add(lblFecha);
		
		JLabel lblDesde_1 = new JLabel("desde:");
		panel_12.add(lblDesde_1);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		textField_2.setColumns(10);
		panel_12.add(textField_2);
		
		JLabel lblHasta_1 = new JLabel("hasta:");
		panel_12.add(lblHasta_1);
		
		textField_3 = new JTextField();
		textField_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		textField_3.setColumns(10);
		panel_12.add(textField_3);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 2;
		gbc_panel_4.gridy = 0;
		panel_10.add(panel_4, gbc_panel_4);
		panel_4.setLayout(new GridLayout(3, 2, 0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_5.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_5);
		
		JLabel lblContenido = new JLabel("Contenido");
		panel_5.add(lblContenido);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		panel_5.add(comboBox);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Entusiasmado", "Contento", "Alegre", "Molesto", "*"}));
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_6);
		
		JLabel lblContexto = new JLabel("Contexto");
		panel_6.add(lblContexto);
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		panel_6.add(comboBox_1);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Pista", "Establo-Terapia", "Hogar", "*"}));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panel_4.add(panel_7);
		
		JLabel lblCategoria = new JLabel("Categoria");
		panel_7.add(lblCategoria);
		
		comboBox_2 = new JComboBox();
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.getInstance().filtroCambio();
			}
		});
		panel_7.add(comboBox_2);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"*", "Emociones", "<Predeterminada>"}));
		
		JPanel panel_16 = new JPanel();
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.anchor = GridBagConstraints.EAST;
		gbc_panel_16.gridwidth = 2;
		gbc_panel_16.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_16.insets = new Insets(0, 0, 0, 5);
		gbc_panel_16.gridx = 1;
		gbc_panel_16.gridy = 1;
		panel_10.add(panel_16, gbc_panel_16);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JButton btnClear = new JButton("Clear");
		btnClear.setAction(actionLimpiarFiltros);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// TODO
				System.out.println("TODO: Limpiar todos los filtros");
				ViewManager.getInstance().filtroCambio();
			}
		});
		btnClear.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_16.add(btnClear, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3){
					int row = table.rowAtPoint(e.getPoint());
					table.setRowSelectionInterval(row, row);
				}
			}
		});
		table.setAutoCreateRowSorter(true);
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmEditar = new JMenuItem("Cambiar etiquetas");
		mntmEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int row = getTableNotificaciones().convertRowIndexToModel(getTableNotificaciones().getSelectedRow());
				Notificacion n = (Notificacion) getTableNotificaciones().getModel().getValueAt(row, 5);
				ViewManager.getInstance().startDialogEtiquetasNotificacion(n);
			}
		});
		popupMenu.add(mntmEditar);
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
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Salir");
			putValue(SHORT_DESCRIPTION, "Salir del Hermes");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(ACTION_COMMAND_KEY, "");
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
			putValue(SMALL_ICON, new ImageIcon(MainView.class.getResource("/icons/erase_16.png")));
			putValue(NAME, "Limpiar");
			putValue(SHORT_DESCRIPTION, "Limpiar todos los filtros");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(SMALL_ICON, new ImageIcon(MainView.class.getResource("/icons/edit_16.png")));
			putValue(NAME, "Editar");
			putValue(SHORT_DESCRIPTION, "Cambiar o eliminar etiquetas");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	public JList getListEtiquetas() {
		return list;
	}
	public JTable getTableNotificaciones() {
		return table;
	}
	public JComboBox getComboBoxPaciente() {
		return comboBox_3;
	}
	public JComboBox getComboBoxContenido() {
		return comboBox;
	}
	public JComboBox getComboBoxContexto() {
		return comboBox_1;
	}
	public JComboBox getComboBoxCategoria() {
		return comboBox_2;
	}
	public JTextField getTextFieldHoraDesde() {
		return textField;
	}
	public JTextField getTextFieldHoraHasta() {
		return textField_1;
	}
	public JTextField getTextFieldFechaDesde() {
		return textField_2;
	}
	public JTextField getTextFieldFechaHasta() {
		return textField_3;
	}
}
