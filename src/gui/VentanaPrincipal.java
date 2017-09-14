package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import extensionesGui.*;
import logica.Articulo;
import logica.Cliente;
import logica.Controlador;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.help.*;
import java.net.*;
import java.io.*;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	//Controlador logica
	private Controlador cont = new Controlador();

	//Cambios de panel
	private int last;

	final static int lastP = 0;
	final static int lastR = 1;
	final static int lastU = 2;
	final static int lastC = 3;
	final static int lastV = 4;

	//Componentes
	private JPanel contentPane;
	private JPanel centralPanel;
	private panelCabecera cabecera;
	private panelPrincipal principal;
	private panelRegistro registro;
	private panelUsuario usuario;
	private panelCatalogo catalogo;
	private panelArticuloVenta venta;
	private panelNorthCategorias categorias;
	private panelCarrito carrito;
	private panelFinalizarCompra finalizar;
	private panelTerminar terminar;

	//Traduccion
	private ResourceBundle textos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void cargaLenguaje(){
		Locale localizacion = Locale.getDefault(Locale.Category.FORMAT);
		textos = ResourceBundle.getBundle("properties/language", localizacion);
	}

	public void changeIdioma(int id){
		Locale localizacion;

		if(id == 1)
			localizacion = new Locale("es", "ES");

		else
			localizacion = new Locale("en", "US");

		textos = ResourceBundle.getBundle("properties/language", localizacion);

		contentPane.removeAll();

		if(cabecera!=null){
			cabecera = new panelCabecera(this);
		}

		if(principal!=null){
			principal = new panelPrincipal(this);
		}

		if(usuario!=null){
			usuario = new panelUsuario(this);
		}

		if(catalogo!=null){
			catalogo = new panelCatalogo(this);
		}

		if(venta!=null){
			venta = new panelArticuloVenta(this);
		}	

		if(categorias!=null){
			categorias = new panelNorthCategorias(this);
		}

		if(carrito!=null){
			carrito = new panelCarrito(this);
		}

		if(finalizar!=null){
			finalizar = new panelFinalizarCompra(this);
		}

		if(terminar!=null){
			terminar = new panelTerminar(this);
		}

		contentPane.add(getCabecera(), BorderLayout.NORTH);
		contentPane.add(getCentralPanel(), BorderLayout.CENTER);

		actualizarCarrito();
		centralPanel.removeAll();
		changePrincipal();

	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/imgAplicacion/logo60.png")));
		setTitle("eII Market");
		setLocationRelativeTo(null);
		cargaLenguaje();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 956, 580);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#ebeced"));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(getCabecera(), BorderLayout.NORTH);
		contentPane.add(getCentralPanel(), BorderLayout.CENTER);
		setContentPane(contentPane);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cont.actualizaClientes();
				System.exit(0);
			}

		});

		changePrincipal();
		cargaAyuda();

		last = 0;
	}

	public JPanel getCatalogo() {
		if(catalogo==null){
			catalogo = new panelCatalogo(this);
		}

		return catalogo;
	}

	public JPanel getCentralPanel() {
		if(centralPanel==null){
			centralPanel = new JPanel();
			centralPanel.setLayout(new BorderLayout(0, 0));
		}

		return centralPanel;
	}

	public JPanel getVenta() {
		if(venta==null) {
			venta = new panelArticuloVenta(this);
		}

		return venta;
	}

	public JPanel getTerminar(){
		if(terminar == null){
			terminar = new panelTerminar(this);
		}

		return terminar;
	}

	public JPanel getCarrito(){
		if(carrito==null){
			carrito = new panelCarrito(this);
		}

		return carrito;
	}

	public JPanel getCabecera(){
		if(cabecera==null){
			cabecera = new panelCabecera(this);
		}

		return cabecera;
	}

	public JPanel getPrincipal(){
		if(principal==null){
			principal = new panelPrincipal(this);
		}

		return principal;
	}

	public JPanel getRegistro(){
		if(registro==null){
			registro = new panelRegistro(this);
		}

		return registro;
	}

	public JPanel getUsuario(){
		if(usuario==null){
			usuario = new panelUsuario(this);
		}

		return usuario;
	}

	public JPanel getCategorias() {
		if(categorias==null) {
			categorias = new panelNorthCategorias(this);
		}

		return categorias;
	}

	public JPanel getFinalizar() {
		if(finalizar == null){
			finalizar = new panelFinalizarCompra(this);
		}

		return finalizar;
	}

	public void changeRegistro(){
		getCentralPanel().removeAll();

		((panelRegistro)getRegistro()).clearAll();
		centralPanel.add(getRegistro(), BorderLayout.CENTER);

		contentPane.revalidate();
		contentPane.repaint();

		last = 1;
	}

	public void changePrincipal(){
		getCentralPanel().removeAll();

		centralPanel.add(getPrincipal(), BorderLayout.CENTER);

		contentPane.revalidate();
		contentPane.repaint();

		last = 0;
	}

	public void changeUsuario(){
		getCentralPanel().removeAll();

		centralPanel.add(getUsuario(), BorderLayout.CENTER);

		usuario.cargaCliente(cont.getLogged());
		contentPane.revalidate();
		contentPane.repaint();

		last = 2;
	}

	public void changeCatalogo(){
		getCentralPanel().removeAll();

		centralPanel.add(getCategorias(), BorderLayout.NORTH);
		centralPanel.add(getCatalogo(), BorderLayout.CENTER);

		contentPane.revalidate();
		contentPane.repaint();
		((panelCabecera)cabecera).hideAtChange();

		last = 3;
	}

	public void changeVenta(Articulo a) {
		getCentralPanel().removeAll();

		((panelArticuloVenta)getVenta()).actualizaPanel(a);
		centralPanel.add(getCategorias(), BorderLayout.NORTH);
		centralPanel.add(getVenta(), BorderLayout.CENTER);

		contentPane.revalidate();
		contentPane.repaint();

		((panelCabecera)cabecera).hideAtChange();

	}

	public void changeCarrito(){
		getCentralPanel().removeAll();

		((panelCarrito)getCarrito()).cargaCarrito();
		centralPanel.add(getCarrito(), BorderLayout.CENTER);

		centralPanel.revalidate();
		centralPanel.repaint();

		last = 4;
	}

	public void changeFinalizarCompra(){
		getCentralPanel().removeAll();

		((panelFinalizarCompra)getFinalizar()).actualizaDatos();

		centralPanel.add(getFinalizar(), BorderLayout.CENTER);

		centralPanel.revalidate();
		centralPanel.repaint();
	}

	public void changeTerminarCompra(){
		getCentralPanel().removeAll();

		centralPanel.add(getTerminar(), BorderLayout.CENTER);

		centralPanel.revalidate();
		centralPanel.repaint();
	}

	public void updateTerminar(String nombre, String apellidos, String nif, int codigo){
		((panelTerminar)getTerminar()).cargaDatos(nombre, apellidos, nif, codigo);
	}

	public void nuevoLog(){
		Cliente log = cont.loggedClient;

		cabecera.nuevoLog(log);
		changePrincipal();

	}

	public void logout(){
		cont.logout();
		actualizarCarrito();
		changePrincipal();
		JOptionPane.showMessageDialog(this, "Sesión cerrada con exito");
	}

	public Controlador getControlador(){
		return cont;
	}

	public void actualizarCarrito(){
		panelCarritoPop temp = ((panelCarritoPop)((panelCabecera)cabecera).getPanelCarrito());
		temp.colocaCarrito(cont.getCarrito());
		temp.revalidate();
		temp.repaint();

		panelCarrito temp2 = ((panelCarrito)getCarrito());
		temp2.cargaCarrito();
		temp2.revalidate();
		temp2.repaint();

		cabecera.actualizaCarrito();
	}

	public void compraFinalizada(){
		cont.actualizaStock();
		((panelCabecera)cabecera).finCompra();
		cont.logout();
		actualizarCarrito();
		changePrincipal();
		JOptionPane.showMessageDialog(this, "Compra realizada con exito");
	}

	public ResourceBundle getTextos(){
		return textos;
	}

	private void cargaAyuda(){

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e){
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();

		hb.enableHelpKey(getRootPane(),"introduccion", hs);
		hb.enableHelp(this, "introduccion", hs);

	}
	
	public void changeLast(){
		
		if(last == lastP)
			changePrincipal();
		
		else if(last == lastR)
			changeRegistro();
		
		else if(last == lastU)
			changeUsuario();
		
		else if(last == lastC)
			changeCatalogo();
		
		else if(last == lastV)
			changeCarrito();
		
	}

}
