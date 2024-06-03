package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import Artista.Artista;
import CargadorGaleria.Galeria;

public class HistArtista extends JFrame implements ActionListener 
{

    private int indiceActual = 0;
    private List<String> listaPiezas;
    
    private JPanel panelCentral;
    private JPanel panelNorte;
    private JPanel panelSur;
    
    private JLabel lblInformacionArtista;
    private JLabel lblInformacionPieza;
    private JLabel lblImagen;
    
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnVolver;
    
    private Artista artista;

    public HistArtista(String nombreArtista) 
    {
        artista = Galeria.getArtista(nombreArtista);
        listaPiezas = new ArrayList<>(artista.getPiezasHechas().keySet());

        setTitle("Historial del Artista - " + nombreArtista);
        setSize(1030,850);
        setTitle( "Galeria" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new BorderLayout( ) );
        
        panelCentral = new JPanel( );
        add(panelCentral,BorderLayout.CENTER);
        panelCentral.setLayout( new GridLayout( 1, 1 ) );
                
        panelNorte = new JPanel( );
        add(panelNorte,BorderLayout.NORTH);
        
        panelSur = new JPanel( );
        add(panelSur,BorderLayout.SOUTH);
        
        Color grisesito = new Color (230, 230, 230);
        Color grisesito2 = new Color (204, 204, 204);
        Color azulito = new Color (0, 103, 184);
        MatteBorder bordeInferior = new MatteBorder(0, 0, 2, 0, Color.gray);
        
        // Panel Norte

        lblInformacionArtista = new JLabel("Información del Artista: " + nombreArtista);
        lblInformacionArtista.setFont(new Font("Arial", Font.BOLD, 36));
        panelNorte.add(lblInformacionArtista);
        
        panelNorte.setBackground(grisesito);
        panelNorte.setBorder(bordeInferior);
        
        // Panel Central
        panelCentral.setBackground(grisesito);
        
        lblImagen = new JLabel();
        lblInformacionPieza = new JLabel();
        actualizarInformacionPieza();
        
        ImageIcon icono = new ImageIcon("./datos/imagenes/" + nombreArtista + ".png");
        lblImagen.setIcon(new ImageIcon(icono.getImage().getScaledInstance(325, -1, Image.SCALE_SMOOTH)));
        lblInformacionPieza.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        
        panelCentral.add(lblImagen);
        panelCentral.add(lblInformacionPieza);
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel Sur
        panelSur.setBackground(grisesito);
        
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnVolver = new JButton("Salir");
        
        btnAnterior.setFont(new Font("Arial", Font.PLAIN, 25));
        btnAnterior.setBackground(azulito);
        btnAnterior.setForeground(Color.white);
        btnSiguiente.setFont(new Font("Arial", Font.PLAIN, 25));
        btnSiguiente.setBackground(azulito);
        btnSiguiente.setForeground(Color.white);
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 25));
        btnVolver.setBackground(grisesito2);
        btnVolver.setForeground(Color.black);
        
        panelSur.add(btnAnterior);
        btnAnterior.addActionListener( this );
        btnAnterior.setActionCommand( "anterior" );
        panelSur.add(btnVolver);
        btnVolver.addActionListener( this );
        btnVolver.setActionCommand( "volver" );
        panelSur.add(btnSiguiente);
        btnSiguiente.addActionListener( this );
        btnSiguiente.setActionCommand( "siguiente" );
        

        panelSur.setBackground(grisesito);
    }
   

    private void actualizarInformacionPieza() 
    {
        if (listaPiezas != null && !listaPiezas.isEmpty()) 
        {
            String nombrePieza = listaPiezas.get(indiceActual);
            Map<String, Object> infoPieza = artista.getPiezasHechas().get(nombrePieza);
            String piezaInfo = historiaArtista(artista, nombrePieza);
            lblInformacionPieza.setText("<html>" + piezaInfo.replace("\n", "<br>") + "</html>");
        }
    }
   
    
    public void actionPerformed(ActionEvent e) 
    {
        if ("anterior".equals(e.getActionCommand()))
        {
            if (indiceActual > 0) 
            {
                indiceActual--;
                actualizarInformacionPieza();
            }
        } 
        else if ("siguiente".equals(e.getActionCommand()))
        {
            if (indiceActual < listaPiezas.size() - 1)
            {
                indiceActual++;
                actualizarInformacionPieza();
            }
        } 
        else if ("volver".equals(e.getActionCommand())) 
        {
        	PanelPrincipalLogin panelLogin = new PanelPrincipalLogin();
			
			Point location = getLocation();
   		 	panelLogin.setLocation(location);
			setVisible(false);
			dispose();
			panelLogin.setVisible(true);
        }
    }

    public static String historiaArtista(Artista artista, String nombrePieza) 
    {
        Map<String, Object> infoPieza = artista.getPiezasHechas().get(nombrePieza);
        String piezaInfo = "Pieza: " + nombrePieza;
        piezaInfo += "\nFecha de creacion: " + infoPieza.get("fechaCreacion");
        boolean vendida = (boolean) infoPieza.get("vendida");

        if (vendida==true)
        {
            piezaInfo += "\nFecha de venta: " + infoPieza.get("fechaVenta");
            piezaInfo += "\nPrecio de venta: " + infoPieza.get("precioVenta");
        } 
        else 
        {
            piezaInfo += "\nEsta pieza no ha sido vendida.";
        }

        return piezaInfo;
    }
}

