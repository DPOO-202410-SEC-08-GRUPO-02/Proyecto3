package Interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Usuario.Administrador;
import Usuario.Comprador;
import Usuario.Usuario;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
			try {
				CargadorGaleria.salvarInventario("./datos/Inventario.json");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog( null, "Error al guardar informacion del inventario" ); 
				e1.printStackTrace();
			}
    		try {
				CargadorGaleria.salvarArtistas("./datos/Artistas.json");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog( null, "Error al guardar informacion de los artistas" ); 
				e1.printStackTrace();
			}
    		try {
				CargadorGaleria.salvarUsuario("./datos/Usuarios.json");
			} catch (IOException e1) {
				JOptionPane.showMessageDialog( null, "Error al guardar informacion de los usuarios" );
				e1.printStackTrace();
			}
			dispose();
        }
		else if(e.getActionCommand( ).equals("login"))
        {
			String login = txtlogin.getText().trim();
			String contrasenia = txtcontrasenia.getText().trim();
			
			Boolean esta = Galeria.existeUsuario(login);
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
			        	PrincipalAdmin principalAdmin = new PrincipalAdmin();
			   		 	principalAdmin.setVisible(true);
			   		 	Point location = getLocation();
			   		 	principalAdmin.setLocation(location);
			   		 	setVisible(false);

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
		try {
			CargadorGaleria.cargarInventario("./datos/Inventario.json");
		} catch (IOException e) {
			JOptionPane.showMessageDialog( null, "Error al cargar informacion del inventario" );
			e.printStackTrace();
		}
    	try {
			CargadorGaleria.cargarArtista("./datos/Artistas.json");
		} catch (IOException e) {
			JOptionPane.showMessageDialog( null, "Error al cargar informacion de los artistas" );
			e.printStackTrace();
		}
    	try {
			CargadorGaleria.cargarUsuario("./datos/Usuarios.json");
		} catch (IOException e) {
			JOptionPane.showMessageDialog( null, "Error al cargar informacion de los usuarios" );
			e.printStackTrace();
		}
    	
		PanelPrincipalLogin inicio = new PanelPrincipalLogin( );
        inicio.setVisible( true );
        inicio.setLocationRelativeTo( null );
    }
	
}