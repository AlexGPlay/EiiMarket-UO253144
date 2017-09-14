package extensionesGui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class panelFiltro extends JPanel {
	private JPanel pnTodos;
	private JLabel lblMostrasTodos;
	private JPanel pnFiltros;
	private JPanel pnSubcategorias;
	private JLabel lblSubactegorias;
	private JPanel pnListaSub;
	@SuppressWarnings("rawtypes")
	private JList lstSubcat;
	private JPanel pnStockPrecio;
	private JCheckBox chckbxMostrarProductosSin;
	private JPanel pnCheck;
	private JLabel lblStock;
	private JPanel pnPrecios;
	private JLabel lblPrecios;
	private JPanel pnContenido;
	private JPanel pnSetPrecios;
	private JPanel pnDesde;
	private JPanel pnHasta;
	private JLabel lblDesde;
	private JTextField txtDesde;
	private JLabel lblHasta;
	private JTextField txtHasta;
	private JLabel lblEurosDesde;
	private JLabel lblEurosHasta;
	
	private onlyNumerical oN;
	private VentanaPrincipal parent;

	/**
	 * Create the panel.
	 */
	public panelFiltro(VentanaPrincipal v) {
		parent = v;
		oN = new onlyNumerical();
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPnTodos(), BorderLayout.NORTH);
		add(getPnFiltros(), BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(300, 0));
	}

	private JPanel getPnTodos() {
		if (pnTodos == null) {
			pnTodos = new JPanel();
			pnTodos.setBackground(Color.WHITE);
			pnTodos.setLayout(new BorderLayout(0, 0));
			pnTodos.add(getLblMostrasTodos());
		}
		return pnTodos;
	}
	public JLabel getLblMostrasTodos() {
		if (lblMostrasTodos == null) {
			lblMostrasTodos = new JLabel(parent.getTextos().getString("MostrarTodos"));
			lblMostrasTodos.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblMostrasTodos.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblMostrasTodos.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblMostrasTodos;
	}
	private JPanel getPnFiltros() {
		if (pnFiltros == null) {
			pnFiltros = new JPanel();
			pnFiltros.setLayout(new BorderLayout(0, 0));
			pnFiltros.setBackground(Color.decode("#e3e3e3"));
			pnFiltros.add(getPnSubcategorias(), BorderLayout.NORTH);
			pnFiltros.add(getPnStockPrecio(), BorderLayout.CENTER);
		}
		return pnFiltros;
	}
	private JPanel getPnSubcategorias() {
		if (pnSubcategorias == null) {
			pnSubcategorias = new JPanel();
			pnSubcategorias.setLayout(new BorderLayout(0, 0));
			pnSubcategorias.setBackground(Color.decode("#d9d9d9"));
			pnSubcategorias.add(getLblSubactegorias(), BorderLayout.NORTH);
			pnSubcategorias.add(getPnListaSub(), BorderLayout.SOUTH);
		}
		return pnSubcategorias;
	}
	private JLabel getLblSubactegorias() {
		if (lblSubactegorias == null) {
			lblSubactegorias = new JLabel(parent.getTextos().getString("Subcategoria"));
			lblSubactegorias.setForeground(new Color(0, 0, 0));
			lblSubactegorias.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblSubactegorias.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblSubactegorias;
	}
	private JPanel getPnListaSub() {
		if (pnListaSub == null) {
			pnListaSub = new JPanel();
			pnListaSub.setBackground(Color.WHITE);
			pnListaSub.setLayout(new BorderLayout(0, 0));
			pnListaSub.add(getLstSubcat());
		}
		return pnListaSub;
	}
	@SuppressWarnings("rawtypes")
	public JList getLstSubcat() {
		if (lstSubcat == null) {
			lstSubcat = new JList();
			lstSubcat.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			lstSubcat.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lstSubcat;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setModel(DefaultListModel model) {
		lstSubcat.setModel(model);
	}
	
	public void ocultarPanelSubcategorias() {
		pnSubcategorias.setVisible(false);
	}
	
	public void mostrarPanelSubcategorias(){
		pnSubcategorias.setVisible(true);
	}
		
	private JPanel getPnStockPrecio() {
		if (pnStockPrecio == null) {
			pnStockPrecio = new JPanel();
			pnStockPrecio.setLayout(new BorderLayout(0, 0));
			pnStockPrecio.setBackground(Color.decode("#d9d9d9"));
			pnStockPrecio.add(getPnCheck(), BorderLayout.CENTER);
			pnStockPrecio.add(getLblStock(), BorderLayout.NORTH);
		}
		return pnStockPrecio;
	}
	public JCheckBox getChckbxMostrarProductosSin() {
		if (chckbxMostrarProductosSin == null) {
			chckbxMostrarProductosSin = new JCheckBox(parent.getTextos().getString("MostrarDisponibles"));
			chckbxMostrarProductosSin.setBorder(new EmptyBorder(10, 10, 10, 10));
			chckbxMostrarProductosSin.setHorizontalAlignment(SwingConstants.LEFT);
			chckbxMostrarProductosSin.setBackground(Color.WHITE);
		}
		return chckbxMostrarProductosSin;
	}
	private JPanel getPnCheck() {
		if (pnCheck == null) {
			pnCheck = new JPanel();
			pnCheck.setBackground(Color.WHITE);
			pnCheck.setLayout(new BorderLayout(0, 0));
			pnCheck.add(getChckbxMostrarProductosSin(), BorderLayout.NORTH);
			pnCheck.add(getPnPrecios(), BorderLayout.CENTER);
		}
		return pnCheck;
	}
	private JLabel getLblStock() {
		if (lblStock == null) {
			lblStock = new JLabel("Stock");
			lblStock.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblStock.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblStock;
	}
	private JPanel getPnPrecios() {
		if (pnPrecios == null) {
			pnPrecios = new JPanel();
			pnPrecios.setLayout(new BorderLayout(0, 0));
			pnPrecios.setBackground(Color.decode("#d9d9d9"));
			pnPrecios.add(getLblPrecios(), BorderLayout.NORTH);
			pnPrecios.add(getPnContenido(), BorderLayout.CENTER);
		}
		return pnPrecios;
	}
	private JLabel getLblPrecios() {
		if (lblPrecios == null) {
			lblPrecios = new JLabel(parent.getTextos().getString("Precio"));
			lblPrecios.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblPrecios.setHorizontalAlignment(SwingConstants.LEFT);
			lblPrecios.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblPrecios;
	}
	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setBackground(Color.decode("#e3e3e3"));
			pnContenido.setLayout(new BorderLayout(0, 0));
			pnContenido.add(getPnSetPrecios(), BorderLayout.NORTH);
		}
		return pnContenido;
	}
	private JPanel getPnSetPrecios() {
		if (pnSetPrecios == null) {
			pnSetPrecios = new JPanel();
			pnSetPrecios.setBackground(Color.WHITE);
			pnSetPrecios.setLayout(new GridLayout(2, 1, 0, 0));
			pnSetPrecios.add(getPnDesde());
			pnSetPrecios.add(getPnHasta());
		}
		return pnSetPrecios;
	}
	private JPanel getPnDesde() {
		if (pnDesde == null) {
			pnDesde = new JPanel();
			pnDesde.setBorder(new EmptyBorder(10, 10, 5, 10));
			pnDesde.setBackground(Color.WHITE);
			pnDesde.setLayout(new BorderLayout(0, 0));
			pnDesde.add(getLblDesde(), BorderLayout.WEST);
			pnDesde.add(getTxtDesde(), BorderLayout.CENTER);
			pnDesde.add(getLblEurosDesde(), BorderLayout.EAST);
		}
		return pnDesde;
	}
	
	private JLabel getLblEurosDesde() {
		if(lblEurosDesde == null){
			lblEurosDesde = new JLabel("€");
		}
		
		return lblEurosDesde;
	}
	
	private JLabel getLblEurosHasta(){
		if(lblEurosHasta == null){
			lblEurosHasta = new JLabel("€");
		}
		
		return lblEurosHasta;
	}

	private JPanel getPnHasta() {
		if (pnHasta == null) {
			pnHasta = new JPanel();
			pnHasta.setBorder(new EmptyBorder(5, 10, 10, 10));
			pnHasta.setBackground(Color.WHITE);
			pnHasta.setLayout(new BorderLayout(0, 0));
			pnHasta.add(getLblHasta(), BorderLayout.WEST);
			pnHasta.add(getTxtHasta(), BorderLayout.CENTER);
			pnHasta.add(getLblEurosHasta(), BorderLayout.EAST);
		}
		return pnHasta;
	}
	private JLabel getLblDesde() {
		if (lblDesde == null) {
			lblDesde = new JLabel(parent.getTextos().getString("Desde"));
		}
		return lblDesde;
	}
	public JTextField getTxtDesde() {
		if (txtDesde == null) {
			txtDesde = new JTextField();
			txtDesde.addKeyListener(oN);
			txtDesde.setColumns(4);
		}
		return txtDesde;
	}
	private JLabel getLblHasta() {
		if (lblHasta == null) {
			lblHasta = new JLabel(parent.getTextos().getString("Hasta"));
		}
		return lblHasta;
	}
	public JTextField getTxtHasta() {
		if (txtHasta == null) {
			txtHasta = new JTextField();
			txtHasta.addKeyListener(oN);
			txtHasta.setColumns(10);
		}
		return txtHasta;
	}
	
	private class onlyNumerical extends KeyAdapter{
		
		@SuppressWarnings("unused")
		@Override
		public void keyTyped(KeyEvent e){
			String x = String.valueOf(e.getKeyChar());
			
			try{
				int t = Integer.valueOf(x);
			}
			catch(NumberFormatException exc){
				e.consume();
			}
		}
		
	}
}
