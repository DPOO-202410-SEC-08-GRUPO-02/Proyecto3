package Usuario;

import java.util.HashMap;

import CargadorGaleria.Galeria;
import Inventario.Inventario;
import Inventario.Pieza;

public class Administrador extends Empleado{
	
	public Administrador(String Login, String Contraseña,String ID,String Nombre,String Correo,int Numero, String Tipo, boolean AccesoGaleria) {
		super(Login,Contraseña,ID,Nombre,Correo, Numero,Tipo, AccesoGaleria);
		this.login=Login;
		this.contraseña=Contraseña;
		this.iD=ID;
		this.nombre=Nombre;
		this.correo=Correo;
		this.numero=Numero;
		this.tipo=Tipo;
		this.accesoGaleria=AccesoGaleria;
	}
	
	public static boolean verificarCompra (Comprador comprador, Pieza pieza, double valor) {
		/* Verifica que todo este en orden para realizar una compra si es asi entonces la aprueba*/
		
		boolean verificado = Administrador.verificarUsuario(comprador);
		boolean limite = Administrador.verificarLimite(comprador, valor);
		
		if ((verificado && limite) == true)
			return true;
		else
			return false;
	}
	
	public static boolean verificarUsuario (Cliente cliente) {
		/* Verifica un usuario para que sea comprador o propietario*/
		boolean verificado = cliente.getVerificado();
		if (verificado == false)
		{
			String id = cliente.getID();
			Object obj = Galeria.getUsuario(id);
			Comprador comprador = (Comprador) obj;
			double dineroActual = comprador.getDineroActual();
			
			double limiteCompras = Math.round((dineroActual - (dineroActual/3)* 100.0) / 100.0);
			comprador.setLimiteCompras(limiteCompras);
		}
		
		return true;
	}
	
	public static void verificarDevolucion (Comprador comprador, Pieza pieza) {
		/* Verifica que todo este en orden para realizar una devolucion si es asi entonces la aprueba*/
		
		comprador.devolverPieza(pieza);
		pieza.setDevolucion(true);
	}
	
	public static void cambiarEstadoObra (Pieza pieza, String llave, String valor) {
		/* Cambia el estado y disponibilidad de una obra*/
		if (llave == "disponibilidad")
		{
			pieza.setDisponibilidad(Boolean.parseBoolean(valor));
		}
		
		else if (llave == "estado")
		{
			pieza.setEstado(valor);
		}
	}
	
	public static boolean verificarLimite (Comprador comprador, double valor) {
		/* Verifica que todo este en orden segun el limite del comprador y el dinero actual*/
		
		double dineroActual = comprador.getDineroActual();
		HashMap<String, Double> metodoPago= (HashMap<String, Double>) comprador.getMetodoPago();
		
		boolean Saldo =Cajero.verificarSaldo(valor, comprador, metodoPago,dineroActual );
		boolean Limite = Cajero.verificarLimite(valor, comprador);
		
		if ((Saldo && Limite) == true)
			return true;
		else
			return false;
		
	}
	
	public static void agregarPieza (Comprador comprador, Pieza pieza) {
		/* Agrega una pieza a el inventario del comprador y hace todos los procesos necesarios para sacarlo del inventario*/
		comprador.agregarPieza(pieza);
	}
	
	public static void ingresarPieza (Pieza pieza) {
		/* Ingresa una pieza totalmente nueva al inventario de la galeria*/
		Inventario.agregarPiezaInventario(pieza);
	}
	
	public static void ingresarPiezaConsignacion (Pieza pieza, String fechaLim) {
		/* Ingresa una pieza totalmente nueva al inventario de la galeria en estado de consignacion*/
		Inventario.modificarConsignacion(fechaLim, pieza);
		Inventario.agregarPiezaInventario(pieza);
	}
}
