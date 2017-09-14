package extensionesGui;

import javax.swing.JPanel;

import gui.VentanaPrincipal;
import logica.ArticuloCarrito;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panelCarrito extends JPanel {
	private JPanel pnArticulos;
	private VentanaPrincipal parent;
	private panelRealizarPedido pedido;
	private JPanel pnArticulosCont;
	private JPanel pnCont;
	private JScrollPane scrollPane;
	private JPanel pnNorth;
	private JLabel lblCarrito;
	private JPanel pnVaciar;
	private JLabel lblVaciar;
	private JPanel pnN2;
	private Dimension pred;

	/**
	 * Create the panel.
	 */
	public panelCarrito(VentanaPrincipal v) {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		parent = v;

		setBackground(Color.decode("#e3e3e3"));
		setLayout(new BorderLayout(10, 0));
		add(getScrollPane(), BorderLayout.CENTER);
	}

	public void cargaCarrito() {
		ArrayList<ArticuloCarrito> carrito = parent.getControlador().getCarrito();
		pedido.actualizaTotal(parent.getControlador().getPrecioTotal());

		if (pnArticulosCont.getComponentCount() > 0 && pnArticulosCont.getComponent(0) != null)
			pred = pnArticulosCont.getComponent(0).getSize();

		pnArticulosCont.removeAll();

		if (carrito.size() == 0) {
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(10, 10));

			if (pred != null)
				panel.setPreferredSize(pred);

			JLabel temp = new JLabel(parent.getTextos().getString("NoArticulos"));
			temp.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			temp.setBorder(new EmptyBorder(10, 10, 10, 10));
			temp.setHorizontalAlignment(SwingConstants.CENTER);

			pedido.getBtnRealizar().setEnabled(false);
			panel.add(temp, BorderLayout.CENTER);

			pnArticulosCont.add(panel);
		}

		else {
			pedido.getBtnRealizar().setEnabled(true);
		}

		for (int i = 0; i < carrito.size(); i++) {
			pnArticulosCont.add(new panelArticuloPanelCarrito(carrito.get(i), parent));
		}
	}

	private JPanel getPnArticulos() {
		if (pnArticulos == null) {
			pnArticulos = new JPanel();
			pnArticulos.setBackground(Color.decode("#e3e3e3"));
			pnArticulos.setLayout(new BorderLayout(10, 10));
			pnArticulos.add(getPnArticulosCont(), BorderLayout.NORTH);
			pnArticulos.add(getPnVaciar(), BorderLayout.CENTER);
		}
		return pnArticulos;
	}

	private JPanel getPedido() {
		if (pedido == null) {
			pedido = new panelRealizarPedido(parent);
			pedido.getBtnRealizar().setText(parent.getTextos().getString("Pedir"));
			pedido.getBtnRealizar().addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (parent.getControlador().loggedClient == null) {
						int i = JOptionPane.showConfirmDialog(parent, parent.getTextos().getString("NoRegistro"),
								parent.getTextos().getString("Continuar"), JOptionPane.YES_NO_OPTION);

						if (i == JOptionPane.YES_OPTION) {
							((panelDatosFinalizar) ((panelFinalizarCompra) parent.getFinalizar()).getFinalizar())
									.cargaDatos(parent.getControlador().getLogged());
							parent.changeFinalizarCompra();
						}

						else
							parent.changeRegistro();
					}

					else {
						((panelDatosFinalizar) ((panelFinalizarCompra) parent.getFinalizar()).getFinalizar())
								.cargaDatos(parent.getControlador().getLogged());
						parent.changeFinalizarCompra();
					}
				}
			});
		}
		return pedido;
	}

	private JPanel getPnArticulosCont() {
		if (pnArticulosCont == null) {
			pnArticulosCont = new JPanel();
			pnArticulosCont.setLayout(new GridLayout(0, 1, 0, 10));
		}
		return pnArticulosCont;
	}

	private JPanel getPnCont() {
		if (pnCont == null) {
			pnCont = new JPanel();
			pnCont.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnCont.setLayout(new BorderLayout(10, 10));
			pnCont.setBackground(Color.decode("#e3e3e3"));
			pnCont.add(getPnArticulos(), BorderLayout.WEST);
			pnCont.add(getPedido(), BorderLayout.CENTER);
			pnCont.add(getPnNorth(), BorderLayout.NORTH);
		}
		return pnCont;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBorder(null);
			scrollPane.setViewportView(getPnCont());
		}
		return scrollPane;
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnNorth.setBackground(Color.WHITE);
			pnNorth.setLayout(new BorderLayout(0, 0));
			pnNorth.add(getLblCarrito(), BorderLayout.NORTH);
		}
		return pnNorth;
	}

	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel(parent.getTextos().getString("Carrito"));
			lblCarrito.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		}
		return lblCarrito;
	}

	private JPanel getPnVaciar() {
		if (pnVaciar == null) {
			pnVaciar = new JPanel();
			pnVaciar.setBackground(Color.decode("#e3e3e3"));
			pnVaciar.setLayout(new BorderLayout(0, 0));
			pnVaciar.add(getPnN2(), BorderLayout.NORTH);
		}
		return pnVaciar;
	}

	private JLabel getLblVaciar() {
		if (lblVaciar == null) {
			lblVaciar = new JLabel(parent.getTextos().getString("Vaciar"));
			lblVaciar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					vaciarCarrito();
				}
			});
			lblVaciar.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblVaciar;
	}

	private void vaciarCarrito() {
		parent.getControlador().getCarrito().clear();
		parent.actualizarCarrito();
	}

	private JPanel getPnN2() {
		if (pnN2 == null) {
			pnN2 = new JPanel();
			pnN2.setBackground(Color.WHITE);
			pnN2.setLayout(new BorderLayout(0, 0));
			pnN2.add(getLblVaciar(), BorderLayout.CENTER);
		}
		return pnN2;
	}
}
