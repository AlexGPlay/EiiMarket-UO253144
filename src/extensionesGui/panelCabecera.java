package extensionesGui;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Locale;
import java.awt.Font;
import javax.swing.border.LineBorder;

import gui.VentanaPrincipal;
import logica.Articulo;
import logica.Cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class panelCabecera extends JPanel {
	private JPanel pnLogo;
	private JPanel pnBusqueda;
	private JPanel pnDatos;
	private JLabel lblLogo;
	private JPanel pnRegistro;
	private JPanel pnCarrito;
	private JLabel lblUsuario;
	private JLabel lblCarrito;
	private JTextField textBusqueda;
	private JButton btnBuscar;
	private JPanel pnTop;
	private JPanel pnBottom;
	private mouseLabels mL;
	private panelLogin pnLogin;
	private JPopupMenu popLogin;
	private JPopupMenu popCuenta;
	private JPopupMenu popBusqueda;
	private JPopupMenu popCarrito;
	private registroLabel rL;
	private panelCarritoPop panelCarrito;

	private boolean enterText = false;
	private boolean enterLog = false;
	private Timer timerOcLog;
	private Timer timerVisLog;

	private panelBarra busquedaBarra;
	
	private boolean enterPnCarrito = false;
	private boolean enterPopCarrito = false;
	private Timer timerOcCarrito;
	private Timer timerVisCarrito;

	public VentanaPrincipal parent;
	private JLabel lblPuntos;
	private registroPop rP;
	private ArrayList<Articulo> filtro;

	private mouseHandler mH;
	private mouseCarrito mC;
	private JLabel lblIdioma;
	private JPopupMenu popIdioma;
	private changeIdioma cI;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public panelCabecera() {
		this(null);
	}

	public panelCabecera(VentanaPrincipal ventana){
		rP = new registroPop();
		mH = new mouseHandler();
		mC = new mouseCarrito();
		cI = new changeIdioma();
		filtro = new ArrayList<Articulo>();
		parent = ventana;

		this.setBackground(Color.decode("#232f3e"));
		setLayout(new BorderLayout(0, 0));
		add(getPnLogo(), BorderLayout.WEST);
		add(getPnBusqueda(), BorderLayout.CENTER);
		add(getPnDatos(), BorderLayout.EAST);
		setImagenAdaptada(getBtnBuscar(), "/imgAplicacion/lupa.png");
		add(getPnTop(), BorderLayout.NORTH);
		add(getPnBottom(), BorderLayout.SOUTH);
		
		
		lblUsuario.setText(parent.getTextos().getString("MiCuenta"));
		lblCarrito.setText(parent.getTextos().getString("Carrito"));
	}

	private mouseLabels getMouseLabels(){
		if(mL==null){
			mL = new mouseLabels();
		}

		return mL;
	}

	private registroLabel getRegistroLabel(){

		if(rL==null){
			rL = new registroLabel();
		}

		return rL;
	}

	private JPanel getPnLogin(){

		if(pnLogin==null){
			pnLogin = new panelLogin(this);
			pnLogin.btnIdentificarse.addMouseListener(rP);
			pnLogin.lblClic.addMouseListener(rP);
			pnLogin.txtPass.addMouseListener(rP);
			pnLogin.txtUser.addMouseListener(rP);
		}

		return pnLogin;
	}



	private JPanel getPnLogo() {
		if (pnLogo == null) {
			pnLogo = new JPanel();
			pnLogo.setBorder(new EmptyBorder(0, 15, 0, 0));
			pnLogo.setLayout(new BorderLayout(0, 0));
			pnLogo.setBackground(Color.WHITE);
			pnLogo.add(getLblLogo(), BorderLayout.CENTER);
		}
		return pnLogo;
	}
	private JPanel getPnBusqueda() {
		if (pnBusqueda == null) {
			pnBusqueda = new JPanel();
			pnBusqueda.setBorder(new EmptyBorder(30, 50, 30, 50));
			pnBusqueda.setBackground(Color.WHITE);
			pnBusqueda.setLayout(new BorderLayout(0, 0));
			pnBusqueda.add(getTextBusqueda(), BorderLayout.CENTER);
			pnBusqueda.add(getBtnBuscar(), BorderLayout.EAST);
		}
		return pnBusqueda;
	}
	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBackground(Color.WHITE);
			pnDatos.setLayout(new BorderLayout(0, 0));
			pnDatos.add(getPnRegistro(), BorderLayout.WEST);
			pnDatos.add(getPnCarrito(), BorderLayout.EAST);
		}
		return pnDatos;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("");
			lblLogo.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					parent.changePrincipal();
				}
			});
			lblLogo.setIcon(new ImageIcon(panelCabecera.class.getResource("/imgAplicacion/logo60.png")));
		}
		return lblLogo;
	}
	private JPanel getPnRegistro() {
		if (pnRegistro == null) {
			pnRegistro = new JPanel();
			pnRegistro.setBorder(new EmptyBorder(20, 0, 20, 50));
			pnRegistro.setLayout(new BorderLayout(0, 0));
			pnRegistro.setBackground(Color.WHITE);
			pnRegistro.add(getLblUsuario());
			pnRegistro.add(getLblPuntos(), BorderLayout.SOUTH);
		}
		return pnRegistro;
	}
	private JPanel getPnCarrito() {
		if (pnCarrito == null) {
			pnCarrito = new JPanel();
			pnCarrito.setBorder(new EmptyBorder(20, 0, 20, 15));
			pnCarrito.setLayout(new BorderLayout(0, 0));
			pnCarrito.setBackground(Color.WHITE);
			pnCarrito.add(getLblCarrito());
		}
		return pnCarrito;
	}
	private JLabel getLblUsuario() {
		if (lblUsuario == null) {
			lblUsuario = new JLabel("New label");
			lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			lblUsuario.setBackground(Color.DARK_GRAY);
			lblUsuario.setFont(new Font("Arial Black", Font.BOLD, 12));
			lblUsuario.setForeground(Color.DARK_GRAY);
			lblUsuario.addMouseListener(getMouseLabels());
			lblUsuario.addMouseListener(getRegistroLabel());
			lblUsuario.setBorder(new LineBorder(Color.WHITE, 1));

		}
		return lblUsuario;
	}
	private JLabel getLblCarrito() {
		if (lblCarrito == null) {
			lblCarrito = new JLabel("New label");
			lblCarrito.setName("lblCarrito");
			lblCarrito.setBackground(Color.DARK_GRAY);
			lblCarrito.setIcon(new ImageIcon(panelCabecera.class.getResource("/imgAplicacion/carrito0.png")));
			lblCarrito.setForeground(Color.DARK_GRAY);
			lblCarrito.setFont(new Font("Arial Black", Font.BOLD, 12));
			lblCarrito.addMouseListener(getMouseLabels());
			lblCarrito.addMouseListener(mC);
			
			lblCarrito.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e){
					if(parent.getControlador().getCarrito().size()>0)
						parent.changeCarrito();
				}
				
			});
			
			lblCarrito.setBorder(new LineBorder(Color.WHITE, 1));
		}
		return lblCarrito;
	}
	private JTextField getTextBusqueda() {
		if (textBusqueda == null) {
			textBusqueda = new JTextField();
			textBusqueda.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
			textBusqueda.setBackground(Color.decode("#ebeced"));
			textBusqueda.setFont(new Font("Arial", Font.PLAIN, 15));
			textBusqueda.addKeyListener(new filtroBarra());

			textBusqueda.setColumns(10);
		}
		return textBusqueda;
	}
	
	private void busquedaBarra() {
		panelCatalogo temp = (panelCatalogo)parent.getCatalogo();
		String[] estado = {"1", textBusqueda.getText()};
		temp.generarCatalogo(filtro, estado);
		parent.changeCatalogo();
	}

	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton("");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!textBusqueda.getText().equals("") && filtro.size()>0){
						busquedaBarra();
					}
				}
			});
			btnBuscar.setBackground(Color.decode("#232f3e"));
			btnBuscar.setFont(new Font("Arial", Font.PLAIN, 11));
		}
		return btnBuscar;
	}

	private void setImagenAdaptada(JButton boton, String rutaImagen){
		this.setVisible(true);
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada = imgOriginal.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		boton.setIcon(new ImageIcon(imgEscalada));
		boton.setDisabledIcon(new ImageIcon(imgEscalada));
	}
	private JPanel getPnTop() {
		if (pnTop == null) {
			pnTop = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnTop.getLayout();
			flowLayout.setHgap(4);
			pnTop.setBackground(Color.decode("#232f3e"));
		}
		return pnTop;
	}
	private JPanel getPnBottom() {
		if (pnBottom == null) {
			pnBottom = new JPanel();
			pnBottom.setBackground(Color.decode("#232f3e"));
			pnBottom.setLayout(new BorderLayout(0, 0));
			pnBottom.add(getPanel(), BorderLayout.EAST);
		}
		return pnBottom;
	}

	private JPopupMenu getPopupLogin(){

		if(popLogin == null){
			popLogin = new JPopupMenu();
			popLogin.add(getPnLogin());
			popLogin.addMouseListener(rP);
			popLogin.setOpaque(true);
			popLogin.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		}

		return popLogin;
	}
	
	private JPopupMenu getPopupCarrito(){
		
		if(popCarrito == null){
			popCarrito = new JPopupMenu();
			popCarrito.setName("popCarrito");
			popCarrito.add(getPanelCarrito());
			popCarrito.setBackground(Color.WHITE);
			popCarrito.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			popCarrito.addMouseListener(mC);
		}
		
		return popCarrito;
	}
	
	public JPanel getPanelCarrito(){
		if(panelCarrito == null){
			panelCarrito = new panelCarritoPop(parent, mC);
			panelCarrito.setName("pC");
			panelCarrito.addMouseListener(mC);
		}
		
		
		return panelCarrito;
	}

	private JPopupMenu getPopupBusqueda(){

		if(popBusqueda ==null){
			popBusqueda = new JPopupMenu();
			popBusqueda.add(getBusquedaBarra());
			popBusqueda.setOpaque(true);
			popBusqueda.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
			popBusqueda.setFocusable(false);
		}

		return popBusqueda;
	}
	
	private JPopupMenu getPopIdiomas(){
		
		if(popIdioma == null){
			popIdioma = new JPopupMenu();
			
			JMenuItem item1 = new JMenuItem("ES");
			item1.setName("1");
			item1.addActionListener(cI);
			
			JMenuItem item2 = new JMenuItem("EN");
			item2.setName("2");
			item2.addActionListener(cI);
			
			popIdioma.add(item1);
			popIdioma.add(item2);
		}
		
		return popIdioma;
	}

	private JPopupMenu getPopCuenta(){

		if(popCuenta == null){
			popCuenta = new JPopupMenu();
			popCuenta.addMouseListener(rP);
			JMenuItem item1 = new JMenuItem(parent.getTextos().getString("MiCuenta"));
			item1.addMouseListener(rP);

			item1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parent.changeUsuario();
				}
			});

			popCuenta.add(item1);
			
			JMenuItem item3 = new JMenuItem(parent.getTextos().getString("CerrarSesion"));

			item3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logout();
				}
			});

			item3.addMouseListener(rP);
			popCuenta.add(item3);
		}

		return popCuenta;
	}

	private panelBarra getBusquedaBarra(){

		if(busquedaBarra == null){
			busquedaBarra = new panelBarra(parent);
		}

		return busquedaBarra;
	}

	private class mouseLabels extends MouseAdapter{

		@Override
		public void mouseEntered(MouseEvent e){
			JLabel label = (JLabel)e.getSource();
			label.setBorder(new LineBorder(Color.decode("#232f3e"), 1));

		}

		@Override
		public void mouseExited(MouseEvent e){
			JLabel label = (JLabel)e.getSource();
			label.setBorder(new LineBorder(Color.WHITE, 1));
		}
		
	}

	private class registroLabel extends MouseAdapter{

		@Override
		public void mouseEntered(MouseEvent e){
			getTimerVisLog().start();
			enterText = true;
		}

		@Override
		public void mouseExited(MouseEvent e){
			enterText = false;
			getTimerOcLog().start();

		}
	}

	private class registroPop extends MouseAdapter{

		@Override
		public void mouseEntered(MouseEvent e){
			enterLog = true;
		}

		@Override
		public void mouseExited(MouseEvent e){
			enterLog = false;
			getTimerOcLog().start();
		}

	}

	private void showPop(){
		int h = pnRegistro.getHeight();

		if(parent.getControlador().loggedClient==null)
			getPopupLogin().show(getLblUsuario(), pnRegistro.getX(), pnRegistro.getY() + h/2);

		else
			getPopCuenta().show(getLblUsuario(), pnRegistro.getX(), pnRegistro.getY() + h/2);
	}

	private void hidePop(){

		if(!enterLog && !enterText && parent.getControlador().loggedClient==null && !pnLogin.focused())
			getPopupLogin().setVisible(false);

		else if(!enterLog && !enterText && parent.getControlador().loggedClient!=null)
			getPopCuenta().setVisible(false);

	}
	
	private void hideCarrito(){
		if(!enterPnCarrito && !enterPopCarrito)
			getPopupCarrito().setVisible(false);
	}
	
	private void muestraCarrito(){
		getPopupCarrito().show(pnCarrito, lblCarrito.getX(), lblCarrito.getY() + lblCarrito.getHeight());
	}

	private Timer getTimerOcLog(){

		if(timerOcLog==null){
			timerOcLog = new Timer(100, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Timer t = (Timer)e.getSource();
					t.setDelay(100);
					hidePop();
					t.stop();
				}
			});
		}

		return timerOcLog;
	}
	
	private Timer getTimerOcCarrito(){
		
		if(timerOcCarrito==null){
			timerOcCarrito = new Timer(100, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					Timer t = (Timer)e.getSource();
					t.setDelay(100);
					hideCarrito();
					t.stop();
				}
				
			});
			
		}
		
		return timerOcCarrito;
	}
	
	private Timer getTimerVisCarrito(){
		
		if(timerVisCarrito==null){
			timerVisCarrito = new Timer(100, new ActionListener(){
				
				@Override
				public void actionPerformed(ActionEvent e){
					Timer t = (Timer)e.getSource();
					t.setDelay(100);
					muestraCarrito();
					t.stop();
				}
				
			});
		}
		
		return timerVisCarrito;
	}

	private Timer getTimerVisLog(){

		if(timerVisLog==null){
			timerVisLog = new Timer(100, new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					Timer t = (Timer)e.getSource();
					t.setDelay(100);
					showPop();
					t.stop();

				}
			});
		}

		return timerVisLog;
	}

	public void nuevoLog(Cliente log){
		lblUsuario.setText(log.getUsuario());
		lblPuntos.setText(String.valueOf(log.getPuntos()) + " puntos");
		popLogin.setVisible(false);
	}

	public void logout(){
		lblUsuario.setText("Mi cuenta");
		lblPuntos.setText("");
		parent.logout();
		getPopCuenta().setVisible(false);
	}
	
	public void finCompra(){
		lblUsuario.setText("Mi cuenta");
		lblPuntos.setText("");
		getPopCuenta().setVisible(false);
	}

	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel("");
		}
		return lblPuntos;
	}

	public void repaintPopup(){
		popLogin.setVisible(false);
		popLogin.revalidate();
		popLogin.repaint();
		popLogin.setVisible(true);
	}

	private class filtroBarra extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER && filtro.size()>0 && !textBusqueda.getText().equals("")){
				busquedaBarra();
			}
		}

		@Override
		public void keyReleased(KeyEvent e){
			JTextField temp = (JTextField)e.getSource();
			String t = temp.getText();

			if(t.length()>0){
				filtro = parent.getControlador().filtraTexto(t);
				if(filtro.size()>0){
					getBusquedaBarra().colocaArticulos(filtro);
					getPopupBusqueda().show(temp, temp.getX()/40, temp.getY() + temp.getHeight()/4);
					getBusquedaBarra().setArticulos(parent.getTextos().getString("MasArticulos"));
					getBusquedaBarra().getLblVerMsArticulos().addMouseListener(mH);
					getPopupBusqueda().revalidate();
					getPopupBusqueda().repaint();
				}

				else{
					getBusquedaBarra().setArticulos(parent.getTextos().getString("NoEncontrado"));
					getBusquedaBarra().colocaArticulos(filtro);
					getBusquedaBarra().getLblVerMsArticulos().removeMouseListener(mH);
					getPopupBusqueda().show(temp, temp.getX()/40, temp.getY() + temp.getHeight()/4);
					getBusquedaBarra().setVisible(false);
					getPopupBusqueda().revalidate();
					getPopupBusqueda().repaint();
					getBusquedaBarra().setVisible(true);
				}
			}

			else
				getPopupBusqueda().setVisible(false);
		}

	}

	public void hideAtChange(){
		getPopupBusqueda().setVisible(false);
	}

	private class mouseHandler extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e){
			if(!textBusqueda.getText().equals("")){
				busquedaBarra();
			}
		}
	}
	
	private class mouseCarrito extends MouseAdapter{
		
		@Override
		public void mouseEntered(MouseEvent e){
			JComponent temp = (JComponent)e.getSource();
			if(temp.getName().equals("lblCarrito")){
				getTimerVisCarrito().start();
				enterPnCarrito = true;
			}
			
			else
				enterPopCarrito = true;
			
		}
		
		@Override
		public void mouseExited(MouseEvent e){
			JComponent temp = (JComponent)e.getSource();
			if(temp.getName().equals("lblCarrito"))
				enterPnCarrito = false;
			
			else
				enterPopCarrito = false;
			
			getTimerOcCarrito().start();
		}
		
	}
	private JLabel getLblIdioma() {
		if (lblIdioma == null) {
			lblIdioma = new JLabel("New label");
			lblIdioma.setBorder(new EmptyBorder(5, 1, 5, 10));
			lblIdioma.setFont(new Font("Lucida Grande", Font.BOLD, 14));
			lblIdioma.setForeground(Color.WHITE);
			
			lblIdioma.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e){
					getPopIdiomas().show(panel, lblIdioma.getX() , lblIdioma.getY() + lblIdioma.getHeight());
				}
				
			});
			
			if(parent.getTextos().getLocale().equals(new Locale("es", "ES")))
				lblIdioma.setText("ES");
			
			
			else
				lblIdioma.setText("EN");
			
			
		}
		return lblIdioma;
	}
	
	private class changeIdioma implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e){
			JMenuItem item = (JMenuItem)e.getSource();

			if(item.getName().equals("1"))
				lblIdioma.setText("ES");
			
			else
				lblIdioma.setText("EN");
			
			parent.changeIdioma(Integer.valueOf(item.getName()));
		}
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getLblIdioma());
			panel.setBackground(Color.decode("#232f3e"));
		}
		return panel;
	}
	
	public void actualizaCarrito() {
		int s = parent.getControlador().carritoSize();
		
		if(s<10)
			lblCarrito.setIcon(new ImageIcon(panelCabecera.class.getResource("/imgAplicacion/carrito" + s + ".png")));
		
		else
			lblCarrito.setIcon(new ImageIcon(panelCabecera.class.getResource("/imgAplicacion/carrito10.png")));
	}
}
