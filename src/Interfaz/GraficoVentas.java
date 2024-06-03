package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Usuario.Administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraficoVentas extends JFrame implements ActionListener 
{

    private JPanel panel;
	private JPanel panelSur;
	private JButton btnSalida;
	private Administrador admin;


    public GraficoVentas(Administrador admin) 
    {
    	this.admin=admin;
        setTitle("Gráfico de Ventas");
        setSize(500, 500);
        Color grisesito2 = new Color (204, 204, 204);
        panelSur = new JPanel( );
        add(panelSur,BorderLayout.SOUTH);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);

//         Panel principal
        panel = new JPanel() 
        {
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);

//                 El eje Y
                g.drawLine(50, 50, 50, 350);
//                 El eje X
                g.drawLine(50, 350, 450, 350);

//                 Dibujar etiquetas en el eje Y (para efectos prácticos números del 0 al 5)
                for (int i = 0; i <= 5; i++) {
                    int y = 350 - i * 60;
                    g.drawLine(45, y, 50, y);
                    g.drawString(String.valueOf(i), 30, y + 5);
                }

//                 Dibujar etiquetas en el eje X (los meses)
                String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
                for (int i = 0; i < meses.length; i++) {
                    int x = 50 + i * 33;
                    g.drawLine(x, 350, x, 355);
                    g.drawString(meses[i], x - 10, 370);
                }

//                 Ventas en septiembre y noviembre (Únicos meses donde nuestra galería vendió)
                int[] ventas = new int[12];
                ventas[8] += 1;
                ventas[10] += 1;

//                 Dibujar las ventas
                for (int i = 0; i < ventas.length; i++) 
                {
                    if (ventas[i] > 0) {
                        int x = 50 + i * 33;
                        int y = 350 - ventas[i] * 60;
                        g.fillOval(x - 5, y - 5, 10, 10);
                    }
                }
            }
        };

        add(panel, BorderLayout.CENTER);
        
        btnSalida = new JButton("SALIR");
        btnSalida.setFont(new Font("Arial", Font.PLAIN, 25));
        btnSalida.setBackground(grisesito2);
        btnSalida.setForeground(Color.black);
        panelSur.add(btnSalida);
        btnSalida.addActionListener(this);
        btnSalida.setActionCommand("regresar");
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

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            GraficoVentas frame = new GraficoVentas(null);
            frame.setVisible(true);
        });
    }
}
