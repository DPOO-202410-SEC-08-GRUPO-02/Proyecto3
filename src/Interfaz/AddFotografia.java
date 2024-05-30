package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CargadorGaleria.Galeria;
import Inventario.Fotografia;
import Usuario.Administrador;

public class AddFotografia extends JFrame implements ActionListener
{
	private JPanel panelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JLabel lblTituloPantalla;
	private JLabel lblInstruccion;
	
	private JLabel lblTituloAlto;
	private JLabel lblTituloAncho;
	private JLabel lblTituloaColor;
	private JLabel lblTituloInstalacion;
	
	private JTextField txtAlto;
	private JTextField txtAncho;
	private JTextField txtaColor;
	private JTextField txtInstalacion;
	
	private String altoSTR;
	private String anchoSTR;
	private String aColorSTR;
	private String instalacionSTR;
	
	private String id;
	private String tecnica;
	private String autor;
	private String titulo;
	private String lugar;
	private String estado;
	private String fechaLim;
	private String tipo;
	private String fecha;
	
	private List<String> duenios;
	
	private int anio;
	
	private boolean disponibilidad;
	private boolean consignacion;
	private boolean devolucion;
	private boolean subasta;
	private boolean vendida;
	private boolean instalacion;
	private boolean aColor;
	
	private double valor;
	private double valorM;
	private double valorI;
	private double precio;
	private double alto;
	private double ancho;
	
	private JButton btnAdd;
	
	private Administrador admin;
	
	public AddFotografia(Administrador admin, String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
			String FechaLimite, double Valor, boolean Consignacion, boolean Devolucion, boolean Subasta, double ValorMinimoS, double ValorInicialS, String Tipo,
			boolean vendida, double precioVenta, String fechaVenta,List<String> dueños)
	{
		this.admin = admin;
		this.id=ID;
		this.tecnica=Tecnica;
		this.autor=Autor;
		this.titulo=Titulo;
		this.anio=Anio;
		this.lugar=Lugar;
		this.estado=Estado;
		this.disponibilidad=Disponibilidad;
		this.fechaLim=FechaLimite;
		this.valor=Valor;
		this.consignacion=Consignacion;
		this.devolucion=Devolucion;
		this.subasta=Subasta;
		this.valorM=ValorMinimoS;
		this.valorI=ValorInicialS;
		this.tipo=Tipo;
		this.vendida=vendida;
		this.precio=precioVenta;
		this.fecha=fechaVenta;
		this.duenios=dueños;
		
		setSize(1030,850);
		setTitle( "Galeria" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
//		setResizable( false );
		setLayout( new BorderLayout( ) );
		
		panelNorte = new JPanel( );
		add(panelNorte,BorderLayout.NORTH);
		panelNorte.setLayout( new GridLayout( 2, 1 ) );
	
		panelSur = new JPanel( );
		add(panelSur,BorderLayout.SOUTH);
		
		panelCentral = new JPanel( );
		add(panelCentral,BorderLayout.CENTER);
		panelCentral.setLayout( new GridLayout( 4, 2 ) );
		
//		Panel norte
		lblTituloPantalla= new JLabel("Agregar una pintura");
		panelNorte.add(lblTituloPantalla);
		
		lblInstruccion= new JLabel("Ingrese los valores que corresponden en cada espacio");
		panelNorte.add(lblInstruccion);
	
//		Panel sur
	
		btnAdd = new JButton("Agregar pieza" );
		panelSur.add( btnAdd );
		btnAdd.addActionListener( this );
		btnAdd.setActionCommand( "add" );
		
//		Panel central
		
		lblTituloAlto= new JLabel("Alto:" );
        panelCentral.add(lblTituloAlto);
        txtAlto = new JTextField();
        panelCentral.add(txtAlto);
        
        lblTituloAncho= new JLabel("Ancho:" );
        panelCentral.add(lblTituloAncho);
        txtAncho = new JTextField();
        panelCentral.add(txtAncho);
        
        lblTituloaColor= new JLabel("A color (true/false):" );
        panelCentral.add(lblTituloaColor);
        txtaColor = new JTextField();
        panelCentral.add(txtaColor);
        
        lblTituloInstalacion= new JLabel("Instalacion (true/false):" );
        panelCentral.add(lblTituloInstalacion);
        txtInstalacion = new JTextField();
        panelCentral.add(txtInstalacion);
		
	}
	public void actionPerformed( ActionEvent e )
    {
		if(e.getActionCommand( ).equals("add"))
        {
			altoSTR = txtAlto.getText().trim();
			alto = Double.parseDouble(altoSTR);
			anchoSTR = txtAncho.getText().trim();
			ancho = Double.parseDouble(anchoSTR);
			instalacionSTR = txtInstalacion.getText().trim();
			instalacion = Boolean.parseBoolean(instalacionSTR);
			aColorSTR = txtaColor.getText().trim();
			aColor = Boolean.parseBoolean(aColorSTR);
			
			Fotografia nuevaPieza = new Fotografia (id, tecnica, autor, titulo, anio, lugar, estado, 
					disponibilidad, fechaLim,valor, consignacion, devolucion, subasta, valorM, valorI, tipo,
					alto, ancho, aColor, instalacion, vendida, precio, fecha, duenios);
			
			Galeria.agregarPiezaInventario(nuevaPieza);
			
			if (subasta == true)
			{
				Galeria.agregarPiezaSubasta(nuevaPieza);
			}
			
			JOptionPane.showMessageDialog( null, "La pieza fue agragada con éxito" );
			
			InventarioAdmin inventarioAdmin = new InventarioAdmin(admin);
			
			Point location = getLocation();
			inventarioAdmin.setLocation(location);
			setVisible(false);
			inventarioAdmin.setVisible(true);
        }
    }
}
