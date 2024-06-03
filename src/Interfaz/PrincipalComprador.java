package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Artista.Artista;
import CargadorGaleria.Galeria;
import Inventario.Inventario;
import Inventario.Pieza;
import Inventario.Pintura;
import Usuario.Administrador;
import Usuario.Comprador;

public class PrincipalComprador extends JFrame implements ActionListener
{
	private JPanel panelCentral;
	private JPanel subPanelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JLabel lbltituloGal;
	
	private JButton btnSalida;
	private JButton btnInfoCliente;
	private JButton btnHistArtista;
	private JButton btnHistPieza;
	private JButton btnInventario;
	
	private Comprador comprador;
	
	
	public PrincipalComprador(Comprador comprador)
	{
		this.comprador = comprador;
		
		setSize(1030,850);
	    setTitle( "Galeria" );
	    setDefaultCloseOperation( EXIT_ON_CLOSE );
	//    setResizable( false );
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
        lbltituloGal= new JLabel("Bienvenido comprador ");
        panelNorte.add(lbltituloGal);
        
//        Panel sur
        
        btnSalida = new JButton("Cerrar Sesion" );
        panelSur.add( btnSalida );
        btnSalida.addActionListener( this );
        btnSalida.setActionCommand( "salir" );
        
//        Panel central
        
        panelCentral.setLayout(new GridLayout ( 2, 2));
        
        btnInfoCliente = new JButton("<html>Comprar una Pieza" );
        panelCentral.add( btnInfoCliente );
        btnInfoCliente.addActionListener( this );
        btnInfoCliente.setActionCommand( "Comprar Pieza" );
        
        btnInventario = new JButton("<html> Participar en la subasta de una pieza");
        panelCentral.add( btnInventario );
        btnInventario.addActionListener( this );
        btnInventario.setActionCommand( "Subasta" );
        
        btnHistArtista = new JButton("<html>Mirar la historia de un artista" );
        panelCentral.add( btnHistArtista );
        btnHistArtista.addActionListener( this );
        btnHistArtista.setActionCommand( "histArtista" );
        
        btnHistPieza = new JButton("<html>Mirar la historia de una pieza" );
        panelCentral.add( btnHistPieza );
        btnHistPieza.addActionListener( this );
        btnHistPieza.setActionCommand( "histPieza" );
        
	}	
	
	public void actionPerformed( ActionEvent e )
    {
		if(e.getActionCommand( ).equals("salir"))
        {
			PanelPrincipalLogin panelLogin = new PanelPrincipalLogin();
			
			Point location = getLocation();
   		 	panelLogin.setLocation(location);
			setVisible(false);
			dispose();
			panelLogin.setVisible(true);
        }
		else if(e.getActionCommand( ).equals("Comprar Pieza"))
        {
		String idPieza= JOptionPane.showInputDialog("Ingrese el id de la pieza que desea comprar:");
		
		Pieza pieza = Galeria.getPiezaInventario(idPieza);
		
		if (pieza != null) 
		{
			Compra comprar= new Compra(comprador, idPieza);
			
			Point location = getLocation();
			comprar.setLocation(location);
			setVisible(false);
			comprar.setVisible(true);
		} 
		
		else 
		{
		    JOptionPane.showMessageDialog(null, "El artista no existe en la galería.");
		}
			
			
        }
		else if(e.getActionCommand( ).equals("histArtista"))
        {
			String nomArtista = JOptionPane.showInputDialog("Ingrese el nombre del artista:");
			
			Artista artista = Galeria.getArtista(nomArtista);
			
			if (artista != null) 
			{
				HistArtista histArtista = new HistArtista(nomArtista);
				
				Point location = getLocation();
				histArtista.setLocation(location);
				setVisible(false);
				histArtista.setVisible(true);
			} 
			
			else 
			{
			    JOptionPane.showMessageDialog(null, "El artista no existe en la galería.");
			}
        }
		else if(e.getActionCommand( ).equals("histPieza"))
        {
			
			String Idpieza = JOptionPane.showInputDialog( "Ingrese el ID de la pieza" );
			
			Pieza pieza;
			pieza = (Pieza) Inventario.getPiezaInventario(Idpieza);
			
			
			if (pieza != null)
			{	
				HistPieza histPieza = new HistPieza(Idpieza);
				
				Point location = getLocation();
				histPieza.setLocation(location);
				setVisible(false);
				histPieza.setVisible(true);	
			}
			
			else
			{
				JOptionPane.showMessageDialog(null, "La pieza no existe, vuelve a intentarlo");
			}			
		
        }
		else if(e.getActionCommand( ).equals("Subasta"))
        {
        }
    }
}
