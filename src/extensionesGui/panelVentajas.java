package extensionesGui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Insets;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import gui.VentanaPrincipal;
import java.awt.Font;

@SuppressWarnings("serial")
public class panelVentajas extends JPanel {
	private JTextArea txtrPrueba;
	private VentanaPrincipal parent;

	/**
	 * Create the panel.
	 */
	public panelVentajas(VentanaPrincipal v) {
		parent = v;
		
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(Color.decode("#e3e3e3"));
		setLayout(new BorderLayout(0, 0));
		add(getTxtrPrueba(), BorderLayout.NORTH);

	}

	private JTextArea getTxtrPrueba() {
		if (txtrPrueba == null) {
			txtrPrueba = new JTextArea();
			txtrPrueba.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
			txtrPrueba.setEditable(false);
			txtrPrueba.setMargin(new Insets(10,10,10,10));
			txtrPrueba.setText(parent.getTextos().getString("Ventajas"));
			txtrPrueba.setWrapStyleWord(true);
			txtrPrueba.setLineWrap(true);
		}
		return txtrPrueba;
	}
}
