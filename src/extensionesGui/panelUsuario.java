package extensionesGui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.VentanaPrincipal;
import logica.Cliente;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panelUsuario extends JPanel {
	private JPanel pnCentro;
	private VentanaPrincipal parent;
	private JScrollPane scrollPane;
	private JPanel pnTitulo;
	private JLabel lblTitulo;
	private JPanel pnCentro2;
	private JPanel pnNorthDatos;
	private JPanel pnDatosPersonales;
	private JPanel pnTituloPersonales;
	private JLabel lblPersonales;
	private JPanel pnDPersonales;
	private JLabel lblNombre;
	private JPanel pnTitulosNN;
	private JLabel lblNif;
	private JPanel pnSouthD;
	private JTextField txtNombre;
	private JTextField txtNif;
	private JPanel pnDatosCuenta;
	private JPanel pnTituloDatosCuenta;
	private JLabel lblDatosCuenta;
	private JPanel pnDCuenta;
	private JLabel lblUsuario;
	private JPanel pnContCuenta;
	private JTextField txtUser;
	private JButton btnComprobar;
	private JPanel pnCentro3;
	private JPanel pnContra;
	private JPanel pnTituloContra;
	private JLabel lblContrasea;
	private JPanel pnCambiar;
	private JLabel lblContraseaActual;
	private JLabel lblNuevaContrasea;
	private JLabel lblRepetirContrasea;
	private JPasswordField txtRep;
	private JPasswordField txtNueva;
	private JPasswordField txtActual;
	private JPanel pnCT;
	private JPanel pnDatosCompra;
	private JPanel pnTituloCompra;
	private JLabel lblDatosFacturacin;
	private JPanel pnFacCentro;
	private JPanel pnDatos;
	private JLabel lblTelefono;
	private JLabel lblTarjeta;
	private JTextField txtTarjeta;
	private JTextField txtTelefono;
	private JPanel pnSouth;
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private Cliente c;

	/**
	 * Create the panel.
	 */
	public panelUsuario(VentanaPrincipal principal) {
		parent = principal;
		
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.decode("#e3e3e3"));
		add(getScrollPane(), BorderLayout.CENTER);
	}
	
	public void cargaCliente(Cliente c){
		txtNombre.setText(c.getNombre() + " " + c.getApellidos());
		txtNif.setText(c.getNif());
		txtUser.setText(c.getUsuario());
		txtTelefono.setText(String.valueOf(c.getTelefono()));
		txtTarjeta.setText(c.getTarjetaBanco());
		txtUser.setBackground(Color.WHITE);
		this.c = c;
	}

	private JPanel getPnCentro() {
		if (pnCentro == null) {
			pnCentro = new JPanel();
			pnCentro.setBackground(Color.decode("#e3e3e3"));
			pnCentro.setBorder(new EmptyBorder(10, 10, 30, 10));
			pnCentro.setLayout(new BorderLayout(0, 30));
			pnCentro.add(getPnTitulo(), BorderLayout.NORTH);
			pnCentro.add(getPnCentro2(), BorderLayout.CENTER);
			
		}
		return pnCentro;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnCentro());
		}
		return scrollPane;
	}
	private JPanel getPnTitulo() {
		if (pnTitulo == null) {
			pnTitulo = new JPanel();
			pnTitulo.setBackground(Color.WHITE);
			pnTitulo.setLayout(new BorderLayout(0, 0));
			pnTitulo.add(getLblTitulo(), BorderLayout.CENTER);
		}
		return pnTitulo;
	}
	private JLabel getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = new JLabel(parent.getTextos().getString("MisDatos"));
			lblTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblTitulo.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		}
		return lblTitulo;
	}
	private JPanel getPnCentro2() {
		if (pnCentro2 == null) {
			pnCentro2 = new JPanel();
			pnCentro2.setBackground(Color.decode("#e3e3e3"));
			pnCentro2.setLayout(new BorderLayout(0, 20));
			pnCentro2.add(getPnNorthDatos(), BorderLayout.NORTH);
			pnCentro2.add(getPnCentro3(), BorderLayout.CENTER);
		}
		return pnCentro2;
	}
	private JPanel getPnNorthDatos() {
		if (pnNorthDatos == null) {
			pnNorthDatos = new JPanel();
			pnNorthDatos.setBackground(Color.decode("#e3e3e3"));
			pnNorthDatos.setLayout(new BorderLayout(10, 10));
			pnNorthDatos.add(getPnDatosPersonales(), BorderLayout.WEST);
			pnNorthDatos.add(getPnDatosCuenta(), BorderLayout.CENTER);
		}
		return pnNorthDatos;
	}
	private JPanel getPnDatosPersonales() {
		if (pnDatosPersonales == null) {
			pnDatosPersonales = new JPanel();
			pnDatosPersonales.setBackground(Color.decode("#e3e3e3"));
			pnDatosPersonales.setLayout(new BorderLayout(0, 5));
			pnDatosPersonales.add(getPnTituloPersonales(), BorderLayout.NORTH);
			pnDatosPersonales.add(getPnDPersonales(), BorderLayout.CENTER);
		}
		return pnDatosPersonales;
	}
	private JPanel getPnTituloPersonales() {
		if (pnTituloPersonales == null) {
			pnTituloPersonales = new JPanel();
			pnTituloPersonales.setBackground(Color.WHITE);
			pnTituloPersonales.setLayout(new BorderLayout(0, 0));
			pnTituloPersonales.add(getLblPersonales(), BorderLayout.NORTH);
		}
		return pnTituloPersonales;
	}
	private JLabel getLblPersonales() {
		if (lblPersonales == null) {
			lblPersonales = new JLabel(parent.getTextos().getString("DatosPers"));
			lblPersonales.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblPersonales.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		}
		return lblPersonales;
	}
	private JPanel getPnDPersonales() {
		if (pnDPersonales == null) {
			pnDPersonales = new JPanel();
			pnDPersonales.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnDPersonales.setBackground(Color.WHITE);
			pnDPersonales.setLayout(new BorderLayout(0, 0));
			pnDPersonales.add(getPnTitulosNN(), BorderLayout.NORTH);
		}
		return pnDPersonales;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel(parent.getTextos().getString("Nombre"));
			lblNombre.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblNombre;
	}
	private JPanel getPnTitulosNN() {
		if (pnTitulosNN == null) {
			pnTitulosNN = new JPanel();
			pnTitulosNN.setBackground(Color.WHITE);
			pnTitulosNN.setLayout(new BorderLayout(10, 0));
			pnTitulosNN.add(getLblNombre(), BorderLayout.WEST);
			pnTitulosNN.add(getLblNif(), BorderLayout.CENTER);
			pnTitulosNN.add(getPnSouthD(), BorderLayout.SOUTH);
		}
		return pnTitulosNN;
	}
	private JLabel getLblNif() {
		if (lblNif == null) {
			lblNif = new JLabel(parent.getTextos().getString("NIF"));
			lblNif.setFont(new Font("Lucida Grande", Font.BOLD, 13));
			lblNif.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNif;
	}
	private JPanel getPnSouthD() {
		if (pnSouthD == null) {
			pnSouthD = new JPanel();
			pnSouthD.setBorder(new EmptyBorder(5, 0, 0, 0));
			pnSouthD.setBackground(Color.WHITE);
			pnSouthD.setLayout(new BorderLayout(10, 10));
			pnSouthD.add(getTxtNombre(), BorderLayout.WEST);
			pnSouthD.add(getTxtNif(), BorderLayout.EAST);
		}
		return pnSouthD;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setPreferredSize(new Dimension(0,30));
			txtNombre.setColumns(30);
		}
		return txtNombre;
	}
	private JTextField getTxtNif() {
		if (txtNif == null) {
			txtNif = new JTextField();
			txtNif.setEditable(false);
			txtNif.setColumns(30);
		}
		return txtNif;
	}
	private JPanel getPnDatosCuenta() {
		if (pnDatosCuenta == null) {
			pnDatosCuenta = new JPanel();
			pnDatosCuenta.setLayout(new BorderLayout(0, 5));
			pnDatosCuenta.setBackground(Color.decode("#e3e3e3"));
			pnDatosCuenta.add(getPnTituloDatosCuenta(), BorderLayout.NORTH);
			pnDatosCuenta.add(getPnDCuenta(), BorderLayout.CENTER);
		}
		return pnDatosCuenta;
	}
	private JPanel getPnTituloDatosCuenta() {
		if (pnTituloDatosCuenta == null) {
			pnTituloDatosCuenta = new JPanel();
			pnTituloDatosCuenta.setBackground(Color.WHITE);
			pnTituloDatosCuenta.setLayout(new BorderLayout(0, 0));
			pnTituloDatosCuenta.add(getLblDatosCuenta(), BorderLayout.NORTH);
		}
		return pnTituloDatosCuenta;
	}
	private JLabel getLblDatosCuenta() {
		if (lblDatosCuenta == null) {
			lblDatosCuenta = new JLabel(parent.getTextos().getString("DatosCuen"));
			lblDatosCuenta.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			lblDatosCuenta.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblDatosCuenta;
	}
	private JPanel getPnDCuenta() {
		if (pnDCuenta == null) {
			pnDCuenta = new JPanel();
			pnDCuenta.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnDCuenta.setBackground(Color.WHITE);
			pnDCuenta.setLayout(new BorderLayout(0, 0));
			pnDCuenta.add(getLblUsuario(), BorderLayout.WEST);
			pnDCuenta.add(getPnContCuenta(), BorderLayout.SOUTH);
		}
		return pnDCuenta;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel(parent.getTextos().getString("Usuario"));
			lblUsuario.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblUsuario;
	}
	private JPanel getPnContCuenta() {
		if (pnContCuenta == null) {
			pnContCuenta = new JPanel();
			pnContCuenta.setBackground(Color.WHITE);
			pnContCuenta.setLayout(new BorderLayout(10, 10));
			pnContCuenta.add(getTxtUser(), BorderLayout.CENTER);
			pnContCuenta.add(getBtnComprobar(), BorderLayout.EAST);
		}
		return pnContCuenta;
	}
	private JTextField getTxtUser() {
		if (txtUser == null) {
			txtUser = new JTextField();
			txtUser.setPreferredSize(new Dimension(0, 30));
			txtUser.setColumns(15);
		}
		return txtUser;
	}
	private JButton getBtnComprobar() {
		if (btnComprobar == null) {
			btnComprobar = new JButton(parent.getTextos().getString("ComprobarDisp"));
			btnComprobar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(parent.getControlador().nombreDisponible(txtUser.getText()))
						txtUser.setBackground(Color.GREEN);
					
					else
						txtUser.setBackground(Color.RED);
					
				}
			});
			//btnComprobar.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnComprobar.setForeground(Color.WHITE);
			btnComprobar.setBackground(Color.decode("#232f3e"));
		}
		return btnComprobar;
	}
	private JPanel getPnCentro3() {
		if (pnCentro3 == null) {
			pnCentro3 = new JPanel();
			pnCentro3.setLayout(new BorderLayout(10, 10));
			pnCentro3.setBackground(Color.decode("#e3e3e3"));
			pnCentro3.add(getPnContra(), BorderLayout.WEST);
			pnCentro3.add(getPnDatosCompra(), BorderLayout.CENTER);
			pnCentro3.add(getPnSouth(), BorderLayout.SOUTH);
		}
		return pnCentro3;
	}
	private JPanel getPnContra() {
		if (pnContra == null) {
			pnContra = new JPanel();
			pnContra.setBackground(Color.decode("#e3e3e3"));
			pnContra.setLayout(new BorderLayout(0, 5));
			pnContra.add(getPnTituloContra(), BorderLayout.NORTH);
			pnContra.add(getPnCT(), BorderLayout.CENTER);
		}
		return pnContra;
	}
	private JPanel getPnTituloContra() {
		if (pnTituloContra == null) {
			pnTituloContra = new JPanel();
			pnTituloContra.setBackground(Color.WHITE);
			pnTituloContra.setLayout(new BorderLayout(0, 0));
			pnTituloContra.add(getLblContrasea());
		}
		return pnTituloContra;
	}
	private JLabel getLblContrasea() {
		if (lblContrasea == null) {
			lblContrasea = new JLabel(parent.getTextos().getString("Contraseña"));
			lblContrasea.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblContrasea.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		}
		return lblContrasea;
	}
	private JPanel getPnCambiar() {
		if (pnCambiar == null) {
			pnCambiar = new JPanel();
			pnCambiar.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnCambiar.setBackground(Color.WHITE);
			pnCambiar.setLayout(new GridLayout(2, 3, 10, 5));
			pnCambiar.add(getLblContraseaActual());
			pnCambiar.add(getLblNuevaContrasea());
			pnCambiar.add(getLblRepetirContrasea());
			pnCambiar.add(getTxtActual());
			pnCambiar.add(getTxtNueva());
			pnCambiar.add(getTxtRep());
		}
		return pnCambiar;
	}
	private JLabel getLblContraseaActual() {
		if (lblContraseaActual == null) {
			lblContraseaActual = new JLabel(parent.getTextos().getString("ContraAct"));
			lblContraseaActual.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblContraseaActual;
	}
	private JLabel getLblNuevaContrasea() {
		if (lblNuevaContrasea == null) {
			lblNuevaContrasea = new JLabel(parent.getTextos().getString("ContraNuev"));
			lblNuevaContrasea.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblNuevaContrasea;
	}
	private JLabel getLblRepetirContrasea() {
		if (lblRepetirContrasea == null) {
			lblRepetirContrasea = new JLabel(parent.getTextos().getString("ContraRep"));
			lblRepetirContrasea.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblRepetirContrasea;
	}
	private JPasswordField getTxtRep() {
		if (txtRep == null) {
			txtRep = new JPasswordField();
		}
		return txtRep;
	}
	private JPasswordField getTxtNueva() {
		if (txtNueva == null) {
			txtNueva = new JPasswordField();
		}
		return txtNueva;
	}
	private JPasswordField getTxtActual() {
		if (txtActual == null) {
			txtActual = new JPasswordField();
			txtActual.setPreferredSize(new Dimension(0, 30));
			txtActual.setColumns(20);
		}
		return txtActual;
	}
	private JPanel getPnCT() {
		if (pnCT == null) {
			pnCT = new JPanel();
			pnCT.setLayout(new BorderLayout(0, 0));
			pnCT.setBackground(Color.decode("#e3e3e3"));
			pnCT.add(getPnCambiar(), BorderLayout.NORTH);
		}
		return pnCT;
	}
	private JPanel getPnDatosCompra() {
		if (pnDatosCompra == null) {
			pnDatosCompra = new JPanel();
			pnDatosCompra.setBackground(Color.decode("#e3e3e3"));
			pnDatosCompra.setLayout(new BorderLayout(5, 5));
			pnDatosCompra.add(getPanel_1(), BorderLayout.NORTH);
			pnDatosCompra.add(getPanel_2(), BorderLayout.CENTER);
		}
		return pnDatosCompra;
	}
	private JPanel getPanel_1() {
		if (pnTituloCompra == null) {
			pnTituloCompra = new JPanel();
			pnTituloCompra.setBackground(Color.WHITE);
			pnTituloCompra.setLayout(new BorderLayout(0, 0));
			pnTituloCompra.add(getLblDatosFacturacin());
		}
		return pnTituloCompra;
	}
	private JLabel getLblDatosFacturacin() {
		if (lblDatosFacturacin == null) {
			lblDatosFacturacin = new JLabel(parent.getTextos().getString("DatosFact"));
			lblDatosFacturacin.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			lblDatosFacturacin.setBorder(new EmptyBorder(10, 10, 10, 10));
		}
		return lblDatosFacturacin;
	}
	private JPanel getPanel_2() {
		if (pnFacCentro == null) {
			pnFacCentro = new JPanel();
			pnFacCentro.setBackground(Color.decode("#e3e3e3"));
			pnFacCentro.setLayout(new BorderLayout(0, 0));
			pnFacCentro.add(getPnDatos(), BorderLayout.NORTH);
		}
		return pnFacCentro;
	}
	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnDatos.setBackground(Color.WHITE);
			pnDatos.setLayout(new GridLayout(2, 2, 10, 5));
			pnDatos.add(getLblTarjeta());
			pnDatos.add(getLblTelefono());
			pnDatos.add(getTxtTarjeta());
			pnDatos.add(getTxtTelefono());
		}
		return pnDatos;
	}
	private JLabel getLblTelefono() {
		if (lblTelefono == null) {
			lblTelefono = new JLabel(parent.getTextos().getString("Telefono"));
			lblTelefono.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblTelefono;
	}
	private JLabel getLblTarjeta() {
		if (lblTarjeta == null) {
			lblTarjeta = new JLabel(parent.getTextos().getString("Tarjeta"));
			lblTarjeta.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		}
		return lblTarjeta;
	}
	private JTextField getTxtTarjeta() {
		if (txtTarjeta == null) {
			txtTarjeta = new JTextField();
			txtTarjeta.setColumns(10);
			txtTarjeta.setPreferredSize(new Dimension(0,30));
		}
		return txtTarjeta;
	}
	private JTextField getTxtTelefono() {
		if (txtTelefono == null) {
			txtTelefono = new JTextField();
			txtTelefono.setColumns(10);
		}
		return txtTelefono;
	}
	private JPanel getPnSouth() {
		if (pnSouth == null) {
			pnSouth = new JPanel();
			pnSouth.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnSouth.setBackground(Color.WHITE);
			pnSouth.add(getBtnGuardar());
			pnSouth.add(getBtnCancelar());
		}
		return pnSouth;
	}
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton(parent.getTextos().getString("Guardar"));
			btnGuardar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean nameCheck = false;
					boolean passCheck = false;
					boolean passBlanco = false;
					boolean tarjTlf = false;
					
					if(!txtUser.getText().equals(c.getUsuario()) && txtUser.getText().length()>5){
						if(parent.getControlador().nombreDisponible(txtUser.getText()))
							nameCheck = true;
					}
					
					else
						nameCheck = false;
					
					if(new String(txtActual.getPassword()).equals("") && new String(txtNueva.getPassword()).equals("") && new String(txtRep.getPassword()).equals("")){
						passCheck = true;
						passBlanco = true;
					}
					
					else if(new String(txtActual.getPassword()).equals(new String(c.getPassword())) && !new String(txtNueva.getPassword()).equals("") && new String(txtNueva.getPassword()).equals(new String(txtRep.getPassword())) && parent.getControlador().passwordValida(txtNueva.getPassword())){
						passCheck = true;
					}
					
					if(!txtTarjeta.getText().equals("") && parent.getControlador().checkTelefono(txtTelefono.getText()))
						tarjTlf = true;
					
					if(passCheck && nameCheck && tarjTlf){
						c.setUsuario(txtUser.getText());
						
						if(!passBlanco)
							c.setPassword(new String(txtNueva.getPassword()));
						
						c.setTarjetaBanco(txtTarjeta.getText());
						c.setTelefono(Integer.valueOf(txtTelefono.getText()));
						
						((panelCabecera)parent.getCabecera()).nuevoLog(parent.getControlador().loggedClient);
						
						JOptionPane.showMessageDialog(parent, parent.getTextos().getString("GuardExito"));
					}
					
					else{
						JOptionPane.showMessageDialog(parent, parent.getTextos().getString("DatosIncorrectos"));
					}
					
				}
			});
			btnGuardar.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnGuardar.setForeground(Color.WHITE);
			btnGuardar.setBackground(Color.decode("#232f3e"));
			btnGuardar.setPreferredSize(new Dimension(230, 60));
		}
		return btnGuardar;
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
		}
		return btnCancelar;
	}
}
