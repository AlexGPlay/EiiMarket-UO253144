package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import gui.VentanaPrincipal;
import logica.Articulo;
import logica.Categoria;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;

@SuppressWarnings("serial")
public class panelCatalogo extends JPanel {
	private JScrollPane scrollCatalogo;
	private JPanel pnCatalogo;

	private VentanaPrincipal parent;
	private ArrayList<Articulo> preFiltro;
	private JPanel pnContenedor;
	private panelFiltro panelFiltro;
	private JPanel pnCat;
	private JPanel pnTitulo;
	private JLabel lblCat;
	private String[] codigo;

	private JCheckBox chTemp;
	private JTextField txDesde;
	private JTextField txHasta;
	private JLabel lblMostrar;
	@SuppressWarnings("rawtypes")
	private JList lstSub;

	/**
	 * Create the panel.
	 */
	public panelCatalogo(VentanaPrincipal v) {
		parent = v;
		
		setLayout(new BorderLayout(0, 0));
		add(getScrollCatalogo(), BorderLayout.CENTER);

		chTemp = panelFiltro.getChckbxMostrarProductosSin();
		txDesde = panelFiltro.getTxtDesde();
		txHasta = panelFiltro.getTxtHasta();
		lblMostrar = panelFiltro.getLblMostrasTodos();
		lstSub = panelFiltro.getLstSubcat();

		crearEventosFiltro();
	}
	
	public ArrayList<Articulo> getPreFiltro(){
		return preFiltro;
	}

