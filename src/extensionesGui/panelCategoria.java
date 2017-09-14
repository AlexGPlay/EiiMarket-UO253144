package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class panelCategoria extends JPanel {
	private JPanel pnImagen;
	private JLabel lblCategoria;
	private JLabel lblImagen;

	/**
	 * Create the panel.
	 */
	public panelCategoria(String rutaImagen, String nombreCategoria) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPnImagen(), BorderLayout.WEST);
		add(getLblCategoria(), BorderLayout.CENTER);
		addMouseListener(new mouseEnter());
		
		lblCategoria.setText(nombreCategoria);
		lblImagen.setIcon(new ImageIcon(setImagenAdaptada(rutaImagen, 50, 50)));
	}
	
	public panelCategoria(String rutaImagen, String nombreCategoria, Dimension d) {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPnImagen(), BorderLayout.WEST);
		add(getLblCategoria(), BorderLayout.CENTER);
		addMouseListener(new mouseEnter());
		
		lblCategoria.setText(nombreCategoria);
		lblImagen.setIcon(new ImageIcon(setImagenAdaptada(rutaImagen, d.width, d.height)));
	}
	
	private Image setImagenAdaptada(String rutaImagen, int width, int height){
		this.setVisible(true);
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		return imgEscalada;
	}

	public JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setBackground(Color.WHITE);
			pnImagen.setPreferredSize(new Dimension(60, 60));
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.add(getLblImagen());
		}
		return pnImagen;
	}
	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel();
			lblCategoria.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCategoria;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel();
			lblImagen.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImagen;
	}
	
	private class mouseEnter extends MouseAdapter{
		
		@Override
		public void mouseEntered(MouseEvent e) {
			setBackground(Color.LIGHT_GRAY);
			pnImagen.setBackground(Color.LIGHT_GRAY);
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			setBackground(Color.WHITE);
			pnImagen.setBackground(Color.WHITE);
		}
		
	}
}
