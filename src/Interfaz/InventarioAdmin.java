package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CargadorGaleria.CargadorGaleria;
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
	private JLabel lblFecha;
	private JLabel lblTituloDuenios;
	private JLabel lblDuenios;
	
	private JLabel lblEspacio;
	
	private JButton btnDisponibilidad;
	private JButton btnEstado;
	private JButton btnAnterior;
	private JButton btnAddPieza;
	private JButton btnRegresar;
	private JButton btnSiguiente;
	
	private char idActual = 'a';
	
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
		
		mostrarPieza(idActual);
	}
	
//	Panel central
	
	public void mostrarPieza(char idActual)
	{
		panelCentral = new JPanel( );
		add(panelCentral,BorderLayout.CENTER);
		panelCentral.setLayout( new GridLayout( 1, 3 ) );
		
		subPanelCentralCentro = new JPanel( );
		subPanelCentralEste = new JPanel( );
		
		Pieza pieza = Galeria.getPiezaInventario(idActual + "");
		
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
		
		ImageIcon fOriginal = new ImageIcon("./datos/imagenes/" + idActual + ".png");
        Image iOriginal = fOriginal.getImage();

        Image iNueva = iOriginal.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        ImageIcon fNueva = new ImageIcon(iNueva);

		lblImagen.setIcon( fNueva );
		
		// Primer subpanel
		
		subPanelCentralCentro.setLayout( new GridLayout(8 , 2 ) );
		
		lblTituloTitulo= new JLabel("Titulo:" );
		subPanelCentralCentro.add(lblTituloTitulo);
		lblTitulo= new JLabel(tituloP );
		subPanelCentralCentro.add(lblTitulo);
		
		lblTituloAutor= new JLabel("Autor:" );
		subPanelCentralCentro.add(lblTituloAutor);
		lblAutor= new JLabel(autorP );
		subPanelCentralCentro.add(lblAutor);
		
		lblTituloAnio= new JLabel("Año:" );
		subPanelCentralCentro.add(lblTituloAnio);
		lblAnio= new JLabel(anioP );
		subPanelCentralCentro.add(lblAnio);
		
		lblTituloTipo= new JLabel("Tipo:" );
		subPanelCentralCentro.add(lblTituloTipo);
		lblTipo= new JLabel(tipoP );
		subPanelCentralCentro.add(lblTipo);
		
		lblTituloTecnica= new JLabel("Tecnica:" );
		subPanelCentralCentro.add(lblTituloTecnica);
		lblTecnica= new JLabel(tecnicaP );
		subPanelCentralCentro.add(lblTecnica);
		
		lblTituloEstado= new JLabel("Estado:" );
		subPanelCentralCentro.add(lblTituloEstado);
		lblEstado= new JLabel(estadoP );
		subPanelCentralCentro.add(lblEstado);
		
		lblTituloLugar= new JLabel("Lugar:" );
		subPanelCentralCentro.add(lblTituloLugar);
		lblLugar= new JLabel(lugarP );
		subPanelCentralCentro.add(lblLugar);

		btnDisponibilidad = new JButton("Editar disponibilidad" );
		subPanelCentralCentro.add( btnDisponibilidad );
		btnDisponibilidad.addActionListener( this );
		btnDisponibilidad.setActionCommand( "disponibilidad" );
		
		panelCentral.add(subPanelCentralCentro);
		
		// Segundo subpanel
		
		subPanelCentralEste.setLayout( new GridLayout(8 , 2 ) );
		
		lblTituloDisponibilidad= new JLabel("Disponibilidad:" );
		subPanelCentralEste.add(lblTituloDisponibilidad);
		lblDisponibilidad= new JLabel(disponibilidadP );
		subPanelCentralEste.add(lblDisponibilidad);
		
		lblTituloVendida= new JLabel("Vendida:" );
		subPanelCentralEste.add(lblTituloVendida);
		lblVendida= new JLabel(vendidaP );
		subPanelCentralEste.add(lblVendida);
		
		lblTituloPrecio= new JLabel("Precio:" );
		subPanelCentralEste.add(lblTituloPrecio);
		lblPrecio= new JLabel(precioP );
		subPanelCentralEste.add(lblPrecio);
		
		lblTituloFecha= new JLabel("Fecha de venta:" );
		subPanelCentralEste.add(lblTituloFecha);
		lblFecha= new JLabel(fechaP );
		subPanelCentralEste.add(lblFecha);
		
		lblTituloDuenios= new JLabel("Dueños:" );
		subPanelCentralEste.add(lblTituloDuenios);
		
		String duenios = "No ha sido vendida";
		
		for (String duenio: dueniosP) 
		{
			if (duenios.equals("No ha sido vendida"))
			{
				duenios = duenio;
			}
			
			else
			{
				duenios += "-"+duenio;
			}
		}
		
		lblDuenios= new JLabel(duenios );
		subPanelCentralEste.add(lblDuenios);
		
		lblEspacio= new JLabel("" );
		subPanelCentralEste.add(lblEspacio);
		
		lblEspacio= new JLabel("" );
		subPanelCentralEste.add(lblEspacio);
		
		lblEspacio= new JLabel("" );
		subPanelCentralEste.add(lblEspacio);
		
		lblEspacio= new JLabel("" );
		subPanelCentralEste.add(lblEspacio);

		btnEstado = new JButton("Editar estado" );
		subPanelCentralEste.add( btnEstado );
		btnEstado.addActionListener( this );
		btnEstado.setActionCommand( "estado" );
		
		panelCentral.add(subPanelCentralEste);
		
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
		else if(e.getActionCommand( ).equals("estado"))
        {
			Pieza pieza = Galeria.getPiezaInventario(idActual + "");
			
			String usuario = JOptionPane.showInputDialog( "Ingrese el nuevo estado de la pieza" );
			admin.cambiarEstadoObra (pieza, "estado", usuario);
			
			try {
				CargadorGaleria.salvarInventario("./datos/Inventario.json");
				JOptionPane.showMessageDialog( null, "El estado fue cambiado con éxito" );
				mostrarPieza(idActual);
			} catch (IOException a) {
				JOptionPane.showMessageDialog( null, "Error al cambiar el estado" );
				a.printStackTrace();
			}
        }
		else if(e.getActionCommand( ).equals("disponibilidad"))
        {
			Pieza pieza = Galeria.getPiezaInventario(idActual + "");
			
			String usuario = JOptionPane.showInputDialog( "Ingrese la nueva disponibilidad de la pieza (true/false)" );
			admin.cambiarEstadoObra (pieza, "disponibilidad", usuario);
			
			try {
				CargadorGaleria.salvarInventario("./datos/Inventario.json");
				JOptionPane.showMessageDialog( null, "La disponibilidad fue cambiada con éxito" );
				mostrarPieza(idActual);
			} catch (IOException a) {
				JOptionPane.showMessageDialog( null, "Error al cambiar la disponibilidad" );
				a.printStackTrace();
			}
        }
		else if(e.getActionCommand( ).equals("anterior"))
        {
			if ((idActual + "").equals("a"))
			{
				JOptionPane.showMessageDialog( null, "Esta en la primera pieza" );
			}
			else
			{
				idActual--;
				mostrarPieza(idActual);
			}
        }
		else if(e.getActionCommand( ).equals("siguiente"))
        {
			if ((idActual + "").equals("f"))
			{
				JOptionPane.showMessageDialog( null, "Esta en la ultima pieza" );
			}
			else
			{
				idActual++;
				mostrarPieza(idActual);
			}
        }
		else if(e.getActionCommand( ).equals("addPieza"))
        {
			
        }
    }
	
}
