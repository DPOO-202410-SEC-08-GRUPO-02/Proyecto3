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
import Usuario.Cajero;
import Usuario.Comprador;
import Usuario.Usuario;
import Compra.Compra;


public class PanelElejir extends JFrame implements ActionListener
{
	private JPanel panelCentral;
	private JPanel subPanelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JLabel lbltituloGal;
	
	private JButton btnSalida;
	private JButton btnEfectivo;
	private JButton btnTransferencia;
	private JButton btnTarjeta;
	private JButton btnPasarela;
	
	private Comprador comprador;
	private Administrador admin=Usuario.getAdmin();
	private Cajero cajero=Usuario.getCajero();
	private Pieza pieza;
	
	
	public PanelElejir(Comprador comprador, Pieza pieza)
	{
		this.comprador = comprador;
		this.pieza =pieza;
		
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
        lbltituloGal= new JLabel("Elija el metodo de Pago: ");
        panelNorte.add(lbltituloGal);
        
//        Panel sur
        
        btnSalida = new JButton("Cerrar Sesion" );
        panelSur.add( btnSalida );
        btnSalida.addActionListener( this );
        btnSalida.setActionCommand( "salir" );
        
//        Panel central
        
        panelCentral.setLayout(new GridLayout ( 2, 2));
        
        btnEfectivo = new JButton("<html>Pagar con efectivo" );
        panelCentral.add( btnEfectivo );
        btnEfectivo.addActionListener( this );
        btnEfectivo.setActionCommand( "efectivo" );
        
        btnTransferencia = new JButton("<html>Pagar por medio de transferencia electronica");
        panelCentral.add( btnTransferencia );
        btnTransferencia.addActionListener( this );
        btnTransferencia.setActionCommand( "transferencia" );
        
        btnTarjeta = new JButton("<html>Pagar con Tarjeta de credito en datafono" );
        panelCentral.add( btnTarjeta );
        btnTarjeta.addActionListener( this );
        btnTarjeta.setActionCommand( "tarjeta" );
        
        btnPasarela = new JButton("<html>Pagar con Pasarelas (tarjeta de credito)" );
        panelCentral.add( btnPasarela );
        btnPasarela.addActionListener( this );
        btnPasarela.setActionCommand( "pasarelas" );
        
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
		else if(e.getActionCommand( ).equals("efectivo"))
        {
			Compra.pasarCaja(comprador, pieza, "normal", admin, cajero, "efectivo", false);
			JOptionPane.showMessageDialog(null, "Compra realizada con exito");
			
        }
		else if(e.getActionCommand( ).equals("transferencia"))
        {
			Compra.pasarCaja(comprador, pieza, "normal", admin, cajero, "transferenciaElectronica", false);
			JOptionPane.showMessageDialog(null, "Compra realizada con exito");
        }
		else if(e.getActionCommand( ).equals("tarjeta"))
        {
			
			Compra.pasarCaja(comprador, pieza, "normal", admin, cajero, "tarjetaCredito", false);
			JOptionPane.showMessageDialog(null, "Compra realizada con exito");
		
        }
		else if(e.getActionCommand( ).equals("pasarelas"))
        {
			Compra.pasarCaja(comprador, pieza, "normal", admin, cajero, "tarjetaCredito", true);
			JOptionPane.showMessageDialog(null, "Compra realizada con exito");
        }
    }
}
