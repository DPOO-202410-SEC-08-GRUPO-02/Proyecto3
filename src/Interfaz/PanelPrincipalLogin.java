package Interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import CargadorGaleria.CargadorGaleria;
import CargadorGaleria.Galeria;
import Usuario.Administrador;
import Usuario.Cajero;
import Usuario.Comprador;
import Usuario.Operador;
import Usuario.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
	
	private JTextField txtlogin;
    private JTextField txtcontrasenia;
	
	private JButton btnSalida;
	private JButton btnLogin;
	
	public PanelPrincipalLogin()
	
    {
        setSize(1030,850);
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
        
        Color grisesito = new Color (230, 230, 230);
        Color grisesito2 = new Color (204, 204, 204);
        Color azulito = new Color (0, 103, 184);
        MatteBorder bordeInferior = new MatteBorder(0, 0, 2, 0, Color.gray);

        
//        Panel norte
        
        lbltituloGal= new JLabel("Bienvenido a la galeria");
        lbltituloGal.setFont(new Font("Arial", Font.BOLD, 42));
        panelNorte.add(lbltituloGal);
        panelNorte.setBorder(bordeInferior);
        panelNorte.setBackground(grisesito);
        
//        Panel sur
        

        btnSalida = new JButton("SALIR");
        btnSalida.setFont(new Font("Arial", Font.PLAIN, 25));
        btnSalida.setBackground(grisesito2);
        btnSalida.setForeground(Color.black);
        panelSur.add(btnSalida);
        btnSalida.addActionListener(this);
        btnSalida.setActionCommand("salir");

        btnLogin = new JButton("INGRESAR");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 25));
        btnLogin.setBackground(azulito);
        btnLogin.setForeground(Color.white);
        panelSur.add(btnLogin);
        btnLogin.addActionListener(this);
        btnLogin.setActionCommand("login");
        
        panelSur.setBackground(grisesito);
        
//        Panel central
        
        lbltituloLogin= new JLabel("Iniciar sesión");
        lbltituloLogin.setFont(new Font("Arial", Font.BOLD, 20));
        lbltituloLogin.setHorizontalAlignment(SwingConstants.CENTER);
        panelCentral.add(lbltituloLogin);
        
        subPanelCentral.setLayout(new GridLayout(3, 2, 10, 10));
        lbllogin= new JLabel("Usuario:" );
        lbllogin.setFont(new Font("Arial", Font.PLAIN, 22));
        lbllogin.setHorizontalAlignment(SwingConstants.CENTER);
        subPanelCentral.add(lbllogin);
        
        txtlogin = new JTextField();
        txtlogin.setFont(new Font("Calibri",Font.PLAIN, 17));
        subPanelCentral.add(txtlogin);
        
        lblcontrasenia= new JLabel("Contraseña:" );
        lblcontrasenia.setFont(new Font("Arial", Font.PLAIN, 22));
        lblcontrasenia.setHorizontalAlignment(SwingConstants.CENTER);
        subPanelCentral.add(lblcontrasenia);
        
        txtcontrasenia = new JPasswordField();
        subPanelCentral.add(txtcontrasenia);
        
        panelCentral.add(subPanelCentral);
        
        panelCentral.setBackground(grisesito);
        subPanelCentral.setBackground(grisesito);
        
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
		       			Administrador admin = (Administrador) usuario;
		       			
			        	PrincipalAdmin principalAdmin = new PrincipalAdmin(admin);
			        	
			        	Point location = getLocation();
			   		 	principalAdmin.setLocation(location);
			        	setVisible(false);
			   		 	principalAdmin.setVisible(true);

			        }
			        else if (tipo.equals("Operador"))
			        {
			        	Operador operador = (Operador) usuario;
			        	
			        	PrincipalEmpleado principalEmpleado = new PrincipalEmpleado(operador);
			        	
			        	Point location = getLocation();
			        	principalEmpleado.setLocation(location);
			        	setVisible(false);
			        	principalEmpleado.setVisible(true);
			        			        	
			        	
//			        	menuOperador();
			        }
			        else if (tipo.equals("Cajero"))
			        {
			        	Cajero cajero = (Cajero) usuario;
			        	
			        	PrincipalEmpleado principalEmpleado = new PrincipalEmpleado(cajero);
			        	
			        	Point location = getLocation();
			        	principalEmpleado.setLocation(location);
			        	setVisible(false);
			        	principalEmpleado.setVisible(true);
			        	

			        }
			        else if (tipo.equals("Comprador"))
			        {
			        	Comprador comprador = (Comprador) usuario;
			        	PrincipalComprador principalComprador= new PrincipalComprador(comprador);
			        	
			        	Point location = getLocation();
			   		 	principalComprador.setLocation(location);
			        	setVisible(false);
			   		 	principalComprador.setVisible(true);
			        }
			        
			        else if (tipo.equals("Propietario"))
			        {
			        	
			        	JOptionPane.showMessageDialog(null, "El usuario ingresado es un propietario, para esta entrega no se cuenta con consola");
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