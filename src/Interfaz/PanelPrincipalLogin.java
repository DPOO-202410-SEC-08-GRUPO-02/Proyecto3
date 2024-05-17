package Interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;

public class PanelPrincipalLogin extends JFrame
{
//	private PanelPrincipalLogin panelLogin;
	
	private JPanel panelCentral;
	private JPanel subPanelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	private JPanel panelEste;
	private JPanel panelOeste;
	
	private JLabel lbltituloGal;
	private JLabel lbltituloLogin;
	private JLabel lbllogin;
	private JLabel lblcontrasenia;
	private JLabel lblespacio;
	
	private JTextField txtlogin;
    private JTextField txtcontrasenia;
	
	private JButton btnSalida;
	
	public PanelPrincipalLogin()
	
    {
        setSize(750,600);
        setTitle( "Galeria" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
//        setResizable( false );
        setLayout( new BorderLayout( ) );
        
        panelCentral = new JPanel( );
        add(panelCentral,BorderLayout.CENTER);
        panelCentral.setLayout( new BorderLayout( ) );
        
        subPanelCentral = new JPanel( );
        
        panelNorte = new JPanel( );
        add(panelNorte,BorderLayout.NORTH);
        
        panelSur = new JPanel( );
        add(panelSur,BorderLayout.SOUTH);
        
        panelEste = new JPanel( );
        add(panelEste,BorderLayout.EAST);
        
        panelOeste = new JPanel( );
        add(panelOeste,BorderLayout.WEST);
        
//        Panel norte
        lbltituloGal= new JLabel("Bienvenido a la galeria");
        panelNorte.add(lbltituloGal);
        
//        Panel sur
        
        btnSalida = new JButton("SALIDA" );
        panelSur.add( btnSalida );
        
//      Panel este
        lblespacio= new JLabel("                                    ");
        panelEste.add(lblespacio);
      
//      Panel oeste
        
        lblespacio= new JLabel("                                    ");
        panelOeste.add( lblespacio );
        
//        Panel central
        
        lbltituloLogin= new JLabel("Inicio de sesión");
        panelCentral.add(lbltituloLogin,BorderLayout.NORTH);
        
        subPanelCentral.setLayout( new GridLayout( 2, 2 ) );
        lbllogin= new JLabel("Login:" );
        subPanelCentral.add(lbllogin);
        txtlogin = new JTextField();
        subPanelCentral.add(txtlogin);
        
        lblcontrasenia= new JLabel("Contraseña:" );
        subPanelCentral.add(lblcontrasenia);
        txtcontrasenia = new JTextField();
        subPanelCentral.add(txtcontrasenia);
        
        panelCentral.add(subPanelCentral,BorderLayout.SOUTH);
    }
	
	public static void main (String[] args)
    {
		PanelPrincipalLogin inicio = new PanelPrincipalLogin( );
        inicio.setVisible( true );
        inicio.setLocationRelativeTo( null );
    }
	
}