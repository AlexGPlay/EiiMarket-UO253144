package extensionesGui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;
import logica.Articulo;

@SuppressWarnings("serial")
public class panelArticuloBarra extends JPanel {
	private JPanel pnImagen;
	private JLabel lblImagen;
	private JPanel pnTexto;
	private JLabel lblNombre;
	private JLabel lblPrecio;

	private VentanaPrincipal parent;
	/**
	 * Create the panel.
	 */
	
	Articulo art;
	
	public panelArticuloBarra(Articulo a, VentanaPrincipal v) {
		parent = v;
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPnImagen(), BorderLayout.WEST);
		add(getPnTexto(), BorderLayout.CENTER);
		addMouseListener(new mouseHandler());
		
		art = a;
		actualiza();
	}
	
	private void actualiza(){
		lblNombre.setText(art.getDenominacion());
		DecimalFormat df = new DecimalFormat("0.00");
		lblPrecio.setText(df.format(art.getPrecio()) + " €");
		
		Image produc = adaptarImagen(art.getImagen());
		ImageIcon icon = new ImageIcon(produc);
		lblImagen.setIcon(icon);
	}

	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnImagen.setBackground(Color.WHITE);
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblImagen());
		}
		return pnImagen;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
		}
		return lblImagen;
	}
	private JPanel getPnTexto() {
		if (pnTexto == null) {
			pnTexto = new JPanel();
			pnTexto.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnTexto.setBackground(Color.WHITE);
			pnTexto.setLayout(new BorderLayout(0, 0));
			pnTexto.add(getLblNombre(), BorderLayout.NORTH);
			pnTexto.add(getLblPrecio(), BorderLayout.SOUTH);
		}
		return pnTexto;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("New label");
		}
		return lblNombre;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("New label");
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
	
	private class mouseHandler extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			parent.changeVenta(art);
		}
		
	}
}
