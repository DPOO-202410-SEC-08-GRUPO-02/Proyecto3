package Interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CargadorGaleria.Galeria;
import Inventario.Pieza;
import Usuario.Administrador;
import Usuario.Cliente;

public class InfoComprador extends JFrame implements ActionListener
{
	private JPanel panelNorte;
	private JPanel panelEste;
	private JPanel panelOeste;
	private JPanel subPanelOeste;
	private JPanel subPanelEsteNorte;
	private JPanel subPanelEsteCentro;
	private JPanel subPanelEsteSur;
	private JPanel subSubPanelEsteNorte;
	private JPanel subSubPanelEsteCentro;
	
	private JLabel lblImagen;
	
	private JLabel lblTituloPantalla;
	
	private JLabel lblTituloNombre;
    private JLabel lblNombre;
    private JLabel lblTituloId;
    private JLabel lblId;
    private JLabel lblTituloLogin;
    private JLabel lblLogin;
    private JLabel lblTituloCorreo;
    private JLabel lblCorreo;
    private JLabel lblTituloNumero;
    private JLabel lblNumero;
    private JLabel lblTituloTipo;
    private JLabel lblTipo;
    
    private JLabel lblTituloHist;
    private JLabel lblNoHist;
    private JLabel lblTituloActl;
    private JLabel lblNoActl;
    private JLabel lblTituloValor;
    private JLabel lblValor;
    
    private JLabel lblTituloFechaP;
	private JLabel lblFechaP;
	private JLabel lblTituloIDP;
	private JLabel lblIDP;
	private JLabel lblTituloTituloP;
	private JLabel lblTituloP;
	private JLabel lblTituloAutorP;
	private JLabel lblAutorP;
	private JLabel lblTituloAnioP;
	private JLabel lblAnioP;
	private JLabel lblTituloTecnicaP;
	private JLabel lblTecnicaP;
	private JLabel lblTituloPrecioP;
	private JLabel lblPrecioP;
	
	private JLabel lblEspacio;
    
    private JButton btnRegresar;
    
    private Administrador admin;
	
