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
import Inventario.Escultura;
import Usuario.Administrador;

public class AddEscultura extends JFrame implements ActionListener
{
	private JPanel panelCentral;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JLabel lblTituloPantalla;
	private JLabel lblInstruccion;
	
	private JLabel lblTituloAlto;
	private JLabel lblTituloAncho;
	private JLabel lblTituloProfundidad;
	private JLabel lblTituloMateriales;
	private JLabel lblTituloPeso;
	private JLabel lblTituloInstalacion;
	private JLabel lblTituloElectricidad;
	
	private JTextField txtAlto;
	private JTextField txtAncho;
	private JTextField txtProfundidad;
	private JTextField txtMateriales;
	private JTextField txtPeso;
	private JTextField txtInstalacion;
	private JTextField txtElectricidad;
	
	private String altoSTR;
	private String anchoSTR;
	private String profundidadSTR;
	private String materialesSTR;
	private String pesoSTR;
	private String instalacionSTR;
	private String electricidadSTR;
	
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
	private List<String> materiales;
	
	private int anio;
	
	private boolean disponibilidad;
	private boolean consignacion;
	private boolean devolucion;
	private boolean subasta;
	private boolean vendida;
	private boolean instalacion;
	private boolean electricidad;
	
	private double valor;
	private double valorM;
	private double valorI;
	private double precio;
	private double alto;
	private double ancho;
	private double profundidad;
	private double peso;
	
	private JButton btnAdd;
	
	private Administrador admin;
	
	public AddEscultura(Administrador admin, String ID, String Tecnica,String Autor,String Titulo,int Anio,String Lugar, String Estado, boolean Disponibilidad, 
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
	
		panelSur = new JPanel( );
		add(panelSur,BorderLayout.SOUTH);
		
		panelCentral = new JPanel( );
		add(panelCentral,BorderLayout.CENTER);
		panelCentral.setLayout( new GridLayout( 7, 2 ) );
		
//		Panel norte
		lblTituloPantalla= new JLabel("Agregar una escultura");
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
        
    	lblTituloProfundidad= new JLabel("Profundidad:" );
        panelCentral.add(lblTituloProfundidad);
        txtProfundidad = new JTextField();
        panelCentral.add(txtProfundidad);
        
        lblTituloMateriales= new JLabel("Materiales:" );
        panelCentral.add(lblTituloMateriales);
        txtMateriales = new JTextField();
        panelCentral.add(txtMateriales);
        
        lblTituloPeso= new JLabel("Peso:" );
        panelCentral.add(lblTituloPeso);
        txtPeso = new JTextField();
        panelCentral.add(txtPeso);
        
        lblTituloInstalacion= new JLabel("Instalacion (true/false):" );
        panelCentral.add(lblTituloInstalacion);
        txtInstalacion = new JTextField();
        panelCentral.add(txtInstalacion);
        
        lblTituloElectricidad= new JLabel("Electricidad (true/false):" );
        panelCentral.add(lblTituloElectricidad);
        txtElectricidad = new JTextField();
        panelCentral.add(txtElectricidad);
        
	}
	
	public void actionPerformed( ActionEvent e )
    {
		if(e.getActionCommand( ).equals("add"))
        {
			altoSTR = txtAlto.getText().trim();
			alto = Double.parseDouble(altoSTR);
			anchoSTR = txtAncho.getText().trim();
			ancho = Double.parseDouble(anchoSTR);
			profundidadSTR = txtProfundidad.getText().trim();
			profundidad = Double.parseDouble(profundidadSTR);
			materialesSTR = txtMateriales.getText().trim();
			materiales.add(materialesSTR);
			pesoSTR = txtPeso.getText().trim();
			peso = Double.parseDouble(pesoSTR);
			instalacionSTR = txtInstalacion.getText().trim();
			instalacion = Boolean.parseBoolean(instalacionSTR);
			electricidadSTR = txtElectricidad.getText().trim();
			electricidad = Boolean.parseBoolean(electricidadSTR);

			
			Escultura nuevaPieza = new Escultura (id, tecnica, autor, titulo, anio, lugar, estado, 
					disponibilidad, fechaLim,valor, consignacion, devolucion, subasta, valorM, valorI, tipo,
					alto, ancho, profundidad, materiales, peso, instalacion, electricidad, vendida, precio, fecha, duenios);
			
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

