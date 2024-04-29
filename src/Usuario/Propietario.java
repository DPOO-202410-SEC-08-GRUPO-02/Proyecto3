package Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Inventario.Pieza;

public class Propietario extends Cliente{
	
	private  List<String> estadoPiezas= new ArrayList<String>( );
	private Map<String,Pieza> historialPiezas= new HashMap<String,Pieza>();
	private Comprador comprador;
	
	public Propietario (String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean Verificado, 
			List<String> EstadoPiezas, Map<String,Pieza> HistorialPiezas) 
	{
		super(Login,Contraseña,ID,Nombre,Correo, Numero, Tipo, Verificado);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.verificado=Verificado;
		this.estadoPiezas=EstadoPiezas;
		this.historialPiezas = HistorialPiezas;
	}

	public List<String> consultarEstado() {
		/* Devuelve una lista de string con el ID/Nombre de la pieza y el estado en cada posicion*/
		return estadoPiezas;
	}

	 public void consignarPieza(Pieza pieza, String fechaLim, Administrador admin) {
		 /* Da una pieza a la Galeria con el atributo consignacion TRUE y una fecha limite*/
		 admin.ingresarPiezaConsignacion(pieza, fechaLim);
		 
	 }
	 
	 public Map<String,Pieza> consultarHistorial() {
		 /* Devuelve una lista con todas las piezas que fueron del Usuario y estan en consignacion o ya estan vendidas en cada posicion*/
		 return historialPiezas;
	}

	public List<String> getEstadoPiezas() {
		return estadoPiezas;
	}

	public Map<String, Pieza> getHistorialPiezas() {
		return historialPiezas;
	}

	public Comprador getComprador() {
		return comprador;
	}
	 
}

