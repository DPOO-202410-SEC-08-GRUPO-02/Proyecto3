package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import CargadorGaleria.Galeria;
import Inventario.Pieza;
import Usuario.Administrador;

public class InventarioAdmin extends JFrame implements ActionListener
{
	private JPanel panelNorte;
	private JPanel panelCentral;
	private JPanel panelSur;
	private JPanel subPanelCentralCentro;
	private JPanel subPanelCentralEste;
	
	private JLabel lblImagen;

	private JLabel lblTituloPantalla;
	
	private JLabel lblTituloTitulo;
	private JLabel lblTitulo;
	private JLabel lblTituloAutor;
	private JLabel lblAutor;
	private JLabel lblTituloAnio;
	private JLabel lblAnio;
	private JLabel lblTituloTipo;
	private JLabel lblTipo;
	private JLabel lblTituloTecnica;
	private JLabel lblTecnica;
	private JLabel lblTituloEstado;
	private JLabel lblEstado;
	private JLabel lblTituloLugar;
	private JLabel lblLugar;
	private JLabel lblTituloDisponibilidad;
	private JLabel lblDisponibilidad;
	private JLabel lblTituloVendida;
	private JLabel lblVendida;
	private JLabel lblTituloPrecio;
	private JLabel lblPrecio;
	private JLabel lblTituloFecha;
	private JLabel lblFacha;
	private JLabel lblTituloDuenios;
	private JLabel lblDuenios;
	
	private JLabel lblEspacio;
	
	private JButton btnDisponibilidad;
	private JButton btnEstado;
	private JButton btnAnterior;
	private JButton btnAddPieza;
	private JButton btnRegresar;
	private JButton btnSiguiente;
	
	private String idActual = "a";
	
	private Administrador admin;
	
	public InventarioAdmin(Administrador admin) 
	{
		this.admin = admin;
		
		setSize(1030,850);
		setTitle( "Galeria" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
//		setResizable( false );
		setLayout( new BorderLayout( ) );
		
		panelNorte = new JPanel( );
		add(panelNorte,BorderLayout.NORTH);

		panelSur = new JPanel( );
		add(panelSur,BorderLayout.SOUTH);
		panelSur.setLayout( new GridLayout( 1, 4 ) );
		
		panelCentral = new JPanel( );
		add(panelCentral,BorderLayout.CENTER);
		panelCentral.setLayout( new GridLayout( 1, 3 ) );
		
//		Panel norte
		lblTituloPantalla= new JLabel("Piezas del inventario");
		panelNorte.add(lblTituloPantalla);

//		Panel sur

		btnAnterior = new JButton("Anterior" );
		panelSur.add( btnAnterior );
		btnAnterior.addActionListener( this );
		btnAnterior.setActionCommand( "anterior" );
		
		btnAddPieza = new JButton("Agregar una pieza" );
		panelSur.add( btnAddPieza );
		btnAddPieza.addActionListener( this );
		btnAddPieza.setActionCommand( "addPieza" );
		
		btnRegresar = new JButton("REGRESAR" );
		panelSur.add( btnRegresar );
		btnRegresar.addActionListener( this );
		btnRegresar.setActionCommand( "regresar" );
		
		btnSiguiente = new JButton("Siguiente" );
		panelSur.add( btnSiguiente );
		btnSiguiente.addActionListener( this );
		btnSiguiente.setActionCommand( "siguiente" );
		
		
	}
	
//	Panel central
	
	public void mostrarPieza(String idActual)
	{
		Pieza pieza = Galeria.getPiezaInventario(idActual);
		
//		Datos pieza
		
        String tituloP = pieza.getTitulo();
        String autorP = pieza.getAutor();
        String anioP = pieza.getAnio() + "";
        String tipoP = pieza.getTipo();
        String tecnicaP = pieza.getTecnica();
        String estadoP = pieza.getEstado();
        String lugarP = pieza.getLugar();
        String disponibilidadP = pieza.getDisponibilidad() + "";
        String vendidaP = pieza.getVendida() + "";
        String precioP = pieza.getValor() + "";
        String fechaP = pieza.getFechaVenta();
        List<String> dueniosP = pieza.getDueños();
        
		this.idActual = idActual;
		
		//Imagen
		
		lblImagen = new JLabel( );
		panelCentral.add(lblImagen);
		
		ImageIcon fOriginal = new ImageIcon("./datos/imagenes/prueba.png");
        Image iOriginal = fOriginal.getImage();

        Image iNueva = iOriginal.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon fNueva = new ImageIcon(iNueva);

		lblImagen.setIcon( fNueva );
		
		// Primer subpanel
		
		
		
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
