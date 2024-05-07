package Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public boolean verificarCompra (Comprador comprador, Pieza pieza, double valor, Cajero cajero) {
		/* Verifica que todo este en orden para realizar una compra si es asi entonces la aprueba*/
		
		boolean verificado = this.verificarUsuario(comprador);
		boolean limite = this.verificarLimite(comprador, valor, cajero);
		
		if ((verificado == true) && (limite == true))
			return true;
		else
			return false;
	}
	
	public boolean verificarUsuario (Comprador comprador) {
		/* Verifica un usuario para que sea comprador o propietario*/
		
		boolean verificado = comprador.getVerificado();
		if (verificado == false)
		{
			double dineroActual = comprador.getDineroActual();
			
			double limiteCompras = Math.round((dineroActual - (dineroActual/3)* 100.0) / 100.0);
			comprador.setLimiteCompras(limiteCompras);
			comprador.setVerificado(true);
		}
		
		return true;
	}
	
	public void verificarDevolucion (Comprador comprador, Pieza pieza) {
		/* Verifica que todo este en orden para realizar una devolucion si es asi entonces la aprueba*/
		
		comprador.devolverPieza(pieza);
		pieza.setDevolucion(true);
	}
	
	public void cambiarEstadoObra (Pieza pieza, String llave, String valor) {
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
	
	public boolean verificarLimite (Comprador comprador, double valor, Cajero cajero) {
		/* Verifica que todo este en orden segun el limite del comprador y el dinero actual*/
		
		double dineroActual = comprador.getDineroActual();
		HashMap<String, Double> metodoPago= (HashMap<String, Double>) comprador.getMetodoPago();
		
		boolean saldo = cajero.verificarSaldo(valor, comprador, metodoPago, dineroActual);
		boolean limite = cajero.verificarLimite(valor, comprador);
		
		if ((saldo && limite) == true)
			return true;
		else
			return false;
		
	}
	
	public void agregarPieza (Comprador comprador, Pieza pieza) {
		/* Agrega una pieza a el inventario del comprador y hace todos los procesos necesarios para sacarlo del inventario*/
		comprador.agregarPieza(pieza);
	}
	
	public void ingresarPieza (Pieza pieza) {
		/* Ingresa una pieza totalmente nueva al inventario de la galeria*/
		Inventario.agregarPiezaInventario(pieza);
	}
	
	public void ingresarPiezaConsignacion (Pieza pieza, String fechaLim) {
		/* Ingresa una pieza totalmente nueva al inventario de la galeria en estado de consignacion*/
		Inventario.modificarConsignacion(fechaLim, pieza);
		Inventario.agregarPiezaInventario(pieza);
	}
	
	public Map<String, Pieza> piezasCompradasProp(Propietario propietario)
	{
		Map<String, Pieza> piezas = propietario.getHistorialPiezas();
		
		return piezas;
	}
	
	public List<Pieza> piezasDuenioProp(Propietario propietario)
	{
		List<Pieza> piezas = propietario.getPiezasActuales();
		return piezas;
	}
	
	public double valorColleccion(Propietario propietario)
	{
		List<Pieza> listaPiezas = propietario.getPiezasActuales();
		
		double valor = 0;
		
		for (int i=0; i < listaPiezas.size(); i++)
    	{
    		Pieza pieza = listaPiezas.get(i);
    		double valorPieza= pieza.getValor();
    		
    		valor += valorPieza;
    	}
		
		return valor;
	}
}
