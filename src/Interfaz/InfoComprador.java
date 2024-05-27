package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CargadorGaleria.Galeria;
import Usuario.Cliente;

public class InfoComprador extends JFrame //implements ActionListener
{
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel subPanelOeste;
	
	private JLabel lblImagen;
	
	public InfoComprador(String usuarioSTR)
	{
		setSize(750,600);
		setTitle( "Galeria" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
//		setResizable( false );
		setLayout( new BorderLayout( ) );

		panelEste = new JPanel( );
		add(panelEste,BorderLayout.EAST);

		panelOeste = new JPanel( );
		add(panelOeste,BorderLayout.WEST);
		panelOeste.setLayout( new GridLayout( 1, 2 ) );
		
		subPanelOeste = new JPanel( );

		Cliente cliente = (Cliente) Galeria.getUsuario(usuarioSTR);

//		Info usuario

		String nombre = cliente.getNombre();
		String id = cliente.getID();
		String login = cliente.getLogin();
		String correo = cliente.getCorreo();
		int numero = cliente.getNumero();
		String tipo = cliente.getTipo();

//		Panel Oeste

		lblImagen = new JLabel( );
		panelOeste.add(lblImagen);

		//imagen obra
		ImageIcon foto = new ImageIcon("./imagenes/" + nombre +".png" );
		lblImagen.setIcon( foto );

	}
}
