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
import Usuario.Empleado;

public class PrincipalEmpleado extends JFrame implements ActionListener 
{
    private JPanel panelCentral;
    private JPanel panelNorte;
    private JPanel panelSur;

    private JLabel lblTitulo;

    private JButton btnCerrarSesion;
    private JButton btnHistPieza;
	private JButton btnHistArtista;

    public PrincipalEmpleado(Empleado empleado) 
    {
        setSize(800, 600);
        setTitle("Interfaz Cajero");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelCentral = new JPanel();
        add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new GridLayout(2, 1));

        panelNorte = new JPanel();
        add(panelNorte, BorderLayout.NORTH);

        panelSur = new JPanel();
        add(panelSur, BorderLayout.SOUTH);

        // Panel norte
        lblTitulo = new JLabel("Bienvenido");
        panelNorte.add(lblTitulo);

        // Panel sur
        btnCerrarSesion = new JButton("Cerrar Sesión");
        panelSur.add(btnCerrarSesion);
        btnCerrarSesion.addActionListener(this);
        btnCerrarSesion.setActionCommand("salir");

        // Panel central
        
        btnHistArtista = new JButton("<html>Mirar la historia de un artista" );
        panelCentral.add( btnHistArtista );
        btnHistArtista.addActionListener( this );
        btnHistArtista.setActionCommand( "histArtista" );
        
        btnHistPieza = new JButton("<html>Mirar la historia de una pieza" );
        panelCentral.add( btnHistPieza );
        btnHistPieza.addActionListener( this );
        btnHistPieza.setActionCommand( "histPieza" );
        

    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getActionCommand().equals("salir")) 
        {
        	PanelPrincipalLogin panelLogin = new PanelPrincipalLogin();
			
			Point location = getLocation();
   		 	panelLogin.setLocation(location);
			setVisible(false);
			dispose();
			panelLogin.setVisible(true);

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
			pieza = (Pintura) Inventario.getPiezaInventario(Idpieza);
			
			
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
    }

}

