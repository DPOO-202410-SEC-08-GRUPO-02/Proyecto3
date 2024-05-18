package Interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CargadorGaleria.Galeria;
import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.Usuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class PanelPrincipalLogin extends JFrame implements ActionListener
{
//	private PanelPrincipalLogin panelLogin;
	
	private JPanel panelCentral;
	private JPanel subPanelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JLabel lbltituloGal;
	private JLabel lbltituloLogin;
	private JLabel lbllogin;
	private JLabel lblcontrasenia;
	private JLabel lblespacio;
	
	private JTextField txtlogin;
    private JTextField txtcontrasenia;
	
	private JButton btnSalida;
	private JButton btnLogin;
	
	public PanelPrincipalLogin()
	
    {
        setSize(750,600);
        setTitle( "Galeria" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
//        setResizable( false );
        setLayout( new BorderLayout( ) );
        
        panelCentral = new JPanel( );
        add(panelCentral,BorderLayout.CENTER);
        panelCentral.setLayout( new GridLayout( 3, 1 ) );
        
        subPanelCentral = new JPanel( );
        
        panelNorte = new JPanel( );
        add(panelNorte,BorderLayout.NORTH);
        
        panelSur = new JPanel( );
        add(panelSur,BorderLayout.SOUTH);
        
//        Panel norte
        lbltituloGal= new JLabel("Bienvenido a la galeria");
        panelNorte.add(lbltituloGal);
        
//        Panel sur
        
        btnSalida = new JButton("SALIDA" );
        panelSur.add( btnSalida );
        btnSalida.addActionListener( this );
        btnSalida.setActionCommand( "salir" );
        
//        Panel central
        
        lbltituloLogin= new JLabel("Inicio de sesión");
        panelCentral.add(lbltituloLogin);
        
        subPanelCentral.setLayout( new GridLayout( 2, 1 ) );
        lbllogin= new JLabel("Login:" );
        subPanelCentral.add(lbllogin);
        
        txtlogin = new JTextField();
        subPanelCentral.add(txtlogin);
        
        panelCentral.add(subPanelCentral);
        
        subPanelCentral.setLayout( new GridLayout( 2, 1 ) );
        lblcontrasenia= new JLabel("Contraseña:" );
        subPanelCentral.add(lblcontrasenia);
        
        txtcontrasenia = new JTextField();
        subPanelCentral.add(txtcontrasenia);
        
        panelCentral.add(subPanelCentral);
        
        btnLogin = new JButton("ACEPTAR" );
        panelCentral.add( btnLogin );
        btnLogin.addActionListener( this );
        btnLogin.setActionCommand( "login" );
        
    }
	
	public void actionPerformed( ActionEvent e )
    {
		if(e.getActionCommand( ).equals("salir"))
        {
			JOptionPane.showMessageDialog( null, "Gracias por visitar la galeria" );  
			dispose();
        }
		else if(e.getActionCommand( ).equals("login"))
        {
			String login = txtlogin.getText();
			String contrasenia = txtcontrasenia.getText();
			
			Boolean esta = Galeria.existeUsuario("Majo");
			System.out.println(login);
			System.out.println(esta);
	    	if (esta == false)
	    		JOptionPane.showMessageDialog(null, "El usuario no existe, vuelva a intentarlo");
	    	else
	    	{
	    		Usuario usuario = (Usuario) Galeria.getUsuario(login);
	        	String password = usuario.getContraseña();
            
		        if (contrasenia.equals(password))
		       	{
		       		String tipo = usuario.getTipo();
		       		
		       		if (tipo.equals("Administrador"))
			        {
//			        	Administrador admin = (Administrador) usuario;
//			        	menuAdministrador(admin);
			        }
			        else if (tipo.equals("Operador"))
			        {
//			        	menuOperador();
			        }
			        else if (tipo.equals("Cajero"))
			        {
//			        	menuCajero();
			        }
			        else if (tipo.equals("Comprador"))
			        {
//			        	Comprador comprador = (Comprador) usuario;
//			        	menuComprador(comprador);
			        }
			        
			        else if (tipo.equals("Propietario"))
			        {
//			        	System.out.println("\nEl usuario ingresado es un propietario, para esta entrega no se cuenta con consola");
//			        	System.out.println("Ingrese otro usuario\n");
			        }
		       		
		       	}
		        	
		       	else 
		       	{
		       		JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
		       	}
	    	}
        }
		
    }
	
	public static void main (String[] args)
    {
		PanelPrincipalLogin inicio = new PanelPrincipalLogin( );
        inicio.setVisible( true );
        inicio.setLocationRelativeTo( null );
    }
	
}