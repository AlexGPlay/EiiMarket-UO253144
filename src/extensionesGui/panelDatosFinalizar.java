package extensionesGui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import gui.VentanaPrincipal;
import logica.Cliente;

@SuppressWarnings("serial")
public class panelDatosFinalizar extends JPanel {
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblNif;
	private JTextField txtNif;
	private JLabel lblTlf;
	private JTextField txtTlf;
	private JLabel lblTarj;
	private JTextField txtTarjeta;

	private VentanaPrincipal parent;

	/**
	 * Create the panel.
	 */

	public panelDatosFinalizar(VentanaPrincipal v) {
		this(null, v);
	}

	public panelDatosFinalizar(Cliente c, VentanaPrincipal v) {
		parent = v;

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(Color.WHITE);
		setLayout(new GridLayout(0, 1, 0, 10));
		add(getPanel());
		add(getPanel_1());
		add(getPanel_2());
		add(getPanel_3());
		add(getPanel_4());

		if (c != null)
			cargaDatos(c);
	}

	public void cargaDatos(Cliente c) {

		if (c != null) {
			txtNombre.setText(c.getNombre());
			txtApellidos.setText(c.getApellidos());
			txtNif.setText(c.getNif());
			txtTlf.setText(String.valueOf(c.getTelefono()));
			txtTarjeta.setText(c.getTarjetaBanco());

			txtNombre.setEnabled(false);
			txtApellidos.setEnabled(false);
			txtNif.setEnabled(false);
			txtTlf.setEnabled(false);
			txtTarjeta.setEnabled(false);
		}

		if (c == null) {
			txtNombre.setEnabled(true);
			txtApellidos.setEnabled(true);
			txtNif.setEnabled(true);
			txtTlf.setEnabled(true);
			txtTarjeta.setEnabled(true);

			txtNombre.setText("");
			txtApellidos.setText("");
			txtNif.setText("");
			txtTlf.setText("");
			txtTarjeta.setText("");
		}

	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getLblApellidos(), BorderLayout.WEST);
			panel_1.add(getTxtApellidos(), BorderLayout.EAST);
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getLblNif(), BorderLayout.WEST);
			panel_2.add(getTxtNif(), BorderLayout.EAST);
		}
		return panel_2;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new BorderLayout(0, 0));
			panel_3.add(getLblTlf(), BorderLayout.WEST);
			panel_3.add(getTxtTlf(), BorderLayout.EAST);
		}
		return panel_3;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(getLblTarj(), BorderLayout.WEST);
			panel_4.add(getTxtTarjeta(), BorderLayout.EAST);
		}
		return panel_4;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(74, 0));
			panel.add(getLblNombre(), BorderLayout.WEST);
			panel.add(getTxtNombre(), BorderLayout.EAST);
		}
		return panel;
	}

	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(parent.getTextos().getString("Nombre"));
			lblNombre.setBorder(new EmptyBorder(5, 0, 5, 0));
		}
		return lblNombre;
	}

	public JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setColumns(15);
			txtNombre.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtNombre;
	}

	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel(parent.getTextos().getString("Apellidos"));
		}
		return lblApellidos;
	}

	public JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setColumns(15);
			txtApellidos.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtApellidos;
	}

	private JLabel getLblNif() {
		if (lblNif == null) {
			lblNif = new JLabel(parent.getTextos().getString("NIF"));
		}
		return lblNif;
	}

	public JTextField getTxtNif() {
		if (txtNif == null) {
			txtNif = new JTextField();
			txtNif.setColumns(15);
			txtNif.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtNif;
	}

	private JLabel getLblTlf() {
		if (lblTlf == null) {
			lblTlf = new JLabel(parent.getTextos().getString("Telefono"));
		}
		return lblTlf;
	}

	public JTextField getTxtTlf() {
		if (txtTlf == null) {
			txtTlf = new JTextField();
			txtTlf.setColumns(15);
			txtTlf.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtTlf;
	}

	private JLabel getLblTarj() {
		if (lblTarj == null) {
			lblTarj = new JLabel(parent.getTextos().getString("Tarjeta"));
		}
		return lblTarj;
	}

	public JTextField getTxtTarjeta() {
		if (txtTarjeta == null) {
			txtTarjeta = new JTextField();
			txtTarjeta.setColumns(15);
			txtTarjeta.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtTarjeta;
	}

	public boolean allFill() {
		if (!txtTarjeta.getText().equals("") && !txtTlf.getText().equals("") && !txtNif.getText().equals("")
				&& !txtApellidos.getText().equals("") && !txtNombre.getText().equals(""))
			return true;

		return false;
	}

	public boolean datosCorrectos() {
		if (parent.getControlador().checkTelefono(txtTlf.getText())
				&& parent.getControlador().dniDisponible(txtNif.getText()))
			return true;

		return false;
	}
}
