package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Usuario.Administrador;

public class AddPieza extends JFrame implements ActionListener
{
	private JPanel panelCentral;
	private JPanel subPanelCentralEste;
	private JPanel subPanelCentralOeste;
	private JPanel panelNorte;
	private JPanel panelSur;
	
	private JLabel lblTituloPantalla;
	private JLabel lblInstruccion;
	
	private JLabel lblTituloId;
	private JLabel lblTituloTecnica;
	private JLabel lblTituloAutor;
	private JLabel lblTituloTitulo;
	private JLabel lblTituloAnio;
	private JLabel lblTituloLugar;
	private JLabel lblTituloEstado;
	private JLabel lblTituloDisponibilidad;
	private JLabel lblTituloFechaLim;
	private JLabel lblTituloValor;
	private JLabel lblTituloConsignacion;
	private JLabel lblTituloDevolucion;
	private JLabel lblTituloSubasta;
	private JLabel lblTituloValorM;
	private JLabel lblTituloValorI;
	private JLabel lblTituloTipo;
	
	private JLabel lblEspacio;
	
	private JTextField txtId;
	private JTextField txtTecnica;
	private JTextField txtAutor;
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private JTextField txtLugar;
	private JTextField txtEstado;
	private JTextField txtDisponibilidad;
	private JTextField txtFechaLim;
	private JTextField txtValor;
	private JTextField txtConsignacion;
	private JTextField txtDevolucion;
	private JTextField txtSubasta;
	private JTextField txtValorM;
	private JTextField txtValorI;
	
	private JComboBox boxTipo;
	
	private String id;
	private String tecnica;
	private String autor;
	private String titulo;
	private String anioSTR;
	private String lugar;
	private String estado;
	private String disponibilidadSTR;
	private String fechaLim;
	private String valorSTR;
	private String consignacionSTR;
	private String devolucionSTR;
	private String subastaSTR;
	private String valorMSTR;
	private String valorISTR;
	private String tipo;
	private String vendidaSTR;
	private String precioSTR;
	private String fecha = "n/a";
	
	private List<String> duenios = null;
	
	private int anio;
	
	private boolean disponibilidad;
	private boolean consignacion;
	private boolean devolucion;
	private boolean subasta;
	private boolean vendida = false;
	
	private double valor;
	private double valorM;
	private double valorI;
	private double precio =0.0;
	
	private JButton btnSiguiente;
	
	private Administrador admin;
	
	public AddPieza(Administrador admin)
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
		
		panelCentral = new JPanel( );
		add(panelCentral,BorderLayout.CENTER);
		panelCentral.setLayout( new GridLayout( 2, 1 ) );
		
		subPanelCentralEste = new JPanel( );
		
//		Panel norte
		lblTituloPantalla= new JLabel("Agregar una pieza");
		panelNorte.add(lblTituloPantalla);
		
		lblInstruccion= new JLabel("Ingrese los valores que corresponden en cada espacio");
		panelNorte.add(lblInstruccion);
	
//		Panel sur
	
		btnSiguiente = new JButton("Siguiente" );
		panelSur.add( btnSiguiente );
		btnSiguiente.addActionListener( this );
		btnSiguiente.setActionCommand( "siguiente" );
		
//		Panel central
		
		//Panel Oeste central
		
		subPanelCentralOeste = new JPanel( );
		subPanelCentralOeste.setLayout( new GridLayout( 8, 2 ) );
		
		lblTituloId= new JLabel("ID:" );
        subPanelCentralOeste.add(lblTituloId);
        txtId = new JTextField();
        subPanelCentralOeste.add(txtId);
        
        lblTituloTecnica= new JLabel("Tecnica:" );
        subPanelCentralOeste.add(lblTituloTecnica);
        txtTecnica = new JTextField();
        subPanelCentralOeste.add(txtTecnica);
        
        lblTituloAutor= new JLabel("Autor:" );
        subPanelCentralOeste.add(lblTituloAutor);
        txtAutor = new JTextField();
        subPanelCentralOeste.add(txtAutor);
        
        lblTituloTitulo= new JLabel("Titulo:" );
        subPanelCentralOeste.add(lblTituloTitulo);
        txtTitulo = new JTextField();
        subPanelCentralOeste.add(txtTitulo);
        
        lblTituloAnio= new JLabel("Contraseña:" );
        subPanelCentralOeste.add(lblTituloAnio);
        txtAnio = new JTextField();
        subPanelCentralOeste.add(txtAnio);
        
    	lblTituloLugar= new JLabel("Lugar:" );
        subPanelCentralOeste.add(lblTituloLugar);
        txtLugar = new JTextField();
        subPanelCentralOeste.add(txtLugar);
        
        lblTituloEstado= new JLabel("Estado:" );
        subPanelCentralOeste.add(lblTituloEstado);
        txtEstado = new JTextField();
        subPanelCentralOeste.add(txtEstado);
        
        lblTituloDisponibilidad= new JLabel("Disponibilidad (true/false):" );
        subPanelCentralOeste.add(lblTituloDisponibilidad);
        txtDisponibilidad = new JTextField();
        subPanelCentralOeste.add(txtDisponibilidad);
        
        panelCentral.add(subPanelCentralOeste);
        
        //Panel central este
        
