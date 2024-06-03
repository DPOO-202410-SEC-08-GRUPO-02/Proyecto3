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
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import Inventario.Inventario;
import Inventario.Pieza;

public class HistPieza extends JFrame implements ActionListener
{

    private JTextArea textAreaHistorial;
    private Pieza pieza;
    
    private JPanel panelCentral;
    private JPanel panelSur;
    
    private JLabel lblImagen;
    
    private JButton btnVolver;

    public HistPieza(String Idpieza) 
    {
    	pieza = (Pieza) Inventario.getPiezaInventario(Idpieza);
        setTitle("Historial de la Pieza");
        setSize(800, 900);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setLayout( new BorderLayout( ) );
        
        panelCentral = new JPanel( );
        add(panelCentral,BorderLayout.CENTER);
        panelCentral.setLayout( new GridLayout( 2, 1 ) );
        
        panelSur = new JPanel( );
        add(panelSur,BorderLayout.SOUTH);
               
        Color grisesito = new Color (230, 230, 230);
        Color grisesito2 = new Color (204, 204, 204);
        MatteBorder bordeSuperior = new MatteBorder(2, 0, 0, 0, Color.gray);

        
//		Panel Central
        
        textAreaHistorial = new JTextArea();
        textAreaHistorial.setEditable(false);
        textAreaHistorial.setFont(new Font("Calibri", Font.PLAIN, 24));
        textAreaHistorial.setBackground(grisesito);
        textAreaHistorial.setText(crearTextoHistorial(pieza));
        
        lblImagen = new JLabel();
        ImageIcon icono = new ImageIcon("./datos/imagenes/" + pieza.getID() + ".png");
        lblImagen.setIcon(new ImageIcon(icono.getImage().getScaledInstance(200, -1, Image.SCALE_SMOOTH)));
        
        panelCentral.add(lblImagen);
        add(panelCentral, BorderLayout.CENTER);
        
        panelCentral.add(textAreaHistorial);
        panelCentral.setBackground(grisesito);

//		Panel Sur
        
        btnVolver = new JButton("Cerrar Sesion" );
        btnVolver.setFont(new Font("Arial", Font.PLAIN, 25));
        btnVolver.setBackground(grisesito2);
        btnVolver.setForeground(Color.black);
        
        panelSur.add( btnVolver );
        btnVolver.addActionListener( this );
        btnVolver.setActionCommand( "volver" );
        
        panelSur.setBackground(grisesito);
        panelSur.setBorder(bordeSuperior);
        
    }
    
    public void actionPerformed(ActionEvent e) 
    {
    	if ("volver".equals(e.getActionCommand())) 
        {
        	PanelPrincipalLogin panelLogin = new PanelPrincipalLogin();
			
			Point location = getLocation();
   		 	panelLogin.setLocation(location);
			setVisible(false);
			dispose();
			panelLogin.setVisible(true);
        }
    }

    private String crearTextoHistorial(Pieza pieza) 
    {
        return "Título: " + pieza.getTitulo() + "\n" +
                "Autor: " + pieza.getAutor() + "\n" +
                "Año: " + pieza.getAnio() + "\n" +
                "Tipo: " + pieza.getTipo() + "\n" +
                "ID: " + pieza.getID() + "\n" +
                "Técnica: " + pieza.getTecnica() + "\n" +
                "Estado: " + pieza.getEstado() + "\n" +
                "Lugar: " + pieza.getLugar() + "\n" +
                "Valor: $" + pieza.getValor() + "\n" +
                "¿Ya se vendió?: " + (pieza.getVendida() ? "Sí" : "No") + "\n" +
                "Precio de la venta: $" + pieza.getPrecioVenta() + "\n" +
                "Fecha de la venta: " + pieza.getFechaVenta() + "\n" +
                "Dueños (en orden de último hasta el primero): " + pieza.getDueños();
    }
}