	public InfoComprador(String usuarioSTR, Administrador admin)
	{
		this.admin = admin;
		
		setSize(1030,850);
		setTitle( "Galeria" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
//		setResizable( false );
		setLayout( new BorderLayout( ) );

		panelNorte = new JPanel( );
		add(panelNorte,BorderLayout.NORTH);
		
		panelEste = new JPanel( );
		add(panelEste,BorderLayout.EAST);
		panelEste.setLayout( new GridLayout( 3, 1 ) );

		panelOeste = new JPanel( );
		add(panelOeste,BorderLayout.WEST);
		panelOeste.setLayout( new GridLayout( 2, 1 ) );
		
		subPanelOeste = new JPanel( );
		
		subPanelEsteNorte = new JPanel( );
		subPanelEsteCentro = new JPanel( );
		subPanelEsteSur = new JPanel( );
		
		subSubPanelEsteNorte = new JPanel( );
		subSubPanelEsteCentro = new JPanel( );

//		Panel norte
		lblTituloPantalla= new JLabel("Información del cliente");
		panelNorte.add(lblTituloPantalla);

		Cliente cliente = (Cliente) Galeria.getUsuario(usuarioSTR);

//		Info usuario

		String nombre = cliente.getNombre();
		String id = cliente.getID();
		String login = cliente.getLogin();
		String correo = cliente.getCorreo();
		String numero = (cliente.getNumero()) + "";
		String tipo = cliente.getTipo();

//		Panel Oeste

		lblImagen = new JLabel( );
		panelOeste.add(lblImagen);

		//imagen obra
		
		ImageIcon fOriginal = new ImageIcon("./datos/imagenes/" + nombre + ".png");
        Image iOriginal = fOriginal.getImage();

        Image iNueva = iOriginal.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon fNueva = new ImageIcon(iNueva);

		lblImagen.setIcon( fNueva );
		
		//Subpanel con informacion
		
		subPanelOeste.setLayout( new GridLayout( 7, 2 ) );
		
		lblTituloNombre= new JLabel("Nombre:" );
		subPanelOeste.add(lblTituloNombre);
		lblNombre= new JLabel(nombre );
		subPanelOeste.add(lblNombre);
        
		lblTituloId= new JLabel("ID:" );
		subPanelOeste.add(lblTituloId);
		lblId= new JLabel(id );
		subPanelOeste.add(lblId);
		
		lblTituloLogin= new JLabel("Login:" );
		subPanelOeste.add(lblTituloLogin);
		lblLogin= new JLabel(login );
		subPanelOeste.add(lblLogin);
		
		lblTituloCorreo= new JLabel("Correo:" );
		subPanelOeste.add(lblTituloCorreo);
		lblCorreo= new JLabel(correo );
		subPanelOeste.add(lblCorreo);
		
		lblTituloNumero= new JLabel("Numero:" );
		subPanelOeste.add(lblTituloNumero);
		lblNumero= new JLabel( numero );
		subPanelOeste.add(lblNumero);
		
		lblTituloTipo= new JLabel("Tipo de cliente:" );
		subPanelOeste.add(lblTituloTipo);
		lblTipo= new JLabel(tipo );
		subPanelOeste.add(lblTipo);

		panelOeste.add(subPanelOeste);

		btnRegresar = new JButton("REGRESAR" );
		subPanelOeste.add( btnRegresar );
		btnRegresar.addActionListener( this );
		btnRegresar.setActionCommand( "regresar" );

//		Panel Este
		
		//Panel Este norte
		
		Map<String,Pieza> piezas = admin.piezasCompradas(cliente);

    	if (piezas == null)
    	{
    		subPanelEsteNorte.setLayout( new GridLayout(2 , 1 ) );
    		
    		lblTituloHist= new JLabel("Historial de piezas:" );
    		subPanelEsteNorte.add(lblTituloHist);
    		
    		lblNoHist= new JLabel("El cliente no tiene historial" );
    		subPanelEsteNorte.add(lblNoHist);
    	}
    	
    	else
    	{
    		int lengPiezas = piezas.size();
    		
    		subPanelEsteNorte.setLayout( new GridLayout(lengPiezas + 1 , 1 ) );
    		
    		lblTituloHist= new JLabel("Historial de piezas:" );
    		subPanelEsteNorte.add(lblTituloHist);
	    	
	    	for (Map.Entry<String, Pieza> entry : piezas.entrySet()) 
	    	{
	    		subSubPanelEsteNorte.setLayout( new GridLayout(7 , 2 ) );

	    		String fecha = entry.getKey();
	            Pieza pieza = entry.getValue();		
	            
	            //Datos pieza
	            
	            String idP = pieza.getID();
	            String tituloP = pieza.getTitulo();
	            String autorP = pieza.getAutor();
	            String anioP = pieza.getAnio() + "";
	            String tecnicaP = pieza.getTecnica();
	            String precioP = pieza.getValor() + "";
	            
	            //Sub sub panel norte
	            
	            lblTituloIDP= new JLabel("ID:" );
	    		subSubPanelEsteNorte.add(lblTituloIDP);
	    		lblIDP= new JLabel(idP );
	    		subSubPanelEsteNorte.add(lblIDP);
	    		
	    		lblTituloTituloP= new JLabel("Titulo:" );
	    		subSubPanelEsteNorte.add(lblTituloTituloP);
	    		lblTituloP= new JLabel(tituloP );
	    		subSubPanelEsteNorte.add(lblTituloP);
	    		
	    		lblTituloAutorP= new JLabel("Autor:" );
	    		subSubPanelEsteNorte.add(lblTituloAutorP);
	    		lblAutorP= new JLabel(autorP );
	    		subSubPanelEsteNorte.add(lblAutorP);
	    		
	    		lblTituloAnioP= new JLabel("Año:" );
	    		subSubPanelEsteNorte.add(lblTituloAnioP);
	    		lblAnioP= new JLabel(anioP );
	    		subSubPanelEsteNorte.add(lblAnioP);
	    		
	    		lblTituloTecnicaP= new JLabel("Tecnica:" );
	    		subSubPanelEsteNorte.add(lblTituloTecnicaP);
	    		lblTecnicaP= new JLabel(tecnicaP );
	    		subSubPanelEsteNorte.add(lblTecnicaP);
	    		
	    		lblTituloPrecioP= new JLabel("Precio:" );
	    		subSubPanelEsteNorte.add(lblTituloPrecioP);
	    		lblPrecioP= new JLabel(precioP );
	    		subSubPanelEsteNorte.add(lblPrecioP);
	    		
	    		lblTituloFechaP= new JLabel("Pieza obtenida el:" );
	    		subSubPanelEsteNorte.add(lblTituloFechaP);
	    		lblFechaP= new JLabel(fecha );
	    		subSubPanelEsteNorte.add(lblFechaP);
	    		
	    		subPanelEsteNorte.add(subSubPanelEsteNorte);
            }
	    	
	    	panelEste.add(subPanelEsteNorte);
    	}
		
    	//Panel Este centro
    	
    	List<Pieza> piezasL = admin.piezasDueño(cliente);
    	
    	if (piezasL == null)
    	{
    		subPanelEsteCentro.setLayout( new GridLayout(2 , 1 ) );
    		
    		lblTituloActl= new JLabel("Piezas actuales:" );
    		subPanelEsteCentro.add(lblTituloActl);
    		
    		lblNoActl= new JLabel("El cliente no tiene piezas actualmente" );
    		subPanelEsteCentro.add(lblNoActl);
    	}
    	
    	else
    	{
    		int lengPiezas = piezasL.size();
    		
    		subPanelEsteCentro.setLayout( new GridLayout(lengPiezas + 1 , 1 ) );
    		
    		lblTituloActl= new JLabel("Historial de piezas:" );
    		subPanelEsteCentro.add(lblTituloActl);
	    	
    		for (Pieza pieza: piezasL)  
	    	{
	    		subSubPanelEsteCentro.setLayout( new GridLayout(7 , 2 ) );	
	            
	            //Datos pieza
	            
	            String idP = pieza.getID();
	            String tituloP = pieza.getTitulo();
	            String autorP = pieza.getAutor();
	            String anioP = pieza.getAnio() + "";
	            String tecnicaP = pieza.getTecnica();
	            String precioP = pieza.getValor() + "";
	            
	            //Sub sub panel centro
	            
	            lblTituloIDP= new JLabel("ID:" );
	    		subSubPanelEsteCentro.add(lblTituloIDP);
	    		lblIDP= new JLabel(idP );
	    		subSubPanelEsteCentro.add(lblIDP);
	    		
	    		lblTituloTituloP= new JLabel("Titulo:" );
	    		subSubPanelEsteCentro.add(lblTituloTituloP);
	    		lblTituloP= new JLabel(tituloP );
	    		subSubPanelEsteCentro.add(lblTituloP);
	    		
	    		lblTituloAutorP= new JLabel("Autor:" );
	    		subSubPanelEsteCentro.add(lblTituloAutorP);
	    		lblAutorP= new JLabel(autorP );
	    		subSubPanelEsteCentro.add(lblAutorP);
	    		
	    		lblTituloAnioP= new JLabel("Año:" );
	    		subSubPanelEsteCentro.add(lblTituloAnioP);
	    		lblAnioP= new JLabel(anioP );
	    		subSubPanelEsteCentro.add(lblAnioP);
	    		
	    		lblTituloTecnicaP= new JLabel("Tecnica:" );
	    		subSubPanelEsteCentro.add(lblTituloTecnicaP);
	    		lblTecnicaP= new JLabel(tecnicaP );
	    		subSubPanelEsteCentro.add(lblTecnicaP);
	    		
	    		lblTituloPrecioP= new JLabel("Precio:" );
	    		subSubPanelEsteCentro.add(lblTituloPrecioP);
	    		lblPrecioP= new JLabel(precioP );
	    		subSubPanelEsteCentro.add(lblPrecioP);
	    		
	    		lblEspacio= new JLabel("" );
	    		subSubPanelEsteCentro.add(lblEspacio);
	    		
	    		lblEspacio= new JLabel("" );
	    		subSubPanelEsteCentro.add(lblEspacio);
	    		
	    		subPanelEsteCentro.add(subSubPanelEsteCentro);
            }
	    	
	    	panelEste.add(subPanelEsteCentro);
    	}
    	
    	//Panel Este sur
    	
    	subPanelEsteSur.setLayout( new GridLayout(1 , 2 ) );
		
    	double valor = admin.valorColleccion(cliente);
    	
    	lblTituloValor= new JLabel("Valor de la coleccion:" );
		subPanelEsteSur.add(lblTituloValor);
		
		lblValor= new JLabel(valor + "" );
		subPanelEsteSur.add(lblValor);
		
		panelEste.add(subPanelEsteSur);

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
