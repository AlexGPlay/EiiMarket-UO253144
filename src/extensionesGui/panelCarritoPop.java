package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;
import logica.ArticuloCarrito;
import java.awt.Color;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class panelCarritoPop extends JPanel {
	private JPanel pnArticulos;
	private JPanel pnVerMas;
	private JLabel lblVerCarritoCompleto;
	
	private VentanaPrincipal parent;
	private MouseAdapter adapter;

	/**
	 * Create the panel.
	 */
	public panelCarritoPop(VentanaPrincipal v, MouseAdapter a) {
		parent = v;
		adapter = a;
		
		setLayout(new BorderLayout(0, 0));
		add(getPnArticulos(), BorderLayout.CENTER);
		add(getPnVerMas(), BorderLayout.SOUTH);

	}
	
	public void colocaCarrito(ArrayList<ArticuloCarrito> carrito){
		pnArticulos.removeAll();
		int i=0;
		
		while(i<carrito.size() && i<5){
			panelArticulosCarrito temp = new panelArticulosCarrito(carrito.get(i), parent);
			temp.addMouseListener(adapter);
			temp.getTxtCantidad().addMouseListener(adapter);
			temp.getBtnMas().addMouseListener(adapter);
			temp.getBtnMenos().addMouseListener(adapter);
			temp.getLblImagen().addMouseListener(adapter);
			pnArticulos.add(temp);
			i++;
		}
		
		if(carrito.size()==0)
			lblVerCarritoCompleto.setText(parent.getTextos().getString("NoArticulos"));
		
		else
			lblVerCarritoCompleto.setText(parent.getTextos().getString("CarritoCompleto"));
	}

	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBackground(Color.WHITE);
			pnArticulos.setBorder(new EmptyBorder(0, 10, 0, 10));
			pnArticulos.setLayout(new GridLayout(0, 1, 0, 5));
		}
		return pnArticulos;
	}
	private JPanel getPnVerMas() {
		if (pnVerMas == null) {
			pnVerMas = new JPanel();
			pnVerMas.setBackground(Color.WHITE);
			pnVerMas.setLayout(new BorderLayout(0, 0));
			pnVerMas.add(getLblVerCarritoCompleto(), BorderLayout.CENTER);
		}
		return pnVerMas;
	}
	private JLabel getLblVerCarritoCompleto() {
		if (lblVerCarritoCompleto == null) {
			lblVerCarritoCompleto = new JLabel(parent.getTextos().getString("NoArticulos"));
			lblVerCarritoCompleto.setName("carrito");
			lblVerCarritoCompleto.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(pnArticulos.getComponentCount()>0){
						parent.changeCarrito();
					}
				}
			});
			
			lblVerCarritoCompleto.addMouseListener(adapter);
			lblVerCarritoCompleto.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblVerCarritoCompleto.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblVerCarritoCompleto;
	}
}
