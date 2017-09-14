package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import logica.Articulo;
import logica.ArticuloCarrito;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panelArticuloPanelCarrito extends JPanel {
	private JPanel pnImagen;
	private JPanel pnDatos;
	private JLabel lblImagen;
	private JLabel lblNombre;
	private JPanel pnDatos2;
	private JLabel lblPrecio;
	private JPanel pnUnidades;
	private JPanel pnContUnidades;
	private JButton btnMenos;
	private JTextField txtCantidad;
	private JButton btnMas;
	private JLabel lblTotalprecio;
	private JPanel pnS;
	private JPanel pnContenedorAll;
	
	private Articulo articulo;
	private VentanaPrincipal parent;
	private JPanel panel;
	private JButton btnEliminar;
	private ArticuloCarrito posCarrito;

	/**
	 * Create the panel.
	 */
	public panelArticuloPanelCarrito(ArticuloCarrito a, VentanaPrincipal v) {
		posCarrito = a;
		articulo = a.getArticulo();
		parent = v;
		
		setLayout(new BorderLayout(0, 0));
		add(getPnImagen(), BorderLayout.WEST);
		add(getPnDatos(), BorderLayout.CENTER);
		
		lblNombre.setText(a.getArticulo().getDenominacion());
		DecimalFormat df = new DecimalFormat("0.00");
		lblPrecio.setText(df.format(a.getArticulo().getPrecio()) + " €");
		txtCantidad.setText(String.valueOf(a.getCantidad()));
		lblTotalprecio.setText(parent.getTextos().getString("Total") + ":" + df.format(a.getArticulo().getPrecio()*a.getCantidad()) + " €");
		
		Image produc = adaptarImagen(a.getArticulo().getImagen());
		ImageIcon icon = new ImageIcon(produc);
		lblImagen.setIcon(icon);
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setBackground(Color.WHITE);
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblImagen());
			pnImagen.setPreferredSize(new Dimension(200, 200));
			
			pnImagen.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e){
					parent.changeVenta(articulo);
				}
				
			});
		}
		return pnImagen;
	}
	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBackground(Color.WHITE);
			pnDatos.setLayout(new BorderLayout(0, 0));
			pnDatos.add(getLblNombre(), BorderLayout.WEST);
			pnDatos.add(getPnDatos2(), BorderLayout.CENTER);
		}
		return pnDatos;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			lblImagen.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblImagen;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("New label");
			lblNombre.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblNombre;
	}
	private JPanel getPnDatos2() {
		if (pnDatos2 == null) {
			pnDatos2 = new JPanel();
			pnDatos2.setBackground(Color.WHITE);
			pnDatos2.setLayout(new BorderLayout(0, 0));
			pnDatos2.add(getLblPrecio(), BorderLayout.CENTER);
			pnDatos2.add(getPnUnidades(), BorderLayout.EAST);
		}
		return pnDatos2;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("New label");
			lblPrecio.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPrecio.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblPrecio;
	}
	private JPanel getPnUnidades() {
		if (pnUnidades == null) {
			pnUnidades = new JPanel();
			pnUnidades.setBackground(Color.WHITE);
			pnUnidades.setLayout(new BorderLayout(0, 0));
			pnUnidades.add(getPnContUnidades(), BorderLayout.CENTER);
			pnUnidades.add(getLblTotalprecio(), BorderLayout.SOUTH);
		}
		return pnUnidades;
	}
	private JPanel getPnContUnidades() {
		if (pnContUnidades == null) {
			pnContUnidades = new JPanel();
			pnContUnidades.setBackground(Color.WHITE);
			pnContUnidades.setLayout(new BorderLayout(0, 0));
			pnContUnidades.add(getPnS(), BorderLayout.CENTER);
		}
		return pnContUnidades;
	}
	private JButton getBtnMenos() {
		if (btnMenos == null) {
			btnMenos = new JButton("-");
			btnMenos.setBackground(Color.WHITE);
			btnMenos.setFont(new Font("Tahoma", Font.BOLD, 15));
			
			btnMenos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.getControlador().comprar(articulo, -1);
					parent.actualizarCarrito();
				}
			});
		}
		return btnMenos;
	}
	private JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
			txtCantidad.setColumns(3);
		}
		return txtCantidad;
	}
	private JButton getBtnMas() {
		if (btnMas == null) {
			btnMas = new JButton("+");
			btnMas.setBackground(Color.WHITE);
			btnMas.setFont(new Font("Tahoma", Font.BOLD, 15));
			
			btnMas.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					parent.getControlador().comprar(articulo, 1);
					parent.actualizarCarrito();	
				}
				
			});
			
		}
		return btnMas;
	}
	private JLabel getLblTotalprecio() {
		if (lblTotalprecio == null) {
			lblTotalprecio = new JLabel("TotalPrecio");
			lblTotalprecio.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTotalprecio.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblTotalprecio;
	}
	
	private Dimension getScaledDimension(Dimension imagen, Dimension objetivo) {

		int imgWidth = imagen.width;
		int imgHeigth = imagen.height;
		int objWidth = objetivo.width;
		int objHeigth = objetivo.height;
		int width = imgWidth;
		int heigth = imgHeigth;

		if (imgWidth > objWidth) {
			width = objWidth;
			heigth = (width * imgHeigth) / imgWidth;
		}

		if (heigth > objHeigth) {
			heigth = objHeigth;
			width = (width * imgWidth) / imgHeigth;
		}

		return new Dimension(width, heigth);
	}
	
	private Image adaptarImagen(String rutaImagen){
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		int width = imgOriginal.getWidth(null);
		int heigth = imgOriginal.getHeight(null);
		
		Dimension escala = getScaledDimension(new Dimension(width, heigth), new Dimension(190, 190));
		Image imgEscalada = imgOriginal.getScaledInstance((int)escala.getWidth(), (int)escala.getHeight(), Image.SCALE_SMOOTH);

		return imgEscalada;
	}
	private JPanel getPnS() {
		if (pnS == null) {
			pnS = new JPanel();
			pnS.setBorder(new EmptyBorder(0, 0, 50, 0));
			pnS.setBackground(Color.WHITE);
			pnS.setLayout(new BorderLayout(0, 0));
			pnS.add(getPnContenedorAll(), BorderLayout.SOUTH);
			pnS.add(getPanel(), BorderLayout.NORTH);
		}
		return pnS;
	}
	private JPanel getPnContenedorAll() {
		if (pnContenedorAll == null) {
			pnContenedorAll = new JPanel();
			pnContenedorAll.setBorder(new EmptyBorder(0, 0, 0, 10));
			pnContenedorAll.setBackground(Color.WHITE);
			pnContenedorAll.setLayout(new BorderLayout(0, 0));
			pnContenedorAll.add(getBtnMenos(), BorderLayout.WEST);
			pnContenedorAll.add(getTxtCantidad(), BorderLayout.CENTER);
			pnContenedorAll.add(getBtnMas(), BorderLayout.EAST);
		}
		return pnContenedorAll;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EmptyBorder(10, 10, 10, 10));
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getBtnEliminar(), BorderLayout.EAST);
		}
		return panel;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton("X");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i = parent.getControlador().getCarrito().indexOf(posCarrito);
					parent.getControlador().getCarrito().remove(i);
					parent.actualizarCarrito();
				}
			});
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(Color.WHITE);
			btnEliminar.setForeground(Color.BLACK);
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		return btnEliminar;
	}
}
