package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logica.ArticuloCarrito;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;

@SuppressWarnings("serial")
public class panelArticuloFinalizar extends JPanel {
	private JPanel pnImagen;
	private JLabel lblImagen;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JPanel pnCont;
	private JLabel lblUnidades;
	private JPanel panel;
	private JPanel panel_1;
	private VentanaPrincipal parent;
	
	/**
	 * Create the panel.
	 */
	public panelArticuloFinalizar(ArticuloCarrito a, VentanaPrincipal v) {
		parent = v;
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.CENTER);
		add(getPnImagen(), BorderLayout.WEST);
		
		lblNombre.setText(a.getArticulo().getDenominacion());
		
		DecimalFormat df = new DecimalFormat("0.00");
		lblPrecio.setText(String.valueOf(df.format(a.getArticulo().getPrecio()*a.getCantidad())) + " €  ");
		
		Image produc = adaptarImagen(a.getArticulo().getImagen());
		ImageIcon icon = new ImageIcon(produc);
		lblImagen.setIcon(icon);
		
		if(a.getCantidad()==1)
			lblUnidades.setText(a.getCantidad() + " " + parent.getTextos().getString("Unidad"));
		
		else
			lblUnidades.setText(a.getCantidad() + " " +parent.getTextos().getString("Unidades"));
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnImagen.setPreferredSize(new Dimension(120, 120));
			pnImagen.setBackground(Color.WHITE);
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblImagen(), BorderLayout.CENTER);
		}
		return pnImagen;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImagen;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("New label");
			lblNombre.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblNombre.setBackground(Color.WHITE);
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNombre;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("New label");
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrecio.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblPrecio;
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
		
		Dimension escala = getScaledDimension(new Dimension(width, heigth), new Dimension(100,100));
		Image imgEscalada = imgOriginal.getScaledInstance((int)escala.getWidth(), (int)escala.getHeight(), Image.SCALE_SMOOTH);

		return imgEscalada;
	}
	private JPanel getPnCont() {
		if (pnCont == null) {
			pnCont = new JPanel();
			pnCont.setBackground(Color.WHITE);
			pnCont.setLayout(new BorderLayout(0, 0));
			pnCont.add(getLblUnidades(), BorderLayout.CENTER);
			pnCont.add(getPanel_1(), BorderLayout.EAST);
		}
		return pnCont;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel("New label");
			lblUnidades.setHorizontalAlignment(SwingConstants.TRAILING);
			lblUnidades.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblUnidades;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblNombre(), BorderLayout.WEST);
			panel.add(getPnCont(), BorderLayout.CENTER);
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getLblPrecio(), BorderLayout.CENTER);
		}
		return panel_1;
	}
}
