package extensionesGui;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;
import logica.Articulo;
import logica.ArticuloCarrito;

import java.awt.Dimension;

import javax.swing.JSpinner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class panelArticuloVenta extends JPanel {
	private JScrollPane scrollPane;
	private JPanel pnVista;
	private JPanel pnContenido;
	private JPanel pnProducto;
	private JPanel pnDescripcion;
	private JPanel pnImagen;
	private JPanel pnInformacion;
	private JLabel lblImagen;
	private JPanel pnNombrePrecio;
	private JLabel lblNombreProducto;
	private JLabel lblPrecio;
	private JPanel pnDatos;
	private JPanel pnTextos;
	private JPanel pnInfo;
	private JLabel lblPuntos;
	private JLabel lblCantidad;
	private JLabel lblDisponibilidad;
	private JLabel lblPtos;
	private JSpinner spnCantidad;
	private JLabel lblDispo;
	private JPanel pnBoton;
	private JPanel pnInteriorBtn;
	private JButton btnCarrito;
	private JPanel pnFinalDesc;
	private VentanaPrincipal parent;
	private SpinnerNumberModel model;

	private Articulo articulo;
	private JTextArea txtDescrip;
	private JPanel pnContenedor;
	private JLabel lblInvalido;
	private JButton btnComprar;
	private JPanel panel;
	private JPanel pnWest;
	private JPanel pnFlecha;
	private JLabel lblBack;

	private Timer timer;
	private JPopupMenu comprado;
	private JLabel estado;

	public panelArticuloVenta(VentanaPrincipal v) {
		parent = v;

		setMaximumSize(parent.getMaximumSize());

		setLayout(new BorderLayout(0, 0));
		setBackground(Color.decode("#ebeced"));
		add(getScrollPane(), BorderLayout.CENTER);

	}

	public void actualizaPanel(Articulo a) {

		articulo = a;

		ArticuloCarrito temp = parent.getControlador().articuloDetected(a);
		int restante = a.getStock();

		if(temp!=null)
			restante -= temp.getCantidad();

		btnComprar.setText(parent.getTextos().getString("Comprar"));
		btnCarrito.setText(parent.getTextos().getString("Añadir"));

		lblNombreProducto.setText(articulo.getDenominacion());
		DecimalFormat df = new DecimalFormat("0.00");
		lblPrecio.setText(df.format(articulo.getPrecio()) + " €");

		lblPtos.setText(String.valueOf( parent.getTextos().getString("Acumulacion") + articulo.getPuntos()) + " " + parent.getTextos().getString("PuntosMinus"));

		if(articulo.getStock()>0) {
			lblDispo.setText(parent.getTextos().getString("Quedan") + " " + articulo.getStock() + " " + parent.getTextos().getString("Unidades"));

			if(restante>0)
				model = new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(restante), new Integer(1));

			else
				model = new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(0), new Integer(1));

			spnCantidad.setModel(model);
			spnCantidad.setEnabled(true);
			btnComprar.setEnabled(true);
			btnCarrito.setEnabled(true);
		}

		else{
			lblDispo.setText(parent.getTextos().getString("Agotado"));
			model = new SpinnerNumberModel(new Integer(0), new Integer(0), new Integer(0), new Integer(0));
			spnCantidad.setModel(model);
			spnCantidad.setEnabled(false);
			spnCantidad.setEnabled(false);
			btnComprar.setEnabled(false);
			btnCarrito.setEnabled(false);
		}

		txtDescrip.setText(articulo.getDescripcion());

		Image produc = adaptarImagen(articulo.getImagen(), 390, 390);
		ImageIcon icon = new ImageIcon(produc);

		Image flecha = adaptarImagen("/imgAplicacion/FlechaIzda.png",50,50);
		ImageIcon icoFlecha = new ImageIcon(flecha);

		lblImagen.setIcon(icon);
		lblBack.setIcon(icoFlecha);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBackground(Color.decode("#e3e3e3"));
			scrollPane.setViewportView(getPnContenedor());
		}
		return scrollPane;
	}
	private JPanel getPnVista() {
		if (pnVista == null) {
			pnVista = new JPanel();
			pnVista.setBorder(new EmptyBorder(20, 0, 0, 0));
			pnVista.setBackground(Color.decode("#e3e3e3"));
			pnVista.add(getPnContenido());
		}
		return pnVista;
	}
	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setLayout(new BorderLayout(0, 0));
			pnContenido.add(getPnProducto(), BorderLayout.CENTER);
			pnContenido.add(getPnDescripcion(), BorderLayout.SOUTH);
		}
		return pnContenido;
	}
	private JPanel getPnProducto() {
		if (pnProducto == null) {
			pnProducto = new JPanel();
			pnProducto.setLayout(new GridLayout(0, 2, 10, 0));
			pnProducto.add(getPnImagen());
			pnProducto.add(getPnInformacion());
		}
		return pnProducto;
	}
	private JPanel getPnDescripcion() {
		if (pnDescripcion == null) {
			pnDescripcion = new JPanel();
			pnDescripcion.setBorder(new EmptyBorder(10, 0, 0, 0));
			pnDescripcion.setLayout(new BorderLayout(0, 0));
			pnDescripcion.setBackground(Color.decode("#e3e3e3"));
			pnDescripcion.add(getPnFinalDesc(), BorderLayout.NORTH);
		}
		return pnDescripcion;
	}
	private JPanel getPnImagen() {
		if (pnImagen == null) {
			pnImagen = new JPanel();
			pnImagen.setBackground(Color.WHITE);
			pnImagen.setLayout(new BorderLayout(0, 0));
			pnImagen.setPreferredSize(new Dimension(400, 400));
			pnImagen.add(getLblImagen());
		}
		return pnImagen;
	}
	private JPanel getPnInformacion() {
		if (pnInformacion == null) {
			pnInformacion = new JPanel();
			pnInformacion.setBackground(Color.decode("#e3e3e3"));
			pnInformacion.setLayout(new BorderLayout(0, 0));
			pnInformacion.add(getPnNombrePrecio(), BorderLayout.NORTH);
			pnInformacion.add(getPnDatos(), BorderLayout.CENTER);
			pnInformacion.add(getPnBoton(), BorderLayout.SOUTH);
		}
		return pnInformacion;
	}
	private JLabel getLblImagen() {
		if (lblImagen == null) {
			lblImagen = new JLabel("");
			lblImagen.setBorder(new EmptyBorder(10, 10, 10, 10));
			lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImagen;
	}
	private JPanel getPnNombrePrecio() {
		if (pnNombrePrecio == null) {
			pnNombrePrecio = new JPanel();
			pnNombrePrecio.setBackground(Color.WHITE);
			pnNombrePrecio.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnNombrePrecio.setLayout(new BorderLayout(0, 0));
			pnNombrePrecio.add(getLblNombreProducto(), BorderLayout.NORTH);
			pnNombrePrecio.add(getLblPrecio(), BorderLayout.SOUTH);
		}
		return pnNombrePrecio;
	}
	private JLabel getLblNombreProducto() {
		if (lblNombreProducto == null) {
			lblNombreProducto = new JLabel("New label");
			lblNombreProducto.setBorder(new EmptyBorder(0, 0, 10, 0));
			lblNombreProducto.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lblNombreProducto;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("New label");
			lblPrecio.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		return lblPrecio;
	}
	private JPanel getPnDatos() {
		if (pnDatos == null) {
			pnDatos = new JPanel();
			pnDatos.setBackground(Color.decode("#e3e3e3"));
			pnDatos.setBorder(new EmptyBorder(10, 0, 0, 0));
			pnDatos.setLayout(new BorderLayout(0, 0));
			pnDatos.add(getPnTextos(), BorderLayout.WEST);
			pnDatos.add(getPnInfo(), BorderLayout.CENTER);
		}
		return pnDatos;
	}
	private JPanel getPnTextos() {
		if (pnTextos == null) {
			pnTextos = new JPanel();
			pnTextos.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnTextos.setBackground(Color.WHITE);
			pnTextos.setLayout(new GridLayout(3, 0, 0, 10));
			pnTextos.add(getLblPuntos());
			pnTextos.add(getLblCantidad());
			pnTextos.add(getLblDisponibilidad());
		}
		return pnTextos;
	}
	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBorder(new EmptyBorder(10, 40, 10, 10));
			pnInfo.setBackground(Color.WHITE);
			pnInfo.setLayout(new GridLayout(3, 0, 0, 10));
			pnInfo.add(getLblPtos());
			pnInfo.add(getPanel());
			pnInfo.add(getLblDispo());
		}
		return pnInfo;
	}
	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel(parent.getTextos().getString("Puntos") + ": ");
		}
		return lblPuntos;
	}
	private JLabel getLblCantidad() {
		if (lblCantidad == null) {
			lblCantidad = new JLabel(parent.getTextos().getString("Cantidad") + ": ");
		}
		return lblCantidad;
	}
	private JLabel getLblDisponibilidad() {
		if (lblDisponibilidad == null) {
			lblDisponibilidad = new JLabel(parent.getTextos().getString("Stock") + ": ");
		}
		return lblDisponibilidad;
	}
	private JLabel getLblPtos() {
		if (lblPtos == null) {
			lblPtos = new JLabel("New label");
			lblPtos.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblPtos;
	}
	private JSpinner getSpnCantidad() {
		if (spnCantidad == null) {
			spnCantidad = new JSpinner();
			spnCantidad.setPreferredSize(new Dimension(60, 30));
			spnCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		}
		return spnCantidad;
	}
	private JLabel getLblDispo() {
		if (lblDispo == null) {
			lblDispo = new JLabel("New label");
			lblDispo.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblDispo;
	}
	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setBorder(new EmptyBorder(10, 0, 0, 0));
			pnBoton.setBackground(Color.decode("#e3e3e3"));
			pnBoton.setLayout(new BorderLayout(0, 0));
			pnBoton.add(getPnInteriorBtn(), BorderLayout.NORTH);
		}
		return pnBoton;
	}
	private JPanel getPnInteriorBtn() {
		if (pnInteriorBtn == null) {
			pnInteriorBtn = new JPanel();
			pnInteriorBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnInteriorBtn.setBackground(Color.WHITE);
			pnInteriorBtn.setLayout(new BorderLayout(10, 0));
			pnInteriorBtn.add(getBtnCarrito(), BorderLayout.CENTER);
			pnInteriorBtn.add(getLblInvalido(), BorderLayout.NORTH);
			pnInteriorBtn.add(getBtnComprar(), BorderLayout.WEST);
		}
		return pnInteriorBtn;
	}
	private JButton getBtnCarrito() {
		if (btnCarrito == null) {
			btnCarrito = new JButton(parent.getTextos().getString("Añadir"));
			btnCarrito.setBorder(new LineBorder(Color.GRAY, 2, true));
			btnCarrito.setBackground(Color.WHITE);
			btnCarrito.setForeground(Color.GRAY);
			btnCarrito.setPreferredSize(new Dimension(230, 60));
			btnCarrito.setFont(new Font("Lucida Grande", Font.BOLD, 16));

			btnCarrito.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseEntered(MouseEvent e){
					btnCarrito.setBackground(Color.GRAY);
					btnCarrito.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e){
					btnCarrito.setBackground(Color.WHITE);
					btnCarrito.setForeground(Color.GRAY);
				}

			});

			btnCarrito.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if((int)spnCantidad.getValue()>0){

						if(parent.getControlador().comprar(articulo, (int)spnCantidad.getValue())==1){
							getEstado().setText(parent.getTextos().getString("Excede"));
						}

						else{
							parent.actualizarCarrito();
							getEstado().setText(parent.getTextos().getString("Añadido"));
						}
					}

					else{
						getEstado().setText(parent.getTextos().getString("Invalida"));
					}

					getComprado().show(pnBoton, btnComprar.getX()+btnComprar.getWidth(), btnCarrito.getY()-35);
					getTimer().start();
				}
			});
		}
		return btnCarrito;
	}
	private JPanel getPnFinalDesc() {
		if (pnFinalDesc == null) {
			pnFinalDesc = new JPanel();
			pnFinalDesc.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnFinalDesc.setBackground(Color.WHITE);
			pnFinalDesc.setLayout(new BorderLayout(0, 0));
			pnFinalDesc.add(getTxtDescrip(), BorderLayout.NORTH);
		}
		return pnFinalDesc;
	}
	private JTextArea getTxtDescrip() {
		if (txtDescrip == null) {
			txtDescrip = new JTextArea();
			txtDescrip.setEditable(false);
			txtDescrip.setWrapStyleWord(true);
			txtDescrip.setLineWrap(true);
		}
		return txtDescrip;
	}

	private Dimension getScaledDimension(Dimension imagen, Dimension objetivo) {

		int imgWidth = imagen.width;
		int imgHeigth = imagen.height;
		int objWidth = objetivo.width;
		int objHeigth = objetivo.height;
		int width = imgWidth;
		int heigth = imgHeigth;

		if (imgWidth > objWidth) {
			width = objWidth;
			heigth = (width * imgHeigth) / imgWidth;
		}

		if (heigth > objHeigth) {
			heigth = objHeigth;
			width = (width * imgWidth) / imgHeigth;
		}

		return new Dimension(width, heigth);
	}

	private Image adaptarImagen(String rutaImagen, int x, int y){
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		int width = imgOriginal.getWidth(null);
		int heigth = imgOriginal.getHeight(null);

		Dimension escala = getScaledDimension(new Dimension(width, heigth), new Dimension(x,y));
		Image imgEscalada = imgOriginal.getScaledInstance((int)escala.getWidth(), (int)escala.getHeight(), Image.SCALE_SMOOTH);

		return imgEscalada;
	}

	private JPanel getPnContenedor() {
		if (pnContenedor == null) {
			pnContenedor = new JPanel();
			pnContenedor.setLayout(new BorderLayout(0, 0));
			pnContenedor.add(getPnVista(), BorderLayout.CENTER);
			pnContenedor.add(getPnWest(), BorderLayout.WEST);
		}
		return pnContenedor;
	}


	private JLabel getLblInvalido() {
		if (lblInvalido == null) {
			lblInvalido = new JLabel("");
			lblInvalido.setForeground(Color.RED);
			lblInvalido.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblInvalido;
	}
	private JButton getBtnComprar() {
		if (btnComprar == null) {
			btnComprar = new JButton(parent.getTextos().getString("Comprar"));
			btnComprar.setFont(new Font("Lucida Grande", Font.BOLD, 16));
			btnComprar.setForeground(Color.WHITE);
			btnComprar.setBackground(Color.decode("#232f3e"));
			btnComprar.setPreferredSize(new Dimension(230, 60));

			btnComprar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if((int)spnCantidad.getValue()>0){

						if(parent.getControlador().comprar(articulo, (int)spnCantidad.getValue())==1){
							parent.changeCarrito();
						}

						else{
							parent.actualizarCarrito();
							parent.changeCarrito();
						}
					}

					else{
						getEstado().setText(parent.getTextos().getString("Invalida"));
						getComprado().show(pnBoton, btnComprar.getX()+btnComprar.getWidth(), btnCarrito.getY()-35);
						timer.start();
					}
				}

			});
		}
		return btnComprar;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getSpnCantidad(), BorderLayout.WEST);
		}
		return panel;
	}
	private JPanel getPnWest() {
		if (pnWest == null) {
			pnWest = new JPanel();
			pnWest.setBackground(Color.decode("#e3e3e3"));
			pnWest.setLayout(new BorderLayout(0, 0));
			pnWest.add(getPnFlecha(), BorderLayout.NORTH);
		}
		return pnWest;
	}
	private JPanel getPnFlecha() {
		if (pnFlecha == null) {
			pnFlecha = new JPanel();
			pnFlecha.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnFlecha.setBackground(Color.WHITE);

			pnFlecha.addMouseListener(new MouseAdapter(){

				@Override
				public void mouseClicked(MouseEvent e){
					parent.changeLast();
				}

				@Override
				public void mouseEntered(MouseEvent e){
					pnFlecha.setBackground(Color.decode("#e3e3e3"));
				}

				@Override
				public void mouseExited(MouseEvent e){
					pnFlecha.setBackground(Color.WHITE);
				}

			});

			pnFlecha.add(getLblBack());
		}
		return pnFlecha;
	}
	private JLabel getLblBack() {
		if (lblBack == null) {
			lblBack = new JLabel("");
		}
		return lblBack;
	}

	private Timer getTimer(){

		if(timer==null){

			timer = new Timer(2000, new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					comprado.setVisible(false);
					timer.setDelay(2000);
					timer.stop();
				}

			});

		}

		return timer;
	}

	private JPopupMenu getComprado(){

		if(comprado==null){
			comprado = new JPopupMenu();
			comprado.setBackground(Color.WHITE);

			comprado.add(getEstado());
		}

		return comprado;
	}

	private JLabel getEstado(){

		if(estado==null){
			estado = new JLabel();
			estado.setBorder(new EmptyBorder(10,10,10,10));
		}

		return estado;
	}
}
