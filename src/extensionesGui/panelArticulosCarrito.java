package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;
import logica.Articulo;
import logica.ArticuloCarrito;

import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panelArticulosCarrito extends JPanel {
	private JPanel pnFoto;
	private JPanel pnDatos;
	private JPanel pnCantidad;
	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblTotalPrecio;
	private JPanel pnEliminarProducto;
	private JPanel pnCantidades;
	private JPanel pnBar;
	private JTextField txtCantidad;
	private JButton btnMenos;
	private JButton btnMas;

	private VentanaPrincipal parent;
	private Articulo articulo;

	/**
	 * Create the panel.
	 */
	public panelArticulosCarrito(ArticuloCarrito art, VentanaPrincipal v) {
		parent = v;
		articulo = art.getArticulo();

		setLayout(new BorderLayout(0, 0));
		setName("");
		add(getPnFoto(), BorderLayout.WEST);
		add(getPnDatos(), BorderLayout.CENTER);
		add(getPnCantidad(), BorderLayout.EAST);

		createPanel(art);
	}

	private void createPanel(ArticuloCarrito a) {

		lblNombre.setText(a.getArticulo().getDenominacion());
		DecimalFormat df = new DecimalFormat("0.00");
		lblTotalPrecio.setText(df.format(a.getArticulo().getPrecio() * a.getCantidad()) + "€");

		txtCantidad.setText(String.valueOf(a.getCantidad()));

		Image produc = adaptarImagen(a.getArticulo().getImagen());
		ImageIcon icon = new ImageIcon(produc);
		lblImagen.setIcon(icon);

	}

	private JPanel getPnFoto() {
		if (pnFoto == null) {
			pnFoto = new JPanel();
			pnFoto.setBackground(Color.WHITE);
			pnFoto.setLayout(new BorderLayout(0, 0));
			pnFoto.add(getLblImagen());
		}
		return pnFoto;
	}

	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBackground(Color.WHITE);
			pnDatos.setBorder(new EmptyBorder(0, 10, 10, 10));
			pnDatos.setLayout(new BorderLayout(0, 0));
			pnDatos.add(getLblNombre(), BorderLayout.CENTER);
			pnDatos.add(getLblTotalPrecio(), BorderLayout.SOUTH);
		}
		return pnDatos;
	}

	private JPanel getPnCantidad() {
		if (pnCantidad == null) {
			pnCantidad = new JPanel();
			pnCantidad.setBackground(Color.WHITE);
			pnCantidad.setLayout(new BorderLayout(0, 0));
			pnCantidad.add(getPnEliminarProducto(), BorderLayout.NORTH);
			pnCantidad.add(getPnCantidades(), BorderLayout.CENTER);
		}
		return pnCantidad;
	}

	public JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			lblImagen.setName("");

			lblImagen.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					parent.changeVenta(articulo);
				}

			});
		}
		return lblImagen;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("New label");
		}
		return lblNombre;
	}

	private JLabel getLblTotalPrecio() {
		if (lblTotalPrecio == null) {
			lblTotalPrecio = new JLabel("New label");
		}
		return lblTotalPrecio;
	}

	private JPanel getPnEliminarProducto() {
		if (pnEliminarProducto == null) {
			pnEliminarProducto = new JPanel();
			pnEliminarProducto.setBackground(Color.WHITE);
			pnEliminarProducto.setLayout(new BorderLayout(0, 0));
		}
		return pnEliminarProducto;
	}

	private JPanel getPnCantidades() {
		if (pnCantidades == null) {
			pnCantidades = new JPanel();
			pnCantidades.setBackground(Color.WHITE);
			pnCantidades.setLayout(new BorderLayout(0, 0));
			pnCantidades.add(getPanel_1(), BorderLayout.NORTH);
		}
		return pnCantidades;
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

	private Image adaptarImagen(String rutaImagen) {
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		int width = imgOriginal.getWidth(null);
		int heigth = imgOriginal.getHeight(null);

		Dimension escala = getScaledDimension(new Dimension(width, heigth), new Dimension(100, 100));
		Image imgEscalada = imgOriginal.getScaledInstance((int) escala.getWidth(), (int) escala.getHeight(),
				Image.SCALE_SMOOTH);

		return imgEscalada;
	}

	private JPanel getPanel_1() {
		if (pnBar == null) {
			pnBar = new JPanel();
			pnBar.setBorder(new EmptyBorder(40, 0, 0, 0));
			pnBar.setBackground(Color.WHITE);
			pnBar.setLayout(new BorderLayout(0, 0));
			pnBar.add(getTxtCantidad(), BorderLayout.CENTER);
			pnBar.add(getBtnMenos(), BorderLayout.WEST);
			pnBar.add(getBtnMas(), BorderLayout.EAST);
		}
		return pnBar;
	}

	public JTextField getTxtCantidad() {
		if (txtCantidad == null) {
			txtCantidad = new JTextField();
			txtCantidad.setName("txt");
			txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
			txtCantidad.setColumns(3);
		}
		return txtCantidad;
	}

	public JButton getBtnMenos() {
		if (btnMenos == null) {
			btnMenos = new JButton("-");
			btnMenos.setBackground(Color.WHITE);
			btnMenos.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnMenos.setName("menos");
			btnMenos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.getControlador().comprar(articulo, -1);
					parent.actualizarCarrito();
				}
			});
		}
		return btnMenos;
	}

	public JButton getBtnMas() {
		if (btnMas == null) {
			btnMas = new JButton("+");
			btnMas.setBackground(Color.WHITE);
			btnMas.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnMas.setName("mas");
			btnMas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.getControlador().comprar(articulo, 1);
					parent.actualizarCarrito();
				}
			});
		}
		return btnMas;
	}
}