	private void crearEventosFiltro(){
		keyFiltro kF = new keyFiltro();
		focusFiltro fF = new focusFiltro();

		lblMostrar.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				generarCatalogo(preFiltro, codigo);
			}
		});
		
		chTemp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				aplicaFiltros();
			}
		});
		
		lstSub.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				aplicaFiltros();
			}
			
		});

		txDesde.addKeyListener(kF);
		txDesde.addFocusListener(fF);
		txHasta.addKeyListener(kF);
		txHasta.addFocusListener(fF);
	}

	private JScrollPane getScrollCatalogo() {
		if (scrollCatalogo == null) {
			scrollCatalogo = new JScrollPane();
			scrollCatalogo.setBorder(null);
			scrollCatalogo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollCatalogo.setViewportView(getPnContenedor());

		}
		return scrollCatalogo;
	}
	private JPanel getPnCatalogo() {
		if (pnCatalogo == null) {
			pnCatalogo = new JPanel();
			pnCatalogo.setBackground(Color.WHITE);
			pnCatalogo.setLayout(new GridLayout(0, 4, 10, 10));
		}
		return pnCatalogo;
	}

	public void generarCatalogo(ArrayList<Articulo> art, String[] cod){
		pnCatalogo.removeAll();
		preFiltro = art;
		codigo = cod;

		chTemp.setSelected(false);
		txDesde.setText("0");
		lstSub.clearSelection();

		int j = 0;

		int codigo = Integer.valueOf(cod[0]);
		String busqueda = cod[1];

		if(codigo==1) {
			lblCat.setText(parent.getTextos().getString("Busqueda") + busqueda);

			panelFiltro.ocultarPanelSubcategorias();
		}

		else {
			lblCat.setText(parent.getControlador().getCategorias().get(Integer.valueOf(busqueda)).getNombre());

			ArrayList<Categoria> subcategorias = parent.getControlador().getCategorias().get(Integer.valueOf(busqueda)).getSubcategorias();

			if(subcategorias.size()>1) {
				DefaultListModel<Categoria> model = new DefaultListModel<Categoria>();

				for(int i=0;i<subcategorias.size();i++) {
					model.addElement(subcategorias.get(i));
				}

				panelFiltro.mostrarPanelSubcategorias();
				panelFiltro.setModel(model);
			}

			else
				panelFiltro.ocultarPanelSubcategorias();
		}

		double max = 0;
		while(j<art.size()){
			boolean descuento = false;
			if(art.get(j).getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
				descuento = true;
			
			panelArticulo temp = new panelArticulo(art.get(j), parent, descuento);
			temp.setPreferredSize(new Dimension(150,300));
			temp.setMaximumSize(new Dimension(150,300));
			pnCatalogo.add(temp);

			if(art.get(j).getPrecio()>max)
				max = art.get(j).getPrecio();

			j++;
		}
		
		int x = (int)(max+1);
		txHasta.setText(String.valueOf(x));

		while(j<5) {
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			pnCatalogo.add(temp);
			j++;
		}

	}

	@SuppressWarnings("rawtypes")
	private void aplicaFiltros(){
		DefaultListModel model = (DefaultListModel) lstSub.getModel();
		lstSub.getSelectedIndex();
		pnCatalogo.removeAll();
		Categoria cat = null;

		if(lstSub.getSelectedIndex()>=0)
			cat = (Categoria)model.getElementAt(lstSub.getSelectedIndex());

		double desde = Double.valueOf(txDesde.getText());
		double hasta = Double.valueOf(txHasta.getText());
		
		int a = 0;

		for(int i=0;i<preFiltro.size();i++){
			Articulo temp = preFiltro.get(i);

			if(chTemp.isSelected()){
				if(cat==null){
					if(temp.getPrecio()>=desde && temp.getPrecio()<=hasta && temp.getStock()>0){
						boolean descuento = false;
						if(preFiltro.get(i).getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
							descuento = true;
						
						panelArticulo temporal = new panelArticulo(preFiltro.get(i), parent, descuento);
						temporal.setPreferredSize(new Dimension(150,300));
						temporal.setMaximumSize(new Dimension(150,300));
						pnCatalogo.add(temporal);
						a++;
					}
				}

				else{
					if(temp.getPrecio()>=desde && temp.getPrecio()<=hasta && temp.getSubcategoria().equals(cat.getNombre()) && temp.getStock()>0){
						boolean descuento = false;
						if(preFiltro.get(i).getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
							descuento = true;
						
						panelArticulo temporal = new panelArticulo(preFiltro.get(i), parent, descuento);
						temporal.setPreferredSize(new Dimension(150,300));
						temporal.setMaximumSize(new Dimension(150,300));
						pnCatalogo.add(temporal);
						a++;
					}
				}
			}

			else{
				if(cat==null){
					if(temp.getPrecio()>=desde && temp.getPrecio()<=hasta){
						boolean descuento = false;
						if(preFiltro.get(i).getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
							descuento = true;
						
						panelArticulo temporal = new panelArticulo(preFiltro.get(i), parent, descuento);;
						temporal.setPreferredSize(new Dimension(150,300));
						temporal.setMaximumSize(new Dimension(150,300));
						pnCatalogo.add(temporal);
						a++;
					}
				}
				else{
					if(temp.getPrecio()>=desde && temp.getPrecio()<=hasta  && temp.getSubcategoria().equals(cat.getNombre())){
						boolean descuento = false;
						if(preFiltro.get(i).getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
							descuento = true;
						
						panelArticulo temporal = new panelArticulo(preFiltro.get(i), parent, descuento);
						temporal.setPreferredSize(new Dimension(150,300));
						temporal.setMaximumSize(new Dimension(150,300));
						pnCatalogo.add(temporal);
						a++;
					}
				}

			}

		} 	
		
		while(a<5) {
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			pnCatalogo.add(temp);
			a++;
		}
		
		pnCatalogo.revalidate();
		pnCatalogo.repaint();
	}

	private JPanel getPnContenedor() {
		if (pnContenedor == null) {
			pnContenedor = new JPanel();
			pnContenedor.setBorder(new EmptyBorder(20, 0, 0, 0));
			pnContenedor.setLayout(new BorderLayout(10, 0));
			pnContenedor.setBackground(Color.decode("#e3e3e3"));
			pnContenedor.add(getPnCat(), BorderLayout.CENTER);
			pnContenedor.add(getPanelFiltro(), BorderLayout.WEST);
		}
		return pnContenedor;
	}
	private JPanel getPanelFiltro() {
		if (panelFiltro == null) {
			panelFiltro = new panelFiltro(parent);
		}
		return panelFiltro;
	}
	private JPanel getPnCat() {
		if (pnCat == null) {
			pnCat = new JPanel();
			pnCat.setLayout(new BorderLayout(0, 10));
			pnCat.setBackground(Color.decode("#e3e3e3"));
			pnCat.add(getPnCatalogo());
			pnCat.add(getPnTitulo(), BorderLayout.NORTH);
		}
		return pnCat;
	}
	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.setLayout(new BorderLayout(0, 0));
			pnTitulo.add(getLblCat(), BorderLayout.NORTH);
		}
		return pnTitulo;
	}
	private JLabel getLblCat() {
		if (lblCat == null) {
			lblCat = new JLabel("New label");
			lblCat.setFont(new Font("Tahoma", Font.BOLD, 13));
		}
		return lblCat;
	}

	private class keyFiltro extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				aplicaFiltros();
			}

		}
	}
	
	private class focusFiltro extends FocusAdapter{
		
		@Override
		public void focusLost(FocusEvent e){
			aplicaFiltros();
		}
		
	}
}
