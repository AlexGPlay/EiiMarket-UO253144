package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import gui.VentanaPrincipal;

import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panelRegistro extends JPanel {
	private JPanel pnCentro;
	private JPanel pnRegistro;
	private JPanel pnLabelR;
	private JPanel pnBotonR;
	private JPanel pnDatosR;
	private JLabel lblRegistro;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblDni;
	private JTextField txtDni;
	private JLabel lblNombreDeUsuario;
	private JTextField txtNombreUsuario;
	private JLabel lblPassR;
	private JLabel lblRepetirContrasea;
	private JLabel lblTelfono;
	private JTextField txtTlf;
	private JLabel lblTarjetaBancaria;
	private JTextField txtBanco;
	private JButton btnCompletarRegistro;
	public VentanaPrincipal parent;
	private JPasswordField txtPassR;
	private JPasswordField txtRePass;
	private JLabel lblError;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;

	private panelVentajas ventajas;
	private JPanel pnVent;
	private JPanel pnTitulo;
	private JLabel lblVentajas;
	private JButton btnCancelar;

	/**
	 * Create the panel.
	 */
	public panelRegistro(VentanaPrincipal newV) {
		parent = newV;

		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(Color.decode("#e3e3e3"));
		setLayout(new BorderLayout(5, 0));
		add(getPnLabelR(), BorderLayout.NORTH);
		add(getPnCentro(), BorderLayout.EAST);
		add(getPnVent(), BorderLayout.CENTER);
	}
	
	public void clearAll(){
		txtNombre.setText("");
		txtApellidos.setText("");
		txtDni.setText("");
		txtNombreUsuario.setText("");
		txtPassR.setText("");
		txtRePass.setText("");
		txtTlf.setText("");
		txtBanco.setText("");
		
	}
	
	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.decode("#e3e3e3"));
			pnCentro.setBorder(new EmptyBorder(5, 0, 20, -5));
			pnCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnCentro.add(getPnRegistro());

		}
		return pnCentro;
	}

	private JPanel getPnVentajas(){
		if(ventajas == null){
			ventajas = new panelVentajas(parent);
			ventajas.setBorder(new EmptyBorder(10, 0, 10, 0));
		}

		return ventajas;
	}

	private JPanel getPnRegistro() {
		if (pnRegistro == null) {
			pnRegistro = new JPanel();
			pnRegistro.setBorder(null);
			pnRegistro.setBackground(Color.WHITE);
			pnRegistro.setLayout(new BorderLayout(10, 10));
			pnRegistro.add(getPnBotonR(), BorderLayout.SOUTH);
			pnRegistro.add(getPnDatosR(), BorderLayout.CENTER);
		}
		return pnRegistro;
	}
	private JPanel getPnLabelR() {
		if (pnLabelR == null) {
			pnLabelR = new JPanel();
			pnLabelR.setBackground(Color.WHITE);
			pnLabelR.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnLabelR.setLayout(new BorderLayout(0, 0));
			pnLabelR.add(getLblRegistro());
		}
		return pnLabelR;
	}
	private JPanel getPnBotonR() {
		if (pnBotonR == null) {
			pnBotonR = new JPanel();
			pnBotonR.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnBotonR.setBackground(Color.WHITE);
			pnBotonR.setLayout(new BorderLayout(10, 0));
			pnBotonR.add(getBtnCompletarRegistro(), BorderLayout.CENTER);
			pnBotonR.add(getLblError(), BorderLayout.NORTH);
			pnBotonR.add(getBtnCancelar(), BorderLayout.EAST);
		}
		return pnBotonR;
	}
	private JPanel getPnDatosR() {
		if (pnDatosR == null) {
			pnDatosR = new JPanel();
			pnDatosR.setBackground(Color.WHITE);
			pnDatosR.setBorder(new EmptyBorder(10, 20, 10, 20));
			pnDatosR.setLayout(new GridLayout(0, 1, 10, 15));
			pnDatosR.add(getPanel());
			pnDatosR.add(getPanel_1());
			pnDatosR.add(getPanel_2());
			pnDatosR.add(getPanel_3());
			pnDatosR.add(getPanel_4());
			pnDatosR.add(getPanel_5());
			pnDatosR.add(getPanel_6());
			pnDatosR.add(getPanel_7());

		}
		return pnDatosR;
	}
	private JLabel getLblRegistro() {
		if (lblRegistro == null) {
			lblRegistro = new JLabel(parent.getTextos().getString("Crear"));
			lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
			lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
		}
		return lblRegistro;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(parent.getTextos().getString("Nombre"));
			lblNombre.setBorder(new EmptyBorder(5, 0, 5, 0));
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBorder(new LineBorder(Color.GRAY, 1, true));
			txtNombre.setColumns(15);
		}
		return txtNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel(parent.getTextos().getString("Apellidos"));
		}
		return lblApellidos;
	}
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setBorder(new LineBorder(Color.GRAY, 1, true));
			txtApellidos.setColumns(15);
		}
		return txtApellidos;
	}
	private JLabel getLblDni() {
		if (lblDni == null) {
			lblDni = new JLabel(parent.getTextos().getString("NIF"));
		}
		return lblDni;
	}
	private JTextField getTxtDni() {
		if (txtDni == null) {
			txtDni = new JTextField();
			txtDni.setBorder(new LineBorder(Color.GRAY, 1, true));
			txtDni.setColumns(15);
		}
		return txtDni;
	}
	private JLabel getLblNombreDeUsuario() {
		if (lblNombreDeUsuario == null) {
			lblNombreDeUsuario = new JLabel(parent.getTextos().getString("NombreUser"));
			lblNombreDeUsuario.setBorder(new EmptyBorder(0, 0, 0, 0));
		}
		return lblNombreDeUsuario;
	}
	private JTextField getTxtNombreUsuario() {
		if (txtNombreUsuario == null) {
			txtNombreUsuario = new JTextField();
			txtNombreUsuario.setBorder(new LineBorder(Color.GRAY, 1, true));
			txtNombreUsuario.setColumns(15);
		}
		return txtNombreUsuario;
	}
	private JLabel getLblPassR() {
		if (lblPassR == null) {
			lblPassR = new JLabel(parent.getTextos().getString("Contraseña"));
		}
		return lblPassR;
	}
	private JLabel getLblRepetirContrasea() {
		if (lblRepetirContrasea == null) {
			lblRepetirContrasea = new JLabel(parent.getTextos().getString("RepetirContra"));
		}
		return lblRepetirContrasea;
	}
	private JLabel getLblTelfono() {
		if (lblTelfono == null) {
			lblTelfono = new JLabel(parent.getTextos().getString("Telefono"));
		}
		return lblTelfono;
	}
	private JTextField getTxtTlf() {
		if (txtTlf == null) {
			txtTlf = new JTextField();
			txtTlf.setBorder(new LineBorder(Color.GRAY, 1, true));
			txtTlf.setColumns(15);
		}
		return txtTlf;
	}
	private JLabel getLblTarjetaBancaria() {
		if (lblTarjetaBancaria == null) {
			lblTarjetaBancaria = new JLabel(parent.getTextos().getString("Tarjeta"));
		}
		return lblTarjetaBancaria;
	}
	private JTextField getTxtBanco() {
		if (txtBanco == null) {
			txtBanco = new JTextField();
			txtBanco.setBorder(new LineBorder(Color.GRAY, 1, true));
			txtBanco.setColumns(15);
		}
		return txtBanco;
	}
	private JButton getBtnCompletarRegistro() {
		if (btnCompletarRegistro == null) {
			btnCompletarRegistro = new JButton(parent.getTextos().getString("CompletarRegis"));
			btnCompletarRegistro.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnCompletarRegistro.setForeground(Color.WHITE);
			btnCompletarRegistro.setBackground(Color.decode("#232f3e"));
			btnCompletarRegistro.setPreferredSize(new Dimension(230, 60));

			btnCompletarRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(checkAll()){

						if(parent.getControlador().nombreDisponible(txtNombreUsuario.getText()) &&
								parent.getControlador().dniDisponible(txtDni.getText()) && 
								parent.getControlador().checkTelefono(txtTlf.getText())
								&& parent.getControlador().passwordValida(getTxtPassR().getPassword())
								&& txtNombreUsuario.getText().length()>5){
							String text = createText();
							parent.getControlador().registraUsuario(text);
							JOptionPane.showMessageDialog(parent, parent.getTextos().getString("RegisExito"));
							parent.nuevoLog();
						}

						else{
							lblError.setText(parent.getTextos().getString("DatosInc"));
						}
					}
				}
			});
		}
		return btnCompletarRegistro;
	}

	private boolean checkAll(){
		if(txtNombre.getText().equals("") || txtApellidos.getText().equals("") || txtDni.getText().equals("")
				|| txtNombreUsuario.getText().equals("") || new String(txtPassR.getPassword()).equals("")
				|| new String(txtRePass.getPassword()).equals("") || txtTlf.getText().equals("") 
				|| txtBanco.getText().equals("")){
			lblError.setText(parent.getTextos().getString("CampoVacio"));
			return false;
		}

		else if(!(new String(txtRePass.getPassword()).equals(new String(txtPassR.getPassword())))){
			lblError.setText(parent.getTextos().getString("ContNoIg"));
			return false;
		}

		return true;
	}

	private String createText(){
		String text = "";
		text += txtNombre.getText() + ";";
		text += txtApellidos.getText() + ";";
		text += txtDni.getText() + ";";
		text += txtNombreUsuario.getText() + ";";
		text += new String(txtPassR.getPassword()) + ";";
		text += txtTlf.getText() + ";";
		text += txtBanco.getText() + ";";
		text += "0";

		return text;
	}
	private JPasswordField getTxtPassR() {
		if (txtPassR == null) {
			txtPassR = new JPasswordField();
			txtPassR.setColumns(15);
			txtPassR.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtPassR;
	}
	private JPasswordField getTxtRePass() {
		if (txtRePass == null) {
			txtRePass = new JPasswordField();
			txtRePass.setColumns(15);
			txtRePass.setBorder(new LineBorder(Color.GRAY, 1, true));
		}
		return txtRePass;
	}
	private JLabel getLblError() {
		if (lblError == null) {
			lblError = new JLabel("");
			lblError.setForeground(Color.RED);
			lblError.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblError;
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
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.WHITE);
			panel_1.setLayout(new BorderLayout(65, 0));
			panel_1.add(getLblApellidos(), BorderLayout.WEST);
			panel_1.add(getTxtApellidos(), BorderLayout.EAST);
		}
		return panel_1;
	}
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.WHITE);
			panel_2.setLayout(new BorderLayout(76, 0));
			panel_2.add(getLblDni(), BorderLayout.WEST);
			panel_2.add(getTxtDni(), BorderLayout.EAST);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.WHITE);
			panel_3.setLayout(new BorderLayout(4, 0));
			panel_3.add(getLblNombreDeUsuario(), BorderLayout.WEST);
			panel_3.add(getTxtNombreUsuario(), BorderLayout.EAST);
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.WHITE);
			panel_4.setLayout(new BorderLayout(53, 0));
			panel_4.add(getLblPassR(), BorderLayout.WEST);
			panel_4.add(getTxtPassR(), BorderLayout.EAST);
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
			panel_5.setBackground(Color.WHITE);
			panel_5.setLayout(new BorderLayout(8, 0));
			panel_5.add(getLblRepetirContrasea(), BorderLayout.WEST);
			panel_5.add(getTxtRePass(), BorderLayout.EAST);
		}
		return panel_5;
	}
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
			panel_6.setBackground(Color.WHITE);
			panel_6.setLayout(new BorderLayout(70, 0));
			panel_6.add(getLblTelfono(), BorderLayout.WEST);
			panel_6.add(getTxtTlf(), BorderLayout.EAST);
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
			panel_7.setBackground(Color.WHITE);
			panel_7.setLayout(new BorderLayout(25, 0));
			panel_7.add(getLblTarjetaBancaria(), BorderLayout.WEST);
			panel_7.add(getTxtBanco(), BorderLayout.EAST);
		}
		return panel_7;
	}
	private JPanel getPnVent() {
		if (pnVent == null) {
			pnVent = new JPanel();
			pnVent.setBorder(new EmptyBorder(10, 0, 10, 10));
			pnVent.setLayout(new BorderLayout(0, 10));
			pnVent.setBackground(Color.decode("#e3e3e3"));
			pnVent.add(getPnVentajas());
			pnVent.add(getPnTitulo(), BorderLayout.NORTH);
		}
		return pnVent;
	}
	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.setLayout(new BorderLayout(0, 0));
			pnTitulo.add(getLblVentajas(), BorderLayout.NORTH);
		}
		return pnTitulo;
	}
	private JLabel getLblVentajas() {
		if (lblVentajas == null) {
			lblVentajas = new JLabel(parent.getTextos().getString("VentajasLabel"));
			lblVentajas.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblVentajas.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblVentajas;
	}
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton(parent.getTextos().getString("SalirUsuario"));
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.changePrincipal();
				}
			});
			btnCancelar.setBorder(new LineBorder(Color.GRAY, 2, true));
			btnCancelar.setBackground(Color.WHITE);
			btnCancelar.setForeground(Color.GRAY);
			btnCancelar.setPreferredSize(new Dimension(230, 60));
			btnCancelar.setFont(new Font("Lucida Grande", Font.BOLD, 16));

			btnCancelar.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseEntered(MouseEvent e){
					btnCancelar.setBackground(Color.GRAY);
					btnCancelar.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e){
					btnCancelar.setBackground(Color.WHITE);
					btnCancelar.setForeground(Color.GRAY);
				}

			});

		}
		return btnCancelar;
	}
}
