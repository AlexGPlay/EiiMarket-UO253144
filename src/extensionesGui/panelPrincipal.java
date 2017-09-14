package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;
import logica.Articulo;

import java.awt.GridLayout;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.CardLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

@SuppressWarnings("serial")
public class panelPrincipal extends JPanel {
	private JPanel pnOfertas;
	private JPanel pnContenido;
	private JLabel lblImagenOfertas;
	private JLabel lblFlechaIzda;
	private VentanaPrincipal parent;
	private JPanel pnFlechaIzda;
	private JPanel pnFlechaDcha;
	private JLabel lblFlechaDcha;
	private mouseLabel mL;
	private JPanel pnCategorias;
	private JPanel pnVisibles;
	private clickCategoria cC;
	private JPanel pnDestacados;
	private JLabel lblDestacados;
	private JPanel pnArticulosDestac;
	private JPanel pnGeneral;
	private JScrollPane scrollPane;
	private JPanel cardPanel;
	private JPanel pn1;
	private JPanel pn2;
	private JPanel pn3;
	private int card = 0;
	private JLabel lblImagenRegistro;
	private JLabel lblNueva;
	private Timer tim;

	/**
	 * Create the panel.
	 */
	public panelPrincipal(VentanaPrincipal v) {
		mL = new mouseLabel();
		cC = new clickCategoria();
		parent = v;
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		add(getScrollPane(), BorderLayout.CENTER);

		scrollPane.setPreferredSize(new Dimension(0, (int)pnGeneral.getPreferredSize().getHeight()));

		colocaDestacados();
		createTimer();
	}

	private JPanel getPnOfertas() {
		if (pnOfertas == null) {
			pnOfertas = new JPanel();
			pnOfertas.setBorder(new EmptyBorder(0, 0, 0, 0));
			pnOfertas.setBackground(Color.BLACK);
			pnOfertas.setLayout(new BorderLayout(0, 0));
			pnOfertas.add(getPnFlechaIzda(), BorderLayout.WEST);
			pnOfertas.add(getPnFlechaDcha(), BorderLayout.EAST);
			pnOfertas.add(getCardPanel(), BorderLayout.CENTER);
		}
		return pnOfertas;
	}
	private JPanel getPnContenido() {
		if (pnContenido == null) {
			pnContenido = new JPanel();
			pnContenido.setBorder(new EmptyBorder(20, 30, 20, 30));
			pnContenido.setBackground(Color.decode("#e3e3e3"));
			pnContenido.setLayout(new BorderLayout(0, 0));
			pnContenido.add(getPnCategorias(), BorderLayout.CENTER);
		}
		return pnContenido;
	}

	private Image adaptarImagen(String rutaImagen, int x, int y, boolean escalado){
		//pnImagen.setVisible(true);
		Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
		Image imgEscalada;
		int width = imgOriginal.getWidth(null);
		int heigth = imgOriginal.getHeight(null);

		if(escalado) {
			Dimension escala = getScaledDimension(new Dimension(width, heigth), new Dimension(x, y));
			imgEscalada = imgOriginal.getScaledInstance((int)escala.getWidth(), (int)escala.getHeight(), Image.SCALE_SMOOTH);
		}

		else
			imgEscalada = imgOriginal.getScaledInstance(x, y, Image.SCALE_FAST);

		return imgEscalada;
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

	private JLabel getLblImagenOfertas() {
		if (lblImagenOfertas == null) {
			lblImagenOfertas = new JLabel("");
			lblImagenOfertas.setHorizontalAlignment(SwingConstants.CENTER);

			parent.getControlador();

			lblImagenOfertas.setIcon(new ImageIcon(adaptarImagen("/imgAplicacion/" + parent.getControlador().getNumeroDescuento() + ".png", 700, 300, true)));

		}
		return lblImagenOfertas;
	}
	private JLabel getLblFlechaIzda() {
		if (lblFlechaIzda == null) {
			lblFlechaIzda = new JLabel("");
			lblFlechaIzda.setHorizontalAlignment(SwingConstants.LEFT);
			lblFlechaIzda.setVerticalAlignment(SwingConstants.CENTER);

			lblFlechaIzda.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					atras();
				}

			});

