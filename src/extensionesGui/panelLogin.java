package extensionesGui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

import logica.Controlador;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class panelLogin extends JPanel {
	private JPanel pnContenedor;
	public JButton btnIdentificarse;
	private JLabel lblRegist;
	private JPanel pnSeparacion;
	public JLabel lblClic;
	private panelCabecera parent;
	private JPanel pnLog;
	public JTextField txtUser;
	public JPasswordField txtPass;
	private mouseClick mC;
	private focusChange fC;
	private logKey lK;
	private boolean grabbedFirst = false;
	private boolean clickedUser = false;
	private boolean clickedPass = false;
	private JPanel pnBoton;
	private JLabel lblError;
	
	private boolean focusPass = false;
	private boolean focusUser = false;

	/**
	 * Create the panel.
	 */
	public panelLogin(panelCabecera newParent) {
		mC = new mouseClick();
		fC = new focusChange();
		lK = new logKey();
		parent = newParent;
		setOpaque(false);
		setLayout(new BorderLayout(0, 0));
		add(getPnContenedor(), BorderLayout.CENTER);
	}

	private JPanel getPnContenedor() {
		if (pnContenedor == null) {
			pnContenedor = new JPanel();
			pnContenedor.setBackground(Color.WHITE);
			pnContenedor.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnContenedor.setLayout(new BorderLayout(0, 0));
			pnContenedor.add(getPnSeparacion(), BorderLayout.SOUTH);
			pnContenedor.add(getPnLog(), BorderLayout.NORTH);
			pnContenedor.add(getPnBoton(), BorderLayout.CENTER);
		}
		return pnContenedor;
	}
	private JButton getBtnIdentificarse() {
		if (btnIdentificarse == null) {
			btnIdentificarse = new JButton(parent.parent.getTextos().getString("Identificarse"));
			btnIdentificarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					login();
				}
			});
			btnIdentificarse.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnIdentificarse.setBackground(Color.decode("#232f3e"));
			btnIdentificarse.setForeground(Color.WHITE);
			btnIdentificarse.setFocusable(false);
		}
		return btnIdentificarse;
	}
	private JLabel getLblRegist() {
		if (lblRegist == null) {
			lblRegist = new JLabel(parent.parent.getTextos().getString("NuevoCliente"));
			lblRegist.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblRegist;
	}
	private JPanel getPnSeparacion() {
		if (pnSeparacion == null) {
			pnSeparacion = new JPanel();
			pnSeparacion.setBorder(new EmptyBorder(15, 0, 0, 0));
			pnSeparacion.setBackground(Color.WHITE);
			pnSeparacion.setLayout(new BorderLayout(0, 0));
			pnSeparacion.add(getLblRegist(), BorderLayout.WEST);
			pnSeparacion.add(getLblClic(), BorderLayout.EAST);
		}
		return pnSeparacion;
	}
	private JLabel getLblClic() {
		if (lblClic == null) {
			lblClic = new JLabel("  " + parent.parent.getTextos().getString("Registrate"));
			lblClic.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					parent.parent.changeRegistro();
				}
			});
			lblClic.setForeground(Color.BLUE);
		}
		return lblClic;
	}
	private JPanel getPnLog() {
		if (pnLog == null) {
			pnLog = new JPanel();
			pnLog.setBackground(Color.WHITE);
			pnLog.setLayout(new BorderLayout(0, 0));
			pnLog.add(getTxtUser(), BorderLayout.NORTH);
			pnLog.add(getTxtPass(), BorderLayout.SOUTH);
		}
		return pnLog;
	}
	private JTextField getTxtUser() {
		if (txtUser == null) {
			txtUser = new JTextField();
			txtUser.setName("user");
			txtUser.setForeground(Color.LIGHT_GRAY);
			txtUser.setText(parent.parent.getTextos().getString("Usuario"));
			txtUser.setColumns(10);
			txtUser.addMouseListener(mC);
			txtUser.addKeyListener(new keyChange());
			txtUser.addFocusListener(fC);
			txtUser.addKeyListener(lK);
		}
		return txtUser;
	}
	private JPasswordField getTxtPass() {
		if (txtPass == null) {
			txtPass = new JPasswordField();
			txtPass.setName("pass");
			txtPass.setText(parent.parent.getTextos().getString("Contraseña"));
			txtPass.setForeground(Color.LIGHT_GRAY);
			txtPass.setEchoChar((char) 0);
			txtPass.addMouseListener(mC);
			txtPass.addFocusListener(fC);
			txtPass.addKeyListener(lK);
		}
		return txtPass;
	}

	private void login(){
		Controlador cont = parent.parent.getControlador();
		char[] pass = txtPass.getPassword();
		String cPass = new String(pass);

		if(cont.login(txtUser.getText(), cPass)){
			parent.parent.nuevoLog();
			txtUser.setText("");
			txtPass.setText("");
		}

		else{
			lblError.setText(parent.parent.getTextos().getString("LogError"));
			lblError.setForeground(Color.RED);
			this.revalidate();
			this.repaint();
			parent.repaintPopup();
		}
	}
	
	private class logKey extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode()==KeyEvent.VK_ENTER){
				login();
			}

		}
	}

	private class mouseClick extends MouseAdapter{

		@Override
		public void mouseClicked(MouseEvent e){

			JTextField temp = (JTextField)e.getSource();

			if(temp.getName().equals("user")){
				if(!clickedUser){
					temp.setText("");
					temp.setForeground(Color.BLACK);
					clickedUser = !clickedUser;
				}
			}

			else{
				if(!clickedPass){
					JPasswordField tempP = (JPasswordField)e.getSource();
					tempP.setText("");
					tempP.setForeground(Color.BLACK);
					tempP.setEchoChar('●');
					clickedPass = !clickedPass;
				}
			}

		}
	}

	private class keyChange extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e){
			if(!clickedUser){
				JTextField temp = (JTextField)e.getSource();
				temp.setText("");
				temp.setForeground(Color.BLACK);
				clickedUser = !clickedUser;
			}
		}

	}

	private class focusChange extends FocusAdapter{
		
		@Override
		public void focusLost(FocusEvent e){
			JTextField temp = (JTextField)e.getSource();
			
			if(temp.getName().equals("user"))
				focusUser = false;
			
			
			else
				focusPass = false;
		}
		
		@Override
		public void focusGained(FocusEvent e){
			JTextField temp = (JTextField)e.getSource();

			if(temp.getName().equals("user")){
				if(!clickedUser && grabbedFirst){
					temp.setText("");
					temp.setForeground(Color.BLACK);
					clickedUser = !clickedUser;
				}
				
				else if(!clickedUser && !grabbedFirst)
					grabbedFirst = !grabbedFirst;
				
				focusUser = true;
			}

			else{
				if(!clickedPass){
					JPasswordField tempP = (JPasswordField)e.getSource();
					tempP.setText("");
					tempP.setForeground(Color.BLACK);
					tempP.setEchoChar('●');
					clickedPass = !clickedPass;
				}
				
				focusPass = true;
			}
		}
	}
	private JPanel getPnBoton() {
		if (pnBoton == null) {
			pnBoton = new JPanel();
			pnBoton.setBackground(Color.WHITE);
			pnBoton.setLayout(new BorderLayout(0, 0));
			pnBoton.add(getBtnIdentificarse());
			pnBoton.add(getLblError(), BorderLayout.NORTH);
		}
		return pnBoton;
	}
	private JLabel getLblError() {
		if (lblError == null) {
			lblError = new JLabel("");
			lblError.setBackground(Color.WHITE);
		}
		return lblError;
	}
	
	public boolean focused(){
		if(focusPass || focusUser)
			return true;
		
		return false;
	}
}
