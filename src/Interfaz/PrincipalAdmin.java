package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import CargadorGaleria.Galeria;
import Usuario.Administrador;

public class PrincipalAdmin extends JFrame implements ActionListener
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
	
	private Administrador admin;
	
	public PrincipalAdmin(Administrador admin)
	{
		this.admin = admin;
		
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
        lbltituloGal= new JLabel("Bienvenida administradora");
        panelNorte.add(lbltituloGal);
        
//        Panel sur
        
        btnSalida = new JButton("SALIR" );
        panelSur.add( btnSalida );
        btnSalida.addActionListener( this );
        btnSalida.setActionCommand( "salir" );
        
//        Panel central
        
        panelCentral.setLayout(new GridLayout ( 2, 2));
        
        btnInfoCliente = new JButton("<html>Obtener el historial, piezas actuales y el valor de la colección de un cliente" );
        panelCentral.add( btnInfoCliente );
        btnInfoCliente.addActionListener( this );
        btnInfoCliente.setActionCommand( "infoCliente" );
        
        btnInventario = new JButton("<html>Cambiar la disponibilidad/estado de una pieza y agregar una pieza");
        panelCentral.add( btnInventario );
        btnInventario.addActionListener( this );
        btnInventario.setActionCommand( "inventario" );
        
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
		else if(e.getActionCommand( ).equals("infoCliente"))
        {
			String usuario = JOptionPane.showInputDialog( "Ingrese el nombre del usuario" );
			
			boolean esta = Galeria.existeUsuario(usuario);
			
			if (esta == false)
			{
				JOptionPane.showMessageDialog(null, "El usuario no existe, vuelva a intentarlo");
			}
			
			else
			{
				InfoComprador infoComprador = new InfoComprador(usuario, admin);
				
				Point location = getLocation();
				infoComprador.setLocation(location);
				setVisible(false);
				infoComprador.setVisible(true);
			
			}
        }
		else if(e.getActionCommand( ).equals("histArtista"))
        {
			HistArtista histArtista = new HistArtista();
			
			Point location = getLocation();
			histArtista.setLocation(location);
			setVisible(false);
			histArtista.setVisible(true);
        }
		else if(e.getActionCommand( ).equals("histPieza"))
        {
			HistPieza histPieza = new HistPieza();
			
			Point location = getLocation();
			histPieza.setLocation(location);
			setVisible(false);
			histPieza.setVisible(true);
        }
		else if(e.getActionCommand( ).equals("inventario"))
        {
			InventarioAdmin inventarioAdmin = new InventarioAdmin(admin);
			
			Point location = getLocation();
			inventarioAdmin.setLocation(location);
			setVisible(false);
			inventarioAdmin.setVisible(true);
        }
    }
}