			lblFlechaIzda.setOpaque(false);
			lblFlechaIzda.setIcon(new ImageIcon(adaptarImagen("/imgAplicacion/FlechaIzda.png", 30, 100, false)));


		}
		return lblFlechaIzda;
	}

	private class mouseLabel extends MouseAdapter{

		@Override
		public void mouseEntered(MouseEvent e) {
			JPanel temp = (JPanel)e.getSource();
			temp.setBackground( new Color(255, 255, 255, 245) );

			pnOfertas.revalidate();
			pnOfertas.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JPanel temp = (JPanel)e.getSource();
			temp.setBackground( new Color(255, 255, 255, 50) );

			pnOfertas.revalidate();
			pnOfertas.repaint();
		}

	}

	private JPanel getPnFlechaIzda() {
		if (pnFlechaIzda == null) {
			pnFlechaIzda = new JPanel();
			pnFlechaIzda.setLayout(new BorderLayout(0, 0));
			pnFlechaIzda.setOpaque(true);
			pnFlechaIzda.addMouseListener(mL);
			pnFlechaIzda.setBackground(new Color(255,255,255,50));
			pnFlechaIzda.add(getLblFlechaIzda());
		}
		return pnFlechaIzda;
	}
	private JPanel getPnFlechaDcha() {
		if (pnFlechaDcha == null) {
			pnFlechaDcha = new JPanel();
			pnFlechaDcha.setLayout(new BorderLayout(0, 0));
			pnFlechaDcha.setOpaque(true);
			pnFlechaDcha.addMouseListener(mL);
			pnFlechaDcha.add(getLblFlechaDcha(), BorderLayout.CENTER);
			pnFlechaDcha.setBackground(new Color(255,255,255,50));
		}
		return pnFlechaDcha;
	}
	private JLabel getLblFlechaDcha() {
		if (lblFlechaDcha == null) {
			lblFlechaDcha = new JLabel("");
			lblFlechaDcha.setHorizontalAlignment(SwingConstants.CENTER);

			lblFlechaDcha.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					adelante();
				}

			});

			lblFlechaDcha.setOpaque(false);
			lblFlechaDcha.setIcon(new ImageIcon(adaptarImagen("/imgAplicacion/FlechaDcha.png", 30, 100, false)));
		}
		return lblFlechaDcha;
	}
	private JPanel getPnCategorias() {
		if (pnCategorias == null) {
			pnCategorias = new JPanel();
			pnCategorias.setBackground(Color.decode("#e3e3e3"));
			pnCategorias.setLayout(new BorderLayout(0, 0));
			pnCategorias.add(getPnVisibles(), BorderLayout.NORTH);
			pnCategorias.add(getPnDestacados(), BorderLayout.CENTER);
		}
		return pnCategorias;
	}
	private JPanel getPnVisibles() {
		if (pnVisibles == null) {
			pnVisibles = new JPanel();
			pnVisibles.setBackground(Color.decode("#e3e3e3"));
			pnVisibles.setLayout(new GridLayout(0, 5, 10, 0));

			panelCategoria cat1 = new panelCategoria("/imgAplicacion/iconoConsola.png", parent.getTextos().getString("Consolas"));
			cat1.setName("0");
			cat1.addMouseListener(cC);
			pnVisibles.add(cat1);

			panelCategoria cat2 = new panelCategoria("/imgAplicacion/iconoCamara.png", parent.getTextos().getString("Fotografia"));
			cat2.setName("1");
			cat2.addMouseListener(cC);
			pnVisibles.add(cat2);

			panelCategoria cat3 = new panelCategoria("/imgAplicacion/iconoMovil.png", parent.getTextos().getString("Telefonia"));
			cat3.setName("2");
			cat3.addMouseListener(cC);
			pnVisibles.add(cat3);

			panelCategoria cat4 = new panelCategoria("/imgAplicacion/iconoOrdenador.png", parent.getTextos().getString("Ordenadores"));
			cat4.setName("3");
			cat4.addMouseListener(cC);
			pnVisibles.add(cat4);

			panelCategoria cat5 = new panelCategoria("/imgAplicacion/iconoVideovigilancia.png", parent.getTextos().getString("Videovigilancia"));
			cat5.setName("4");
			cat5.addMouseListener(cC);
			pnVisibles.add(cat5);
		}
		return pnVisibles;
	}

	private class clickCategoria extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e) {

			JPanel temp = (JPanel)e.getSource();
			temp.setBackground(Color.WHITE);
			((panelCategoria)temp).getPnImagen().setBackground(Color.WHITE);
			int i = Integer.valueOf(temp.getName());

			parent.getControlador().generarFiltroCategoria(i);
			String[] str = {"0", temp.getName()};

			panelCatalogo temp2 = (panelCatalogo)parent.getCatalogo();
			temp2.generarCatalogo(parent.getControlador().getFiltroCategoria(), str);
			parent.changeCatalogo();
		}

	}


	private JPanel getPnDestacados() {
		if (pnDestacados == null) {
			pnDestacados = new JPanel();
			pnDestacados.setBorder(new EmptyBorder(30, 0, 0, 0));
			pnDestacados.setBackground(Color.decode("#e3e3e3"));
			pnDestacados.setLayout(new BorderLayout(0, 0));
			pnDestacados.add(getLblDestacados(), BorderLayout.NORTH);
			pnDestacados.add(getPnArticulosDestac(), BorderLayout.CENTER);
		}
		return pnDestacados;
	}
	private JLabel getLblDestacados() {
		if (lblDestacados == null) {
			lblDestacados = new JLabel(parent.getTextos().getString("Destacados"));
			lblDestacados.setHorizontalAlignment(SwingConstants.LEFT);
			lblDestacados.setBorder(null);
			lblDestacados.setFont(new Font("Tahoma", Font.PLAIN, 23));
		}
		return lblDestacados;
	}
	private JPanel getPnArticulosDestac() {
		if (pnArticulosDestac == null) {
			pnArticulosDestac = new JPanel();
			pnArticulosDestac.setBorder(new EmptyBorder(20, 0, 0, 0));
			pnArticulosDestac.setBackground(Color.decode("#e3e3e3"));
			pnArticulosDestac.setLayout(new GridLayout(0, 5, 10, 10));
		}
		return pnArticulosDestac;
	}

	private void colocaDestacados() {
		ArrayList<Articulo> destacados = parent.getControlador().getDestacados();

		for(int i=0;i<destacados.size();i++) {
			boolean descuento = false;
			if(destacados.get(i).getCategoria().equals(parent.getControlador().getDescuento().getNombre()))
				descuento = true;
			
			panelArticulo temp = new panelArticulo(destacados.get(i), parent, descuento);
			pnArticulosDestac.add(temp);
		}

	}

	private JPanel getPnGeneral() {
		if (pnGeneral == null) {
			pnGeneral = new JPanel();
			pnGeneral.setLayout(new BorderLayout(0, 0));
			pnGeneral.add(getPnContenido(), BorderLayout.CENTER);
			pnGeneral.add(getPnOfertas(), BorderLayout.NORTH);
		}
		return pnGeneral;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnGeneral());

			scrollPane.revalidate();
			scrollPane.repaint();
		}
		return scrollPane;
	}
	private JPanel getCardPanel() {
		if (cardPanel == null) {
			cardPanel = new JPanel();
			cardPanel.setLayout(new CardLayout(0, 0));
			cardPanel.add(getPn1(), "name_17848943845350");
			cardPanel.add(getPn2(), "name_17919256068327");
			cardPanel.add(getPn3(), "name_17928865724965");
			cardPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{getPn1(), getPn2(), getPn3()}));
		}
		return cardPanel;
	}
	private JPanel getPn1() {
		if (pn1 == null) {
			pn1 = new JPanel();
			pn1.setBackground(Color.BLACK);
			pn1.setLayout(new BorderLayout(0, 0));
			pn1.add(getLblImagenOfertas());
		}
		return pn1;
	}
	private JPanel getPn2() {
		if (pn2 == null) {
			pn2 = new JPanel();
			pn2.setBackground(Color.WHITE);
			pn2.setLayout(new BorderLayout(0, 0));
			pn2.add(getLblImagenRegistro(), BorderLayout.CENTER);
		}
		return pn2;
	}
	private JPanel getPn3() {
		if (pn3 == null) {
			pn3 = new JPanel();
			pn3.setBackground(Color.WHITE);
			pn3.setLayout(new BorderLayout(0, 0));
			pn3.add(getLblNueva(), BorderLayout.CENTER);
		}
		return pn3;
	}
	private JLabel getLblImagenRegistro() {
		if (lblImagenRegistro == null) {
			lblImagenRegistro = new JLabel();
			lblImagenRegistro.setHorizontalAlignment(SwingConstants.CENTER);
			lblImagenRegistro.setIcon(new ImageIcon(adaptarImagen("/imgAplicacion/regis.png", 700, 300, true)));
		}
		return lblImagenRegistro;
	}

	private void adelante() {
		CardLayout cardL = (CardLayout)cardPanel.getLayout();

		card++;

		if(card>2) {
			card = 0;
			cardL.first(cardPanel);
		}

		else
			cardL.next(cardPanel);
		
		tim.restart();
		cambiarFlechas();
	}

	private void atras() {
		CardLayout cardL = (CardLayout)cardPanel.getLayout();

		card--;

		if(card<0) {
			card = 2;
			cardL.last(cardPanel);
		}

		else
			cardL.previous(cardPanel);
		
		tim.restart();
		cambiarFlechas();
	}

	private void cambiarFlechas() {
		if(card==0) {
			pnFlechaIzda.setBackground(new Color(255,255,255,50));
			pnFlechaDcha.setBackground(new Color(255,255,255,50));
		}
		else{
			pnFlechaIzda.setBackground(new Color(255,255,255,25));
			pnFlechaDcha.setBackground(new Color(255,255,255,25));
		}

		pnOfertas.repaint();
	}
	private JLabel getLblNueva() {
		if (lblNueva == null) {
			lblNueva = new JLabel("");
			lblNueva.setIcon(new ImageIcon(panelPrincipal.class.getResource("/imgAplicacion/nueva.png")));
			lblNueva.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNueva;
	}
	
	private void createTimer() {
		
		tim = new Timer(10000, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				adelante();
			}
		});
		
		tim.start();
	}

}


