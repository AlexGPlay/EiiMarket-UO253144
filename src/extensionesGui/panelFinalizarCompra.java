package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import gui.VentanaPrincipal;
import logica.ArticuloCarrito;
import logica.Regalo;
import logica.RegaloCarrito;

import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class panelFinalizarCompra extends JPanel {
	private JPanel pnCentral;
	private JPanel pnCarrito;
	private JLabel lblTituloC;
	private JPanel pnProductos;
	private panelDatosFinalizar finalizar;
	private JScrollPane scrollPane;

	private VentanaPrincipal parent;
	private JPanel pnSubtotal;
	private JLabel lblTotal;
	private JPanel pnTituloN;
	private JPanel pnNorth;
	private JLabel lblFinalizarCompra;
	private JPanel pnEast;
	private JPanel pnTituloDatos;
	private JLabel lblTituloD;
	private JPanel pnCenterEast;
	private JPanel pnSouth;
	private JPanel pnTituloPuntos;
	private JLabel lblPuntos;
	private JPanel pnPuntos;
	private JPanel pnBoton;
	private JRadioButton rdbtnUsarPuntos;
	private JRadioButton rdbtnCanjear;
	private JRadioButton rdbtnGuardar;
	private JPanel pnRegalos;
	private JPanel pnTituloRegalos;
	private JLabel lblRegalos;
	private JPanel pnCompRegalos;
	private JScrollPane scrollPane_1;
	private int puntosTotales = 0;

	private JButton cont;
	private JButton btnCancel;
	private JPanel pnBotonInt;

	/**
	 * Create the panel.
	 */
	public panelFinalizarCompra(VentanaPrincipal v) {
		parent = v;

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(10, 10));
		setBackground(Color.decode("#e3e3e3"));
		add(getPnRegalos(), BorderLayout.EAST);
		add(getPnCentral());
		add(getPnNorth(), BorderLayout.NORTH);
	}

	public void actualizaDatos() {
		ArrayList<ArticuloCarrito> carrito = parent.getControlador().getCarrito();
		pnProductos.removeAll();

		for (int i = 0; i < carrito.size(); i++) {
			pnProductos.add(new panelArticuloFinalizar(carrito.get(i), parent));
		}

		pnRegalos.setVisible(false);

		for (int i = 0; i < pnCompRegalos.getComponentCount(); i++) {
			JPanel panel = (JPanel) pnCompRegalos.getComponent(i);

			for (int j = 0; j < panel.getComponentCount(); j++) {
				JSpinner spinner = null;

				try {
					spinner = (JSpinner) panel.getComponent(j);
					spinner.setValue(new Integer(0));
				} catch (ClassCastException excep) {
				}
			}
		}

		DecimalFormat df = new DecimalFormat("0.00");
		lblTotal.setText(parent.getTextos().getString("Total") + ": "
				+ df.format(parent.getControlador().getPrecioTotal()) + " €");

		if (parent.getControlador().getLogged() == null) {
			pnPuntos.setVisible(false);
			pnTituloPuntos.setVisible(false);

			rdbtnUsarPuntos.setSelected(false);
			rdbtnCanjear.setSelected(false);
			rdbtnGuardar.setSelected(false);

			rdbtnUsarPuntos.setEnabled(false);
			rdbtnCanjear.setEnabled(false);
			rdbtnGuardar.setEnabled(false);
		}

		else {
			pnPuntos.setVisible(true);
			pnTituloPuntos.setVisible(true);

			rdbtnUsarPuntos.setSelected(false);
			rdbtnCanjear.setSelected(false);
			rdbtnGuardar.setSelected(true);

			rdbtnUsarPuntos.setEnabled(true);
			rdbtnCanjear.setEnabled(true);
			rdbtnGuardar.setEnabled(true);

			puntosTotales = parent.getControlador().getLogged().getPuntos();
		}

		pnProductos.revalidate();
		pnProductos.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	private JPanel getPnCentral() {
		if (pnCentral == null) {
			pnCentral = new JPanel();
			pnCentral.setLayout(new BorderLayout(10, 0));
			pnCentral.setBackground(Color.decode("#e3e3e3"));
			pnCentral.add(getPnCarrito(), BorderLayout.CENTER);
			pnCentral.add(getPnEast(), BorderLayout.EAST);
		}
		return pnCentral;
	}

	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setBackground(Color.decode("#e3e3e3"));
			pnCarrito.setLayout(new BorderLayout(0, 10));
			pnCarrito.add(getPnSubtotal(), BorderLayout.SOUTH);
			pnCarrito.add(getScrollPane(), BorderLayout.CENTER);
			pnCarrito.add(getPnTituloN(), BorderLayout.NORTH);
		}
		return pnCarrito;
	}

	private JLabel getLblTituloC() {
		if (lblTituloC == null) {
			lblTituloC = new JLabel(parent.getTextos().getString("Productos"));
			lblTituloC.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblTituloC.setHorizontalAlignment(SwingConstants.CENTER);
			lblTituloC.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		}
		return lblTituloC;
	}

	private JPanel getPnProductos() {
		if (pnProductos == null) {
			pnProductos = new JPanel();
			pnProductos.setBackground(Color.WHITE);
			pnProductos.setLayout(new GridLayout(0, 1, 0, 10));
		}
		return pnProductos;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnProductos());
		}
		return scrollPane;
	}

	private JPanel getPnSubtotal() {
		if (pnSubtotal == null) {
			pnSubtotal = new JPanel();
			pnSubtotal.setBackground(Color.WHITE);
			pnSubtotal.setLayout(new BorderLayout(0, 0));
			pnSubtotal.add(getLblTotal(), BorderLayout.EAST);
		}
		return pnSubtotal;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("New label");
			lblTotal.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblTotal;
	}

	private JPanel getPnTituloN() {
		if (pnTituloN == null) {
			pnTituloN = new JPanel();
			pnTituloN.setBackground(Color.WHITE);
			pnTituloN.add(getLblTituloC());
		}
		return pnTituloN;
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setBackground(Color.WHITE);
			pnNorth.setLayout(new BorderLayout(0, 0));
			pnNorth.add(getLblFinalizarCompra(), BorderLayout.NORTH);
		}
		return pnNorth;
	}

	private JLabel getLblFinalizarCompra() {
		if (lblFinalizarCompra == null) {
			lblFinalizarCompra = new JLabel(parent.getTextos().getString("FinalizarCompra"));
			lblFinalizarCompra.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblFinalizarCompra.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		}
		return lblFinalizarCompra;
	}

	public JPanel getFinalizar() {

		if (finalizar == null) {
			try {
				finalizar = new panelDatosFinalizar(parent.getControlador().getLogged(), parent);
			} catch (NullPointerException e) {
				finalizar = new panelDatosFinalizar(parent);
			}
		}

		return finalizar;
	}

	private JPanel getPnEast() {
		if (pnEast == null) {
			pnEast = new JPanel();
			pnEast.setLayout(new BorderLayout(10, 10));
			pnEast.setBackground(Color.decode("#e3e3e3"));
			pnEast.add(getPnTituloDatos(), BorderLayout.NORTH);
			pnEast.add(getPnCenterEast(), BorderLayout.CENTER);
		}
		return pnEast;
	}

	private JPanel getPnTituloDatos() {
		if (pnTituloDatos == null) {
			pnTituloDatos = new JPanel();
			pnTituloDatos.setBackground(Color.WHITE);
			pnTituloDatos.add(getLblTituloD());
		}
		return pnTituloDatos;
	}

	private JLabel getLblTituloD() {
		if (lblTituloD == null) {
			lblTituloD = new JLabel(parent.getTextos().getString("DatosUsuario"));
			lblTituloD.setHorizontalAlignment(SwingConstants.CENTER);
			lblTituloD.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			lblTituloD.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblTituloD;
	}

	private JPanel getPnCenterEast() {
		if (pnCenterEast == null) {
			pnCenterEast = new JPanel();
			pnCenterEast.setBorder(new EmptyBorder(-5, -5, -5, -5));
			pnCenterEast.setBackground(Color.decode("#e3e3e3"));
			pnCenterEast.setLayout(new BorderLayout(0, 10));
			pnCenterEast.add(getFinalizar(), BorderLayout.NORTH);
			pnCenterEast.add(getPnSouth(), BorderLayout.CENTER);
		}
		return pnCenterEast;
	}

	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setLayout(new BorderLayout(0, 10));
			pnSouth.setBackground(Color.decode("#e3e3e3"));
			pnSouth.add(getPnTituloPuntos(), BorderLayout.NORTH);
			pnSouth.add(getPnPuntos(), BorderLayout.CENTER);
			pnSouth.add(getPnBoton(), BorderLayout.SOUTH);
		}
		return pnSouth;
	}

	private JPanel getPnTituloPuntos() {
		if (pnTituloPuntos == null) {
			pnTituloPuntos = new JPanel();
			pnTituloPuntos.setBackground(Color.WHITE);
			pnTituloPuntos.setLayout(new BorderLayout(0, 0));
			pnTituloPuntos.add(getLblPuntos(), BorderLayout.CENTER);
		}
		return pnTituloPuntos;
	}

	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel(parent.getTextos().getString("Puntos"));
			lblPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			lblPuntos.setHorizontalTextPosition(SwingConstants.CENTER);
			lblPuntos.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblPuntos.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		}
		return lblPuntos;
	}

	private JPanel getPnPuntos() {
		if (pnPuntos == null) {
			pnPuntos = new JPanel();
			pnPuntos.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnPuntos.setBackground(Color.WHITE);
			pnPuntos.setLayout(new BorderLayout(0, 0));
			pnPuntos.add(getRdbtnUsarPuntos(), BorderLayout.NORTH);
			pnPuntos.add(getRdbtnCanjear(), BorderLayout.SOUTH);
			pnPuntos.add(getRdbtnGuardar(), BorderLayout.CENTER);
		}
		return pnPuntos;
	}

	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setBorder(new EmptyBorder(0, 5, 5, 5));
			pnBoton.setLayout(new BorderLayout(0, 0));
			pnBoton.setBackground(Color.decode("#e3e3e3"));
			pnBoton.add(getPnBotonInt(), BorderLayout.NORTH);
		}
		return pnBoton;
	}

	private JButton getCont() {

		if (cont == null) {
			cont = new JButton(parent.getTextos().getString("Continuar"));
			cont.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			cont.setForeground(Color.WHITE);
			cont.setBackground(Color.decode("#232f3e"));
			cont.setPreferredSize(new Dimension(0, 60));

			cont.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int codigo = -1;

					if (rdbtnUsarPuntos.isSelected())
						codigo = 0;

					else if (rdbtnCanjear.isSelected())
						codigo = 1;

					else if (rdbtnGuardar.isSelected())
						codigo = 2;

					if (codigo == 1) {

						puntosTotales = 0;

						for (int i = 0; i < pnCompRegalos.getComponentCount(); i++) {
							JPanel panel = (JPanel) pnCompRegalos.getComponent(i);

							for (int j = 0; j < panel.getComponentCount(); j++) {
								JSpinner spinner = null;

								try {
									spinner = (JSpinner) panel.getComponent(j);
									int z = Integer.valueOf(spinner.getName());

									Regalo r = parent.getControlador().listRegalos().get(z);
									puntosTotales += r.getPuntos() * (int) spinner.getValue();

									if ((int) spinner.getValue() > 0)
										parent.getControlador().getRegalos()
												.add(new RegaloCarrito(parent.getControlador().listRegalos().get(i),
														(int) spinner.getValue()));

								} catch (ClassCastException excep) {
								}
							}
						}

						if (puntosTotales > parent.getControlador().getLogged().getPuntos()) {
							JOptionPane.showMessageDialog(parent, "Los articulos seleccionados exceden tus puntos");
							parent.getControlador().getRegalos().clear();
							return;
						}

					}

					else {
						parent.getControlador().getRegalos().clear();
					}

					if (parent.getControlador().getLogged() == null) {
						if (finalizar.allFill() && finalizar.datosCorrectos()) {
							parent.updateTerminar(finalizar.getTxtNombre().getText(),
									finalizar.getTxtApellidos().getText(), finalizar.getTxtNif().getText(), codigo);
							parent.changeTerminarCompra();
						}

						else {
							JOptionPane.showMessageDialog(parent, parent.getTextos().getString("DatosIncorrectos"));
						}
					}

					else {
						parent.updateTerminar(finalizar.getTxtNombre().getText(), finalizar.getTxtApellidos().getText(),
								finalizar.getTxtNif().getText(), codigo);
						parent.changeTerminarCompra();
					}
				}
			});
		}

		return cont;
	}

	private JRadioButton getRdbtnUsarPuntos() {
		if (rdbtnUsarPuntos == null) {
			rdbtnUsarPuntos = new JRadioButton(parent.getTextos().getString("UsarPuntos"));
			rdbtnUsarPuntos.setVerticalAlignment(SwingConstants.BOTTOM);
			rdbtnUsarPuntos.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnUsarPuntos.setBackground(Color.WHITE);

			rdbtnUsarPuntos.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnUsarPuntos.setSelected(true);
					rdbtnCanjear.setSelected(false);
					rdbtnGuardar.setSelected(false);

					pnRegalos.setVisible(false);
				}
			});

		}
		return rdbtnUsarPuntos;
	}

	private JRadioButton getRdbtnCanjear() {
		if (rdbtnCanjear == null) {
			rdbtnCanjear = new JRadioButton(parent.getTextos().getString("CanjearPuntos"));
			rdbtnCanjear.setVerticalAlignment(SwingConstants.TOP);
			rdbtnCanjear.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnCanjear.setBackground(Color.WHITE);

			rdbtnCanjear.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnCanjear.setSelected(true);
					rdbtnUsarPuntos.setSelected(false);
					rdbtnGuardar.setSelected(false);

					pnRegalos.setVisible(true);
				}
			});
		}

		return rdbtnCanjear;
	}

	private JRadioButton getRdbtnGuardar() {
		if (rdbtnGuardar == null) {
			rdbtnGuardar = new JRadioButton(parent.getTextos().getString("GuardarPuntos"));
			rdbtnGuardar.setHorizontalAlignment(SwingConstants.CENTER);
			rdbtnGuardar.setBackground(Color.WHITE);

			rdbtnGuardar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rdbtnGuardar.setSelected(true);
					rdbtnUsarPuntos.setSelected(false);
					rdbtnCanjear.setSelected(false);

					pnRegalos.setVisible(false);
				}
			});

		}
		return rdbtnGuardar;
	}

	private JPanel getPnRegalos() {
		if (pnRegalos == null) {
			pnRegalos = new JPanel();
			pnRegalos.setBackground(Color.decode("#e3e3e3"));
			pnRegalos.setLayout(new BorderLayout(0, 10));
			pnRegalos.add(getPnTituloRegalos(), BorderLayout.NORTH);
			pnRegalos.add(getScrollPane_1(), BorderLayout.CENTER);
			pnRegalos.setVisible(false);

		}
		return pnRegalos;
	}

	private JPanel getPnTituloRegalos() {
		if (pnTituloRegalos == null) {
			pnTituloRegalos = new JPanel();
			pnTituloRegalos.setBackground(Color.WHITE);
			pnTituloRegalos.setLayout(new BorderLayout(0, 0));
			pnTituloRegalos.add(getLblRegalos(), BorderLayout.NORTH);
		}
		return pnTituloRegalos;
	}

	private JLabel getLblRegalos() {
		if (lblRegalos == null) {
			lblRegalos = new JLabel("Regalos");
			lblRegalos.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblRegalos.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		}
		return lblRegalos;
	}

	private JPanel getPnCompRegalos() {
		if (pnCompRegalos == null) {
			pnCompRegalos = new JPanel();
			pnCompRegalos.setBackground(Color.WHITE);
			pnCompRegalos.setLayout(new GridLayout(0, 1, 0, 10));

			ArrayList<Regalo> regalos = parent.getControlador().listRegalos();

			for (int i = 0; i < regalos.size(); i++) {
				JPanel temp = new JPanel();
				temp.setLayout(new BorderLayout(0, 0));
				temp.setBackground(Color.WHITE);

				JLabel nombre = new JLabel(regalos.get(i).getDenominacion());
				JLabel puntos = new JLabel(String.valueOf(regalos.get(i).getPuntos()) + " puntos");
				puntos.setHorizontalAlignment(JLabel.RIGHT);
				puntos.setBorder(new EmptyBorder(0, 10, 0, 10));
				JSpinner spinner = new JSpinner(
						new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
				spinner.setName(String.valueOf(i));

				temp.add(nombre, BorderLayout.WEST);
				temp.add(puntos, BorderLayout.CENTER);
				temp.add(spinner, BorderLayout.EAST);
				temp.setBorder(new EmptyBorder(0, 10, 0, 10));
				pnCompRegalos.add(temp);
			}

		}
		return pnCompRegalos;
	}

	private JScrollPane getScrollPane_1() {
		if (scrollPane_1 == null) {
			scrollPane_1 = new JScrollPane();
			scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane_1.setViewportView(getPnCompRegalos());
		}
		return scrollPane_1;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton(parent.getTextos().getString("SalirUsuario"));
			btnCancel.setBorder(new LineBorder(Color.GRAY, 2, true));
			btnCancel.setBackground(Color.WHITE);
			btnCancel.setForeground(Color.GRAY);
			btnCancel.setPreferredSize(new Dimension(0, 60));
			btnCancel.setFont(new Font("Lucida Grande", Font.BOLD, 16));

			btnCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnCancel.setBackground(Color.GRAY);
					btnCancel.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnCancel.setBackground(Color.WHITE);
					btnCancel.setForeground(Color.GRAY);
				}

			});

			btnCancel.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					parent.changeCarrito();
				}

			});

		}
		return btnCancel;
	}

	private JPanel getPnBotonInt() {
		if (pnBotonInt == null) {
			pnBotonInt = new JPanel();
			pnBotonInt.setBackground(Color.decode("#e3e3e3"));
			pnBotonInt.setLayout(new GridLayout(0, 2, 10, 0));
			pnBotonInt.add(getCont());
			pnBotonInt.add(getBtnCancel());
		}
		return pnBotonInt;
	}
}
