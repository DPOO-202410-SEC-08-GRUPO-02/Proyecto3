package Usuario;

import java.util.HashMap;
import java.util.Map;

import Compra.Compra;
import Inventario.Pieza;
import Subasta.Subasta;

public class Comprador extends Cliente{
	private double dineroActual;
	private double limiteCompras;
	private HashMap<String,Pieza> infoCompras= new HashMap<String,Pieza>();
	private Map<String,Double> metodoPago= new HashMap<String,Double>();
	private Propietario Propietario;
	
	public Comprador (String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean Verificado,double dineroActual, double LimiteCompras, Map<String,Double> MetodoPago ) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero,Tipo,Verificado);
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
	}

	public double getDineroActual() {
		return dineroActual;
	}

	public double getLimiteCompras() {
		return limiteCompras;
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
	
	public void comprarPieza(Pieza pieza,Comprador comprador, Administrador admin, Cajero cajero) {
		/* El usuario podra elegir una pieza en el Catalogo para comprar*/
		Compra.pasarCaja(comprador, pieza, "Compra normal", admin, cajero);
	}
	
	public void editarLimite(double nuevoLimite) {
		/* No se le presentara al comprador para que la use. el administrador es el que debe usarla.*/ 
	}
	
	public void devolverPieza(Pieza pieza) {
		/* No se le presentara al comprador para que la use. el administrador es el que debe usarla.*/
		
		this.infoCompras.remove(pieza.getID(),pieza);
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
	
}
