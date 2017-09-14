package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import gui.VentanaPrincipal;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panelRealizarPedido extends JPanel {
	private JPanel pnNorth;
	private JPanel pnCentro;
	private JPanel pnSur;
	private JButton btnRealizar;
	private JLabel lblTotal;
	private JLabel lblDinero;
	private JPanel pnCont;

	private VentanaPrincipal parent;
	private JPanel panel;
	private JButton btnSeguir;

	/**
	 * Create the panel.
	 */
	public panelRealizarPedido(VentanaPrincipal v) {
		parent = v;

		setBackground(Color.decode("#e3e3e3"));
		setLayout(new BorderLayout(0, 0));
		add(getPnNorth(), BorderLayout.NORTH);
		add(getPnCentro(), BorderLayout.CENTER);

	}

	public void actualizaTotal(double total) {
		DecimalFormat df = new DecimalFormat("0.00");
		lblDinero.setText(df.format(total) + " €");
	}

	private JPanel getPnNorth() {
		if (pnNorth == null) {
			pnNorth = new JPanel();
			pnNorth.setBackground(Color.decode("#232f3e"));
		}
		return pnNorth;
	}

	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.WHITE);
			pnCentro.setBorder(null);
			pnCentro.setLayout(new BorderLayout(0, 0));
			pnCentro.add(getPnCont(), BorderLayout.NORTH);
			pnCentro.add(getPnSur(), BorderLayout.CENTER);
		}
		return pnCentro;
	}

	private JPanel getPnSur() {
		if (pnSur == null) {
			pnSur = new JPanel();
			pnSur.setBorder(new EmptyBorder(10, 0, 10, 0));
			pnSur.setBackground(Color.decode("#e3e3e3"));
			pnSur.setLayout(new BorderLayout(0, 0));
			pnSur.add(getPanel(), BorderLayout.NORTH);
		}
		return pnSur;
	}

	public JButton getBtnRealizar() {
		if (btnRealizar == null) {
			btnRealizar = new JButton(parent.getTextos().getString("Realizar"));
			btnRealizar.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnRealizar.setForeground(Color.WHITE);
			btnRealizar.setBackground(Color.decode("#232f3e"));
			btnRealizar.setPreferredSize(new Dimension(0, 50));
		}
		return btnRealizar;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel(parent.getTextos().getString("TotalMayus"));
			lblTotal.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			lblTotal.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblTotal;
	}

	private JLabel getLblDinero() {
		if (lblDinero == null) {
			lblDinero = new JLabel("dinero");
			lblDinero.setFont(new Font("Lucida Grande", Font.BOLD, 15));
			lblDinero.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblDinero;
	}

	private JPanel getPnCont() {
		if (pnCont == null) {
			pnCont = new JPanel();
			pnCont.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnCont.setBackground(Color.WHITE);
			pnCont.setLayout(new BorderLayout(0, 0));
			pnCont.add(getLblTotal(), BorderLayout.WEST);
			pnCont.add(getLblDinero());
		}
		return pnCont;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.decode("#e3e3e3"));
			panel.setLayout(new BorderLayout(10, 0));
			panel.add(getBtnRealizar());
			panel.add(getBtnSeguir(), BorderLayout.EAST);
		}
		return panel;
	}

	private JButton getBtnSeguir() {
		if (btnSeguir == null) {
			btnSeguir = new JButton(parent.getTextos().getString("Seguir"));
			btnSeguir.setBorder(new LineBorder(Color.GRAY, 2, true));
			btnSeguir.setBackground(Color.WHITE);
			btnSeguir.setForeground(Color.GRAY);
			btnSeguir.setPreferredSize(new Dimension(230, 60));
			btnSeguir.setFont(new Font("Lucida Grande", Font.BOLD, 16));

			btnSeguir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.changePrincipal();
				}
			});

			btnSeguir.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					btnSeguir.setBackground(Color.GRAY);
					btnSeguir.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnSeguir.setBackground(Color.WHITE);
					btnSeguir.setForeground(Color.GRAY);
				}

			});
		}
		return btnSeguir;
	}
}
