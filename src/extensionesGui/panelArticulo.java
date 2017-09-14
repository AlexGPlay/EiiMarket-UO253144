package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.border.LineBorder;

import gui.VentanaPrincipal;
import logica.Articulo;

@SuppressWarnings("serial")
public class panelArticulo extends JPanel {
	private JPanel pnImagen;
	private JPanel pnPrecio;
	private JLabel lblNombre;
	private JLabel lblPrecio;
	private JLabel lblFoto;
	
	private Articulo art;
	private VentanaPrincipal parent;
	private JPanel pnPromocion;
	private JLabel lblPromocion;

	/**
	 * Create the panel.
	 */

	public panelArticulo(Articulo articulo, VentanaPrincipal v, boolean rebajado){
		parent = v;
		
		setBorder(new LineBorder(Color.WHITE, 1, true));
		setLayout(new BorderLayout(0, 0));
		add(getPnImagen(), BorderLayout.CENTER);
		add(getPnPrecio(), BorderLayout.SOUTH);
		addMouseListener(new mouseHandler());
		
		art = articulo;
		
		if(rebajado) {
			Image rebaja = adaptarImagen("/imgAplicacion/oferta.png", 100, 100);
			ImageIcon rebajaIcon = new ImageIcon(rebaja);
			
			getLblPromocion().setIcon(rebajaIcon);
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		lblPrecio.setText(df.format(art.getPrecio()) + " €");
		lblNombre.setText(art.getDenominacion());

		Image produc = adaptarImagen(art.getImagen(), 200, 200);
		ImageIcon icon = new ImageIcon(produc);
		lblFoto.setIcon(icon);
		
		pnImagen.setMinimumSize(new Dimension(100, 100));
		lblFoto.setMinimumSize(new Dimension(100,100));
		add(getPnPromocion(), BorderLayout.NORTH);
	}

	private Image adaptarImagen(String rutaImagen, int x, int y){
		//pnImagen.setVisible(true);
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		int width = imgOriginal.getWidth(null);
		int heigth = imgOriginal.getHeight(null);
		
		Dimension escala = getScaledDimension(new Dimension(width, heigth), new Dimension(x, y));
		Image imgEscalada = imgOriginal.getScaledInstance((int)escala.getWidth(), (int)escala.getHeight(), Image.SCALE_SMOOTH);

		return imgEscalada;
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setBackground(Color.WHITE);
			pnImagen.setPreferredSize(new Dimension(210, 210));
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblFoto(), BorderLayout.CENTER);
		}
		return pnImagen;
	}
	private JPanel getPnPrecio() {
		if (pnPrecio == null) {
			pnPrecio = new JPanel();
			pnPrecio.setBackground(Color.WHITE);
			pnPrecio.setLayout(new BorderLayout(0, 0));
			pnPrecio.add(getLblNombre(), BorderLayout.NORTH);
			pnPrecio.add(getLblPrecio(), BorderLayout.CENTER);
		}
		return pnPrecio;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("New label");
			lblNombre.setFont(new Font("Arial", Font.PLAIN, 13));
			lblNombre.setForeground(Color.DARK_GRAY);
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNombre;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("New label");
			lblPrecio.setFont(new Font("Arial", Font.BOLD, 13));
			lblPrecio.setBorder(new EmptyBorder(10, 0, 0, 0));
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPrecio;
	}
	private JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("");
			lblFoto.setBackground(Color.WHITE);
			lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblFoto;
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

	
	private class mouseHandler extends MouseAdapter{
		
		@Override
		public void mouseEntered(MouseEvent e){
			setBorder(new LineBorder(Color.decode("#232f3e"), 1, true));
		}
		
		@Override
		public void mouseExited(MouseEvent e){
			setBorder(new LineBorder(Color.WHITE, 1, true));
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			parent.changeVenta(art);
		}
		
	}

	private JPanel getPnPromocion() {
		if (pnPromocion == null) {
			pnPromocion = new JPanel();
			pnPromocion.setBackground(Color.WHITE);
			pnPromocion.setLayout(new BorderLayout(0, 0));
			pnPromocion.add(getLblPromocion(), BorderLayout.NORTH);
		}
		return pnPromocion;
	}
	private JLabel getLblPromocion() {
		if (lblPromocion == null) {
			lblPromocion = new JLabel("");
		}
		return lblPromocion;
	}
}
