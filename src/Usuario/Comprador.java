package Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Compra.Compra;
import Inventario.Pieza;
import Subasta.Subasta;

public class Comprador extends Cliente{
	private double dineroActual;
	private double limiteCompras;
	private Map<String,Pieza> infoCompras= new HashMap<String,Pieza>();
	private Map<String,Double> metodoPago= new HashMap<String,Double>();
	private List<String> pasarelas= new ArrayList<String>();
	private int numeroCuentaPas;
	private String estadoTarjeta;
	private String numeroTarjeta;
	private Propietario Propietario;
	
	public Comprador (String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, 
			boolean Verificado,double dineroActual, double LimiteCompras, Map<String,Double> MetodoPago,
			Map<String,Pieza> InfoCompras, Map<String,Pieza> HistorialPiezas, List<Pieza> PiezasActuales,List<String> pasarelas, int numeroCuentaPas, String estadoTarjeta, String numeroTarjeta) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero,Tipo,Verificado, HistorialPiezas, PiezasActuales);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.verificado=Verificado;
		this.dineroActual=dineroActual;
		this.limiteCompras=LimiteCompras;
		this.metodoPago=MetodoPago;
		this.infoCompras=InfoCompras;
		this.historialPiezas = HistorialPiezas;
		this.piezasActuales = PiezasActuales;
		this.pasarelas=pasarelas;
		this.numeroCuentaPas=numeroCuentaPas;
		this.estadoTarjeta=estadoTarjeta;
		this.numeroTarjeta=numeroTarjeta;
	}

	public double getDineroActual() {
		return dineroActual;
	}

	public double getLimiteCompras() {
		return limiteCompras;
	}
	
	public int getNumeroCuentaPas() {
		return numeroCuentaPas;
	}
	
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	
	public String getEstadoTarjeta() {
		return estadoTarjeta;
	}
	
	public Map<String, Pieza> getInfoCompras() {
		return infoCompras;
	}
	
	public Map<String,Double> getMetodoPago() {
		return metodoPago;
	}

	public String generarOfertasSubasta(Pieza pieza, double valor, Operador operador, Administrador admin) {
		/* En estado de oferta el usuario sera capaz de hacer una oferta.
		 * Posiles formas (pueden ser ambas):
		 * 1. El usuario generara una oferta y si es menor que el valor minimo entonces el programa generara un numero aleatorio entre la oferta de usuario y el valor minimo (pero nunca igual a el valor minimo).
		 * 2. Para dar mas sensacion de estar en una subasta, el usuario debe realizar minimo 3 ofertas antes de que se cierre la subasta.
		 */
		
		String mensajeOferta = Subasta.generarOferta(this, pieza, valor, operador, admin);
		return mensajeOferta;
		
	}
	
	public void comprarPieza(Pieza pieza,Comprador comprador, Administrador admin, Cajero cajero, String metodoPagoPreferido) {
		/* El usuario podra elegir una pieza en el Catalogo para comprar*/
		Compra.pasarCaja(comprador, pieza, "Compra normal", admin, cajero,metodoPagoPreferido,false);
	}
	
	public void editarLimite(double nuevoLimite) {
		/* No se le presentara al comprador para que la use. el administrador es el que debe usarla.*/ 
	}
	
	public void devolverPieza(Pieza pieza) {
		/* No se le presentara al comprador para que la use. el administrador es el que debe usarla.*/
		List<Pieza> nuevasPiezasActuales= new ArrayList<Pieza>( );
		for (int i=0; i<piezasActuales.size(); i++) {
			Pieza piezaEnLista = piezasActuales.get(i);
			if (pieza.getID()!=piezaEnLista.getID()) {
				nuevasPiezasActuales.add(piezaEnLista);
			}
		}
		piezasActuales=nuevasPiezasActuales;
	}
	
	public void agregarPieza(Pieza pieza) {
		/* No se le presentara al comprador para que la use. el administrador es el que debe usarla.*/
		
		this.infoCompras.put(pieza.getID(),pieza);
	}
	
	public  void editarDineroActual(double dineroActual, double tarjetaCredito, double transferenciaElectronica, double efectivo, Map<String, Double> map) {
		/* No se le presentara al comprador para que la use. el cajero es el que debe usarla (para descontar el dinero en una compra por ejemplo).*/
		setDineroActual(dineroActual);
		map.put("tarjetaCredito",tarjetaCredito);
		map.put("transferenciaElectronica",transferenciaElectronica);
		map.put("efectivo", efectivo);
		setMetodoPago(map);
	}

	public void setDineroActual(double dineroActual) {
		this.dineroActual = dineroActual;
	}

	public void setLimiteCompras(double limiteCompras) {
		this.limiteCompras = limiteCompras;
	}

	public void setInfoCompras(HashMap<String, Pieza> infoCompras) {
		this.infoCompras = infoCompras;
	}

	public void setMetodoPago(Map<String, Double> map) {
		this.metodoPago= (HashMap<String, Double>) map ;
	}
	
	public void setVerificado(boolean nuevoValor)
	{
		this.verificado = nuevoValor;
	}
	public List<String> getPasarelas() {
		return pasarelas;
	}
}
