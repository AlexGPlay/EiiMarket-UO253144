package extensionesGui;

import javax.swing.JPanel;

import gui.VentanaPrincipal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class panelNorthCategorias extends JPanel {

	
	private clickCategoria cC;
	private VentanaPrincipal  parent;
	
	public panelNorthCategorias(VentanaPrincipal v) {
		cC = new clickCategoria();
		parent = v;
		
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 5, 0, 0));
		setBackground(Color.decode("#ebeced"));

		colocarPaneles();
	}
	
	private void colocarPaneles() {
		panelCategoria cat1 = new panelCategoria("/imgAplicacion/iconoConsola.png", parent.getTextos().getString("Consolas"), new Dimension(30, 30));
		cat1.setBorder(new LineBorder(new Color(0, 0, 0)));
		cat1.setName("0");
		cat1.addMouseListener(cC);
		add(cat1);
		
		panelCategoria cat2 = new panelCategoria("/imgAplicacion/iconoCamara.png", parent.getTextos().getString("Fotografia"), new Dimension(30, 30));
		cat2.setBorder(new LineBorder(new Color(0, 0, 0)));
		cat2.setName("1");
		cat2.addMouseListener(cC);
		add(cat2);
		
		panelCategoria cat3 = new panelCategoria("/imgAplicacion/iconoMovil.png", parent.getTextos().getString("Telefonia"), new Dimension(30, 30));
		cat3.setBorder(new LineBorder(new Color(0, 0, 0)));
		cat3.setName("2");
		cat3.addMouseListener(cC);
		add(cat3);
		
		panelCategoria cat4 = new panelCategoria("/imgAplicacion/iconoOrdenador.png", parent.getTextos().getString("Ordenadores"), new Dimension(30, 30));
		cat4.setBorder(new LineBorder(new Color(0, 0, 0)));
		cat4.setName("3");
		cat4.addMouseListener(cC);
		add(cat4);
		
		panelCategoria cat5 = new panelCategoria("/imgAplicacion/iconoVideovigilancia.png", parent.getTextos().getString("Videovigilancia"), new Dimension(30, 30));
		cat5.setBorder(new LineBorder(new Color(0, 0, 0)));
		cat5.setName("4");
		cat5.addMouseListener(cC);
		add(cat5);
	}
	
	private class clickCategoria extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			JPanel temp = (JPanel)e.getSource();
			temp.setBackground(Color.WHITE);
			((panelCategoria)temp).getPnImagen().setBackground(Color.WHITE);
			int i = Integer.valueOf(temp.getName());
			
			parent.getControlador().generarFiltroCategoria(i);
			String[] str = {"0", temp.getName()};
			
			panelCatalogo temp2 = (panelCatalogo)parent.getCatalogo();
			temp2.generarCatalogo(parent.getControlador().getFiltroCategoria(), str);
			parent.changeCatalogo();
		}
		
	}

}
