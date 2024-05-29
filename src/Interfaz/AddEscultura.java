package Interfaz;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddEscultura extends JFrame
{
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
}
