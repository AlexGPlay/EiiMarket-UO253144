package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.VentanaPrincipal;
import logica.ArticuloCarrito;
import logica.RegaloCarrito;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class panelTerminar extends JPanel {
	private JPanel pnTxt;
	private JTextArea textArea;
	private JPanel pnNorth;
	private JLabel lblFinalizar;
	private JPanel pnCenter;
	private VentanaPrincipal parent;
	private JPanel pnGracias;
	private JLabel lblGracias;
	private JPanel pnC;
	private JPanel pnBotones;
	private JButton btnFinalizar;
	private JButton btnCancelar;
	private JPanel pnGuardar;
	private JLabel lblGuardar;
	private JPanel pnN;
	private String factura;
	private String dni;
	private int codigo;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public panelTerminar(VentanaPrincipal v) {
		parent = v;

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		setBackground(Color.decode("#e3e3e3"));
		add(getPnTxt(), BorderLayout.WEST);
		add(getPnNorth(), BorderLayout.NORTH);
		add(getPnCenter(), BorderLayout.CENTER);
	}

	private JPanel getPnTxt() {
		if (pnTxt == null) {
			pnTxt = new JPanel();
			pnTxt.setBackground(Color.WHITE);
			pnTxt.setLayout(new BorderLayout(0, 0));
			pnTxt.add(getScrollPane(), BorderLayout.CENTER);
		}
		return pnTxt;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
			textArea.setColumns(60);
			textArea.setTabSize(0);
			textArea.setMargin(new Insets(10, 10, 10, 10));
		}
		return textArea;
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setBackground(Color.WHITE);
			pnNorth.setLayout(new BorderLayout(0, 0));
			pnNorth.add(getLblFinalizar(), BorderLayout.NORTH);
		}
		return pnNorth;
	}

	private JLabel getLblFinalizar() {
		if (lblFinalizar == null) {
			lblFinalizar = new JLabel(parent.getTextos().getString("Finalizar"));
			lblFinalizar.setFont(new Font("Lucida Grande", Font.BOLD, 17));
			lblFinalizar.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblFinalizar;
	}

	private JPanel getPnCenter() {
		if (pnCenter == null) {
			pnCenter = new JPanel();
			pnCenter.setBackground(Color.decode("#e3e3e3"));
			pnCenter.setLayout(new BorderLayout(0, 10));
			pnCenter.add(getPnGracias(), BorderLayout.NORTH);
			pnCenter.add(getPnC(), BorderLayout.CENTER);
		}
		return pnCenter;
	}

	public void cargaDatos(String nombre, String apellidos, String nif, int codigo) {
		Date now = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat("0.00");
		dni = nif;
		this.codigo = codigo;

		String cabecera = "\t" + "FACTURA" + " - " + "EII MARKET" + " - " + dateFormat.format(now) + "\n";
		String separador = "-----------------------------------------------------------------------------\n";

		String datos = "NOMBRE: " + nombre + " " + apellidos + "\t\tNIF: " + nif + "\n";
		String relacionProd = "\n\t** RELACION DE ARTICULOS COMPRADOS **" + "\n\n";
		String tituloProd = "Denominación / Código / Unidades / Total artículo" + "\n";
		String separador2 = "---------------------------------------------" + "\n";

		String compras = "";

		ArrayList<ArticuloCarrito> carrito = parent.getControlador().getCarrito();

		for (int i = 0; i < carrito.size(); i++) {
			compras += "* ";
			compras += carrito.get(i).getArticulo().getDenominacion() + " / ";
			compras += carrito.get(i).getArticulo().getCodigo() + " / ";
			compras += carrito.get(i).getCantidad() + " / ";
			compras += df.format(carrito.get(i).getArticulo().getPrecio() * carrito.get(i).getCantidad());

			if (carrito.get(i).getArticulo().getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
				compras += " (*)";

			compras += "\n";
		}

		String aclaracion = "\n(*) Artículo/s con descuento aplicado al 10%\n";
		String regalos = "";

		if (parent.getControlador().getRegalos().size() > 0) {
			regalos += "\n\t** RELACION DE ARTICULOS REGALADOS **" + "\n\n";
			regalos += "Denominación / Código / Cantidad / Puntos\n";
			regalos += "--------------------\n";

			ArrayList<RegaloCarrito> regalosArray = parent.getControlador().getRegalos();

			for (int i = 0; i < regalosArray.size(); i++) {
				regalos += "* ";
				regalos += regalosArray.get(i).getRegalo().getDenominacion() + " / ";
				regalos += regalosArray.get(i).getRegalo().getCodigo() + " / ";
				regalos += regalosArray.get(i).getCantidad() + " / ";
				regalos += regalosArray.get(i).getCantidad() * regalosArray.get(i).getRegalo().getPuntos() + "\n\n";
			}

		}

		String totalArticulos = "\nTotal Artículos. . . . . . . . . . . . . . . . . . . . . . . . . "
				+ df.format(parent.getControlador().getPrecioTotal()) + " €\n";
		String totalPuntos;
		String totalFactura;

		if (parent.getControlador().getLogged() != null && codigo == 0) {
			totalPuntos = "Descuento por puntos . . . . . . . . . . . . . . . . . . . . . . "
					+ parent.getControlador().getPuntosCompra() + "€\n";

			if (parent.getControlador().getPrecioTotal() - parent.getControlador().getPuntosCompra() < 0)
				totalFactura = "TOTAL FACTURA. . . . . . . . . . . . . . . . . . . . . . . . . . " + 0 + " €";

			else
				totalFactura = "TOTAL FACTURA. . . . . . . . . . . . . . . . . . . . . . . . . . "
						+ df.format(
								parent.getControlador().getPrecioTotal() - parent.getControlador().getPuntosCompra())
						+ " €";
		}

		else {
			totalPuntos = "Descuento por puntos . . . . . . . . . . . . . . . . . . . . . . 0 €\n";
			totalFactura = "TOTAL FACTURA. . . . . . . . . . . . . . . . . . . . . . . . . . "
					+ df.format(parent.getControlador().getPrecioTotal()) + " €";
		}

		factura = cabecera + separador + datos + separador + relacionProd + tituloProd + separador2 + compras
				+ aclaracion + regalos + totalArticulos + totalPuntos + totalFactura;

		textArea.setTabSize(10);
		textArea.setText(factura);
	}

	private JPanel getPnGracias() {
		if (pnGracias == null) {
			pnGracias = new JPanel();
			pnGracias.setBackground(Color.WHITE);
			pnGracias.setLayout(new BorderLayout(0, 10));
			pnGracias.add(getLblGracias(), BorderLayout.NORTH);
		}
		return pnGracias;
	}

	private JLabel getLblGracias() {
		if (lblGracias == null) {
			lblGracias = new JLabel(parent.getTextos().getString("Gracias"));
			lblGracias.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblGracias.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblGracias;
	}

	private JPanel getPnC() {
		if (pnC == null) {
			pnC = new JPanel();
			pnC.setLayout(new BorderLayout(0, 10));
			pnC.setBackground(Color.decode("#e3e3e3"));
			pnC.add(getPnGuardar(), BorderLayout.NORTH);
			pnC.add(getPnN(), BorderLayout.CENTER);
		}
		return pnC;
	}

	private JPanel getPnBotones() {
		if (pnBotones == null) {
			pnBotones = new JPanel();
			pnBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnBotones.setBackground(Color.WHITE);
			pnBotones.setLayout(new BorderLayout(10, 0));
			pnBotones.add(getBtnCancelar(), BorderLayout.EAST);
			pnBotones.add(getBtnFinalizar(), BorderLayout.CENTER);
		}
		return pnBotones;
	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton(parent.getTextos().getString("Finalizar"));
			btnFinalizar.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnFinalizar.setForeground(Color.WHITE);
			btnFinalizar.setBackground(Color.decode("#232f3e"));
			btnFinalizar.setPreferredSize(new Dimension(230, 60));

			btnFinalizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					// Usar en esta
					if (codigo == 0) {
						int p = parent.getControlador().getPuntosGanados();
						parent.getControlador().getLogged().setPuntos((parent.getControlador().getLogged().getPuntos()
								- parent.getControlador().getPuntosCompra()) + p);
					}

					// Canjear regalo
					else if (codigo == 1) {
						int p = parent.getControlador().getPuntosGastados();
						parent.getControlador().getLogged().setPuntos(parent.getControlador().getLogged().getPuntos()
								- p + parent.getControlador().getPuntosGanados());
					}

					// Guardar
					else if (codigo == 2) {
						int p = parent.getControlador().getPuntosGanados();
						parent.getControlador().getLogged()
								.setPuntos(parent.getControlador().getLogged().getPuntos() + p);
					}

					parent.compraFinalizada();
				}
			});
		}
		return btnFinalizar;
	}

	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton(parent.getTextos().getString("Cancelar"));
			btnCancelar.setBorder(new LineBorder(Color.GRAY, 2, true));
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setForeground(Color.GRAY);
			btnCancelar.setPreferredSize(new Dimension(230, 60));
			btnCancelar.setFont(new Font("Lucida Grande", Font.BOLD, 16));

			btnCancelar.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					btnCancelar.setBackground(Color.GRAY);
					btnCancelar.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnCancelar.setBackground(Color.WHITE);
					btnCancelar.setForeground(Color.GRAY);
				}

			});

			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int i = JOptionPane.showConfirmDialog(parent, parent.getTextos().getString("PaneCanc"),
							parent.getTextos().getString("Continuar"), JOptionPane.YES_NO_OPTION);

					if (i == JOptionPane.YES_OPTION) {
						parent.changePrincipal();
					}
				}
			});

		}
		return btnCancelar;
	}

	private JPanel getPnGuardar() {
		if (pnGuardar == null) {
			pnGuardar = new JPanel();
			pnGuardar.setBackground(Color.WHITE);
			pnGuardar.setLayout(new BorderLayout(0, 0));
			pnGuardar.add(getLblGuardar(), BorderLayout.CENTER);
		}
		return pnGuardar;
	}

	private JLabel getLblGuardar() {
		if (lblGuardar == null) {
			lblGuardar = new JLabel(parent.getTextos().getString("GuardarFac"));
			lblGuardar.setIcon(new ImageIcon(panelTerminar.class.getResource("/imgAplicacion/Notepad30.png")));
			lblGuardar.setBorder(new EmptyBorder(10, 10, 10, 10));

			lblGuardar.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					parent.getControlador().guardaFactura(factura, dni);
					JOptionPane.showMessageDialog(parent, parent.getTextos().getString("FactGuardada"));
				}

			});

		}
		return lblGuardar;
	}

	private JPanel getPnN() {
		if (pnN == null) {
			pnN = new JPanel();
			pnN.setBackground(Color.decode("#e3e3e3"));
			pnN.setLayout(new BorderLayout(0, 0));
			pnN.add(getPnBotones(), BorderLayout.NORTH);
		}
		return pnN;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
}