        subPanelCentralEste = new JPanel( );
		subPanelCentralEste.setLayout( new GridLayout( 8, 2 ) );
        
        lblTituloFechaLim= new JLabel("Fecha limite:" );
        subPanelCentralEste.add(lblTituloFechaLim);
        txtFechaLim = new JTextField();
        subPanelCentralEste.add(txtFechaLim);

        
        lblTituloValor= new JLabel("Valor:" );
        subPanelCentralEste.add(lblTituloValor);
        txtValor = new JTextField();
        subPanelCentralEste.add(txtValor);
        
		lblTituloConsignacion= new JLabel("Consignacion (true/false):" );
        subPanelCentralEste.add(lblTituloConsignacion);
        txtConsignacion = new JTextField();
        subPanelCentralEste.add(txtConsignacion);
        
        lblTituloDevolucion= new JLabel("Devolucion (true/false):" );
        subPanelCentralEste.add(lblTituloDevolucion);
        txtDevolucion = new JTextField();
        subPanelCentralEste.add(txtDevolucion);
        
        lblTituloSubasta= new JLabel("Subasta (true/false):" );
        subPanelCentralEste.add(lblTituloSubasta);
        txtSubasta = new JTextField();
        subPanelCentralEste.add(txtSubasta);
        
        lblTituloValorM= new JLabel("Valor minimo:" );
        subPanelCentralEste.add(lblTituloValorM);
        txtValorM = new JTextField();
        subPanelCentralEste.add(txtValorM);
        
    	lblTituloValorI= new JLabel("Valor inicial:" );
        subPanelCentralEste.add(lblTituloValorI);
        txtValorI = new JTextField();
        subPanelCentralEste.add(txtValorI);
        
        lblTituloTipo= new JLabel("Tipo:" );
        subPanelCentralEste.add(lblTituloTipo);
        String[] opciones = {"Pintura", "Esculruta", "Impresion", "Fotografia", "Video"};
        boxTipo = new JComboBox<>(opciones);
        subPanelCentralEste.add(boxTipo);

        panelCentral.add(subPanelCentralEste);
	

	}
	
	public void actionPerformed( ActionEvent e )
    {
		if(e.getActionCommand( ).equals("siguiente"))
        {
//	      id = txtId.getText().trim();
//	      tecnica = txtTecnica.getText().trim();
//	      autor = txtAutor.getText().trim();
//	      titulo = txtTitulo.getText().trim();
//	      anioSTR = txtAnio.getText().trim();
//	      anio = Integer.parseInt(anioSTR);
//	      lugar = txtLugar.getText().trim();
//	      estado = txtEstado.getText().trim();
//	      disponibilidadSTR = txtDisponibilidad.getText().trim();
//	      disponibilidad = Boolean.parseBoolean(disponibilidadSTR);
//	      fechaLim = txtFechaLim.getText().trim();
//	      valorSTR = txtValor.getText().trim();
//	      valor = Double.parseDouble(valorSTR);
//	      consignacionSTR = txtConsignacion.getText().trim();
//	      consignacion = Boolean.parseBoolean(consignacionSTR);
//	      devolucionSTR = txtDevolucion.getText().trim();
//	      devolucion = Boolean.parseBoolean(devolucionSTR);
//	      subastaSTR = txtSubasta.getText().trim();
//	      subasta = Boolean.parseBoolean(subastaSTR);
//	      valorMSTR = txtValorM.getText().trim();
//	      valorM = Double.parseDouble(valorMSTR);
//	      valorISTR = txtValorI.getText().trim();
//	      valorI = Double.parseDouble(valorISTR);
	      
	      String seleccion = (String) boxTipo.getSelectedItem();
	      
	      tipo = seleccion;
	      
	      if (seleccion.equals("Pintura"))
	      {
	    	  AddPintura addPintura = new AddPintura(admin, id, tecnica, autor, titulo, anio, lugar, estado, 
	    				disponibilidad, fechaLim,valor, consignacion, devolucion, subasta, valorM, valorI,tipo);
				
				Point location = getLocation();
				addPintura.setLocation(location);
				setVisible(false);
				addPintura.setVisible(true);
	      }
//	      else if (seleccion.equals("Esculruta"))
//	      {
//	    	  AddEscultura addEscultura = new AddEscultura(admin);
//				
//				Point location = getLocation();
//				addEscultura.setLocation(location);
//				setVisible(false);
//				addEscultura.setVisible(true);
//	      }
//	      else if (seleccion.equals("Impresion"))
//	      {
//	    	  AddImpresion addImpresion = new AddImpresion(admin);
//				
//				Point location = getLocation();
//				addImpresion.setLocation(location);
//				setVisible(false);
//				addImpresion.setVisible(true);
//	      }
//	      else if (seleccion.equals("Fotografia"))
//	      {
//	    	  AddFotografia addFotografia = new AddFotografia(admin);
//				
//				Point location = getLocation();
//				addFotografia.setLocation(location);
//				setVisible(false);
//				addFotografia.setVisible(true);
//	      }
//	      else if (seleccion.equals("Video"))
//	      {
//	    	  AddVideo addVideo = new AddVideo(admin);
//				
//				Point location = getLocation();
//				addVideo.setLocation(location);
//				setVisible(false);
//				addVideo.setVisible(true);
//	      }
        }
    }
	
}
