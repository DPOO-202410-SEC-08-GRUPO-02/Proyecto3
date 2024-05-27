package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CargadorGaleria.Galeria;
import Inventario.Pieza;
import Usuario.Administrador;
import Usuario.Cliente;

public class InfoComprador extends JFrame implements ActionListener
{
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel subPanelOeste;
	
	private JLabel lblImagen;
	
	private JLabel lblTituloNombre;
    private JLabel lblNombre;
    private JLabel lblTituloId;
    private JLabel lblId;
    private JLabel lblTituloLogin;
    private JLabel lblLogin;
    private JLabel lblTituloCorreo;
    private JLabel lblCorreo;
    private JLabel lblTituloNumro;
    private JLabel lblNumero;
    private JLabel lblTituloTipo;
    private JLabel lblTipo;
    
    private JButton btnRegresar;
    
    private Administrador admin;
	
	public InfoComprador(String usuarioSTR, Administrador admin)
	{
		this.admin = admin;
		
		setSize(750,600);
		setTitle( "Galeria" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
//		setResizable( false );
		setLayout( new BorderLayout( ) );

		panelEste = new JPanel( );
		add(panelEste,BorderLayout.EAST);

		panelOeste = new JPanel( );
		add(panelOeste,BorderLayout.WEST);
		panelOeste.setLayout( new GridLayout( 2, 1 ) );
		
		subPanelOeste = new JPanel( );

		Cliente cliente = (Cliente) Galeria.getUsuario(usuarioSTR);

//		Info usuario

		String nombre = cliente.getNombre();
		String id = cliente.getID();
		String login = cliente.getLogin();
		String correo = cliente.getCorreo();
		String numero = (cliente.getNumero()) + "";
		String tipo = cliente.getTipo();

//		Panel Oeste

		lblImagen = new JLabel( );
		panelOeste.add(lblImagen);

		//imagen obra
		
		ImageIcon fOriginal = new ImageIcon("./datos/imagenes/" + nombre + ".png");
		
		System.out.println(("./datos/imagenes/" + nombre + ".png"));
		
        Image iOriginal = fOriginal.getImage();

        Image iNueva = iOriginal.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon fNueva = new ImageIcon(iNueva);

		lblImagen.setIcon( fNueva );
		
		//Subpanel con informacio
		
		subPanelOeste.setLayout( new GridLayout( 7, 2 ) );
		
		lblTituloNombre= new JLabel("Nombre:" );
		subPanelOeste.add(lblTituloNombre);
		lblNombre= new JLabel(nombre );
		subPanelOeste.add(lblNombre);
        
		lblTituloId= new JLabel("ID:" );
		subPanelOeste.add(lblTituloId);
		lblId= new JLabel(id );
		subPanelOeste.add(lblId);
		
		lblTituloLogin= new JLabel("Login:" );
		subPanelOeste.add(lblTituloLogin);
		lblLogin= new JLabel(login );
		subPanelOeste.add(lblLogin);
		
		lblTituloCorreo= new JLabel("Correo:" );
		subPanelOeste.add(lblTituloCorreo);
		lblCorreo= new JLabel(correo );
		subPanelOeste.add(lblCorreo);
		
		lblTituloNumro= new JLabel("Numero:" );
		subPanelOeste.add(lblTituloNumro);
		lblNumero= new JLabel( numero );
		subPanelOeste.add(lblNumero);
		
		lblTituloTipo= new JLabel("Tipo de cliente:" );
		subPanelOeste.add(lblTituloTipo);
		lblTipo= new JLabel(tipo );
		subPanelOeste.add(lblTipo);

		panelOeste.add(subPanelOeste);

		btnRegresar = new JButton("ANTERIOR" );
		subPanelOeste.add( btnRegresar );
		btnRegresar.addActionListener( this );
		btnRegresar.setActionCommand( "regresar" );

//		Panel Este
		
    	Map<String,Pieza> piezas = admin.piezasCompradas(cliente);
    	
    	if (piezas == null)
    	{
    		System.out.println("El cliente no tiene historial");
    	}
    	
    	else
    	{
	    	System.out.println("\nEste es el historial del cliente: \n");
	    	
	    	for (Map.Entry<String, Pieza> entry : piezas.entrySet()) 
	    	{
	    		String key = entry.getKey();
	            Pieza pieza = entry.getValue();		
	            
	            System.out.println("Pieza obtenida el: " + key);
	    		System.out.println("ID: " + pieza.getID() + " - Título: " + pieza.getTitulo() +
                " - Autor: " + pieza.getAutor() + " - Año: " + pieza.getAnio() +
                " - Técnica: " + pieza.getTecnica() + " - Precio: $" + pieza.getValor() + "\n");
            }
    	}
		
		
	}
	
	public void actionPerformed( ActionEvent e )
    {
		if(e.getActionCommand( ).equals("regresar"))
        {
			PrincipalAdmin principalAdmin = new PrincipalAdmin(admin);
        	
        	Point location = getLocation();
   		 	principalAdmin.setLocation(location);
        	setVisible(false);
   		 	principalAdmin.setVisible(true);
        }
    }
}
