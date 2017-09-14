package extensionesGui;

import javax.swing.JPanel;
import java.awt.Color;

import gui.VentanaPrincipal;
import logica.Articulo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class panelBarra extends JPanel {
	private JPanel pnArticulos;
	private JPanel pnVerMas;
	private JLabel lblVerMsArticulos;
	private VentanaPrincipal parent;
	
	/**
	 * Create the panel.
	 */
	public panelBarra(VentanaPrincipal v) {
		
		parent = v;
		
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getPnArticulos(), BorderLayout.CENTER);
		add(getPnVerMas(), BorderLayout.SOUTH);
	}
	
	public void colocaArticulos(ArrayList<Articulo> articulos){
		pnArticulos.removeAll();
		int i = 0;
		
		while(i<articulos.size() && i<4){
			pnArticulos.add(new panelArticuloBarra(articulos.get(i), parent));
			i++;
		}
		
		while(i<3) {
			JPanel temp = new JPanel();
			temp.setBackground(Color.WHITE);
			pnArticulos.add(temp);
			i++;
		}
		
	}

	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBackground(Color.WHITE);
			pnArticulos.setLayout(new GridLayout(0, 2, 0, 0));
		}
		return pnArticulos;
	}
	private JPanel getPnVerMas() {
		if (pnVerMas == null) {
			pnVerMas = new JPanel();
			pnVerMas.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnVerMas.setBackground(Color.WHITE);
			pnVerMas.setLayout(new BorderLayout(0, 0));
			pnVerMas.add(getLblVerMsArticulos(), BorderLayout.CENTER);
		}
		return pnVerMas;
	}
	public JLabel getLblVerMsArticulos() {
		if (lblVerMsArticulos == null) {
			lblVerMsArticulos = new JLabel(parent.getTextos().getString("MasArticulos"));
			lblVerMsArticulos.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblVerMsArticulos;
	}
	
	public void setArticulos(String s){
		lblVerMsArticulos.setText(s);
	}
	
}
